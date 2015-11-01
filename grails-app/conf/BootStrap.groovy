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

        //User.attach()
        User user = new User(username: "Veoth", firstName: "Hugues", lastName: "Odegaard", email: "ho@gmail.com", password: "groscaca").save(flush: true)

        User user2 = new User(username: "Django", firstName: "Jackie", lastName: "Chan", email: "chan@gmail.com", password: "lalala").save(flush: true)
        User user3 = new User(username: "Rails", firstName: "Nathan", lastName: "Drake", email: "drake@gmail.com", password: "lalala", following: [user, user2]).save(flush: true)

        User user1 = new User(username: "Melo", firstName: "Manantsoa", lastName: "Razanajatovo", email: "lala@gmail.com", password: "lalala", following: [user, user2, user3]).save(flush: true)

        //user1.attach()


        def itemBook1 = new ItemUser(user: User.findByUsername("Veoth"), book: Book.findByGoogleID("4hNrYIhNqUEC"), rating: 7,interested: true).save(flush: true)
        def itemBook2 = new ItemUser(user: User.findByUsername("Veoth"), book: Book.findByGoogleID("PawQ51UXVGEC"),rating: null, favourite: true).save(flush: true)
        def itemBook3 = new ItemUser(user: User.findByUsername("Veoth"), book: Book.findByGoogleID("s9qy66rTpMgC"),rating: 7, favourite: true).save(flush: true)
        def itemBook4 = new ItemUser(user: User.findByUsername("Veoth"), book: Book.findByGoogleID("vQT8B6fXgf4C"),rating: null, favourite: true).save(flush: true)
        def itemBook5 = new ItemUser(user: User.findByUsername("Veoth"), book: Book.findByGoogleID("6YS8CgAAQBAJ"),rating: 8,interested: true, favourite: true).save(flush: true)
        def itemMovie1 = new ItemUser(user: User.findByUsername("Veoth"), movie: Movie.findByImdbID("tt0468569"),rating: null,interested: true).save(flush: true)
        def itemMovie2 = new ItemUser(user: User.findByUsername("Veoth"), movie: Movie.findByImdbID("tt0369610"),rating: 9,interested: true).save(flush: true)
        def itemMovie3 = new ItemUser(user: User.findByUsername("Veoth"), movie: Movie.findByImdbID("tt0088763"),rating: null, favourite: true).save(flush: true)
        def itemMovie4 = new ItemUser(user: User.findByUsername("Veoth"), movie: Movie.findByImdbID("tt0111161"),rating: 5, favourite: true).save(flush: true)
        def itemMovie5 = new ItemUser(user: User.findByUsername("Veoth"), movie: Movie.findByImdbID("tt2361509"),rating: null, favourite: true).save(flush: true)
        def itemMovie6 = new ItemUser(user: User.findByUsername("Veoth"), movie: Movie.findByImdbID("tt3567288"),rating: 6,interested: true, favourite: true).save(flush: true)
        def itemMovie7 = new ItemUser(user: User.findByUsername("Veoth"), movie: Movie.findByImdbID("tt0096874"),rating: 9, favourite: true).save(flush: true)
        def itemTvShow1 = new ItemUser(user: User.findByUsername("Veoth"), tvShow: TVShow.findByImdbID("tt0303461"),rating: null,interested: true).save(flush: true)
        def itemTvShow2 = new ItemUser(user: User.findByUsername("Veoth"), tvShow: TVShow.findByImdbID("tt1439629"),rating: null,interested: true).save(flush: true)
        def itemTvShow3 = new ItemUser(user: User.findByUsername("Veoth"), tvShow: TVShow.findByImdbID("tt3216608"),rating: 4,interested: true, favourite: true).save(flush: true)
        def itemTvShow4 = new ItemUser(user: User.findByUsername("Veoth"), tvShow: TVShow.findByImdbID("tt0898266"),rating: 3,interested: true).save(flush: true)
        def itemTvShow5 = new ItemUser(user: User.findByUsername("Veoth"), tvShow: TVShow.findByImdbID("tt0460649"),rating: null, favourite: true).save(flush: true)
        def itemTvShow6 = new ItemUser(user: User.findByUsername("Veoth"), tvShow: TVShow.findByImdbID("tt0121955"),rating: null, favourite: true).save(flush: true)
        def itemTvShow7 = new ItemUser(user: User.findByUsername("Veoth"), tvShow: TVShow.findByImdbID("tt4189022"),rating: 9, favourite: true).save(flush: true)
    }

    def destroy = {
    }
}
