package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReliefServiceTest {

    private ReliefService reliefService;
    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private Location lastKnownLocation;
    private String dateOfInquiry = "2024-03-13";
    private String infoProvided = "Looking for John Brown";

    // Assume that Inquirer, DisasterVictim, and Location classes have appropriate constructors
    @Before
    public void setUp() {
        inquirer = new Inquirer("Alex", "Brown", "555-0101", "AlexBrown@icloud.com");
        missingPerson = new DisasterVictim("John", "2024-03-12"); 
        lastKnownLocation = new Location("Saddledome","555 Saddledome Rise Se"); 
        reliefService = new ReliefService(inquirer, missingPerson, dateOfInquiry, infoProvided, lastKnownLocation);
    }

    // Test to get inquirer
    @Test
    public void testGetInquirer() {
        assertEquals("Getter should return the correct inquirer.", inquirer, reliefService.getInquirer());
    }

    // Test to get the missing person
    @Test
    public void testGetMissingPerson() {
        assertEquals("Getter should return the correct missing person.", missingPerson, reliefService.getMissingPerson());
    }

    // Test to get the date of inquiry
    @Test
    public void testGetDateOfInquiry() {
        assertEquals("Getter should return the correct date of inquiry.", dateOfInquiry, reliefService.getDateOfInquiry());
    }

    // Test to get info provided
    @Test
    public void testGetInfoProvided() {
        assertEquals("Getter should return the correct info provided.", infoProvided, reliefService.getInfoProvided());
    }

    // Test to get the last known location
    @Test
    public void testGetLastKnownLocation() {
        assertEquals("Getter should return the correct last known location.", lastKnownLocation, reliefService.getLastKnownLocation());
    }

    // Test to set inquirer
    @Test
    public void testSetInquirer() {
        Inquirer newInquirer = new Inquirer("Liam", "Doman", "555-0202", "LiamD@gmail.com");
        reliefService.setInquirer(newInquirer);
        assertEquals("Setter should update the inquirer.", newInquirer, reliefService.getInquirer());
    }

    // Test to set missing person
    @Test
    public void testSetMissingPerson() {
        DisasterVictim newMissingPerson = new DisasterVictim("Alejandro", "2024-03-10"); 
        reliefService.setMissingPerson(newMissingPerson);
        assertEquals("Setter should update the missing person.", newMissingPerson, reliefService.getMissingPerson());
    }

    // Test setting a valid date of inquiry
    @Test
    public void testSetValidDateOfInquiry() {
        String newValidDateOfInquiry = "2024-12-01";
        try {
            reliefService.setDateOfInquiry(newValidDateOfInquiry);
            assertEquals("Setter should update the date of inquiry with a valid format.", newValidDateOfInquiry, reliefService.getDateOfInquiry());
        } catch (IllegalArgumentException e) {
            fail("Setting a valid date of inquiry should not throw an IllegalArgumentException.");
        }
    }

    // Test setting an invalid date of inquiry
    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidDateOfInquiry() {
        String newInvalidDateOfInquiry = "01-12-2024"; // This assumes that the date format should be "YYYY-MM-DD"
        reliefService.setDateOfInquiry(newInvalidDateOfInquiry); // This should throw an IllegalArgumentException
    }

    // Test to set info provided
    @Test
    public void testSetInfoProvided() {
        String newInfoProvided = "Seeking Jane Doe";
        reliefService.setInfoProvided(newInfoProvided);
        assertEquals("Setter should update the info provided.", newInfoProvided, reliefService.getInfoProvided());
    }

    // Test to set last known location
    @Test
    public void testSetLastKnownLocation() {
        Location newLastKnownLocation = new Location("University Of Calgary", "376 Collegiate Blvd Nw"); 
        reliefService.setLastKnownLocation(newLastKnownLocation);
        assertEquals("Setter should update the last known location.", newLastKnownLocation, reliefService.getLastKnownLocation());
    }
}

