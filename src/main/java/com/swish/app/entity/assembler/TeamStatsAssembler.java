package com.swish.app.entity.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.swish.app.controller.TeamStatsController;
import com.swish.app.entity.TeamStats;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class TeamStatsAssembler implements RepresentationModelAssembler<TeamStats, EntityModel<TeamStats>> {

  @Override
  public EntityModel<TeamStats> toModel(final TeamStats teamStats) {

    return EntityModel.of(teamStats, //
        linkTo(methodOn(TeamStatsController.class).one(teamStats.getId())).withSelfRel(),
        linkTo(methodOn(TeamStatsController.class).all()).withRel("teamStats"));
  }

}
