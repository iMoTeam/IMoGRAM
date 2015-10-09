package ivvq

import grails.transaction.Transactional
import net.sf.json.JSONObject
import org.codehaus.groovy.grails.web.json.JSONArray
import org.codehaus.groovy.grails.web.json.JSONElement
import org.xml.sax.SAXException

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

        if (!currentMovie.hasErrors() && Movie.findByImdbID(imdbID) != null) {
            return currentMovie
        } else {
            throw new SaveAPIException("An error has occured while saving this movie : " + imdbID)
        }
    }

    Book jsonToBookSave(String googleID) {

        JSONElement json = itemAPIService.bookAPI(googleID)

        Book currentBook = new Book()

        currentBook.isbn13 = json.volumeInfo.industryIdentifiers[1].identifier
        currentBook.title = json.volumeInfo.title
        // TODO Cast with a real date format
        currentBook.publishedDate = json.volumeInfo.publishedDate
        // TODO Save all the autors
        currentBook.author = json.volumeInfo.authors[0]
        currentBook.publisher = json.volumeInfo.publisher
        currentBook.description = "description" //json.volumeInfo.description
        currentBook.image = "image" //json.volumeInfo.imageLinks.thumbnail
        currentBook.pageCount = json.volumeInfo.pageCount

        currentBook.save(flush: true)

        if (!currentBook.hasErrors() && Book.findByIsbn13(currentBook.isbn13) != null) {
            return currentBook
        } else {
            throw new SaveAPIException("An error has occured while saving this movie : " + imdbID)
        }
    }

    TVShow jsonToTVShowSave(String imdbID) {

        TVShow currentTVShow = new TVShow()
        ArrayClass tempString = new ArrayClass()

        String requestSummury = imdbID + "?extended=full"
        JSONElement json = itemAPIService.tvshowAPI(requestSummury)

        String requestCast = imdbID + "/people"
        JSONElement jsonCast = itemAPIService.tvshowAPI(requestCast)

        String requestSeason = imdbID + "/seasons?extended=episodes"
        JSONElement jsonSeason = itemAPIService.tvshowAPI(requestSeason)

        // Fill the shwo sumary first
        if (json != null) {

            currentTVShow.imdbID = imdbID
            currentTVShow.title = json.title
            currentTVShow.releaseDate = json.first_aired
            currentTVShow.runtime = json.runtime
            currentTVShow.network = json.network
            currentTVShow.overview = "test"

            for (int i = 0 ; i<json.genres.size() ; i++) {
                currentTVShow.addToGenres(new ArrayClass(title: json.genres[i]).save(flush: true))
            }

            currentTVShow.airedEpisodes = json.aired_episodes
            currentTVShow.country = json.country

            // Casts filling
            for (int i = 0 ; i<jsonCast.cast.size() ; i++) {
                Role r = new Role()

                r.realName = jsonCast.cast[i].person.name
                r.role = jsonCast.cast[i].character
                r.save(flush: true)

                currentTVShow.addToActors(r)
            }

            // Crew filling (only the production crew)
            for (int i = 0 ; i<jsonCast.crew.production.size() ; i++) {
                String name = jsonCast.crew.production[i].person.name
                currentTVShow.addToCrews(new ArrayClass(title: name).save(flush: true))
            }

            //Seasons fillng
            for (int i = 1 ; i<jsonSeason.size() ; i++) {
                Season currentSeason = new Season()

                currentSeason.seasonSize = jsonSeason[i].episodes.size()

                for (int j = 0 ; j<currentSeason.seasonSize ; j++) {
                    String epTitle = jsonSeason[i].episodes[j].title
                    currentSeason.addToEpisodes(new ArrayClass(title: epTitle).save(flush: true))
                }

                currentSeason.save(flush:true)
                currentTVShow.addToSeasons(currentSeason)
            }

            currentTVShow.save(flush: true)

            return currentTVShow
        }
        else
            null
    }
}
