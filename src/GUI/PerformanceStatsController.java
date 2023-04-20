/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;

/**
 * FXML Controller class
 *
 * @author Siwar
 */
public class PerformanceStatsController implements Initializable {

    @FXML
    private LineChart<?, ?> linechart;
    @FXML
    private PieChart piechart;
    @FXML
    private StackedBarChart<?, ?> barchart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
