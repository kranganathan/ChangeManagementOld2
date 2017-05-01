package changemanagement

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CommentsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Comments.list(params), model:[commentsCount: Comments.count()]
    }

    def show(Comments comments) {
        respond comments
    }

    def create() {
        respond new Comments(params)
    }

    @Transactional
    def save(Comments comments) {
        if (comments == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (comments.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond comments.errors, view:'create'
            return
        }

        comments.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'comments.label', default: 'Comments'), comments.id])
                redirect comments
            }
            '*' { respond comments, [status: CREATED] }
        }
    }

    def edit(Comments comments) {
        respond comments
    }

    @Transactional
    def update(Comments comments) {
        if (comments == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (comments.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond comments.errors, view:'edit'
            return
        }

        comments.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'comments.label', default: 'Comments'), comments.id])
                redirect comments
            }
            '*'{ respond comments, [status: OK] }
        }
    }

    @Transactional
    def delete(Comments comments) {

        if (comments == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        comments.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'comments.label', default: 'Comments'), comments.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'comments.label', default: 'Comments'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
