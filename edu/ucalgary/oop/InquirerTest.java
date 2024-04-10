package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InquirerTest {

    private Inquirer inquirer;

    @Before
    public void setUp() {
        inquirer = new Inquirer("John", "Doe", "555-0102");
    }

    // Test for constructor
    @Test
    public void testConstructor() {
        assertEquals("John", inquirer.getFirstName());
        assertEquals("Doe", inquirer.getLastName());
        assertEquals("555-0102", inquirer.getPhoneNumber());
    }

    // Test to get first name
    @Test
    public void testGetFirstName() {
        assertEquals("Getter for first name should return 'John'.", "John", inquirer.getFirstName());
    }

    // Test to get last name
    @Test
    public void testGetLastName() {
        assertEquals("Getter for last name should return 'Doe'.", "Doe", inquirer.getLastName());
    }

    // Test to get services phone number
    @Test
    public void testGetServicesPhoneNum() {
        assertEquals("Getter for services phone number should return '555-0102'.", "555-0102", inquirer.getPhoneNumber());
    }

}
