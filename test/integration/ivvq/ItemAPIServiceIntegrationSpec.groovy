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

    void "test movie API with a given url"() {

        given: "an IMDB id that belongs to a unique movie"
        String imdbID = "tt0369610"

        when: "calling the movie API with the given id"
        JSONElement json = itemAPIService.movieAPI(imdbID)

        then: "the title of the current movie is Jurassic World"
        json.Title == "Jurassic World"
    }
}
