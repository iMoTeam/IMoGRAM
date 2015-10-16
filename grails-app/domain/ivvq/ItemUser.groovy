package ivvq

class ItemUser {

    User user
    Movie movie
    TVShow tvShow
    Book book
    boolean coupCoeur = false
    boolean favourite = false

    static hasMany = [comments: Comment]

    static constraints = {
        movie nullable: true
        tvShow nullable: true
        book nullable:  true
    }
}
