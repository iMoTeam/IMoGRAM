package ivvq

class Season {

    Integer seasonSize

    static hasMany = [
          episodes: ArrayClass
    ]

    static belongsTo = [
            TVShow
    ]

    static constraints = {
        seasonSize min: 0, max: 30
    }
}
