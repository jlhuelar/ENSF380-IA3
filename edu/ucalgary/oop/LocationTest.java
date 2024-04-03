
package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class LocationTest {

    private Location location;
    private DisasterVictim occupant;
    private Supply supply;

    @Before
    public void setUp() {
        location = new Location("Central Shelter", "123 Main St");
        occupant = new DisasterVictim("Andrei","2024-03-10"); // replace with appropriate constructor
        supply = new Supply("Water", 100);
    }

    // Test for constructor
    @Test
    public void testConstructor() {
        assertEquals("Central Shelter", location.getName());
        assertEquals("123 Main St", location.getAddress());
        assertTrue(location.getOccupants().isEmpty());
        assertTrue(location.getSupplies().isEmpty());
    }

    // Test to set location name
    @Test
    public void testSetName() {
        location.setName("East Shelter");
        assertEquals("East Shelter", location.getName());
    }

    // Test to set location address
    @Test
    public void testSetAddress() {
        location.setAddress("456 Elm St");
        assertEquals("456 Elm St", location.getAddress());
    }

    // Test to add occupant to the location
    @Test
    public void testAddOccupant() {
        location.addOccupant(occupant);
        assertTrue(location.getOccupants().contains(occupant));
        assertEquals(1, location.getOccupants().size());
    }

    // Test to remove occupant from location
    @Test
    public void testRemoveOccupant() {
        location.addOccupant(occupant);
        location.removeOccupant(occupant);
        assertFalse(location.getOccupants().contains(occupant));
        assertTrue(location.getOccupants().isEmpty());
    }

    // Test to add supply to the location
    @Test
    public void testAddSupply() {
        location.addSupply(supply);
        assertTrue(location.getSupplies().contains(supply));
        assertEquals(1, location.getSupplies().size());
    }

    // Test to remove supply from the location
    @Test
    public void testRemoveSupply() {
        location.addSupply(supply);
        location.removeSupply(supply);
        assertFalse(location.getSupplies().contains(supply));
        assertTrue(location.getSupplies().isEmpty());
    }

    // Testing setters for occupants and supplies with external lists
    @Test
    public void testSetOccupants() {
        ArrayList<DisasterVictim> newOccupants = new ArrayList<>();
        newOccupants.add(occupant);
        location.setOccupants(newOccupants);
        assertSame(newOccupants, location.getOccupants());
    }

    @Test
    public void testSetSupplies() {
        ArrayList<Supply> newSupplies = new ArrayList<>();
        newSupplies.add(supply);
        location.setSupplies(newSupplies);
        assertSame(newSupplies, location.getSupplies());
    }
}
