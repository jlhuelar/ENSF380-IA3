package edu.ucalgary.oop;

import java.sql.SQLException;

public class DatabaseManagerTest {

    public static void main(String[] args) {
        try {
            DatabaseManager dbManager = new DatabaseManager(
                    "jdbc:postgresql://localhost/ensf380project", "oop", "ucalgary");

            // Initialize tables
            dbManager.initializeTables();

            // Add an inquirer
            Inquirer newInquirer = new Inquirer("Test", "User", "123-456-7890");
            dbManager.addInquirer(newInquirer);

            // Log an interaction
            dbManager.logInteraction(newInquirer.getId(), "2023-04-05", "Initial inquiry logged");

            // Retrieve and print inquirer details and interactions
            Inquirer retrievedInquirer = dbManager.getInquirerById(newInquirer.getId());
            System.out.println("Retrieved Inquirer: " + retrievedInquirer);
            for (String log : retrievedInquirer.getInteractionLogs()) {
                System.out.println("Interaction Log: " + log);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}