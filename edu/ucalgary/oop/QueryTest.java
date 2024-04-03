package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueryTest {
    
    private Query query;
    private DisasterVictim victim;

    @Before
    public void setUp() {
        victim = new DisasterVictim("John", "2021-01-01");
        query = new Query(1, "Inquiry about available shelters", victim);
    }

    // Test to create a query
    @Test
    public void testCreateQuery() {
        assertEquals("Query ID should be set to 1.", 1, query.getQueryID());
        assertEquals("Description should match constructor argument.", "Inquiry about available shelters", query.getDescription());
        assertSame("Victim should be the same as in constructor argument.", victim, query.getVictim());
        assertNull("Initial response should be null.", query.getResponse());
    }

    // Test to update a query description
    @Test
    public void testUpdateDescription() {
        String newDescription = "Inquiry about available food supplies";
        query.updateDescription(newDescription);
        assertEquals("Description should be updated.", newDescription, query.getDescription());
    }

    // Test to set a query response
    @Test
    public void testSetResponse() {
        String response = "3 shelters available";
        query.setResponse(response);
        assertEquals("Response should be set.", response, query.getResponse());
    }
}
