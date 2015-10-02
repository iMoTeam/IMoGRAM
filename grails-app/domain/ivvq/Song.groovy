package ivvq

class Song {

    String title
    String artist
    String description
    Date releaseDate
    String image
    static constraints = {
        title blank: false
        artist blank: false
        description nullable: true
        releaseDate nullable: true
        image blank: true
        image nullable: true
    }
}
