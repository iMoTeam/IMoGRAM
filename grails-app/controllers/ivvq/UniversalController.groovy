package ivvq

import grails.transaction.Transactional

@Transactional(readOnly = true)
class UniversalController {

    MovieService movieService
    BookService bookService
    TVShowService TVShowService

    static nbItemByPage = 5

    /**
     * Searches movies,books corresponding to a search string passed in params
     * @return a list of movies, books as search result
     */
    def doSearchAll() {
        params.max = 5
        def moviesList = movieService.searchMovies(params)
        def booksList = bookService.searchBooks(params)
        def tvShowList = TVShowService.searchTVShow(params)
        render(view: '/results', model: [bookInstanceList:booksList, bookInstanceCount: booksList.size(),
                                      movieInstanceList:moviesList, movieInstanceCount: moviesList.size(),
                                      tvShowInstanceList:tvShowList, tvShowInstanceCount: tvShowList.size()])
    }

    /**
     * Searches books corresponding to a search string passed in params
     * @return a list of books as search result
     */
    def doSearchBooks() {

        def offsetTmp = params.int('offset') ?: 0
        params.max =nbItemByPage
        def booksList = bookService.searchBooks(params, nbItemByPage, offsetTmp)

        render(view: '/results', model: [bookInstanceList:booksList, bookInstanceCount: booksList.size(), itemsCount: booksList.getTotalCount()])
    }

    /**
     * Searches movies corresponding to a search string passed in params
     * @return a list of movies as search result
     */
    def doSearchMovies() {
        def offsetTmp = params.int('offset') ?: 0
        params.max =nbItemByPage
        def moviesList = movieService.searchMovies(params, nbItemByPage, offsetTmp)
        render(view: '/results', model: [movieInstanceList:moviesList, movieInstanceCount: moviesList.size(), itemsCount: moviesList.getTotalCount()])
    }

    def doSearchTvShow() {
        def offsetTmp = params.int('offset') ?: 0
        params.max =nbItemByPage
        def tvShowList = TVShowService.searchTVShow(params, nbItemByPage, offsetTmp)
        render(view: '/results', model: [tvShowInstanceList: tvShowList, tvShowInstanceCount: tvShowList.size(), itemsCount: tvShowList.getTotalCount()])
    }


}
