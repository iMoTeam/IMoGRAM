package ivvq

import grails.transaction.Transactional

@Transactional
class BookService {

    List<Book> searchBooks(def params) {
        String stringToSearch
        if(params.stringToSearch != null){
            stringToSearch = params.stringToSearch
            stringToSearch = stringToSearch.trim()
        }
        def criteria = Book.createCriteria()
        def res = criteria.list (){
            if (stringToSearch) {
               or {
                   like 'title', "%${stringToSearch}%"
                   like 'isbn13', "%${stringToSearch}%"
                   like 'description', "%${stringToSearch}%"
                   like 'author', "%${stringToSearch}%"
               }
               }
               }
        res
    }
}
