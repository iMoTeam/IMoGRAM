package ivvq


import grails.test.mixin.*
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionStatus
import spock.lang.*

@TestFor(TVShowController)
@Mock([TVShow, ItemUser, User])
class TVShowControllerSpec extends Specification {

    def itemUserService

    def setup() {
        itemUserService = new ItemUserService()
        itemUserService.transactionManager = Mock(PlatformTransactionManager) {getTransaction(_) >> Mock(TransactionStatus)}
        controller.itemUserService = itemUserService
    }

    def populateValidParams(params) {
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

    def populateValidParamsUser(params) {
        assert params != null

        params["firstName"] = 'Barry'
        params["lastName"] = 'Lindon'
        params["username"] = 'deNiro'
        params["email"] = 'smala@maill.com'
        params["password"] = 'lalala'
        params["profilePhoto"] = null
        params["following"] = new HashSet()
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.TVShowInstanceList
        model.TVShowInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.TVShowInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        def TVShow = new TVShow()
        TVShow.validate()
        controller.save(TVShow)

        then: "The create view is rendered again with the correct model"
        model.TVShowInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        TVShow = new TVShow(params)

        controller.save(TVShow)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/TVShow/show/1'
        controller.flash.message != null
        TVShow.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def TVShow = new TVShow(params)
        controller.show(TVShow)

        then: "A model is populated containing the domain instance"
        model.TVShowInstance == TVShow
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def TVShow = new TVShow(params)
        controller.edit(TVShow)

        then: "A model is populated containing the domain instance"
        model.TVShowInstance == TVShow
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/TVShow/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def TVShow = new TVShow()
        TVShow.validate()
        controller.update(TVShow)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.TVShowInstance == TVShow

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        TVShow = new TVShow(params).save(flush: true)
        controller.update(TVShow)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/TVShow/show/$TVShow.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/TVShow/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def TVShow = new TVShow(params).save(flush: true)

        then: "It exists"
        TVShow.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(TVShow)

        then: "The instance is deleted"
        TVShow.count() == 0
        response.redirectedUrl == '/TVShow/index'
        flash.message != null
    }

    void "test that the deletion of a favourite tvShow works" () {

        when: "the itemUser favourite is created with tvShow and user"
        populateValidParams(params)
        TVShow tvShow = new TVShow(params).save(flush: true)
        populateValidParamsUser(params)
        User user = new User(params).save(flush: true)
        ItemUser itemUser = new ItemUser(user: user, tvShow: tvShow, favourite: true).save(flush: true)

        then: "the itemUser exists"
        ItemUser.count() == 1

        when: "the tvShwo is deleted to the favourite"
        session.currentUser = user
        controller.deleteToFavourite(tvShow)

        then: "the itemUser doesn't exist anymore"
        ItemUser.count() == 0

    }

    void "test that the add of a favourite tvShow works" () {

        given: "a tvShow and a user"
        populateValidParams(params)
        TVShow tvShow = new TVShow(params).save(flush: true)
        populateValidParamsUser(params)
        User user = new User(params).save(flush: true)

        when: "the itemUser favourite is created with tvShow and user"
        session.currentUser = user
        controller.addToFavourite(tvShow)

        then: "the itemUser exists"
        ItemUser.count() == 1

        and: "it's favourite"
        itemUserService.getItemUser(user, tvShow).favourite == true

    }
}
