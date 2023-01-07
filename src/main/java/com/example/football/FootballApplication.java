package com.example.football;

import com.example.football.models.Player;
import com.example.football.models.User;
import com.example.football.models.enums.Role;
import com.example.football.repositories.PlayerRepository;
import com.example.football.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;
import javax.annotation.PostConstruct;

@SpringBootApplication
public class FootballApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PlayerRepository playerRepository;

	public static void main(String[] args) {
		SpringApplication.run(FootballApplication.class, args);
	}

	@PostConstruct
	public void init() {
		User user = new User();

		user.setName("1");
		user.setEmail("example@example.com");
		user.setNumberPhone("1");
		user.setActive(true);
		user.setRoles(Set.of(Role.ROLE_ADMIN));
		user.setPassword("$2a$08$nY3epNASB7z7pEifUNL03es302qbzdy7rlHe/YZk5JdmQHBmT8E2G");

		User createdUser = userRepository.save(user);

		Player player = new Player();

		player.setSurname("1");
		player.setName("1");
		player.setPlayOfNumber(1);
		player.setAge(1);
		player.setRole("1");
		player.setCups("1");
		player.setNationality("1");
		player.setContract("1");
		player.setPersonalAwards("1");
		player.setStatistics("1");
		player.setClubCareer("1");
		player.setUser(createdUser);

		playerRepository.save(player);
	}

}
