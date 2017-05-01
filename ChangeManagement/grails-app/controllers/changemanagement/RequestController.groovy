package changemanagement

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RequestController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Request.list(params), model:[requestCount: Request.count()]
    }

    def show(Request request) {
        respond request
    }

    def create() {
        respond new Request(params)
    }

    @Transactional
    def save(Request request) {
        if (request == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (request.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond request.errors, view:'create'
            return
        }

        request.setState("Open")
        request.setStatus("Submitted")
        request.setPhase("Submit")

        request.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'request.created.message', args: [request.item])
                redirect request
            }
            '*' { respond request, [status: CREATED] }
        }
    }

    def edit(Request request) {
        respond request
    }

    @Transactional
    def update(Request request) {
        if (request == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (request.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond request.errors, view:'edit'
            return
        }

        request.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'request.label', default: 'Request'), request.id])
                redirect request
            }
            '*'{ respond request, [status: OK] }
        }
    }

    @Transactional
    def delete(Request request) {

        if (request == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        request.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'request.label', default: 'Request'), request.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'request.label', default: 'Request'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
