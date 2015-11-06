package ivvq


import spock.lang.*

/**
 *
 */
class UserServiceIntegrationSpec extends Specification {

    def userService

    def setup() {
    }

    def cleanup() {
    }

    void "test that the delete action correctly delete the user and all the item related to it"() {
        given: "a specific user arleady registered"
        User user = User.findByUsername("Veoth")

        when: "we search for max 20 movies which belong are related to this user"
        List<ItemUser> items = ItemUser.findAllByUser(user)

        then: "The size of the list is not null"
        items.size() != null

        when: "the delete method is called"
        userService.deleteUser(user)

        then: "all the items are deleted"
        ItemUser.findAllByUser(user).size() == 0

        and: "the user is also deleted"
        User.findByUsername("Veoth") == null
    }
}
