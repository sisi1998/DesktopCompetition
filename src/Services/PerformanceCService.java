/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Competition;
import Entities.PerformanceC;
import Entities.User;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Siwar
 */
public class PerformanceCService implements IserviceP<PerformanceC>{
private Connection conn;
    
    public PerformanceCService(){
    conn=DataSource.getInstance().getCnx();}
@Override
public void Add(PerformanceC c) {
    String competitionQuery = "SELECT id FROM competition WHERE id = ?";
    String performanceQuery = "INSERT INTO performance_c (competition_p_id,user_id,apps, mins, buts, points_decisives, jaune, rouge, Tp_m, pr, aeriens_g, hd_m, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        PreparedStatement competitionStatement = conn.prepareStatement(competitionQuery);
        competitionStatement.setInt(1, c.getIdcom().getId());
        ResultSet competitionResult = competitionStatement.executeQuery();
        if (!competitionResult.next()) {
            // competition_p_id value not found in competition table, handle error appropriately
            throw new RuntimeException("Competition ID not found in database");
        }

        PreparedStatement performanceStatement = conn.prepareStatement(performanceQuery);
        performanceStatement.setInt(1, c.getIdcom().getId());
        performanceStatement.setInt(2, c.getIdjoueur().getId());
        performanceStatement.setString(3, c.getApps());
        performanceStatement.setString(4, c.getMins());
        performanceStatement.setString(5, c.getButs());
        performanceStatement.setString(6, c.getPointsDecisives());
        performanceStatement.setString(7, c.getJaune());
        performanceStatement.setString(8, c.getRouge());
        performanceStatement.setString(9, c.getTpM());
        performanceStatement.setString(10, c.getPr());
        performanceStatement.setString(11, c.getAerienG());
        performanceStatement.setString(12, c.getHdM());
        performanceStatement.setString(13, c.getNote());

        performanceStatement.executeUpdate();

    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle any exceptions that may occur
    }
}



    @Override
   public void Update(PerformanceC performance) {
       //competition_p_id,user_id,apps, mins, buts, points_decisives, jaune, rouge, Tp_m, pr, aeriens_g, hd_m, note
    String query = "UPDATE performance_c SET apps = ?, mins = ?, buts = ?, points_decisives = ?, jaune = ?, rouge = ?, Tp_m = ?, pr = ?, aeriens_g = ?, hd_m = ?, note = ? WHERE id = ?";
    
    try {
        PreparedStatement statement = conn.prepareStatement(query);
        
        // Set the parameter values for the prepared statement
        statement.setString(1, performance.getApps());
        statement.setString(2, performance.getMins());
        statement.setString(3, performance.getButs());
        statement.setString(4, performance.getPointsDecisives());
        statement.setString(5, performance.getJaune());
        statement.setString(6, performance.getRouge());
        statement.setString(7, performance.getTpM());
        statement.setString(8, performance.getPr());
        statement.setString(9, performance.getAerienG());
        statement.setString(10, performance.getHdM());
        statement.setString(11, performance.getNote());
        statement.setInt(12, performance.getId());
        System.out.println(performance.getId());

        // Execute the prepared statement to update the performance in the database
        statement.executeUpdate();
        
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle any exceptions that may occur
    }
}


  @Override
public void Delete(int id) {
    try {
        conn.setAutoCommit(false); // Start a transaction
        
        // First delete any references to the performance entity in the competition table
//        String competitionQuery = "UPDATE competition SET id_performance_c = NULL WHERE id_performance_c = ?";
//        PreparedStatement competitionStatement = conn.prepareStatement(competitionQuery);
//        competitionStatement.setInt(1, id);
//        competitionStatement.executeUpdate();
//        
        // Next delete any references to the performance entity in the user table
//        String userQuery = "UPDATE user SET id_performance_c = NULL WHERE id_performance_c = ?";
//        PreparedStatement userStatement = conn.prepareStatement(userQuery);
//        userStatement.setInt(1, id);
//        userStatement.executeUpdate();
        
        // Then delete the performance entity
        String performanceQuery = "DELETE FROM performance_c WHERE id = ?";
        PreparedStatement performanceStatement = conn.prepareStatement(performanceQuery);
        performanceStatement.setInt(1, id);
        performanceStatement.executeUpdate();
        
        conn.commit(); // Commit the transaction
        conn.setAutoCommit(true); // Reset auto-commit mode
    } catch (SQLException ex) {
        Logger.getLogger(PerformanceC.class.getName()).log(Level.SEVERE, null, ex);
        try {
            conn.rollback(); // Rollback the transaction in case of error
            conn.setAutoCommit(true); // Reset auto-commit mode
        } catch (SQLException ex2) {
            Logger.getLogger(PerformanceC.class.getName()).log(Level.SEVERE, null, ex2);
        }
    }
}





    @Override
    public List<PerformanceC> searchCompetition(PerformanceC c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
   @Override
public List<PerformanceC> affichage() {
    List<PerformanceC> performances = new ArrayList<>();
    String query = "SELECT pc.id, pc.user_id, pc.competition_p_id, pc.apps, pc.mins, pc.buts, pc.points_decisives, pc.jaune, pc.rouge, pc.tp_m, pc.pr, pc.aeriens_g, pc.hd_m, pc.note, " +
                   "u.nom AS user_nom, " +
                   "u.nom AS user_nom, " +
                   "c.nom AS competition_nom " +
                   "FROM performance_c pc " +
                   "JOIN user u ON pc.user_id = u.id " +
                   "JOIN competition c ON pc.competition_p_id = c.id";

    try {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            PerformanceC performance = new PerformanceC();
            performance.setId(resultSet.getInt("id"));
            
            // Set User object for user_id
            User user = new User();
            user.setId(resultSet.getInt("user_id")); // foreign key
            user.setNom(resultSet.getString("user_nom")); // name attribute
            performance.setIdjoueur(user);
            
            // Set Competition object for competition_p_id
            Competition competition = new Competition();
            competition.setId(resultSet.getInt("competition_p_id")); // foreign key
            competition.setNom(resultSet.getString("competition_nom")); // name attribute
            performance.setIdcom(competition);
            
            // Set other properties of PerformanceC
            performance.setApps(resultSet.getString("apps"));
            performance.setMins(resultSet.getString("mins"));
            performance.setButs(resultSet.getString("buts"));
            performance.setPointsDecisives(resultSet.getString("points_decisives"));
            performance.setJaune(resultSet.getString("jaune"));
            performance.setRouge(resultSet.getString("rouge"));
            performance.setTpM(resultSet.getString("tp_m"));
            performance.setPr(resultSet.getString("pr"));
            performance.setAerienG(resultSet.getString("aeriens_g"));
            performance.setHdM(resultSet.getString("hd_m"));
            performance.setNote(resultSet.getString("note"));

            performances.add(performance);
        }

    } catch (SQLException ex) {
        Logger.getLogger(PerformanceC.class.getName()).log(Level.SEVERE, null, ex);
    }

    return performances;
}
public List<Object[]> moyperformance(int id) {
    String query = "SELECT user.id, competition.id, performance_c.apps, performance_c.mins, performance_c.buts, performance_c.points_decisives, performance_c.jaune, performance_c.rouge, performance_c.tp_m, performance_c.pr, performance_c.hd_m "
                 + "FROM performance_c "
                 + "INNER JOIN user ON performance_c.user_id = user.id "
                 + "INNER JOIN competition ON performance_c.competition_p_id = competition.id "
                 + "WHERE user.id = ?";
    
    try (PreparedStatement statement = conn.prepareStatement(query)) {
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        
        Map<String, List<Double>> scoresByCompetition = new HashMap<>();
        
        while (resultSet.next()) {
            String competitionId = resultSet.getString("competition.id");
            List<Double> scores = scoresByCompetition.computeIfAbsent(competitionId, k -> new ArrayList<>());
            
            double score = (Double.parseDouble(resultSet.getString("performance_c.apps")) * 10)
                         + (Double.parseDouble(resultSet.getString("performance_c.mins")) * 10)
                         + (Double.parseDouble(resultSet.getString("performance_c.buts")) * 20)
                         + (Double.parseDouble(resultSet.getString("performance_c.points_decisives")) * 20)
                         - (Double.parseDouble(resultSet.getString("performance_c.jaune")) * 20)
                         - (Double.parseDouble(resultSet.getString("performance_c.rouge")) * 10)
                         + (Double.parseDouble(resultSet.getString("performance_c.tp_m")) * 10)
                         + (Double.parseDouble(resultSet.getString("performance_c.pr")) * 10)
                         + (Double.parseDouble(resultSet.getString("performance_c.hd_m")) * 10);
            
            scores.add(score);
        }
        
        List<Object[]> result = new ArrayList<>();
        for (Map.Entry<String, List<Double>> entry : scoresByCompetition.entrySet()) {
            String competitionId = entry.getKey();
            List<Double> scores = entry.getValue();
            
            double average = scores.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            
            Object[] row = new Object[3];
            row[0] = id;
            row[1] = competitionId;
            row[2] = average;
            
            result.add(row);
        }
        
        return result;
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle any exceptions that may occur
    }
    
    return null; // or an empty list, depending on your requirements
}

public String getPlayerName(int playerId) {
    String query = "SELECT prenom FROM user WHERE id = ?";
    
    try (PreparedStatement statement = conn.prepareStatement(query)) {
        statement.setInt(1, playerId);
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            return resultSet.getString("prenom");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle any exceptions that may occur
    }
    
    return null; // or an empty string, depending on your requirements
}

public String getPlayerlastName(int playerId) {
    String query = "SELECT nom FROM user WHERE id = ?";
    
    try (PreparedStatement statement = conn.prepareStatement(query)) {
        statement.setInt(1, playerId);
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            return resultSet.getString("nom");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle any exceptions that may occur
    }
    
    return null; // or an empty string, depending on your requirements
}


public List<User> rankPlayersByScore() {
    List<User> players = new ArrayList<>();
    Map<Integer, Double> playerScores = new HashMap<>();

    // Loop through all player IDs
    for (int playerId = 1; playerId <= getMaxPlayerId(); playerId++) {
        // Get the average score for the player
        List<Object[]> playerAverages = moyperformance(playerId);
        if (playerAverages.size() > 0) { // Only rank players who have played in a competition
            double averageScore = (double) playerAverages.get(0)[2];
            playerScores.put(playerId, averageScore);
        }
    }

    // Sort the players by score in descending order
    List<Map.Entry<Integer, Double>> sortedPlayers = new ArrayList<>(playerScores.entrySet());
    sortedPlayers.sort(Map.Entry.<Integer, Double>comparingByValue().reversed());

    // Get the players in order
    for (Map.Entry<Integer, Double> entry : sortedPlayers) {
        int playerId = entry.getKey();
        String playerName = getPlayerName(playerId);
     
         String playerlastName = getPlayerlastName(playerId);
        User player = new User(playerId, playerName,playerlastName);
        players.add(player);
    }

    return players;
}


private int getMaxPlayerId() {
    String query = "SELECT MAX(id) FROM user";
    
    try (PreparedStatement statement = conn.prepareStatement(query)) {
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle any exceptions that may occur
    }
    
    return 0;
}


 public int sumButs() {
        String query = "SELECT SUM(CAST(buts AS INT)) FROM performance_c WHERE buts <> '0'";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0; // or throw an exception, depending on your requirements
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle any exceptions that may occur
            return 0; // or throw an exception, depending on your requirements
        }
    }
    
    public int sumJaune() {
        String query = "SELECT SUM(CAST(jaune AS INT)) FROM performance_c WHERE jaune <> '0'";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0; // or throw an exception, depending on your requirements
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle any exceptions that may occur
            return 0; // or throw an exception, depending on your requirements
        }
    }
    
    public int sumTpM() {
        String query = "SELECT SUM(CAST(tp_m AS INT)) FROM performance_c WHERE tp_m <> '0'";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0; // or throw an exception, depending on your requirements
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle any exceptions that may occur
            return 0; // or throw an exception, depending on your requirements
        }
    }
    
    public int sumRouge() {
        String query = "SELECT SUM(CAST(rouge AS INT)) FROM performance_c WHERE rouge <> '0'";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0; // or throw an exception, depending on your requirements
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle any exceptions that may occur
            return 0; // or throw an exception, depending on your requirements
        }
    }
    
    public int sumPointsDecisives() {
        String query = "SELECT SUM(CAST(points_decisives AS INT)) FROM performance_c WHERE points_decisives <> '0'";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0; // or throw an exception, depending on your requirements
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle any exceptions that may occur
            return 0; // or throw an exception, depending on your requirements
        }
    }
    
   public int sumAeriensG() {
String query = "SELECT SUM(CAST(aeriens_g AS INT)) FROM performance_c WHERE aeriens_g <> '0'";
try (PreparedStatement statement = conn.prepareStatement(query)) {
ResultSet resultSet = statement.executeQuery();
if (resultSet.next()) {
return resultSet.getInt(1);
} else {
return 0; // or throw an exception, depending on your requirements
}
} catch (SQLException ex) {
ex.printStackTrace();
// Handle any exceptions that may occur
return 0; // or throw an exception, depending on your requirements
}
}
   
   
   
   
   ////////////////////counts number prr
   public int countTotalPerformances() {
    String query = "SELECT COUNT(*) AS count FROM performance_c";

    try (PreparedStatement statement = conn.prepareStatement(query)) {
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            return resultSet.getInt("count");
        } else {
            return 0;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        return 0;
    }
}

   
////////////////////average for pie chart 
  public List<Integer> countPerformancesByNoteRange() {
    String query = "SELECT COUNT(*) AS count, "
                 + "CASE "
                 + "WHEN note > 20 THEN 'Note > 20' "
                 + "WHEN note <= 20 AND note > 10 THEN 'Note between 20 and 10' "
                 + "WHEN note <= 10 AND note > 5 THEN 'Note between 10 and 5' "
                 + "ELSE 'Note under 5' "
                 + "END AS note_range "
                 + "FROM performance_c "
                 + "GROUP BY note_range";
    
    List<Integer> counts = new ArrayList<>(4); // initialize with size 4
    
    // add four 0 values to the list to prevent IndexOutOfBoundsException
    counts.add(0);
    counts.add(0);
    counts.add(0);
    counts.add(0);
    
    try (PreparedStatement statement = conn.prepareStatement(query)) {
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            int count = resultSet.getInt("count");
            String noteRange = resultSet.getString("note_range");
            
            switch (noteRange) {
                case "Note > 20":
                    counts.set(0, count);
                    break;
                case "Note between 20 and 10":
                    counts.set(1, count);
                    break;
                case "Note between 10 and 5":
                    counts.set(2, count);
                    break;
                case "Note under 5":
                    counts.set(3, count);
                    break;
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle any exceptions that may occur
    }
    
    return counts;
}

   public List<Object[]> moyPerformanceLastTenCompetitions() {
    String query = "SELECT competition.id, performance_c.apps, performance_c.mins, performance_c.buts, performance_c.points_decisives, performance_c.jaune, performance_c.rouge, performance_c.tp_m, performance_c.pr, performance_c.hd_m "
                 + "FROM performance_c "
                 + "INNER JOIN user ON performance_c.user_id = user.id "
                 + "INNER JOIN competition ON performance_c.competition_p_id = competition.id "
                 + "ORDER BY competition.date DESC "
                 + "LIMIT 10";

    try (PreparedStatement statement = conn.prepareStatement(query)) {
        ResultSet resultSet = statement.executeQuery();
        
        Map<String, List<Double>> scoresByCompetition = new HashMap<>();
        
        while (resultSet.next()) {
            String competitionId = resultSet.getString("competition.id");
            List<Double> scores = scoresByCompetition.computeIfAbsent(competitionId, k -> new ArrayList<>());
            
            double score = (Double.parseDouble(resultSet.getString("performance_c.apps")) * 10)
                         + (Double.parseDouble(resultSet.getString("performance_c.mins")) * 10)
                         + (Double.parseDouble(resultSet.getString("performance_c.buts")) * 20)
                         + (Double.parseDouble(resultSet.getString("performance_c.points_decisives")) * 20)
                         - (Double.parseDouble(resultSet.getString("performance_c.jaune")) * 20)
                         - (Double.parseDouble(resultSet.getString("performance_c.rouge")) * 10)
                         + (Double.parseDouble(resultSet.getString("performance_c.tp_m")) * 10)
                         + (Double.parseDouble(resultSet.getString("performance_c.pr")) * 10)
                         + (Double.parseDouble(resultSet.getString("performance_c.hd_m")) * 10);
            
            scores.add(score);
        }
        
        List<Object[]> result = new ArrayList<>();
        for (Map.Entry<String, List<Double>> entry : scoresByCompetition.entrySet()) {
            String competitionId = entry.getKey();
            List<Double> scores = entry.getValue();
            
            double average = scores.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            
            Object[] row = new Object[2];
            row[0] = competitionId;
            row[1] = average;
            
            result.add(row);
        }
        
        return result;
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle any exceptions that may occur
    }
    
    return null; // or an empty list, depending on your requirements
}


}
