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
        def isBook =  false, isTVShow = false, isMovie = false
        Book book
        Movie movie
        TVShow tvShow
        if(params['itemBookId'] != null && params['itemBookId']?.toString().trim() != "")
        {
            book = Book.findByIsbn13(params['itemBookId'])
            isBook = true
        }

        if(params['itemMovieId'] != null && params['itemMovieId']?.toString().trim() != "")
        {
            movie = Movie.findByImdbID(params['itemMovieId'])
            isMovie = true
        }

        if(params['itemTVShowId'] != null && params['itemTVShowId']?.toString().trim() != "")
        {
            tvShow = TVShow.findByImdbID(params['itemTVShowId'])
            isTVShow = true
        }


        boolean verif = comment != null && comment.trim() != "" && user != null && title != null && title.trim() != ""
        Comment newComment = new Comment(user: user, date: new Date(), title: title, comment: comment)
        if(comment.size() <20) verif = false
        if(verif) {
            newComment.save()
            ItemUser newItemUser

            //Verify if the user has commented on this item before before creating another Item
            //To do

            if(isBook)
                newItemUser = new ItemUser(user: user,comments: [newComment],book: book )
            else if (isTVShow)
                newItemUser = new ItemUser(user: user,comments: [newComment],tvShow: tvShow )
            else
                newItemUser = new ItemUser(user: user,comments: [newComment],movie: movie )
            if(!newComment.hasErrors()) {
                newItemUser.save(flush: true)
            }

            }
        else {
            flash.error = "Erreur: Votre commentaire n'est pas posté, verifiez que tous les champs sont saisis et que votre commentaire contient au moins 20 caracteres"
           }


          if(isBook)
              redirect(uri: "/book/show/" + book.id)
          if(isMovie)
              redirect(uri: "/movie/show/" + movie.id)
          if(isTVShow)
              redirect(uri: "/TVShow/show/" + tvShow.id)
    }

}
