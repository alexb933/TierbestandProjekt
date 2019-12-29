package de.alexanderbeck.TierbestandProjekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

//import com.baeldung.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.util.stream.Collectors;

import java.time.LocalDate;

@SpringBootApplication
@Controller
public class SearchController {
	
	@Autowired
	private BestandRepository repository;
		  
    @FXML
    private TextField geburtenTxt;
    @FXML
    private Button geburtenBtn;
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
    private void initialize() {
       
        //lade die letzten Bestände und zeige diese auf der GUI an
    	
    	Bestand bestand1 = repository.findFirstByNutzartcodeOrderByZeitstampDesc(31);
    	int bestandAb30 = bestand1.getBestandab30();
    	int bestandu30 = bestand1.getBestandu30();
    	summeU30.setText(Integer.toString(bestandu30));
    	summeAb30.setText(Integer.toString(bestandAb30));
    	
    	
    	meldedatumPicker.setValue(LocalDate.now());
        geburtenBtn.setOnAction(event -> geburten());
        uebergangBtn.setOnAction(event -> uebergang());
        
        
        //searchButton.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
    }
    @FXML
    public void geburten() {
    	
    	
    	Bestand bestand1 = repository.findFirstByNutzartcodeOrderByZeitstampDesc(31);
    	int bestandAb30 = bestand1.getBestandab30();
    	int bestandu30 = bestand1.getBestandu30();
    	
    	LocalDate meldedatum = meldedatumPicker.getValue();
    	int anzahl = Integer.parseInt(geburtenTxt.getText());
    	bestandu30 = bestandu30 + anzahl;
    	repository.save(new Bestand( "bis30kg", "BZU", anzahl, 192, "Geburt",meldedatum, bestandu30,bestandAb30));
    	

    	summeU30.setText(Integer.toString(bestandu30));
    	summeAb30.setText(Integer.toString(bestandAb30));
    	meldedatumPicker.setValue(LocalDate.now());
    	geburtenTxt.setText("");
    	//System.out.println(txt);
    	//System.out.println("wuhuu");
    }
    @FXML
    public void uebergang() {
    	
    	
    	Bestand bestand1 = repository.findFirstByNutzartcodeOrderByZeitstampDesc(31);
    	int bestandAb30 = bestand1.getBestandab30();
    	int bestandu30 = bestand1.getBestandu30();
    	
    	LocalDate meldedatum = meldedatumPicker.getValue();
    	int anzahl = Integer.parseInt(uebergangTxt.getText());
    	bestandu30 = bestandu30 - anzahl;
    	bestandAb30 = bestandAb30 + anzahl;
    	repository.save(new Bestand( "bis30kg", "BAB", anzahl, 192, "Umstallen",meldedatum, bestandu30,bestandAb30));
    	repository.save(new Bestand( "ab30kg", "BZU", anzahl, 192, "Umstallen",meldedatum, bestandu30,bestandAb30));
    	
    	updaten();
//    	summeU30.setText(Integer.toString(bestandu30));
//    	summeAb30.setText(Integer.toString(bestandAb30));
    	meldedatumPicker.setValue(LocalDate.now());
    	uebergangTxt.setText("");
    	
    }
    
    @FXML
    public void updaten() {
    	
    	
    	Bestand bestand1 = repository.findFirstByNutzartcodeOrderByZeitstampDesc(31);
    	int bestandAb30 = bestand1.getBestandab30();
    	int bestandu30 = bestand1.getBestandu30();

    	summeU30.setText(Integer.toString(bestandu30));
    	summeAb30.setText(Integer.toString(bestandAb30));

    	
    }

	

}
