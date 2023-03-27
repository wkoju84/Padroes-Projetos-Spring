package one.dio.labpadroesprojetosspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Projeto SpringBoot gerado via Spring Initializr
 *
 * Os seguintes módulos foram selecionados:
 *  - Spring Data JPA
 *  - Spring Web
 *  - H2 Database
 *  - OpenFeign
 *
 * @author wkoju84
 */

@EnableFeignClients
@SpringBootApplication
public class LabPadroesProjetosSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabPadroesProjetosSpringApplication.class, args);
	}

}
