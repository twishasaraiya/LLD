package strategy;

import models.Post;

import java.util.List;

public class MostRecentFeedRetrievalStrategy implements FeedRetrievalStrategy {
    @Override
    public void retrieveNewsFeed(List<Post> allPosts) {
        allPosts.sort((p1, p2) -> p2.getCreatedAt().toInstant().compareTo(p1.getCreatedAt().toInstant()));
    }
}
