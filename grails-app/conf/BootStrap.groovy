class BootStrap {

    def dataFillingService

    def init = { servletContext ->

        String[] moviesToLoad = ["tt0107290", "tt0369610"]

        // Load the movies from their imdbIDs
        moviesToLoad.each {
            dataFillingService.jsonToMovieSave(it)
        }
    }

    def destroy = {
    }
}