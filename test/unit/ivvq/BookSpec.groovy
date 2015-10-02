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
    void "test a valid book"(String aName, Date aDateEdition, String aWriter, String aExtrait, String aImage) {
        given:"a book correctly set"
        Book book = new Book(name:aName, dateEdition: aDateEdition, writer: aWriter, extrait: aExtrait, image: aImage);

        expect:"the book is valid"
        book.validate() == true;

        where:
        aName        |   aDateEdition  |   aWriter  |      aImage       |   aExtrait
        "Eva"       |    new Date()   |  "Simon"  |    "im.jpg"    | "Plutot qu'une consciencieuse biographie."
        "La route" |    new Date()   |  "Cormac" |    "im.jpg"    |   null
        "Word War" |    new Date()   |    "Max"   |    "im.jpg"    |   ""
        "Word "     |    new Date()   |    "Max"   |      null      |   "Plutot qu'une consciencieuse biographie."
    }

    @Unroll
    void "test a invalid book"(String aName, Date aDateEdition, String aWriter, String aExtrait, String aImage) {
        given:"a book incorrectly set"
        Book book = new Book(name: aName, date: aDateEdition, writer:aWriter, extrait: aExtrait, image: aImage);

        expect:"the book is invalid"
        book.validate() == false;

        where:
        aName        |   aDateEdition  |   aWriter  |    aImage       |   aExtrait
         null       |    new Date()   |  "Simon"  |   "im.jpg"   |  "Plutot qu'une consciencieuse biographie."
        "La route" |    null         |  "Cormac" |   "im.jpg"   |   null
        "La route" |    new Date()   |     ""     |   "im.jpg"   |   null
        "Word War" |    new Date()   |    null    |    "im.jpg"  |    ""
           ""       |    new Date()   |    "Max"   |    "im.jpg"  |    ""
    }
}
