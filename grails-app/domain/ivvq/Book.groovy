package ivvq

class Book {
    String name
    Date dateEdition
    String writer
    String extrait
    String image
    static constraints = {
        name blank: false
        extrait nullable: true
        writer blank: false
        image nullable: true

    }
}
