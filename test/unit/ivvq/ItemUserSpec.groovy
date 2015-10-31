package ivvq

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ItemUser)
class ItemUserSpec extends Specification {

    ItemUser itemUser

    def setup() {
        itemUser = new ItemUser()
    }

    def cleanup() {
    }

    void "test on the itemUser constraints are valids"() {
        given: "an itemUser"
        itemUser.user = aUser
        itemUser.tvShow = aTvShow
        itemUser.book = aBook
        itemUser.movie = aMovie
        itemUser.comments = aComments
        itemUser.rating = aRating
        itemUser.favourite = aFavourite
        itemUser.interested = anInterested

        when: "the itemUser is validated"
        def valid = itemUser.validate()

        then: "Constraints checking are correct"
        valid == expectedState

        where:
        aUser      | aTvShow      | aBook      | aMovie      | aComments | aFavourite | aRating | anInterested | expectedState
        Mock(User) | Mock(TVShow) | null       | null        | null      | false      | 1       | false         | true
        Mock(User) | null         | Mock(Book) | null        | null      | false      | 1       | false         | true
        Mock(User) | null         | null       | Mock(Movie) | null      | false      | 1       | false         | true
        Mock(User) | null         | null       | Mock(Movie) | null      | false      | null    | false         | true
        Mock(User) | Mock(TVShow) | null       | null        | null      | false      | -10     | false         | false
    }
}
