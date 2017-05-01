package changemanagement

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ContactsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Contacts.list(params), model:[contactsCount: Contacts.count()]
    }

    def show(Contacts contacts) {
        respond contacts
    }

    def create() {
        respond new Contacts(params)
    }

    @Transactional
    def save(Contacts contacts) {
        if (contacts == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (contacts.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond contacts.errors, view:'create'
            return
        }

        contacts.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'contacts.label', default: 'Contacts'), contacts.id])
                redirect contacts
            }
            '*' { respond contacts, [status: CREATED] }
        }
    }

    def edit(Contacts contacts) {
        respond contacts
    }

    @Transactional
    def update(Contacts contacts) {
        if (contacts == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (contacts.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond contacts.errors, view:'edit'
            return
        }

        contacts.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'contacts.label', default: 'Contacts'), contacts.id])
                redirect contacts
            }
            '*'{ respond contacts, [status: OK] }
        }
    }

    @Transactional
    def delete(Contacts contacts) {

        if (contacts == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        contacts.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'contacts.label', default: 'Contacts'), contacts.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'contacts.label', default: 'Contacts'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
