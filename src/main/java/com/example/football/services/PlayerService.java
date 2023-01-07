package com.example.football.services;

import com.example.football.models.Agent;
import com.example.football.models.Image;
import com.example.football.models.Player;
import com.example.football.models.User;
import com.example.football.repositories.AgentRepository;
import com.example.football.repositories.PlayerRepository;
import com.example.football.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerService {

  private final PlayerRepository playerRepository;
  private final UserRepository userRepository;
  private final AgentRepository agentRepository;

  public List<Player> listPlayers(String surname) {
    if (surname != null) {
      return playerRepository.findBySurname(surname);
    }
    return playerRepository.findAll();
  }

  public void savePlayer(Principal principal, Player player, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
    player.setUser(getUserByPrincipal(principal));
    Image image1;
    Image image2;
    Image image3;
    if (file1 != null && file1.getSize() != 0) {
      image1 = toImageEntity(file1);
      image1.setPreviewImage(true);
      player.addImageToPlayer(image1);
    }
    if (file2 != null && file2.getSize() != 0) {
      image2 = toImageEntity(file2);
      player.addImageToPlayer(image2);
    }
    if (file3 != null && file3.getSize() != 0) {
      image3 = toImageEntity(file3);
      player.addImageToPlayer(image3);
    }
    log.info("Saving new Product. Surname: {}", player.getSurname());
    Player playerFromDb = playerRepository.save(player);

    if (playerFromDb.getImages() != null && !playerFromDb.getImages().isEmpty()) {
      playerFromDb.setPreviewImageId(playerFromDb.getImages().get(0).getId());
      playerRepository.save(player);
    }
  }

  public User getUserByPrincipal(Principal principal) {
    if (principal == null) {
      return new User();
    }
    return userRepository.findByEmail(principal.getName());
  }

  private Image toImageEntity(MultipartFile file) throws IOException {
    Image image = new Image();
    image.setName(file.getName());
    image.setOriginalFileName(file.getOriginalFilename());
    image.setContentType(file.getContentType());
    image.setSize(file.getSize());
    image.setBytes(file.getBytes());
    return image;
  }

  public void deletePlayer(Long id) {
    playerRepository.deleteById(id);
  }

  public Player getPlayerById(Long id) {
    return playerRepository.findById(id).orElse(null);
  }

  public Agent getAgentById(Long id) {
    return agentRepository.findById(id).orElseThrow(() -> new RuntimeException("Agent not found!"));
  }

  public Player updatePlayer(Long playerId, Player player) {
    Player existingPlayer = getPlayerById(playerId);
    existingPlayer.setPlayOfNumber(player.getPlayOfNumber());
    existingPlayer.setAge(player.getAge());
    existingPlayer.setRole(player.getRole());

   return playerRepository.save(existingPlayer);
  }
  public Agent createAgent(Long playerId, Agent agent) {
    agent.setPlayerId(playerId);

    if (agent.getImages() != null && !agent.getImages().isEmpty()) {
      agent.setPreviewImageId(agent.getImages().get(0).getId());
    }
    Agent createdAgent = agentRepository.save(agent);

    Player player = getPlayerById(playerId);
    player.setAgentId(createdAgent.getId());
    playerRepository.save(player);

    return createdAgent;
  }

  public void deleteAgent(Long playerId, Long agentId) {
    Player player = getPlayerById(playerId);
    player.setAgentId(null);
    playerRepository.save(player);

    agentRepository.deleteById(agentId);
  }
}
