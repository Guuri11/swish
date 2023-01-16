package com.swish.app.controller;

import com.swish.app.entity.TeamStats;
import com.swish.app.service.TeamStatsService;
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
@RequestMapping("/api/v1/team_stats")
public class TeamStatsController {

  private final TeamStatsService teamStatsService;

  TeamStatsController(final TeamStatsService teamStatsService) {

    this.teamStatsService = teamStatsService;
  }

  @GetMapping
  public CollectionModel<EntityModel<TeamStats>> all() {

    return teamStatsService.all();
  }

  @PostMapping
  ResponseEntity<?> newTeamStats(@RequestBody final TeamStats newTeamStats) {

    final EntityModel<TeamStats> entityModel = teamStatsService.create(newTeamStats);
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
        .body(entityModel);
  }

  @GetMapping("/{id}")
  public EntityModel<TeamStats> one(@PathVariable final Long id) {

    return teamStatsService.one(id);
  }

  @PutMapping("/{id}")
  ResponseEntity<?> replaceTeamStats(@RequestBody final TeamStats newTeamStats, @PathVariable final Long id) {

    final EntityModel<TeamStats> entityModel = teamStatsService.update(newTeamStats, id);
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
        .body(entityModel);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> deleteTeamStats(@PathVariable final Long id) {

    teamStatsService.delete(id);
    return ResponseEntity.noContent()
        .build();
  }
}
