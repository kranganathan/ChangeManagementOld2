package changemanagement

/*
This class captures all the milestones for a request. Every milestone belongs to a request.
Different milestone are defined for ITIL change type of Service and Change Request. The
milestones are used for reporting and tracking purpose.
 */
class Milestones {
    String phase
    String milestoneName
    Date milestoneDate

    static belongsTo = [request:Request]

    static constraints = {
        phase nullable: false
        milestoneName nullable: false
        milestoneDate nullable: false
    }
}
