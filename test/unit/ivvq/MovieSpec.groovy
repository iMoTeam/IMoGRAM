package ivvq

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Movie)
class MovieSpec extends Specification {

    Movie movie

    def setup() {
        movie = new Movie()
    }

    def cleanup() {
    }

    void "test that constraints of a Movie are valids"() {
        given: "a movie"
        movie.name = aName
        movie.yearCreation = aYear
        movie.realizer = aRealizer
        movie.image = anImage

        when: "the Movie is validated"
        def valid = movie.validate()

        then: "Constraints checking are corrects"
        valid == expectedState

        where:
        aName|aYear|aRealizer|anImage|expectedState
        "name"|1915|"Spielberg"|"url"|true
        "name"|1915|"Spielberg"|""|true
        ""|1915|"Spielberg"|"url"|false
        null|1915|"Spielberg"|"url"|false
        "name"|1850|"Spielberg"|"url"|false
        "name"|2200|"Spielberg"|"url"|false
        "name"|1915|""|"url"|false
        "name"|1915|null|"url"|false
        "name"|1915|"Spielberg"|null|false


    }
}
