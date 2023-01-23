package com.swish.app.domain;

import java.time.LocalDateTime;
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
@Table(name = "game")
public class Game {

  private @Id
  @GeneratedValue Long id;
  @ManyToOne
  @JoinColumn(name = "local_id")
  private Team local;
  @ManyToOne
  @JoinColumn(name = "away_id")
  private Team away;
  private Integer localScore;
  private Integer awayScore;
  private LocalDateTime scheduleDate;
  private GameStatus gameStatus;


}
