package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private String url;
    private String user;
    private String password;

    public DatabaseManager(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void initializeTables() throws SQLException {
        String createInquirerTableSQL = "CREATE TABLE IF NOT EXISTS INQUIRER (" +
                "id SERIAL PRIMARY KEY," +
                "firstName VARCHAR(50) NOT NULL," +
                "lastName VARCHAR(50)," +
                "phoneNumber VARCHAR(20) NOT NULL" +
                ");";

        String createInquiryLogTableSQL = "CREATE TABLE IF NOT EXISTS INQUIRY_LOG (" +
                "id SERIAL PRIMARY KEY," +
                "inquirer INT NOT NULL," +
                "callDate DATE NOT NULL," +
                "details VARCHAR(500) NOT NULL," +
                "FOREIGN KEY (inquirer) REFERENCES INQUIRER(id) ON UPDATE CASCADE" +
                ");";
        
        String createDisasterVictimTableSQL = "CREATE TABLE IF NOT EXISTS DISASTER_VICTIM (" +
                "id SERIAL PRIMARY KEY," +
                "firstName VARCHAR(50) NOT NULL," +
                "lastName VARCHAR(50)," +
                "phoneNumber VARCHAR(20) NOT NULL" +
                ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createInquirerTableSQL);
            stmt.execute(createInquiryLogTableSQL);
            stmt.execute(createDisasterVictimTableSQL);
        }
    }

    public void addInquirer(Inquirer inquirer) throws SQLException {
        String insertSQL = "INSERT INTO INQUIRER (firstName, lastName, phoneNumber) VALUES (?, ?, ?);";
    
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, inquirer.getFirstName());
            pstmt.setString(2, inquirer.getLastName());
            pstmt.setString(3, inquirer.getPhoneNumber());
            
            // Execute the update once
            int affectedRows = pstmt.executeUpdate();
    
            // Check if the row was inserted and get generated key
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        inquirer.setId(rs.getInt(1)); // Get the auto-generated key and set it to the inquirer object
                    }
                }
            }
        }
    }

    public void addInquirer(DisasterVictim victim) throws SQLException {
        Inquirer inquirer = new Inquirer(victim.getFirstName(), victim.getLastName(), victim.getPhoneNumber());
        addInquirer(inquirer);
    }

    public void logInteraction(int inquirerId, String callDate, String details) throws SQLException {
    String insertSQL = "INSERT INTO INQUIRY_LOG (inquirer, callDate, details) VALUES (?, ?, ?);";

    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
        pstmt.setInt(1, inquirerId);
        pstmt.setDate(2, Date.valueOf(callDate)); // Convert String to SQL Date
        pstmt.setString(3, details);

        pstmt.executeUpdate();
    }
}

    public List<String> getInteractionsForInquirer(int inquirerId) throws SQLException {
        List<String> interactions = new ArrayList<>();
        String querySQL = "SELECT details FROM INQUIRY_LOG WHERE inquirer = ?;";

        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(querySQL)) {
            pstmt.setInt(1, inquirerId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    interactions.add(rs.getString("details"));
                }
            }
        }
        return interactions;
    }

    public Inquirer getInquirerById(int id) throws SQLException {
        Inquirer inquirer = null;
        String querySQL = "SELECT * FROM INQUIRER WHERE id = ?;";
    
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(querySQL)) {
            pstmt.setInt(1, id);
    
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    inquirer = new Inquirer(
                            rs.getInt("id"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("phoneNumber")
                    );
                    List<String> interactions = getInteractionsForInquirer(id);
                    for (String interaction : interactions) {
                        inquirer.addInteractionLog(interaction);
                    }
                }
            }
        }
        return inquirer;
    }

    public DisasterVictim getDisasterVictimByName(String firstName, String lastName) throws SQLException {
    String querySQL = "SELECT * FROM DISASTER_VICTIM WHERE firstName = ? AND lastName = ?";
    DisasterVictim victim = null;

    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(querySQL)) {
        pstmt.setString(1, firstName);
        pstmt.setString(2, lastName);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                // Extract victim details from the result set
                String phoneNumber = rs.getString("phoneNumber");

                // Create a new DisasterVictim object
                victim = new DisasterVictim(firstName, lastName, phoneNumber);
            }
        }
    }
    return victim;
}
}