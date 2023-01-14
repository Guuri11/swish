package com.swish.app.db;

import com.swish.app.entity.Player;
import com.swish.app.entity.PlayerStatus;
import com.swish.app.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(final PlayerRepository repository) {

    return args -> {
      log.info(
          "Preloading" + repository.save(new Player("Lebron James", "SF", (byte) 23, (byte) 38, PlayerStatus.HEALTHY)));
      log.info(
          "Preloading" + repository.save(new Player("Kevin Durant", "SF", (byte) 7, (byte) 34, PlayerStatus.HEALTHY)));
    };

  }
}
