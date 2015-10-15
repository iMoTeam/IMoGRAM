package ivvq

class UniversalController {

    MovieService movieService
    BookService bookService

/**
 * Searches movies,books corresponding to a search string passed in params
 * @return a list of movies, books as search result
 */
    def doSearchAll() {
        params.max = 5
        def moviesList = movieService.searchMovies(params)
        def booksList = bookService.searchBooks(params)
        render(view: 'index', model: [bookInstanceList:booksList, bookInstanceCount: booksList.totalCount,movieInstanceList:moviesList, movieInstanceCount: moviesList.totalCount])
    }
}
