/**
 * File:    Fencing.java    
 * Author:  Pat Ripley
 * 
 * Program Description:    
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 * 
 * Class Description:
 * Fencing object class.
 * 
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// class Fencing
public class Fencing {
    
    // ivars
    private int fencepickets_score;
    private String fencepickets_type;
    private String fencepickets_notes;
    
    private int lockslatches_score;
    private String lockslatches_notes;
    
    private int gates_score;
    private String gates_notes;
    
    /**
     * Default constructor
     */
    public Fencing() {
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
     * @return the lockslatches_score
     */
    public int getLockslatches_score() {
        return lockslatches_score;
    }

    /**
     * @param lockslatches_score the lockslatches_score to set
     */
    public void setLockslatches_score(int lockslatches_score) {
        this.lockslatches_score = lockslatches_score;
    }

    /**
     * @return the lockslatches_notes
     */
    public String getLockslatches_notes() {
        return lockslatches_notes;
    }

    /**
     * @param lockslatches_notes the lockslatches_notes to set
     */
    public void setLockslatches_notes(String lockslatches_notes) {
        this.lockslatches_notes = lockslatches_notes;
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
     * @return the fencepickets_type
     */
    public String getFencepickets_type() {
        return fencepickets_type;
    }

    /**
     * @param fencepickets_type the fencepickets_type to set
     */
    public void setFencepickets_type(String fencepickets_type) {
        this.fencepickets_type = fencepickets_type;
    }
    
    
    
}
