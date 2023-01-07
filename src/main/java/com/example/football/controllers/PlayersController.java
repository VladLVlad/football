package com.example.football.controllers;

import com.example.football.models.Agent;
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
    model.addAttribute("player", player);
    if (player.getAgentId() != null) {
      Agent agent = playerService.getAgentById(player.getAgentId());
      model.addAttribute("agent", agent);
    }

    model.addAttribute("images", player.getImages());
    return "player-info";
  }

  @PostMapping("/player/create")
  public String createPlayer(
      @RequestParam(value = "file1", required = false) MultipartFile file1,
      @RequestParam(value = "file2", required = false) MultipartFile file2,
      @RequestParam(value = "file3", required = false) MultipartFile file3,
      Player player,
      Principal principal) throws IOException {
    playerService.savePlayer(principal, player, file1, file2, file3);
    return "redirect:/";
  }

  @PostMapping("/player/delete/{id}")
  public String deletePlayer(@PathVariable Long id) {
    playerService.deletePlayer(id);
    return "redirect:/";
  }

  @PostMapping("/player/{id}/agent")
  public String createAgent(@PathVariable Long id, Agent agent) {
    Agent createdAgent = playerService.createAgent(id, agent);
    return "redirect:/player/" + id + "/agent/" + createdAgent.getId();
  }

  @PostMapping("/player/{id}/update")
  public String updatePlayer(@PathVariable Long id, Player player) {
    playerService.updatePlayer(id, player);
    return "redirect:/player/" + id;
  }

  @GetMapping("/player/{playerId}/agent/{agentId}")
  public String getPlayerAgent(@PathVariable Long playerId, @PathVariable Long agentId, Model model) {
    Agent agent = playerService.getAgentById(agentId);
    model.addAttribute("agent", agent);
    model.addAttribute("images", agent.getImages());
    return "agent-info";
  }

  @PostMapping("/player/{playerId}/agent/{agentId}/delete")
  public String deleteAgent(@PathVariable Long playerId, @PathVariable Long agentId, Model model) {
    playerService.deleteAgent(playerId, agentId);
    return "redirect:/player/" + playerId;
  }
}
