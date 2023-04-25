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
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Optional;
import javafx.scene.Node;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Siwar
 */
public class AdminListCompetitionController implements Initializable {

    @FXML
    private TableView<Competition> tableview;
    @FXML
    private TableColumn<Competition, String> nom;
    @FXML
    private TableColumn<Competition, Arena> arene;
    @FXML
    private TableColumn<Competition, String>  date;
    @FXML
    private TableColumn<Competition, String>  statut;
    @FXML
    private TableColumn<Competition, ImageView> image;
    @FXML
    private TableColumn<Competition, Equipe> winner;
    private TableColumn<Competition, Void> colModifBtn;
    private TableColumn<Competition, Void> colSuppBtn;
    private TableColumn<Competition, Void> colExpBtn;
    
    CompetitionService sp = new CompetitionService();
    ObservableList<Competition> obList;
    @FXML
    private Button PerfC;
    @FXML
    private Button compC;
    @FXML
    private Button addCompetition;
    @FXML
    private Button delete;
    


// Create a HashMap to store the ImageViews for each Competition
private HashMap<Integer, ImageView> imageViewMap = new HashMap<>();
    @FXML
    private Button send;
    @FXML
    private TextField recherche;

/**
 * Initializes the controller class.
 */
@Override
public void initialize(URL url, ResourceBundle rb) {
    // Setting the URL

   // colSuppBtn = new TableColumn<>("Supprimer");
    //tableview.getColumns().add(colSuppBtn);

    colModifBtn = new TableColumn<>("Modifier");
    tableview.getColumns().add(colModifBtn);

    addButtonModifToTable();
    //addButtonDeleteToTable();

    ObservableList<Competition> list = FXCollections.observableArrayList();
    for (Competition u : sp.affichage()) {
        list.add(u);
        System.out.println();
    }
    statut.setCellValueFactory(new PropertyValueFactory<>("etat"));
    arene.setCellValueFactory(new PropertyValueFactory<>("Idarena"));
    date.setCellValueFactory(new PropertyValueFactory<>("date"));

    nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    // image.setCellValueFactory(new PropertyValueFactory<>("image"));
    winner.setCellValueFactory(new PropertyValueFactory<>("Idwinner"));

    // First, create a single ImageView object to reuse for each cell
    ImageView imageView = new ImageView();
    imageView.setFitWidth(50);
    imageView.setFitHeight(50);

    // Then, set up the cell value factory to return the ImageView for each cell
    String destDir = "file:///C:/xampp/htdocs/img/";
    image.setCellValueFactory(cellData -> {
        Competition competition = cellData.getValue();
        String imagePath = competition.getImage();
        if (imagePath != null) {
            try {
                // Check if an ImageView has already been created for this Competition ID
                ImageView imageViewForCell = imageViewMap.get(competition.getId());
                if (imageViewForCell == null) {
                    // If not, create a new ImageView and store it in the HashMap
                    imageViewForCell = new ImageView();
                    imageViewForCell.setFitWidth(50);
                    imageViewForCell.setFitHeight(50);
                    imageViewMap.put(competition.getId(), imageViewForCell);
                }
                Image image = new Image(destDir + imagePath);
                if (image.isError()) {
                    System.err.println("Error loading image from URL: " + imagePath);
                    return null;
                }
                // Update the image property of the reusable ImageView
                imageViewForCell.setImage(image);
                return new SimpleObjectProperty<>(imageViewForCell);
            } catch (Exception e) {
                System.err.println("Error loading image: " + e.getMessage());
                return null;
            }
        } else {
            // If the image path is null, return null to display an empty cell
            return null;
        }
    });

    // Load the data from the CompetitionService into the TableView
    tableview.setItems(list);

}

   
    @FXML
    private void supprimerC(ActionEvent event) {

    Competition selectedCompetition = tableview.getSelectionModel().getSelectedItem();
    if (selectedCompetition == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Please select a competition to delete.");
        alert.showAndWait();
        return;
    }

    int id = selectedCompetition.getId();
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to delete the competition with ID " + id + "?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        CompetitionService cs = new CompetitionService();
        cs.Delete(id);
        tableview.getItems().remove(selectedCompetition);
    }
}

     
    Button btn;
    Competition competition = new Competition();
    public static Competition competition2 ;
    public void addButtonModifToTable() {
        Callback<TableColumn<Competition, Void>, TableCell<Competition, Void>> cellFactory = new Callback<TableColumn<Competition, Void>, TableCell<Competition, Void>>() {
            @Override
            public TableCell<Competition, Void> call(final TableColumn<Competition, Void> param) {

                final TableCell<Competition, Void> cell = new TableCell<Competition, Void>() {

                    {
                        btn = new Button("Modifier");
                        btn.setOnAction((ActionEvent event) -> {
                            try {
                              competition = tableview.getSelectionModel().getSelectedItem();//
                            System.out.println(competition);
                                  competition2=competition;
                                  
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AdminUpdateCompetition.fxml"));
                                Parent root = loader.load();
                            AdminUpdateCompetitionController controller = loader.getController();
                            System.out.println(competition2.getId()+"test");
                            
                            controller.setId(competition.getId());
                            controller.setNom(competition.getNom());
                            controller.setArena(competition.getArena());
                            controller.setStatus(competition.getEtat());
                            controller.setEquipeList(competition.getEquipes());
                            controller.setDateAndTime(competition.getDate());
                            controller.setWinner(competition.getIdwinner());
                            controller.setImage(competition.getImage());
                            System.out.println(competition.getNom()+"test2");
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
    private void RechercheHandle(KeyEvent event) {
          ObservableList<Competition> list = FXCollections.observableArrayList();
    for (Competition u : sp.affichage()) {
        list.add(u);
        System.out.println();
    }
          FilteredList<Competition> filteredList = new FilteredList<>(list, b -> true);

        recherche.textProperty().addListener((observable, oldValue, newValue) -> {

            if (recherche.getText().isEmpty()) {

                addButtonModifToTable();
               

            }
            filteredList.setPredicate(cmp -> {
                if (newValue == null || newValue.isEmpty()) {
                    btn = new Button("Modifier");
                    

                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
 if (String.valueOf(cmp.getNom()).toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                } else if (String.valueOf(cmp.getDate()).toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                } else if (String.valueOf(cmp.getArena().getNom()).toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                    
                     } else if (String.valueOf(cmp.getEtat()).toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                }
                else {
                    btn = new Button("Modifier");
                 
                    return false;
                }

            });

        });
        SortedList<Competition> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());

        tableview.setItems(sortedData);

    
    }


    Button btnSupprimer;


    @FXML
    private void EspacePerformance(ActionEvent event) throws IOException {
        
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ListPerformance.fxml"));
    Parent root = loader.load();
    ListPerformanceController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
                            
    }

    @FXML
    private void espaceCompetition(ActionEvent event) {
    }

    @FXML
    private void addCompetitionM(ActionEvent event) throws IOException {
             
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AdminAddCompetition.fxml"));
    Parent root = loader.load();
   AdminAddCompetitionController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
                            
    }

    @FXML
    private void sendMails(ActionEvent event) throws ParseException {
        sp.checkAllCompetitionsDueDate();
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mail");
        alert.setHeaderText(null);
        alert.setContentText("les joeurs sont notifiés.");
        alert.showAndWait();
        return;
    }

   
    @FXML
    private void sendMails(MouseEvent event) {
    }

   
}
