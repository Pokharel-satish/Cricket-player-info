package com.cricket.cricket_data_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlayerController {

    @Autowired
    private PlayerDataRepository playerDataRepository;

    // Show the form to enter player data
    @GetMapping("/playerForm")
    public String showForm(Model model) {
        model.addAttribute("player", new PlayerData());
        return "playerForm";
    }

    // Handle the submission of player data
    @PostMapping("/submitPlayer")
    public String submitPlayer(@RequestParam("name") String name,
                               @RequestParam("age") int age,
                               @RequestParam("team") String team,
                               Model model) {
        // Create a new PlayerData object
        PlayerData player = new PlayerData(name, age, team);
        
        // Save the player data to the database
        playerDataRepository.save(player);
        
        // Send the player object to the success page
        model.addAttribute("player", player);
        
        return "playerSuccess";
    }
}

