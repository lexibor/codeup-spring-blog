package com.codeup.codeupspringblog.controllers;

//import com.codeup.codeupspringblog.models.Ad;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController
{
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/user/{id}/posts")
    public String userAds(@PathVariable long id, Model model)
    {
        User user = userDao.findById(id);
        List<Post> userPosts = user.getPosts();
        model.addAttribute("userPosts", userPosts);
        model.addAttribute("user", user);

        return "posts/userPosts";
    }

//    @GetMapping(/profile)
//    public String getProfile()
//    {
//
//    }
}
