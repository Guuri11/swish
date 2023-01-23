package com.swish.app.domain.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.swish.app.domain.Game;
import com.swish.app.infrastructure.web.controller.GameController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class GameAssembler implements RepresentationModelAssembler<Game, EntityModel<Game>> {

  @Override
  public EntityModel<Game> toModel(final Game game) {

    return EntityModel.of(game, //
        linkTo(methodOn(GameController.class).one(game.getId())).withSelfRel(),
        linkTo(methodOn(GameController.class).all()).withRel("games"));
  }

}
