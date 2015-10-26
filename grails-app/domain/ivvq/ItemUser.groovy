package ivvq

class ItemUser {

    User user
    Movie movie
    TVShow tvShow
    Book book
    Integer rating
    boolean interested = false
    boolean favourite = false

    static hasMany = [comments: Comment]

    static constraints = {
        movie nullable: true
        tvShow nullable: true
        book nullable:  true
        rating nullable: true, min: 0, max: 10
    }
}
