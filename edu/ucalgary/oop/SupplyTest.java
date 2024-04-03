package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SupplyTest {

    private Supply supply;

    // This method sets up the test environment before each test case.
    @Before
    public void setUp() {
        supply = new Supply("Water", 100);
    }

    // Test the constructor for proper assignment of properties.
    @Test
    public void testConstructor() {
        assertEquals("Constructor should set type 'Water'.", "Water", supply.getType());
        assertEquals("Constructor should set quantity to 100.", 100, supply.getQuantity());
    }

    // Test getType to ensure it returns the correct type.
    @Test
    public void testGetType() {
        assertEquals("getType should return 'Water'.", "Water", supply.getType());
    }

    // Test setType to ensure it correctly changes the type.
    @Test
    public void testSetType() {
        supply.setType("Food");
        assertEquals("setType should change type to 'Food'.", "Food", supply.getType());
    }

    // Test getQuantity to ensure it returns the correct quantity.
    @Test
    public void testGetQuantity() {
        assertEquals("getQuantity should return 100.", 100, supply.getQuantity());
    }

    // Test setQuantity to ensure it correctly changes the quantity.
    @Test
    public void testSetQuantity() {
        supply.setQuantity(50);
        assertEquals("setQuantity should change quantity to 50.", 50, supply.getQuantity());
    }

    // Edge case: Test setting the quantity to a negative number.
    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativeQuantity() {
        supply.setQuantity(-1);
    }

    // Edge case: Test setting the type to null.
    @Test(expected = IllegalArgumentException.class)
    public void testSetNullType() {
        supply.setType(null);
    }
}
