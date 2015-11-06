package ivvq


import grails.test.mixin.*
import spock.lang.*

@TestFor(ItemUserController)
@Mock([ItemUser,Book, Comment,Movie, TVShow])
class ItemUserControllerSpec extends Specification {


    def populateValidParams(params) {
        assert params != null
        populateValidMovieParams(params)
        Movie movie = new Movie(params)
        params["user"] = Mock(User)
        params["movie"] = movie
        params["tvShow"] = null
        params["book"] = null
        params["rating"] = 1
        params["comments"] = null
        params["favorite"] = false


    }

    ItemUserService itemUserService = Mock(ItemUserService)

    def setup() {

        controller.itemUserService = itemUserService
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.itemUserInstanceList
        model.itemUserInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.itemUserInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        def itemUser = new ItemUser()
        itemUser.validate()
        controller.save(itemUser)

        then: "The create view is rendered again with the correct model"
        model.itemUserInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        itemUser = new ItemUser(params)

        controller.save(itemUser)

        then: "A redirect is issued to the show action"
        itemUser.hasErrors() == false
        response.redirectedUrl == '/itemUser/show/1'
        controller.flash.message != null
        ItemUser.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def itemUser = new ItemUser(params)
        controller.show(itemUser)

        then: "A model is populated containing the domain instance"
        model.itemUserInstance == itemUser
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def itemUser = new ItemUser(params)
        controller.edit(itemUser)

        then: "A model is populated containing the domain instance"
        model.itemUserInstance == itemUser
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/itemUser/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def itemUser = new ItemUser()
        itemUser.validate()
        controller.update(itemUser)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.itemUserInstance == itemUser

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        itemUser = new ItemUser(params).save(flush: true)
        controller.update(itemUser)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/itemUser/show/$itemUser.id"
        flash.message != null
    }

    def populateValidBookParams(params) {
        assert params != null
        params["googleID"] = "SteVfQT2WY0C"
        params["title"] = "Titre"
        params["publishedDate"] = "22-05-15"
        params["author"] = "Auteur"
        params["publisher"] = "Maison des Cartes"
        params["pageCount"] = 50
        params["isbn13"] = "9782709637404"
    }

    def populateValidMovieParams(params) {
        assert params != null
        params["imdbID"] = "tt4545341"
        params["title"] = "title"
        params["releaseDate"] = "22-09-15"
        params["runtime"] = "127 min"
        params["genre"] = "Snuff"
        params["director"] = "Michael Bay"
        params["writers"] = "M. Pokora"
        params["actors"] = "Jackie Chan"
        params["country"] = "Kinder"
        params["plot"] = "Il était une fois la vie"
        params["poster"] = "Image"
    }


    def populateValidTVShowParams(params) {
        assert params != null
        params["imdbID"] = "tt0107290"
        params["title"] = "The Movie"
        params["releaseDate"] = "22-09-15"
        params["runtime"] = "127"
        params["network"] = "OCS"
        params["overview"] = "Résume"
        params["airedEpisodes"] = 50
        params["country"] = "Madagascar"
    }

    void "Test that the CommentItem action posts the correctly filled comment on a Book and redirects to the right page"() {
        given: "an item and a user"
        populateValidBookParams(params)
        Book book = new Book(params)
        User user = Mock(User)

        when: "It is added into the database "
        book.save(flush: true)

        then: "It exists in the database ready to for a user to comment"
        Book.findByIsbn13("9782709637404").title == "Titre"
        Book.count() ==  1


        when:"User comments with title and comment fields filled"
        controller.session['currentUser'] = user;
        params['itemComment'] = "loved this book, wish every one gets to read this"
        params['title'] = "The best book ever"
        params['itemBookId'] = book.id
        controller.commentItem()

        then: "The user is redirected to the show page of the item they were commenting"
        response.redirectedUrl != null
        response.redirectedUrl == "/book/show/" + book.id

        and: "A new comment is added into the database"
        Comment.count() == 1
}
    void "Test that the CommentItem action doesn't post the incorrectly filled comment on a Book but redirects to the right page"() {
        given: "an item and a user"
        populateValidBookParams(params)
        Book book = new Book(params)
        User user = Mock(User)

        when: "It is added into the database "
        book.save(flush: true)

        then: "It exists in the database ready to for a user to comment"
        Book.findByIsbn13("9782709637404").title == "Titre"
        Book.count() ==  1

         when: "User tries to comment an item with an empty comment title and an empty comment text"
         controller.session['currentUser'] = user;
         params['itemComment'] = ""
         params['title'] = ""
         params['itemBookId'] = book.id

         controller.commentItem()

         then: "The user has an error message and the comment isnt posted"
         controller.flash.error != ""

         and: "The error message is Erreur: Votre commentaire n'est pas posté, verifiez que tous les champs sont saisis"
         controller.flash.error == "Erreur: Votre commentaire n'est pas posté, verifiez que tous les champs sont saisis"

         and: "The user is redirected to the show page of the item they were commenting"
         response.redirectedUrl != null
         response.redirectedUrl == "/book/show/" + book.id

    }

    void "Test that the CommentItem action posts the correctly filled comment on a Movie and redirects to the right page"() {
        given: "an item and a user"
        populateValidMovieParams(params)
        Movie movie = new Movie(params)
        User user = Mock(User)

        when: "It is added into the database "
        movie.save(flush: true)

        then: "It exists in the database ready to for a user to comment"
        Movie.findByImdbID("tt4545341").title == "title"
        Movie.count() ==  1


        when: "The user Comments on a movie with correct title and comment"
        controller.session['currentUser'] = user;
        params['itemComment'] = "loved this movie, wish every one gets to see this"
        params['title'] = "Boom Boom Poow"
        params['itemMovieId'] = movie.id
        controller.commentItem()

        then: "The user is redirected to the show page of the item they were commenting"
        response.redirectedUrl != null
        response.redirectedUrl == "/movie/show/" + movie.id

        and: "A new comment is added into the database"
        Comment.count() == 1

    }

    void "Test that the CommentItem action posts the correctly filled comment on a TVShow and redirects to the right page"() {
        given: "an item and a user"
        populateValidTVShowParams(params)
        TVShow tvShow = new TVShow(params)
        User user = Mock(User)

        when: "It is added into the database "
        tvShow.save(flush: true)

        then: "It exists in the database ready to for a user to comment"
        TVShow.findByImdbID("tt0107290").title == "The Movie"
        TVShow.count() ==  1


        when: "The user Comments on a TVShow with correct title and comment"
        controller.session['currentUser'] = user;
        params['itemComment'] = "This is what we call a down to earth Tvshow"
        params['title'] = "Best Tv show ever"
        params['itemTVShowId'] = tvShow.id
        controller.commentItem()

        then: "The user is redirected to the show page of the item they were commenting"
        response.redirectedUrl != null
        controller.response.redirectedUrl == "/TVShow/show/" + tvShow.id

        and: "A new comment is added into the database"
        Comment.count() == 1

    }
}