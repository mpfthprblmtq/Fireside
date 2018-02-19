/**
 * File:    Mechanical.java    
 * Author:  Pat Ripley
 * 
 * Program Description:    
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 * 
 * Class Description:
 * Mechanical object class.
 * 
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// class Mechanical
public class Mechanical {
    
    // ivars
    private String mechanical_comments;
    
    private int fridge_score;
    private String fridge_model;
    private String fridge_finish;
    private String fridge_icemaker;
    private String fridge_notes;
    
    private int range_score;
    private String range_model;
    private String range_type;
    private String range_finish;
    private String range_notes;
    
    private int dishwasher_score;
    private String dishwasher_model;
    private String dishwasher_finish;
    private String dishwasher_notes;
    
    private int disposal_score;
    private String disposal_notes;
    
    private int hood_score;
    private String hood_microwave;
    private String hood_model;
    private String hood_finish;
    private String hood_notes;
    
    private int hvac_score;
    private String hvac_model;
    private String hvac_type;
    private String hvac_notes;
    
    private int water_heater_score;
    private String water_heater_model;
    private String water_heater_type;
    private String water_heater_notes;
    
    private int washer_dryer_score;
    private String washer_dryer_model;
    private String washer_dryer_finish;
    private String washer_dryer_notes;
    private boolean hasHookup;
    
    /**
     * Default constructor
     */
    public Mechanical() {
        // empty
    }

    /**
     * @return the fridge_score
     */
    public int getFridge_score() {
        return fridge_score;
    }

    /**
     * @param fridge_score the fridge_score to set
     */
    public void setFridge_score(int fridge_score) {
        this.fridge_score = fridge_score;
    }

    /**
     * @return the fridge_model
     */
    public String getFridge_model() {
        return fridge_model;
    }

    /**
     * @param fridge_model the fridge_model to set
     */
    public void setFridge_model(String fridge_model) {
        this.fridge_model = fridge_model;
    }

    /**
     * @return the fridge_notes
     */
    public String getFridge_notes() {
        return fridge_notes;
    }

    /**
     * @param fridge_notes the fridge_notes to set
     */
    public void setFridge_notes(String fridge_notes) {
        this.fridge_notes = fridge_notes;
    }

    /**
     * @return the range_score
     */
    public int getRange_score() {
        return range_score;
    }

    /**
     * @param range_score the range_score to set
     */
    public void setRange_score(int range_score) {
        this.range_score = range_score;
    }

    /**
     * @return the range_model
     */
    public String getRange_model() {
        return range_model;
    }

    /**
     * @param range_model the range_model to set
     */
    public void setRange_model(String range_model) {
        this.range_model = range_model;
    }

    /**
     * @return the range_notes
     */
    public String getRange_notes() {
        return range_notes;
    }

    /**
     * @param range_notes the range_notes to set
     */
    public void setRange_notes(String range_notes) {
        this.range_notes = range_notes;
    }

    /**
     * @return the dishwasher_score
     */
    public int getDishwasher_score() {
        return dishwasher_score;
    }

    /**
     * @param dishwasher_score the dishwasher_score to set
     */
    public void setDishwasher_score(int dishwasher_score) {
        this.dishwasher_score = dishwasher_score;
    }

    /**
     * @return the dishwasher_model
     */
    public String getDishwasher_model() {
        return dishwasher_model;
    }

    /**
     * @param dishwasher_model the dishwasher_model to set
     */
    public void setDishwasher_model(String dishwasher_model) {
        this.dishwasher_model = dishwasher_model;
    }

    /**
     * @return the dishwasher_notes
     */
    public String getDishwasher_notes() {
        return dishwasher_notes;
    }

    /**
     * @param dishwasher_notes the dishwasher_notes to set
     */
    public void setDishwasher_notes(String dishwasher_notes) {
        this.dishwasher_notes = dishwasher_notes;
    }

    /**
     * @return the disposal_score
     */
    public int getDisposal_score() {
        return disposal_score;
    }

    /**
     * @param disposal_score the disposal_score to set
     */
    public void setDisposal_score(int disposal_score) {
        this.disposal_score = disposal_score;
    }

    /**
     * @return the disposal_notes
     */
    public String getDisposal_notes() {
        return disposal_notes;
    }

    /**
     * @param disposal_notes the disposal_notes to set
     */
    public void setDisposal_notes(String disposal_notes) {
        this.disposal_notes = disposal_notes;
    }

    /**
     * @return the hood_score
     */
    public int getHood_score() {
        return hood_score;
    }

    /**
     * @param hood_score the hood_score to set
     */
    public void setHood_score(int hood_score) {
        this.hood_score = hood_score;
    }

    /**
     * @return the hood_model
     */
    public String getHood_model() {
        return hood_model;
    }

    /**
     * @param hood_model the hood_model to set
     */
    public void setHood_model(String hood_model) {
        this.hood_model = hood_model;
    }

    /**
     * @return the hood_notes
     */
    public String getHood_notes() {
        return hood_notes;
    }

    /**
     * @param hood_notes the hood_notes to set
     */
    public void setHood_notes(String hood_notes) {
        this.hood_notes = hood_notes;
    }

    /**
     * @return the hvac_score
     */
    public int getHvac_score() {
        return hvac_score;
    }

    /**
     * @param hvac_score the hvac_score to set
     */
    public void setHvac_score(int hvac_score) {
        this.hvac_score = hvac_score;
    }

    /**
     * @return the hvac_model
     */
    public String getHvac_model() {
        return hvac_model;
    }

    /**
     * @param hvac_model the hvac_model to set
     */
    public void setHvac_model(String hvac_model) {
        this.hvac_model = hvac_model;
    }

    /**
     * @return the hvac_notes
     */
    public String getHvac_notes() {
        return hvac_notes;
    }

    /**
     * @param hvac_notes the hvac_notes to set
     */
    public void setHvac_notes(String hvac_notes) {
        this.hvac_notes = hvac_notes;
    }

    /**
     * @return the water_heater_score
     */
    public int getWater_heater_score() {
        return water_heater_score;
    }

    /**
     * @param water_heater_score the water_heater_score to set
     */
    public void setWater_heater_score(int water_heater_score) {
        this.water_heater_score = water_heater_score;
    }

    /**
     * @return the water_heater_model
     */
    public String getWater_heater_model() {
        return water_heater_model;
    }

    /**
     * @param water_heater_model the water_heater_model to set
     */
    public void setWater_heater_model(String water_heater_model) {
        this.water_heater_model = water_heater_model;
    }

    /**
     * @return the water_heater_notes
     */
    public String getWater_heater_notes() {
        return water_heater_notes;
    }

    /**
     * @param water_heater_notes the water_heater_notes to set
     */
    public void setWater_heater_notes(String water_heater_notes) {
        this.water_heater_notes = water_heater_notes;
    }

    /**
     * @return the washer_dryer_score
     */
    public int getWasher_dryer_score() {
        return washer_dryer_score;
    }

    /**
     * @param washer_dryer_score the washer_dryer_score to set
     */
    public void setWasher_dryer_score(int washer_dryer_score) {
        this.washer_dryer_score = washer_dryer_score;
    }

    /**
     * @return the washer_dryer_model
     */
    public String getWasher_dryer_model() {
        return washer_dryer_model;
    }

    /**
     * @param washer_dryer_model the washer_dryer_model to set
     */
    public void setWasher_dryer_model(String washer_dryer_model) {
        this.washer_dryer_model = washer_dryer_model;
    }

    /**
     * @return the washer_dryer_notes
     */
    public String getWasher_dryer_notes() {
        return washer_dryer_notes;
    }

    /**
     * @param washer_dryer_notes the washer_dryer_notes to set
     */
    public void setWasher_dryer_notes(String washer_dryer_notes) {
        this.washer_dryer_notes = washer_dryer_notes;
    }

    /**
     * @return the range_type
     */
    public String getRange_type() {
        return range_type;
    }

    /**
     * @param range_type the range_type to set
     */
    public void setRange_type(String range_type) {
        this.range_type = range_type;
    }

    /**
     * @return the hasHookup
     */
    public boolean isHasHookup() {
        return hasHookup;
    }

    /**
     * @param hasHookup the hasHookup to set
     */
    public void setHasHookup(boolean hasHookup) {
        this.hasHookup = hasHookup;
    }

    /**
     * @return the fridge_finish
     */
    public String getFridge_finish() {
        return fridge_finish;
    }

    /**
     * @param fridge_finish the fridge_finish to set
     */
    public void setFridge_finish(String fridge_finish) {
        this.fridge_finish = fridge_finish;
    }

    /**
     * @return the range_finish
     */
    public String getRange_finish() {
        return range_finish;
    }

    /**
     * @param range_finish the range_finish to set
     */
    public void setRange_finish(String range_finish) {
        this.range_finish = range_finish;
    }

    /**
     * @return the dishwasher_finish
     */
    public String getDishwasher_finish() {
        return dishwasher_finish;
    }

    /**
     * @param dishwasher_finish the dishwasher_finish to set
     */
    public void setDishwasher_finish(String dishwasher_finish) {
        this.dishwasher_finish = dishwasher_finish;
    }

    /**
     * @return the hood_microwave
     */
    public String getHood_microwave() {
        return hood_microwave;
    }

    /**
     * @param hood_microwave the hood_microwave to set
     */
    public void setHood_microwave(String hood_microwave) {
        this.hood_microwave = hood_microwave;
    }

    /**
     * @return the hood_finish
     */
    public String getHood_finish() {
        return hood_finish;
    }

    /**
     * @param hood_finish the hood_finish to set
     */
    public void setHood_finish(String hood_finish) {
        this.hood_finish = hood_finish;
    }

    /**
     * @return the washer_dryer_finish
     */
    public String getWasher_dryer_finish() {
        return washer_dryer_finish;
    }

    /**
     * @param washer_dryer_finish the washer_dryer_finish to set
     */
    public void setWasher_dryer_finish(String washer_dryer_finish) {
        this.washer_dryer_finish = washer_dryer_finish;
    }

    /**
     * @return the mechanical_comments
     */
    public String getMechanical_comments() {
        return mechanical_comments;
    }

    /**
     * @param mechanical_comments the mechanical_comments to set
     */
    public void setMechanical_comments(String mechanical_comments) {
        this.mechanical_comments = mechanical_comments;
    }

    /**
     * @return the fridge_icemaker
     */
    public String getFridge_icemaker() {
        return fridge_icemaker;
    }

    /**
     * @param fridge_icemaker the fridge_icemaker to set
     */
    public void setFridge_icemaker(String fridge_icemaker) {
        this.fridge_icemaker = fridge_icemaker;
    }

    /**
     * @return the hvac_type
     */
    public String getHvac_type() {
        return hvac_type;
    }

    /**
     * @param hvac_type the hvac_type to set
     */
    public void setHvac_type(String hvac_type) {
        this.hvac_type = hvac_type;
    }

    /**
     * @return the water_heater_type
     */
    public String getWater_heater_type() {
        return water_heater_type;
    }

    /**
     * @param water_heater_type the water_heater_type to set
     */
    public void setWater_heater_type(String water_heater_type) {
        this.water_heater_type = water_heater_type;
    }
} // end Mechanical.java
