/**
 * File:    LivingRoom.java    
 * Author:  Pat Ripley
 * 
 * Program Description:    
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 * 
 * Class Description:
 * Living Room object class.
 * 
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// class LivingRoom
public class LivingRoom {
    
    // ivars
    private String livingroom_comments;
    
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
    
    private int ceilingfan_score;
    private String ceilingfan_notes;
    
    private int patio_score;
    private String patio_door_type;
    private String patio_door_variety;
    private String patio_door_material;
    private String patio_notes;
    
    private int fireplace_score;
    private String fireplace_type;
    private String fireplace_notes;
    
    //private int furnishings_score;
    //private String furnishings_notes;
    
    /**
     * Default constructor
     */
    public LivingRoom() {
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
     * @return the ceilingfan_score
     */
    public int getCeilingfan_score() {
        return ceilingfan_score;
    }

    /**
     * @param ceilingfan_score the ceilingfan_score to set
     */
    public void setCeilingfan_score(int ceilingfan_score) {
        this.ceilingfan_score = ceilingfan_score;
    }

    /**
     * @return the ceilingfan_notes
     */
    public String getCeilingfan_notes() {
        return ceilingfan_notes;
    }

    /**
     * @param ceilingfan_notes the ceilingfan_notes to set
     */
    public void setCeilingfan_notes(String ceilingfan_notes) {
        this.ceilingfan_notes = ceilingfan_notes;
    }

    /**
     * @return the patio_score
     */
    public int getPatio_score() {
        return patio_score;
    }

    /**
     * @param patio_score the patio_score to set
     */
    public void setPatio_score(int patio_score) {
        this.patio_score = patio_score;
    }

    /**
     * @return the patio_notes
     */
    public String getPatio_notes() {
        return patio_notes;
    }

    /**
     * @param patio_notes the patio_notes to set
     */
    public void setPatio_notes(String patio_notes) {
        this.patio_notes = patio_notes;
    }

    /**
     * @return the fireplace_score
     */
    public int getFireplace_score() {
        return fireplace_score;
    }

    /**
     * @param fireplace_score the fireplace_score to set
     */
    public void setFireplace_score(int fireplace_score) {
        this.fireplace_score = fireplace_score;
    }

    /**
     * @return the fireplace_notes
     */
    public String getFireplace_notes() {
        return fireplace_notes;
    }

    /**
     * @param fireplace_notes the fireplace_notes to set
     */
    public void setFireplace_notes(String fireplace_notes) {
        this.fireplace_notes = fireplace_notes;
    }

//    /**
//     * @return the furnishings_score
//     */
//    public int getFurnishings_score() {
//        return furnishings_score;
//    }
//
//    /**
//     * @param furnishings_score the furnishings_score to set
//     */
//    public void setFurnishings_score(int furnishings_score) {
//        this.furnishings_score = furnishings_score;
//    }
//
//    /**
//     * @return the furnishings_notes
//     */
//    public String getFurnishings_notes() {
//        return furnishings_notes;
//    }
//
//    /**
//     * @param furnishings_notes the furnishings_notes to set
//     */
//    public void setFurnishings_notes(String furnishings_notes) {
//        this.furnishings_notes = furnishings_notes;
//    }

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
     * @return the patio_door_type
     */
    public String getPatio_door_type() {
        return patio_door_type;
    }

    /**
     * @param patio_door_type the patio_door_type to set
     */
    public void setPatio_door_type(String patio_door_type) {
        this.patio_door_type = patio_door_type;
    }

    /**
     * @return the patio_door_variety
     */
    public String getPatio_door_variety() {
        return patio_door_variety;
    }

    /**
     * @param patio_door_variety the patio_door_variety to set
     */
    public void setPatio_door_variety(String patio_door_variety) {
        this.patio_door_variety = patio_door_variety;
    }

    /**
     * @return the patio_door_material
     */
    public String getPatio_door_material() {
        return patio_door_material;
    }

    /**
     * @param patio_door_material the patio_door_material to set
     */
    public void setPatio_door_material(String patio_door_material) {
        this.patio_door_material = patio_door_material;
    }

    /**
     * @return the fireplace_type
     */
    public String getFireplace_type() {
        return fireplace_type;
    }

    /**
     * @param fireplace_type the fireplace_type to set
     */
    public void setFireplace_type(String fireplace_type) {
        this.fireplace_type = fireplace_type;
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
     * @return the livingroom_comments
     */
    public String getLivingroom_comments() {
        return livingroom_comments;
    }

    /**
     * @param livingroom_comments the livingroom_comments to set
     */
    public void setLivingroom_comments(String livingroom_comments) {
        this.livingroom_comments = livingroom_comments;
    }
    
    
}
