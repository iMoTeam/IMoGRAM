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

    Map<String, String> casts
    Map<Integer, String[]> seasons

    static hasMany = [
            genres: String,
            crew: String,
    ]

    static constraints = {
        imdbID matches: "tt[0-9]{7}", blank: false, unique: true
        title blank: false
        releaseDate blank: false
        runtime blank: false
        network blank: false
        overview blank: false
        genres blank: false
        airedEpisodes min:0, nullable: false
        country blank: false
        casts empty: false

    }
}
