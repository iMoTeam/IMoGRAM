package ivvq

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TVShow)
class TVShowSpec extends Specification {

    TVShow tvShow
    
    def setup() {
        tvShow = new TVShow()
    }

    def cleanup() {
    }

    void "test that constraints of a tv show are valids"() {
        given: "a tvShow"
        tvShow.imdbID = aImdbID
        tvShow.title = aTitle
        tvShow.releaseDate = aReleaseDate
        tvShow.runtime = aRuntime
        tvShow.network = aNetwork
        tvShow.overview = aOverview
        tvShow.genres = aGenres
        tvShow.airedEpisodes = anAiredEpisode
        tvShow.country = aCountry

        when: "the Movie is validated"
        def valid = tvShow.validate()

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
