package com.codeup.codeupspringblog.controllers;

//import com.codeup.codeupspringblog.models.Ad;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController
{
    private UserRepository usersDao;

    public UserController(UserRepository usersDao)
    {
        this.usersDao = usersDao;
    }

    @GetMapping("/register")
    public String showRegisterUserForm()
    {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam(name="username") String username, @RequestParam(name="email") String email, @RequestParam(name="password") String password){
        User user = new User(username, email, password);
        usersDao.save(user);
        return "redirect:/posts";
    }

    @GetMapping("/user/{id}/posts")
    public String userAds(@PathVariable long id, Model model)
    {
        User user = usersDao.findById(id);
        List<Post> userPosts = user.getPosts();
        model.addAttribute("userPosts", userPosts);
        model.addAttribute("user", user);

        return "posts/userPosts";
    }
}
