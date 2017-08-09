import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Post;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) { //type “psvm + tab” to autocreate this!
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Post> posts = Post.getAll();
            model.put("posts", Post.getAll());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/posts/new", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "post-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/posts/new", (request,response)-> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("content");
            Post newPost = new Post(name);
            model.put("post", newPost);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/posts/:id/delete", (request,response)-> {
            int idOfPostToDelete = Integer.parseInt(request.params("id")); //pull id - must match route segment
            Post.deletePosts(idOfPostToDelete);
            response.redirect("/");
            return null;
        });

        get("/posts/:id/edit", (request,response)-> {
            Map<String, Object> model = new HashMap<>();
            String newContent = request.queryParams("content");
            int idOfPostToEdit = Integer.parseInt(request.params("id"));
            Post editPost = Post.findById(idOfPostToEdit);
            editPost.update(newContent);
            model.put("editPost", editPost);
            return new ModelAndView(model, "post-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/posts/:id/edit", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            String newContent = request.queryParams("content");
            int idOfPostToEdit = Integer.parseInt(request.params("id"));
            Post editPost = Post.findById(idOfPostToEdit);
            editPost.update(newContent);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/posts/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToFind = Integer.parseInt(request.params("id"));
            Post foundPost = Post.findById(idOfPostToFind);
            model.put("post", foundPost);
            return new ModelAndView(model, "post-detail.hbs");
        }, new HandlebarsTemplateEngine());
    }
}