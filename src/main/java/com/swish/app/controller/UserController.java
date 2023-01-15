package com.swish.app.controller;

import com.swish.app.entity.User;
import com.swish.app.service.UserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;

  UserController(final UserService userService) {

    this.userService = userService;
  }

  @GetMapping
  public CollectionModel<EntityModel<User>> all() {

    return userService.all();
  }

  @PostMapping
  ResponseEntity<?> newUser(@RequestBody final User newUser) {

    final EntityModel<User> entityModel = userService.create(newUser);
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
        .body(entityModel);
  }

  @GetMapping("/{id}")
  public EntityModel<User> one(@PathVariable final Long id) {

    return userService.one(id);
  }

  @PutMapping("/{id}")
  ResponseEntity<?> replaceUser(@RequestBody final User newUser, @PathVariable final Long id) {

    final EntityModel<User> entityModel = userService.update(newUser, id);
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
        .body(entityModel);
  }

}