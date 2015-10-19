package ivvq

import grails.transaction.Transactional

@Transactional
class ItemUserService {

    def serviceMethod() {

    }

    def getNbItemByUser(User use) {

        List<ItemUser> items = ItemUser.findAllByUser(user)

        def result = [book: 0, movie: 0, tvShow: 0]

        items.each {
            if (it.book != null) {
                result.book += 1
            } else if (it.movie != null) {
                result.movie += 1
            } else {
                result.tvShow += 1
            }
        }

        return result
    }
}
