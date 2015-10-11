package ivvq



import spock.lang.*

/**
 *
 */
class MovieServiceIntegrationSpec extends Specification {

    def movieService
    def dataFillingService

    void "test a movie in the database can be searched"() {

        given: "an IMDB id that belongs to a unique movie"
        String imdbID = "tt1219289"

        when: "the movie is saved"
        Movie movie = dataFillingService.jsonToMovieSave(imdbID)

        then: "the movie has no errors"
        !movie.hasErrors()

        and: "The movie is added to the database"
        Movie.findByImdbID(imdbID) != null

        when: "The movie is searched by imdb id"
        def params = [stringToSearch: 'tt1219289']
        def list = movieService.searchMovies(params)

        then: "The list returned is not empty,contains only one movie"
        list.size() == 1

        and: "it contains the movie added to database"
        list*.imdbID.contains("tt1219289")
    }



}
