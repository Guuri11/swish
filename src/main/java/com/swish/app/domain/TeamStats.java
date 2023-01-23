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

  public boolean getWon() {

    if (this.game.getAway() == this.team) {
      return this.game.getAwayScore() > this.game.getLocalScore();
    }
    return this.game.getAwayScore() < this.game.getLocalScore();
  }
}
