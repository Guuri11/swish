package com.swish.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class Player {

  private @Id
  @GeneratedValue Long id;
  private String name;
  private String position;
  private Byte number;
  private Byte age;
  private PlayerStatus status;

  public Player() {

  }

  public Player(final String name, final String position, final Byte number, final Byte age,
      final PlayerStatus status) {

    this.name = name;
    this.position = position;
    this.number = number;
    this.age = age;
    this.status = status;
  }

  @Override
  public boolean equals(final Object o) {

    if (this == o) {
      return true;
    }
    if (!(o instanceof Player)) {
      return false;
    }
    final Player player = (Player) o;
    return Objects.equals(this.id, player.id);
  }

  @Override
  public int hashCode() {

    return Objects.hash(this.id, this.name, this.position, this.number, this.age, this.status);
  }

  @Override
  public String toString() {

    return "Player{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", position='" + position + '\'' +
        ", number=" + number +
        ", status=" + status +
        ", age=" + age +
        '}';
  }

  /**
   * GETTERS & SETTERS
   **/

  public Long getId() {

    return id;
  }

  public void setId(final Long id) {

    this.id = id;
  }

  public String getName() {

    return name;
  }

  public void setName(final String name) {

    this.name = name;
  }

  public String getPosition() {

    return position;
  }

  public void setPosition(final String position) {

    this.position = position;
  }

  public Byte getNumber() {

    return number;
  }

  public void setNumber(final Byte number) {

    this.number = number;
  }

  public Byte getAge() {

    return age;
  }

  public void setAge(final Byte age) {

    this.age = age;
  }

  public PlayerStatus getStatus() {

    return status;
  }

  public void setStatus(final PlayerStatus status) {

    this.status = status;
  }
}
