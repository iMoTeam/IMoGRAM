package ivvq

class TVShow {

    String imdbID
    String title
    String releaseDate
    String runtime
    String network
    String overview
    Integer airedEpisodes
    String country

    static hasMany = [
            actors: Role,
            crews: ArrayClass,
            seasons: Season,
            genres: ArrayClass
    ]

    static constraints = {
        imdbID matches: "tt[0-9]{7}", blank: false
        title blank: false
        releaseDate blank: false
        runtime blank: false
        network blank: false
        overview blank: false
        airedEpisodes min:0
        country blank: false
    }
}
