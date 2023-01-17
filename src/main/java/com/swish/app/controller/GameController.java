package com.swish.app.controller;

import com.swish.app.entity.Game;
import com.swish.app.service.GameService;
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

  GameController(final GameService gameService) {

    this.gameService = gameService;
  }

  @GetMapping
  public CollectionModel<EntityModel<Game>> all() {

    return gameService.all();
  }

  @PostMapping
  ResponseEntity<?> newGame(@RequestBody final Game newGame) {

    final EntityModel<Game> entityModel = gameService.create(newGame);
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