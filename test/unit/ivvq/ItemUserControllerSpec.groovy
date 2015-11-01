package ivvq


import grails.test.mixin.*
import spock.lang.*

@TestFor(ItemUserController)
@Mock([ItemUser,Book])
class ItemUserControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        params["user"] = Mock(User)
        params["movie"] = Mock(Movie)
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

    def populateValidUserParams(params) {
        params["firstName"] = "firstname"
        params["lastName"] = "lastname"
        params["username"] = "toto"
        params["email"] = "toto@toto.com"
        params["password"] = "pwd1234"
        params["profilePhoto"] = null
    }

    void "Test that the CommentItem action posts the comment and redirects to the right page"() {
        given: "an item and a user"
        populateValidBookParams(params)
        populateValidUserParams(params)
        Book book = new Book(params)
        User user = Mock(User)

        when: "It is added into the database "
        book.save(flush: true)

        then: "It exists in the database ready to for a user to comment"
        Book.findByIsbn13("9782709637404").title == "Titre"

        when: "User tries to comment an item with an empty comment title and an empty comment text"
        controller.session['currentUser'] = user;
        params['itemComment'] = ""
        params['title'] = ""
        params['itemBookId'] = book.id

        controller.commentItem()

        then: "The user has an error message and the comment isnt posted"
        flash.error != ""







    }
}
