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

    void "test a valid comment"(User aUser, String aTitle, Date aDate, String aComment) {
        given:"a comment correctly set"
        Comment comment = new Comment(user: aUser, title: aTitle, date: aDate, comment: aComment);

        expect:"the comment is valid"
        comment.validate() == true

        where:
        aUser        |      aTitle    |     aDate      |   aComment
        Mock(User)   |      "Cool !" |    new Date()  |  "Interesting movie, I love it !"
    }

    void "test a invalid comment"(User aUser, String aTitle, Date aDate, String aComment) {
        given:"a comment incorrectly set"
        Comment comment = new Comment(user: aUser, date: aDate, comment: aComment, title: aTitle)

        expect:"the comment is invalid"
        comment.validate() == false

        where:
          aUser      |      aTitle    |     aDate      |   aComment
           null     |    "cool !"   |   new Date()   |  "Interesting movie, I love it !"
        Mock(User)   |    "cool !"   |   new Date()   |  ""
        Mock(User)   |    "cool !"   |   new Date()   |  null
        Mock(User)   |    ""          |   new Date()   |  "Interesting movie, I love it !"
        Mock(User)   |    null        |   new Date()   |  "Interesting movie, I love it !"
    }
}
