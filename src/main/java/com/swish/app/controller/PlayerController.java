package com.swish.app.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.swish.app.entity.Player;
import com.swish.app.entity.assembler.PlayerAssembler;
import com.swish.app.exception.PlayerNotFoundException;
import com.swish.app.repository.PlayerRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

  private final PlayerRepository repository;
  private final PlayerAssembler assembler;

  PlayerController(final PlayerRepository repository, final PlayerAssembler assembler) {

    this.repository = repository;
    this.assembler = assembler;
  }

  @GetMapping("/players")
  public CollectionModel<EntityModel<Player>> all() {

    final List<EntityModel<Player>> players = repository.findAll()
        .stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());
    return CollectionModel.of(players, linkTo(methodOn(PlayerController.class).all()).withSelfRel());
  }

  @PostMapping("/players")
  ResponseEntity<?> newPlayer(@RequestBody final Player newPlayer) {

    final EntityModel<Player> entityModel = assembler.toModel(repository.save(newPlayer));

    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
        .body(entityModel);
  }

  @GetMapping("/players/{id}")
  public EntityModel<Player> one(@PathVariable final Long id) {

    final Player player = repository.findById(id) //
        .orElseThrow(() -> new PlayerNotFoundException(id));

    return assembler.toModel(player);
  }

  @PutMapping("/players/{id}")
  ResponseEntity<?> replacePlayer(@RequestBody final Player newPlayer, @PathVariable final Long id) {

    final Player updatedPlayer = repository.findById(id)
        .map(player -> {
          player.setName(newPlayer.getName());
          player.setPosition(newPlayer.getPosition());
          player.setNumber(newPlayer.getNumber());
          player.setAge(newPlayer.getAge());
          player.setStatus(newPlayer.getStatus());
          return repository.save(player);
        })
        .orElseGet(() -> {
          newPlayer.setId(id);
          return repository.save(newPlayer);
        });

    final EntityModel<Player> entityModel = assembler.toModel(updatedPlayer);

    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
            .toUri())
        .body(entityModel);
  }

  @DeleteMapping("/players/{id}")
  ResponseEntity<?> deletePlayer(@PathVariable final Long id) {

    repository.deleteById(id);

    return ResponseEntity.noContent()
        .build();
  }
}
