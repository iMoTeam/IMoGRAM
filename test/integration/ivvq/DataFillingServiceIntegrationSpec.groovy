package ivvq

import org.codehaus.groovy.grails.web.json.JSONElement
import spock.lang.*

/**
 *
 */
class DataFillingServiceIntegrationSpec extends Specification {

    def dataFillingService

    def setup() {
    }

    def cleanup() {
    }

    void "test that the MOVIE is correctly added to the database"() {

        given: "an IMDB id that belongs to a unique movie"
        String imdbID = "tt1219289"

        when: "the json is loaded and the movie is saved"
        Movie movie = dataFillingService.jsonToMovieSave(imdbID)

        then: "the movie has no errors"
        !movie.hasErrors()

        and: "Movie's title is correct"
        movie.title == "Limitless"

        and: "The movie is added to the database"
        Movie.findByImdbID(imdbID) != null
    }

    void "test than an exception is thrown when items can't be saved"() {
        given: "Each item saved"
        String imdbMovie = "tt0107290"
        String googleID = "SteVfQT2WY0C"
        Movie movieSaved = dataFillingService.jsonToMovieSave(imdbMovie)
        Book bookSaved = dataFillingService.jsonToBookSave(googleID)
//        TVShow tvShowSaved = Mock(TVShow)

        when: " a movie with the same imdb ID is saved"
        Movie tryToSaveMovie = dataFillingService.jsonToMovieSave(imdbMovie)

        then: "an exception is thrown because the movie already exist"
        ItemAlreadyExistException eMovie = thrown(Exception)
        eMovie.message == "Movie already saved in the database : " + imdbMovie

        when: " a book with the same google ID is saved"
        Book tryToSaveBook = dataFillingService.jsonToBookSave(googleId)

        then: "an exception is thrown because the book already exist"
        ItemAlreadyExistException eBook = thrown(Exception)
        eBook.message == "Book already saved in the database : " + googleId

        /*when: " a tv show with the same imdb ID is saved"
        TVShow tryToSaveTvShow = dataFillingService.jsonToMovieSave(imdbTvShow)

        then: "an exception is thrown because the movie already exist"
        ItemAlreadyExistException eTv = thrown(Exception)
        eTv.message == "Tv show already saved in the database : " + imdbTvShow*/
    }

    void "test that the BOOK is correctly added to the database"() {

        given: "an google id that belongs to a unique book"
        String googleID = "SteVfQT2WY0C"

        when: "the json is loaded and the book is saved"
        Book book = dataFillingService.jsonToBookSave(googleID)

        then: "the book has no errors"
        !book.hasErrors()

        and: "Book's title is correct"
        book.title == "Da Vinci code"

        and: "The book is added to the database"
        Book.findByIsbn13(9782709637404) != null
    }

    void "test that the TVSHOW is correctly added to the database"() {

        given: "a imdb id that belongs to a unique tv show"
        String imdbID = "tt0944947"

        when: "the json is loaded and the tv show is saved"
        TVShow tvShow = dataFillingService.jsonToTVShowSave(imdbID)

        then: "the book has no errors"
        !tvShow.hasErrors()

        and: "Book's title is correct"
        tvShow.title == "Game of Thrones"

        and: "The book is added to the database"
        TVShow.findByImdbID("tt0944947") != null
    }
}
