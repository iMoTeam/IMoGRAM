package ivvq

import grails.transaction.Transactional
import net.sf.json.JSONObject
import org.codehaus.groovy.grails.web.json.JSONArray
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
        cuurentBook.description = "description" //json.volumeInfo.description
        cuurentBook.image = "image" //json.volumeInfo.imageLinks.thumbnail
        cuurentBook.pageCount = json.volumeInfo.pageCount

        cuurentBook.save(flush: true)

        cuurentBook
    }

    TVShow jsonToTVShowSave(String imdbID) {

        TVShow currentTVShow = new TVShow()

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
            currentTVShow.overview = json.overview

            for (int i = 0 ; i<json.genres.size() ; i++) {
                currentTVShow.genres.add(json.genres[i])
            }

            currentTVShow.airedEpisodes = json.aired_episodes
            currentTVShow.country = json.country

            // Casts filling
            currentTVShow.casts = new HashMap<String, String>()
            for (int i = 0 ; i<jsonCast.cast.size() ; i++) {
                String trueName = jsonCast.cast[i].person.name
                String caracterName = jsonCast.cast[i].character

                currentTVShow.casts[trueName] = caracterName
            }

            // Crew filling (only the production crew)
            for (int i = 0 ; i<currentTVShow.crew.size() ; i++) {
                currentTVShow.crew.add(jsonCast.crew.production[i].person.name)
            }

            //Seasons fillng
            currentTVShow.seasons = new HashMap<Integer, String[]>()
            for (int i = 1 ; i<jsonSeason.size() ; i++) {
                Integer seasonSize = jsonSeason[i].episodes.size()
                String[] currentSeason = new String[seasonSize]

                for (int j = 0 ; j<seasonSize ; j++) {
                    currentSeason[j] = jsonSeason[i].episodes[j].title
                }

                currentTVShow.seasons[i] = currentSeason
            }

            currentTVShow.save(flush: true)

            return currentTVShow
        }
        else
            null
    }
}
