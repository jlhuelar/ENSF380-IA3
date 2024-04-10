package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.Arrays;

public class DisasterVictim {
    private int age;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private int approximateAge;
    private String gender;
    private String comments;
    protected int Assigned_Social_ID;
    private ArrayList<MedicalRecord> medicalRecords;
    private ArrayList<FamilyRelation> familyRelations;
    private String ENTRY_DATE;
    private ArrayList<Supply> personalBelongings;
    private static int counter = 0;
    private ArrayList<DietaryRestrictions> dietaryRestrictions;

    public DisasterVictim(String firstName, String ENTRY_DATE) {
        this.firstName = firstName;
        if (!ENTRY_DATE.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Invalid date format. Please use xxxx-xx-xx format.");
        }
            else this.ENTRY_DATE = ENTRY_DATE;
        this.Assigned_Social_ID = 1 + counter;
        counter++;
        this.personalBelongings = new ArrayList<>();
        this.familyRelations = new ArrayList<>();
        this.dietaryRestrictions = new ArrayList<>();

    }

    public String getFirstName() {
        return firstName;
    }

    public String getEntryDate() {
        return ENTRY_DATE;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getComments() {
        return comments;
    }

    public int getAssignedSocialID() {
        return Assigned_Social_ID;
    }

    public MedicalRecord[] getMedicalRecords() {
        return medicalRecords.toArray(new MedicalRecord[medicalRecords.size()]);
    }

    public FamilyRelation[] getFamilyConnections() {
        return familyRelations.toArray(new FamilyRelation[familyRelations.size()]);
    }

    public Supply[] getPersonalBelongings() {
        return personalBelongings.toArray(new Supply[personalBelongings.size()]);
    }

    public int getApproximateAge() {
        return approximateAge;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        if (!dateOfBirth.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD format.");
        }
        this.dateOfBirth = dateOfBirth;
        this.approximateAge = 0; // Ensure only one field is set at a time
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setMedicalRecords(MedicalRecord[] medicalRecords) {
        this.medicalRecords = new ArrayList<>(Arrays.asList(medicalRecords)); 
    }

    public void setFamilyConnections(FamilyRelation[] familyRelations) {
        this.familyRelations = new ArrayList<>(Arrays.asList(familyRelations));
    }

    public void setPersonalBelongings(Supply[] personalBelongings) {
        this.personalBelongings = new ArrayList<>(Arrays.asList(personalBelongings));
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void addPersonalBelonging(Supply supply) {
        // Checks list of supplies to see if the supply is already in the list
        for (Supply existingSupply : personalBelongings) {
            if (existingSupply.equals(supply)) {
                existingSupply.setQuantity(existingSupply.getQuantity() + supply.getQuantity());
                return;
            }
        }
        // If the supply is not in the list, it adds the supply to the list
        this.personalBelongings.add(supply);
    }

    public void removePersonalBelonging(Supply supply) {
        //checks list of supplies to see if the supply is in the list and if it is, it removes the supply from the list
        for (int i = 0; i < personalBelongings.size(); i++) {
            if (personalBelongings.get(i) == supply) {
                personalBelongings.remove(i);
                return;
            }
        }
        //if the supply is not in the list, it throws an exception
        throw new IllegalArgumentException("Supply not found");
    }

    public void addFamilyConnection(FamilyRelation familyRelation) {
        // Add a family relation to the familyConnections array
        this.familyRelations.add(familyRelation);
    }

    public void addMedicalRecord(MedicalRecord medicalRecord) {
        // Add a medical record to the medicalRecords array
        this.medicalRecords.add(medicalRecord);
    }

    public void removeFamilyConnection(FamilyRelation familyRelation) {
        // Remove a family relation from the familyConnections array
        this.familyRelations.remove(familyRelation);
    }

    public enum DietaryRestrictions {
        AVML, DBML, GFML, KSML, LSML, MOML, PFML, VGML, VJML
    }

    public int getAge() {
        return age;
    }

    public void setApproximateAge(int approximateAge) {
        if (approximateAge < 0) {
            throw new IllegalArgumentException("Approximate age cannot be negative.");
        }
        this.approximateAge = approximateAge;
        this.dateOfBirth = null; // Ensure only one field is set at a time
    }

    // Method to set the age
    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
        this.age = age;
    }

    // Method to add a dietary restriction
    public void addDietaryRestriction(DietaryRestrictions restriction) {
        this.dietaryRestrictions.add(restriction);
    }

    // Method to remove a dietary restriction
    public void removeDietaryRestriction(DietaryRestrictions restriction) {
        if (!this.dietaryRestrictions.remove(restriction)) {
            throw new IllegalArgumentException("Dietary restriction not found.");
        }
    }

    // Getter for dietary restrictions
    public DietaryRestrictions[] getDietaryRestrictions() {
        return this.dietaryRestrictions.toArray(new DietaryRestrictions[0]);
    }
}
