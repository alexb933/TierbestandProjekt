package de.alexanderbeck.TierbestandProjekt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

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


public class SearchController {
		  
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
     
    @FXML
    private void initialize() {
        // search panel
        //searchButton.setText("Search");
        searchButton.setOnAction(event -> test());
        //searchButton.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
    }

    private void test() {
    	//repository.save(new Bestand( "bis30kg", "BZU", 10, 192, "Test Hinweis",300,200));
    	System.out.println("Juhuu");
    }
	

}
