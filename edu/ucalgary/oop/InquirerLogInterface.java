package edu.ucalgary.oop;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InquirerLogInterface {
    private DatabaseManager databaseManager;

    public InquirerLogInterface(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Inquirer Log Interface!");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Log an inquiry");
            System.out.println("2. View interaction logs for an inquirer");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    logInquiry(scanner);
                    break;
                case 2:
                    viewInteractionLogs(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void logInquiry(Scanner scanner) {
        System.out.println("Enter inquirer details:");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        Inquirer inquirer = new Inquirer(firstName, lastName, phoneNumber);

        System.out.print("Enter inquiry details: ");
        String inquiryDetails = scanner.nextLine();

        try {
            databaseManager.addInquirer(inquirer);
            String callDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); // Get current date
            databaseManager.logInteraction(inquirer.getId(), callDate, inquiryDetails); // Pass callDate parameter
            System.out.println("Inquiry logged successfully!");
        } catch (SQLException e) {
            System.out.println("Error occurred while logging inquiry: " + e.getMessage());
        }
    }

    private void viewInteractionLogs(Scanner scanner) {
        System.out.print("Enter inquirer ID: ");
        int inquirerId = scanner.nextInt();
        scanner.nextLine(); 

        try {
            Inquirer inquirer = databaseManager.getInquirerById(inquirerId);
            if (inquirer != null) {
                System.out.println("Interaction logs for " + inquirer.getFirstName() + " " + inquirer.getLastName() + ":");
                for (String log : inquirer.getInteractionLogs()) {
                    System.out.println("- " + log);
                }
            } else {
                System.out.println("Inquirer not found with ID: " + inquirerId);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while fetching interaction logs: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Initialize DatabaseManager with PostgreSQL database credentials
        DatabaseManager databaseManager = new DatabaseManager("jdbc:postgresql://localhost/ensf380project", "oop", "ucalgary");

        // Create InquirerLogInterface instance and start the interface
        InquirerLogInterface inquirerLogInterface = new InquirerLogInterface(databaseManager);
        inquirerLogInterface.start();
    }
}