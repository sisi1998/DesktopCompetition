/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Arena;
import Entities.Competition;
import Entities.Equipe;
import Utils.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.mail.Authenticator;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;

import javax.activation.FileDataSource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;

import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Multipart;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

/**
 *
 * @author Siwar
 */
public class CompetitionService implements Iservice<Competition,Equipe> {
  private Connection conn;
    private static final String[] FEATURES = { "victoires", "defaites", "nuls", "but_marque", "but_encaisses" };
    public CompetitionService(){
    conn=DataSource.getInstance().getCnx();}

    @Override
   public void Add(Competition c, List<Equipe> equipes) {
    String requete="INSERT INTO competition(arena_id,winner_id,date,etat,nom,image,codeqr) VALUES (?, ?, ?, ?, ?, ?,?)";
    String competitionEquipeQuery = "INSERT INTO competition_equipe(competition_id, equipe_id) VALUES (?, ?)";
    try {
        PreparedStatement pst=conn.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1,c.getIdarena().getId());
        pst.setString(3,c.getDate());
        pst.setString(5,c.getNom());
        pst.setString(4,c.getEtat());
        pst.setNull(2, java.sql.Types.INTEGER);
        pst.setString(6,c.getImage());
         pst.setString(7,c.getCodeqr());
        pst.executeUpdate();
        // Retrieve the generated competition ID
        ResultSet generatedKeys = pst.getGeneratedKeys();
        int competitionId = -1;
        if (generatedKeys.next()) {
            competitionId = generatedKeys.getInt(1);
        }

        // Associate the competition with the equipes
        if (competitionId != -1) {
            PreparedStatement competitionEquipeStatement = conn.prepareStatement(competitionEquipeQuery);
            for (Equipe equipe : equipes) {
                competitionEquipeStatement.setInt(1, competitionId);
                competitionEquipeStatement.setInt(2, equipe.getId());
                competitionEquipeStatement.executeUpdate();
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
    
    
    
 @Override
public void Update(Competition c, List<Equipe> equipes, int id) {
    String updateQuery = "UPDATE competition SET arena_id = ?, winner_id = ?, date = ?, etat = ?, nom = ?, image = ? WHERE id = ?";
    //String deleteQuery = "DELETE FROM competition_equipe WHERE competition_id = ?";
    String insertQuery = "INSERT IGNORE INTO competition_equipe(competition_id, equipe_id) VALUES (?, ?)";
    try {
        // Check if the competition object exists in the database
        PreparedStatement checkStatement = conn.prepareStatement("SELECT id FROM competition WHERE id = ?");
        checkStatement.setInt(1, id);
        ResultSet result = checkStatement.executeQuery();
        if (!result.next()) {
            System.out.println("Competition with ID " + c.getId() + " not found in database.");
            return;
        }

        
        
        
        
        
        
        
        
        // Update the competition object
        PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
        updateStatement.setInt(1, c.getIdarena().getId());
        updateStatement.setInt(2, c.getIdwinner().getId());
        updateStatement.setString(3, c.getDate());
        updateStatement.setString(4, c.getEtat());
        updateStatement.setString(5, c.getNom());
        updateStatement.setString(6, c.getImage());
        updateStatement.setInt(7, id);
        updateStatement.executeUpdate();

        // Delete existing associations with equipe objects
       // PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
     //   deleteStatement.setInt(1, c.getId());
       // deleteStatement.executeUpdate();

        // Insert new associations with equipe objects
        PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
        for (Equipe equipe : equipes) {
            insertStatement.setInt(1, c.getId());
            insertStatement.setInt(2, equipe.getId());
            insertStatement.executeUpdate();
        }
    } catch (SQLException ex) {
        Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
    }
}




    @Override
    public void Delete(int id) {
    String competitionEquipeQuery = "DELETE FROM competition_equipe WHERE competition_id = ?";
    String competitionQuery = "DELETE FROM competition WHERE id = ?";
    
    try {
        // Delete the association between the competition and equipes
        PreparedStatement competitionEquipeStatement = conn.prepareStatement(competitionEquipeQuery);
        competitionEquipeStatement.setInt(1, id);
        competitionEquipeStatement.executeUpdate();
        
        // Delete the competition
        PreparedStatement competitionStatement = conn.prepareStatement(competitionQuery);
        competitionStatement.setInt(1, id);
        competitionStatement.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
    }
}


   @Override
public List<Competition> affichage() {
    List<Competition> competitions = new ArrayList<>();
    String query = "SELECT c.id, c.date, a.nom AS arena_nom, c.nom, e.nom AS winner_nom, c.etat, c.image, c.codeqr "
                 + "FROM competition c "
                 + "JOIN arena a ON c.arena_id = a.id "
                 + "LEFT JOIN equipe e ON c.winner_id = e.id";
    try {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String date = resultSet.getString("date");
            String arenaNom = resultSet.getString("arena_nom");
            String nom = resultSet.getString("nom");
            String winnerNom = resultSet.getString("winner_nom");
            String etat = resultSet.getString("etat");
            String image = resultSet.getString("image");
             String codeqr = resultSet.getString("codeqr");
            Competition competition = new Competition(id, date, new Arena(arenaNom), etat, new Equipe(winnerNom), nom, image, codeqr);
            competitions.add(competition);
        }
    } catch (SQLException ex) {
        Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return competitions;
}



    @Override
    public List<Competition> searchCompetition(Competition c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  @Override
public boolean exists(String nom, String date, String arenaNom) {
    String query = "SELECT c.* FROM competition c INNER JOIN arena a ON c.arena_id = a.id WHERE c.nom=? AND c.date=? AND a.nom=?";
    try {
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, nom);
        pst.setString(2, date);
        pst.setString(3, arenaNom);
        ResultSet rs = pst.executeQuery();
        return rs.next(); // Returns true if ResultSet is not empty
    } catch (SQLException ex) {
        Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}


    public void deleteImage(Integer id) {
        String filePath = "C:\\xampp\\htdocs\\imageV\\" + id + ".jpg";
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
}





public boolean exists(String date, int arenaId) {
    String query = "SELECT * FROM competition WHERE date=? AND arena_id=?";
    try {
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, date);
        pst.setInt(2, arenaId);
        ResultSet rs = pst.executeQuery();
        return rs.next(); // Returns true if ResultSet is not empty
    } catch (SQLException ex) {
        Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}



public List<Equipe> getEquipesByCompetitionId(int competitionId) {
    List<Equipe> equipes = new ArrayList<>();

    String query = "SELECT equipe.* FROM equipe " +
                   "INNER JOIN competition_equipe ON equipe.id = competition_equipe.equipe_id " +
                   "WHERE competition_equipe.competition_id = ?";

    try {
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setInt(1, competitionId);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Equipe equipe = new Equipe();
            equipe.setId(rs.getInt("id"));
            equipe.setNom(rs.getString("nom"));
          
            // Set other attributes as needed
            equipes.add(equipe);
        }
    } catch (SQLException ex) {
        Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
    }

    return equipes;
}




public List<Arena> getAllArenas() {
    List<Arena> arenas = new ArrayList<>();

    String query = "SELECT * FROM arena";

    try {
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Arena arena = new Arena();
            arena.setId(rs.getInt("id"));
            arena.setNom(rs.getString("nom"));
          
            // Set other attributes as needed
            arenas.add(arena);
        }
    } catch (SQLException ex) {
        Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
    }

    return arenas;
}

public List<Equipe> getAllEquipes() {
    List<Equipe> equipes = new ArrayList<>();

    String query = "SELECT * FROM equipe";

    try {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            Equipe equipe = new Equipe();
            equipe.setId(rs.getInt("id"));
            equipe.setNom(rs.getString("nom"));
            // Set other attributes as needed
            equipes.add(equipe);
        }
    } catch (SQLException ex) {
        Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
    }

    return equipes;
}
private List<String> getEquipePlayerEmails(int competitionId) throws SQLException {
    String query = "SELECT DISTINCT u.email " +
                   "FROM user u " +
                   "JOIN equipe e ON u.equipe_p_id = e.id " +
                   "JOIN competition_equipe ce ON e.id = ce.equipe_id " +
                   "WHERE ce.competition_id = ?";
    List<String> playerEmails = new ArrayList<>();

    PreparedStatement pst = conn.prepareStatement(query);
    pst.setInt(1, competitionId);
    ResultSet rs = pst.executeQuery();

    while (rs.next()) {
        String playerEmail = rs.getString("email");
        playerEmails.add(playerEmail);
    }

    return playerEmails;
}

private void sendCompetitionReminderEmail(String playerEmail) {
    String to = playerEmail;
    String from = "goacademy66@gmail.com";
    String password = "itqczwigkrkquytz";
    String host = "smtp.gmail.com";

    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", host);
    properties.setProperty("mail.smtp.auth", "true");
    properties.setProperty("mail.smtp.starttls.enable", "true");
    properties.setProperty("mail.smtp.port", "587");

    Session session = Session.getDefaultInstance(properties, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(from, password);
        }
    });

    try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Competition Reminder");

        // Create the message body part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("Dear Player, Your competition is due in one day. Please be prepared.");

        // Create the attachment body part
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        String attachmentFilePath = "C:\\Users\\Siwar\\Documents\\NetBeansProjects\\Pidev\\src\\GUI\\IMG\\342556323_704667194998998_8201126061904933936_n.png";
        FileDataSource source = new FileDataSource(attachmentFilePath);
        attachmentBodyPart.setDataHandler(new DataHandler((javax.activation.DataSource) source));
        attachmentBodyPart.setFileName("picture.jpg");

        // Create the multipart message and add the message and attachment parts to it
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachmentBodyPart);
        message.setContent(multipart);

        Transport.send(message);
    } catch (MessagingException mex) {
        mex.printStackTrace();
    }
}


public void checkAllCompetitionsDueDate() throws ParseException {
    String query = "SELECT id, date FROM competition";
    List<Integer> dueCompetitions = new ArrayList<>();

    try {
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int competitionId = rs.getInt("id");
            String dateString = rs.getString("date");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date competitionDate = format.parse(dateString);

            // calculate the time difference between the competition date and now
            long timeDiffMillis = competitionDate.getTime() - System.currentTimeMillis();
            long timeDiffHours = TimeUnit.MILLISECONDS.toHours(timeDiffMillis);

            if (timeDiffHours <= 24 && timeDiffHours>0) {
                dueCompetitions.add(competitionId);

                // get the email addresses of all players in the equipe associated with the competition
                List<String> playerEmails = getEquipePlayerEmails(competitionId);

                // send an email to each player
                for (String playerEmail : playerEmails) {
                    sendCompetitionReminderEmail(playerEmail);
                }
            }
        }
    } catch (SQLException | ParseException ex) {
        Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
    }

    if (dueCompetitions.size() == 0) {
        System.out.println("No competitions are due in the next 24 hours.");
    } else {
        System.out.println("The following competitions are due in the next 24 hours: " + dueCompetitions);
    }
}

public Competition getCompetitionByCriteria(String nom, String date, String arenaNom) {
    Competition competition = null;
    String query = "SELECT c.id, c.date, a.nom AS arena_nom, c.nom, e.nom AS winner_nom, c.etat, c.image, c.codeqr "
                 + "FROM competition c "
                 + "JOIN arena a ON c.arena_id = a.id "
                 + "LEFT JOIN equipe e ON c.winner_id = e.id "
                 + "WHERE c.nom LIKE ? AND c.date LIKE ? AND a.nom LIKE ?";
    try (PreparedStatement statement = conn.prepareStatement(query)) {
        statement.setString(1, "%" + nom + "%");
        statement.setString(2, date.substring(0, 10) + "%");
        statement.setString(3, "%" + arenaNom + "%");
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String competitionDate = resultSet.getString("date");
            String competitionArenaNom = resultSet.getString("arena_nom");
            String competitionNom = resultSet.getString("nom");
            String winnerNom = resultSet.getString("winner_nom");
            String etat = resultSet.getString("etat");
            String image = resultSet.getString("image");
            String codeqr = resultSet.getString("codeqr");
            Arena arena = new Arena(competitionArenaNom);
            Equipe winner = new Equipe(winnerNom);
            competition = new Competition(id, competitionDate, arena, etat, winner, competitionNom, image, codeqr);
        }
    } catch (SQLException ex) {
        Logger.getLogger(CompetitionService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return competition;
}
public List<Object[]> moyEquipe(int equipeId) {
    String query = "SELECT e.id, AVG(p.but_marque + p.but_encaisses - p.nuls - p.defaites) as moyenne "
                 + "FROM equipe AS e "
                 + "JOIN performance_equipe AS p ON e.performance_e_id = p.id "
                 + "WHERE e.id = ?";
    
    try (PreparedStatement statement = conn.prepareStatement(query)) {
        statement.setInt(1, equipeId);
        ResultSet resultSet = statement.executeQuery();
        
        List<Object[]> result = new ArrayList<>();
        while (resultSet.next()) {
            Object[] row = new Object[2];
            row[0] = resultSet.getInt("e.id");
            row[1] = resultSet.getDouble("moyenne");
            result.add(row);
        }
        
        return result;
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle any exceptions that may occur
    }
    
    return null; // or an empty list, depending on your requirements
}
///////////////////////////////favoredequipe
public Equipe getFavoredEquipe(int competitionId) {
    List<Equipe> equipes = getEquipesByCompetitionId(competitionId);
    Equipe favoredEquipe = null;
    double highestAverage = Double.NEGATIVE_INFINITY;
    
    for (Equipe equipe : equipes) {
        List<Object[]> moyEquipeResult = moyEquipe(equipe.getId());
        
        if (!moyEquipeResult.isEmpty()) {
            double average = (double) moyEquipeResult.get(0)[1];
            
            if (average > highestAverage) {
                favoredEquipe = equipe;
                highestAverage = average;
            }
        }
    }
    
    return favoredEquipe;
}
/////////////////////////////////////fevoredEquipes
public List<Equipe> getFavoredEquipes(int competitionId) {
    List<Equipe> equipes = getEquipesByCompetitionId(competitionId);
    Map<Equipe, Double> averageScores = new HashMap<>();
    
    for (Equipe equipe : equipes) {
        List<Object[]> moyEquipeResult = moyEquipe(equipe.getId());
        
        if (!moyEquipeResult.isEmpty()) {
            double average = (double) moyEquipeResult.get(0)[1];
            averageScores.put(equipe, average);
        }
    }
    
    List<Map.Entry<Equipe, Double>> sortedEntries = new ArrayList<>(averageScores.entrySet());
    sortedEntries.sort(Map.Entry.<Equipe, Double>comparingByValue().reversed());
    
    List<Equipe> favoredEquipes = new ArrayList<>();
    for (Map.Entry<Equipe, Double> entry : sortedEntries) {
        favoredEquipes.add(entry.getKey());
    }
    
    return favoredEquipes;
}
/////////////////favored list
public List<Equipe> getFavoredEquipesForAllCompetitions() {
    List<Equipe> favoredEquipes = new ArrayList<>();
    String query = "SELECT id FROM competition";
    
    try (PreparedStatement statement = conn.prepareStatement(query)) {
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            int competitionId = resultSet.getInt("id");
            Equipe favoredEquipe = getFavoredEquipe(competitionId);
            
            if (favoredEquipe != null) {
                favoredEquipes.add(favoredEquipe);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle any exceptions that may occur
    }
    
    return favoredEquipes;
}
;
 
}
