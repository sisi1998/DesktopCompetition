/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Competition;
import Entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Siwar
 */
public class PlayerRankController implements Initializable {

    @FXML
    private ImageView imageC;
    @FXML
    private Label nomP;
    @FXML
    private Label rangP;
    @FXML
    private AnchorPane payercard;
    
    
        public void setData(User cmp, int rang){
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
       
       
        nomP.setText(cmp.getNom()+' '+cmp.getPrenom());
        rangP.setText(String.valueOf(rang));}
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
