package ivvq

import grails.transaction.Transactional

@Transactional
class BookService {

    List<Book> searchBooks(String search) {
        String stringToSearch = search
     /*if(search != null){
             stringToSearch = search
             stringToSearch = stringToSearch.trim()
         }*/
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
