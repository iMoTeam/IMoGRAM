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
        tvShow.airedEpisodes = anAiredEpisode
        tvShow.country = aCountry

        when: "the Movie is validated"
        def valid = tvShow.validate()

        then: "Constraints checking are corrects"
        valid == expectedState

        where:
        aImdbID     | aTitle  | aReleaseDate  | aRuntime | aNetwork | aOverview | anAiredEpisode | aCountry | expectedState
        "tt4545341" | "title" | "12 Jun 2015" | "124"    | "HBO"    | "blabla"  | 50             | "us"     | true
        "tt454531"  | "title" | "12 Jun 2015" | "124"    | "HBO"    | "blabla"  | 50             | "us"     | false
        ""          | "title" | "12 Jun 2015" | "124"    | "HBO"    | "blabla"  | 50             | "us"     | false
        null        | "title" | "12 Jun 2015" | "124"    | "HBO"    | "blabla"  | 50             | "us"     | false
        "tt4545341" | ""      | "12 Jun 2015" | "124"    | "HBO"    | "blabla"  | 50             | "us"     | false
        "tt4545341" | null    | "12 Jun 2015" | "124"    | "HBO"    | "blabla"  | 50             | "us"     | false
        "tt4545341" | "title" | ""            | "124"    | "HBO"    | "blabla"  | 50             | "us"     | false
        "tt4545341" | "title" | null          | "124"    | "HBO"    | "blabla"  | 50             | "us"     | false
        "tt4545341" | "title" | "12 Jun 2015" | ""       | "HBO"    | "blabla"  | 50             | "us"     | false
        "tt4545341" | "title" | "12 Jun 2015" | null     | "HBO"    | "blabla"  | 50             | "us"     | false
        "tt4545341" | "title" | "12 Jun 2015" | "124"    | ""       | "blabla"  | 50             | "us"     | false
        "tt4545341" | "title" | "12 Jun 2015" | "124"    | null     | "blabla"  | 50             | "us"     | false
        "tt4545341" | "title" | "12 Jun 2015" | "124"    | "HBO"    | ""        | 50             | "us"     | false
        "tt4545341" | "title" | "12 Jun 2015" | "124"    | "HBO"    | null      | 50             | "us"     | false
        "tt4545341" | "title" | "12 Jun 2015" | "124"    | "HBO"    | "blabla"  | -12            | "us"     | false
        "tt4545341" | "title" | "12 Jun 2015" | "124"    | "HBO"    | "blabla"  | null           | "us"     | false
        "tt4545341" | "title" | "12 Jun 2015" | "124"    | "HBO"    | "blabla"  | 50             | ""       | false
        "tt4545341" | "title" | "12 Jun 2015" | "124"    | "HBO"    | "blabla"  | 50             | null     | false



    }
}
