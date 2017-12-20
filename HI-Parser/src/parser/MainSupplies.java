/**
 * File:    MainSupplies.java    
 * Author:  Pat Ripley
 * 
 * Program Description:    
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 * 
 * Class Description:
 * Main Supplies object class.
 * 
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// class MainSupplies
public class MainSupplies {
    
    // ivars
    private int watershutoff_score;
    private String watershutoff_location;
    private String watershutoff_number;
    private String watershutoff_notes;
    
    private int sumppumps_score;
    private String sumppumps_location;
    private String sumppumps_number;
    private String sumppumps_notes;
    
    private int sewercleanout_score;
    private String sewercleanout_location;
    private String sewercleanout_number;
    private String sewercleanout_notes;
    
    private int electricmeter_score;
    private String electricmeter_location;
    private String electricmeter_number;
    private String electricmeter_notes;
    
    private int gasmeter_score;
    private String gasmeter_location;
    private String gasmeter_number;
    private String gasmeter_notes;
    
    private int firehydrants_score;
    private String firehydrants_location;
    private String firehydrants_number;
    private String firehydrants_notes;
    
    private int hvacshutoff_score;
    private String hvacshutoff_location;
    private String hvacshutoff_number;
    private String hvacshutoff_notes;
    
    /**
     * Default constructor
     */
    public MainSupplies() {
        // empty
    }

    /**
     * @return the watershutoff_score
     */
    public int getWatershutoff_score() {
        return watershutoff_score;
    }

    /**
     * @param watershutoff_score the watershutoff_score to set
     */
    public void setWatershutoff_score(int watershutoff_score) {
        this.watershutoff_score = watershutoff_score;
    }

    /**
     * @return the watershutoff_location
     */
    public String getWatershutoff_location() {
        return watershutoff_location;
    }

    /**
     * @param watershutoff_location the watershutoff_location to set
     */
    public void setWatershutoff_location(String watershutoff_location) {
        this.watershutoff_location = watershutoff_location;
    }

    /**
     * @return the watershutoff_notes
     */
    public String getWatershutoff_notes() {
        return watershutoff_notes;
    }

    /**
     * @param watershutoff_notes the watershutoff_notes to set
     */
    public void setWatershutoff_notes(String watershutoff_notes) {
        this.watershutoff_notes = watershutoff_notes;
    }

    /**
     * @return the sumppumps_score
     */
    public int getSumppumps_score() {
        return sumppumps_score;
    }

    /**
     * @param sumppumps_score the sumppumps_score to set
     */
    public void setSumppumps_score(int sumppumps_score) {
        this.sumppumps_score = sumppumps_score;
    }

    /**
     * @return the sumppumps_location
     */
    public String getSumppumps_location() {
        return sumppumps_location;
    }

    /**
     * @param sumppumps_location the sumppumps_location to set
     */
    public void setSumppumps_location(String sumppumps_location) {
        this.sumppumps_location = sumppumps_location;
    }

    /**
     * @return the sumppumps_notes
     */
    public String getSumppumps_notes() {
        return sumppumps_notes;
    }

    /**
     * @param sumppumps_notes the sumppumps_notes to set
     */
    public void setSumppumps_notes(String sumppumps_notes) {
        this.sumppumps_notes = sumppumps_notes;
    }

    /**
     * @return the sewercleanout_score
     */
    public int getSewercleanout_score() {
        return sewercleanout_score;
    }

    /**
     * @param sewercleanout_score the sewercleanout_score to set
     */
    public void setSewercleanout_score(int sewercleanout_score) {
        this.sewercleanout_score = sewercleanout_score;
    }

    /**
     * @return the sewercleanout_location
     */
    public String getSewercleanout_location() {
        return sewercleanout_location;
    }

    /**
     * @param sewercleanout_location the sewercleanout_location to set
     */
    public void setSewercleanout_location(String sewercleanout_location) {
        this.sewercleanout_location = sewercleanout_location;
    }

    /**
     * @return the sewercleanout_notes
     */
    public String getSewercleanout_notes() {
        return sewercleanout_notes;
    }

    /**
     * @param sewercleanout_notes the sewercleanout_notes to set
     */
    public void setSewercleanout_notes(String sewercleanout_notes) {
        this.sewercleanout_notes = sewercleanout_notes;
    }

    /**
     * @return the electricmeter_score
     */
    public int getElectricmeter_score() {
        return electricmeter_score;
    }

    /**
     * @param electricmeter_score the electricmeter_score to set
     */
    public void setElectricmeter_score(int electricmeter_score) {
        this.electricmeter_score = electricmeter_score;
    }

    /**
     * @return the electricmeter_location
     */
    public String getElectricmeter_location() {
        return electricmeter_location;
    }

    /**
     * @param electricmeter_location the electricmeter_location to set
     */
    public void setElectricmeter_location(String electricmeter_location) {
        this.electricmeter_location = electricmeter_location;
    }

    /**
     * @return the electricmeter_notes
     */
    public String getElectricmeter_notes() {
        return electricmeter_notes;
    }

    /**
     * @param electricmeter_notes the electricmeter_notes to set
     */
    public void setElectricmeter_notes(String electricmeter_notes) {
        this.electricmeter_notes = electricmeter_notes;
    }

    /**
     * @return the gasmeter_score
     */
    public int getGasmeter_score() {
        return gasmeter_score;
    }

    /**
     * @param gasmeter_score the gasmeter_score to set
     */
    public void setGasmeter_score(int gasmeter_score) {
        this.gasmeter_score = gasmeter_score;
    }

    /**
     * @return the gasmeter_location
     */
    public String getGasmeter_location() {
        return gasmeter_location;
    }

    /**
     * @param gasmeter_location the gasmeter_location to set
     */
    public void setGasmeter_location(String gasmeter_location) {
        this.gasmeter_location = gasmeter_location;
    }

    /**
     * @return the gasmeter_notes
     */
    public String getGasmeter_notes() {
        return gasmeter_notes;
    }

    /**
     * @param gasmeter_notes the gasmeter_notes to set
     */
    public void setGasmeter_notes(String gasmeter_notes) {
        this.gasmeter_notes = gasmeter_notes;
    }

    /**
     * @return the firehydrants_score
     */
    public int getFirehydrants_score() {
        return firehydrants_score;
    }

    /**
     * @param firehydrants_score the firehydrants_score to set
     */
    public void setFirehydrants_score(int firehydrants_score) {
        this.firehydrants_score = firehydrants_score;
    }

    /**
     * @return the firehydrants_location
     */
    public String getFirehydrants_location() {
        return firehydrants_location;
    }

    /**
     * @param firehydrants_location the firehydrants_location to set
     */
    public void setFirehydrants_location(String firehydrants_location) {
        this.firehydrants_location = firehydrants_location;
    }

    /**
     * @return the firehydrants_notes
     */
    public String getFirehydrants_notes() {
        return firehydrants_notes;
    }

    /**
     * @param firehydrants_notes the firehydrants_notes to set
     */
    public void setFirehydrants_notes(String firehydrants_notes) {
        this.firehydrants_notes = firehydrants_notes;
    }

    /**
     * @return the hvacshutoff_score
     */
    public int getHvacshutoff_score() {
        return hvacshutoff_score;
    }

    /**
     * @param hvacshutoff_score the hvacshutoff_score to set
     */
    public void setHvacshutoff_score(int hvacshutoff_score) {
        this.hvacshutoff_score = hvacshutoff_score;
    }

    /**
     * @return the hvacshutoff_location
     */
    public String getHvacshutoff_location() {
        return hvacshutoff_location;
    }

    /**
     * @param hvacshutoff_location the hvacshutoff_location to set
     */
    public void setHvacshutoff_location(String hvacshutoff_location) {
        this.hvacshutoff_location = hvacshutoff_location;
    }

    /**
     * @return the hvacshutoff_notes
     */
    public String getHvacshutoff_notes() {
        return hvacshutoff_notes;
    }

    /**
     * @param hvacshutoff_notes the hvacshutoff_notes to set
     */
    public void setHvacshutoff_notes(String hvacshutoff_notes) {
        this.hvacshutoff_notes = hvacshutoff_notes;
    }

    /**
     * @return the watershutoff_number
     */
    public String getWatershutoff_number() {
        return watershutoff_number;
    }

    /**
     * @param watershutoff_number the watershutoff_number to set
     */
    public void setWatershutoff_number(String watershutoff_number) {
        this.watershutoff_number = watershutoff_number;
    }

    /**
     * @return the sumppumps_number
     */
    public String getSumppumps_number() {
        return sumppumps_number;
    }

    /**
     * @param sumppumps_number the sumppumps_number to set
     */
    public void setSumppumps_number(String sumppumps_number) {
        this.sumppumps_number = sumppumps_number;
    }

    /**
     * @return the swercleanout_number
     */
    public String getSewercleanout_number() {
        return sewercleanout_number;
    }

    /**
     * @param swercleanout_number the swercleanout_number to set
     */
    public void setSewercleanout_number(String swercleanout_number) {
        this.sewercleanout_number = swercleanout_number;
    }

    /**
     * @return the electricmeter_number
     */
    public String getElectricmeter_number() {
        return electricmeter_number;
    }

    /**
     * @param electricmeter_number the electricmeter_number to set
     */
    public void setElectricmeter_number(String electricmeter_number) {
        this.electricmeter_number = electricmeter_number;
    }

    /**
     * @return the gasmeter_number
     */
    public String getGasmeter_number() {
        return gasmeter_number;
    }

    /**
     * @param gasmeter_number the gasmeter_number to set
     */
    public void setGasmeter_number(String gasmeter_number) {
        this.gasmeter_number = gasmeter_number;
    }

    /**
     * @return the firehydrants_number
     */
    public String getFirehydrants_number() {
        return firehydrants_number;
    }

    /**
     * @param firehydrants_number the firehydrants_number to set
     */
    public void setFirehydrants_number(String firehydrants_number) {
        this.firehydrants_number = firehydrants_number;
    }

    /**
     * @return the hvacshutoff_number
     */
    public String getHvacshutoff_number() {
        return hvacshutoff_number;
    }

    /**
     * @param hvacshutoff_number the hvacshutoff_number to set
     */
    public void setHvacshutoff_number(String hvacshutoff_number) {
        this.hvacshutoff_number = hvacshutoff_number;
    }
    
    
}
