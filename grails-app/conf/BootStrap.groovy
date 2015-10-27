import ivvq.Book
import ivvq.ItemAlreadyExistException
import ivvq.ItemUser
import ivvq.ItemUserAlreadyAddedException
import ivvq.ItemUserNotValidException
import ivvq.JSonAPIException
import ivvq.Movie
import ivvq.SaveAPIException
import ivvq.TVShow
import ivvq.User

class BootStrap {

    def dataFillingService
    def itemUserService

    def init = { servletContext ->

        String[] moviesToLoad = ["tt0468569", "tt0369610", "tt0137523", "tt0088763", "tt0111161", "tt1285016",
                                 "tt0076759", "tt0167260", "tt0167261", "tt0133093", "tt2510894", "tt2361509", "tt3397884",
                                 "tt4046784", "tt2719848", "tt1355683", "tt3567288", "tt3862750", "tt3832914", "tt2403021",
                                 "tt1250777", "tt1119646", "tt0096874", "tt1156398", "tt1375666", "tt0172495", "tt0208092"]
        String[] bookToLoad = ["4hNrYIhNqUEC", "Ii1RBAAAQBAJ", "PawQ51UXVGEC", "ERghoa8HhNoC", "s9qy66rTpMgC", "vQT8B6fXgf4C",
                               "jSouIA8hsw4C", "nTKNAgAAQBAJ", "FsGJGAAACAAJ", "JhMUvnUr29UC", "qJHlSKugbPMC", "iEWAAwAAQBAJ",
                               "mwXCiqkzAhQ", "Y7GnBAAAQBAJ", "6YS8CgAAQBAJ"]
        String[] tvShowToLoad = ["tt0303461", "tt1439629", "tt0903747", "tt1520211", "tt0773262", "tt0096697", "tt0108778",
                                 "tt0411008", "tt0285331", "tt0407362", "tt0898266", "tt0460649", "tt0460681",
                                 "tt0413573", "tt0412142", "tt2364582", "tt0182576", "tt0121955", "tt1442437",
                                 "tt3216608", "tt3322314", "tt4428124", "tt4209256", "tt3865236", "tt3230854", "tt4189022"]

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

        User user = new User(username: "Veoth", firstName: "Hugues", lastName: "Odegaard", email: "ho@gmail.com", password: "groscaca").save(flus: true)

        def itemBook1 = itemUserService.insertItemUser(User.findByUsername("Veoth"), Book.findByGoogleID("4hNrYIhNqUEC"), 7, true, null)
        def itemBook2 = itemUserService.insertItemUser(User.findByUsername("Veoth"), Book.findByGoogleID("PawQ51UXVGEC"), null, null, true)
        def itemBook3 = itemUserService.insertItemUser(User.findByUsername("Veoth"), Book.findByGoogleID("s9qy66rTpMgC"), 7, null, true)
        def itemBook4 = itemUserService.insertItemUser(User.findByUsername("Veoth"), Book.findByGoogleID("vQT8B6fXgf4C"), null, null, true)
        def itemBook5 = itemUserService.insertItemUser(User.findByUsername("Veoth"), Book.findByGoogleID("6YS8CgAAQBAJ"), 8, true, true)

        def itemMovie1 = itemUserService.insertItemUser(User.findByUsername("Veoth"), Movie.findByImdbID("tt0468569"), null, true, null)
        def itemMovie2 = itemUserService.insertItemUser(User.findByUsername("Veoth"), Movie.findByImdbID("tt0369610"), 9, true, null)
        def itemMovie3 = itemUserService.insertItemUser(User.findByUsername("Veoth"), Movie.findByImdbID("tt0088763"), null, null, true)
        def itemMovie4 = itemUserService.insertItemUser(User.findByUsername("Veoth"), Movie.findByImdbID("tt0111161"), 5, null, true)
        def itemMovie5 = itemUserService.insertItemUser(User.findByUsername("Veoth"), Movie.findByImdbID("tt2361509"), null, null, true)
        def itemMovie6 = itemUserService.insertItemUser(User.findByUsername("Veoth"), Movie.findByImdbID("tt3567288"), 6, true, true)
        def itemMovie7 = itemUserService.insertItemUser(User.findByUsername("Veoth"), Movie.findByImdbID("tt0096874"), 9, null, true)

        def itemTvShow1 = itemUserService.insertItemUser(User.findByUsername("Veoth"), TVShow.findByImdbID("tt0303461"), null, true, null)
        def itemTvShow2 = itemUserService.insertItemUser(User.findByUsername("Veoth"), TVShow.findByImdbID("tt1439629"), null, true, null)
        def itemTvShow3 = itemUserService.insertItemUser(User.findByUsername("Veoth"), TVShow.findByImdbID("tt3216608"), 4, true, true)
        def itemTvShow4 = itemUserService.insertItemUser(User.findByUsername("Veoth"), TVShow.findByImdbID("tt0898266"), 3, true, null)
        def itemTvShow5 = itemUserService.insertItemUser(User.findByUsername("Veoth"), TVShow.findByImdbID("tt0460649"), null, null, true)
        def itemTvShow6 = itemUserService.insertItemUser(User.findByUsername("Veoth"), TVShow.findByImdbID("tt0121955"), null, null, true)
        def itemTvShow7 = itemUserService.insertItemUser(User.findByUsername("Veoth"), TVShow.findByImdbID("tt4189022"), 9, null, true)

        /*def iu1 = new ItemUser(book: Book.findByGoogleID("4hNrYIhNqUEC"),user: User.findByUsername("Veoth"), rating: 7).save(flus: true)
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
        def iu16= new ItemUser(movie: Movie.findByImdbID("tt0111161"), user: User.findByUsername("Veoth"), rating: 3).save(flus: true)*/
    }

    def destroy = {
    }
}
