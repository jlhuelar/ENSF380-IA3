package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class DisasterVictimTest {

    private DisasterVictim victim;
    private final String validDate = "2024-03-14";

    @Before
    public void setUp() {
        victim = new DisasterVictim("Johnny", validDate);
    }

    // Test constructor with valid entry date
    @Test
    public void testConstructor() {
        assertEquals("The constructor should set the first name correctly.", "Johnny", victim.getFirstName());
        assertEquals("The constructor should set the entry date correctly.", validDate, victim.getEntryDate());
    }

    // Test constructor with invalid entry date
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidDate() {
        new DisasterVictim("Jane", "01-01-2021");
    }

    // Test to get first name
    @Test
    public void testGetFirstName() {
        assertEquals("Johnny", victim.getFirstName());
    }

    // Test to get last name
    @Test
    public void testGetLastName() {
        victim.setLastName("Doe"); // We must set it first since it is not set in the constructor
        assertEquals("Doe", victim.getLastName());
    }

    // Test to get the date of birth
    @Test
    public void testGetDateOfBirth() {
        String birthDate = "1990-05-21";
        victim.setDateOfBirth(birthDate);
        assertEquals(birthDate, victim.getDateOfBirth());
    }

    // Test to get the gender
    @Test
    public void testGetGender() {
        String gender = "Male";
        victim.setGender(gender);
        assertEquals(gender, victim.getGender());
    }

    // Test to get comments
    @Test
    public void testGetComments() {
        String comments = "No known allergies";
        victim.setComments(comments);
        assertEquals(comments, victim.getComments());
    }

    // Test to get the unique and assigned social ID of a person
    @Test
    public void testGetAssignedSocialID() {
        // ID is set in the constructor and auto-increments for every instance
        int id = victim.getAssignedSocialID();
        assertTrue(id > 0); // Assuming the IDs start from 1
    }

    // Test to set the first name
    @Test
    public void testSetFirstName() {
        String newFirstName = "Jane";
        victim.setFirstName(newFirstName);
        assertEquals(newFirstName, victim.getFirstName());
    }

    // Test to set the last name
    @Test
    public void testSetLastName() {
        String newLastName = "Smith";
        victim.setLastName(newLastName);
        assertEquals(newLastName, victim.getLastName());
    }

    // Test to set a valid date of birth
    @Test
    public void testSetDateOfBirthWithValidDate() {
        String newDateOfBirth = "1990-05-21";
        victim.setDateOfBirth(newDateOfBirth);
        assertEquals(newDateOfBirth, victim.getDateOfBirth());
    }

    // Test to set an invalid date of birth
    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthWithInvalidDate() {
        victim.setDateOfBirth("21-05-1990");
    }

    // Test to set gender
    @Test
    public void testSetGender() {
        String newGender = "Female";
        victim.setGender(newGender);
        assertEquals(newGender, victim.getGender());
    }

    // Test to set comments
    @Test
    public void testSetComments() {
        String newComments = "Allergic to penicillin";
        victim.setComments(newComments);
        assertEquals(newComments, victim.getComments());
    }

    // Test to set and get valid age
    @Test
    public void testAgeSetterGetter() {
        victim.setAge(30);
        assertEquals(30, victim.getAge());
    }

    // Test to set and get invalid age
    @Test(expected = IllegalArgumentException.class)
    public void testAgeSetterWithNegative() {
        victim.setAge(-1);
    }

    // Test to add dietary restrictions
    @Test
    public void testAddDietaryRestriction() {
        victim.addDietaryRestriction(DisasterVictim.DietaryRestrictions.GFML);
        assertTrue(Arrays.asList(victim.getDietaryRestrictions()).contains(DisasterVictim.DietaryRestrictions.GFML));
    }

    // Test to remove dietary restrictions that exists in the enumurmation
    @Test
    public void testRemoveDietaryRestriction() {
        victim.addDietaryRestriction(DisasterVictim.DietaryRestrictions.KSML);
        victim.removeDietaryRestriction(DisasterVictim.DietaryRestrictions.KSML);
        assertFalse(Arrays.asList(victim.getDietaryRestrictions()).contains(DisasterVictim.DietaryRestrictions.KSML));
    }

    // Test to remove dietary restrictions that is not existing in the enumuration
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingDietaryRestriction() {
        victim.removeDietaryRestriction(DisasterVictim.DietaryRestrictions.VJML);
    }

    // Test that family relationships are consistent between two DisasterVictims
    @Test
    public void testConsistentFamilyRelationship() {
        DisasterVictim sibling = new DisasterVictim("Sam", validDate);
        familyRelation = new FamilyRelation(victim, "sibling", sibling);
        
        victim.addFamilyConnection(familyRelation);
        sibling.addFamilyConnection(familyRelation); 
        
        assertTrue(victim.getFamilyConnections().contains(familyRelation));
        assertTrue(sibling.getFamilyConnections().contains(familyRelation));
    }

    // Test that altering a family relationship affects both DisasterVictims
    @Test
    public void testAlteringFamilyRelationship() {
        DisasterVictim sibling = new DisasterVictim("Sam", validDate);
        FamilyRelation familyRelation = new FamilyRelation(victim, "sibling", sibling);
        
        victim.addFamilyConnection(familyRelation);
        sibling.addFamilyConnection(familyRelation);

        victim.removeFamilyConnection(familyRelation);
        
        assertFalse(victim.getFamilyConnections().contains(familyRelation));
        assertFalse(sibling.getFamilyConnections().contains(familyRelation)); 
    }

    // Test that genderoptions are read from the txt file and verify if the gender option is valid
    @Test
    public void testGenderOptionsFromFile() throws IOException {
        String genderFileName = "GenderOptions.txt"; 
        List<String> genderOptions = Files.readAllLines(Paths.get(genderFileName));

        String validGenderOption = genderOptions.get(0); 
        victim.setGender(validGenderOption);

        assertTrue("Gender should be one of the options from the file",
                genderOptions.contains(victim.getGender()));
    }

}

