package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class CompetitionPredictionController implements Initializable {

    @FXML
    private Label predictionLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Call the Python script and retrieve the predicted winners
        List<Integer> predictedWinners = getPredictedWinners();
        
        // Update the UI with the predicted winners
        String winnersString = Arrays.toString(predictedWinners.toArray());
        predictionLabel.setText("Predicted winners: " + winnersString);
    }
    
    private List<Integer> getPredictedWinners() {
        List<Integer> predictedWinners = new ArrayList<>();
        
        // Create a PythonInterpreter object
        PythonInterpreter interp = new PythonInterpreter();
        // Execute the Python script
       // C:\Users\Siwar\PycharmProjects
       
       interp.execfile("file://C:/Users/Siwar/PycharmProjects/modelPerformance/main.py");
        // Get the value of the predictedWinners variable from the script
        PyObject pyObject = interp.get("predictedWinners");
        List<PyObject> winnersObject = new ArrayList<>();
        for (int i = 0; i < pyObject.__len__(); i++) {
            winnersObject.add(pyObject.__getitem__(i));
        }
        // Parse the output of the script to extract the predicted winners
        for (PyObject winner : winnersObject) {
            predictedWinners.add(winner.asInt());
        }
        // Close the PythonInterpreter object
        interp.close();
        
        return predictedWinners;
    }
}