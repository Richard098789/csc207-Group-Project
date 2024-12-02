package Use_case.read_from_db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data_transfer_object.Recording;
import entity.content.Content;
import entity.content.ContentFactory;

/**
 * Artist read interaction.
 */
public class ArtistReadInteractor implements ReadInputBoundary {

    private final ReadOutputBoundary readPresenter;
    private final ReadDataAccessInterface readDataAccessObject;
    private final ReadSongDataAccessInterface readSongDataAccessObject;
    // private globalStorage

    public ArtistReadInteractor(ReadOutputBoundary readPresenter, ReadDataAccessInterface readDataAccessObject,
                                ReadSongDataAccessInterface musicBrainzApi) {
        this.readPresenter = readPresenter;
        this.readDataAccessObject = readDataAccessObject;
        this.readSongDataAccessObject = musicBrainzApi;
    }

    @Override
    public void execute(ReadInputData readInputData) {
        List<Map<String, String>> comments = new ArrayList<>();
        double averageRating = 0.0;
        final String documentID = readInputData.getDocumentID();

        final Map<String, Map<String, Object>> contentInfo = new HashMap<>();
        final Map<String, Object> document = readDataAccessObject.readContents(documentID);
        if (document != null) {
            for (String key : document.keySet()) {
                final Map<String, Object> value = (Map<String, Object>) document.get(key);
                contentInfo.put(key, value);
            }
            final ContentFactory contentFactory = new ContentFactory();
            final Content content = contentFactory.create(documentID);
            content.setContentInfo(contentInfo);
            comments = content.getComments();
            averageRating = content.getAverageRating();
        }
        final Recording[] topSongs = readSongDataAccessObject.readTopSongs(documentID);
        final ReadOutputData readOutputData = new ReadOutputData(topSongs,
                comments, averageRating, readInputData.getArtist());
        readPresenter.prepareArtistDetailedView(readOutputData);

    }
}
