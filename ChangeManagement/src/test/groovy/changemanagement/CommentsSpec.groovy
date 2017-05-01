package changemanagement

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Comments)
@Mock(Request)
class CommentsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validation of nullable objects" () {
        when:
        Comments comment = new Comments()
        then:
        !comment.validate()
    }

    void "test for comment creation with all attributes"() {
        when:
        Request r1 = new Request(category:"Change Management",type:"General",item:"Password Change", title:"Change Password",description:"test",
                urgency: "High", impact:"High", risk:"Low",phase:"Submit",state:"Open",status: "Submitted", createdDate: Date.parse("MM/dd/yyyy","04/22/2017")
        )
        r1.save(flush:true)
        Comments c1 = new Comments(addedBy: "Jack Rogers", commentAddedDate: new Date(), comments: 'This change needs to be done immediately', request: r1)
        c1.save(flush:true)
        then:
        c1.validate()

    }

    void "test for comment creation failure when all attributes are not provided"() {
        when:
        Request r1 = new Request(category:"Change Management",type:"General",item:"Password Change", title:"Change Password",description:"test",
                urgency: "High", impact:"High", risk:"Low",phase:"Submit",state:"Open",status: "Submitted", createdDate: Date.parse("MM/dd/yyyy","04/22/2017")
        )
        r1.save(flush:true)
        Comments c1 = new Comments(addedBy: "Jack Rogers", comments: 'This change needs to be done immediately',request: r1)
        c1.save(flush:true)
        then:
        !c1.validate()

    }
}
