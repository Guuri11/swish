package com.swish.app.infrastructure.persistence;

import com.swish.app.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}
