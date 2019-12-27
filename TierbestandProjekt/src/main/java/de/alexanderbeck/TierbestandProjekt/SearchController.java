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
    private TextArea summe;    
    
    
     
    @FXML
    private void initialize() {
        // search panel
        //searchButton.setText("Search");
    	
        geburtenBtn.setOnAction(event -> geburten());
        
        //searchButton.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
    }
    @FXML
    public void geburten() {
    	int anzahl = Integer.parseInt(geburtenTxt.getText());
    	
    	repository.save(new Bestand( "bis30kg", "BZU", anzahl, 192, "blabla",300,200));
    	
	    Bestand bestand1 = repository.findFirstByNutzartcodeOrderByZeitstampDesc(31);
	    int bestandu30 = bestand1.getBestandu30();
    	
    	summe.setText(Integer.toString(bestandu30));
    	//System.out.println(txt);
    	//System.out.println("wuhuu");
    }
	

}
