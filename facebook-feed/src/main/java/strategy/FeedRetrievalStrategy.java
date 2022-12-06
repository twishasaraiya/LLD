package strategy;

import models.Post;

import java.util.List;

public interface FeedRetrievalStrategy {

    void retrieveNewsFeed(List<Post> allPosts);
}
