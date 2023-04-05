package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.*;
//import com.codeup.codeupspringblog.repositories.CategoryRepository;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
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
//    private final CategoryRepository categoriesDao;

    public PostController(PostRepository postDao,
                          UserRepository usersDao,
//                          CategoryRepository categoriesDao,
                          EmailService emailService)
    {
        this.postDao = postDao;
        this.usersDao = usersDao;
//        this.categoriesDao = categoriesDao;
        this.emailService = emailService;
    }


    @GetMapping("/posts")
    public String indexPage(Model model) // all posts
    {

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

    @GetMapping("/posts/create")
    public String viewCreatePost(Model model)
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println(user);


        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post)
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userCopy = new User(user);

        System.out.println(user);
        System.out.println(userCopy);
        System.out.println(post.getTitle());
        System.out.println(post.getBody());
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

//        emailService.prepareAndSend(post, "test", "this is a test");

        post.setUser(usersDao.findById(userCopy.getId()));

        System.out.println(post.getUser());


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
