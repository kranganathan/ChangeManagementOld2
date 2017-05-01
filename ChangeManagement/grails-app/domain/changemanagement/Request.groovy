package changemanagement

/*
This is the main class and captures all the details about a request.
A request can have one or more contacts. Some Examples of the contacts are Submitter, Requester etc
A request can also have one or more comments. The comments can be entered during the request submission or during the request
fulfillment process.
A request can also have one or more milestones. Milestones are mainly captured for reporting and tracking purpose
 */
class Request {
    String category
    String type
    String item
    String title
    String description
    String urgency
    String impact
    String risk
    String phase
    String state
    String status
    String createdDate
    Date modifiedDate
    Date scheduledStartDate
    Date scheduledEndDate
    Date closedDate

    static hasMany = [contacts:Contacts, comments:Comments, milestones: Milestones]

    static constraints = {
        urgency inList: ["Critical", "High", "Medium", "Low"]
        impact inList: ["Critical", "High", "Medium", "Low"]
        risk inList:["High","Medium","Low"]
        modifiedDate nullable: true
        scheduledStartDate nullable:true
        scheduledEndDate nullable: true
        closedDate nullable:true
    }
}
