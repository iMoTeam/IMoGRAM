import ivvq.Book
import ivvq.ItemAlreadyExistException
import ivvq.ItemUser
import ivvq.ItemUserAlreadyAddedException
import ivvq.JSonAPIException
import ivvq.Movie
import ivvq.SaveAPIException
import ivvq.User

class BootStrap {

    def dataFillingService
    def itemUserService

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

        def user = new User(username: "Veoth", firstName: "Hugues", lastName: "Odegaard", email: "ho@gmail.com", password: "groscaca").save(flus: true)

        /* Example set , NOT CORRECT use the service */
        def iu1 = new ItemUser(book: Book.findByGoogleID("4hNrYIhNqUEC"),user: User.findByUsername("Veoth"), rating: 7).save(flus: true)
        def iu2 = new ItemUser(movie: Movie.findByImdbID("tt0107290"), user: User.findByUsername("Veoth"), rating: 8).save(flus: true)
        def iu3 = new ItemUser(movie: Movie.findByImdbID("tt0137523"), user: User.findByUsername("Veoth"), rating: 3).save(flus: true)
        def iu4 = new ItemUser(book: Book.findByGoogleID("Ii1RBAAAQBAJ"),user: User.findByUsername("Veoth"), rating: 7).save(flus: true)
        def iu5 = new ItemUser(book: Book.findByGoogleID("PawQ51UXVGEC"),user: User.findByUsername("Veoth"), rating: 7).save(flus: true)
        def iu6 = new ItemUser(book: Book.findByGoogleID("ERghoa8HhNoC"),user: User.findByUsername("Veoth"), rating: 7).save(flus: true)
        def iu7 = new ItemUser(book: Book.findByGoogleID("jSouIA8hsw4C"),user: User.findByUsername("Veoth"), rating: 7).save(flus: true)
        def iu8 = new ItemUser(book: Book.findByGoogleID("nTKNAgAAQBAJ"),user: User.findByUsername("Veoth"), rating: 7).save(flus: true)
        def iu9 = new ItemUser(book: Book.findByGoogleID("JhMUvnUr29UC"),user: User.findByUsername("Veoth"), rating: 7).save(flus: true)
        def iu10 = new ItemUser(movie: Movie.findByImdbID("tt0088763"), user: User.findByUsername("Veoth"), rating: 8).save(flus: true)
        def iu11= new ItemUser(movie: Movie.findByImdbID("tt0111161"), user: User.findByUsername("Veoth"), rating: 3).save(flus: true)
        def iu12 = new ItemUser(book: Book.findByGoogleID("jSouIA8hsw4C"),user: User.findByUsername("Veoth"), rating: 7).save(flus: true)
        def iu13 = new ItemUser(book: Book.findByGoogleID("nTKNAgAAQBAJ"),user: User.findByUsername("Veoth"), rating: 7).save(flus: true)
        def iu14 = new ItemUser(book: Book.findByGoogleID("JhMUvnUr29UC"),user: User.findByUsername("Veoth"), rating: 7).save(flus: true)
        def iu15 = new ItemUser(movie: Movie.findByImdbID("tt0088763"), user: User.findByUsername("Veoth"), rating: 8).save(flus: true)
        def iu16= new ItemUser(movie: Movie.findByImdbID("tt0111161"), user: User.findByUsername("Veoth"), rating: 3).save(flus: true)

        /* CORRECT ADD OF A USER ITEM
        try {
            def item1 = itemUserService.insertItemUser(user, Book.findByGoogleID("4hNrYIhNqUEC"))
        } catch (ItemUserAlreadyAddedException e) {
            log.error(e.message)
        }*/

    }

    def destroy = {
    }
}
