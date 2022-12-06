import facade.Facebook;
import models.Post;
import models.User;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        Facebook facebook = new Facebook();
        User userSunil = facebook.createUser("Sunil");
        User userAmbuj = facebook.createUser("Ambuj");
        User userJawed = facebook.createUser("Jawed");
        User userSheshan = facebook.createUser("Sheshan");


        facebook.follow(userAmbuj.getId(), userSunil.getId());
        facebook.follow(userJawed.getId(), userSunil.getId());
        facebook.follow(userSheshan.getId(), userSunil.getId());

        facebook.follow(userJawed.getId(), userAmbuj.getId());
        facebook.follow(userSheshan.getId(), userAmbuj.getId());

//        Post postFromSunil = facebook.createPost(userSunil.getId(), "post-sunil-1");
        for(int i=1; i<=20; i++){
            String postId = "post-sunil-" + i;
            facebook.createPost(userSunil.getId(), postId);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Post postFromAmbuj = facebook.createPost(userAmbuj.getId(), "post-ambuj-1");

        List<Post> feed = facebook.getNewsFeed(userJawed.getId());
        for(Post post: feed){
            System.out.println(post);
        }

        facebook.deletePost(userAmbuj.getId(), "post-ambuj-1");
        // After post deletion
        System.out.println("--------------------------After deleting post --------------------------");

        List<Post> newFeed = facebook.getNewsFeed(userJawed.getId());
        for(Post post: newFeed){
            System.out.println(post);
        }

        System.out.println("<----------------------Showing Sheshan's feed------------------------------->");
        for(int i=0; i<10; i++){
            int pageNumber = i+1;
            List<Post> paginatedFeed = facebook.getNewsFeedPaginated(userSheshan.getId(), pageNumber);
            System.out.println("Showing result of page number : " + pageNumber);
            for(Post post: paginatedFeed){
                System.out.println(post);
            }
        }

        /*
            Handle case when user unfollow someone, his feed should be updated

            Handle case when user follow someone, his feed should be generated

            Recent post logic is not implemented yet

        */

    }
}
