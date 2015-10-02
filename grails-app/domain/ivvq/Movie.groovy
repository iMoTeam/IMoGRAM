package ivvq

class Movie {

    String imdbID
    String title
    String releaseDate
    String runtime
    String genre
    String director
    String writers
    String actors
    String country
    String plot
    String poster

    static constraints = {
        imdbID matches: "tt[0-9]{7}", blank: false, unique: true
        title blank: false
        director blank: false
        runtime blank: false
        releaseDate blank: false
        writers blank: false
        actors blank: false
        country nullable: false
        plot blank: false
        poster nullable: false
        genre blank: false
    }
}
