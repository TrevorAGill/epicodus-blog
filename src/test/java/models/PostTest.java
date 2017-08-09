package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static models.Post.newPost;
import static org.junit.Assert.*;

public class PostTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        // Remember, the test will fail without these lines! We need to empty leftover Posts from previous tests!
        Post.clearAllPosts();
        Post.setPostSize(0);
    }

    @Test
    public void NewPostObjectGetsCorrectlyCreated_true() throws Exception {
        Post post = new Post("Day 1: Intro");
        assertEquals(true, post instanceof Post);
    }

    @Test
    public void PostInstantiatesWithContent_true() throws Exception {
        Post post = new Post("Day 1: Intro");
        assertEquals("Day 1: Intro", post.getContent());
    }

    @Test
    public void getPublished_isFalseAfterInstantiation_false() throws Exception {
        Post myPost = new Post("Day 1: Intro");
        assertEquals(false, myPost.getPublished()); //should never start as published
    }

//    @Test
//    public void getCreatedAt_instantiatesWithCurrentTime_today() throws Exception {
//        Post myPost = newPost();
//        assertEquals(LocalDateTime.now().getDayOfWeek(),myPost.getCreatedAt().getDayOfWeek());
//    }

    @Test
    public void getId_postsInstantiateWithAnID_1() throws Exception {
        Post myPost = newPost();
        Post otherPost = new Post("How to pair successfully");
        assertEquals(2, otherPost.getId());
    }

    @Test
    public void findReturnsCorrectPost() throws Exception {
        Post myPost = newPost();
        assertEquals(myPost, Post.findById(1));
    }

    @Test
    public void findReturnsCorrectPostWhenMoreThanOnePostExists() throws Exception {
        Post post = newPost();
        Post otherPost = new Post("How to pair successfully");
        assertEquals(otherPost, Post.findById(2));
    }

    @Test
    public void testIDIncrementFunction() throws Exception {
        Post post = newPost();
        Post otherPost = new Post("How to pair successfully");
        Post anotherPost = new Post("hey guys");
        Post fourthPost = new Post("4th post");
        assertEquals(4, fourthPost.getId());
    }

    @Test
    public void testDeletePostFunction1() throws Exception {
        Post post = newPost();
        Post otherPost = new Post("How to pair successfully");
        Post anotherPost = new Post("hey guys");
        Post fourthPost = new Post("4th post");
        Post.deletePosts(3);
        assertEquals(3, Post.getAll().size());
    }

    @Test
    public void testDeletePostFunction2() throws Exception {
        Post post = newPost();
        Post otherPost = new Post("How to pair successfully");
        Post anotherPost = new Post("hey guys");
        Post fourthPost = new Post("4th post");
        Post.deletePosts(3);
        Post fifthPost = new Post("5th post");
        assertEquals(5, fifthPost.getId());
    }

    @Test
    public void testDeletePostContains() throws Exception {
        Post post = newPost();
        Post otherPost = new Post("How to pair successfully");
        Post anotherPost = new Post("hey guys");
        Post fourthPost = new Post("4th post");
        Post.deletePosts(3);
        Post fifthPost = new Post("5th post");
        assertFalse(Post.getAll().contains(anotherPost));
    }

    @Test
    public void testUpdateMethod() throws Exception {
        Post post = newPost();
        post.update("this is an edit");
        assertEquals("this is an edit", post.getContent());
    }

    @Test
    public void testUpdateMethodOnMultiplePosts() throws Exception {
        Post post = newPost();
        Post otherPost = new Post("How to pair successfully");
        Post anotherPost = new Post("hey guys");
        Post fourthPost = new Post("4th post");
        Post found = Post.findById(3);
        found.update("edited post");
        assertEquals("edited post", found.getContent());
    }
}