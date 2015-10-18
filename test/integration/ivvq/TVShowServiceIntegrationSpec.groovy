package ivvq



import spock.lang.*

/**
 *
 */
class TVShowServiceIntegrationSpec extends Specification {

    def TVShowService
    def dataFillingService

    void "test a TVShow in the database can be searched"() {

        given: "an IMDB id that belongs to a unique TvShow"
        String imdbID = "tt0944947"

        when: "the TVShow is saved"
        TVShow tvShow = dataFillingService.jsonToTVShowSave(imdbID)

        then: "the TVShow has no errors"
        !tvShow.hasErrors()

        and: "The TVShow is added to the database"
        TVShow.findByImdbID(imdbID) != null

        when: "The TVShow is searched by imdb id"
        def params = [stringToSearch: 'tt0944947']
        def list = TVShowService.searchTVShow(params)

        then: "The list returned is not empty,contains only one TVShow"
        list.size() == 1

        and: "it contains the tvShow added to database"
        list*.imdbID.contains("tt0944947")
    }
}
