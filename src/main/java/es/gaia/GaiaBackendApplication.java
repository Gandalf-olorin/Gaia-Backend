package es.gaia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import es.libreria.gaia.config.GaiaSecurityConfig;

@SpringBootApplication
@Import(GaiaSecurityConfig.class)
public class GaiaBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(GaiaBackendApplication.class, args);
  }

}
