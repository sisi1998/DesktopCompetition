/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Arena;
import Entities.Competition;
import Entities.Equipe;
import Entities.PerformanceC;
import Entities.User;
import Services.CompetitionService;
import Services.PerformanceCService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;


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

    /**
     * Initializes the controller class.
     */
    
     PerformanceCService ps = new PerformanceCService();
    @FXML
    private TableView<PerformanceC> tableview;
    @FXML
    private Button delete;
    
    
    
    
    private TableColumn<PerformanceC, Void> colModifBtn;
    private TableColumn<PerformanceC, Void> colSuppBtn;
    private TableColumn<PerformanceC, Void> colExpBtn;
    @FXML
    private Button AddB;
    @FXML
    private Button ComB;
    @FXML
    private Button compC;
    @FXML
    private Button PerfC;
    @FXML
    private Button stats;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     colModifBtn = new TableColumn<>("Modifier");
    tableview.getColumns().add(colModifBtn);
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
        addButtonModifToTable();
       
  
}
    
     Button btn;
    
    public static PerformanceC perfs ;
    public void addButtonModifToTable(){
        Callback<TableColumn<PerformanceC, Void>, TableCell<PerformanceC, Void>> cellFactory = new Callback<TableColumn<PerformanceC, Void>, TableCell<PerformanceC, Void>>() {
            @Override
            public TableCell<PerformanceC, Void> call(final TableColumn<PerformanceC, Void> param) {

                final TableCell<PerformanceC, Void> cell = new TableCell<PerformanceC, Void>() {

                    {
                        btn = new Button("Modifier");
                        btn.setOnAction((ActionEvent event) -> {
                            try {
                              perfs = tableview.getSelectionModel().getSelectedItem();//
                         
                                
                                  
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/UpdatePerformance.fxml"));
                                Parent root = loader.load();
                                UpdatePerformanceController  controller= loader.getController();
                                System.out.println(perfs.getId()+"test");
                                System.out.println(perfs);
                            
                            
                            controller.setCom(perfs.getIdcom());
                            controller.setJou(perfs.getIdjoueur());
                            controller.setId(perfs.getId());
                            System.out.println(perfs.getId()+"jjjjj");
                            controller.setApps(perfs.getApps());
                            controller.setMins(perfs.getMins());
                            controller.setButs(perfs.getButs());
                            controller.setPd(perfs.getPointsDecisives());
                            controller.setJaune(perfs.getJaune());
                            controller.setRouge(perfs.getRouge());
                            controller.setTpm(perfs.getTpM());
                           controller.setPr(perfs.getPr());
                           controller.setAg(perfs.getAerienG());
                           controller.setHdm(perfs.getHdM());
                           controller.setNote(perfs.getNote());
                           System.out.println(perfs);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colModifBtn.setCellFactory(cellFactory);
    }

    @FXML
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
    private void addPerf(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AddPerformance.fxml"));
    Parent root = loader.load();
   AddPerformanceController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }

    @FXML
    private void GotoCom(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AdminListCompetition.fxml"));
    Parent root = loader.load();
   AdminListCompetitionController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }

    @FXML
    private void espaceCompetition(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AdminListCompetition.fxml"));
    Parent root = loader.load();
   AdminListCompetitionController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }

    @FXML
    private void EspacePerformance(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ListPerformance.fxml"));
    Parent root = loader.load();
   AdminListCompetitionController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }

    @FXML
    private void GoStats(ActionEvent event) throws IOException { 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/PerformanceStats.fxml"));
    Parent root = loader.load();
   PerformanceStatsController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }
    
   
}