/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Competition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

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
        // TODO
    }    
    
}
