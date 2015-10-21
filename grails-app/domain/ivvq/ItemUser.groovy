package ivvq

class ItemUser {

    User user
    Movie movie
    TVShow tvShow
    Book book
    Integer note
    boolean coupCoeur = false

    static hasMany = [comments: Comment]

    static constraints = {
        movie nullable: true
        tvShow nullable: true
        book nullable:  true
    }
}
