package ivvq
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionStatus
import spock.lang.Specification
/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(UniversalController)
@Mock([Book,Movie,TVShow])
class UniversalControllerSpec extends Specification {

        def movieService
        def TVShowService
        BookService bookService
        def dataFillingService

    def setup() {
       /* controller.bookService = bookService
        controller.movieService = movieService
        controller.TVShowService = TVShowService*/
        bookService = new BookService()
        bookService.transactionManager = Mock(PlatformTransactionManager) {getTransaction(_) >> Mock(TransactionStatus)}
    }

    def populateValidParams(params) {
        assert params != null

        params["isbn13"] = '9782709637404'
        params["title"] = 'Da Vinci code'
        params["publishedDate"] = '2004-03-03'
        params["author"] = 'Dan Brown'
        params["publisher"] = 'JC Lattès'
        params["description"] = '\\u003cp\\u003e« \\u003ci\\u003eDa Vinci Code\\u003c/i\\u003e est un livre envoûtant, ' +
                'idéal pour les passionnés d\'histoire, les amateurs de conspirations, les mordus du mystère, ' +
                'pour tous ceux qui aiment les grands récits que l\'on ne parvient pas à lâcher. J\'ai adoré ce roman.' +
                ' »\\u003cbr\\u003eHarlan Coben\\u003c/p\\u003e\\u003cp\\u003eDe passage à Paris, Robert Langdon, professeur ' +
                'à Havard et spécialiste de symbologie, est appelé d\'urgence au Louvre, en pleine nuit. '
        params["image"] = 'image'
        params["pageCount"] = '571'
    }

    def cleanup() {
    }

    void "test that the doSearchAll search a book"() {

        setup:
        controller.bookService = bookService

        when: "the doResearchAll action is executed"
            populateValidParams(params)
            Book book = new Book(params)
            book.save(flush: true)
            params["stringToSearch"] = "Da"
            controller.doSearchAll()

        then: "a book is returned"
        model.bookInstanceCount == 1

        and: "his name is Da vinci Code"
        model.bookInstanceList.get(0) == book

    }

    /*void "test that the doResearchAll search a movie"() {
        setup:
        controller.movieService = movieService

        when: "the doResearchAll action is executed"
    }

    void "test that the doResearchAll search a TVShow"() {
        setup:
        controller.tvShowService = tvShowService

        when: "the doResearchAll action is executed"
    }*/
}
