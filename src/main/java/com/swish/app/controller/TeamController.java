package com.swish.app.controller;

import com.swish.app.entity.Team;
import com.swish.app.service.TeamService;
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
@RequestMapping("/api/v1/teams")
public class TeamController {

  private final TeamService teamService;

  TeamController(final TeamService teamService) {

    this.teamService = teamService;
  }

  @GetMapping
  public CollectionModel<EntityModel<Team>> all() {

    return teamService.all();
  }

  @PostMapping
  ResponseEntity<?> newTeam(@RequestBody final Team newTeam) {

    final EntityModel<Team> entityModel = teamService.create(newTeam);
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
        .body(entityModel);
  }

  @GetMapping("/{id}")
  public EntityModel<Team> one(@PathVariable final Long id) {

    return teamService.one(id);
  }

  @PutMapping("/{id}")
  ResponseEntity<?> replaceTeam(@RequestBody final Team newTeam, @PathVariable final Long id) {

    final EntityModel<Team> entityModel = teamService.update(newTeam, id);
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
        .body(entityModel);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> deleteTeam(@PathVariable final Long id) {

    teamService.delete(id);
    return ResponseEntity.noContent()
        .build();
  }

}