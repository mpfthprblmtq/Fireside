/**
 * File:    Signage.java    
 * Author:  Pat Ripley
 * 
 * Program Description:    
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 * 
 * Class Description:
 * Signage object class.
 * 
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// class Signage
public class Signage {
    
    // ivars
    private int entrance_score;
    private String entrance_notes;
    
    private int office_score;
    private String office_notes;
    
    private int street_score;
    private String street_notes;
    
    private int parking_score;
    private String parking_notes;
    
    private int handicapparking_score;
    private String handicapparking_notes;
    
    private int pool_score;
    private String pool_notes;
    
    private int adamarking_score;
    private String adamarking_notes;
    
    private int dogpark_score;
    private String dogpark_notes;
    
    private int dumpster_score;
    private String dumpster_notes;
    
    private int firelane_score;
    private String firelane_notes;
    
    /**
     * Default constructor
     */
    public Signage() {
        // empty
    }

    /**
     * @return the entrance_score
     */
    public int getEntrance_score() {
        return entrance_score;
    }

    /**
     * @param entrance_score the entrance_score to set
     */
    public void setEntrance_score(int entrance_score) {
        this.entrance_score = entrance_score;
    }

    /**
     * @return the entrance_notes
     */
    public String getEntrance_notes() {
        return entrance_notes;
    }

    /**
     * @param entrance_notes the entrance_notes to set
     */
    public void setEntrance_notes(String entrance_notes) {
        this.entrance_notes = entrance_notes;
    }

    /**
     * @return the office_score
     */
    public int getOffice_score() {
        return office_score;
    }

    /**
     * @param office_score the office_score to set
     */
    public void setOffice_score(int office_score) {
        this.office_score = office_score;
    }

    /**
     * @return the office_notes
     */
    public String getOffice_notes() {
        return office_notes;
    }

    /**
     * @param office_notes the office_notes to set
     */
    public void setOffice_notes(String office_notes) {
        this.office_notes = office_notes;
    }

    /**
     * @return the street_score
     */
    public int getStreet_score() {
        return street_score;
    }

    /**
     * @param street_score the street_score to set
     */
    public void setStreet_score(int street_score) {
        this.street_score = street_score;
    }

    /**
     * @return the street_notes
     */
    public String getStreet_notes() {
        return street_notes;
    }

    /**
     * @param street_notes the street_notes to set
     */
    public void setStreet_notes(String street_notes) {
        this.street_notes = street_notes;
    }

    /**
     * @return the parking_score
     */
    public int getParking_score() {
        return parking_score;
    }

    /**
     * @param parking_score the parking_score to set
     */
    public void setParking_score(int parking_score) {
        this.parking_score = parking_score;
    }

    /**
     * @return the parking_notes
     */
    public String getParking_notes() {
        return parking_notes;
    }

    /**
     * @param parking_notes the parking_notes to set
     */
    public void setParking_notes(String parking_notes) {
        this.parking_notes = parking_notes;
    }

    /**
     * @return the handicapparking_score
     */
    public int getHandicapparking_score() {
        return handicapparking_score;
    }

    /**
     * @param handicapparking_score the handicapparking_score to set
     */
    public void setHandicapparking_score(int handicapparking_score) {
        this.handicapparking_score = handicapparking_score;
    }

    /**
     * @return the handicapparking_notes
     */
    public String getHandicapparking_notes() {
        return handicapparking_notes;
    }

    /**
     * @param handicapparking_notes the handicapparking_notes to set
     */
    public void setHandicapparking_notes(String handicapparking_notes) {
        this.handicapparking_notes = handicapparking_notes;
    }

    /**
     * @return the pool_score
     */
    public int getPool_score() {
        return pool_score;
    }

    /**
     * @param pool_score the pool_score to set
     */
    public void setPool_score(int pool_score) {
        this.pool_score = pool_score;
    }

    /**
     * @return the pool_notes
     */
    public String getPool_notes() {
        return pool_notes;
    }

    /**
     * @param pool_notes the pool_notes to set
     */
    public void setPool_notes(String pool_notes) {
        this.pool_notes = pool_notes;
    }

    /**
     * @return the adamarking_score
     */
    public int getAdamarking_score() {
        return adamarking_score;
    }

    /**
     * @param adamarking_score the adamarking_score to set
     */
    public void setAdamarking_score(int adamarking_score) {
        this.adamarking_score = adamarking_score;
    }

    /**
     * @return the adamarking_notes
     */
    public String getAdamarking_notes() {
        return adamarking_notes;
    }

    /**
     * @param adamarking_notes the adamarking_notes to set
     */
    public void setAdamarking_notes(String adamarking_notes) {
        this.adamarking_notes = adamarking_notes;
    }

    /**
     * @return the dogpark_score
     */
    public int getDogpark_score() {
        return dogpark_score;
    }

    /**
     * @param dogpark_score the dogpark_score to set
     */
    public void setDogpark_score(int dogpark_score) {
        this.dogpark_score = dogpark_score;
    }

    /**
     * @return the dogpark_notes
     */
    public String getDogpark_notes() {
        return dogpark_notes;
    }

    /**
     * @param dogpark_notes the dogpark_notes to set
     */
    public void setDogpark_notes(String dogpark_notes) {
        this.dogpark_notes = dogpark_notes;
    }

    /**
     * @return the dumpster_score
     */
    public int getDumpster_score() {
        return dumpster_score;
    }

    /**
     * @param dumpster_score the dumpster_score to set
     */
    public void setDumpster_score(int dumpster_score) {
        this.dumpster_score = dumpster_score;
    }

    /**
     * @return the dumpster_notes
     */
    public String getDumpster_notes() {
        return dumpster_notes;
    }

    /**
     * @param dumpster_notes the dumpster_notes to set
     */
    public void setDumpster_notes(String dumpster_notes) {
        this.dumpster_notes = dumpster_notes;
    }

    /**
     * @return the firelane_score
     */
    public int getFirelane_score() {
        return firelane_score;
    }

    /**
     * @param firelane_score the firelane_score to set
     */
    public void setFirelane_score(int firelane_score) {
        this.firelane_score = firelane_score;
    }

    /**
     * @return the firelane_notes
     */
    public String getFirelane_notes() {
        return firelane_notes;
    }

    /**
     * @param firelane_notes the firelane_notes to set
     */
    public void setFirelane_notes(String firelane_notes) {
        this.firelane_notes = firelane_notes;
    }
    
}
