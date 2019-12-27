package de.alexanderbeck.TierbestandProjekt;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;



import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ChartApplication extends Application {
    private ConfigurableApplicationContext applicationContext;
    private Parent rootNode;

    @Override
    public void init() throws Exception {
        applicationContext = new SpringApplicationBuilder(TierbestandProjektApplication.class).run();
    	
        //SpringApplicationBuilder builder = new SpringApplicationBuilder(ChartApplication.class);
        //applicationContext = builder.run(getParameters().getRaw().toArray(new String[0]));
        		
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        rootNode = loader.load();
    }
 
    @Override
    public void start(Stage stage) throws Exception {
        //applicationContext.publishEvent(new StageReadyEvent(stage));
        
    	Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        double width = visualBounds.getWidth();
//        double height = visualBounds.getHeight();
        
        stage.setTitle("Tierbestands Meldeprogramm");
        stage.setScene(new Scene(rootNode, width, 300));
        stage.centerOnScreen();
        stage.show();
    }
 
    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
 
    static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }
        public Stage getStage() {
            return ((Stage) getSource());
        }
    }
}