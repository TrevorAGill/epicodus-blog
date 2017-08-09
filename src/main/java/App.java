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
//            model.put("posts", Post.getAll());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/posts/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Post foundPost = Post.findById(idOfPostToFind); //use it to find post
            model.put("post", foundPost); //add it to model for template to display
            return new ModelAndView(model, "post-detail.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());

        get("/posts/new", (request,response)-> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"newpost-form");
        }, new HandlebarsTemplateEngine());

        //route that makes new post
        post("/posts/new", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            Post newPost = new Post(name);
            model.put("post", newPost);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}