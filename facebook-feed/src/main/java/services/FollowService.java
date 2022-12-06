package services;

import exceptions.NotFoundException;

import java.util.*;

public class FollowService {

    private final Map<String, Set<String>> followers;
//    private final Map<String, Set<String>> following;


    public FollowService() {
        this.followers = new HashMap<>();
//        this.following = new HashMap<>();
    }


    public void follow(String followerId, String followeeId){
        if(!followers.containsKey(followeeId)) followers.put(followeeId, new HashSet<String>());
        followers.get(followeeId).add(followerId);

//        if(!following.containsKey(followerId)) following.put(followerId, new HashSet<String>());
//        following.get(followerId).add(followeeId);

    }

    public void unfollow(String followerId, String followeeId){
        if(!followers.containsKey(followeeId)){
            throw new NotFoundException("Followee not found");
        }
        followers.get(followeeId).remove(followerId);

//        if(!following.containsKey(followerId)){
//            throw new NotFoundException("Follower not found");
//        }
//        following.get(followerId).remove(followeeId);
    }


    public List<String> getFollowers(String userId){
        return new ArrayList<>(followers.get(userId));
    }
}
