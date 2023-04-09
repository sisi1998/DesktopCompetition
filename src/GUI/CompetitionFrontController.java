/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Competition;
import Services.CompetitionService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Siwar
 */
public class CompetitionFrontController implements Initializable {

    @FXML
    private VBox cardLayout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 CompetitionService cp =new CompetitionService();
    List <Competition> Competitions = cp.affichage();

    for(int i=0; i< Competitions.size();i++){
     try {
         FXMLLoader fxmlloader =new FXMLLoader();
         fxmlloader.setLocation(getClass().getResource("competition.fxml"));
         AnchorPane cardbox = fxmlloader.load();
         CompetitionController competitionController =fxmlloader.getController();
         competitionController.setData(Competitions.get(i));
         cardLayout.getChildren().add(cardbox);
     }
     // TODO
     catch (IOException ex) {
         Logger.getLogger(CompetitionFrontController.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
    
    
    }    
  
   
}
