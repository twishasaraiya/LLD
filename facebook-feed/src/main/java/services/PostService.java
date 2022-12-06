package services;

import exceptions.BadRequestException;
import exceptions.NotFoundException;
import models.Post;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PostService {

    private final Map<String, Post> posts;

    public PostService() {
        this.posts = new HashMap<>();
    }

    public Post getPost(String postId){
        if(!posts.containsKey(postId)){
            throw new NotFoundException("Post not found");
        }
        return posts.get(postId);
    }

    public Post createPost(String userId, String postId){
        Post post = new Post(postId, userId, new Date());
        posts.put(postId, post);
        return post;
    }

    public void deletePost(String userId, String postId){
        if(!posts.containsKey(postId)){
            throw new NotFoundException("Post not found");
        }
        if(!posts.get(postId).getCreatedBy().equals(userId)){
            throw new BadRequestException();
        }
        posts.remove(postId);
    }
}
