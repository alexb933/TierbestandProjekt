package de.alexanderbeck.TierbestandProjekt;

import java.time.LocalDateTime;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bestand {
	
	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Long id;
	  private String bnummer;
	  private LocalDateTime zeitstamp;
	  private int nutzartcode;
	  private String nutzarttxt;
	  private String aenderartcode;
	  private String aenderarttxt;
	  private int anzahl;
	  private int halbjahr;
	  private String hinweis;
	  private int bestandu30;
	  private int bestandab30;
	  private LocalDate meldedatum;

	  protected Bestand() {}

	  public Bestand( String nutzarttxt, String aenderartcode, Integer anzahl, Integer halbjahr, String hinweis, LocalDate meldedatum, Integer bestandu30, Integer bestandab30) {
	    
		this.bnummer ="08 436 095 0018"; //konstant
	    
		this.nutzarttxt = nutzarttxt;
	    if (nutzarttxt == "bis30kg") {
	    	this.nutzartcode = 31;
	    } else if (nutzarttxt == "ab30kg"){
	    	this.nutzartcode = 32;
	    } else {
	    	this.nutzartcode = 0;
	    	System.out.println("Error: Undefinierbare Nutzungsart");
	    }
	    
	    this.aenderartcode = aenderartcode;
	    if (aenderartcode == "BZU") {
	    	this.aenderarttxt = "Bestandszugang";
	    } else if (aenderartcode == "BAB") {
	    	this.aenderarttxt = "Bestandsabgang";
	    } else {
	    	this.aenderarttxt = "Error";
	    	System.out.println("Error: Undefinierbare Änderungsart");
	    }
	    
	    this.anzahl = anzahl;
	    this.halbjahr = halbjahr;
	    this.hinweis = hinweis;
	    this.zeitstamp = LocalDateTime.now();
	    this.meldedatum = meldedatum;
	    this.bestandu30 = bestandu30;
	    this.bestandab30 = bestandab30;
	    
	    
	  }

	  @Override
	  public String toString() {
	    return String.format(
	        "Bestand[id=%d, bnummer='%s', Zeit='%s', nutzartcode='%d', nutzarttxt='%s', aenderartcode='%s', aenderarttxt='%s', anzahl='%d', halbjahr='%d', hinweis='%s']",
	        id, bnummer, zeitstamp, nutzartcode, nutzarttxt, aenderartcode, aenderarttxt, anzahl, halbjahr, hinweis);
	  }

	  public Long getId() {
	    return id;
	  }
	  public String getBNummer() {
		    return bnummer;
		  }
	  public LocalDateTime getZeitStamp() {
		  return zeitstamp;
		  }
	  public int getNutzartCode() {
		    return nutzartcode;
		  }
	  public String getNutzartTxt() {
		    return nutzarttxt;
		  }
	  public String getAenderartCode() {
		    return aenderartcode;
		  }
	  public String getAenderartTxt() {
		    return aenderarttxt;
		  }
	  public int getAnzahl() {
	    return anzahl;
	  }
	  public int getHalbjahr() {
		    return halbjahr;
		  }
	  public String getHinweis() {
	    return hinweis;
	  }
	  public int getBestandu30() {
		    return bestandu30;
		  }
	  public int getBestandab30() {
		    return bestandab30;
		  }
	  public LocalDate getMeldedatum() {
		    return meldedatum;
		  }

}
