package edu.ucalgary.oop;

import java.util.Scanner;
import edu.ucalgary.oop.DatabaseManager;
import edu.ucalgary.oop.DisasterVictimInterface;
import edu.ucalgary.oop.InquirerInterface;
import edu.ucalgary.oop.InquirerLogInterface;

/**
* @author Jericho Huelar
*/


public class MainMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static DatabaseManager dbManager = new DatabaseManager(
            "jdbc:postgresql://localhost/ensf380project", "oop", "password");

    public static void main(String[] args) {
        System.out.println("Welcome to the Disaster Management System");

        while (true) {
            System.out.println("\nMain Menu: Please choose an option:");
            System.out.println("1. Disaster Victim Management");
            System.out.println("2. Inquirer Management");
            System.out.println("3. Inquiry Log Management");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    DisasterVictimInterface.main(new String[0]);
                    break;
                case 2:
                    InquirerInterface.main(new String[0]);
                    break;
                case 3:
                    InquirerLogInterface.main(new String[0]);
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }
        }
    }
}
