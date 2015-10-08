package ivvq

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Role)
class RoleSpec extends Specification {

    Role role

    def setup() {
        role = new Role()
    }

    def cleanup() {
    }

    void "test that constraints of a Role are valids"() {
        given: "a role"
        role.realName = aRealName
        role.role = aRole

        when: "the Movie is validated"
        def valid = role.validate()

        then: "Constraints checking are corrects"
        valid == expectedState

        where:
        aRealName | aRole   | expectedState
        "aName"   | "aRole" | true
        ""        | "aRole" | false
        null      | "aRole" | false
        "aName"   | ""      | false
        "aName"   | null    | false
    }
}
