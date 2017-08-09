package models;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post {
    private String content;
    private static ArrayList<Post> instances = new ArrayList<>();
    private boolean published;
    private LocalDateTime createdAt;
    private int id;

    public Post (String content){
        this.content = content;
        this.published = false;
        this.createdAt = LocalDateTime.now();
        instances.add(this);
        this.id = instances.size();
    }

    //method to use use constructor to create a new post in order to DRY test code
    public static Post newPost() {
        Post newPost = new Post("this is a test");
        return newPost;
    }

    public static Post findById(int id){
        return instances.get(id-1);
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public static ArrayList<Post> getAll(){
        return instances;
    }

    public static void clearAllPosts(){
        instances.clear();
    }

    public boolean getPublished(){ //new too
        return this.published;
    }

    public String getCreatedAt() {
        return createdAt.getDayOfWeek().toString().toLowerCase();
    }
}
