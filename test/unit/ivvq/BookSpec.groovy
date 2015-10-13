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
    void "test a valid book"() {

        given: "a book which is correctly set"
        Book book = new Book(googleID: aGoogleID, isbn13: aIsbn13, title: aTitle, publishedDate: aPublishedDate, author: anAuthor, description: aDescription, image: aImage, publisher: aPublisher, pageCount: aPageCount)

        expect: "the book is valid"
        book.validate() == true

        where:
        aGoogleID      | aIsbn13         | aTitle          | aPublishedDate         | anAuthor    | aImage | aDescription | aPublisher  | aPageCount
        "SteVfQT2WY0C" | "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "img"  | "blabla"     | "JC Lattès" | 571
        "SteVfQT2WY0C" | ""              | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "img" | "blabla" | "JC Lattès" | 571
        "SteVfQT2WY0C" | null            | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "img" | "blabla" | "JC Lattès" | 571
        "SteVfQT2WY0C" | "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | ""  | "blabla"     | "JC Lattès" | 571
        "SteVfQT2WY0C" | "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | null  | "blabla"     | "JC Lattès" | 571
        "SteVfQT2WY0C" | "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "img" | ""       | "JC Lattès" | 571
        "SteVfQT2WY0C" | "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "img" | null     | "JC Lattès" | 571
    }

    @Unroll
    void "test a invalid book"() {

        given: "a book which is not correctly set"
        Book book = new Book(googleID: aGoogleID, isbn13: aIsbn13, title: aTitle, publishedDate: aPublishedDate, author: anAuthor, description: aDescription, image: aImage, publisher: aPublisher, pageCount: aPageCount);

        expect: "the book is not valid"
        book.validate() == false

        where:
        aGoogleID      | aIsbn13         | aTitle          | aPublishedDate         | anAuthor    | aImage   | aDescription | aPublisher  | aPageCount
        "SteVfQT2WY"   | "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | "JC Lattès" | 571
        ""             | "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | "JC Lattès" | 571
        null           | "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | "JC Lattès" | 571
        "SteVfQT2WY0C" | "978270963740"  | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | "JC Lattès" | 571
        "SteVfQT2WY0C" | "9782709637404" | ""              | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | "JC Lattès" | 571
        "SteVfQT2WY0C" | "9782709637404" | null            | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | "JC Lattès" | 571
        "SteVfQT2WY0C" | "9782709637404" | "Da Vinci code" | null                   | "Dan Brown" | "im.jpg" | "blabla"     | "JC Lattès" | 571
        "SteVfQT2WY0C" | "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | ""          | "im.jpg" | "blabla"     | "JC Lattès" | 571
        "SteVfQT2WY0C" | "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | null        | "im.jpg" | "blabla"     | "JC Lattès" | 571
        "SteVfQT2WY0C" | "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | ""          | 571
        "SteVfQT2WY0C" | "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | null        | 571
        "SteVfQT2WY0C" | "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | "JC Lattès" | null
        "SteVfQT2WY0C" | "9782709637404" | "Da Vinci code" | new Date(2004, 03, 03) | "Dan Brown" | "im.jpg" | "blabla"     | "JC Lattès" | -23
    }
}
