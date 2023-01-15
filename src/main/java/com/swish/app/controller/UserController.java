package com.swish.app.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.swish.app.entity.User;
import com.swish.app.entity.assembler.UserAssembler;
import com.swish.app.exception.UserNotFoundException;
import com.swish.app.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasRole('USER')")
public class UserController {

  private final UserRepository repository;
  private final UserAssembler assembler;

  UserController(final UserRepository repository, final UserAssembler assembler) {

    this.repository = repository;
    this.assembler = assembler;
  }

  @GetMapping
  public CollectionModel<EntityModel<User>> all() {

    final List<EntityModel<User>> users = repository.findAll()
        .stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());
    return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
  }

  @PostMapping
  ResponseEntity<?> newUser(@RequestBody final User newUser) {

    final EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));

    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
        .body(entityModel);
  }

  @GetMapping("/{id}")
  public EntityModel<User> one(@PathVariable final Long id) {

    final User user = repository.findById(id) //
        .orElseThrow(() -> new UserNotFoundException(id));

    return assembler.toModel(user);
  }

  @PutMapping("/{id}")
  ResponseEntity<?> replaceUser(@RequestBody final User newUser, @PathVariable final Long id) {

    final User updatedUser = repository.findById(id)
        .map(user -> {
          user.setName(newUser.getName());
          user.setEmail(newUser.getEmail());
          return repository.save(user);
        })
        .orElseGet(() -> {
          newUser.setId(id);
          return repository.save(newUser);
        });

    final EntityModel<User> entityModel = assembler.toModel(updatedUser);

    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
        .body(entityModel);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> deleteUser(@PathVariable final Long id) {

    repository.deleteById(id);

    return ResponseEntity.noContent()
        .build();
  }
}
