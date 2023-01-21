package com.swish.app.repository;

import com.swish.app.entity.PlayerStats;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long> {

  Optional<PlayerStats> findByPlayer(Long player);

  Optional<PlayerStats> findByGame(Long game);
}
