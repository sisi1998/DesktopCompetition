/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Arena;
import Entities.Competition;
import Entities.PerformanceC;
import Entities.User;
import Services.CompetitionService;
import Services.PerformanceCService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Siwar
 */
public class AddPerformanceController implements Initializable {

    @FXML
    private TextField jaune;
    @FXML
    private TextField mins;
    @FXML
    private TextField buts;
    @FXML
    private TextField pd;
    @FXML
    private TextField apps;
    @FXML
    private TextField rouge;
    @FXML
    private TextField tpm;
    @FXML
    private TextField pr;
    @FXML
    private TextField ag;
    @FXML
    private TextField hdm;
    @FXML
    private TextField note;
    @FXML
    private ChoiceBox<Competition> competition;
    @FXML
    private ChoiceBox<User> joueur;
    @FXML
    private Button ajouter;
     CompetitionService sp = new CompetitionService();
     PerformanceCService ps = new PerformanceCService();
    @FXML
    private Button retoub;
    @FXML
    private Button compC;
    @FXML
    private Button perfC1;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          // public User(int id, String nom, String prenom, String role) {
      User us1 = new User(2,"Najjar","siwar","joueur");
        ObservableList<User> arenesList = FXCollections.observableArrayList(us1);
        joueur.setItems(arenesList);
    
       ObservableList<Competition> list = FXCollections.observableArrayList();
        for (Competition u : sp.affichage()) {
            list.add(u);
            System.out.println();
        }
            competition.setItems(list);
    }    

    @FXML
    private void ajouterP(ActionEvent event) {
        Competition cmp = competition.getValue();
        User jou = joueur.getValue();
       String jauneStr = jaune.getText();
        String rougeStr = rouge.getText();
        String minsStr = mins.getText();
        String pdStr = pd.getText();
        String appsStr = apps.getText();
       String noteStr = note.getText();
       String prStr = pr.getText();
        String butsStr = buts.getText();
         String agStr = ag.getText();
        String notee = note.getText();
         String hdmStr = hdm.getText();
        String tpmStr = tpm.getText();
                
   // int jaun = 0, roug = 0, min = 0, app = 0, pdd = 0, tpmm = 0, prr = 0, but = 0, agg = 0, hdmm = 0, notee = 0;

    // Regex pattern for matching positive integers
    Pattern intPattern = Pattern.compile("^\\d+$");

    // Validate jaune
    try {
        
        if (jou==null){showAlert("Erreur de saisie", "Veuillez choisir un joueur");}
        else if (cmp==null){showAlert("Erreur de saisie", "Veuillez choisir une competition");}
        
        else if (cmp==null) {
        showAlert("Erreur de saisie", "Veuillez saisir un entier valide pour le champ jaune");
      
    }
   // jaun = Integer.parseInt(jauneStr);

    // Validate rouge
    
    else if (rougeStr.isEmpty() || !intPattern.matcher(rougeStr).matches()) {
        showAlert("Erreur de saisie", "Veuillez saisir un entier valide pour le champ rouge");
       
    }
   // roug = Integer.parseInt(rougeStr);

    // Validate mins
   
    else if (minsStr.isEmpty() || !intPattern.matcher(minsStr).matches()) {
        showAlert("Erreur de saisie", "Veuillez saisir un entier valide pour le champ mins");
    
    }
   // min = Integer.parseInt(minsStr);

    // Validate apps
   
    else if (appsStr.isEmpty() || !intPattern.matcher(appsStr).matches()) {
        showAlert("Erreur de saisie", "Veuillez saisir un entier valide pour le champ apps");
     
    }
  //  app = Integer.parseInt(appsStr);

    // Validate pd
    
    else if (pdStr.isEmpty() || !intPattern.matcher(pdStr).matches()) {
        showAlert("Erreur de saisie", "Veuillez saisir un entier valide pour le champ pd");
        
    }
    //pdd = Integer.parseInt(pdStr);

    // Validate tpm
  
    else  if (tpmStr.isEmpty() || !intPattern.matcher(tpmStr).matches()) {
        showAlert("Erreur de saisie", "Veuillez saisir un entier valide pour le champ tpm");
        
    }
    //tpmm = Integer.parseInt(tpmStr);

    // Validate pr

   else if (prStr.isEmpty() || !intPattern.matcher(prStr).matches()) {
        showAlert("Erreur de saisie", "Veuillez saisir un entier valide pour le champ pr");
        
    }
  //  prr = Integer.parseInt(prStr);

    // Validate buts
  
   else if (butsStr.isEmpty() || !intPattern.matcher(butsStr).matches()) {
        showAlert("Erreur de saisie", "Veuillez saisir un entier valide pour le champ buts");
      
    }
   // but = Integer.parseInt(butsStr);

    // Validate ag
  
   else if (agStr.isEmpty() || !intPattern.matcher(agStr).matches()) {
        showAlert("Erreur de saisie", "Veuillez saisir un entier valide pour le champ ag");
       
    }
    
   else if (noteStr.isEmpty() || !intPattern.matcher(noteStr).matches()) {
        showAlert("Erreur de saisie", "Veuillez saisir un entier valide pour le champ note");
       
    }
   
   else if (noteStr.isEmpty() || !intPattern.matcher(noteStr).matches()) {
        showAlert("Erreur de saisie", "Veuillez saisir un entier valide pour le champ note");
       
    }
    else{
    //public PerformanceC(int Idjoueur, int Idcom, String Apps, String Mins, String Buts, String PointsDecisives, String Jaune, String Rouge, String TpM, String Pr, String AerienG, String HdM, String Note) {

     PerformanceC per = new PerformanceC(jou,cmp,appsStr,minsStr,butsStr,pdStr,jauneStr,rougeStr,tpmStr,prStr,agStr,hdmStr,noteStr);
System.out.println(per);
 System.out.println(per.getIdcom().getId());
            // Call the addCompetition method from the service and add the competition
            ps.Add(per);
             showAlert("succes", "Une performance est ajoutée");
 FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ListPerformance.fxml"));
    Parent root = loader.load();
   ListPerformanceController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
            // Clear input fields
            
            
    }
    } catch (Exception ex) {
        ex.printStackTrace();
        showAlert("Erreur", "Une erreur s'est produite. Veuillez réessayer.");
    }}
    
    
    private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

    @FXML
    private void retour(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ListPerformance.fxml"));
    Parent root = loader.load();
   ListPerformanceController controller = loader.getController();
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
    private void espacePerformance(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ListPerformance.fxml"));
    Parent root = loader.load();
   ListPerformanceController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }
}
