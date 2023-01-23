package com.swish.app.domain.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.swish.app.domain.User;
import com.swish.app.infrastructure.web.controller.UserController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

  @Override
  public EntityModel<User> toModel(final User user) {

    return EntityModel.of(user,
        linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
        linkTo(methodOn(UserController.class).all()).withRel("users"));
  }

}
