package changemanagement

/**
 * Created by ranga on 4/30/2017.
 */
class CreateRequest implements grails.validation.Validateable{

    Request requestInstance
    Comments commentsInstance
    Contacts contactsInstance

    static constraints = {
        requestInstance nullable: false, validator: { value, obj -> value != null && value.validate()}
        commentsInstance nullable: false, validator: { value, obj -> value != null && value.validate()}
        contactsInstance nullable: false, validator: { value, obj -> value != null && value.validate()}
    }
}
