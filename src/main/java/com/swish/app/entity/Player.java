package com.swish.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;

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
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final Player player = (Player) o;

    if (!Objects.equals(id, player.id)) {
      return false;
    }
    if (!Objects.equals(name, player.name)) {
      return false;
    }
    if (!Objects.equals(position, player.position)) {
      return false;
    }
    if (!Objects.equals(number, player.number)) {
      return false;
    }
    if (!Objects.equals(age, player.age)) {
      return false;
    }
    if (status != player.status) {
      return false;
    }
    return Objects.equals(team, player.team);
  }

  @Override
  public int hashCode() {

    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (position != null ? position.hashCode() : 0);
    result = 31 * result + (number != null ? number.hashCode() : 0);
    result = 31 * result + (age != null ? age.hashCode() : 0);
    result = 31 * result + (status != null ? status.hashCode() : 0);
    result = 31 * result + (team != null ? team.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {

    return "Player{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", position='" + position + '\'' +
        ", number=" + number +
        ", status=" + status +
        ", team=" + team +
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

  public Team getTeam() {

    return team;
  }

  public void setTeam(final Team team) {

    this.team = team;
  }

}
