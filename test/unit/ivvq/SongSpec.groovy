package ivvq

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Song)
class SongSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    Song song = new Song()

    void "test valid song"() {
        given:"a song correctly set"
        song.title = "loyal"
        song.artist = "Chris Brown"
        song.description = "A single released after Chris was un jailed"
        song.releaseDate = Mock(Date)
        song.image = null


        when:"trying to validate the song"
        def res = song.validate()

        then:"the song is valid"
        res == true

        and:"the song no has errors"
        !song.hasErrors()
    }

   void "test invalid song"() {
        given:"a song with some attributes  well set"
        Song song = new Song()
        song.title = "IDFWU"
        song.description = "Big sean's hit song after break up"
        song.releaseDate = null
       song.image = null

        and: "with a blank artist"
        song.artist = ""


        when:"trying to validate the song"
        def res = song.validate()

        then:"the song is invalid"
        res == false

        and:"the song has errors"
        song.hasErrors()
    }

    def "test invalid songs with where clause"() {
        given: "a song"
        song.title = aTitle
        song.artist = anArtist
        song.description = aDescription
        song.releaseDate = aReleaseDate
        song.image = anImage

        when: "validating the song"
        def isValid = song.validate()

        then: "the song is not valid"
        isValid == false
        where:
        aTitle  |   anArtist | aDescription | aReleaseDate | anImage
        null    | "Ariana"   | "hit song"   | Mock(Date)    | null
        ""   | ""   | "hit song"   | Mock(Date)             | null
        "Lollipop"    | ""   | "this guy mad"   | Mock(Date) | null
    }
}
