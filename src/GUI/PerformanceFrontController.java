/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Competition;
import Entities.PerformanceC;
import Entities.User;
import Services.PerformanceCService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Siwar
 */
public class PerformanceFrontController implements Initializable {

    @FXML
    private TableColumn<PerformanceC, User> joueur;
    @FXML
    private TableColumn<PerformanceC, Competition>  competition;
    @FXML
    private TableColumn<PerformanceC, String>  apps;
    @FXML
    private TableColumn<PerformanceC, String>  mins;
    @FXML
    private TableColumn<PerformanceC, String> buts;
    @FXML
    private TableColumn<PerformanceC, String>  pd;
    @FXML
    private TableColumn<PerformanceC, String>  jaune;
    @FXML
    private TableColumn<PerformanceC, String>  rouge;
    @FXML
    private TableColumn<PerformanceC, String>  tpm;
    @FXML
    private TableColumn<PerformanceC, String> pr;
    @FXML
    private TableColumn<PerformanceC, String>  ag;
    @FXML
    private TableColumn<PerformanceC, String>  hdm;
    @FXML
    private TableColumn<PerformanceC, String>  note;
 PerformanceCService ps = new PerformanceCService();
    @FXML
    private TableView<PerformanceC> tableview;
    @FXML
    private Button compB;
    @FXML
    private Button ranked;
    @FXML
    private Button pdf;
    @FXML
    private Button perfButton1;
   
   
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ObservableList<PerformanceC> list = FXCollections.observableArrayList();
        for (PerformanceC p : ps.affichage()) {
            list.add(p);
            System.out.println();
        }
         joueur.setCellValueFactory(new PropertyValueFactory<>("idjoueur"));
        competition.setCellValueFactory(new PropertyValueFactory<>("idcom"));
       apps.setCellValueFactory(new PropertyValueFactory<>("apps"));
      mins.setCellValueFactory(new PropertyValueFactory<>("mins"));
        buts.setCellValueFactory(new PropertyValueFactory<>("buts"));
       pd.setCellValueFactory(new PropertyValueFactory<>("pointsDecisives"));
       
        jaune.setCellValueFactory(new PropertyValueFactory<>("jaune"));
        rouge.setCellValueFactory(new PropertyValueFactory<>("rouge"));
        tpm.setCellValueFactory(new PropertyValueFactory<>("tpM"));
        pr.setCellValueFactory(new PropertyValueFactory<>("pr"));
        ag.setCellValueFactory(new PropertyValueFactory<>("aerienG"));
        hdm.setCellValueFactory(new PropertyValueFactory<>("hdM"));
        note.setCellValueFactory(new PropertyValueFactory<>("note"));

        // Load the data from the PerformanceCService into the TableView
        tableview.setItems(list);
       
  


        
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
    private void torank(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/SortedPlayers.fxml"));
    Parent root = loader.load();
   SortedPlayersController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }
@FXML
private void PDFGen(ActionEvent event) {
    System.out.println("PDFGen button clicked");
    FileChooser fileChooser = new FileChooser();

    // Définir l'extension de fichier par défaut
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
    fileChooser.getExtensionFilters().add(extFilter);

    // Afficher la boîte de dialogue pour enregistrer le fichier
    File file = fileChooser.showSaveDialog(null);

    if (file != null) {
        System.out.println("Selected file: " + file.getAbsolutePath());
        PDFGenerator pdfGenerator = new PDFGenerator();
        pdfGenerator.generatePDF(ps.affichage(), file.getAbsolutePath());
    }
}

    @FXML
    private void Toperformance(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/PerformanceFront.fxml"));
    Parent root = loader.load();
   ListPerformanceController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }

    
    
}
