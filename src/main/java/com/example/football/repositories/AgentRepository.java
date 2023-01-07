package com.example.football.repositories;

import com.example.football.models.Agent;
import com.example.football.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
}
