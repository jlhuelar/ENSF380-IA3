package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MedicalRecordTest {

    private MedicalRecord medicalRecord;
    private Location location;
    private String treatmentDetails = "Treated for minor injuries";
    private String dateOfTreatment = "2024-03-13"; 

    // Assume that the Location class has an appropriate constructor.
    @Before
    public void setUp() {
        location = new Location("Shelter 777", "460 Punta Tabuc SW"); 
        medicalRecord = new MedicalRecord(location, treatmentDetails, dateOfTreatment);
    }

    // Test the constructor 
    @Test
    public void testConstructor() {
        assertSame("Constructor should set location correctly.", location, medicalRecord.getLocation());
        assertEquals("Constructor should set treatment details correctly.", treatmentDetails, medicalRecord.getTreatmentDetails());
        assertEquals("Constructor should set date of treatment correctly.", dateOfTreatment, medicalRecord.getDateOfTreatment());
    }

    // Test to get location
    @Test
    public void testGetLocation() {
        assertSame("Getter should return the correct location.", location, medicalRecord.getLocation());
    }

    // Test to get treatment details
    @Test
    public void testGetTreatmentDetails() {
        assertEquals("Getter should return the correct treatment details.", treatmentDetails, medicalRecord.getTreatmentDetails());
    }

    // Test to get the date of treatment
    @Test
    public void testGetDateOfTreatment() {
        assertEquals("Getter should return the correct date of treatment.", dateOfTreatment, medicalRecord.getDateOfTreatment());
    }

    // Test to set the location
    @Test
    public void testSetLocation() {
        Location newLocation = new Location("Foothills Medical Centre", "3315 Hospital Dr Nw");
        medicalRecord.setLocation(newLocation);
        assertEquals("Setter should update the location.", newLocation, medicalRecord.getLocation());
    }

    // Test to set treatment details
    @Test
    public void testSetTreatmentDetails() {
        String newTreatmentDetails = "Treated for flu symptoms";
        medicalRecord.setTreatmentDetails(newTreatmentDetails);
        assertEquals("Setter should update the treatment details.", newTreatmentDetails, medicalRecord.getTreatmentDetails());
    }

    // Test to set the date of treatment with valid date
    @Test
    public void testSetDateOfTreatment() {
        String newDateOfTreatment = "2024-04-14";
        medicalRecord.setDateOfTreatment(newDateOfTreatment);
        assertEquals("Setter should update the date of treatment.", newDateOfTreatment, medicalRecord.getDateOfTreatment());
    }

    // Test to set the date of treatment with invalid date
    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfTreatmentWithInvalidFormat() {
        medicalRecord.setDateOfTreatment("14/04/2024");
    }
}

