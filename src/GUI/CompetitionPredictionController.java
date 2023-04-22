package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

public class CompetitionPredictionController implements Initializable {

    @FXML
    private Label predictionLabel;

    @FXML
    private Button predictButton;

    private static final String[] FEATURES = { "victoires", "defaites", "nuls", "but_marque", "but_encaisses" };

    private Instances data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        predict();
//        try {
//            // Load the data from the MySQL database
//            data = loadData();
//
//            // Set the action listener for the predict button
//            predictButton.setOnAction(event -> predict());
//
//        } catch (Exception ex) {
//            Logger.getLogger(CompetitionPredictionController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void predict() {
        try {
            // Train the model on the data
            RandomForest model = new RandomForest();
            model.buildClassifier(data);

            // Evaluate the model using 10-fold cross-validation
            Evaluation eval = new Evaluation(data);
            eval.crossValidateModel(model, data, 10, new java.util.Random(1));

            // Print the evaluation summary
            String evaluationSummary = eval.toSummaryString();
            System.out.println(evaluationSummary);

            // Print the confusion matrix
            String confusionMatrix = eval.toMatrixString();
            System.out.println(confusionMatrix);

            // Make predictions for each competition
            List<Integer> predictedWinners = new ArrayList<>();
            for (int i = 0; i < data.numInstances(); i++) {
                DenseInstance instance = (DenseInstance) data.instance(i);
                int winnerId = (int) model.classifyInstance(instance);
                predictedWinners.add(winnerId);
            }

            // Update the UI with the predicted winners
            String winnersString = predictedWinners.toString();
            predictionLabel.setText("Predicted winners: " + winnersString);
        } catch (Exception ex) {
            Logger.getLogger(CompetitionPredictionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Instances loadData() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/integration3", "root", "");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "SELECT c.id AS competition_id, e.id AS equipe_id, p.victoires, p.defaites, p.nuls, p.but_marque, p.but_encaisses FROM competition AS c JOIN competition_equipe AS ce ON c.id = ce.competition_id JOIN equipe AS e ON ce.equipe_id = e.id JOIN performance_equipe AS p ON e.performance_e_id = p.id");

        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("competition_id"));
        attributes.add(new Attribute("equipe_id"));
        for (String feature : FEATURES) {
            attributes.add(new Attribute(feature));
        }
        Instances data = new Instances("competition_data", attributes, 0);
        data.setClassIndex(attributes.size() - 1);

        while (resultSet.next()) {
            double[] values = new double[attributes.size()];
            values[0] = resultSet.getInt("competition_id");
            values[1] = resultSet.getInt("equipe_id");
            for (int i = 0; i < FEATURES.length; i++) {
                values[i + 2] = resultSet.getDouble(FEATURES[i]);
            }
            data.add(new DenseInstance(1.0, values));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return data;
    }
}

