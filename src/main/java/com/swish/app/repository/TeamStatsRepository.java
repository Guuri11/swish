package com.swish.app.repository;

import com.swish.app.entity.Game;
import com.swish.app.entity.Team;
import com.swish.app.entity.TeamStats;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamStatsRepository extends JpaRepository<TeamStats, Long> {

  Collection<TeamStats> findByTeam(Team team);

  Collection<TeamStats> findByGame(Game game);
}
