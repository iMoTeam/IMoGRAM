package ivvq


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TVShowController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TVShow.list(params), model: [TVShowInstanceCount: TVShow.count()]
    }

    def show(TVShow TVShowInstance) {
        respond TVShowInstance
    }

    def create() {
        respond new TVShow(params)
    }

    @Transactional
    def save(TVShow TVShowInstance) {
        if (TVShowInstance == null) {
            notFound()
            return
        }

        if (TVShowInstance.hasErrors()) {
            respond TVShowInstance.errors, view: 'create'
            return
        }

        TVShowInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'TVShow.label', default: 'TVShow'), TVShowInstance.id])
                redirect TVShowInstance
            }
            '*' { respond TVShowInstance, [status: CREATED] }
        }
    }

    def edit(TVShow TVShowInstance) {
        respond TVShowInstance
    }

    @Transactional
    def update(TVShow TVShowInstance) {
        if (TVShowInstance == null) {
            notFound()
            return
        }

        if (TVShowInstance.hasErrors()) {
            respond TVShowInstance.errors, view: 'edit'
            return
        }

        TVShowInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TVShow.label', default: 'TVShow'), TVShowInstance.id])
                redirect TVShowInstance
            }
            '*' { respond TVShowInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TVShow TVShowInstance) {

        if (TVShowInstance == null) {
            notFound()
            return
        }

        TVShowInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TVShow.label', default: 'TVShow'), TVShowInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'TVShow.label', default: 'TVShow'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
