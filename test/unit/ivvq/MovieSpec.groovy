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
        movie.imdbID = aImdbID
        movie.title = aTitle
        movie.releaseDate = aReleaseDate
        movie.runtime = aRuntime
        movie.genre = aGenre
        movie.director = aDirector
        movie.writers = someWriters
        movie.actors = someActors
        movie.country = someCountrys
        movie.plot = aPlot
        movie.poster = aPoster

        when: "the Movie is validated"
        def valid = movie.validate()

        then: "Constraints checking are corrects"
        valid == expectedState

        where:
        aImdbID     | aTitle  | aReleaseDate  | aRuntime  | aGenre      | aDirector   | someWriters | someActors  | someCountrys | aPlot    | aPoster | expectedState
        "tt4545341" | "title" | "12 Jun 2015" | "124 min" | "Adventure" | "Spielberg" | "aWriter"   | "Tom Cruiz" | "USA"        | "blabla" | "url"   | true
        "tt4545341" | "title" | "12 Jun 2015" | "124 min" | "Adventure" | "Spielberg" | "aWriter"   | "Tom Cruiz" | ""           | "blabla" | "url"   | true
        "tt4545341" | "title" | "12 Jun 2015" | "124 min" | "Adventure" | "Spielberg" | "aWriter"   | "Tom Cruiz" | "USA"        | "blabla" | ""      | true
        "tt4545341" | "title" | "12 Jun 2015" | "124 min" | "Adventure" | "Spielberg" | "aWriter"   | "Tom Cruiz" | ""           | "blabla" | ""      | true
        "t0000000"  | "title" | "12 Jun 2015" | "124 min" | "Adventure" | "Spielberg" | "aWriter"   | "Tom Cruiz" | "USA"        | "blabla" | "url"   | false
        null        | "title" | "12 Jun 2015" | "124 min" | "Adventure" | "Spielberg" | "aWriter"   | "Tom Cruiz" | "USA"        | "blabla" | "url"   | false
        "tt4545341" | ""      | "12 Jun 2015" | "124 min" | "Adventure" | "Spielberg" | "aWriter"   | "Tom Cruiz" | "USA"        | "blabla" | "url"   | false
        "tt4545341" | null    | "12 Jun 2015" | "124 min" | "Adventure" | "Spielberg" | "aWriter"   | "Tom Cruiz" | "USA"        | "blabla" | "url"   | false
        "tt4545341" | "title" | null          | "124 min" | "Adventure" | "Spielberg" | "aWriter"   | "Tom Cruiz" | "USA"        | "blabla" | "url"   | false
        "tt4545341" | "title" | ""            | "124 min" | "Adventure" | "Spielberg" | "aWriter"   | "Tom Cruiz" | "USA"        | "blabla" | "url"   | false
        "tt4545341" | "title" | "12 Jun 2015" | ""        | "Adventure" | "Spielberg" | "aWriter"   | "Tom Cruiz" | "USA"        | "blabla" | "url"   | false
        "tt4545341" | "title" | "12 Jun 2015" | null      | "Adventure" | "Spielberg" | "aWriter"   | "Tom Cruiz" | "USA"        | "blabla" | "url"   | false
        "tt4545341" | "title" | "12 Jun 2015" | "124 min" | "     "     | "Spielberg" | "aWriter"   | "Tom Cruiz" | "USA"        | "blabla" | "url"   | false
        "tt4545341" | "title" | "12 Jun 2015" | "124 min" | null        | "Spielberg" | "aWriter"   | "Tom Cruiz" | "USA"        | "blabla" | "url"   | false
        "tt4545341" | "title" | "12 Jun 2015" | "124 min" | "Adventure" | ""          | "aWriter"   | "Tom Cruiz" | "USA"        | "blabla" | "url"   | false
        "tt4545341" | "title" | "12 Jun 2015" | "124 min" | "Adventure" | null        | "aWriter"   | "Tom Cruiz" | "USA"        | "blabla" | "url"   | false
        "tt4545341" | "title" | "12 Jun 2015" | "124 min" | "Adventure" | "Spielberg" | ""          | "Tom Cruiz" | "USA"        | "blabla" | "url"   | false
        "tt4545341" | "title" | "12 Jun 2015" | "124 min" | "Adventure" | "Spielberg" | null        | "Tom Cruiz" | "USA"        | "blabla" | "url"   | false
        "tt4545341" | "title" | "12 Jun 2015" | "124 min" | "Adventure" | "Spielberg" | "aWriter"   | ""          | "USA"        | "blabla" | "url"   | false
        "tt4545341" | "title" | "12 Jun 2015" | "124 min" | "Adventure" | "Spielberg" | "aWriter"   | null        | "USA"        | "blabla" | "url"   | false
        "tt4545341" | "title" | "12 Jun 2015" | "124 min" | "Adventure" | "Spielberg" | "aWriter"   | "Tom Cruiz" | null         | "blabla" | "url"   | false
        "tt4545341" | "title" | "12 Jun 2015" | "124 min" | "Adventure" | "Spielberg" | "aWriter"   | "Tom Cruiz" | "USA"        | ""       | "url"   | false
        "tt4545341" | "title" | "12 Jun 2015" | "124 min" | "Adventure" | "Spielberg" | "aWriter"   | "Tom Cruiz" | "USA"        | null     | "url"   | false
        "tt4545341" | "title" | "12 Jun 2015" | "124 min" | "Adventure" | "Spielberg" | "aWriter"   | "Tom Cruiz" | "USA"        | "blabla" | null    | false

    }
}
