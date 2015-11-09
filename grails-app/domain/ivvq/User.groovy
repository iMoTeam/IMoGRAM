package ivvq

import groovy.transform.EqualsAndHashCode


@EqualsAndHashCode(includes = 'firstName, lastName, username')
class User {
    String firstName
    String lastName
    String username
    String email
    String password
    byte[] profilePhoto

    static hasMany = [following:User]

    static mapping = {
            following lazy: false
    }

    static constraints = {
        email email: true
        username blank: false, unique: true
        password minSize: 6
        firstName blank: false
        lastName blank: false
        profilePhoto nullable: true;
        }

    String toString() {
        username
    }

    }


