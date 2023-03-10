package com.swish.app.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.swish.app.domain.Game;
import com.swish.app.domain.Team;
import com.swish.app.domain.TeamStats;
import com.swish.app.domain.assembler.TeamStatsAssembler;
import com.swish.app.exception.TeamStatsNotFoundException;
import com.swish.app.infrastructure.persistence.TeamStatsRepository;
import com.swish.app.infrastructure.web.controller.TeamStatsController;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
public class TeamStatsService {

  private final TeamStatsRepository repository;
  private final TeamStatsAssembler assembler;

  TeamStatsService(final TeamStatsRepository repository, final TeamStatsAssembler assembler) {

    this.repository = repository;
    this.assembler = assembler;
  }

  public CollectionModel<EntityModel<TeamStats>> all() {

    final List<EntityModel<TeamStats>> teamStats = repository.findAll()
        .stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());
    return CollectionModel.of(teamStats, linkTo(methodOn(TeamStatsController.class).all()).withSelfRel());
  }

  public EntityModel<TeamStats> one(final Long id) {

    final TeamStats teamStats = repository.findById(id)
        .orElseThrow(() -> new TeamStatsNotFoundException(id));
    return assembler.toModel(teamStats);
  }

  public CollectionModel<EntityModel<TeamStats>> allByTeam(final Team team) {

    final List<EntityModel<TeamStats>> teamStats = repository.findByTeam(team)
        .stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());
    return CollectionModel.of(teamStats, linkTo(methodOn(TeamStatsController.class).all()).withSelfRel());
  }

  public CollectionModel<EntityModel<TeamStats>> oneByGame(final Game game) {

    final List<EntityModel<TeamStats>> teamStats = repository.findByGame(game)
        .stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());
    return CollectionModel.of(teamStats, linkTo(methodOn(TeamStatsController.class).all()).withSelfRel());
  }

  public EntityModel<TeamStats> create(final TeamStats teamStats) {

    return assembler.toModel(repository.save(teamStats));
  }

  public EntityModel<TeamStats> update(final TeamStats teamStats, final Long id) {

    final TeamStats updatedTeamStats = repository.findById(id)
        .map(ps -> {
          ps.setAssists(teamStats.getAssists());
          ps.setGame(teamStats.getGame());
          ps.setTeam(teamStats.getTeam());
          ps.setPoints(teamStats.getPoints());
          ps.setRebounds(teamStats.getRebounds());
          return repository.save(ps);
        })
        .orElseGet(() -> {
          teamStats.setId(id);
          return repository.save(teamStats);
        });
    return assembler.toModel(updatedTeamStats);
  }

  public void delete(final Long id) {

    repository.deleteById(id);
  }
}
