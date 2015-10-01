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
        itemUser.song = aSong
        itemUser.book = aBook
        itemUser.movie = aMovie
        itemUser.comments = aComments

        when: "the itemUser is validated"
        def valid = itemUser.validate()

        then: "Constraints checking are correct"
        valid == expectedState

        where:
        aUser|aSong|aBook|aMovie|aComments|expectedState
        Mock(User)|Mock(Song)|null|null|null|true
        Mock(User)|null|Mock(Book)|null|null|true
        Mock(User)|null|null|Mock(Movie)|null|true

    }


}
