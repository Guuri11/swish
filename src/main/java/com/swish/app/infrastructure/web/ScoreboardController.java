package com.swish.app.infrastructure.web;

import com.swish.app.domain.Game;
import com.swish.app.domain.PlayerStats;
import com.swish.app.domain.TeamStats;
import com.swish.app.service.GameService;
import com.swish.app.service.PlayerStatsService;
import com.swish.app.service.TeamStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ScoreboardController {

  @Autowired
  private GameService gameService;

  @Autowired
  private PlayerStatsService playerService;

  @Autowired
  private TeamStatsService teamService;

  @MessageMapping("/updateGame")
  @SendTo("/topic/game")
  public Game updateGame(final Game game) {

    gameService.update(game, game.getId());
    return game;
  }

  @MessageMapping("/updatePlayerStats")
  @SendTo("/topic/playerStats")
  public CollectionModel<EntityModel<PlayerStats>> updatePlayerStats(final PlayerStats playerStats) {

    playerService.update(playerStats, playerStats.getId());
    return playerService.allbyGame(playerStats.getGame());
  }

  @MessageMapping("/updateTeamStats")
  @SendTo("/topic/teamStats")
  public TeamStats updateTeamStats(final TeamStats teamStats) {

    teamService.update(teamStats, teamStats.getId());
    return teamStats;
  }
}
