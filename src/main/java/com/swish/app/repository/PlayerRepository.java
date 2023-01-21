package com.swish.app.repository;

import com.swish.app.entity.Player;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

  Optional<Player> findByTeam(Long team);
}
