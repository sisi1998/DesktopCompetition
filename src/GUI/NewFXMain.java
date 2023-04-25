/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Siwar
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage)throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("FavoriteCompetition.fxml"));
        primaryStage.setTitle("GOAcademyGo");
        primaryStage.setScene(new Scene(root, 800,600));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
