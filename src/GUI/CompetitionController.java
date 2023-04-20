/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Competition;
import Entities.PerformanceC;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Siwar
 */
public class CompetitionController implements Initializable {

    @FXML
    private Label nomC;
    @FXML
    private Label dateC;
    @FXML
    private ImageView imageC;
    @FXML
    private VBox box;
    private String color="";
    public static Competition competition ;
    

    /**
     * Initializes the controller class.
     */
    public void setData(Competition cmp){
        String destDir = "file:///C:/xampp/htdocs/img/";
        String imagePath = cmp.getImage();
     if (imagePath != null) {
        try {
            Image image = new Image(destDir+imagePath);
            if (image.isError()) {
                System.err.println("Error loading image from URL: " + imagePath);
            
            }
            // Update the image property of the reusable ImageView
            imageC.setImage(image);
           
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
          
        }
    }
       
       
        nomC.setText(cmp.getNom());
        dateC.setText(cmp.getDate());
        
        box.setStyle("-fx-background-color: #DCDCDC ");
    
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           imageC.setOnMouseClicked(this:: Godetails);
    }    
    
    
    public Competition getData() {
    Competition cmp = new Competition();
    cmp.setNom(nomC.getText());
    cmp.setDate(dateC.getText());
  //  cmp.setImage(imageC.getImage().getUrl());
    // set any other fields you may have displayed in the view
    return cmp;
}


    private void Godetails(MouseEvent event) {
//          try {
//            // Load the new window with the detailed information of the competition
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("CompetitonDetail.fxml"));
//            Parent root = loader.load();
//            CompetitonDetailController controller = loader.getController();
//             controller.setId(competition.getId());
//                            controller.setNom(competition.getNom());
//                            controller.setArena(competition.getArena());
//                            controller.setStatus(competition.getEtat());
//                            controller.setEquipeList(competition.getEquipes());
//                            controller.setDateAndTime(competition.getDate());
//                            controller.setWinner(competition.getIdwinner());
//                            controller.setImage(competition.getImage());
//                            System.out.println(competition.getNom()+"test2");
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    }
    

