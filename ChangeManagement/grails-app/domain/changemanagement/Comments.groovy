package changemanagement

/*
This class captures all the comments for a request. A comments always belongs to a request.
All the attributes defined are required.
 */
class Comments {
    String addedBy
    String comments
    Date commentAddedDate

    static belongsTo = [request:Request]

    static constraints = {
        addedBy nullable: false
        comments nullable: false
        commentAddedDate nullable: false
    }
}
