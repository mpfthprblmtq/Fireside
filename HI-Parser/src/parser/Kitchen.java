/**
 * File:    Kitchen.java    
 * Author:  Pat Ripley
 * 
 * Program Description:    
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 * 
 * Class Description:
 * Kitchen object class.
 * 
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// class Kitchen
public class Kitchen {
    
    // ivars
    private String kitchen_comments;
    
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
    private String ceiling_type;
    private String wallsceiling_notes;
    
    private int light_score;
    private String light_type;
    private String light_finish;
    private String light_notes;
    
    private int blindsdrapes_score;
    private String blindsdrapes_type;
    private String blindsdrapes_notes;
    
    private int sink_score;
    private String sink_type;
    private String sink_notes;
    
    private int countertops_score;
    private String countertops_type;
    private String countertops_notes;
    
    private int passbar_score;
    private String passbar_type;
    private String passbar_notes;
    
    private int cabinets_score;
    private String cabinets_type;
    private String cabinets_notes;
    
    private int pantry_score;
    private String pantry_door_type;
    private String pantry_door_variety;
    private String pantry_door_material;
    private String pantry_notes;
    
    private String unaccounted_items;
    
    /**
     * Empty constructor
     */
    public Kitchen() {
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
     * @return the sink_score
     */
    public int getSink_score() {
        return sink_score;
    }

    /**
     * @param sink_score the sink_score to set
     */
    public void setSink_score(int sink_score) {
        this.sink_score = sink_score;
    }

    /**
     * @return the sink_notes
     */
    public String getSink_notes() {
        return sink_notes;
    }

    /**
     * @param sink_notes the sink_notes to set
     */
    public void setSink_notes(String sink_notes) {
        this.sink_notes = sink_notes;
    }

    /**
     * @return the countertops_score
     */
    public int getCountertops_score() {
        return countertops_score;
    }

    /**
     * @param countertops_score the countertops_score to set
     */
    public void setCountertops_score(int countertops_score) {
        this.countertops_score = countertops_score;
    }

    /**
     * @return the countertops_type
     */
    public String getCountertops_type() {
        return countertops_type;
    }

    /**
     * @param countertops_type the countertops_type to set
     */
    public void setCountertops_type(String countertops_type) {
        this.countertops_type = countertops_type;
    }

    /**
     * @return the countertops_notes
     */
    public String getCountertops_notes() {
        return countertops_notes;
    }

    /**
     * @param countertops_notes the countertops_notes to set
     */
    public void setCountertops_notes(String countertops_notes) {
        this.countertops_notes = countertops_notes;
    }

    /**
     * @return the passbar_score
     */
    public int getPassbar_score() {
        return passbar_score;
    }

    /**
     * @param passbar_score the passbar_score to set
     */
    public void setPassbar_score(int passbar_score) {
        this.passbar_score = passbar_score;
    }

    /**
     * @return the passbar_type
     */
    public String getPassbar_type() {
        return passbar_type;
    }

    /**
     * @param passbar_type the passbar_type to set
     */
    public void setPassbar_type(String passbar_type) {
        this.passbar_type = passbar_type;
    }

    /**
     * @return the passbar_notes
     */
    public String getPassbar_notes() {
        return passbar_notes;
    }

    /**
     * @param passbar_notes the passbar_notes to set
     */
    public void setPassbar_notes(String passbar_notes) {
        this.passbar_notes = passbar_notes;
    }

    /**
     * @return the cabinets_score
     */
    public int getCabinets_score() {
        return cabinets_score;
    }

    /**
     * @param cabinets_score the cabinets_score to set
     */
    public void setCabinets_score(int cabinets_score) {
        this.cabinets_score = cabinets_score;
    }

    /**
     * @return the cabinets_type
     */
    public String getCabinets_type() {
        return cabinets_type;
    }

    /**
     * @param cabinets_type the cabinets_type to set
     */
    public void setCabinets_type(String cabinets_type) {
        this.cabinets_type = cabinets_type;
    }

    /**
     * @return the cabinets_notes
     */
    public String getCabinets_notes() {
        return cabinets_notes;
    }

    /**
     * @param cabinets_notes the cabinets_notes to set
     */
    public void setCabinets_notes(String cabinets_notes) {
        this.cabinets_notes = cabinets_notes;
    }

    /**
     * @return the pantry_score
     */
    public int getPantry_score() {
        return pantry_score;
    }

    /**
     * @param pantry_score the pantry_score to set
     */
    public void setPantry_score(int pantry_score) {
        this.pantry_score = pantry_score;
    }

    /**
     * @return the pantry_door_type
     */
    public String getPantry_door_type() {
        return pantry_door_type;
    }

    /**
     * @param pantry_door_type the pantry_door_type to set
     */
    public void setPantry_door_type(String pantry_door_type) {
        this.pantry_door_type = pantry_door_type;
    }

    /**
     * @return the pantry_door_variety
     */
    public String getPantry_door_variety() {
        return pantry_door_variety;
    }

    /**
     * @param pantry_door_variety the pantry_door_variety to set
     */
    public void setPantry_door_variety(String pantry_door_variety) {
        this.pantry_door_variety = pantry_door_variety;
    }

    /**
     * @return the pantry_notes
     */
    public String getPantry_notes() {
        return pantry_notes;
    }

    /**
     * @param pantry_notes the pantry_notes to set
     */
    public void setPantry_notes(String pantry_notes) {
        this.pantry_notes = pantry_notes;
    }
    
    /**
     * @return the sink_type
     */
    public String getSink_type() {
        return sink_type;
    }

    /**
     * @param sink_type the sink_type to set
     */
    public void setSink_type(String sink_type) {
        this.sink_type = sink_type;
    }

    /**
     * @return the wallsceiling_type
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
     * @return the pantry_door_material
     */
    public String getPantry_door_material() {
        return pantry_door_material;
    }

    /**
     * @param pantry_door_material the pantry_door_material to set
     */
    public void setPantry_door_material(String pantry_door_material) {
        this.pantry_door_material = pantry_door_material;
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
     * @return the kitchen_comments
     */
    public String getKitchen_comments() {
        return kitchen_comments;
    }

    /**
     * @param kitchen_comments the kitchen_comments to set
     */
    public void setKitchen_comments(String kitchen_comments) {
        this.kitchen_comments = kitchen_comments;
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
} // end Kitchen.java
