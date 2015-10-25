package ivvq

class Comment {
    User user
    Date date
    String title
    String comment

    static constraints = {
        comment blank: false, minSize: 20
        title blank: false
    }
}
