package GUI;

import Entities.Competition;
import Entities.Equipe;
import Services.CompetitionService;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FavoriteCompetitionController implements Initializable {

    @FXML
    private TableColumn<Competition, String> cmpC;
    @FXML
    private TableColumn<Competition, ImageView> image;
    @FXML
    private TableColumn<Competition, String> equipeC;

    private final CompetitionService sp = new CompetitionService();
    private final ObservableList<Competition> list = FXCollections.observableArrayList();
    private final HashMap<Integer, ImageView> imageViewMap = new HashMap<>();

    @FXML
    private TableView<Competition> tableview;
    @FXML
    private Button perfButton1;
    @FXML
    private Button compB;
    @FXML
    private Button Goback;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cmpC.setCellValueFactory(new PropertyValueFactory<>("nom"));

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

        equipeC.setCellValueFactory(cellData -> {
            Competition competition = cellData.getValue();
            Equipe favoredEquipe = sp.getFavoredEquipe(competition.getId());
            return new SimpleStringProperty(favoredEquipe.getNom());
        });

        for (Competition u : sp.affichage()) {
            list.add(u);
        }

        tableview.setItems(list);
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
    private void goBck(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CompetitionFront.fxml"));
    Parent root = loader.load();
   CompetitionFrontController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }
}
