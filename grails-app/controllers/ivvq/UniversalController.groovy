package ivvq

import grails.transaction.Transactional

@Transactional(readOnly = true)
class UniversalController {

    MovieService movieService
    BookService bookService
    TVShowService TVShowService

    /**
     * Searches movies,books corresponding to a search string passed in params
     * @return a list of movies, books as search result
     */
    def doSearchAll() {
        params.max = 5
        def moviesList = movieService.searchMovies(params)
        def booksList = bookService.searchBooks(params)
        def tvShowList = TVShowService.searchTVShow(params)
        render(view: 'index', model: [bookInstanceList:booksList, bookInstanceCount: booksList.size(),
                                      movieInstanceList:moviesList, movieInstanceCount: moviesList.size(),
                                      tvShowInstanceList:tvShowList, tvShowInstanceCount: tvShowList.size()])
    }

    /**
     * Searches books corresponding to a search string passed in params
     * @return a list of books as search result
     */
    def doSearchBooks() {
        params.max = 5
        def booksList = bookService.searchBooks(params)
        render(view: 'index', model: [bookInstanceList:booksList, bookInstanceCount: booksList.size()])

    }

    /**
     * Searches movies corresponding to a search string passed in params
     * @return a list of movies as search result
     */
    def doSearchMovies() {
        params.max = 5
        def moviesList = movieService.searchMovies(params)
        render(view: 'searchResults', model: [movieInstanceList:moviesList, movieInstanceCount: moviesList.size()])

    }

    def doSearchTvShow() {

        def tvShowList = TVShowService.searchTVShow(params)
    }


}
