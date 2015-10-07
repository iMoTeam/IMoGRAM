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
        String imdbID = "tt0369610"

        when: "calling the movie API with the given id"
        JSONElement json = itemAPIService.movieAPI(imdbID)

        then: "the title of the current movie is Jurassic World"
        json.Title == "Jurassic World"
    }

    void "test the book API with a given url"() {

        given: "a google id that belongs to a unique book"
        String googleID = "SteVfQT2WY0C"

        when: "calling the book API with the given id"
        JSONElement json = itemAPIService.bookAPI(googleID)

        then: "the title of the current book is Da Vinci Code"
        json.volumeInfo.title == "Da Vinci code"
    }
}
