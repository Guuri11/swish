package com.swish.app.controller;

import com.swish.app.entity.PlayerStats;
import com.swish.app.service.PlayerStatsService;
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
@RequestMapping("/api/v1/player_stats")
public class PlayerStatsController {

  private final PlayerStatsService playerStatsService;

  PlayerStatsController(final PlayerStatsService playerStatsService) {

    this.playerStatsService = playerStatsService;
  }

  @GetMapping
  public CollectionModel<EntityModel<PlayerStats>> all() {

    return playerStatsService.all();
  }

  @PostMapping
  ResponseEntity<?> newPlayerStats(@RequestBody final PlayerStats newPlayerStats) {

    final EntityModel<PlayerStats> entityModel = playerStatsService.create(newPlayerStats);
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
        .body(entityModel);
  }

  @GetMapping("/{id}")
  public EntityModel<PlayerStats> one(@PathVariable final Long id) {

    return playerStatsService.one(id);
  }

  @PutMapping("/{id}")
  ResponseEntity<?> replacePlayerStats(@RequestBody final PlayerStats newPlayerStats, @PathVariable final Long id) {

    final EntityModel<PlayerStats> entityModel = playerStatsService.update(newPlayerStats, id);
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
        .body(entityModel);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> deletePlayerStats(@PathVariable final Long id) {

    playerStatsService.delete(id);
    return ResponseEntity.noContent()
        .build();
  }
}
