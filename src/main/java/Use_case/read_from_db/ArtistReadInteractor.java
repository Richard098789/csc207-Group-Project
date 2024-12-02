package Use_case.read_from_db;

import data_transfer_object.Recording;
import entity.content.Content;
import entity.content.ContentFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtistReadInteractor implements ReadInputBoundary {

    private final ReadOutputBoundary readPresenter;
    private final ReadDataAccessInterface readDataAccessObject;
    private final ReadSongDataAccessInterface readSongDataAccessObject;
    // private globalStorage

    public ArtistReadInteractor(ReadOutputBoundary readPresenter, ReadDataAccessInterface readDataAccessObject,
                                ReadSongDataAccessInterface musicBrainZAPI) {
        this.readPresenter = readPresenter;
        this.readDataAccessObject = readDataAccessObject;
        this.readSongDataAccessObject = musicBrainZAPI;
    }

    @Override
    public void execute(ReadInputData readInputData) {
        Map<String, String> comments = new HashMap<>();
        double averageRating = 0.0;
        String documentID = readInputData.getDocumentID();

        Map<String, Map<String, Object>> contentInfo = new HashMap<>();
        Map<String, Object> document = readDataAccessObject.readContents(documentID);
        if (document != null) {
            for (String key : document.keySet()) {
                Map<String, Object> value = (Map<String, Object>) document.get(key);
                contentInfo.put(key, value);
            }
            final ContentFactory contentFactory = new ContentFactory();
            Content content = contentFactory.create(documentID);
            content.setContentInfo(contentInfo);
            comments = content.getComments();
            averageRating = content.getAverageRating();
        }
        Recording[] topSongs = readSongDataAccessObject.readTopSongs(documentID);
        System.out.println(comments);
        final ReadOutputData readOutputData = new ReadOutputData(topSongs, comments, averageRating, readInputData.getArtist());
        readPresenter.prepareArtistDetailedView(readOutputData);

    }
}
