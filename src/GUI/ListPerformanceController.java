/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Competition;
import Entities.PerformanceC;
import Entities.User;
import Services.CompetitionService;
import Services.PerformanceCService;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Siwar
 */
public class ListPerformanceController implements Initializable {

    @FXML
    private TableColumn<PerformanceC, User> joueur;
    @FXML
    private TableColumn<PerformanceC, Competition>  competition;
    @FXML
    private TableColumn<PerformanceC, String>  apps;
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

    /**
     * Initializes the controller class.
     */
    
     PerformanceCService ps = new PerformanceCService();
    @FXML
    private TableView<PerformanceC> tableview;
    @FXML
    private Button CompButton;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

    private void supprimer(ActionEvent event) {
          

    PerformanceC selectedPer = tableview.getSelectionModel().getSelectedItem();
    if (selectedPer == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Please select a Performance to delete.");
        alert.showAndWait();
        return;
    }

    int id = selectedPer.getId();
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to delete the competition with ID " + id + "?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        PerformanceCService cs = new  PerformanceCService();
        cs.Delete(id);
        tableview.getItems().remove(selectedPer);
    }


    
    }

    @FXML
    private void ToComp(ActionEvent event) {
    }
}