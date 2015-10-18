package ivvq

import grails.transaction.Transactional

@Transactional
class TVShowService {

    def searchTVShow(def params) {
        String stringToSearch = params['stringToSearch']
        if(params.stringToSearch != null){
            stringToSearch = params.stringToSearch
            stringToSearch = stringToSearch.trim()
        }
        def criteria = TVShow.createCriteria()
        def res = criteria.list (max:params.max,offset:params.offset){
            if (stringToSearch) {
                or {
                    like 'title', "%${stringToSearch}%"
                    like 'releaseDate', "%${stringToSearch}%"
                    like 'network', "%${stringToSearch}%"
                    like 'overview', "%${stringToSearch}%"
                    like 'country', "%${stringToSearch}%"
                    like 'imdbID', "%${stringToSearch}%"
                }
            }
        }
        res
    }

}

