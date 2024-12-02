package Use_case.read_from_db;

import entity.content.Content;
import entity.content.ContentFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadInteractor implements ReadInputBoundary {

    private ReadOutputBoundary readPresenter;
    private ReadDataAccessInterface readDataAccessObject;
    // private globalStorage

    public ReadInteractor(ReadOutputBoundary readPresenter, ReadDataAccessInterface readDataAccessObject) {
        this.readPresenter = readPresenter;
        this.readDataAccessObject = readDataAccessObject;
    }

    @Override
    public void execute(ReadInputData readInputData) {

        String documentID = readInputData.getDocumentID();

        Map<String, Map<String, Object>> contentInfo = new HashMap<>();
        Map<String, Object> document = readDataAccessObject.readContents(documentID);
        for (String key : document.keySet()) {
            Map<String, Object> value = (Map<String, Object>) document.get(key);
            contentInfo.put(key, value);
        }

        final ContentFactory contentFactory = new ContentFactory();
        Content content = contentFactory.create(documentID);
        content.setContentInfo(contentInfo);

        List<Map<String, String>> comments = content.getComments();
        double averageRating = content.getAverageRating();

        final ReadOutputData readOutputData = new ReadOutputData(comments, averageRating);
        readPresenter.prepareDetailedView(readOutputData);

    }
}
