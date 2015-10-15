package ivvq

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.transaction.Transactional

@Transactional
class ItemAPIService {

    RestBuilder rest = new RestBuilder()

    def movieAPI(String imdbID) {

        String urlAPI = 'http://www.omdbapi.com/?plot=short&r=json&i=' + imdbID

        RestResponse response = rest.get(urlAPI) {

            accept JSON
        }

        // If rest response is empty
        if (response.json.Error != null) {
            throw new JSonAPIException("An error has occured while downloading json (Movie): " + imdbID)
        } else {
            return response.json
        }
    }

    def bookAPI(String googleID) {

        //String api_key = "eGnBLXtKoqpJHrEhdaGjbw"
        String urlAPI = "https://www.googleapis.com/books/v1/volumes/" + googleID

        RestResponse response = rest.get(urlAPI) {

            accept JSON
        }

        if (response.statusCode.value() != 200) {
            throw new JSonAPIException("An error has occured while downloading json (Book): " + googleID)
        } else {
            return response.json
        }
    }

    /*
     * the imdb ID is passed to handle properly the exception
     */
    def tvshowAPI(String request, String imdbTv) {

        String api_key = "3c486bec651c7a338941bbeaea332cef3ec4f601b9869702686644e7bf1fdda2"
        String urlAPI = "https://api-v2launch.trakt.tv/shows/" + request

        RestResponse response = rest.get(urlAPI) {
            contentType "application/json"
            headers["trakt-api-version"] = "2"
            headers["trakt-api-key"] = api_key

            accept JSON
        }

        if (response.statusCode.value() != 200) {
            throw new JSonAPIException("An error has occured while downloading json (TV Show): " + imdbTv)
        } else {
            return response.json
        }
    }
}
