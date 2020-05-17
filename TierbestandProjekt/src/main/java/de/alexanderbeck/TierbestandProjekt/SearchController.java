package de.alexanderbeck.TierbestandProjekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;


import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    private TextArea letzterEintragTxt; 
    @FXML
    private DatePicker meldedatumPicker; 
    @FXML
    private Button loeschenBtn;
    @FXML
    private Button csvExportBtn;
    @FXML
    private Button closeBtn;
    
    
     
    @FXML
    private void initialize() {
       
        //lade die letzten Bestände und zeige diese auf der GUI an
//    	Bestand bestand1 = repository.findFirstByBnummerOrderByZeitstampDesc("08 436 095 0018");
//    	int bestandAb30 = bestand1.getBestandab30();
//    	int bestandu30 = bestand1.getBestandu30();
//    	summeU30.setText(Integer.toString(bestandu30));
//    	summeAb30.setText(Integer.toString(bestandAb30));
    	updaten();
    	
    	
    	
    	meldedatumPicker.setValue(LocalDate.now());
        geburtenBtn.setOnAction(event -> geburten());
        uebergangBtn.setOnAction(event -> uebergang());
        csvExportBtn.setOnAction(event -> csvExportieren());
        toteU30Btn.setOnAction(event -> toteU30());
        toteAb30Btn.setOnAction(event -> toteAb30());
        verkaufBtn.setOnAction(event -> verkauf());
        loeschenBtn.setOnAction(event -> loeschen());
        closeBtn.setOnAction(EventHandler -> Platform.exit());
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
    public void toteAb30() {
    	
    	Bestand bestand1 = repository.findFirstByBnummerOrderByZeitstampDesc("08 436 095 0018");
    	int bestandAb30 = bestand1.getBestandab30();
    	int bestandu30 = bestand1.getBestandu30();
    		
    	LocalDate meldedatum = meldedatumPicker.getValue();
    	String halbjahr = ermittleHalbjahr(meldedatum);
    	
    	int anzahl = Integer.parseInt(toteAb30Txt.getText());
    	bestandAb30 = bestandAb30 - anzahl;
    	repository.save(new Bestand( "ab30kg", "BAB", anzahl, halbjahr, "Tod/Verendung",meldedatum, bestandu30,bestandAb30));
    	
    	updaten();
    	meldedatumPicker.setValue(LocalDate.now());
    	toteAb30Txt.setText("");
    }
    @FXML
    public void verkauf() {
    	
    	Bestand bestand1 = repository.findFirstByBnummerOrderByZeitstampDesc("08 436 095 0018");
    	int bestandAb30 = bestand1.getBestandab30();
    	int bestandu30 = bestand1.getBestandu30();
    		
    	LocalDate meldedatum = meldedatumPicker.getValue();
    	String halbjahr = ermittleHalbjahr(meldedatum);
    	
    	int anzahl = Integer.parseInt(verkaufTxt.getText());
    	bestandAb30 = bestandAb30 - anzahl;
    	repository.save(new Bestand( "ab30kg", "BAB", anzahl, halbjahr, "Verkauf",meldedatum, bestandu30,bestandAb30));
    	
    	updaten();
    	meldedatumPicker.setValue(LocalDate.now());
    	verkaufTxt.setText("");
    }
    @FXML
    public void loeschen() {
    	
    	Bestand bestand1 = repository.findFirstByBnummerOrderByZeitstampDesc("08 436 095 0018");

    	repository.deleteById(bestand1.getId());
    	showAlert("Der zuletzt gespeicherte Datensatz wurde gelöscht. Angezeigt wird nun der neue letzte Eintrag (zweitletzter)");
    	updaten();

    }
    
    @FXML
    public void updaten() {
    	
    	Bestand bestand1 = repository.findFirstByBnummerOrderByZeitstampDesc("08 436 095 0018");
    	int bestandAb30 = bestand1.getBestandab30();
    	int bestandu30 = bestand1.getBestandu30();

    	summeU30.setText(Integer.toString(bestandu30));
    	summeAb30.setText(Integer.toString(bestandAb30));
    	letzterEintragTxt.setText(bestand1.toString());
	    }

    @FXML
    public void csvExportieren() {
    	List<String[]> dataLines = new ArrayList<>();
    	String pfad = System.getProperty("user.home") + "/Desktop";
    	File csvOutputFile = new File(pfad + "/Exportdatei.txt");
    	
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
        showAlert("Die CSV Datei wurde erfolgreich auf dem Desktop gespeichert");
        
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
    private void showAlert(String nachricht) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Hinweis");
        alert.setHeaderText(null);
        alert.setContentText(nachricht);
        alert.showAndWait();
    }
}
