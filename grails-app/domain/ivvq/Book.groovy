package ivvq

class Book {
    String isbn13
    String title
    String publishedDate
    String author
    String publisher
    String description
    String image
    Integer pageCount

    static constraints = {
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
