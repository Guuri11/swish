package com.swish.app.infrastructure.persistence;

import com.swish.app.domain.Game;
import com.swish.app.domain.Player;
import com.swish.app.domain.PlayerStats;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long> {

  Collection<PlayerStats> findByPlayer(Player player);

  Collection<PlayerStats> findByGame(Game game);
}
