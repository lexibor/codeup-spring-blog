package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController
{
    @GetMapping("/posts")
    public String indexPage(Model model) // all posts
    {
        List<Post> posts = new ArrayList<>(List.of(
                new Post(2, "Ultimate staring contest", "There are two men staring at each other doing bizarre poses. They started floating??"),
                new Post(3, "I found a weird bug", "It is very small but it has a tiny sword and keeps hitting my foot with it. It tried climbing up my leg.")
        ));

        model.addAttribute("posts", posts);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postPage(@PathVariable long id, Model model) //individual post
    {
        Post testProduct = new Post(1, "Homeless man broke into my home", "A mute, homeless looking dude with a sword broke came into my home and broke my pots :(");

        model.addAttribute("title", testProduct.getTitle());
        model.addAttribute("description", testProduct.getBody());

        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String viewCreatePost()
    {
        return "<form method=\"post\"><button type=\"submit\">submit</button></form>";
    }

    @PostMapping("/posts/create")
    public String createPost()
    {
        return "creates the post";
    }
}
