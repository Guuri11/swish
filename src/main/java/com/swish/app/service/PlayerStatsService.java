package com.swish.app.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.swish.app.domain.Game;
import com.swish.app.domain.Player;
import com.swish.app.domain.PlayerStats;
import com.swish.app.domain.assembler.PlayerStatsAssembler;
import com.swish.app.exception.PlayerStatsNotFoundException;
import com.swish.app.infrastructure.persistence.PlayerStatsRepository;
import com.swish.app.infrastructure.web.controller.PlayerStatsController;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
public class PlayerStatsService {

  private final PlayerStatsRepository repository;
  private final PlayerStatsAssembler assembler;

  PlayerStatsService(final PlayerStatsRepository repository, final PlayerStatsAssembler assembler) {

    this.repository = repository;
    this.assembler = assembler;
  }

  public CollectionModel<EntityModel<PlayerStats>> all() {

    final List<EntityModel<PlayerStats>> playerStats = repository.findAll()
        .stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());
    return CollectionModel.of(playerStats, linkTo(methodOn(PlayerStatsController.class).all()).withSelfRel());
  }

  public EntityModel<PlayerStats> one(final Long id) {

    final PlayerStats playerStats = repository.findById(id)
        .orElseThrow(() -> new PlayerStatsNotFoundException(id));
    return assembler.toModel(playerStats);
  }

  public CollectionModel<EntityModel<PlayerStats>> allByPlayer(final Player player) {

    final List<EntityModel<PlayerStats>> playerStats = repository.findByPlayer(player)
        .stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());
    return CollectionModel.of(playerStats, linkTo(methodOn(PlayerStatsController.class).all()).withSelfRel());
  }

  public CollectionModel<EntityModel<PlayerStats>> allbyGame(final Game game) {

    final List<EntityModel<PlayerStats>> playerStats = repository.findByGame(game)
        .stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());
    return CollectionModel.of(playerStats, linkTo(methodOn(PlayerStatsController.class).all()).withSelfRel());
  }


  public EntityModel<PlayerStats> create(final PlayerStats playerStats) {

    return assembler.toModel(repository.save(playerStats));
  }

  public EntityModel<PlayerStats> update(final PlayerStats playerStats, final Long id) {

    final PlayerStats updatedPlayerStats = repository.findById(id)
        .map(ps -> {
          ps.setAssists(playerStats.getAssists());
          ps.setGame(playerStats.getGame());
          ps.setPlayer(playerStats.getPlayer());
          ps.setPoints(playerStats.getPoints());
          ps.setRebounds(playerStats.getRebounds());
          return repository.save(ps);
        })
        .orElseGet(() -> {
          playerStats.setId(id);
          return repository.save(playerStats);
        });
    return assembler.toModel(updatedPlayerStats);
  }

  public void delete(final Long id) {

    repository.deleteById(id);
  }
}
