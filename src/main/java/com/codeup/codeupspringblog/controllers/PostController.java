package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.models.Users;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController
{
    private final PostRepository postDao;
    private final UserRepository usersDao;

    public PostController(PostRepository postDao, UserRepository usersDao)
    {
        this.postDao = postDao;
        this.usersDao = usersDao;
    }


    @GetMapping("/posts")
    public String indexPage(Model model) // all posts
    {
//        List<Post> posts = new ArrayList<>(List.of(
//                new Post(2, "Ultimate staring contest", "There are two men staring at each other doing bizarre poses. They started floating??"),
//                new Post(3, "I found a weird bug", "It is very small but it has a tiny sword and keeps hitting my foot with it. It tried climbing up my leg.")
//        ));

        List<Post> posts = postDao.findAll();

        model.addAttribute("posts", posts);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postPage(@PathVariable long id, Model model) //individual post
    {
//        Post testProduct = new Post(1, "Homeless man broke into my home", "A mute, homeless looking dude with a sword broke came into my home and broke my pots :(");

        Post foundPost = postDao.findById(id);
        User postUser = foundPost.getUser();
        if(foundPost != null)
        {
            model.addAttribute("post", foundPost);
            model.addAttribute("user", postUser);
            return "posts/show";
        }
        else
        {
            return "redirect:/posts/error/postNotFound";
        }

    }

//    @GetMapping("/posts/error/{errorType}")
//    public String errorNotFound()
//    {
//        return "posts/error";
//    }

    @GetMapping("/posts/create")
    public String viewCreatePost()
    {
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, Model model)
    {
        User user = Users.randomUser(usersDao);

        Post newPost = new Post(title, body, user);

        System.out.println(title);
        System.out.println(body);

        postDao.save(newPost);

        return "redirect:/posts";
    }
}
