package de.alexanderbeck.TierbestandProjekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.stereotype.Controller;


import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import java.io.File;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@Controller
public class SearchController {
	
	@Autowired
	private BestandRepository repository;
		  
    @FXML
    private TextField geburtenTxt;
    @FXML
    private TextField toteU30Txt;
    @FXML
    private TextField toteAb30Txt;
    @FXML
    private TextField verkaufTxt;
    @FXML
    private Button geburtenBtn;
    @FXML
    private Button toteU30Btn;
    @FXML
    private Button toteAb30Btn;
    @FXML
    private Button verkaufBtn;
    @FXML
    private TextField uebergangTxt;
    @FXML
    private Button uebergangBtn;
    @FXML
    private TextArea summeU30;
    @FXML
    private TextArea summeAb30; 
    @FXML
    private DatePicker meldedatumPicker; 
    @FXML
    private Button csvExportBtn;
    
    
     
    @FXML
    private void initialize() {
       
        //lade die letzten Bestände und zeige diese auf der GUI an
    	
    	Bestand bestand1 = repository.findFirstByBnummerOrderByZeitstampDesc("08 436 095 0018");
    	int bestandAb30 = bestand1.getBestandab30();
    	int bestandu30 = bestand1.getBestandu30();
    	summeU30.setText(Integer.toString(bestandu30));
    	summeAb30.setText(Integer.toString(bestandAb30));
    	
    	
    	meldedatumPicker.setValue(LocalDate.now());
        geburtenBtn.setOnAction(event -> geburten());
        uebergangBtn.setOnAction(event -> uebergang());
        csvExportBtn.setOnAction(event -> csvExportieren());
        
        //searchButton.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
    }
    @FXML
    public void geburten() {
    	
    	Bestand bestand1 = repository.findFirstByBnummerOrderByZeitstampDesc("08 436 095 0018");
    	int bestandAb30 = bestand1.getBestandab30();
    	int bestandu30 = bestand1.getBestandu30();
    		
    	LocalDate meldedatum = meldedatumPicker.getValue();
    	String halbjahr = ermittleHalbjahr(meldedatum);
    	
    	int anzahl = Integer.parseInt(geburtenTxt.getText());
    	bestandu30 = bestandu30 + anzahl;
    	repository.save(new Bestand( "bis30kg", "BZU", anzahl, halbjahr, "Geburt",meldedatum, bestandu30,bestandAb30));
    	
    	updaten();
    	meldedatumPicker.setValue(LocalDate.now());
    	geburtenTxt.setText("");
    }
    @FXML
    public void toteU30() {
    	
    	Bestand bestand1 = repository.findFirstByBnummerOrderByZeitstampDesc("08 436 095 0018");
    	int bestandAb30 = bestand1.getBestandab30();
    	int bestandu30 = bestand1.getBestandu30();
    		
    	LocalDate meldedatum = meldedatumPicker.getValue();
    	String halbjahr = ermittleHalbjahr(meldedatum);
    	
    	int anzahl = Integer.parseInt(toteU30Txt.getText());
    	bestandu30 = bestandu30 - anzahl;
    	repository.save(new Bestand( "bis30kg", "BAB", anzahl, halbjahr, "Tod/Verendung",meldedatum, bestandu30,bestandAb30));
    	
    	updaten();
    	meldedatumPicker.setValue(LocalDate.now());
    	toteU30Txt.setText("");
    }
    

	@FXML
    public void uebergang() {
    	
    	Bestand bestand1 = repository.findFirstByBnummerOrderByZeitstampDesc("08 436 095 0018");
    	int bestandAb30 = bestand1.getBestandab30();
    	int bestandu30 = bestand1.getBestandu30();
    	
    	LocalDate meldedatum = meldedatumPicker.getValue();
    	String halbjahr = ermittleHalbjahr(meldedatum);
    	
    	int anzahl = Integer.parseInt(uebergangTxt.getText());
    	bestandu30 = bestandu30 - anzahl;
    	bestandAb30 = bestandAb30 + anzahl;
    	repository.save(new Bestand( "bis30kg", "BAB", anzahl, halbjahr, "Umstallen",meldedatum, bestandu30,bestandAb30));
    	repository.save(new Bestand( "ab30kg", "BZU", anzahl, halbjahr, "Umstallen",meldedatum, bestandu30,bestandAb30));
    	
    	updaten();

    	meldedatumPicker.setValue(LocalDate.now());
    	uebergangTxt.setText("");
    }
    
    @FXML
    public void updaten() {
    	
    	
    	Bestand bestand1 = repository.findFirstByBnummerOrderByZeitstampDesc("08 436 095 0018");
    	int bestandAb30 = bestand1.getBestandab30();
    	int bestandu30 = bestand1.getBestandu30();

    	summeU30.setText(Integer.toString(bestandu30));
    	summeAb30.setText(Integer.toString(bestandAb30));
    	

	    }

    @FXML
    public void csvExportieren() {
    	List<String[]> dataLines = new ArrayList<>();
    	File csvOutputFile = new File("Exportdatei.txt");
    	
    	dataLines.add(new String[] 
  			  {"BNR15","TAMV_DAT","TAMB_FORM","TAMV_ART","TAMV_ANZ","TAM_PERIOD"});
    	
	    for (Bestand bestand : repository.findAll()) {
	    	  //System.out.println(bestand.toString());
	    	LocalDate meldedatum = bestand.getMeldedatum();
	    	String formattedMeldedatum = meldedatum.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	  
	    	dataLines.add(new String[] 
	    			  {bestand.getBNummer(),formattedMeldedatum, Integer.toString(bestand.getNutzartCode()), bestand.getAenderartCode(),Integer.toString(bestand.getAnzahl()), bestand.getHalbjahr() });
	    }
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
              .map(this::convertToCSV)
              .forEach(pw::println);
        } catch (Exception e) {
            // Handle it.
        	//assertTrue(csvOutputFile.exists());
        	System.out.println("Fehler bei der CSV Erstellung");
        }
        
    }
    
    public String convertToCSV(String[] data) {
        return Stream.of(data)
            .map(this::escapeSpecialCharacters)
            .collect(Collectors.joining(";"));
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
        private String ermittleHalbjahr(LocalDate meldedatum) {
        	int monat = Integer.valueOf(meldedatum.format(DateTimeFormatter.ofPattern("MM")));
        	String jahr = meldedatum.format(DateTimeFormatter.ofPattern("yy"));
        	String halbjahr;
    	    if (monat <= 6 && monat >=1) {
    	    	halbjahr = "1";
    	    }  else {
    	    	halbjahr = "2";
    	    }
        	halbjahr = jahr+halbjahr;
		return halbjahr;
	}
}
