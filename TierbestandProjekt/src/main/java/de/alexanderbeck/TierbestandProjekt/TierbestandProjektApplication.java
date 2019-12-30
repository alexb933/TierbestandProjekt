package de.alexanderbeck.TierbestandProjekt;


import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;
import javafx.application.Application;



@SpringBootApplication
public class TierbestandProjektApplication {

	
	  
	public static void main(String[] args) {
        Application.launch(ChartApplication.class, args);
	}
	
	  @Bean
	  public CommandLineRunner demo(BestandRepository repository) {
	    return (args) -> {

	    	
	    //setzen auf create und dann Ersten Eintrag schreiben:	
	    //repository.save(new Bestand( "bis30kg", "BZU", 0, "192", "Erster Eintrag",LocalDate.now(),0,0));

	      			// fetch all bestand
//	      log.info("Bestand found with findAll():");
//	      log.info("-------------------------------");
//	      for (Bestand bestand : repository.findAll()) {
//	        log.info(bestand.toString());
//	      }
//	      log.info("");




	      			// fetch an individual eintrag by ID
//	      Bestand bestand = repository.findById(1L);
//	      System.out.println(bestand.getId());
//	      log.info("Eintrag found with findById(1L):");
//	      log.info("--------------------------------");
//	      log.info(bestand.toString());
//	      log.info("");

	      			// fetch customers by hinweis
//	      log.info("Customer found with Hinweis('Test Hinweis'):");
//	      log.info("--------------------------------------------");
//	      repository.findByHinweis("Test Hinweis").forEach(hinweis -> {
//	        log.info(hinweis.toString());
//	      });
//			      // for (Customer bauer : repository.findByLastName("Bauer")) {
//			      //  log.info(bauer.toString());
//			      // }
//	      log.info("");
	    };
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
