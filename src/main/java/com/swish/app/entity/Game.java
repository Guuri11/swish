package com.swish.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
  private Integer local_score;
  private Integer away_score;
  private LocalDateTime scheduleDate;


}
