package edu.ucalgary.oop;

import java.util.Scanner;
import java.util.List;

public class InquirerInterface {

    private static Scanner scanner = new Scanner(System.in);
    private static DatabaseManager dbManager; // Assume this has been properly initialized

    public static void main(String[] args) {
        dbManager = new DatabaseManager(
                "jdbc:postgresql://localhost/ensf380project", "oop", "password");

        System.out.println("Welcome to the Inquirer Management System");

        while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Log new interaction with an inquirer");
            System.out.println("2. View inquirer interactions");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    logInteraction();
                    break;
                case 2:
                    viewInteractions();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void logInteraction() {
        System.out.println("Enter the ID of the inquirer:");
        int inquirerId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter details of the interaction:");
        String details = scanner.nextLine();

       
        String currentDate = java.time.LocalDate.now().toString();

        try {
            dbManager.logInteraction(inquirerId, currentDate, details);
            System.out.println("Interaction logged successfully.");
        } catch (Exception e) {
            System.out.println("Error logging interaction: " + e.getMessage());
        }
    }

    private static void viewInteractions() {
        System.out.println("Enter the ID of the inquirer to view interactions:");
        int inquirerId = scanner.nextInt();
        scanner.nextLine();

        try {
            List<String> interactions = dbManager.getInteractionsForInquirer(inquirerId);
            System.out.println("Interactions for Inquirer ID " + inquirerId + ":");
            for (String interaction : interactions) {
                System.out.println(interaction);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving interactions: " + e.getMessage());
        }
    }
}