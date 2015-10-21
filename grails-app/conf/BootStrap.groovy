import ivvq.ItemAlreadyExistException
import ivvq.JSonAPIException
import ivvq.SaveAPIException

class BootStrap {

    def dataFillingService

    def init = { servletContext ->

        String[] moviesToLoad = ["tt0468569", "tt0107290", "tt0369610", "tt0137523", "tt0088763", "tt0111161", "tt1285016",
                                 "tt0076759", "tt0167260", "tt0167261", "tt0133093"]
        String[] bookToLoad = ["4hNrYIhNqUEC", "Ii1RBAAAQBAJ", "PawQ51UXVGEC", "ERghoa8HhNoC",
                                   "jSouIA8hsw4C", "nTKNAgAAQBAJ", "FsGJGAAACAAJ", "JhMUvnUr29UC", "qJHlSKugbPMC"]
        String[] tvShowToLoad = ["tt0303461", "tt1439629", "tt0903747", "tt1520211", "tt0773262", "tt0096697",
                                 "tt0411008", "tt0285331", "tt0407362"]

        // Loading Datas
        moviesToLoad.each {
            try {
                dataFillingService.jsonToMovieSave(it)
            } catch (SaveAPIException e) {
                log.error(e.message)
            } catch (ItemAlreadyExistException ie) {
                log.error(ie.message)
            } catch (JSonAPIException je) {
                log.error(je.message)
            }
        }

        bookToLoad.each {
            try {
                dataFillingService.jsonToBookSave(it)
            } catch (SaveAPIException e) {
                log.error(e.message)
            } catch (ItemAlreadyExistException ie) {
                log.error(ie.message)
            } catch (JSonAPIException je) {
                log.error(je.message)
            }
        }

        tvShowToLoad.each {
            try {
                dataFillingService.jsonToTVShowSave(it)
            } catch (SaveAPIException e) {
                log.error(e.message)
            } catch (ItemAlreadyExistException ie) {
                log.error(ie.message)
            } catch (JSonAPIException je) {
                log.error(je.message)
            }
        }

    }

    def destroy = {
    }
}
