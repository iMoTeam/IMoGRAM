package ivvq

import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONElement

@Transactional
class DataFillingService {

    def itemAPIService

    def serviceMethod() {

    }

    Movie jsonToMovieSave(String imdbID) {

        JSONElement json = itemAPIService.movieAPI(imdbID)

        Movie currentMovie = new Movie()

        currentMovie.imdbID = json.imdbID
        currentMovie.title = json.Title
        currentMovie.releaseDate = new Date(2015, 03, 03);
        currentMovie.runtime = json.Runtime
        currentMovie.genre = json.Genre
        currentMovie.director = json.Director
        currentMovie.writers = json.Writer
        currentMovie.actors = json.Actors
        currentMovie.country = json.Country
        currentMovie.plot = json.Plot
        currentMovie.poster = json.Poster

        currentMovie.save(flush: true)

        currentMovie
    }

    Book jsonToBookSave(String googleID) {

        JSONElement json = itemAPIService.bookAPI(googleID)

        Book cuurentBook = new Book()

        cuurentBook.isbn13 = json.volumeInfo.industryIdentifiers[1].identifier
        cuurentBook.title = json.volumeInfo.title
        // TODO Cast with a real date format
        cuurentBook.publishedDate = json.volumeInfo.publishedDate
        // TODO Save all the autors
        cuurentBook.author = json.volumeInfo.authors[0]
        cuurentBook.publisher = json.volumeInfo.publisher
        cuurentBook.description = json.volumeInfo.description
        cuurentBook.image = json.volumeInfo.imageLinks.thumbnail
        cuurentBook.pageCount = json.volumeInfo.pageCount

        cuurentBook.save(flush: true)

        cuurentBook
    }
}
