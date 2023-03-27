package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.print.attribute.standard.PresentationDirection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class DiceRollController
{
    Random rand = new Random();
    @GetMapping("/roll-dice")
    public String viewDiceRollForm()
    {
        return "dice_roll";
    }

    @PostMapping("/roll-dice")
    public String sendDiceRollFormInformation(@RequestParam(name = "diceGuess") int n, Model model)
    {
        return "redirect:/roll-dice/" + n;
    }

    @GetMapping("/roll-dice/{n}")
    public String viewGuess(@PathVariable int n, Model model)
    {
        model.addAttribute("guess", n);

        int count = 0;
//        int correctNum = rand.nextInt(6 - 1 + 1) + 1;
//        model.addAttribute("correctNum", correctNum);

        List<Integer> diceRolls = new ArrayList<>();
        for(int i = 0; i < 5; i++)
        {
            diceRolls.add(rand.nextInt(6 - 1 + 1) + 1);
        }

        for(int roll : diceRolls)
        {
            if(n == roll)
            {
                count++;
            }
        }

        model.addAttribute("allRolls", diceRolls);
        model.addAttribute("count", count);

        System.out.println(n);

        return "dice_roll_guess";
    }

}
