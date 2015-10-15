package ivvq

import grails.plugins.rest.client.RestResponse
import org.codehaus.groovy.grails.web.json.JSONElement
import spock.lang.*

/**
 *
 */
class ItemAPIServiceIntegrationSpec extends Specification {

    def itemAPIService

    def setup() {
    }

    def cleanup() {
    }

    void "test the movie API with a given url"() {

        given: "an IMDB id that belongs to a unique movie"
        String imdbID = "tt1219289"

        when: "calling the movie API with the given id"
        JSONElement json = itemAPIService.movieAPI(imdbID)

        then: "the title of the current movie is Jurassic World"
        json.Title == "Limitless"
    }

    void "test the book API with a given url"() {

        given: "a google id that belongs to a unique book"
        String googleID = "SteVfQT2WY0C"

        when: "calling the book API with the given id"
        JSONElement json = itemAPIService.bookAPI(googleID)

        then: "the title of the current book is Da Vinci Code"
        json.volumeInfo.title == "Da Vinci code"
    }

    void "test the tvshow API with a given url"() {

        given: "a tv show id that belongs to a unique book"
        String tvID = "tt0944947"

        when: "calling the tv show API with the given id"
        JSONElement json = itemAPIService.tvshowAPI(tvID, tvID)

        then: "the title of the current book is Da Vinci Code"
        json != null
    }

    void "test than an exception is thrown when json can't be download"() {
        given: "Wrong id for item"
        String imdbMovie = "tt010ef7290"
        String googleID = "SteVfQTefe2WY0C"
        String imdbTv = "tt0773ef262"

        when: "trying to download a movie"
        itemAPIService.movieAPI(imdbMovie)

        then: "an exception is thrown because the movie does not exist"
        JSonAPIException eMovie = thrown(Exception)
        eMovie.message == "An error has occured while downloading json (Movie): " + imdbMovie

        when: "trying to download a book"
        itemAPIService.bookAPI(googleID)

        then: "an exception is thrown because the book does not exist"
        JSonAPIException eBook = thrown(Exception)
        eBook.message == "An error has occured while downloading json (Book): " + googleID

        when: "trying to download a tv show"
        itemAPIService.tvshowAPI(imdbTv, imdbTv)

        then: "an exception is thrown because the tv show does not exist"
        JSonAPIException eTv = thrown(Exception)
        eTv.message == "An error has occured while downloading json (TV Show): " + imdbTv
    }
}
