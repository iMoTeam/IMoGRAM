package ivvq



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ItemUserController {
ItemUserService itemUserService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ItemUser.list(params), model:[itemUserInstanceCount: ItemUser.count()]
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
            respond itemUserInstance.errors, view:'create'
            return
        }

        itemUserInstance.save flush:true

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
            respond itemUserInstance.errors, view:'edit'
            return
        }

        itemUserInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ItemUser.label', default: 'ItemUser'), itemUserInstance.id])
                redirect itemUserInstance
            }
            '*'{ respond itemUserInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ItemUser itemUserInstance) {
        if (itemUserInstance == null) {
            notFound()
            return
        }

        itemUserInstance.delete flush:true
        //itemUserService.deleteItemUser(itemUserInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ItemUser.label', default: 'ItemUser'), itemUserInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'itemUser.label', default: 'ItemUser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def commentItem() {
        String comment = params['itemComment']
        User user = session['currentUser']
        String title = params['title']
        Book book = Book.findByIsbn13(params['itemId'])
        boolean verif = comment != null && comment.trim() != "" && user != null && title != null && title.trim() != "" && book != null
        Comment newComment = new Comment(user: user, date: new Date(), title: title, comment: comment)
        if(verif) {
            newComment.save()

            //Verify if the user has commented on this item before before creating another Item
            //To do
            ItemUser newItemUser = new ItemUser(user: user,comments: [newComment],book: book )
            if(!newComment.hasErrors()) {
                newItemUser.save(flush: true)
            }

            }
        else {
            flash.error = "Erreur: Votre commentaire n'est pas posté, verifiez que tous les champs sont saisis"
        }
                redirect(controller: "book", action: "show", id: book.id)



    }

}
