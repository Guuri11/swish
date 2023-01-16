package com.swish.app.entity.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.swish.app.controller.PlayerController;
import com.swish.app.entity.Player;
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
