package com.swish.app.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.swish.app.entity.Team;
import com.swish.app.entity.assembler.TeamAssembler;
import com.swish.app.exception.TeamNotFoundException;
import com.swish.app.repository.TeamRepository;
import java.util.List;
import java.util.stream.Collectors;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

  private final TeamRepository repository;
  private final TeamAssembler assembler;

  TeamController(final TeamRepository repository, final TeamAssembler assembler) {

    this.repository = repository;
    this.assembler = assembler;
  }

  @GetMapping("/teams")
  public CollectionModel<EntityModel<Team>> all() {

    final List<EntityModel<Team>> teams = repository.findAll()
        .stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());
    return CollectionModel.of(teams, linkTo(methodOn(TeamController.class).all()).withSelfRel());
  }

  @PostMapping("/teams")
  ResponseEntity<?> newTeam(@RequestBody final Team newTeam) {

    final EntityModel<Team> entityModel = assembler.toModel(repository.save(newTeam));

    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
        .body(entityModel);
  }

  @GetMapping("/teams/{id}")
  public EntityModel<Team> one(@PathVariable final Long id) {

    final Team team = repository.findById(id) //
        .orElseThrow(() -> new TeamNotFoundException(id));

    return assembler.toModel(team);
  }

  @PutMapping("/teams/{id}")
  ResponseEntity<?> replaceTeam(@RequestBody final Team newTeam, @PathVariable final Long id) {

    final Team updatedTeam = repository.findById(id)
        .map(team -> {
          team.setName(newTeam.getName());
          team.setLocation(newTeam.getLocation());
          return repository.save(team);
        })
        .orElseGet(() -> {
          newTeam.setId(id);
          return repository.save(newTeam);
        });

    final EntityModel<Team> entityModel = assembler.toModel(updatedTeam);

    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
        .body(entityModel);
  }

  @DeleteMapping("/teams/{id}")
  ResponseEntity<?> deleteTeam(@PathVariable final Long id) {

    repository.deleteById(id);

    return ResponseEntity.noContent()
        .build();
  }
}
