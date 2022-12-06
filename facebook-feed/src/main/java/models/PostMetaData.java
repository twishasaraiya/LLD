package models;

import java.util.ArrayList;
import java.util.List;

public class PostMetaData {

    private final String caption;
    private final String imageUrl;
    private final List<String> tags;


    public PostMetaData(String caption, String imageUrl) {
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.tags = new ArrayList<>();
    }

    public String getCaption() {
        return caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getTags() {
        return tags;
    }

    public void addTag(String tag){
        this.tags.add(tag);
    }
}
