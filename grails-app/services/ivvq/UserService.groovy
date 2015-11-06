package ivvq

import grails.transaction.Transactional

@Transactional
class UserService {
    /**
     * Gets the informations concerning the user logging in
     * @param username
     * @param password
     * @return
     */
    User getUserLoggingIn(String username, String password){
        if(username != null && password != null ) {
            username = username.trim()
            password = password.trim()
        }
        return User.findByUsernameAndPassword(username,password)
    }

    /**
     * Delete the user and all the ItemUser related to the user
     * @param user The user to delete
     */
    def deleteUser(User user) {

        List<ItemUser> items = ItemUser.findAllByUser(user)

        items.each {
            it.delete(flush: true)
        }

        user.delete(flush: true)
    }
}
