package com.swish.app.repository;

import com.swish.app.entity.Game;
import com.swish.app.entity.Player;
import com.swish.app.entity.PlayerStats;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long> {

  Collection<PlayerStats> findByPlayer(Player player);

  Collection<PlayerStats> findByGame(Game game);
}
