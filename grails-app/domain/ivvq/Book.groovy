package ivvq

class Book {
    String name
    Date dateEdition
    String writer
    String extrait
    static constraints = {
        extrait nullable: true
    }
}
