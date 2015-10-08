package ivvq

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ArrayClass)
class ArrayClassSpec extends Specification {

    ArrayClass string

    def setup() {
        string = new ArrayClass()
    }

    def cleanup() {
    }

    void "test that constraints of a Genre are valids"() {
        given: "a genre"
        string.title = aString

        when: "the Movie is validated"
        def valid = string.validate()

        then: "Constraints checking are corrects"
        valid == expectedState

        where:
        aString   | expectedState
        "String" | true
        ""       | false
        null     | false

    }
}
