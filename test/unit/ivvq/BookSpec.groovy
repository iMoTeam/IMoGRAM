package ivvq

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Book)
class BookSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    void "test a valid book"(String aName, Date aDateEdition, String aWriter, String aExtrait) {
        given:"a book correctly set"
        Book book = new Book(name:aName, dateEdition: aDateEdition, writer: aWriter, extrait: aExtrait);

        expect:"the book is valid"
        book.validate() == true;

        where:
        aName        |   aDateEdition  |   aWriter  |   aExtrait
        "Eva"       |    new Date()   |  "Simon"  |  "Plutot qu'une consciencieuse biographie."
        "La route" |    new Date()   |  "Cormac" |    null
        "Word War" |    new Date()   |    "Max"   |    ""
        "Word "     |    new Date()   |    "Max"   |    ""
    }

    @Unroll
    void "test a invalid book"(String aName, Date aDateEdition, String aWriter, String aExtrait) {
        given:"a book incorrectly set"
        Book book = new Book(name: aName, date: aDateEdition, writer:aWriter, extrait: aExtrait);

        expect:"the book is invalid"
        book.validate() == false;

        where:
        aName        |   aDateEdition  |   aWriter  |   aExtrait
         null       |    new Date()   |  "Simon"  |  "Plutot qu'une consciencieuse biographie."
        "La route" |    null         |  "Cormac" |    null
        "La route" |    new Date()   |     ""     |    null
        "Word War" |    new Date()   |    null    |    ""
           ""       |    new Date()   |    "Max"   |    ""
           "test"  |    new Date()   |    "Max"   |    ""
    }
}
