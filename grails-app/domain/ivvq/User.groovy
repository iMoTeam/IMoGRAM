package ivvq

class User {
    String firstName
    String lastName
    String username
    String email
    String password
    byte[] profilePhoto

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
