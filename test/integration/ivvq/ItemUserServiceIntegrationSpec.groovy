package ivvq


import spock.lang.*

/**
 *
 */
class ItemUserServiceIntegrationSpec extends Specification {

    def itemUserService
    User user
    ItemUser iu1, iu2

    def setup() {
        user = new User(firstName: "Hugues", lastName: "Odegaard", username: "Veoth", email: "hugues.odegaard@gmail.com", password:"youhou").save(flush: true)
        iu1 = new ItemUser(book: Book.findByGoogleID("4hNrYIhNqUEC"), user: user, rating: 7).save(flus: true)
        iu2 = new ItemUser(movie: Movie.findByImdbID("tt0107290"), user: user, rating: 8).save(flus: true)
    }

    def cleanup() {
    }

    void "test that all user's items (rated or favourite) are correctly returned"() {
        given: "a specific user"
        User user = User.findByUsername("Veoth")

        when: "we try get find the items linked to this user"
        List<ItemUser> items = itemUserService.getAllUserItemDAO(user, 20, 0, null, null)

        then: "The list isn't null and empty"
        items != null && !items.empty

        and: "the list has two Itemuser instance"
        items.size() == 16
    }
}
