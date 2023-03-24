package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.print.attribute.standard.PresentationDirection;
import java.util.Random;

@Controller
public class DiceRollController
{
    Random rand = new Random();
    @GetMapping("/roll-dice")
    public String viewDiceRollForm()
    {
        System.out.println("test");
        return "dice_roll";
    }

    @PostMapping("/roll-dice")
    public String sendDiceRollForm(@RequestParam(name = "diceGuess") int n, Model model)
    {
        return "redirect:/roll-dice/" + n;
    }

    @GetMapping("/roll-dice/{n}")
    public String viewGuess(@PathVariable int n, Model model)
    {

        model.addAttribute("guess", n);

        int randomNum = rand.nextInt(6 - 1 + 1) + 1;

        model.addAttribute("correctNum", randomNum);

        System.out.println(n);

        return "dice_roll_guess";
    }

}
