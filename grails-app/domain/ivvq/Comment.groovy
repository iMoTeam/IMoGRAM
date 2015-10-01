package ivvq

class Comment {
    String username
    Date date
    String comment
    static constraints = {
        username blank: false
        comment blank: false, minSize: 20
    }
}
