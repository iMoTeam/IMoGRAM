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

        // Movie already added to the database
        if (Movie.findByImdbID(imdbID) != null) {
            return null
        }

        Movie currentMovie = new Movie()
        JSONElement json
        
        try {
            json = itemAPIService.movieAPI(imdbID)
        } catch (JSonAPIException e) {
            log.error(e.message)
            return
        }

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

        // Book already added to the database
        if (Book.findByGoogleID(googleID) != null) {
            return
        }

        Book currentBook = new Book()
        JSONElement json

        try {
            json = itemAPIService.bookAPI(googleID)
        } catch (JSonAPIException e) {
            log.error(e.message)
            return
        }

        currentBook.googleID = googleID

        // Checking if a isbn exist for this book
        def isIsbnPresent = json?.volumeInfo?.industryIdentifiers
        currentBook.isbn13 = isIsbnPresent != null ? isIsbnPresent[1]?.identifier : null

        currentBook.title = json.volumeInfo.title
        // TODO Cast with a real date format
        currentBook.publishedDate = json.volumeInfo.publishedDate
        currentBook.author = json.volumeInfo.authors[0]
        currentBook.publisher = json.volumeInfo.publisher
        // TODO Change Varachar(255)
        currentBook.description = "description" //json.volumeInfo.description
        currentBook.image = "image" //json.volumeInfo.imageLinks.thumbnail
        currentBook.pageCount = json.volumeInfo.pageCount

        currentBook.save(flush: true)

        if (!currentBook.hasErrors() && Book.findByIsbn13(currentBook.isbn13) != null) {
            return currentBook
        } else {
            throw new SaveAPIException("An error has occured while saving this book : " + googleID)
        }
    }

    TVShow jsonToTVShowSave(String imdbID) {

        // TV Show already added to the database
        if (TVShow.findByImdbID(imdbID) != null) {
            return null
        }

        TVShow currentTVShow = new TVShow()

        try {
            String requestSummury = imdbID + "?extended=full"
            JSONElement json = itemAPIService.tvshowAPI(requestSummury)
            currentTVShow = fillSummury(currentTVShow, json)

            String requestCast = imdbID + "/people"
            JSONElement jsonCast = itemAPIService.tvshowAPI(requestCast)
            currentTVShow = fillPeople(currentTVShow, jsonCast)

            String requestSeason = imdbID + "/seasons?extended=episodes"
            JSONElement jsonSeason = itemAPIService.tvshowAPI(requestSeason)
            currentTVShow = fillSeasons(currentTVShow, jsonSeason)

        } catch (JSonAPIException e) {
            log.error(e.message)
            return
        }

        currentTVShow.save(flush: true)

        if (!currentTVShow.hasErrors() && TVShow.findByImdbID(imdbID) != null) {
            return currentTVShow
        } else {
            throw new SaveAPIException("An error has occured while saving this tv show : " + imdbID)
        }
    }

    TVShow fillSummury(TVShow currentTVShow, JSONElement json) {

        currentTVShow.imdbID = json.ids.imdb
        currentTVShow.title = json.title
        currentTVShow.releaseDate = json.first_aired
        currentTVShow.runtime = json.runtime
        currentTVShow.network = json.network
        //TODO Change VARCHAR 255
        currentTVShow.overview = "test"

        for (int i = 0; i < json.genres.size(); i++) {
            currentTVShow.addToGenres(new ArrayClass(title: json.genres[i]).save(flush: true))
        }

        currentTVShow.airedEpisodes = json.aired_episodes
        currentTVShow.country = json.country

        return currentTVShow
    }

    TVShow fillPeople(TVShow currentTVShow, JSONElement jsonCast) {

        // Casts filling
        for (int i = 0; i < jsonCast.cast.size(); i++) {
            Role r = new Role()

            r.realName = jsonCast.cast[i].person.name
            r.role = jsonCast.cast[i].character
            r.save(flush: true)

            currentTVShow.addToActors(r)
        }

        // Crew filling (only the production crew)
        if (jsonCast.crew != null) {
            for (int i = 0; i < jsonCast.crew.production.size(); i++) {
                String name = jsonCast.crew.production[i].person.name
                currentTVShow.addToCrews(new ArrayClass(title: name).save(flush: true))
            }
        }

        return currentTVShow
    }

    TVShow fillSeasons(TVShow currentTVShow, JSONElement jsonSeason) {

        //Seasons filling
        for (int i = 1; i < jsonSeason.size(); i++) {
            Season currentSeason = new Season()

            currentSeason.seasonSize = jsonSeason[i].episodes.size()

            for (int j = 0; j < currentSeason.seasonSize; j++) {
                String epTitle = jsonSeason[i].episodes[j].title
                currentSeason.addToEpisodes(new ArrayClass(title: epTitle).save(flush: true))
            }

            currentSeason.save(flush: true)
            currentTVShow.addToSeasons(currentSeason)
        }

        return currentTVShow
    }
}
