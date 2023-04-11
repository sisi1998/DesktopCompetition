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
import java.util.List;
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

}
