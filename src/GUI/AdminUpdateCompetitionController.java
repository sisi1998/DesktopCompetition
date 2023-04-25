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
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Siwar
 */
public class AdminUpdateCompetitionController implements Initializable {

    @FXML
    private Button Load_image;
    @FXML
    private Button saveC;
    @FXML
    private JFXDatePicker DateC;
    @FXML
    private JFXTimePicker timeC;
    @FXML
    private ChoiceBox<Arena> areneC;
    @FXML
    private ChoiceBox<String> statutC;
    @FXML
    private TextField nomC;
    @FXML
    private ListView<Equipe> listview;
     private int id;
    
    
      
    String filePath="";
    
    CompetitionService sp = new CompetitionService();
    
    @FXML
    private AnchorPane anchorePaneEl;

    @FXML
    private ChoiceBox<Equipe> winnersE;
    @FXML
    private ImageView imgview;
    @FXML
    private Label EqG;
    @FXML
    private Button retoub;
    @FXML
    private Button compC;
    @FXML
    private Button PerfC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/GUI/AdminListCompetition.fxml"));
            Stage prStage = new Stage();

            Parent root;
        
        try {
                root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                AdminListCompetitionController irc = loader.getController();
                CompetitionService sp = new CompetitionService();

                 id = AdminListCompetitionController.competition2.getId();
                 System.out.println(id+"ttest333");
                 

            } catch (IOException ex) {
            }
    ObservableList<Arena> arenesList = FXCollections.observableArrayList(sp.getAllArenas());
   
    areneC.setItems(arenesList);

    ObservableList<String> statutList = FXCollections.observableArrayList("En attente", "En cours", "Terminé");
    statutC.setItems(statutList);
    
    
    
    ObservableList<Equipe>eqList= FXCollections.observableArrayList(sp.getAllEquipes());
    winnersE.setItems(eqList);

    // Initialize the listView
    ObservableList<Equipe> items = FXCollections.observableArrayList(sp.getAllEquipes());
    listview.setItems(items);
     System.out.println( items);
    listview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }   
    
    
   int idS;

    public void setId(int id) {

        this.id = id;
        System.out.println("her id " + idS);}
        
        
        
        
 

    @FXML
    private void saveC(ActionEvent event) {
   try {
        String name = nomC.getText();
        LocalDate date = DateC.getValue();
        LocalTime selectedTime = timeC.getValue();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
         String formattedTime = selectedTime.format(formatter);
        String dateString = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        Arena arena = areneC.getValue();
        String status = statutC.getValue();
        Equipe winner = winnersE.getValue();
        MultipleSelectionModel<Equipe> selectionModel = listview.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<Equipe> selectedItems = selectionModel.getSelectedItems();
        List<Equipe> equipeList = selectedItems.stream().collect(Collectors.toList());

        if (name.isEmpty()) {
            showAlert("Nom obligatoire", "Nom doit être non vide");
        } else if (dateString.isEmpty()) {
            showAlert("Date obligatoire", "Sélectionnez une date");
        } else if (arena == null || arena.getNom().isEmpty()) {
            showAlert("Arena obligatoire", "Sélectionnez une Arena");
        } else if (status.isEmpty()) {
            showAlert("Statut obligatoire", "Sélectionnez un statut");
//        }  else if (equipeList.size() < 2) {
//    showAlert("Equipe obligatoire", "Sélectionnez au moins deux équipes");;
        } else if (sp.exists(dateString, arena.getId())) {
            showAlert("Compétition existante", "Une compétition avec la même date et la même arena existe déjà");
        } else {
            // Create a new competition object from the input values
            //    public Competition(String date, Arena arena, String etat, Equipe equipe, String nom, String image) {

            Competition competition = new Competition(dateString+" "+formattedTime, arena, status,winner, name, filePath);

            // Call the addCompetition method from the service and add the competition
            System.out.println(id);
             System.out.println(competition.getIdarena().getId()+"ddd");
            System.out.println(competition.getIdwinner()+"idwin");
            sp.Update(competition, equipeList, id);
            showAlert("succes", "modifiée avec succes");
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AdminListCompetition.fxml"));
    Parent root = loader.load();
   AdminListCompetitionController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();

            // Clear input fields
//            nomC.clear();
//            DateC.setValue(null);
//            areneC.setValue(null);
//            statutC.setValue(null);
//            imgview.setImage(null);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        showAlert("Erreur", "Une erreur s'est produite. Veuillez réessayer.");
    }
}

        
       
    private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    @FXML
  private void UploadImageHandle(ActionEvent event) {
    FileChooser fileOpen = new FileChooser();
   System.out.println("hi");
    Stage stage = (Stage) anchorePaneEl.getScene().getWindow();
    File file = fileOpen.showOpenDialog(stage);
    
    if(file != null) {
        try {
             
            String path = file.getName(); //file.getPath() 0 security...
            filePath=path;
            //https://img.png 
            
            Image image = new Image(file.toURI().toString(),100,100,false,true);
            imgview.setImage(image);
            
            // Set the destination directory path
            String destDir = "C:/xampp/htdocs/img/";
            
            // Copy the uploaded file to the destination directory
            Path source = Paths.get(file.getAbsolutePath());
            Path dest = Paths.get(destDir + file.getName());
            Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
            
            System.out.println("File uploaded successfully to " + destDir + file.getName());
        } catch (IOException ex) {
            System.out.println("Error uploading file: " + ex.getMessage());
        }
    }
    else {
        System.out.println("NO PICTURE EXISTS!!");
    }
}



     
    public void setNom(String nom) {
    Platform.runLater(() -> nomC.setText(nom));
}

public void setArena(Arena arena) {
    Platform.runLater(() -> areneC.setValue(arena));
}

public void setStatus(String status) {
    Platform.runLater(() -> statutC.setValue(status));
}

public void setEquipeList(List<Equipe> equipeList) {
    Platform.runLater(() -> {
      //  listview.getItems().clear();
        //listview.getItems().addAll(equipeList);
    });
}

public void setDateAndTime(String dateAndTime) {
    Platform.runLater(() -> {
        LocalDate date = LocalDate.parse(dateAndTime.split(" ")[0]);
        LocalTime time = LocalTime.parse(dateAndTime.split(" ")[1]);
        DateC.setValue(date);
        timeC.setValue(time);
    });
}


public void setWinner(Equipe eq) {
    Platform.runLater(() -> EqG.setText(eq.getNom()));
    System.out.println(eq);
}

public void setImage(String img) {
    Platform.runLater(() -> {
        filePath = img;
        File file = new File(img);
        Image image = new Image(file.toURI().toString());
        imgview.setImage(image);
    });
}

    @FXML
    private void retour(ActionEvent event) throws IOException {
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
   ListPerformanceController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }

    @FXML
    private void EspacePerformance(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ListPerformance.fxml"));
    Parent root = loader.load();
   ListPerformanceController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }

                           
}

