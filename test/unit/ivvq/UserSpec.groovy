package ivvq

import grails.test.mixin.TestFor
import org.springframework.mock.web.MockMultipartFile
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    User user = new User()

    void "test valid user"() {
        given:"a user correctly set"
        user.firstName = "firstname"
        user.lastName = "lastname"
        user.username = "toto"
        user.email = "toto@toto.com"
        user.password = "pwd1234"
        user.profilePhoto = "1234567" as byte[]


        when:"trying to validate the user"
        def res = user.validate()

        then:"the user is valid"
        res == true

        and:"the user no has errors"
        !user.hasErrors()
    }

    void "test invalid user"() {
        given:"a user with a good firstName, lastName, username and password"
        User user = new User()
        user.firstName = "firstname"
        user.lastName = "lastname"
        user.username = "toto"
        user.password = "pwd1234"

        and: "with an invalid email"
        user.email = "totototo.com"


        when:"trying to validate the user"
        def res = user.validate()

        then:"the user is not valid"
        res == false

        and:"the user has errors"
        user.hasErrors()
    }

    def "test invalid users with where clause"() {
        given: "a user"
        user.firstName = aFirstName
        user.lastName = aLastName
        user.username = aUsername
        user.password = aPassword
        user.email = anEmail
        user.profilePhoto = aPhoto

        when: "validating the user"
        def isValid = user.validate()

        then: "the user is not valid"
        isValid == false
        where:
        aFirstName    | aLastName | aUsername | aPassword | anEmail | aPhoto
         "firstName" |"lastName" | "toto1"   | "toto1234" | "toto"  | "1234567" as byte[]
        "firstName" | "firstName" | ""        | "toto"    | "toto" | "1234567" as byte[]
    }
}
