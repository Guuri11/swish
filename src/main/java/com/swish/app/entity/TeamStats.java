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
@Table(name = "team_stats")
public class TeamStats {

  @Id
  @GeneratedValue()
  private Long id;

  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;

  @ManyToOne
  @JoinColumn(name = "game_id")
  private Game game;

  private Integer points;
  private Integer rebounds;
  private Integer assists;

}
