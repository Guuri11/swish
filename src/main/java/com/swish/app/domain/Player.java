package com.swish.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
