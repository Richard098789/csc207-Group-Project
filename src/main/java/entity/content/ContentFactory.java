package entity.content;

public class ContentFactory implements ContentFactoryInterface{


    @Override
    public Content create(String contentId) {
        return new Content(contentId);
    }
}
