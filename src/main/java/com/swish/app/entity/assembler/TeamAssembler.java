package com.swish.app.entity.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.swish.app.controller.TeamController;
import com.swish.app.entity.Team;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class TeamAssembler implements RepresentationModelAssembler<Team, EntityModel<Team>> {

  @Override
  public EntityModel<Team> toModel(final Team team) {

    return EntityModel.of(team, //
        linkTo(methodOn(TeamController.class).one(team.getId())).withSelfRel(),
        linkTo(methodOn(TeamController.class).all()).withRel("teams"));
  }

}
