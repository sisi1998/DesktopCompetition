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
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.awt.event.MouseEvent;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
//=import javafx.scene.control.DatePicker;
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
import javax.swing.text.DateFormatter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.util.Hashtable;

import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Siwar
 */
public class AdminAddCompetitionController implements Initializable {

    @FXML
    private TextField nomC;
   
    @FXML
    private Button Load_image;
    @FXML
    private ChoiceBox<Arena> areneC;
    @FXML
    private ChoiceBox<String> statutC;
     
    
    String filePath="";
    
    CompetitionService sp = new CompetitionService();
    @FXML
    private AnchorPane anchorePaneEl;
    @FXML
    private ListView<Equipe> listview;
    Arena a1 = new Arena(1,"Sofia");
    Arena a2 = new Arena(2,"Haruka");
    /////static equipe instancitaion to be implented after integration
    
      Equipe eq1 = new Equipe(1,"Stars");
     Equipe eq2 = new Equipe(3,"Fanatic");
     Equipe eq3 = new Equipe(2,"Manta");
    @FXML
    private ImageView imgview;
    @FXML
    private JFXTimePicker timeC;
    @FXML
    private DatePicker DateC;
    @FXML
    private Button btn_ajouterC1;
    @FXML
    private Button backButton;
    @FXML
    private Button compC;
    @FXML
    private Button perfC1;
        
     
    

    /**
     * Initializes the controller class.
     */
    @Override
public void initialize(URL url, ResourceBundle rb) {
    
    // Initialize the areneC and statutC ChoiceBoxes
    
    ObservableList<Arena> arenesList = FXCollections.observableArrayList(sp.getAllArenas());
    areneC.setItems(arenesList);

    ObservableList<String> statutList = FXCollections.observableArrayList("En attente", "En cours", "Terminé");
    statutC.setItems(statutList);

    // Initialize the listView
    ObservableList<Equipe> items = FXCollections.observableArrayList(sp.getAllEquipes());
    listview.setItems(items);
    listview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
}

  
   @FXML
private void AjouterV(ActionEvent event) {
    try {
        String name = nomC.getText();
        LocalDate date = DateC.getValue();
        LocalTime selectedTime = timeC.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = selectedTime.format(formatter);
        String dateString = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        Arena arena = areneC.getValue();
        String status = statutC.getValue();
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
        } else if (equipeList.size() < 2) {
            showAlert("Equipe obligatoire", "Sélectionnez au moins deux équipes");
        } else if (sp.exists(dateString, arena.getId())) {
            showAlert("Compétition existante", "Une compétition avec la même date et la même arena existe déjà");
        } else {
             String fileName = dateString + "-" + arena.getId() + ".png";
            String qrCodeFilePath = "C:/xampp/htdocs/img/" + fileName;
            Competition competition = new Competition(dateString + " " + formattedTime, arena, status, name, filePath);
          //  BufferedImage qrCodeImage = GenerateQRCode.generateQRCodeForCompetition(competition);
         generateQRCodeImage("Nom Competition:"+name+",\n arena:"+arena.getNom()+",\n date:"+dateString, 350, 350, qrCodeFilePath);
            // Save the QR code image as a file
           
          //  ImageIO.write(qrCodeImage, "png", new File(qrCodeFilePath));

            // Set the QR code file path in the competition object
            competition.setCodeqr("/img/" + fileName);
             System.out.println(competition.getCodeqr());
            competition.setCodeqr(qrCodeFilePath);
             
            
            // Add the competition to the database
            sp.Add(competition, equipeList);

            showAlert("Succès", "La compétition a été ajoutée avec succès.");

            // Navigate to the competition list page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AdminListCompetition.fxml"));
            Parent root = loader.load();
            AdminListCompetitionController controller = loader.getController();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

            // Clear input fields
            nomC.clear();
            DateC.setValue(null);
            timeC.setValue(null);
            areneC.setValue(null);
            statutC.setValue(null);
            imgview.setImage(null);
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


    @FXML
    private void retourB(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AdminListCompetition.fxml"));
    Parent root = loader.load();
   AdminListCompetitionController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }


  private static String decodeQRCode(File qrCodeimage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            System.out.println("There is no QR code in the image");
            return null;
        }
    }
 


    private static void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        
        
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
    private void espacePerformance(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ListPerformance.fxml"));
    Parent root = loader.load();
   ListPerformanceController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }
    
    
}
