package ivvq

import grails.gorm.CriteriaBuilder
import grails.transaction.Transactional
import org.hibernate.Criteria
import org.hibernate.criterion.CriteriaSpecification

@Transactional
class ItemUserService {


    /**
     * Try to add an Itemuser instance
     * @param user The user to link with the item
     * @param item The item to link with the user
     * @return The ItemUser instance if it has been correctly saved
     */
    def insertItemUser(User user, def item, Integer rating, Boolean interested, Boolean favourite) {

        /*
        Exception can't be thrown for the moment because of Bootstrap and conveniency
         */

        ItemUser itemToAdd = null

        if (rating == null && (interested == null || !interested) && (favourite == null || !favourite)) {
            throw new ItemUserNotValidException("An item can't have rating, interested and favourite attrivute set to null.")
        }

        if (item instanceof Book) {
            if (ItemUser.findByBookAndUser(item, user) == null) {
                itemToAdd = new ItemUser(user: user, book: item).save(flush: true)
            } else {
                throw new ItemUserAlreadyAddedException("Cet item (id : " + item.googleID + ") existe deja pour cette utilisateur.")
            }
        } else if (item instanceof Movie) {
            if (ItemUser.findByMovieAndUser(item, user) == null) {
                itemToAdd = new ItemUser(user: user, movie: item).save(flush: true)
            } else {
                throw new ItemUserAlreadyAddedException("Cet item (id : " + item.imdbID + ") existe deja pour cette utilisateur.")
            }
        } else if (item instanceof TVShow) {
            if (ItemUser.findByTvShowAndUser(item, user) == null) {
                itemToAdd = new ItemUser(user: user, tvShow: item).save(flush: true)
            } else {
                throw new ItemUserAlreadyAddedException("Cet item (id : " + item.imdbID + ") existe deja pour cette utilisateur.")
            }
        }

        // if no exception thrown, set the attribute
        if (itemToAdd != null) {
            if (interested) {
                itemToAdd.interested = true
            }
            if (favourite) {
                itemToAdd.favourite = true
            }

            itemToAdd.rating = rating
        }

        itemToAdd
    }

    /**
     *
     * @param userSearch The user's item we are lloking for
     * @param max Number if item display by page
     * @param offset for the pagination
     * @param type Type of the item (movie, book or tv show)
     * @param kind Look for rating, interested or favourite item
     * @return The list of ItemUser which has mached
     */
    List<ItemUser> getAllUserItemDAO(User userSearch, Integer max, Integer offset, String type, String kind) {

        def criteria = ItemUser.createCriteria()
        def res = criteria.list(max: max, offset: offset) {
            //setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
            eq('user', userSearch)
            if (type) {
                isNotNull(type)
            }
            if (kind) {
                if (kind == "rating") {
                    isNotNull(kind)
                } else {
                    eq(kind, true)
                }
            }
        }

        return res

    }

    boolean isItemOfUser(User user, def item) {

        boolean isItem = false

        if(item instanceof Book) {
            isItem = (ItemUser.findByUserAndBook(user, item) != null)
        } else if (item instanceof Movie) {
            isItem = (ItemUser.findByUserAndMovie(user, item) != null)
        } else {
            isItem = (ItemUser.findByUserAndTvShow(user, item) != null)
        }
        isItem
    }

    boolean isFavourite(User user, def item) {

        boolean isFavourite = false


        if (isItemOfUser(user, item)) {
            if(item instanceof Book) {
                isFavourite = ItemUser.findByUserAndBook(user, item).favourite
            } else if (item instanceof Movie) {
                isFavourite = ItemUser.findByUserAndMovie(user, item)
            } else {
                isFavourite = ItemUser.findByUserAndTvShow(user, item)
            }
        }
        isFavourite
    }
}
