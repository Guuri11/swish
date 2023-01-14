package com.swish.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class User {

  private @Id
  @GeneratedValue Long id;
  private String email;
  private String name;

  public User() {

  }

  public User(final String email, final String name) {

    this.email = email;
    this.name = name;
  }

  @Override
  public boolean equals(final Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final User user = (User) o;

    if (!Objects.equals(id, user.id)) {
      return false;
    }
    if (!Objects.equals(email, user.email)) {
      return false;
    }
    return Objects.equals(name, user.name);
  }

  @Override
  public int hashCode() {

    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {

    return "User{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", name='" + name + '\'' +
        '}';
  }

  public Long getId() {

    return id;
  }

  public void setId(final Long id) {

    this.id = id;
  }

  public String getEmail() {

    return email;
  }

  public void setEmail(final String email) {

    this.email = email;
  }

  public String getName() {

    return name;
  }

  public void setName(final String name) {

    this.name = name;
  }
}
