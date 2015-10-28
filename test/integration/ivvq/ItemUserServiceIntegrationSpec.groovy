package ivvq


import spock.lang.*

/**
 *
 */
class ItemUserServiceIntegrationSpec extends Specification {

    def itemUserService

    def setup() {
    }

    def cleanup() {
    }

    void "test that the search function return the correct collection"() {
        given: "a specific user arleady registered"
        User user = User.findByUsername("Veoth")

        when: "we search for max 20 movies which belong are related to this user"
        List<ItemUser> items = itemUserService.getAllUserItemDAO(user, 20, 0, "movie", null)

        then: "The size of the list is 7"
        items.size() == 7

        and: "all the items are movies"
        items.each {
            it.movie != null
        }

        when: "we search for max 20 items which have been rated by the user"
        List<ItemUser> items2 = itemUserService.getAllUserItemDAO(user, 20, 0, null, "rating")

        then: "The size of the list is 7"
        items.size() == 7

        and: "all the items have a rate"
        items.each {
            it.rating != null
        }
    }

    void "test that an item user can be correctly added to the database"() {
        given: "an item user with ratin, interessted and favourite set to null"
        User user = User.findByUsername("Veoth")
        Book book = Mock(Book)
        Integer rating = null
        Boolean favourite = null
        Boolean interested = null

        when: "we try to create an item user with those attribute"
        def itemUser = itemUserService.insertItemUser(user, book, rating, interested, favourite)

        then: "the return value is null"
        itemUser == null

        when: "we try to create an item user with correct value"
        def itemUser2 = itemUserService.insertItemUser(user, Book.findByGoogleID("ERghoa8HhNoC"), 8, true, true)

        then: "the return value isn't null"
        itemUser2 != null
    }
}
