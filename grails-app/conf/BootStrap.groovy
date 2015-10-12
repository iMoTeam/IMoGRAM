import ivvq.SaveAPIException

class BootStrap {

    def dataFillingService

    def init = { servletContext ->

        String[] moviesToLoad = ["tt0107290", "tt0369610"]
        String[] bookToLoad = ["SteVfQT2WY0C", "YnBRB84BHscC", "4hNrYIhNqUEC", "Ii1RBAAAQBAJ", "JthPwAACAAJ",
                               "PawQ51UXVGEC", "ERghoa8HhNoC", "jSouIA8hsw4C", "nTKNAgAAQBAJ", "FsGJGAAACAAJ", "JhMUvnUr29UC", "qJHlSKugbPMC"]
        String[] tvShowToLoad = ["tt0303461", "tt1439629", "tt0944947", "tt0903747", "tt1520211", "tt0773262"]

        // Loading Datas
        moviesToLoad.each {
            try {
                dataFillingService.jsonToMovieSave(it)
            } catch (SaveAPIException e) {
                log.error(e.message)
            }
        }

        bookToLoad.each {
            try {
                dataFillingService.jsonToBookSave(it)
            } catch (SaveAPIException e) {
                log.error(e.message)
            }
        }

        tvShowToLoad.each {
            try {
                dataFillingService.jsonToTVShowSave(it)
            } catch (SaveAPIException e) {
                log.error(e.message)
            }
        }
    }

    def destroy = {
    }
}
