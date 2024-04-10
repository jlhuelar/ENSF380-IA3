package edu.ucalgary.oop;

import java.util.Scanner;
import java.sql.SQLException;


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
        
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine(); // Add this line to get the phone number
    
        try {
            DisasterVictim victim = new DisasterVictim(firstName, entryDate, phoneNumber); // Pass all required parameters
            databaseManager.addInquirer(victim);
            System.out.println("Disaster Victim entered successfully with ID: " + victim.getAssignedSocialID());
        } catch (SQLException e) {
            System.out.println("Error adding Disaster Victim: " + e.getMessage());
        }
    }

    private static void addFamilyRelationship() {
        System.out.println("Adding a family relationship:");
    
        try {
            // Prompt for details of the relationship (e.g., two victim IDs, relationship type)
            // and create or update the FamilyRelation object(s)
            System.out.print("Enter first person's name: ");
            String firstName1 = scanner.nextLine();
            System.out.print("Enter first person's last name: ");
            String lastName1 = scanner.nextLine();
            System.out.print("Enter relationship to: ");
            String relationship = scanner.nextLine();
            System.out.print("Enter second person's name: ");
            String firstName2 = scanner.nextLine();
            System.out.print("Enter second person's last name: ");
            String lastName2 = scanner.nextLine();
    
            DisasterVictim person1 = databaseManager.getDisasterVictimByName(firstName1, lastName1);
            DisasterVictim person2 = databaseManager.getDisasterVictimByName(firstName2, lastName2);
            
            if (person1 == null) {
                System.out.println("First person not found in the database.");
                return;
            }
    
            if (person2 == null) {
                System.out.println("Second person not found in the database.");
                return;
            }

            FamilyRelation familyRelation = new FamilyRelation(person1, relationship, person2); // This line is causing the NullPointerException
    
            // Add the family relation to the respective persons
            person1.addFamilyConnection(familyRelation);
            person2.addFamilyConnection(familyRelation);
    
            System.out.println("Family relationship added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding family relationship: " + e.getMessage());
        }
    }

    private static void addMedicalRecord() {
        System.out.println("Adding a medical record:");
    
        try {
            // Prompt for victim's details to select the victim
            System.out.print("Enter victim's first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter victim's last name: ");
            String lastName = scanner.nextLine();
    
            // Retrieve the victim from the database
            DisasterVictim victim = databaseManager.getDisasterVictimByName(firstName, lastName);
    
            if (victim == null) {
                System.out.println("Victim not found in the database.");
                return;
            }
    
            // Prompt for details of the medical record
            System.out.print("Enter location name: ");
            String locationName = scanner.nextLine();
            System.out.print("Enter location address: ");
            String locationAddress = scanner.nextLine();
            System.out.print("Enter treatment details: ");
            String treatmentDetails = scanner.nextLine();
            System.out.print("Enter date of treatment (YYYY-MM-DD): ");
            String dateOfTreatment = scanner.nextLine();
    
            // Create a Location object with the provided location details
            Location medicalLocation = new Location(locationName, locationAddress);
    
            // Create a MedicalRecord object with the provided details
            MedicalRecord medicalRecord = new MedicalRecord(medicalLocation, treatmentDetails, dateOfTreatment);
    
            // Add the medical record to the victim's list of medical records
            victim.addMedicalRecord(medicalRecord);
    
            System.out.println("Medical record added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding medical record: " + e.getMessage());
        }
    }
}
