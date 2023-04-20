/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Competition;
import Entities.User;
import Services.CompetitionService;
import Services.PerformanceCService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Siwar
 */
public class SortedPlayersController implements Initializable {

    @FXML
    private GridPane compGrid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    PerformanceCService cp =new PerformanceCService();
    List <User>players = cp.rankPlayersByScore();
    int comuns =0;
int row=1;
   for(int i=0; i< players.size();i++){
     try {
         FXMLLoader fxmlloader =new FXMLLoader();
         fxmlloader.setLocation(getClass().getResource("PlayerRank.fxml"));
         AnchorPane cardbox = fxmlloader.load();
         PlayerRankController Controller =fxmlloader.getController();
         Controller.setData(players.get(i),i);
        if(comuns==2){
            comuns=0;
        ++row;
        }
     compGrid.add(cardbox,comuns++, row);
     GridPane.setMargin(cardbox, new Insets (10));
     
     
     
     }
     // TODO
     catch (IOException ex) {
         Logger.getLogger(CompetitionFrontController.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
 
    }    
    
}
