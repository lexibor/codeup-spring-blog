package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.*;
import com.codeup.codeupspringblog.repositories.CategoryRepository;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class PostController
{
    private final EmailService emailService;
    private final PostRepository postDao;
    private final UserRepository usersDao;
    private final CategoryRepository categoriesDao;

    public PostController(PostRepository postDao,
                          UserRepository usersDao,
                          CategoryRepository categoriesDao,
                          EmailService emailService)
    {
        this.postDao = postDao;
        this.usersDao = usersDao;
        this.categoriesDao = categoriesDao;
        this.emailService = emailService;
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
    public String viewCreatePost(Model model)
    {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post)
    {
        User user = Users.randomUser(usersDao);
//
//        Set<Category> initialCategories = Categories.makeCategorySet(categories);
//        Set<Category> finalPostCategories = Categories.makeCategorySet(categories);
//        Set<Category> ifDuplicatedCategories = new HashSet<>();
//        for(Category cat : initialCategories)
//        {
//            System.out.println("Category: " + cat);
//            if(categoriesDao.findCategoryByName(cat.getName()) != null)
//            {
//                if(categoriesDao.findCategoryByName(cat.getName()).getName().equals(cat.getName()))
//                {
//                    finalPostCategories.remove(cat);
//                    ifDuplicatedCategories.add(categoriesDao.findCategoryByName(cat.getName()));
//                }
//            }
//        }
//        System.out.println(finalPostCategories);
//        System.out.println(ifDuplicatedCategories);
//
//        if(!finalPostCategories.isEmpty())
//        {
//            categoriesDao.saveAll(finalPostCategories);
//            ifDuplicatedCategories.addAll(finalPostCategories);
//        }


//        Post newPost = new Post(title, body, user, ifDuplicatedCategories);

//        System.out.println(newPost);

        post.setUser(user);

        emailService.prepareAndSend(post, "test", "this is a test");

        postDao.save(post);

        return "redirect:/posts";
    }


    @GetMapping("/posts/{id}/edit")
    public String showEditPostForm(Model model, @PathVariable long id)
    {
        model.addAttribute("post", postDao.findById(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@ModelAttribute Post post)
    {
        postDao.save(post);
        return "redirect:/posts";
    }

}
