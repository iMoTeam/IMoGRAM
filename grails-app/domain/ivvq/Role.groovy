package ivvq

class Role {

    String role
    String realName

    static constraints = {
        role blank: false
        realName blank: false
    }
}
