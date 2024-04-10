package edu.ucalgary.oop;

import java.util.Scanner;

public class DisasterVictimInterface {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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
    
        DisasterVictim victim = new DisasterVictim(firstName, entryDate);
        System.out.println("Disaster Victim entered successfully with ID: " + victim.getAssignedSocialID());
    
        // Here, you would add the victim to your storage mechanism (e.g., a list, database)
    }
    

    private static void addFamilyRelationship() {
        System.out.println("Adding a family relationship:");
        // Prompt for details of the relationship (e.g., two victim IDs, relationship type)
        // and create or update the FamilyRelation object(s)
    }

    private static void addMedicalRecord() {
        System.out.println("Adding a medical record:");
    
        DisasterVictim victim = findVictimById(getAssignedSocialID("Enter the victim's ID: "));
    
        Location location = getLocationFromUser();
    
        System.out.print("Enter treatment details: ");
        String treatmentDetails = scanner.nextLine();
    
        String dateOfTreatment;
        while (true) {
            System.out.print("Enter date of treatment (YYYY-MM-DD): ");
            dateOfTreatment = scanner.nextLine();
            if (dateOfTreatment.matches("\\d{4}-\\d{2}-\\d{2}")) {
                break;
            } else {
                System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
            }
        }
    
        MedicalRecord medicalRecord = new MedicalRecord(location, treatmentDetails, dateOfTreatment);
        victim.addMedicalRecord(medicalRecord);
    
        System.out.println("Medical record added successfully.");
    }
    
    private static Location getLocationFromUser() {
        
        System.out.print("Enter location name: ");
        String name = scanner.nextLine();
    
        System.out.print("Enter location address: ");
        String address = scanner.nextLine();
    
        return new Location(name, address);
    }
}

