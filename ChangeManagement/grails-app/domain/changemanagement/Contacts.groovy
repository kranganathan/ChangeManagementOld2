package changemanagement

/*
This class capture all the contacts that pertain to a request. Every contact belongs to a request.
Phone is nullable. All other attributes defined are required
 */
class Contacts {

    String contactName
    String phone
    String email
    String contactType

    static belongsTo = [request:Request]

    static constraints = {
        phone nullable: true
        contactName nullable: false
        email nullable: false
        contactType nullable: false
    }
}
