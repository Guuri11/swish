package com.swish.app.infrastructure.web.controller;

import com.swish.app.domain.Player;
import com.swish.app.domain.Team;
import com.swish.app.service.PlayerService;
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
@RequestMapping("/api/v1/players")
public class PlayerController {

  private final PlayerService playerService;

  PlayerController(final PlayerService playerService) {

    this.playerService = playerService;
  }

  @GetMapping
  public CollectionModel<EntityModel<Player>> all() {

    return playerService.all();
  }

  @PostMapping
  ResponseEntity<?> newPlayer(@RequestBody final Player newPlayer) {

    final EntityModel<Player> entityModel = playerService.create(newPlayer);
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
        .body(entityModel);
  }

  @GetMapping("/{id}")
  public EntityModel<Player> one(@PathVariable final Long id) {

    return playerService.one(id);
  }

  @GetMapping("/team/{team}")
  public CollectionModel<EntityModel<Player>> allByTeam(@PathVariable final Team team) {

    return playerService.allByTeam(team);
  }

  @PutMapping("/{id}")
  ResponseEntity<?> replacePlayer(@RequestBody final Player newPlayer, @PathVariable final Long id) {

    final EntityModel<Player> entityModel = playerService.update(newPlayer, id);
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
        .body(entityModel);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> deletePlayer(@PathVariable final Long id) {

    playerService.delete(id);
    return ResponseEntity.noContent()
        .build();
  }
}
