package de.alexanderbeck.TierbestandProjekt;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Bestand {
	
	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Long id;
	  private int anzahl;
	  private String hinweis;

	  protected Bestand() {}

	  public Bestand(Integer anzahl, String hinweis) {
	    this.anzahl = anzahl;
	    this.hinweis = hinweis;
	  }

	  @Override
	  public String toString() {
	    return String.format(
	        "Bestand[id=%d, anzahl='%d', hinweis='%s']",
	        id, anzahl, hinweis);
	  }

	  public Long getId() {
	    return id;
	  }

	  public int getAnzahl() {
	    return anzahl;
	  }

	  public String getHinweis() {
	    return hinweis;
	  }

}
