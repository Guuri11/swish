package com.swish.app.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.swish.app.controller.GameController;
import com.swish.app.entity.Game;
import com.swish.app.entity.assembler.GameAssembler;
import com.swish.app.exception.GameNotFoundException;
import com.swish.app.repository.GameRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  private final GameRepository repository;
  private final GameAssembler assembler;

  GameService(final GameRepository repository, final GameAssembler assembler) {

    this.repository = repository;
    this.assembler = assembler;
  }

  public CollectionModel<EntityModel<Game>> all() {

    final List<EntityModel<Game>> games = repository.findAll()
        .stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());
    return CollectionModel.of(games, linkTo(methodOn(GameController.class).all()).withSelfRel());
  }

  public EntityModel<Game> one(final Long id) {

    final Game game = repository.findById(id)
        .orElseThrow(() -> new GameNotFoundException(id));
    return assembler.toModel(game);
  }

  public EntityModel<Game> create(final Game game) {

    return assembler.toModel(repository.save(game));
  }

  public EntityModel<Game> update(final Game game, final Long id) {

    final Game updatedGame = repository.findById(id)
        .map(g -> {
          g.setAway(game.getAway());
          g.setLocal(game.getLocal());
          g.setAwayScore(game.getAwayScore());
          g.setScheduleDate(game.getScheduleDate());
          g.setLocalScore(game.getLocalScore());
          return repository.save(g);
        })
        .orElseGet(() -> {
          game.setId(id);
          return repository.save(game);
        });
    return assembler.toModel(updatedGame);
  }

  public void delete(final Long id) {

    repository.deleteById(id);
  }
}
