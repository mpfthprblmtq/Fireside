/**
 * File:    InteriorHallway.java    
 * Author:  Pat Ripley
 * 
 * Program Description:    
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 * 
 * Class Description:
 * Interior Hallway object class.
 * 
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// class InteriorHallway
public class InteriorHallway {
    
    // ivars
    private int floor_score;
    private String floor_type;
    private String floor_notes;
    
    private int doors_score;
    private String doors_type;
    private String doors_variety;
    private String doors_material;
    private String doors_notes;
    
    private int door_hardware_score;
    private String door_hardware_notes;
    
    private int wallsceilings_score;
    private String walls_type;
    private String ceiling_type;
    private String wallsceilings_notes;
    
    private int lightfixture_score;
    private String lightfixture_type;
    private String lightfixture_notes;
    
    private int handrails_score;
    private String handrails_type;
    private String handrails_notes;
    
    private int moldings_score;
    private String moldings_notes;
    
    private int smoke_detectors_score;
    private String smoke_detectors_notes;
    
    private int fire_extinguisher_score;
    private String fire_extinguisher_notes;
    
    private int staircoverings_score;
    private String staircoverings_type;
    private String staircoverings_notes;
    
    private int doorknockers_score;
    private String doorknockers_notes;
    
    private int unitnumbers_score;
    private String unitnumbers_notes;
    
    private String handicapaccessible;
    private String handicapaccessible_notes;
    
    private String unaccounted_items;
    
    /**
     * Default constructor
     */
    public InteriorHallway() {
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
     * @return the doors_score
     */
    public int getDoors_score() {
        return doors_score;
    }

    /**
     * @param doors_score the doors_score to set
     */
    public void setDoors_score(int doors_score) {
        this.doors_score = doors_score;
    }

    /**
     * @return the doors_notes
     */
    public String getDoors_notes() {
        return doors_notes;
    }

    /**
     * @param doors_notes the doors_notes to set
     */
    public void setDoors_notes(String doors_notes) {
        this.doors_notes = doors_notes;
    }

    /**
     * @return the door_hardware_score
     */
    public int getDoor_hardware_score() {
        return door_hardware_score;
    }

    /**
     * @param door_hardware_score the door_hardware_score to set
     */
    public void setDoor_hardware_score(int door_hardware_score) {
        this.door_hardware_score = door_hardware_score;
    }

    /**
     * @return the door_hardware_notes
     */
    public String getDoor_hardware_notes() {
        return door_hardware_notes;
    }

    /**
     * @param door_hardware_notes the door_hardware_notes to set
     */
    public void setDoor_hardware_notes(String door_hardware_notes) {
        this.door_hardware_notes = door_hardware_notes;
    }

    /**
     * @return the wallsceilings_score
     */
    public int getWallsceilings_score() {
        return wallsceilings_score;
    }

    /**
     * @param wallsceilings_score the wallsceilings_score to set
     */
    public void setWallsceilings_score(int wallsceilings_score) {
        this.wallsceilings_score = wallsceilings_score;
    }

    /**
     * @return the wallsceilings_notes
     */
    public String getWallsceilings_notes() {
        return wallsceilings_notes;
    }

    /**
     * @param wallsceilings_notes the wallsceilings_notes to set
     */
    public void setWallsceilings_notes(String wallsceilings_notes) {
        this.wallsceilings_notes = wallsceilings_notes;
    }

    /**
     * @return the lightfixture_score
     */
    public int getLightfixture_score() {
        return lightfixture_score;
    }

    /**
     * @param lightfixture_score the lightfixture_score to set
     */
    public void setLightfixture_score(int lightfixture_score) {
        this.lightfixture_score = lightfixture_score;
    }

    /**
     * @return the lightfixture_notes
     */
    public String getLightfixture_notes() {
        return lightfixture_notes;
    }

    /**
     * @param lightfixture_notes the lightfixture_notes to set
     */
    public void setLightfixture_notes(String lightfixture_notes) {
        this.lightfixture_notes = lightfixture_notes;
    }

    /**
     * @return the handrails_score
     */
    public int getHandrails_score() {
        return handrails_score;
    }

    /**
     * @param handrails_score the handrails_score to set
     */
    public void setHandrails_score(int handrails_score) {
        this.handrails_score = handrails_score;
    }

    /**
     * @return the handrails_notes
     */
    public String getHandrails_notes() {
        return handrails_notes;
    }

    /**
     * @param handrails_notes the handrails_notes to set
     */
    public void setHandrails_notes(String handrails_notes) {
        this.handrails_notes = handrails_notes;
    }

    /**
     * @return the moldings_score
     */
    public int getMoldings_score() {
        return moldings_score;
    }

    /**
     * @param moldings_score the moldings_score to set
     */
    public void setMoldings_score(int moldings_score) {
        this.moldings_score = moldings_score;
    }

    /**
     * @return the moldings_notes
     */
    public String getMoldings_notes() {
        return moldings_notes;
    }

    /**
     * @param moldings_notes the moldings_notes to set
     */
    public void setMoldings_notes(String moldings_notes) {
        this.moldings_notes = moldings_notes;
    }

    /**
     * @return the smoke_detectors_score
     */
    public int getSmoke_detectors_score() {
        return smoke_detectors_score;
    }

    /**
     * @param smoke_detectors_score the smoke_detectors_score to set
     */
    public void setSmoke_detectors_score(int smoke_detectors_score) {
        this.smoke_detectors_score = smoke_detectors_score;
    }

    /**
     * @return the smoke_detectors_notes
     */
    public String getSmoke_detectors_notes() {
        return smoke_detectors_notes;
    }

    /**
     * @param smoke_detectors_notes the smoke_detectors_notes to set
     */
    public void setSmoke_detectors_notes(String smoke_detectors_notes) {
        this.smoke_detectors_notes = smoke_detectors_notes;
    }

    /**
     * @return the fire_extinguisher_score
     */
    public int getFire_extinguisher_score() {
        return fire_extinguisher_score;
    }

    /**
     * @param fire_extinguisher_score the fire_extinguisher_score to set
     */
    public void setFire_extinguisher_score(int fire_extinguisher_score) {
        this.fire_extinguisher_score = fire_extinguisher_score;
    }

    /**
     * @return the fire_extinguisher_notes
     */
    public String getFire_extinguisher_notes() {
        return fire_extinguisher_notes;
    }

    /**
     * @param fire_extinguisher_notes the fire_extinguisher_notes to set
     */
    public void setFire_extinguisher_notes(String fire_extinguisher_notes) {
        this.fire_extinguisher_notes = fire_extinguisher_notes;
    }

    /**
     * @return the staircoverings_score
     */
    public int getStaircoverings_score() {
        return staircoverings_score;
    }

    /**
     * @param staircoverings_score the staircoverings_score to set
     */
    public void setStaircoverings_score(int staircoverings_score) {
        this.staircoverings_score = staircoverings_score;
    }

    /**
     * @return the staircoverings_notes
     */
    public String getStaircoverings_notes() {
        return staircoverings_notes;
    }

    /**
     * @param staircoverings_notes the staircoverings_notes to set
     */
    public void setStaircoverings_notes(String staircoverings_notes) {
        this.staircoverings_notes = staircoverings_notes;
    }

    /**
     * @return the doorknockers_score
     */
    public int getDoorknockers_score() {
        return doorknockers_score;
    }

    /**
     * @param doorknockers_score the doorknockers_score to set
     */
    public void setDoorknockers_score(int doorknockers_score) {
        this.doorknockers_score = doorknockers_score;
    }

    /**
     * @return the doorknockers_notes
     */
    public String getDoorknockers_notes() {
        return doorknockers_notes;
    }

    /**
     * @param doorknockers_notes the doorknockers_notes to set
     */
    public void setDoorknockers_notes(String doorknockers_notes) {
        this.doorknockers_notes = doorknockers_notes;
    }

    /**
     * @return the unitnumbers_score
     */
    public int getUnitnumbers_score() {
        return unitnumbers_score;
    }

    /**
     * @param unitnumbers_score the unitnumbers_score to set
     */
    public void setUnitnumbers_score(int unitnumbers_score) {
        this.unitnumbers_score = unitnumbers_score;
    }

    /**
     * @return the unitnumbers_notes
     */
    public String getUnitnumbers_notes() {
        return unitnumbers_notes;
    }

    /**
     * @param unitnumbers_notes the unitnumbers_notes to set
     */
    public void setUnitnumbers_notes(String unitnumbers_notes) {
        this.unitnumbers_notes = unitnumbers_notes;
    }

    /**
     * @return the handicapaccessible
     */
    public String getHandicapaccessible() {
        return handicapaccessible;
    }

    /**
     * @param handicapaccessible the handicapaccessible to set
     */
    public void setHandicapaccessible(String handicapaccessible) {
        this.handicapaccessible = handicapaccessible;
    }

    /**
     * @return the handicapaccessible_notes
     */
    public String getHandicapaccessible_notes() {
        return handicapaccessible_notes;
    }

    /**
     * @param handicapaccessible_notes the handicapaccessible_notes to set
     */
    public void setHandicapaccessible_notes(String handicapaccessible_notes) {
        this.handicapaccessible_notes = handicapaccessible_notes;
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
     * @return the doors_type
     */
    public String getDoors_type() {
        return doors_type;
    }

    /**
     * @param doors_type the doors_type to set
     */
    public void setDoors_type(String doors_type) {
        this.doors_type = doors_type;
    }

    /**
     * @return the doors_variety
     */
    public String getDoors_variety() {
        return doors_variety;
    }

    /**
     * @param doors_variety the doors_variety to set
     */
    public void setDoors_variety(String doors_variety) {
        this.doors_variety = doors_variety;
    }

    /**
     * @return the doors_material
     */
    public String getDoors_material() {
        return doors_material;
    }

    /**
     * @param doors_material the doors_material to set
     */
    public void setDoors_material(String doors_material) {
        this.doors_material = doors_material;
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
     * @return the lightfixture_type
     */
    public String getLightfixture_type() {
        return lightfixture_type;
    }

    /**
     * @param lightfixture_type the lightfixture_type to set
     */
    public void setLightfixture_type(String lightfixture_type) {
        this.lightfixture_type = lightfixture_type;
    }

    /**
     * @return the handrails_type
     */
    public String getHandrails_type() {
        return handrails_type;
    }

    /**
     * @param handrails_type the handrails_type to set
     */
    public void setHandrails_type(String handrails_type) {
        this.handrails_type = handrails_type;
    }

    /**
     * @return the staircoverings_type
     */
    public String getStaircoverings_type() {
        return staircoverings_type;
    }

    /**
     * @param staircoverings_type the staircoverings_type to set
     */
    public void setStaircoverings_type(String staircoverings_type) {
        this.staircoverings_type = staircoverings_type;
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
} // end InteriorHallway.java
