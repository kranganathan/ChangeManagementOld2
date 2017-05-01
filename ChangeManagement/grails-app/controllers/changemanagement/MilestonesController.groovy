package changemanagement

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MilestonesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Milestones.list(params), model:[milestonesCount: Milestones.count()]
    }

    def show(Milestones milestones) {
        respond milestones
    }

    def create() {
        respond new Milestones(params)
    }

    @Transactional
    def save(Milestones milestones) {
        if (milestones == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (milestones.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond milestones.errors, view:'create'
            return
        }

        milestones.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'milestones.label', default: 'Milestones'), milestones.id])
                redirect milestones
            }
            '*' { respond milestones, [status: CREATED] }
        }
    }

    def edit(Milestones milestones) {
        respond milestones
    }

    @Transactional
    def update(Milestones milestones) {
        if (milestones == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (milestones.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond milestones.errors, view:'edit'
            return
        }

        milestones.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'milestones.label', default: 'Milestones'), milestones.id])
                redirect milestones
            }
            '*'{ respond milestones, [status: OK] }
        }
    }

    @Transactional
    def delete(Milestones milestones) {

        if (milestones == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        milestones.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'milestones.label', default: 'Milestones'), milestones.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'milestones.label', default: 'Milestones'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
