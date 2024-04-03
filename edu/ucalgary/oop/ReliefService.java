package edu.ucalgary.oop;

public class ReliefService {
    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private String dateOfInquiry;
    private String infoProvided;
    private Location lastKnownLocation;

    public ReliefService(Inquirer inquirer, DisasterVictim missingPerson, String dateofInquiry, String infoProvided, Location lastKnownLocation) {
        this.inquirer = inquirer;
        this.missingPerson = missingPerson;
        this.dateOfInquiry = dateofInquiry;
        this.infoProvided = infoProvided;
        this.lastKnownLocation = lastKnownLocation;
    }


    public Inquirer getInquirer() {
        return inquirer;
    }

    public void setInquirer(Inquirer inquirer) {
        this.inquirer = inquirer;
    }

    public DisasterVictim getMissingPerson() {
        return missingPerson;
    }

    public void setMissingPerson(DisasterVictim missingPerson) {
        this.missingPerson = missingPerson;
    }

    public String getDateOfInquiry() {
        return dateOfInquiry;
    }

    public void setDateOfInquiry(String dateOfInquiry) {
       if(!dateOfInquiry.matches("\\d{4}-\\d{2}-\\d{2}")) {
        throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD format.");
       }
       
        this.dateOfInquiry = dateOfInquiry;
    }

    public String getInfoProvided() {
        return infoProvided;
    }

    public void setInfoProvided(String infoProvided) {
        this.infoProvided = infoProvided;
    }

    public Location getLastKnownLocation() {
        return lastKnownLocation;
    }

    public void setLastKnownLocation(Location lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }

    public String getLogDetails() {
        return "Inquirer: " + inquirer.getFirstName() +
               ", Missing Person: " + missingPerson.getFirstName() +
               ", Date of Inquiry: " + dateOfInquiry +
               ", Info Provided: " + infoProvided +
               ", Last Known Location: " + lastKnownLocation.getName();
    }

    public interface ReliefServiceInterface {
        // Constructor-like method (actual implementation will differ)
        void initializeService(Inquirer inquirer, DisasterVictim missingPerson, 
                               String dateOfInquiry, String infoProvided, Location lastKnownLocation);
        
        Inquirer getInquirer();
        void setInquirer(Inquirer inquirer);
        
        DisasterVictim getMissingPerson();
        void setMissingPerson(DisasterVictim missingPerson);
        
        String getDateOfInquiry();
        void setDateOfInquiry(String dateOfInquiry);
        
        String getInfoProvided();
        void setInfoProvided(String infoProvided);
        
        Location getLastKnownLocation();
        void setLastKnownLocation(Location lastKnownLocation);
        
        String getDetails();
    }
}

