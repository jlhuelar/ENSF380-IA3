package edu.ucalgary.oop;

import java.util.Scanner;
import java.sql.SQLException;
import edu.ucalgary.oop.DisasterVictim;

public class DisasterVictimInterface {

    private static Scanner scanner = new Scanner(System.in);
    private static DatabaseManager databaseManager = new DatabaseManager("jdbc:postgresql://localhost/ensf380project", "oop", "ucalgary");

    public static void main(String[] args) {
        try {
            databaseManager.initializeTables();
        } catch (SQLException e) {
            System.out.println("Error initializing database tables: " + e.getMessage());
            return;
        }

        System.out.println("Welcome to the Disaster Victim Entry System");

        while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Enter new Disaster Victim");
            System.out.println("2. Add family relationship");
            System.out.println("3. Add medical record");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    enterDisasterVictim();
                    break;
                case 2:
                    addFamilyRelationship();
                    break;
                case 3:
                    addMedicalRecord();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void enterDisasterVictim() {
        System.out.println("Entering a new Disaster Victim:");

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        String entryDate;
        while (true) {
            System.out.print("Enter entry date (YYYY-MM-DD): ");
            entryDate = scanner.nextLine();
            if (entryDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                break;
            } else {
                System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
            }
        }

        try {
            DisasterVictim victim = new DisasterVictim(firstName, entryDate);
            databaseManager.addInquirer(victim); // Assuming you have an addDisasterVictim method in DatabaseManager
            System.out.println("Disaster Victim entered successfully with ID: " + victim.getAssignedSocialID());
        } catch (SQLException e) {
            System.out.println("Error adding Disaster Victim: " + e.getMessage());
        }
    }

    private static void addFamilyRelationship() {
        System.out.println("Adding a family relationship:");
        // Prompt for details of the relationship (e.g., two victim IDs, relationship type)
        // and create or update the FamilyRelation object(s)
    }

    private static void addMedicalRecord() {
        System.out.println("Adding a medical record:");

        // Implement this method to add a medical record for a victim
    }
}
