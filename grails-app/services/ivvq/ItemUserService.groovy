package ivvq

import grails.transaction.Transactional

@Transactional
class ItemUserService {

    def deleteItemUser(ItemUser itemUser) {
        itemUser.comments*.delete()
        itemUser.delete()
    }
}
