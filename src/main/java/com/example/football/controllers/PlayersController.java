package com.example.football.controllers;

import com.example.football.models.Player;
import com.example.football.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class PlayersController {
    private final PlayerService playerService;


    @GetMapping("/")
    public String players(@RequestParam(name = "surname", required = false) String surname, Principal principal, Model model) {
        model.addAttribute("players", playerService.listPlayers(surname));
        model.addAttribute("user", playerService.getUserByPrincipal(principal));
        return "players";
    }

    @GetMapping("/player/{id}")
    public String playerInfo(@PathVariable Long id, Model model) {
        Player player = playerService.getPlayerById(id);
        model.addAttribute("player",player);
        model.addAttribute("images", player.getImages());
        return "player-info";
    }

    @PostMapping("/player/create")
    public String createPlayer(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                               @RequestParam("file3") MultipartFile file3, Player player, Principal principal) throws IOException {
        playerService.savePlayer(principal, player, file1, file2, file3);
        return "redirect:/";
    }

    @PostMapping("/player/delete/{id}")
    public String deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return "redirect:/";
    }
}
