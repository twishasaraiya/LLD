# Design Facebook

### Question Description

Design a simplified version of Facebook where users can create/delete posts, follow/unfollow another user and are able to see the most recent posts in the user's news feed. Following methods to be implemented:
- createPost(userId, postId): Compose a new post.
- getNewsFeed(userId): Retrieve the 10 most recent post ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself (Order -> most to least recent)
- follow(followerId, followeeId): Follower follows a followee.
- unfollow(followerId, followeeId): Follower unfollows a followee.
- deletePost(userId, postId): Delete an existing post.
- getNewsFeedPaginated(userId, pageNumber): Retrieve the most recent post ids in the user's news feed in a paginated manner. Each item in the news feed must be posted by users who the user followed or by the user herself (Order -> most to least recent) Assume pageSize= 2.

### Evaluation points :
- Test cases passed
- Code structuring and cleanliness 
- Scale and concurrency