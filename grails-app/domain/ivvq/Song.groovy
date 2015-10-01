package ivvq

class Song {

    String title
    String artist
    String description
    Date releaseDate
    byte[] image
    static constraints = {
        title blank: false
        artist blank: false
        description nullable: true
        releaseDate nullable: true
        image nullable: true
    }
}
