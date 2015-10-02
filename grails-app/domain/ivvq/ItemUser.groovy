package ivvq

class ItemUser {

    User user
    Movie movie
    Song song
    Book book

    static hasMany = [comments: Comment]

    static constraints = {
        movie nullable: true
        song nullable: true
        book nullable:  true
    }
}
