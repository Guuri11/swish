package com.swish.app.repository;

import com.swish.app.entity.TeamStats;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamStatsRepository extends JpaRepository<TeamStats, Long> {

  Optional<TeamStats> findByTeam(Long team);

  Optional<TeamStats> findByGame(Long game);
}
