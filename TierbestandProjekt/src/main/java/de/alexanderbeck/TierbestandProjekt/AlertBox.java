package de.alexanderbeck.TierbestandProjekt;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.EventHandler;
import javafx.geometry.*;


public class AlertBox {

	public static void display(String titel, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(titel);
		window.setMinWidth(250);
		
		Label label = new Label();
		label.setText(message);
		Button closeButton = new Button("Close it");
		closeButton.setOnAction(e -> window.close());
		
		VBox layoutBox = new VBox();
		layoutBox.getChildren().addAll(label, closeButton);
		layoutBox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layoutBox);
		window.setScene(scene);
		window.showAndWait();
	}

}
