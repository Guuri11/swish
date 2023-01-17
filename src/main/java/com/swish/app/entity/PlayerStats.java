package com.swish.app.entity;

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
@Table(name = "player_stats")
public class PlayerStats {

  @Id
  @GeneratedValue()
  private Long id;

  @ManyToOne
  @JoinColumn(name = "player_id")
  private Player player;

  @ManyToOne
  @JoinColumn(name = "game_id")
  private Game game;

  private Integer points;
  private Integer rebounds;
  private Integer assists;

}
