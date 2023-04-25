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
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Siwar
 */
public class SortedPlayersController implements Initializable {

    @FXML
    private GridPane compGrid;
    @FXML
    private Button perfButton;
    @FXML
    private ImageView imgY;
    @FXML
    private Label favoredN;
    @FXML
    private Button Goback;
    @FXML
    private Button compB;

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
         Controller.setData(players.get(i),i+1);
        if(comuns==3){
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

    @FXML
    private void Toperformance(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/PerformanceFront.fxml"));
    Parent root = loader.load();
   PerformanceFrontController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }

    @FXML
    private void imgTOyou(MouseEvent event) throws URISyntaxException, IOException {
         URI youtubeLink = new URI("https://www.youtube.com/watch?v=MnBpFAo5FrI&ab_channel=Ligue1UberEats");
    Desktop.getDesktop().browse(youtubeLink);
    }

    @FXML
    private void ToComp(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CompetitionFront.fxml"));
    Parent root = loader.load();
   CompetitionFrontController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }

    @FXML
    private void GobackF(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/PerformanceFront.fxml"));
    Parent root = loader.load();
   PerformanceFrontController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }
    
}
