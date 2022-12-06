package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsFeedService {


    private final Map<String, List<String>> newsFeeds;

    public NewsFeedService() {
        this.newsFeeds = new HashMap<>();
    }


    public List<String> getAllPostForUser(String userId){
        return newsFeeds.get(userId);
    }


    public void addToUserFeed(String userId, String postId){
        if(!newsFeeds.containsKey(userId)){
            newsFeeds.put(userId, new ArrayList<String>());
        }
        newsFeeds.get(userId).add(postId);
    }

    public void removeFromUserFeed(String userId, String postId){
        if(!newsFeeds.containsKey(userId)){
            return;
        }
        newsFeeds.get(userId).remove(postId);
    }

}
