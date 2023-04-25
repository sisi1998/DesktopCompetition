/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Arena;
import Entities.Competition;
import Entities.Equipe;
import Services.CompetitionService;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Siwar
 */
public class CompetitonDetailController implements Initializable {

    @FXML
    private Button compB;
    @FXML
    private ListView<Equipe> equipelist;
    @FXML
    private ImageView imageV;
    @FXML
    private ImageView Vcodeqr;
    @FXML
    private TextField statusC;
    @FXML
    private TextField arenaC;
    @FXML
    private TextField dateC;
    @FXML
    private TextField nomC;
    @FXML
    private TextField winnerC;
       private int id;
     private Competition competition;
     private MyListener mylistener;
     
    String filePath="";
    @FXML
    private Label favoredN;
    @FXML
    private ImageView imgY;
    @FXML
    private Button back;
    @FXML
    private Button perfB;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

          FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/GUI/CompetitionFront.fxml"));
            Stage prStage = new Stage();

            Parent root;
        
        try {
                root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                
              
               // CompetitionService sp = new CompetitionService();
 // CompetitionController  irc = loader.getController();
              id =CompetitionFrontController.selectedCompetition.getId();
                 System.out.println(id+"ttest333");
                 

            } catch (IOException ex) {
            }
    }    

    
    
    
   public void setNom(String nom) {
    Platform.runLater(() -> nomC.setText(nom));
}

public void setArena(Arena arena) {
    Platform.runLater(() -> arenaC.setText(arena.getNom()));
}

public void setStatus(String status) {
    Platform.runLater(() -> statusC.setText(status));
}

public void setEquipeList(List<Equipe> equipeList) {
    Platform.runLater(() -> {
  
        equipelist.getItems().addAll(equipeList);
    });
}

public void setDateAndTime(String dateAndTime) {
    Platform.runLater(() -> dateC.setText( dateAndTime));
}


public void setWinner(Equipe eq) {
    Platform.runLater(() -> winnerC.setText(eq.getNom()));
    System.out.println(eq);
}
public void setfavored(Equipe eq) {
    Platform.runLater(() -> favoredN.setText(eq.getNom()));
    System.out.println(eq);
}


// String destDir = "file:///C:/xampp/htdocs/img/";
//    String imagePath = competition.getImage();
//    if (imagePath != null) {
//        try {
//            Image image = new Image(destDir+imagePath);
//            if (image.isError()) {
//                System.err.println("Error loading image from URL: " + imagePath);
//            }
//            // Update the image property of the reusable ImageView
//            imageC.setImage(image);
//        } catch (Exception e) {
//            System.err.println("Error loading image: " + e.getMessage());
//        }
//    }

public void setImage(String img) {
     String destDir = "file:///C:/xampp/htdocs/img/";
     String imagePath = img;
     if (imagePath != null) {
    Platform.runLater(() -> {
         Image image = new Image(destDir+imagePath);
          if (image.isError()) {
                System.err.println("Error loading image from URL: " + imagePath);
            }
            // Update the image property of the reusable ImageView
        imageV.setImage(image);
    });
    
}}
public void setCodeqr(String codeqr) {
    Platform.runLater(() -> {
        filePath = codeqr;
        File file = new File(codeqr);
        Image image = new Image(file.toURI().toString());
        Vcodeqr.setImage(image);
    });}

    
   int idS;
public void setId(int id) {

        this.id = id;
        System.out.println("her id " + idS);}

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
    private void GoBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CompetitionFront.fxml"));
    Parent root = loader.load();
   CompetitionFrontController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }

    private void TopeCompetiob(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CompetitionFront.fxml"));
    Parent root = loader.load();
   CompetitionFrontController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }

    @FXML
    private void TopeCompe(ActionEvent event) {
    }


}
