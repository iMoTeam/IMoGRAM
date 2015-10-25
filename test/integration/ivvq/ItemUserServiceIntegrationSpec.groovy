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
        List<ItemUser> items = itemUserService.getAllUserItemDAO(user)

        then: "The list isn't null and empty"
        items != null && !items.empty

        and: "the list has two Itemuser instance"
        items.size() == 2
    }

    void "test that the details of a list items is correct"() {

        given: "a list of item user"
        List<ItemUser> list = new ArrayList<>();
        list.push(iu1);
        list.push(iu2);

        when: "we try to get the details of this list"
        Map<String, Integer> details = itemUserService.getDetailsItems(list);

        then: "The details number is the correct set"
        details.book == 1
        details.movie == 1
        details.tvShow == 0
    }
}
