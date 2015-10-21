package ivvq

class ItemUser {

    User user
    Movie movie
    TVShow tvShow
    Book book
    Integer rating
    boolean coupCoeur = false

    static hasMany = [comments: Comment]

    static constraints = {
        movie nullable: true
        tvShow nullable: true
        book nullable:  true
        rating nullable: true, min: 0, max: 10
    }
}
