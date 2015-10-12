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
        String imdbID = "tt0369f610"

        when: "calling the movie API with the given id"
        JSONElement json = itemAPIService.movieAPI(imdbID)

        then: "the title of the current movie is Jurassic World"
        json.Title == "Jurassic World"
    }

    void "test the book API with a given url"() {

        given: "a google id that belongs to a unique book"
        String googleID = "StfQT2WY0C"

        when: "calling the book API with the given id"
        JSONElement json = itemAPIService.bookAPI(googleID)

        then: "the title of the current book is Da Vinci Code"
        json.volumeInfo.title == "Da Vinci code"
    }

    void "test the tvshow API with a given url"() {

        given: "a tv show id that belongs to a unique book"
        String tvID = "4589"

        when: "calling the tv show API with the given id"
        JSONElement json = itemAPIService.tvshowAPI(tvID)

        then: "the title of the current book is Da Vinci Code"
        json != null
    }
}
