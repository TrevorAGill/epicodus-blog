package models;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Post {
    private String content;
    private static ArrayList<Post> instances = new ArrayList<>();
    private boolean published;
    private LocalDateTime createdAt;

    private int id;
    private static int postSize;

    public Post (String content){
            this.content = content;
            this.published = false;
            this.createdAt = LocalDateTime.now();
            instances.add(this);
            this.postSize++;
            this.id = postSize;
    }

    //method to use use constructor to create a new post in order to DRY test code
    public static Post newPost() {
        Post newPost = new Post("this is a test");
        return newPost;
    }

    public static Post findById(int id){
        Post found = null;
        for (Post thisPost: instances) {
            if (thisPost.id == id)
                found = thisPost;
        }
        return found;
    }

    public static void deletePosts(int id) {
        try {
            for (Post thisPost : instances) {
                if (thisPost.id == id)
                    instances.remove(thisPost);
            }
        }
        catch (ConcurrentModificationException ex){
            ex.printStackTrace();
        }
    }

    public static void clearAllPosts(){
        instances.clear();
    }




    //Setters
    public static void setPostSize(int postSize) {
        Post.postSize = postSize;
    }

    //Getters
    public String getContent() {
        return content;
    }

    public int getId() {
        return this.id;
    }

    public static ArrayList<Post> getAll(){
        return instances;
    }


    public boolean getPublished(){ //new too
        return this.published;
    }

    public String getCreatedAt() {
        return createdAt.getDayOfWeek().toString().toLowerCase();
    }
}
