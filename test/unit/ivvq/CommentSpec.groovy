package ivvq

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Comment)
class CommentSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test a valid comment"(String aUsername, Date aDate, String aComment) {
        given:"a comment correctly set"
        Comment comment = new Comment(username: aUsername, date: aDate, comment: aComment);

        expect:"the comment is valid"
        comment.validate() == true

        where:
        aUsername    |      aDate      |   aComment
        "Eva"       |    new Date()   |  "Interesting movie, I love it !"
    }

    void "test a invalid comment"(String aUsername, Date aDate, String aComment) {
        given:"a comment incorrectly set"
        Comment comment = new Comment(username: aUsername, date: aDate, comment: aComment)

        expect:"the comment is invalid"
        comment.validate() == false

        where:
        aUsername    |      aDate      |   aComment
           null     |    new Date()   |  "Interesting movie, I love it !"
           "Eva"    |    new Date()   |  ""
           "Eva"    |    new Date()   |  null
    }
}
