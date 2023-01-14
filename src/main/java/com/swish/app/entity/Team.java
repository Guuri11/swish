package com.swish.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class Team {

  private @Id
  @GeneratedValue Long id;
  private String name;
  private String location;

  public Team() {

  }

  public Team(final String name, final String location) {

    this.name = name;
    this.location = location;
  }

  @Override
  public String toString() {

    return "Team{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", location='" + location + '\'' +
        '}';
  }

  @Override
  public boolean equals(final Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final Team team = (Team) o;

    if (!Objects.equals(id, team.id)) {
      return false;
    }
    if (!Objects.equals(name, team.name)) {
      return false;
    }
    return Objects.equals(location, team.location);
  }

  @Override
  public int hashCode() {

    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (location != null ? location.hashCode() : 0);
    return result;
  }

  /**
   * GETTER & SETTERS
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

  public String getLocation() {

    return location;
  }

  public void setLocation(final String location) {

    this.location = location;
  }
}
