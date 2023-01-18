package com.swish.app.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.swish.app.controller.UserController;
import com.swish.app.entity.User;
import com.swish.app.entity.assembler.UserAssembler;
import com.swish.app.exception.UserNotFoundException;
import com.swish.app.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository repository;
  private final UserAssembler assembler;

  UserService(final UserRepository repository, final UserAssembler assembler) {

    this.repository = repository;
    this.assembler = assembler;
  }

  public CollectionModel<EntityModel<User>> all() {

    final List<EntityModel<User>> users = repository.findAll()
        .stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());
    return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
  }

  public EntityModel<User> one(final Long id) {

    final User user = repository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id.toString()));
    return assembler.toModel(user);
  }

  public EntityModel<User> me(final Authentication authentication) {

    final User user = repository.findByEmail(authentication.getName())
        .orElseThrow(() -> new UserNotFoundException(authentication.getName()));
    return assembler.toModel(user);
  }

  public EntityModel<User> create(final User user) {

    return assembler.toModel(repository.save(user));
  }

  public EntityModel<User> update(final User user, final Long id) {

    final User updatedUser = repository.findById(id)
        .map(u -> {
          u.setName(user.getName());
          u.setEmail(user.getEmail());
          return repository.save(u);
        })
        .orElseGet(() -> {
          user.setId(id);
          return repository.save(user);
        });
    return assembler.toModel(updatedUser);
  }

  public void delete(final Long id) {

    repository.deleteById(id);
  }
}
