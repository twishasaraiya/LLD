package facade;

import models.Post;
import models.User;
import services.*;
import strategy.FeedRetrievalStrategy;
import strategy.MostRecentFeedRetrievalStrategy;

import java.util.List;

public class Facebook {

    private final UserService userService;
    private final FollowService followService;
    private final PostService postService;
    private final NewsFeedService newsFeedService;
    private final FeedRetrievalStrategy feedRetrievalStrategy;
    private final FeedGenerationService feedGenerationService;

    public Facebook() {
        this.userService = new UserService();
        this.followService = new FollowService();
        this.postService = new PostService();
        this.newsFeedService = new NewsFeedService();
        this.feedRetrievalStrategy = new MostRecentFeedRetrievalStrategy();
        this.feedGenerationService = new FeedGenerationService(postService, newsFeedService);
    }


    public User createUser(String userName){
        return userService.createUser(userName);
    }

    public void follow(String followerId, String followeeId){
       followService.follow(followerId, followeeId);

    }

    public void unfollow(String followerId, String followeeId){
        followService.unfollow(followerId, followeeId);
    }

    public Post createPost(String userId, String postId){
        Post post = postService.createPost(userId, postId);
        feedGenerationService.updateFeedOnPostCreation(userId, postId, followService.getFollowers(userId));
        return post;
    }

    public void deletePost(String userId, String postId){
        postService.deletePost(userId, postId);
        feedGenerationService.updateFeedOnPostDeletion(userId, postId, followService.getFollowers(userId));
    }

    public List<Post> getNewsFeed(String userId){
        return feedGenerationService.getNewsFeed(userId, feedRetrievalStrategy);
    }

    public List<Post> getNewsFeedPaginated(String userId, int pageNumber){
        return feedGenerationService.getNewsFeedPaginated(userId, pageNumber, feedRetrievalStrategy);
    }
}
