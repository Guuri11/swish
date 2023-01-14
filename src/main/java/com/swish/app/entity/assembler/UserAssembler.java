package com.swish.app.entity.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.swish.app.controller.UserController;
import com.swish.app.entity.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

  @Override
  public EntityModel<User> toModel(final User employee) {

    return EntityModel.of(employee, //
        linkTo(methodOn(UserController.class).one(employee.getId())).withSelfRel(),
        linkTo(methodOn(UserController.class).all()).withRel("users"));
  }

}
