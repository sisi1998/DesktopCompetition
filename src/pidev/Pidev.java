/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Competition;
import Entities.Equipe;
import Entities.PerformanceC;
import Entities.User;
import Services.CompetitionService;
import Services.PerformanceCService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
public class Pidev extends Application {
    
    @Override
    public void start(Stage primaryStage)  throws IOException {
     //Parent p = FXMLLoader.load(getClass().getResource("/GUI/AdminAddCompetition.fxml"));
      Parent root= FXMLLoader.load(getClass().getResource("/GUI/AdminListCompetition.fxml"));
        primaryStage.setTitle("Hello");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
     
     
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }}
