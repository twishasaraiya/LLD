package services;

import models.Post;
import strategy.FeedRetrievalStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FeedGenerationService {

    private static final int FEED_WINDOW = 10;
    private static final int PAGE_SIZE = 2;
    private final PostService postService;
    private final NewsFeedService newsFeedService;


    public FeedGenerationService(PostService postService, NewsFeedService newsFeedService) {
        this.postService = postService;
        this.newsFeedService = newsFeedService;
    }

    public List<Post> getNewsFeed(String userId, FeedRetrievalStrategy retrievalStrategy){
        List<Post> allPosts =  newsFeedService.getAllPostForUser(userId).stream().map(postService::getPost).collect(Collectors.toList());
        retrievalStrategy.retrieveNewsFeed(allPosts);
        List<Post> relevantPosts = new ArrayList<>();

        for(int i=0; i<FEED_WINDOW; i++){
            if(i >= allPosts.size()) break;
            relevantPosts.add(allPosts.get(i));
        }
        return relevantPosts;
    }

    public List<Post> getNewsFeedPaginated(String userId, int pageNumber, FeedRetrievalStrategy retrievalStrategy){
        List<Post> allPosts =  newsFeedService.getAllPostForUser(userId).stream().map(postService::getPost).collect(Collectors.toList());
        retrievalStrategy.retrieveNewsFeed(allPosts);
        List<Post> relevantPosts = new ArrayList<>();

        int startIndex = (pageNumber-1) * PAGE_SIZE;
        int endIndex = startIndex + PAGE_SIZE;

        for(int i=startIndex; i<endIndex; i++){
            if(i >= allPosts.size()) break;
            relevantPosts.add(allPosts.get(i));
        }
        return relevantPosts;
    }

    public void updateFeedOnPostCreation(String userId, String postId, List<String> followers){
        newsFeedService.addToUserFeed(userId, postId);
        for(String followerId: followers){
            newsFeedService.addToUserFeed(followerId, postId);
        }
    }

    public void updateFeedOnPostDeletion(String userId, String postId, List<String> followers){
        newsFeedService.removeFromUserFeed(userId, postId);
        for(String followerId: followers){
            newsFeedService.removeFromUserFeed(followerId, postId);
        }
    }



}
