package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FamilyRelationTest {

    private DisasterVictim personOne;
    private DisasterVictim personTwo;
    private FamilyRelation familyRelation;
    private String relationshipTo = "Sibling";

    @Before
    public void setUp() {
        personOne = new DisasterVictim("Kurt","2024-03-12","523-434-131"); 
        personTwo = new DisasterVictim("Jose","2024-03-12","587-533-222"); 
        familyRelation = new FamilyRelation(personOne, relationshipTo, personTwo);
    }

    // Test constructor
    @Test
    public void testConstructor() {
        assertSame("Constructor should set person one correctly.", personOne, familyRelation.getPersonOne());
        assertEquals("Constructor should set relationship to correctly.", relationshipTo, familyRelation.getRelationshipTo());
        assertSame("Constructor should set person two correctly.", personTwo, familyRelation.getPersonTwo());
    }

    // Test to set the personOne
    @Test
    public void testSetPersonOne() {
        DisasterVictim newPersonOne = new DisasterVictim("Michael","2024-03-13","323-312-111"); 
        familyRelation.setPersonOne(newPersonOne);
        assertSame("Setter should update person one.", newPersonOne, familyRelation.getPersonOne());
    }

    // Test to set the relationship between PersonOne and PersonTwo
    @Test
    public void testSetRelationshipTo() {
        String newRelationshipTo = "Parent";
        familyRelation.setRelationshipTo(newRelationshipTo);
        assertEquals("Setter should update relationship to.", newRelationshipTo, familyRelation.getRelationshipTo());
    }

    // Test to set the personTwo
    @Test
    public void testSetPersonTwo() {
        DisasterVictim newPersonTwo = new DisasterVictim("Colin","2024-03-13","323-771-131"); 
        familyRelation.setPersonTwo(newPersonTwo);
        assertSame("Setter should update person two.", newPersonTwo, familyRelation.getPersonTwo());
    }
}