package com.swish.app.entity.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.swish.app.controller.PlayerStatsController;
import com.swish.app.entity.PlayerStats;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PlayerStatsAssembler implements RepresentationModelAssembler<PlayerStats, EntityModel<PlayerStats>> {

  @Override
  public EntityModel<PlayerStats> toModel(final PlayerStats playerStats) {

    return EntityModel.of(playerStats, //
        linkTo(methodOn(PlayerStatsController.class).one(playerStats.getId())).withSelfRel(),
        linkTo(methodOn(PlayerStatsController.class).all()).withRel("playerStats"));
  }

}
