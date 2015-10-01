package ivvq

class ItemUser {

    User user
    Movie movie
    Song song
    Book book

    static hasMany = [comments: Comment]

    static constraints = {
        user nullable: false
        movie validator: {if ((movie && !song && !book) || !movie) return true else return false}
        song validator: {if ((!movie && song && !book) || !song) return true else return false}
        book validator: {if ((!movie && !song && book) || !book) return true else return false}
    }
}
