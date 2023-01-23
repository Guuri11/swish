package com.swish.app.infrastructure.persistence;

import com.swish.app.domain.Game;
import com.swish.app.domain.Team;
import com.swish.app.domain.TeamStats;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamStatsRepository extends JpaRepository<TeamStats, Long> {

  Collection<TeamStats> findByTeam(Team team);

  Collection<TeamStats> findByGame(Game game);
}
