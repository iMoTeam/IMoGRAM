package ivvq
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
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
        movieService = new MovieService()
        movieService.transactionManager = Mock(PlatformTransactionManager) {getTransaction(_) >> Mock(TransactionStatus)}
        TVShowService = new TVShowService()
        TVShowService.transactionManager = Mock(PlatformTransactionManager) {getTransaction(_) >> Mock(TransactionStatus)}
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

    def populateValidParamsMovie(params) {
        assert params != null
        params["imdbID"] = 'tt1219289'
        params["title"] = 'Limitless'
        params["releaseDate"] = new Date(2015, 03, 03);
        params["runtime"] = '105min'
        params["genre"] = 'Mystery, Sci-Fi, Thriller'
        params["director"] = 'Neil Burger'
        params["writers"] = 'Leslie Dixon (screenplay), Alan Glynn (novel)'
        params["actors"] = 'Bradley Cooper, Robert De Niro, Abbie Cornish, Andrew Howard'
        params["country"] = 'USA'
        params["plot"] = 'With the help of a mysterious pill that enables the user to access 100 percent of his brain abilities,' +
                ' a struggling writer becomes a financial wizard, but it also puts him in a new world with lots of dangers.'
        params["poster"] = 'http://ia.media-imdb.com/images/M/MV5BMTY3NjczNzc5Nl5BMl5BanBnXkFtZTcwMzA2MzQyNA@@._V1_SX300.jpg'
    }

    def populateValidParamsTVShow(params) {
        assert params != null
        params["imdbID"] = "tt0107290"
        params["title"] = "The Movie"
        params["releaseDate"] = "22-09-15"
        params["runtime"] = "127"
        params["network"] = "OCS"
        params["overview"] = "Résume"
        params["airedEpisodes"] = 50
        params["country"] = "Madagascar"
    }

    def cleanup() {
    }

    void "test that the doSearchAll search a book"() {

        setup:
        controller.bookService = bookService
        controller.movieService = movieService
        controller.TVShowService = TVShowService

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

    void "test that the doSearchAll search a movie"() {
        setup:
        controller.bookService = bookService
        controller.movieService = movieService
        controller.TVShowService = TVShowService

        when: "the doResearchAll action is executed"
        populateValidParamsMovie(params)
        Movie movie = new Movie(params)
        movie.save(flush: true)
        params["stringToSearch"] = "Limit"
        controller.doSearchAll()

        then: "a movie is returned"
        model.movieInstanceCount == 1

        and: "the movie is correct"
        model.movieInstanceList.get(0) == movie
    }

    void "test that the doSearchAll search a TVShow"() {
        setup:
        controller.bookService = bookService
        controller.movieService = movieService
        controller.TVShowService = TVShowService

        when: "the doResearchAll action is executed"
        populateValidParamsTVShow(params)
        TVShow tvShow = new TVShow(params)
        tvShow.save(flush: true)
        params["stringToSearch"] = "Movie"
        controller.doSearchAll()

        then: "a TVShow is returned"
        model.tvShowInstanceCount == 1

        and: "the TVShow is correct"
        model.tvShowInstanceList.get(0) == tvShow
    }

}
