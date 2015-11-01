package ivvq


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static nbItemByRow = 5.0, nbItemByPage = 15
    UserService userService
    def itemUserService

    def index(Integer max) {
        User currentUser = session["currentUser"]

        if (currentUser == null) {
            redirect(uri:'/')
        }

        recherche()
    }

    /**
     * Main action of user controller, display the list of item related to the logged user
     */
    def recherche() {
        User currentUser = session["currentUser"]

        def offsetTmp = params.int('offset') ?: 0
        def items = itemUserService.getAllUserItemDAO(currentUser, nbItemByPage, offsetTmp, params.type, params.kind)


        params.max = nbItemByPage
        params.type = params.type ?: null
        params.kind = params.kind ?: null

        def nbRows = items.size() != 0 ? (int) Math.ceil(items.size()/nbItemByRow) : 0

        render(view : 'index', model:[items: items as List<ItemUser>, nbRows: nbRows, nbItemByRow: (int)nbItemByRow, itemsCount: items.getTotalCount(), params: params])
    }

    @Transactional
    def loggedInUser() {
        String username = params.username
        String password = params.password
        def user = userService.getUserLoggingIn(username,password)
        if(user != null) {
            session["currentUser"] = user
            redirect(uri:'/')
        }
        else {
            flash.error = "Erreur de connexion: identifiant ou mot de passe incorrect"
            redirect(action: "loginUser", id: params.id)
        }
    }

    def show(User userInstance) {
        //respond userInstance
        def offsetTmp = params.int('offset') ?: 0
        def items = itemUserService.getAllUserItemDAO(userInstance, nbItemByPage, offsetTmp, params.type, params.kind)


        params.max = nbItemByPage
        params.type = params.type ?: null
        params.kind = params.kind ?: null

        def nbRows = items.size() != 0 ? (int) Math.ceil(items.size()/nbItemByRow) : 0

        render(view : 'show', model:[items: items as List<ItemUser>, nbRows: nbRows, nbItemByRow: (int)nbItemByRow, itemsCount: items.getTotalCount(), params: params, userInstance: userInstance])
    }

    def create() {
        respond new User(params)
    }

    @Transactional
    def save(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view: 'create'
            return
        }

        userInstance.save flush: true

            flash.message = "iMoGram vous souhaite la bienvenue"
            redirect(action: "loginUser", id: params.id)

    }

    def edit(User userInstance) {
        respond userInstance
    }

    @Transactional
    def update(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view: 'edit'
            return
        }

        userInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*' { respond userInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(User userInstance) {

        if (userInstance == null) {
            notFound()
            return
        }

        userInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    @Transactional
    def follow(User userInstance){

        User currentUser = session["currentUser"]
        currentUser.following.add(userInstance)

        show(userInstance)

    }

    @Transactional
    def unfollow(User userInstance){

        User currentUser = session["currentUser"]
        println(currentUser.following.contains(userInstance))

        currentUser.following.remove(userInstance)
        println("After: " + currentUser.following.contains(userInstance))

        show(userInstance)

    }

    @Transactional
    def loginUser() {

    }
}
