package com.swish.app.infrastructure.persistence;

import com.swish.app.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
