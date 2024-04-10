package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.List;

public class Inquirer {
    private int id; // Matches the id SERIAL PRIMARY KEY in the database
    private String firstName; // Matches the firstName VARCHAR(50) NOT NULL in the database
    private String lastName; // Matches the lastName VARCHAR(50) in the database
    private String phoneNumber; // Matches the phoneNumber VARCHAR(20) NOT NULL in the database
    private List<String> interactionLogs = new ArrayList<>();

    // Constructor without id, for creating new Inquirer objects before saving to the database
    public Inquirer(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    // Constructor including id, for retrieving Inquirer objects from the database
    public Inquirer(int id, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<String> getInteractionLogs() {
        return interactionLogs;
    }

    public void addInteractionLog(String logDetail) {
        interactionLogs.add(logDetail);
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}