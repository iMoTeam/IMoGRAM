package ivvq

import org.codehaus.groovy.grails.web.json.JSONElement
import spock.lang.*

/**
 *
 */
class DataFillingServiceIntegrationSpec extends Specification {

    def dataFillingService

    String imdbMovie
    String googleID
    String imdbTv

    def setup() {
        imdbMovie = "tt1219289"
        googleID = "SteVfQT2WY0C"
        imdbTv = "tt0944947"
    }

    def cleanup() {
    }

    void "test that the MOVIE is correctly added to the database"() {

        when: "the json is loaded and the movie is saved"
        Movie movie = dataFillingService.jsonToMovieSave(imdbMovie)

        then: "the movie has no errors"
        !movie.hasErrors()

        and: "Movie's title is correct"
        movie.title == "Limitless"

        and: "The movie is added to the database"
        Movie.findByImdbID(imdbMovie) != null
    }

    void "test that the BOOK is correctly added to the database"() {

        when: "the json is loaded and the book is saved"
        Book book = dataFillingService.jsonToBookSave(googleID)

        then: "the book has no errors"
        !book.hasErrors()

        and: "Book's title is correct"
        book.title == "Da Vinci code"

        and: "The book is added to the database"
        Book.findByGoogleID(googleID) != null
    }

    void "test that the TVSHOW is correctly added to the database"() {

        when: "the json is loaded and the tv show is saved"
        TVShow tvShow = dataFillingService.jsonToTVShowSave(imdbTv)

        then: "the book has no errors"
        !tvShow.hasErrors()

        and: "Book's title is correct"
        tvShow.title == "Game of Thrones"

        and: "The book is added to the database"
        TVShow.findByImdbID(imdbTv) != null
    }


    void "test than an exception is thrown when items already exist"() {
        given: "Each item saved"
        Movie movieSaved = dataFillingService.jsonToMovieSave(imdbMovie)
        Book bookSaved = dataFillingService.jsonToBookSave(googleID)
        TVShow tvShowSaved = dataFillingService.jsonToTVShowSave(imdbTv)

        when: " a movie with the same imdb ID is saved"
        Movie tryToSaveMovie = dataFillingService.jsonToMovieSave(imdbMovie)

        then: "an exception is thrown because the movie already exist"
        ItemAlreadyExistException eMovie = thrown(Exception)
        eMovie.message == "Movie already saved in the database : " + imdbMovie

        when: " a tv show with the same imdb ID is saved"
        TVShow tryToSaveTvShow = dataFillingService.jsonToTVShowSave(imdbTv)

        then: "an exception is thrown because the movie already exist"
        ItemAlreadyExistException eTv = thrown(Exception)
        eTv.message == "Tv show already saved in the database : " + imdbTv

        /*when: " a book with the same google ID is saved"
        Book tryToSaveBook = dataFillingService.jsonToBookSave(googleId)

        then: "an exception is thrown because the book already exist"
        Exception eBook = thrown(Exception)
        log.info(eBook.message)*/
    }
}
