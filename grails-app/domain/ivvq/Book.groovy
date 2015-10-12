package ivvq

class Book {
    // Allow to check faster if a book has been added to the database
    String googleID

    String isbn13
    String title
    String publishedDate
    String author
    String publisher
    String description
    String image
    Integer pageCount

    static constraints = {
        googleID matches: "[0-9][a-zA-Z]", blank: false
        isbn13 matches: "978[0-9]{10}", blank: false, unique: true
        title blank: false
        description nullable: true
        author blank: false
        pageCount nullable: false, min: 0
        publishedDate nullable: false
        image blank: true, nullable: true
        publisher blank: false
    }
}
