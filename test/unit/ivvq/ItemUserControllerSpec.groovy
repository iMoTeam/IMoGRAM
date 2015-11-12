package ivvq


import grails.test.mixin.*
import spock.lang.*

@TestFor(ItemUserController)
@Mock([ItemUser, Book, Movie, TVShow, User])
class ItemUserControllerSpec extends Specification {

    def populateValidParamsUser(params) {
        assert params != null
        params["firstName"] = 'Brother'
        params["lastName"] = 'Kaka'
        params["username"] = 'kangaroo'
        params["email"] = 'yeeree@mailless.com'
        params["password"] = 'kiki1234'
        params["profilePhoto"] = null
        params["following"] = new HashSet()
    }

    def populateValidParamsMovie(params) {
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
        params["plot"] = "Il ¨¦tait une fois la vie"
        params["poster"] = "Image"
    }

    def populateValidParamsBook(params) {
        assert params != null
        params["googleID"] = "SteVfQT2WY0C"
        params["isbn13"] = "9782709637404"
        params["title"] = "Da Vinci code"
        params["publishedDate"] = "22-05-15"
        params["author"] = "Auteur"
        params["publisher"] = "Maison des Cartes"
        params["pageCount"] = 50
    }

    def populateValidParamsTVShow(params) {
        assert params != null
        params["imdbID"] = "tt0107290"
        params["title"] = "The Movie"
        params["releaseDate"] = "22-09-15"
        params["runtime"] = "127"
        params["network"] = "OCS"
        params["overview"] = "R¨¦sume"
        params["airedEpisodes"] = 50
        params["country"] = "Madagascar"
    }

    void "Test that the rateItem action rate on a movie not rated"() {
        given: "An item and a user"
        populateValidParamsMovie(params)
        Movie movie = new Movie(params).save(flush: true)
        populateValidParamsUser(params)
        User user = new User(params).save(flush: true)

        when: "The user rate the movie"
        session['currentUser'] = user
        params['movieId'] = movie.imdbID
        params['itemRating'] = '5'
        controller.rateItem()

        then: "The user is redirected to the show page of the item he have rated"
        response.redirectedUrl != null
        response.redirectedUrl == "/movie/show/" + movie.id

        and: "The rating is added for the user"
        ItemUser.findByMovieAndUser(movie, user) != null
        Integer oldRating = ItemUser.findByMovieAndUser(movie, user).rating
        oldRating != null

    }

    void "Test that the rateItem action rate on a movie already rated"() {
        given: "An item and a user and the itemUser associated"
        populateValidParamsMovie(params)
        Movie movie = new Movie(params).save(flush: true)
        populateValidParamsUser(params)
        User user = new User(params).save(flush: true)
        ItemUser itemUser = new ItemUser(movie: movie, user: user, rating: 10).save(flush: true)

        when: "The user rate the movie"
        session['currentUser'] = user
        params['movieId'] = movie.imdbID
        params['itemRating'] = '5'
        controller.rateItem()

        then: "The rating is updated for the movie"
        Integer newRating = ItemUser.findByMovieAndUser(movie, user).rating
        newRating == 5
    }

    void "Test that the rateItem action rate on a book not rated"() {
        given: "An item and a user"
        populateValidParamsBook(params)
        Book book = new Book(params).save(flush: true)
        populateValidParamsUser(params)
        User user = new User(params).save(flush: true)

        when: "The user rate the book"
        session['currentUser'] = user
        params['bookId'] = book.isbn13
        params['itemRating'] = '5'
        controller.rateItem()

        then: "The user is redirected to the show page of the item he have rated"
        response.redirectedUrl != null
        response.redirectedUrl == "/book/show/" + book.id

        and: "The rating is added for the user"
        ItemUser.findByBookAndUser(book, user) != null
        Integer oldRating = ItemUser.findByBookAndUser(book, user).rating
        oldRating != null
    }

    void "Test that the rateItem action rate on a book already rated"() {
        given: "An item and a user and the itemUser associated"
        populateValidParamsBook(params)
        Book book = new Book(params).save(flush: true)
        populateValidParamsUser(params)
        User user = new User(params).save(flush: true)
        ItemUser itemUser = new ItemUser(book: book, user: user, rating: 10).save(flush: true)

        when: "The user rate the book"
        session['currentUser'] = user
        params['bookId'] = book.isbn13
        params['itemRating'] = '5'
        controller.rateItem()

        then: "The rating is updated for the book"
        Integer newRating = ItemUser.findByBookAndUser(book, user).rating
        newRating == 5
    }

    void "Test that the rateItem action rate on a TVShow not rated"() {
        given: "An item and a user"
        populateValidParamsTVShow(params)
        TVShow tvShow = new TVShow(params).save(flush: true)
        populateValidParamsUser(params)
        User user = new User(params).save(flush: true)

        when: "The user rate the TVShow"
        session['currentUser'] = user
        params['tvShowId'] = tvShow.imdbID
        params['itemRating'] = '5'
        controller.rateItem()

        then: "The user is redirected to the show page of the item he have rated"
        response.redirectedUrl != null
        response.redirectedUrl == "/TVShow/show/" + tvShow.id

        and: "The rating is added for the user"
        ItemUser.findByTvShowAndUser(tvShow, user) != null
        Integer oldRating = ItemUser.findByTvShowAndUser(tvShow, user).rating
        oldRating != null
    }

    void "Test that the rateItem action rate on a TVShow already rated"() {
        given: "An item and a user and the itemUser associated"
        populateValidParamsTVShow(params)
        TVShow tvShow = new TVShow(params).save(flush: true)
        populateValidParamsUser(params)
        User user = new User(params).save(flush: true)
        ItemUser itemUser = new ItemUser(TVShow: tvShow, user: user, rating: 10).save(flush: true)

        when: "The user rate the TVShow"
        session['currentUser'] = user
        params['tvShowId'] = tvShow.imdbID
        params['itemRating'] = '5'
        controller.rateItem()

        then: "The rating is updated for the TVShow"
        Integer newRating = ItemUser.findByTvShowAndUser(tvShow, user).rating
        newRating == 5
    }


}