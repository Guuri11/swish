package com.swish.app.domain.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.swish.app.domain.Player;
import com.swish.app.infrastructure.web.controller.PlayerController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PlayerAssembler implements RepresentationModelAssembler<Player, EntityModel<Player>> {

  @Override
  public EntityModel<Player> toModel(final Player player) {

    return EntityModel.of(player, //
        linkTo(methodOn(PlayerController.class).one(player.getId())).withSelfRel(),
        linkTo(methodOn(PlayerController.class).all()).withRel("players"));
  }

}
