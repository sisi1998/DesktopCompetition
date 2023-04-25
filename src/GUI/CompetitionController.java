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
import javafx.event.ActionEvent;
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
import GUI.MyListener;
import javafx.scene.input.MouseEvent;

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
 
//  

    

    /**
     * Initializes the controller class.
     */
    @FXML
    private void click(MouseEvent mouseEvent) {
        System.out.println(competition);
          myListener.onClickListener(competition);
            System.out.println(myListener);
          
    }
    
    
  
     private Competition competition ;
 private MyListener myListener;
    
   public void setData(Competition competition, MyListener myListener) {
    this.competition = competition;
    this.myListener = myListener;
    String destDir = "file:///C:/xampp/htdocs/img/";
    String imagePath = competition.getImage();
    if (imagePath != null) {
        try {
            Image image = new Image(destDir+imagePath);
            if (image.isError()) {
                System.err.println("Error loading image from URL: " + imagePath);
            }
            // Update the image property of the reusable ImageView
            imageC.setImage(image);
            imageC.setFitWidth(120);
imageC.setFitHeight(150);

        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
    }
    nomC.setText(competition.getNom());
    dateC.setText(competition.getDate());
    box.setStyle("-fx-background-color: #DCDCDC ");
}

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ;
    }    

    
    
    
    

   
    }
    

