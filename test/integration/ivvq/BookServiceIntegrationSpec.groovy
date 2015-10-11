package ivvq



import spock.lang.*

/**
 *
 */
class BookServiceIntegrationSpec extends Specification {

    def bookService
    def dataFillingService
    def setup() {
    }
    def populateparams(){
        def params = [stringToSearch: '9782709637404']
        controller.params.putAll(params)
    }

    void "Test that a book in the database can be searched"() {
        given: "an google id that belongs to a book"
        String googleID = "SteVfQT2WY0C"

        when: "the book is saved"
        Book book = dataFillingService.jsonToBookSave(googleID)

        then: "the book has no errors"
        !book.hasErrors()

        and: "The book is added to the database"
        Book.findByIsbn13(9782709637404) != null

        when: "The book is searched"
        def params = [stringToSearch: '9782709637404']
        def list = bookService.searchBooks(params)

        then: "The list returned is not empty"
        list.size() == 1

        and: "it contains the book added to database"
        list*.isbn13.contains("9782709637404")


    }
}
