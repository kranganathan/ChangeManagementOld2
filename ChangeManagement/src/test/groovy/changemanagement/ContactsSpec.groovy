package changemanagement

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Contacts)
@Mock(Request)
class ContactsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validation of nullable objects" () {
        when:
        Contacts contact = new Contacts()
        then:
        !contact.validate()

    }

    void "test for contact creation without the nullable attributes"() {
        when:
        Request r1 = new Request(category:"Change Management",type:"General",item:"Password Change", title:"Change Password",description:"test",
                urgency: "High", impact:"High", risk:"Low",phase:"Submit",state:"Open",status: "Submitted", createdDate: Date.parse("MM/dd/yyyy","04/22/2017")
        )
        r1.save(flush:true)
        Contacts c1 = new Contacts(contactName: "Jack Rogers", email: "jackrogers@gmail.com",contactType: "Submitter", request: r1)

        c1.save(flush:true)
        then:
        c1.validate()

    }

    void "test for contact creation with all attributes"() {
        when:
        Request r1 = new Request(category:"Change Management",type:"General",item:"Password Change", title:"Change Password",description:"test",
                urgency: "High", impact:"High", risk:"Low",phase:"Submit",state:"Open",status: "Submitted", createdDate: Date.parse("MM/dd/yyyy","04/22/2017")
        )
        r1.save(flush:true)
        Contacts c1 = new Contacts(contactName: "Jack Rogers", email: "jackrogers@gmail.com",contactType: "Submitter",phone: "9198769098", request: r1)
        c1.save(flush:true)
        then:
        c1.validate()

    }
}
