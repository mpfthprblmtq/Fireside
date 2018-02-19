/**
 * File:    Bathroom.java    
 * Author:  Pat Ripley
 * 
 * Program Description:    
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 * 
 * Class Description:
 * Bathroom object class.
 * 
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// class Main
public class Bathroom {

    // ivars
    private String bathroom_comments;
    
    private int floor_score;
    private String floor_type;
    private String floor_waterdamage;
    private String floor_notes;
    
    private int door_score;
    private String door_type;
    private String door_variety;
    private String door_notes;
    
    private int wallsceiling_score;
    private String wallsceiling_waterdamage;
    private String walls_type;
    private String ceiling_type;
    private String wallsceiling_notes;
    
    private int light_score;
    private String light_type;
    private String light_finish;
    private String light_notes;
    
    private int medicinecabinet_score;
    private String medicinecabinet_notes;
    
    private int mirror_score;
    private String mirror_notes;
    
    private int countertop_score;
    private String countertop_material;
    private String countertop_notes;
    
    private int commode_score;
    private String commode_notes;
    
    private int tubshower_score;
    private String tubshower_material;
    private String tubshower_type;
    private String tubshower_notes;
    
    private String unaccounted_items;
    
    /**
     * Default constructor
     */
    public Bathroom() {
        // empty
    }

    /**
     * @return the floor_score
     */
    public int getFloor_score() {
        return floor_score;
    }

    /**
     * @param floor_score the floor_score to set
     */
    public void setFloor_score(int floor_score) {
        this.floor_score = floor_score;
    }

    /**
     * @return the floor_type
     */
    public String getFloor_type() {
        return floor_type;
    }

    /**
     * @param floor_type the floor_type to set
     */
    public void setFloor_type(String floor_type) {
        this.floor_type = floor_type;
    }

    /**
     * @return the floor_notes
     */
    public String getFloor_notes() {
        return floor_notes;
    }

    /**
     * @param floor_notes the floor_notes to set
     */
    public void setFloor_notes(String floor_notes) {
        this.floor_notes = floor_notes;
    }

    /**
     * @return the door_score
     */
    public int getDoor_score() {
        return door_score;
    }

    /**
     * @param door_score the door_score to set
     */
    public void setDoor_score(int door_score) {
        this.door_score = door_score;
    }

    /**
     * @return the door_type
     */
    public String getDoor_type() {
        return door_type;
    }

    /**
     * @param door_type the door_type to set
     */
    public void setDoor_type(String door_type) {
        this.door_type = door_type;
    }

    /**
     * @return the door_variety
     */
    public String getDoor_variety() {
        return door_variety;
    }

    /**
     * @param door_variety the door_variety to set
     */
    public void setDoor_variety(String door_variety) {
        this.door_variety = door_variety;
    }

    /**
     * @return the door_notes
     */
    public String getDoor_notes() {
        return door_notes;
    }

    /**
     * @param door_notes the door_notes to set
     */
    public void setDoor_notes(String door_notes) {
        this.door_notes = door_notes;
    }

    /**
     * @return the wallsceiling_score
     */
    public int getWallsceiling_score() {
        return wallsceiling_score;
    }

    /**
     * @param wallsceiling_score the wallsceiling_score to set
     */
    public void setWallsceiling_score(int wallsceiling_score) {
        this.wallsceiling_score = wallsceiling_score;
    }

    /**
     * @return the wallsceiling_waterdamage
     */
    public String getWallsceiling_waterdamage() {
        return wallsceiling_waterdamage;
    }

    /**
     * @param wallsceiling_waterdamage
     */
    public void setWallsceiling_waterdamage(String wallsceiling_waterdamage) {
        this.wallsceiling_waterdamage = wallsceiling_waterdamage;
    }

    /**
     * @return the wallsceiling_notes
     */
    public String getWallsceiling_notes() {
        return wallsceiling_notes;
    }

    /**
     * @param wallsceiling_notes the wallsceiling_notes to set
     */
    public void setWallsceiling_notes(String wallsceiling_notes) {
        this.wallsceiling_notes = wallsceiling_notes;
    }

    /**
     * @return the light_score
     */
    public int getLight_score() {
        return light_score;
    }

    /**
     * @param light_score the light_score to set
     */
    public void setLight_score(int light_score) {
        this.light_score = light_score;
    }

    /**
     * @return the light_type
     */
    public String getLight_type() {
        return light_type;
    }

    /**
     * @param light_type the light_type to set
     */
    public void setLight_type(String light_type) {
        this.light_type = light_type;
    }

    /**
     * @return the light_notes
     */
    public String getLight_notes() {
        return light_notes;
    }

    /**
     * @param light_notes the light_notes to set
     */
    public void setLight_notes(String light_notes) {
        this.light_notes = light_notes;
    }

    /**
     * @return the medicinecabinet_score
     */
    public int getMedicinecabinet_score() {
        return medicinecabinet_score;
    }

    /**
     * @param medicinecabinet_score the medicinecabinet_score to set
     */
    public void setMedicinecabinet_score(int medicinecabinet_score) {
        this.medicinecabinet_score = medicinecabinet_score;
    }

    /**
     * @return the medicinecabinet_notes
     */
    public String getMedicinecabinet_notes() {
        return medicinecabinet_notes;
    }

    /**
     * @param medicinecabinet_notes the medicinecabinet_notes to set
     */
    public void setMedicinecabinet_notes(String medicinecabinet_notes) {
        this.medicinecabinet_notes = medicinecabinet_notes;
    }

    /**
     * @return the mirror_score
     */
    public int getMirror_score() {
        return mirror_score;
    }

    /**
     * @param mirror_score the mirror_score to set
     */
    public void setMirror_score(int mirror_score) {
        this.mirror_score = mirror_score;
    }

    /**
     * @return the mirror_notes
     */
    public String getMirror_notes() {
        return mirror_notes;
    }

    /**
     * @param mirror_notes the mirror_notes to set
     */
    public void setMirror_notes(String mirror_notes) {
        this.mirror_notes = mirror_notes;
    }

    /**
     * @return the countertop_score
     */
    public int getCountertop_score() {
        return countertop_score;
    }

    /**
     * @param countertop_score the countertop_score to set
     */
    public void setCountertop_score(int countertop_score) {
        this.countertop_score = countertop_score;
    }

    /**
     * @return the countertop_notes
     */
    public String getCountertop_notes() {
        return countertop_notes;
    }

    /**
     * @param countertop_notes the countertop_notes to set
     */
    public void setSink_notes(String countertop_notes) {
        this.countertop_notes = countertop_notes;
    }

    /**
     * @return the commode_score
     */
    public int getCommode_score() {
        return commode_score;
    }

    /**
     * @param commode_score the commode_score to set
     */
    public void setCommode_score(int commode_score) {
        this.commode_score = commode_score;
    }

    /**
     * @return the commode_notes
     */
    public String getCommode_notes() {
        return commode_notes;
    }

    /**
     * @param commode_notes the commode_notes to set
     */
    public void setCommode_notes(String commode_notes) {
        this.commode_notes = commode_notes;
    }

    /**
     * @return the tubshower_score
     */
    public int getTubshower_score() {
        return tubshower_score;
    }

    /**
     * @param tubshower_score the tubshower_score to set
     */
    public void setTubshower_score(int tubshower_score) {
        this.tubshower_score = tubshower_score;
    }

    /**
     * @return the tubshower_notes
     */
    public String getTubshower_notes() {
        return tubshower_notes;
    }

    /**
     * @param tubshower_notes the tubshower_notes to set
     */
    public void setTubshower_notes(String tubshower_notes) {
        this.tubshower_notes = tubshower_notes;
    }
    
    /**
     * @return the tubshower_type
     */
    public String getTubshower_type() {
        return tubshower_type;
    }

    /**
     * @param tubshower_type the tubshower_type to set
     */
    public void setTubshower_type(String tubshower_type) {
        this.tubshower_type = tubshower_type;
    }

    /**
     * @return the walls_type
     */
    public String getWalls_type() {
        return walls_type;
    }

    /**
     * @param walls_type the walls_type to set
     */
    public void setWalls_type(String walls_type) {
        this.walls_type = walls_type;
    }

    /**
     * @return the ceiling_type
     */
    public String getCeiling_type() {
        return ceiling_type;
    }

    /**
     * @param ceiling_type the ceiling_type to set
     */
    public void setCeiling_type(String ceiling_type) {
        this.ceiling_type = ceiling_type;
    }

    /**
     * @return the countertop_material
     */
    public String getCountertop_material() {
        return countertop_material;
    }

    /**
     * @param countertop_material the countertop_material to set
     */
    public void setCountertop_material(String countertop_material) {
        this.countertop_material = countertop_material;
    }

    /**
     * @return the tubshower_material
     */
    public String getTubshower_material() {
        return tubshower_material;
    }

    /**
     * @param tubshower_material the tubshower_material to set
     */
    public void setTubshower_material(String tubshower_material) {
        this.tubshower_material = tubshower_material;
    }

    /**
     * @return the bathroom_comments
     */
    public String getBathroom_comments() {
        return bathroom_comments;
    }

    /**
     * @param bathroom_comments the bathroom_comments to set
     */
    public void setBathroom_comments(String bathroom_comments) {
        this.bathroom_comments = bathroom_comments;
    }

    /**
     * @return the floor_waterdamage
     */
    public String getFloor_waterdamage() {
        return floor_waterdamage;
    }

    /**
     * @param floor_waterdamage the floor_waterdamage to set
     */
    public void setFloor_waterdamage(String floor_waterdamage) {
        this.floor_waterdamage = floor_waterdamage;
    }

    /**
     * @return the light_finish
     */
    public String getLight_finish() {
        return light_finish;
    }

    /**
     * @param light_finish the light_finish to set
     */
    public void setLight_finish(String light_finish) {
        this.light_finish = light_finish;
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
} // end Bathroom.java
