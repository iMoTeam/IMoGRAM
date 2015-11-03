package ivvq



import spock.lang.*

/**
 *
 */
class UserControllerIntegrationSpec extends Specification {
    def userService
    UserController controller
    def setup() {
        controller = new UserController()
        controller.userService = userService
    }

    def populateValidParams() {
        def params = [firstName: 'kilosa']
        assert params != null
        // TODO: Populate valid properties like...
        params["firstName"] = 'Brother'
        params["lastName"] = 'Kaka'
        params["username"] = 'kangaroo'
        params["email"] = 'yeeree@mailless.com'
        params["password"] = 'kiki1234'
        params["profilePhoto"] = null
        controller.params.putAll(params)
    }


    void "Test that the logged in user is in the session"(){

        when: "A domain instance is created"
        populateValidParams()
        def user = new User(controller.params).save(flush: true)

        then: "It exists"
        User.count() == 5

        when: "A user logs in"
        //response.reset()
        /*controller.params["username"] = 'kangaroo'
        controller.params["password"] = 'kiki1234'*/
        controller.loggedInUser()
        User userLoggedIn = controller.session.getAttribute("currentUser")

        then: "The user logged in is in the session"
        userLoggedIn != null

        then:
        userLoggedIn.username == 'kangaroo'

        then:
        userLoggedIn.password == 'kiki1234'

        then:
        userLoggedIn.email == 'yeeree@mailless.com'

    }
}
