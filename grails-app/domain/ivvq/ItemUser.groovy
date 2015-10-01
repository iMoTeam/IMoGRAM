package ivvq

class ItemUser {

    User user
    Movie movie
    Song song
    Book book

    static hasMany = [comments: Comment]

    static constraints = {
        user nullable: false
    }
}
