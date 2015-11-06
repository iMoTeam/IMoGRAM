package ivvq


import grails.test.mixin.*
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionStatus
import spock.lang.*

@TestFor(UserController)
@Mock([User, ItemUser])
class UserControllerSpec extends Specification {

    def itemUserService
    def userService

    def setup() {
        itemUserService = new ItemUserService()
        itemUserService.transactionManager = Mock(PlatformTransactionManager) {getTransaction(_) >> Mock(TransactionStatus)}

        userService = new UserService()
        userService.transactionManager = Mock(PlatformTransactionManager) {getTransaction(_) >> Mock(TransactionStatus)}
    }

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        params["firstName"] = 'Brother'
        params["lastName"] = 'Kaka'
        params["username"] = 'kangaroo'
        params["email"] = 'yeeree@mailless.com'
        params["password"] = 'kiki1234'
        params["profilePhoto"] = null
    }

    void "Test the index action returns the correct model"() {

        setup:
        controller.itemUserService = itemUserService

        when: "The index action is executed"
        populateValidParams(params)
        controller.session["currentUser"] = new User(params).save(flush: true)

        then: "The user exist"
        User.count == 1

        when: "The index action is called"
        controller.index()

        then: "The model is correct"
        model.items != null
        model.itemsCount != null
    }

    void "Test the search action return correct model"() {

        setup:
        controller.itemUserService = itemUserService

        when: "The search action is executed with a user previously logged"
        populateValidParams(params)
        controller.session["currentUser"] = new User(params).save(flush: true)
        controller.recherche()

        then: "The model is correctly set"
        model.items != null
        model.itemsCount != null
    }


    void "test that the delete action return the correct model"() {
        setup:
        controller.userService = userService

        when: "The search action is executed with a user previously logged"
        populateValidParams(params)
        controller.session["currentUser"] = new User(params).save(flush: true)

        then: "The user exist"
        User.count == 1

        when: "The delete action is called"
        controller.deleteUser()

        then: "The user is deleted"
        User.count == 0

        and: "the session user is set to null"
        controller.session["curretnUser"] == null
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.userInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        def user = new User()
        user.validate()
        controller.save(user)

        then: "The create view is rendered again with the correct model"
        model.userInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        user = new User(params)

        controller.save(user)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/user/loginUser'
        controller.flash.message != null
        User.count() == 1
    }

    void "Test that the recherche action returns the correct model"() {
        when: "The recherche action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def user = new User(params)
        controller.show(user)

        then: "A model is populated containing the domain instance"
        model.userInstance == user
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def user = new User(params)
        controller.show(user)

        then: "A model is populated containing the domain instance"
        model.userInstance == user
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def user = new User(params)
        controller.edit(user)

        then: "A model is populated containing the domain instance"
        model.userInstance == user
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/user/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def user = new User()
        user.validate()
        controller.update(user)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.userInstance == user

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        user = new User(params).save(flush: true)
        controller.update(user)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/user/show/$user.id"
        flash.message != null
    }
}
