package Use_case.read_from_db;

public class ReadInputData {

    private final String documentID;

    public ReadInputData(String documentID) {
        this.documentID = documentID;
    }

    String getDocumentID() {return documentID;}
}
