package ivvq


import grails.test.mixin.*
import spock.lang.*

@TestFor(ItemUserController)
@Mock([ItemUser, Book, Movie, TVShow, User])
class ItemUserControllerSpec extends Specification {

    def populateValidParamsUser() {
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
        populateValidParamsUser()
        Movie movie = new Movie(params)
        movie.save(flush: true)
        params["user"] = new User(params)
        params["movie"] = movie
        params["tvShow"] = null
        params["book"] = null
        params["rating"] = null
    }

    void "Test that the rateItem action rate on a movie"() {
        given: "An item and a user"
        populateValidParamsMovie(params)
        Movie movie = new Movie(params)
        movie.save(flush: true)
        params['movieId'] = movie.id
        params['itemRating'] = '5'
        populateValidParamsUser()
        User user = new User(params)
        user.save(flush: true)

        when: "The user rate the movie"
        controller.rateItem()

        then: "The user is redirected to the show page of the item he have rated"
        response.redirectedUrl != null
        response.redirectedUrl == "/movie/show/"+movie.id

        and: "The rating is added for the user"
        ItemUser.findByBookAndUser(movie, user) != null
        Integer oldRating = ItemUser.findByMovieAndUser(movie, user).rating
        oldRating != null

        when: "The user update his rating for the movie"
        controller.rateItem()

        then: "The rating is updated for the movie"
        Integer newRating = ItemUser.findByMovieAndUser(movie, user).rating
        newRating != oldRating

    }




    /*void "Test the index action returns the correct model"() {

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

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/itemUser/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def itemUser = new ItemUser(params).save(flush: true)

        then: "It exists"
        ItemUser.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(itemUser)

        then: "The instance is deleted"
        ItemUser.count() == 0
        response.redirectedUrl == '/itemUser/index'
        flash.message != null
    }*/
}
