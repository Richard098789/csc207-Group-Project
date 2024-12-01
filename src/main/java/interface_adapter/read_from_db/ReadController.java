package interface_adapter.read_from_db;

import Use_case.read_from_db.ReadInputBoundary;
import Use_case.read_from_db.ReadInputData;

public class ReadController {

    private ReadInputBoundary readInteractor;

    public ReadController(ReadInputBoundary readInteractor) {
        this.readInteractor = readInteractor;
    }

    public void execute(String documentID) {
        final ReadInputData readInputData = new ReadInputData(documentID);

        readInteractor.execute(readInputData);
    }
}
