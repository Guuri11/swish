package com.swish.app.infrastructure.web.controller;

import com.swish.app.domain.Game;
import com.swish.app.domain.Player;
import com.swish.app.domain.PlayerStats;
import com.swish.app.domain.TeamStats;
import com.swish.app.service.GameService;
import com.swish.app.service.PlayerService;
import com.swish.app.service.PlayerStatsService;
import com.swish.app.service.TeamStatsService;
import java.util.Objects;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

  private final GameService gameService;
  private final TeamStatsService teamStatsService;
  private final PlayerStatsService playerStatsService;
  private final PlayerService playerService;

  GameController(final GameService gameService, final TeamStatsService teamStatsService,
      final PlayerStatsService playerStatsService, final PlayerService playerService) {

    this.gameService = gameService;
    this.teamStatsService = teamStatsService;
    this.playerStatsService = playerStatsService;
    this.playerService = playerService;
  }

  @GetMapping
  public CollectionModel<EntityModel<Game>> all() {

    return gameService.all();
  }

  @PostMapping
  ResponseEntity<?> newGame(@RequestBody final Game newGame) {

    final EntityModel<Game> entityModel = gameService.create(newGame);

    final TeamStats localStats = new TeamStats();
    localStats.setGame(entityModel.getContent());
    localStats.setTeam(Objects.requireNonNull(entityModel.getContent())
        .getLocal());
    localStats.setPoints(0);
    localStats.setRebounds(0);
    localStats.setAssists(0);
    teamStatsService.create(localStats);

    final TeamStats awayStats = new TeamStats();
    awayStats.setGame(entityModel.getContent());
    awayStats.setTeam(entityModel.getContent()
        .getAway());
    awayStats.setAssists(0);
    awayStats.setRebounds(0);
    awayStats.setPoints(0);
    teamStatsService.create(awayStats);

    CollectionModel<EntityModel<Player>> players = playerService.allByTeam(entityModel.getContent()
        .getLocal());
    players.forEach(playerEntityModel -> {
      final PlayerStats playerStats = new PlayerStats();
      playerStats.setPlayer(playerEntityModel.getContent());
      playerStats.setGame(entityModel.getContent());
      playerStatsService.create(playerStats);
    });

    players = playerService.allByTeam(entityModel.getContent()
        .getAway());
    players.forEach(playerEntityModel -> {
      final PlayerStats playerStats = new PlayerStats();
      playerStats.setPlayer(playerEntityModel.getContent());
      playerStats.setGame(entityModel.getContent());
      playerStatsService.create(playerStats);
    });

    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
        .body(entityModel);
  }

  @GetMapping("/{id}")
  public EntityModel<Game> one(@PathVariable final Long id) {

    return gameService.one(id);
  }

  @PutMapping("/{id}")
  ResponseEntity<?> replaceGame(@RequestBody final Game newGame, @PathVariable final Long id) {

    final EntityModel<Game> entityModel = gameService.update(newGame, id);
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
        .body(entityModel);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> deleteGame(@PathVariable final Long id) {

    gameService.delete(id);
    return ResponseEntity.noContent()
        .build();
  }

}