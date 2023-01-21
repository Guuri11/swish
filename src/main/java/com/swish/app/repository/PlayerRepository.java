package com.swish.app.repository;

import com.swish.app.entity.Player;
import com.swish.app.entity.Team;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

  Collection<Player> findByTeam(Team team);
}
