package changemanagement

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Milestones)
@Mock(Request)
class MilestonesSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validation of nullable objects" () {
        when:
        Milestones milestones = new Milestones()
        then:
        !milestones.validate()
    }

    void "test for milestone creation with all attributes"() {
        when:
        Request r1 = new Request(category:"Change Management",type:"General",item:"Password Change", title:"Change Password",description:"test",
                urgency: "High", impact:"High", risk:"Low",phase:"Submit",state:"Open",status: "Submitted", createdDate: Date.parse("MM/dd/yyyy","04/22/2017")
        )
        r1.save(flush:true)
        Milestones m1 = new Milestones(phase: "Submit",milestoneDate: new Date(), milestoneName: "Request Received", request: r1)
        m1.save(flush:true)
        then:
        m1.validate()

    }

    void "test for milestone creation failure without all attributes"() {
        when:
        Request r1 = new Request(category:"Change Management",type:"General",item:"Password Change", title:"Change Password",description:"test",
                urgency: "High", impact:"High", risk:"Low",phase:"Submit",state:"Open",status: "Submitted", createdDate: Date.parse("MM/dd/yyyy","04/22/2017")
        )
        r1.save(flush:true)
        Milestones m1 = new Milestones(phase: "Submit", milestoneName: "Request Received", request: r1)
        m1.save(flush:true)
        then:
        !m1.validate()

    }
}
