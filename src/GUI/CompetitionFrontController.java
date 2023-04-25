/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Competition;
import java.net.URISyntaxException;
import static GUI.AdminListCompetitionController.competition2;
import Services.CompetitionService;
import static java.awt.Color.blue;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Siwar
 */
public class CompetitionFrontController implements Initializable {

    private VBox cardLayout;
    @FXML
    private GridPane compGrid;
    @FXML
    private Button perfButton;
    public static ImageView competition ;
    @FXML
    private Label CHcomp;
    @FXML
    private Label chDate;
    @FXML
    private Label CHare;
    @FXML
    private ImageView CHcodeqr;
     private MyListener myListener;
    @FXML
    private AnchorPane chosenCompetitionCard;
    @FXML
    private TextField searchName;
    @FXML
    private Button searchCritere;
    @FXML
    private TextField searchArena;
    @FXML
    private TextField searchDate;
    @FXML
    private Button detail;
    
    public static Competition selectedCompetition;
    @FXML
    private ImageView imgY;
    @FXML
    private Button prevC;
    @FXML
    private Button compButton1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 CompetitionService cp =new CompetitionService();
    List <Competition> Competitions = cp.affichage();
    
     if (Competitions.size() > 0) {
            setChosenCompetition(Competitions.get(0));
            myListener = this::setChosenCompetition;
     System.out.println(myListener);}
     
      detail.setOnAction((event) -> {
            if (selectedCompetition != null) {
                try {
                    System.out.println("this is the chosn one"+selectedCompetition.getId());
                  

                    
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CompetitonDetail.fxml"));

                    Parent root = loader.load();
                    CompetitonDetailController controller = loader.getController();
                                        System.out.println("this is the chosn one after "+selectedCompetition.getId());
                    System.out.println(selectedCompetition.getId()+"test");
                    
                    controller.setId(selectedCompetition.getId());
                    controller.setNom(selectedCompetition.getNom());
                    controller.setArena(selectedCompetition.getArena());
                    controller.setStatus(selectedCompetition.getEtat());
                    CompetitionService dd =new CompetitionService();
                    controller.setEquipeList( dd.getEquipesByCompetitionId(selectedCompetition.getId()));
                    controller.setDateAndTime(selectedCompetition.getDate());
                    controller.setWinner(selectedCompetition.getIdwinner());
                    controller.setImage(selectedCompetition.getImage());
                    System.out.println(selectedCompetition.getNom()+"test2");
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(CompetitionFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("No Competition Selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select a competition to view its details.");
                alert.showAndWait();
            }
        });
    
    
    
    
int comuns =0;
int row=1;
   for(int i=0; i< Competitions.size();i++){
     try {
         FXMLLoader fxmlloader =new FXMLLoader();
         fxmlloader.setLocation(getClass().getResource("competition.fxml"));
         AnchorPane cardbox = fxmlloader.load();
         CompetitionController competitionController =fxmlloader.getController();
         competitionController.setData(Competitions.get(i),myListener);
        if(comuns==4){
            comuns=0;
        ++row;
        }
     compGrid.add(cardbox,comuns++, row);
      compGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
        compGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
          compGrid.setMaxWidth(Region.USE_COMPUTED_SIZE);
     GridPane.setMargin(cardbox, new Insets (10));
     
     
     
     }
     // TODO
     catch (IOException ex) {
         Logger.getLogger(CompetitionFrontController.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
    
    
    }    

    @FXML
    private void Toperformance(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/PerformanceFront.fxml"));
    Parent root = loader.load();
   PerformanceFrontController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }
    
    
    
    
    
    
   private void setChosenCompetition(Competition cmp) {
     selectedCompetition = cmp;
    CHcomp.setText(cmp.getNom());
    CHare.setText(cmp.getArena().getNom());
    chDate.setText(cmp.getDate());
    
    // Load the image from the file path
    String imagePath = cmp.getCodeqr();
    System.out.println(imagePath);
    if (imagePath != null) {
        String imageUrl = "file:///" + imagePath.replace("\\", "/");
        Image image = new Image(imageUrl);
        if (image.isError()) {
            System.err.println("Error loading image from URL: " + imageUrl);
        } else {
            // Update the image property of the reusable ImageView
            CHcodeqr.setImage(image);
        }
    }


}
@FXML
private void searchCritereS(ActionEvent event) {
    String nom = searchName.getText();
    String date = searchDate.getText();
    String arenaNom = searchArena.getText();
    
    // Validate date input format
    boolean validDate = date.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");
    
    if (!validDate) {
        // Display an error message if date input is invalid
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText("La date doit être au format 'yyyy-MM-dd HH:mm:ss'");
        alert.showAndWait();
        return;
    }
    
    CompetitionService cp = new CompetitionService();
    boolean competitionExists = cp.exists(nom, date, arenaNom);
    
    if (competitionExists) {
        Competition competition = cp.getCompetitionByCriteria(nom, date, arenaNom);
        setChosenCompetition(competition);
    } else {
        // Display a message to inform the user that no competition matching the search criteria was found
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("No Competition Found");
        alert.setHeaderText(null);
        alert.setContentText("Pas de competition avec ces criteres");
        alert.showAndWait();
    }
}

    @FXML
    private void GotoDetails(ActionEvent event) {
    }

  @FXML
private void imgTOyou(MouseEvent event) throws URISyntaxException, IOException {
    URI youtubeLink = new URI("https://www.youtube.com/watch?v=MnBpFAo5FrI&ab_channel=Ligue1UberEats");
    Desktop.getDesktop().browse(youtubeLink);
}

    @FXML
    private void GoToPrev(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/FavoriteCompetition.fxml"));
    Parent root = loader.load();
   FavoriteCompetitionController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }

    @FXML
    private void Topcomp(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CompetitionFront.fxml"));
    Parent root = loader.load();
   ListPerformanceController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }







    }
   

