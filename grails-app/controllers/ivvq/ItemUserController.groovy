package ivvq

import com.lowagie.text.pdf.AcroFields
import org.apache.xpath.operations.Bool

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ItemUserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ItemUser.list(params), model: [itemUserInstanceCount: ItemUser.count()]
    }

    def show(ItemUser itemUserInstance) {
        respond itemUserInstance
    }

    def create() {
        respond new ItemUser(params)
    }

    @Transactional
    def save(ItemUser itemUserInstance) {
        if (itemUserInstance == null) {
            notFound()
            return
        }

        if (itemUserInstance.hasErrors()) {
            respond itemUserInstance.errors, view: 'create'
            return
        }

        itemUserInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'itemUser.label', default: 'ItemUser'), itemUserInstance.id])
                redirect itemUserInstance
            }
            '*' { respond itemUserInstance, [status: CREATED] }
        }
    }

    def edit(ItemUser itemUserInstance) {
        respond itemUserInstance
    }

    @Transactional
    def update(ItemUser itemUserInstance) {
        if (itemUserInstance == null) {
            notFound()
            return
        }

        if (itemUserInstance.hasErrors()) {
            respond itemUserInstance.errors, view: 'edit'
            return
        }

        itemUserInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ItemUser.label', default: 'ItemUser'), itemUserInstance.id])
                redirect itemUserInstance
            }
            '*' { respond itemUserInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ItemUser itemUserInstance) {

        if (itemUserInstance == null) {
            notFound()
            return
        }

        itemUserInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ItemUser.label', default: 'ItemUser'), itemUserInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'itemUser.label', default: 'ItemUser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def ratingItem() {
        Integer rating = Integer.parseInt(params['ratingItem'])
        User user = session['currentUser']
        Boolean isBook = false, isMovie = false, isTVShow = false
        Book book
        Movie movie
        TVShow tvShow

        if (params['bookId'] != null) {
            book = Book.findByIsbn13(params['bookId'])
            isBook = true
            
        }

        if (params['movieId'] != null) {
            movie = Movie.findByImdbID(params['movieId'])
            isMovie = true

        }

        if (params['tvShowId'] != null) {
            tvShow = TVShow.findByImdbID(params['tvShowId'])
            isTVShow = true

        }

        ItemUser newItemUser
        def item
        if (isBook) {
            item = ItemUser.findByBookAndUser(book, user)
            if (item == null)
                newItemUser = new ItemUser(user: user, book: book, rating: rating)
            else
                item.rating = rating
        }

        if (isMovie) {
            item = ItemUser.findByMovieAndUser(movie, user)
            if (item == null)
                newItemUser = new ItemUser(user: user, movie: movie, rating: rating)
            else
                item.rating = rating
        }

        if (isTVShow) {
            item = ItemUser.findByTvShowAndUser(tvShow, user)
            if (item == null)
                newItemUser = new ItemUser(user: user, tvShow: tvShow, rating: rating)
            else
                item.rating = rating
        }


        newItemUser.save(flush: true)

        if (isBook) {

            redirect(uri: "/book/show/" + book.id)

        }
        if(isMovie) {
            redirect(uri: "/movie/show/" + movie.id)

        }
        if(isTVShow) {
            redirect(uri: "/TVShow/show/" + tvShow.id)

        }

    }




}
