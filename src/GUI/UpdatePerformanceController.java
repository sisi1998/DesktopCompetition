/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Competition;
import Entities.PerformanceC;
import Entities.User;
import static GUI.ListPerformanceController.perfs;
import Services.CompetitionService;
import Services.PerformanceCService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.application.Platform;
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
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Siwar
 */
public class UpdatePerformanceController implements Initializable {

    @FXML
    private Label competition;
    @FXML
    private Label joueur;
    @FXML
    private TextField apps;
    @FXML
    private TextField mins;
    @FXML
    private TextField buts;
    @FXML
    private TextField pd;
    @FXML
    private TextField jaune;
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
    private Button enregistrerB;
  CompetitionService sp = new CompetitionService();
     PerformanceCService ps = new PerformanceCService();
         private int id;
    @FXML
    private Button compC;
    @FXML
    private Button PerfC;
    @FXML
    private Button Goback;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/GUI/ListPerformance.fxml"));
            Stage prStage = new Stage();

            Parent root;
        
        try {
                root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                ListPerformanceController irc = loader.getController();
                CompetitionService sp = new CompetitionService();

                 id = ListPerformanceController.perfs.getId();
                 System.out.println(id+"ttest333");
                 

            } catch (IOException ex) {
            }
    }    

    @FXML
    private void enregistrer(ActionEvent event) {
       //Competition cmp = competition.getValue();
      //  User jou = joueur.getValue();
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
        
       
   // jaun = Integer.parseInt(jauneStr);

    // Validate rouge
    
     if (rougeStr.isEmpty() || !intPattern.matcher(rougeStr).matches()) {
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

    
     
    
     PerformanceC perf = new PerformanceC(perfs.getId(),perfs.getIdjoueur(),perfs.getIdcom(),appsStr,minsStr,butsStr,pdStr,jauneStr,rougeStr,tpmStr,prStr,agStr,hdmStr,noteStr);
       System.out.println(perfs);
       System.out.println(perfs.getId()+"kkkkkk");
            // Call the addCompetition method from the service and add the competition
         ps.Update(perf);
          showAlert("succes", "modifiée avec succes.");
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
        public void setNote(String nom) {
    Platform.runLater(() -> note.setText(nom));
}                        
   public void setPr(String nom) {
    Platform.runLater(() -> pr.setText(nom));
}        
     public void setAg(String nom) {
    Platform.runLater(() -> ag.setText(nom));
}
          public void setHdm(String nom) {
    Platform.runLater(() -> hdm.setText(nom));
}
    public void setApps(String nom) {
    Platform.runLater(() -> apps.setText(nom));
}
    public void setMins(String nom) {
    Platform.runLater(() -> mins.setText(nom));
}
  public void setButs(String nom) {
    Platform.runLater(() -> buts.setText(nom));
}
   public void setPd(String nom) {
    Platform.runLater(() -> pd.setText(nom));
}
      public void setJaune(String nom) {
    Platform.runLater(() -> jaune.setText(nom));
}
       public void setRouge(String nom) {
    Platform.runLater(() -> rouge.setText(nom));
}        
       
           public void setTpm(String nom) {
    Platform.runLater(() -> tpm.setText(nom));
} 
           
             public void setCom(Competition nom) {
    Platform.runLater(() -> competition.setText(nom.getNom()));

} 
               public void setJou(User nom) {
     Platform.runLater(() -> joueur.setText(nom.getNom()));
   
} 
             
    public void setId(int id) {

        this.id = id;
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
  ListPerformanceController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();

    }

    @FXML
    private void goBckF(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ListPerformance.fxml"));
    Parent root = loader.load();
  ListPerformanceController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }
    
    
    
    
    
        
}
