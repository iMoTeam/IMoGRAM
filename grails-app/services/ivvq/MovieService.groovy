package ivvq

import grails.transaction.Transactional

@Transactional
class MovieService {

    def searchMovies(def params) {
        String stringToSearch
        if(params.stringToSearch != null){
            stringToSearch = params.stringToSearch
            stringToSearch = stringToSearch.trim()
        }
        def criteria = Movie.createCriteria()
        def res = criteria.list (max:params.max,offset:params.offset){
            if (stringToSearch) {
                or {
                    like 'title', "%${stringToSearch}%"
                    like 'releaseDate', "%${stringToSearch}%"
                    like 'genre', "%${stringToSearch}%"
                    like 'director', "%${stringToSearch}%"
                    like 'country', "%${stringToSearch}%"
                    like 'imdbID', "%${stringToSearch}%"
                }
            }
        }
        res
    }
}
