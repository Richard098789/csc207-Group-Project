package interface_adapter.read_from_db;

import Use_case.read_from_db.ReadInputBoundary;
import Use_case.read_from_db.ReadInputData;
import data_transfer_object.Artist;

public class ReadController {
    private final ReadInputBoundary readInteractor;

    public ReadController(ReadInputBoundary readInteractor) {
        this.readInteractor = readInteractor;
    }

    public void execute(String documentID, Artist artist) {
        final ReadInputData readInputData = new ReadInputData(documentID, artist);

        readInteractor.execute(readInputData);
    }
}
