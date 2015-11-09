package ivvq


import grails.test.mixin.*
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionStatus
import spock.lang.*

@TestFor(BookController)
@Mock([Book, ItemUser, User])
class BookControllerSpec extends Specification {

    def itemUserService

    def setup() {
        itemUserService = new ItemUserService()
        itemUserService.transactionManager = Mock(PlatformTransactionManager) {getTransaction(_) >> Mock(TransactionStatus)}
        controller.itemUserService = itemUserService
    }

    def populateValidParams(params) {
        assert params != null
        params["googleID"] = "SteVfQT2WY0C"
        params["title"] = "Titre"
        params["publishedDate"] = "22-05-15"
        params["author"] = "Auteur"
        params["publisher"] = "Maison des Cartes"
        params["pageCount"] = 50
    }

    def populateValidParamsUser(params) {
        assert params != null

        params["firstName"] = 'Barry'
        params["lastName"] = 'Lindon'
        params["username"] = 'deNiro'
        params["email"] = 'smala@maill.com'
        params["password"] = 'lalala'
        params["profilePhoto"] = null
        params["following"] = new HashSet()
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.bookInstanceList
        model.bookInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.bookInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        def book = new Book()
        book.validate()
        controller.save(book)

        then: "The create view is rendered again with the correct model"
        model.bookInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        book = new Book(params)

        controller.save(book)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/book/show/1'
        controller.flash.message != null
        Book.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def book = new Book(params)
        controller.show(book)

        then: "A model is populated containing the domain instance"
        model.bookInstance == book
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def book = new Book(params)
        controller.edit(book)

        then: "A model is populated containing the domain instance"
        model.bookInstance == book
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/book/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def book = new Book()
        book.validate()
        controller.update(book)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.bookInstance == book

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        book = new Book(params).save(flush: true)
        controller.update(book)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/book/show/$book.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/book/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def book = new Book(params).save(flush: true)

        then: "It exists"
        Book.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(book)

        then: "The instance is deleted"
        Book.count() == 0
        response.redirectedUrl == '/book/index'
        flash.message != null
    }

    void "test that the deletion of a favourite book works" () {

        when: "the itemUser favourite is created with book and user"
        populateValidParams(params)
        Book book = new Book(params).save(flush: true)
        populateValidParamsUser(params)
        User user = new User(params).save(flush: true)
        ItemUser itemUser = new ItemUser(user: user, book: book, favourite: true).save(flush: true)

        then: "the itemUser exists"
        ItemUser.count() == 1

        when: "the movie is deleted to the favourite"
        session.currentUser = user
        controller.deleteToFavourite(book)

        then: "the itemUser doesn't exist anymore"
        ItemUser.count() == 0

    }

    void "test that the add of a favourite book works" () {

        given: "a book and a user"
        populateValidParams(params)
        Book book = new Book(params).save(flush: true)
        populateValidParamsUser(params)
        User user = new User(params).save(flush: true)

        when: "the itemUser favourite is created with movie and user"
        session.currentUser = user
        controller.addToFavourite(book)

        then: "the itemUser exists"
        ItemUser.count() == 1

        and: "it's favourite"
        itemUserService.getItemUser(user, book).favourite == true

    }
}
