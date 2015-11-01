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

   /* @Override
    boolean equals(Object o){
        boolean same = false
        if (o != null && o instanceof User)
        {
            same = this.id == ((User) o).id
        }
        return same;
    }*/

    }


