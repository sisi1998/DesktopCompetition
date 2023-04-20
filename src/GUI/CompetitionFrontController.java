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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Siwar
 */
public class CompetitionFrontController implements Initializable {

    private VBox cardLayout;
    @FXML
    private GridPane compGrid;
    @FXML
    private Button perfButton;
    public static Competition competition ;
    @FXML
    private Label CHcomp;
    @FXML
    private Label chDate;
    @FXML
    private Label CHare;
    @FXML
    private ImageView CHcodeqr;
     private MyListener myListener;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 CompetitionService cp =new CompetitionService();
    List <Competition> Competitions = cp.affichage();
    
     if (Competitions.size() > 0) {
            setChosenCompetition(Competitions.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Competition cmp) {
                    setChosenCompetition (cmp);
                }
            };}
    
    
    
    
int comuns =0;
int row=1;
   for(int i=0; i< Competitions.size();i++){
     try {
         FXMLLoader fxmlloader =new FXMLLoader();
         fxmlloader.setLocation(getClass().getResource("competition.fxml"));
         AnchorPane cardbox = fxmlloader.load();
         CompetitionController competitionController =fxmlloader.getController();
         competitionController.setData(Competitions.get(i));
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

    @FXML
    private void Toperformance(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/PerformanceFront.fxml"));
    Parent root = loader.load();
   PerformanceFrontController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }
    
    
      
    private void setChosenCompetition(Competition cmp) {
        CHcomp.setText(cmp.getNom());
         CHare.setText(cmp.getArena().getNom());
        chDate.setText(cmp.getDate());
       String destDir = "file:///C:/xampp/htdocs/img/";
        String imagePath = cmp.getCodeqr();
     if (imagePath != null) {
        try {
            Image image = new Image(destDir+imagePath);
            if (image.isError()) {
                System.err.println("Error loading image from URL: " + imagePath);
            
            }
            // Update the image property of the reusable ImageView
            CHcodeqr.setImage(image);
           
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
          
        }
    }
        

    }
   
}
