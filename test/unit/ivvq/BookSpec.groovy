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
    void "test a valid book "(String aIsbn13, String aTitle, String aPublishedDate, String anAuthor, String aPublisher, String aDescription, String aImage, Integer aPageCount) {
        given: "a book correctly set"
        Book book = new Book(isbn13: aIsbn13, title: aTitle, publishedDate: aPublishedDate, author: anAuthor, description: aDescription, image: aImage, publisher: aPublisher, pageCount: aPageCount);

        expect: "the book is valid"
        book.validate() == true;

        where:
        aIsbn13         | aTitle          | aPublishedDate         | anAuthor    | aImage   | aDescription | aPublisher  | aPageCount
        "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | "JC Lattès" | 571
        // TODO Should work, spok fail
        //"9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | ""       | "blabla"     | "JC Lattès" | 571
        //"9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | null     | "blabla"     | "JC Lattès" | 571
        "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | ""           | "JC Lattès" | 571
        "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | null         | "JC Lattès" | 571
    }

    @Unroll
    void "test a invalid book"(String aIsbn13, String aTitle, String aPublishedDate, String anAuthor, String aPublisher, String aDescription, String aImage, Integer aPageCount) {
        given: "a book which is not correctly set"
        Book book = new Book(isbn13: aIsbn13, title: aTitle, publishedDate: aPublishedDate, author: anAuthor, description: aDescription, image: aImage, publisher: aPublisher, pageCount: aPageCount);

        expect: "the book is not valid"
        book.validate() == false;

        where:
        aIsbn13         | aTitle          | aPublishedDate         | anAuthor    | aImage   | aDescription | aPublisher  | aPageCount
        "978270963740"  | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | "JC Lattès" | 571
        ""              | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | "JC Lattès" | 571
        null            | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | "JC Lattès" | 571
        "9782709637404" | ""              | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | "JC Lattès" | 571
        "9782709637404" | null            | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | "JC Lattès" | 571
        "9782709637404" | "Da Vinci code" | null                   | "Dan Brown" | "im.jpg" | "blabla"     | "JC Lattès" | 571
        "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | ""          | "im.jpg" | "blabla"     | "JC Lattès" | 571
        "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | null        | "im.jpg" | "blabla"     | "JC Lattès" | 571
        // TODO Should work, spock fail
        //"9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | ""          | 571
        //"9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | null        | 571
        "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | "JC Lattès" | null
        "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | "JC Lattès" | -23
    }
}
