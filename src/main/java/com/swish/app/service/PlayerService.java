package com.swish.app.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.swish.app.controller.PlayerController;
import com.swish.app.entity.Player;
import com.swish.app.entity.Team;
import com.swish.app.entity.assembler.PlayerAssembler;
import com.swish.app.exception.PlayerNotFoundException;
import com.swish.app.repository.PlayerRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  private final PlayerRepository repository;
  private final PlayerAssembler assembler;

  PlayerService(final PlayerRepository repository, final PlayerAssembler assembler) {

    this.repository = repository;
    this.assembler = assembler;
  }

  public CollectionModel<EntityModel<Player>> all() {

    final List<EntityModel<Player>> players = repository.findAll()
        .stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());
    return CollectionModel.of(players, linkTo(methodOn(PlayerController.class).all()).withSelfRel());
  }

  public CollectionModel<EntityModel<Player>> allByTeam(final Team team) {

    final List<EntityModel<Player>> players = repository.findByTeam(team)
        .stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());
    return CollectionModel.of(players, linkTo(methodOn(PlayerController.class).all()).withSelfRel());
  }


  public EntityModel<Player> one(final Long id) {

    final Player player = repository.findById(id)
        .orElseThrow(() -> new PlayerNotFoundException(id));
    return assembler.toModel(player);
  }

  public EntityModel<Player> create(final Player player) {

    return assembler.toModel(repository.save(player));
  }

  public EntityModel<Player> update(final Player player, final Long id) {

    final Player updatedPlayer = repository.findById(id)
        .map(p -> {
          p.setName(player.getName());
          p.setPosition(player.getPosition());
          p.setNumber(player.getNumber());
          p.setAge(player.getAge());
          p.setStatus(player.getStatus());
          p.setTeam(player.getTeam());
          return repository.save(p);
        })
        .orElseGet(() -> {
          player.setId(id);
          return repository.save(player);
        });
    return assembler.toModel(updatedPlayer);
  }

  public void delete(final Long id) {

    repository.deleteById(id);
  }
}
