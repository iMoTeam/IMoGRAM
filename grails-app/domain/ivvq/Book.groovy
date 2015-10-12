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
        googleID matches: "[0-9|a-z|A-Z]{12}", blank: false, unique: true
        isbn13 matches: "978[0-9]{10}", nullable: true
        title blank: false
        description blank: true
        author blank: false
        pageCount nullable: false, min: 0
        publishedDate nullable: false
        image blank: true
        publisher blank: false
    }
}
