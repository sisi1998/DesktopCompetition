package GUI;

import Services.PerformanceCService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PerformanceStatsController implements Initializable {

    @FXML
    LineChart<String, Double> linechart;
    
    @FXML
    private PieChart piechart;
    
    @FXML
    private StackedBarChart<String, Integer> barchart;
    
    private int buts, jaune, tpm, rouge, pd, ag;
    @FXML
    private Button Goback;
    @FXML
    private Button compC;
    @FXML
    private Button PerfC;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PerformanceCService ps = new PerformanceCService();
        buts = ps.sumButs();
        jaune = ps.sumJaune();
        tpm = ps.sumTpM();
        rouge = ps.sumRouge();
        pd = ps.sumPointsDecisives();
        ag = ps.sumAeriensG();
        
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Buts", buts));
        series.getData().add(new XYChart.Data<>("Jaunes", jaune));
        series.getData().add(new XYChart.Data<>("Tirs par match", tpm));
        series.getData().add(new XYChart.Data<>("Rouges", rouge));
        series.getData().add(new XYChart.Data<>("Points décisifs", pd));
        series.getData().add(new XYChart.Data<>("Aériens gagnés", ag));

        barchart.getData().add(series);
        
        
      List<Integer> percentages = ps.countPerformancesByNoteRange();
        List<String> labels = new ArrayList<>();
        labels.add("Note > 20");
        labels.add("Note entre 20 et 10");
        labels.add("Note entre 10 et 5");
        labels.add("Note sous 5");

        List<PieChart.Data> pieChartData = new ArrayList<>();
        for (int i = 0; i < percentages.size(); i++) {
            PieChart.Data data = new PieChart.Data(labels.get(i), percentages.get(i));
            pieChartData.add(data);
        }
        piechart.setData(FXCollections.observableArrayList(pieChartData));

        // Set percentage labels
        for (PieChart.Data data : piechart.getData()) {
            double percentage = Math.round(data.getPieValue() / ps.countTotalPerformances() * 10000.0) / 100.0;
            data.setName(data.getName() + " " + percentage + "%");
        }
        
        
         
        
        
        List<Object[]> data = ps.moyPerformanceLastTenCompetitions();

XYChart.Series<String, Double> series2 = new XYChart.Series<>();
for (Object[] row : data) {
    String competitionId = (String) row[0];
    Double average = (Double) row[1];
    series2.getData().add(new XYChart.Data<>(competitionId, average));
}

linechart.getData().add(series2);

        
        
        
        
        
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

    @FXML
    private void GobackF(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ListPerformance.fxml"));
    Parent root = loader.load();
   ListPerformanceController controller = loader.getController();
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
    }
    
    
    
    
    
    
    }   

