package models;

import java.util.Date;

public class Post {

    private final String id;
    private final String createdBy;
    private final Date createdAt;

//    private PostMetaData metaData;

    public Post(String id, String createdBy, Date createdAt) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Post: [" +
                "id='" + id + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ']';
    }
}
