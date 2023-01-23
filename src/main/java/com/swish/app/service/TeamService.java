package com.swish.app.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.swish.app.domain.Team;
import com.swish.app.domain.assembler.TeamAssembler;
import com.swish.app.exception.TeamNotFoundException;
import com.swish.app.infrastructure.persistence.TeamRepository;
import com.swish.app.infrastructure.web.controller.TeamController;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

  private final TeamRepository repository;
  private final TeamAssembler assembler;

  TeamService(final TeamRepository repository, final TeamAssembler assembler) {

    this.repository = repository;
    this.assembler = assembler;
  }

  public CollectionModel<EntityModel<Team>> all() {

    final List<EntityModel<Team>> teams = repository.findAll()
        .stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());
    return CollectionModel.of(teams, linkTo(methodOn(TeamController.class).all()).withSelfRel());
  }

  public EntityModel<Team> one(final Long id) {

    final Team team = repository.findById(id)
        .orElseThrow(() -> new TeamNotFoundException(id));
    return assembler.toModel(team);
  }

  public EntityModel<Team> create(final Team team) {

    return assembler.toModel(repository.save(team));
  }

  public EntityModel<Team> update(final Team team, final Long id) {

    final Team updatedTeam = repository.findById(id)
        .map(t -> {
          t.setName(team.getName());
          t.setLocation(team.getLocation());
          return repository.save(t);
        })
        .orElseGet(() -> {
          team.setId(id);
          return repository.save(team);
        });
    return assembler.toModel(updatedTeam);
  }

  public void delete(final Long id) {

    repository.deleteById(id);
  }
}
