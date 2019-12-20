package de.alexanderbeck.TierbestandProjekt;



import org.aspectj.weaver.ast.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TierbestandProjektApplication {

	private static final Logger log = LoggerFactory.getLogger(TierbestandProjektApplication.class);
	  
	public static void main(String[] args) {
		SpringApplication.run(TierbestandProjektApplication.class);
	}
	
	  @Bean
	  public CommandLineRunner demo(BestandRepository repository) {
	    return (args) -> {
	      // save a few data
	      repository.save(new Bestand( "bis30kg", "BZU", 10, 192, "Test Hinweis"));
	      repository.save(new Bestand( "ab30kg", "BAB", 20, 192, "Test Hinweis2"));
	      repository.save(new Bestand( "bis30serkg", "res", 10, 192, "Test Hinweis3"));


	      			// fetch all bestand
	      log.info("Bestand found with findAll():");
	      log.info("-------------------------------");
	      for (Bestand bestand : repository.findAll()) {
	        log.info(bestand.toString());
	      }
	      log.info("");

	      			// fetch an individual eintrag by ID
	      Bestand bestand = repository.findById(1L);
	      log.info("Eintrag found with findById(1L):");
	      log.info("--------------------------------");
	      log.info(bestand.toString());
	      log.info("");

	      			// fetch customers by hinweis
	      log.info("Customer found with Hinweis('Test Hinweis'):");
	      log.info("--------------------------------------------");
	      repository.findByHinweis("Test Hinweis").forEach(hinweis -> {
	        log.info(hinweis.toString());
	      });
			      // for (Customer bauer : repository.findByLastName("Bauer")) {
			      //  log.info(bauer.toString());
			      // }
	      log.info("");
	    };
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
