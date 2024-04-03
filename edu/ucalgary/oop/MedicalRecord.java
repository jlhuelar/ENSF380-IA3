package edu.ucalgary.oop;

public class MedicalRecord {
    private Location location;
    private String treatmentDetails;
    private String dateOfTreatment;

    private void checkDateOfTreatment(String dateOfTreatment) throws IllegalArgumentException {
        if (!dateOfTreatment.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Invalid date format. Please use XXXX-XX-XX format.");
        }
    }

    public MedicalRecord (Location location, String treatmentDetails, String dateOfTreatment) throws IllegalArgumentException{
        this.location = location;
        this.treatmentDetails = treatmentDetails;
        checkDateOfTreatment(dateOfTreatment);
        this.dateOfTreatment = dateOfTreatment;
    }



    public Location getLocation() {return this.location;}
    public String getTreatmentDetails() {return this.treatmentDetails;}
    public String getDateOfTreatment() {return this.dateOfTreatment;}
    public void setLocation(Location location) {
        this.location = location;
    }
    public void setTreatmentDetails(String treatmentDetails) {
        this.treatmentDetails = treatmentDetails;
    }
    public void setDateOfTreatment(String dateOfTreatment) throws IllegalArgumentException{
        checkDateOfTreatment(dateOfTreatment);
        try {
            this.dateOfTreatment = dateOfTreatment;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Please enter a String");
        }
    }
}