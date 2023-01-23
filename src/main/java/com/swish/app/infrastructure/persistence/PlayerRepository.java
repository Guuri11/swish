package com.swish.app.infrastructure.persistence;

import com.swish.app.domain.Player;
import com.swish.app.domain.Team;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

  Collection<Player> findByTeam(Team team);
}
