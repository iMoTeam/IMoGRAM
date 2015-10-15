package ivvq



import spock.lang.*

/**
 *
 */
class UniversalControllerIntegrationSpec extends Specification {

    def movieService
    def dataFillingService
    def controller = new UniversalController()


    void "test a movie in the database can be searched"() {

        given: "Movies and books inserted in the database by the bootstrap"

        when: "The movie is searched by imdb id"
        def params = [stringToSearch: 'tt0369610']
        controller.params.putAll(params)
        controller.doSearchAll()

        then: "The view rendered is correct and there is only one movie and no books in the search results"
        controller.modelAndView.viewName == '/universal/index'
        controller.modelAndView.model.containsKey('movieInstanceList')
        controller.modelAndView.model.movieInstanceCount == 1
        controller.modelAndView.model.bookInstanceCount == 0
    }

    void "test a book in the database can be searched"() {

        given: "Movies and books inserted in the database by the bootstrap"

        when: "A book is searched by isbn13"
        def params = [stringToSearch: '9782709637404']
        controller.params.putAll(params)
        controller.doSearchAll()

        then: "The view rendered is correct and there is only one book and no movies in the search results"
        controller.modelAndView.viewName == '/universal/index'
        controller.modelAndView.model.containsKey('bookInstanceList')
        controller.modelAndView.model.movieInstanceCount == 0
        controller.modelAndView.model.bookInstanceCount == 1
    }
}
