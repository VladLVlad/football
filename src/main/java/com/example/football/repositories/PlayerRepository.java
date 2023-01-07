package com.example.football.repositories;

import com.example.football.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findBySurname(String surname);
}
