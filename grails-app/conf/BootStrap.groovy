class BootStrap {

    def dataFillingService

    def init = { servletContext ->

        String[] moviesToLoad = ["tt0107290", "tt0369610"]
        String[] bookToLoad = ["SteVfQT2WY0C", "YnBRB84BHscC", "4hNrYIhNqUEC", "Ii1RBAAAQBAJ"]// "JthPwAACAAJ",
                               //"PawQ51UXVGEC", "ERghoa8HhNoC", "jSouIA8hsw4C", "nTKNAgAAQBAJ", "FsGJGAAACAAJ", "JhMUvnUr29UC", "qJHlSKugbPMC"]

        // Load the movies from their imdbIDs
        /*moviesToLoad.each {
            dataFillingService.jsonToMovieSave(it)
        }

        bookToLoad.each {
            dataFillingService.jsonToBookSave(it)
        }*/
    }

    def destroy = {
    }
}
