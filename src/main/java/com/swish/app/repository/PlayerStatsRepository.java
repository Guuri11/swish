package com.swish.app.repository;

import com.swish.app.entity.PlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long> {

}
