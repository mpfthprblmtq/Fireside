/**
 * File:    Entry.java    
 * Author:  Pat Ripley
 * 
 * Program Description:    
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 * 
 * Class Description:
 * Entry/Hallway object class.
 * 
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// class Entry
public class Entry {
    
    // ivars
    private String entry_comments;
    
    private int floor_score;
    private String floor_type;
    private String floor_waterdamage;
    private String floor_notes;
    
    private int door_score;
    private String door_type;
    private String door_variety;
    private String door_material;
    private String door_notes;
    
    private int windows_score;
    private String windows_type;
    private int windows_number;
    private String windows_waterdamage;
    private String windows_notes;
    
    private int wallsceiling_score;
    private String wallsceiling_waterdamage;
    private String walls_type;
    private String ceilings_type;
    private String wallsceiling_notes;
    
    private int light_score;
    private String light_type;
    private String light_finish;
    private String light_notes;
    
    private int blindsdrapes_score;
    private String blindsdrapes_type;
    private String blindsdrapes_notes;
    
    private int stairs_score;
    private String stairs_flooring;
    private String stairs_notes;
    
    private int closet_score;
    private String closet_door_type;
    private String closet_door_variety;
    private String closet_door_material;
    private String closet_notes;
    
    /**
     * Default constructor
     */
    public Entry() {
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
     * @return the windows_score
     */
    public int getWindows_score() {
        return windows_score;
    }

    /**
     * @param windows_score the windows_score to set
     */
    public void setWindows_score(int windows_score) {
        this.windows_score = windows_score;
    }

    /**
     * @return the windows_type
     */
    public String getWindows_type() {
        return windows_type;
    }

    /**
     * @param windows_type the windows_type to set
     */
    public void setWindows_type(String windows_type) {
        this.windows_type = windows_type;
    }

    /**
     * @return the windows_notes
     */
    public String getWindows_notes() {
        return windows_notes;
    }

    /**
     * @param windows_notes the windows_notes to set
     */
    public void setWindows_notes(String windows_notes) {
        this.windows_notes = windows_notes;
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
     * @return the blindsdrapes_score
     */
    public int getBlindsdrapes_score() {
        return blindsdrapes_score;
    }

    /**
     * @param blindsdrapes_score the blindsdrapes_score to set
     */
    public void setBlindsdrapes_score(int blindsdrapes_score) {
        this.blindsdrapes_score = blindsdrapes_score;
    }

    /**
     * @return the blindsdrapes_type
     */
    public String getBlindsdrapes_type() {
        return blindsdrapes_type;
    }

    /**
     * @param blindsdrapes_type the blindsdrapes_type to set
     */
    public void setBlindsdrapes_type(String blindsdrapes_type) {
        this.blindsdrapes_type = blindsdrapes_type;
    }

    /**
     * @return the blindsdrapes_notes
     */
    public String getBlindsdrapes_notes() {
        return blindsdrapes_notes;
    }

    /**
     * @param blindsdrapes_notes the blindsdrapes_notes to set
     */
    public void setBlindsdrapes_notes(String blindsdrapes_notes) {
        this.blindsdrapes_notes = blindsdrapes_notes;
    }

    /**
     * @return the stairs_score
     */
    public int getStairs_score() {
        return stairs_score;
    }

    /**
     * @param stairs_score the stairs_score to set
     */
    public void setStairs_score(int stairs_score) {
        this.stairs_score = stairs_score;
    }

    /**
     * @return the stairs_flooring
     */
    public String getStairs_flooring() {
        return stairs_flooring;
    }

    /**
     * @param stairs_flooring the stairs_flooring to set
     */
    public void setStairs_flooring(String stairs_flooring) {
        this.stairs_flooring = stairs_flooring;
    }

    /**
     * @return the stairs_notes
     */
    public String getStairs_notes() {
        return stairs_notes;
    }

    /**
     * @param stairs_notes the stairs_notes to set
     */
    public void setStairs_notes(String stairs_notes) {
        this.stairs_notes = stairs_notes;
    }

    /**
     * @return the closet_score
     */
    public int getCloset_score() {
        return closet_score;
    }

    /**
     * @param closet_score the closet_score to set
     */
    public void setCloset_score(int closet_score) {
        this.closet_score = closet_score;
    }

    /**
     * @return the closet_door_type
     */
    public String getCloset_door_type() {
        return closet_door_type;
    }

    /**
     * @param closet_door_type the closet_door_type to set
     */
    public void setCloset_door_type(String closet_door_type) {
        this.closet_door_type = closet_door_type;
    }

    /**
     * @return the closet_door_variety
     */
    public String getCloset_door_variety() {
        return closet_door_variety;
    }

    /**
     * @param closet_door_variety the closet_door_variety to set
     */
    public void setCloset_door_variety(String closet_door_variety) {
        this.closet_door_variety = closet_door_variety;
    }

    /**
     * @return the closet_notes
     */
    public String getCloset_notes() {
        return closet_notes;
    }

    /**
     * @param closet_notes the closet_notes to set
     */
    public void setCloset_notes(String closet_notes) {
        this.closet_notes = closet_notes;
    }

    /**
     * @return the wallsceiling_waterdamage
     */
    public String getWallsceiling_waterdamage() {
        return wallsceiling_waterdamage;
    }

    /**
     * @param wallsceiling_waterdamage the wallsceiling_waterdamage to set
     */
    public void setWallsceiling_waterdamage(String wallsceiling_waterdamage) {
        this.wallsceiling_waterdamage = wallsceiling_waterdamage;
    }

    /**
     * @return the door_material
     */
    public String getDoor_material() {
        return door_material;
    }

    /**
     * @param door_material the door_material to set
     */
    public void setDoor_material(String door_material) {
        this.door_material = door_material;
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
     * @return the ceilings_type
     */
    public String getCeilings_type() {
        return ceilings_type;
    }

    /**
     * @param ceilings_type the ceilings_type to set
     */
    public void setCeilings_type(String ceilings_type) {
        this.ceilings_type = ceilings_type;
    }

    /**
     * @return the closet_door_material
     */
    public String getCloset_door_material() {
        return closet_door_material;
    }

    /**
     * @param closet_door_material the closet_door_material to set
     */
    public void setCloset_door_material(String closet_door_material) {
        this.closet_door_material = closet_door_material;
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
     * @return the windows_number
     */
    public int getWindows_number() {
        return windows_number;
    }

    /**
     * @param windows_number the windows_number to set
     */
    public void setWindows_number(int windows_number) {
        this.windows_number = windows_number;
    }

    /**
     * @return the windows_waterdamage
     */
    public String getWindows_waterdamage() {
        return windows_waterdamage;
    }

    /**
     * @param windows_waterdamage the windows_waterdamage to set
     */
    public void setWindows_waterdamage(String windows_waterdamage) {
        this.windows_waterdamage = windows_waterdamage;
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
     * @return the entry_comments
     */
    public String getEntry_comments() {
        return entry_comments;
    }

    /**
     * @param entry_comments the entry_comments to set
     */
    public void setEntry_comments(String entry_comments) {
        this.entry_comments = entry_comments;
    }
}
