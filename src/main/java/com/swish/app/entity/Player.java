package com.swish.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player")
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


}
