package com.swish.app.repository;

import com.swish.app.entity.Player;
import com.swish.app.entity.Team;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

  Optional<Player> findByTeam(Team team);
}
