package changemanagement

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock


/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Request)
@Mock([Contacts,Comments,Milestones])

class RequestSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validation of nullable objects" () {
        when:
        Request req = new Request()
        then:
        !req.validate()

    }
    void "test for request creation with all the request attributes"() {
        when:
         Request r1 = new Request(category:"Change Management",type:"General",item:"Password Change", title:"Change Password",description:"test",
                urgency: "High", impact:"High", risk:"Low",phase:"Submit",state:"Open",status: "Submitted", createdDate: Date.parse("MM/dd/yyyy","04/22/2017"),
                modifiedDate: new Date(), scheduledStartDate: new Date(), scheduledEndDate: new Date(), closedDate: new Date()
        )
        Contacts c1 = new Contacts(contactName: "Jack Rogers", email: "jackrogers@gmail.com",contactType: "Submitter")
        c1.save(flush:true)
        r1.addToContacts(c1)

        Comments comments = new Comments(addedBy: "Jack Rogers", commentAddedDate: new Date(), comments:"This change is needed immediately")
        comments.save(flush:true)
        r1.addToComments(comments)

        Milestones m1 = new Milestones(phase: "Submit",milestoneDate: new Date(), milestoneName: "Request Received")
        m1.save(flush:true)
        r1.addToMilestones(m1)

        r1.save(flush:true)
        then:
        r1.validate()

    }

    void "test for request creation with nullable attributes"() {
        when:
        Request r1 = new Request(category:"Change Management",type:"General",item:"Password Change", title:"Change Password",description:"test",
                urgency: "High", impact:"High", risk:"Low",phase:"Submit",state:"Open",status: "Submitted", createdDate: Date.parse("MM/dd/yyyy","04/22/2017")
        )

        r1.save(flush:true)
        then:
        r1.validate()

    }



}
