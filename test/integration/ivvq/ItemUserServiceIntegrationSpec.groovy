package ivvq


import spock.lang.*

/**
 *
 */
class ItemUserServiceIntegrationSpec extends Specification {

    def itemUserService

    def setup() {
    }

    def cleanup() {
    }

    void "test that the search function return the correct collection"() {
        given: "a specific user arleady registered"
        User user = User.findByUsername("Veoth")

        when: "we search for max 20 movies which belong are related to this user"
        List<ItemUser> items = itemUserService.getAllUserItemDAO(user, 20, 0, "movie", null)

        then: "The size of the list is 7"
        items.size() == 7

        and: "all the items are movies"
        items.each {
            it.movie != null
        }

        when: "we search for max 20 items which have been rated by the user"
        List<ItemUser> items2 = itemUserService.getAllUserItemDAO(user, 20, 0, null, "rating")

        then: "The size of the list is 7"
        items.size() == 7

        and: "all the items have a rate"
        items.each {
            it.rating != null
        }
    }

    void "test that an item user can be correctly added to the database"() {
        given: "an item user with ratin, interessted and favourite set to null"
        User user = User.findByUsername("Veoth")
        Book book = Mock(Book)
        Integer rating = null
        Boolean favourite = null
        Boolean interested = null

        when: "we try to create an item user with those attribute"
        def itemUser = itemUserService.insertItemUser(user, book, rating, interested, favourite)

        then: "the return value is null"
        ItemUserNotValidException eItemUser = thrown(Exception)
        eItemUser.message == "An item can't have rating, interested and favourite attrivute set to null."

        when: "we try to create an item user with correct value"
        def itemUser2 = itemUserService.insertItemUser(user, Book.findByGoogleID("ERghoa8HhNoC"), 8, true, true)

        then: "the return value isn't null"
        itemUser2 != null

        when: "We try to add an item user wich already exist for a user"
        def itemSave = new ItemUser(user: user, book: Book.findByGoogleID("ERghoa8HhNoC"), rating: 6).save(flush: true)
        def itemUserWithService = itemUserService.insertItemUser(user, Book.findByGoogleID("ERghoa8HhNoC"), 8, null, null)

        then: "AN exception is thrown"
        ItemUserAlreadyAddedException eItemUserService = thrown(Exception)
        eItemUserService.message == "Cet item (id : " + Book.findByGoogleID("ERghoa8HhNoC").googleID + ") existe deja pour cette utilisateur."
    }

    void "test that an ItemUser is got or created correctly"() {
        given: "an user and a book"
        Book book = Book.findByGoogleID("ERghoa8HhNoC")
        User user = User.findByUsername("Melo")

        when: "we try to get the itemUser but it doesn't exist"
        ItemUser itemUser = itemUserService.getItemUser(user, book)
        itemUser.save(flush: true)

        then: "a new ItemUser is created"
        itemUser != null

        when: "the itemUser already exists and we get it"
        ItemUser itemUser2 = itemUserService.getItemUser(user, book)
        itemUser2.save(flush: true)

        then: "it gets the itemUser already existing"
        itemUser == itemUser2
    }

    void "test that an item is favourite" () {
        given: "an user, a book and the itemUser associated"
        Book book = Book.findByGoogleID("ERghoa8HhNoC")
        User user = User.findByUsername("Melo")
        ItemUser itemUser = itemUserService.getItemUser(user, book)

        when: "the itemUser isn't favourite"
        itemUser.favourite = false
        itemUser.save(flush: true)

        then: "isFavourite returns false"
        itemUserService.isFavourite(user,book) == false

        when: "the itemUser is favourite"
        itemUser.favourite = true
        itemUser.save(flush: true)

        then: "isFavourite returns true"
        itemUserService.isFavourite(user, book) == true
    }

    void "test that an itemUser is saved or deleted" () {
        given: "a user, a book and the itemUser associated"
        Book book = Book.findByGoogleID("ERghoa8HhNoC")
        User user = User.findByUsername("Melo")
        ItemUser itemUser = itemUserService.getItemUser(user, book)

        when: "the itemUser has favourite set to true and it's saved"
        itemUser.favourite = true
        itemUserService.saveItemUser(itemUser)

        then: "the itemUser exists in database"
        itemUserService.isItemOfUser(user,book) == true

        when: "the itemUser has favourite set to false and it's saved"
        itemUser.favourite = false
        itemUserService.saveItemUser(itemUser)

        then: "the itemUser is deleted"
        itemUserService.isItemOfUser(user, book) == false
    }


}
