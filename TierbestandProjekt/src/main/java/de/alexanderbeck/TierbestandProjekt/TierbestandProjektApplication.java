package de.alexanderbeck.TierbestandProjekt;

import org.aspectj.weaver.ast.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javafx.application.Application;
import java.time.LocalDate;


@SpringBootApplication
public class TierbestandProjektApplication {

	//private static final Logger log = LoggerFactory.getLogger(TierbestandProjektApplication.class);
	  
	public static void main(String[] args) {
        Application.launch(ChartApplication.class, args);
	}
	
	  @Bean
	  public CommandLineRunner demo(BestandRepository repository) {
	    return (args) -> {
	    	
//	      Bestand bestand1 = repository.findFirstByNutzartcodeOrderByZeitstampDesc(31);
//	      int bestandu30 = bestand1.getBestandu30();
//	      //int bestandu30 = 300;
//	      System.out.println(bestandu30);  
//	      Bestand bestand2 = repository.findFirstByNutzartcodeOrderByZeitstampDesc(32);
//	      int bestandab30 = bestand2.getBestandab30();
//	      //int bestandab30 = 700;
//	      System.out.println(bestandab30);  
//	      
//	      
//	      int aenderwert = -30;
//	      bestandu30 = bestandu30 + aenderwert;
	      
	      // save a few data
	      //repository.save(new Bestand( "bis30kg", "BZU", 0, 192, "Erster Eintrag",LocalDate.now(),0,0));
	      
	    	
//	      repository.save(new Bestand( "bis30kg", "BZU", 10, 192, "Test Hinweis",bestandu30,bestandab30));
//	      repository.save(new Bestand( "ab30kg", "BAB", 20, 192, "Test Hinweis2",bestandu30,bestandab30));



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
