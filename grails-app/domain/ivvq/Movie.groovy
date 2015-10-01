package ivvq

class Movie {

    String name
    int yearCreation
    String realizer
    String image

    static constraints = {
        name nullable: false, blank: false
        yearCreation min: 1900, max: 2150
        realizer nullable: false, blank: false
        image nullable: false
    }
}
