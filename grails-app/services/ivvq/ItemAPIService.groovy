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

        response.json
    }

    def bookAPI(String isbn) {

        String api_key = "eGnBLXtKoqpJHrEhdaGjbw"
        String urlAPI = ""

        RestResponse response = rest.get(urlAPI) {

            accept JSON
        }

        response.json
    }
}
