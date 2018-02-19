/**
 * File:    InteriorHallway.java    
 * Author:  Pat Ripley
 * 
 * Program Description:    
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 * 
 * Class Description:
 * Dumpsters object class.
 * 
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// class Dumpsters
public class Dumpsters {
    
    // ivars
    private int fencepickets_score;
    private String fencepickets_material;
    private String fencepickets_notes;
    
    private int gates_score;
    private String gates_notes;
    
    private int gateholes_score;
    private String gateholes_notes;
    
    private int ballasts_score;
    private String ballasts_notes;
    
    private String unaccounted_items;
    
    /**
     * Default constructor
     */
    public Dumpsters() {
        // empty
    }

    /**
     * @return the fencepickets_score
     */
    public int getFencepickets_score() {
        return fencepickets_score;
    }

    /**
     * @param fencepickets_score the fencepickets_score to set
     */
    public void setFencepickets_score(int fencepickets_score) {
        this.fencepickets_score = fencepickets_score;
    }

    /**
     * @return the fencepickets_notes
     */
    public String getFencepickets_notes() {
        return fencepickets_notes;
    }

    /**
     * @param fencepickets_notes the fencepickets_notes to set
     */
    public void setFencepickets_notes(String fencepickets_notes) {
        this.fencepickets_notes = fencepickets_notes;
    }

    /**
     * @return the gates_score
     */
    public int getGates_score() {
        return gates_score;
    }

    /**
     * @param gates_score the gates_score to set
     */
    public void setGates_score(int gates_score) {
        this.gates_score = gates_score;
    }

    /**
     * @return the gates_notes
     */
    public String getGates_notes() {
        return gates_notes;
    }

    /**
     * @param gates_notes the gates_notes to set
     */
    public void setGates_notes(String gates_notes) {
        this.gates_notes = gates_notes;
    }

    /**
     * @return the ballasts_score
     */
    public int getBallasts_score() {
        return ballasts_score;
    }

    /**
     * @param ballasts_score the ballasts_score to set
     */
    public void setBallasts_score(int ballasts_score) {
        this.ballasts_score = ballasts_score;
    }

    /**
     * @return the ballasts_notes
     */
    public String getBallasts_notes() {
        return ballasts_notes;
    }

    /**
     * @param ballasts_notes the ballasts_notes to set
     */
    public void setBallasts_notes(String ballasts_notes) {
        this.ballasts_notes = ballasts_notes;
    }

    /**
     * @return the fencepickets_material
     */
    public String getFencepickets_material() {
        return fencepickets_material;
    }

    /**
     * @param fencepickets_material the fencepickets_material to set
     */
    public void setFencepickets_material(String fencepickets_material) {
        this.fencepickets_material = fencepickets_material;
    }

    /**
     * @return the gateholes_score
     */
    public int getGateholes_score() {
        return gateholes_score;
    }

    /**
     * @param gateholes_score the gateholes_score to set
     */
    public void setGateholes_score(int gateholes_score) {
        this.gateholes_score = gateholes_score;
    }

    /**
     * @return the gateholes_notes
     */
    public String getGateholes_notes() {
        return gateholes_notes;
    }

    /**
     * @param gateholes_notes the gateholes_notes to set
     */
    public void setGateholes_notes(String gateholes_notes) {
        this.gateholes_notes = gateholes_notes;
    }

    /**
     * @return the unaccounted_items
     */
    public String getUnaccounted_items() {
        return unaccounted_items;
    }

    /**
     * @param unaccounted_items the unaccounted_items to set
     */
    public void setUnaccounted_items(String unaccounted_items) {
        this.unaccounted_items = unaccounted_items;
    }
} // end Dumpsters.java
