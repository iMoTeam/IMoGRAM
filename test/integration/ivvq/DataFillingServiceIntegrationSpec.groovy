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

    void "test that the movie is correctly added to the database"() {

        given: "an IMDB id that belongs to a unique movie"
        String imdbID = "tt1219289"

        when: "the json is loaded and the musee is saved"
        Movie movie = dataFillingService.jsonToMovieSave(imdbID)

        then: "The movie is added to the database"
        !movie.hasErrors()

        and: "Movie's title is correct"
        movie.title == "Limitless"

        and: "the movie has no errors"
        Movie.findByImdbID(imdbID) != null
    }
}
