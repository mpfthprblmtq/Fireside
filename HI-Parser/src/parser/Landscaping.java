/**
 * File:    Landscaping.java    
 * Author:  Pat Ripley
 * 
 * Program Description:    
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 * 
 * Class Description:
 * Landscaping object class.
 * 
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// class Landscaping
public class Landscaping {
    
    // ivars
    private int trees_score;
    private String trees_notes;
    
    private int bushes_score;
    private String bushes_notes;
    
    private int mulchgravel_score;
    private String mulchgravel_notes;
    
    private int retainingwalls_score;
    private String retainingwalls_material;
    private String retainingwalls_notes;
    
    private int irrigation_score;
    private String irrigation_notes;
    
    private int drainage_score;
    private String drainage_notes;
    
    private int waterpooling_score;
    private String waterpooling_notes;
    
    private int weeds_score;
    private String weeds_notes;
    
    private int picnicarea_score;
    private String picnicarea_notes;
    
    private int lighting_score;
    private String lighting_notes;
    
    private int debris_score;
    private String debris_notes;
    
    private int grills_score;
    private String grills_notes;
    
    private int dogpark_score;
    private String dogpark_notes;
    
    private int dogpark_fence_score;
    private String dogpark_fence_material;
    private String dogpark_fence_notes;
    
    /**
     * Default constructor
     */
    public Landscaping() {
        
    }

    /**
     * @return the trees_score
     */
    public int getTrees_score() {
        return trees_score;
    }

    /**
     * @param trees_score the trees_score to set
     */
    public void setTrees_score(int trees_score) {
        this.trees_score = trees_score;
    }

    /**
     * @return the trees_notes
     */
    public String getTrees_notes() {
        return trees_notes;
    }

    /**
     * @param trees_notes the trees_notes to set
     */
    public void setTrees_notes(String trees_notes) {
        this.trees_notes = trees_notes;
    }

    /**
     * @return the bushes_score
     */
    public int getBushes_score() {
        return bushes_score;
    }

    /**
     * @param bushes_score the bushes_score to set
     */
    public void setBushes_score(int bushes_score) {
        this.bushes_score = bushes_score;
    }

    /**
     * @return the bushes_notes
     */
    public String getBushes_notes() {
        return bushes_notes;
    }

    /**
     * @param bushes_notes the bushes_notes to set
     */
    public void setBushes_notes(String bushes_notes) {
        this.bushes_notes = bushes_notes;
    }

    /**
     * @return the mulchgravel_score
     */
    public int getMulchgravel_score() {
        return mulchgravel_score;
    }

    /**
     * @param mulchgravel_score the mulchgravel_score to set
     */
    public void setMulchgravel_score(int mulchgravel_score) {
        this.mulchgravel_score = mulchgravel_score;
    }

    /**
     * @return the mulchgravel_notes
     */
    public String getMulchgravel_notes() {
        return mulchgravel_notes;
    }

    /**
     * @param mulchgravel_notes the mulchgravel_notes to set
     */
    public void setMulchgravel_notes(String mulchgravel_notes) {
        this.mulchgravel_notes = mulchgravel_notes;
    }

    /**
     * @return the retainingwalls_score
     */
    public int getRetainingwalls_score() {
        return retainingwalls_score;
    }

    /**
     * @param retainingwalls_score the retainingwalls_score to set
     */
    public void setRetainingwalls_score(int retainingwalls_score) {
        this.retainingwalls_score = retainingwalls_score;
    }

    /**
     * @return the retainingwalls_notes
     */
    public String getRetainingwalls_notes() {
        return retainingwalls_notes;
    }

    /**
     * @param retainingwalls_notes the retainingwalls_notes to set
     */
    public void setRetainingwalls_notes(String retainingwalls_notes) {
        this.retainingwalls_notes = retainingwalls_notes;
    }

    /**
     * @return the irrigation_score
     */
    public int getIrrigation_score() {
        return irrigation_score;
    }

    /**
     * @param irrigation_score the irrigation_score to set
     */
    public void setIrrigation_score(int irrigation_score) {
        this.irrigation_score = irrigation_score;
    }

    /**
     * @return the irrigation_notes
     */
    public String getIrrigation_notes() {
        return irrigation_notes;
    }

    /**
     * @param irrigation_notes the irrigation_notes to set
     */
    public void setIrrigation_notes(String irrigation_notes) {
        this.irrigation_notes = irrigation_notes;
    }

    /**
     * @return the drainage_score
     */
    public int getDrainage_score() {
        return drainage_score;
    }

    /**
     * @param drainage_score the drainage_score to set
     */
    public void setDrainage_score(int drainage_score) {
        this.drainage_score = drainage_score;
    }

    /**
     * @return the drainage_notes
     */
    public String getDrainage_notes() {
        return drainage_notes;
    }

    /**
     * @param drainage_notes the drainage_notes to set
     */
    public void setDrainage_notes(String drainage_notes) {
        this.drainage_notes = drainage_notes;
    }

    /**
     * @return the waterpooling_score
     */
    public int getWaterpooling_score() {
        return waterpooling_score;
    }

    /**
     * @param waterpooling_score the waterpooling_score to set
     */
    public void setWaterpooling_score(int waterpooling_score) {
        this.waterpooling_score = waterpooling_score;
    }

    /**
     * @return the waterpooling_notes
     */
    public String getWaterpooling_notes() {
        return waterpooling_notes;
    }

    /**
     * @param waterpooling_notes the waterpooling_notes to set
     */
    public void setWaterpooling_notes(String waterpooling_notes) {
        this.waterpooling_notes = waterpooling_notes;
    }

    /**
     * @return the weeds_score
     */
    public int getWeeds_score() {
        return weeds_score;
    }

    /**
     * @param weeds_score the weeds_score to set
     */
    public void setWeeds_score(int weeds_score) {
        this.weeds_score = weeds_score;
    }

    /**
     * @return the weeds_notes
     */
    public String getWeeds_notes() {
        return weeds_notes;
    }

    /**
     * @param weeds_notes the weeds_notes to set
     */
    public void setWeeds_notes(String weeds_notes) {
        this.weeds_notes = weeds_notes;
    }

    /**
     * @return the picnicarea_score
     */
    public int getPicnicarea_score() {
        return picnicarea_score;
    }

    /**
     * @param picnicarea_score the picnicarea_score to set
     */
    public void setPicnicarea_score(int picnicarea_score) {
        this.picnicarea_score = picnicarea_score;
    }

    /**
     * @return the picnicarea_notes
     */
    public String getPicnicarea_notes() {
        return picnicarea_notes;
    }

    /**
     * @param picnicarea_notes the picnicarea_notes to set
     */
    public void setPicnicarea_notes(String picnicarea_notes) {
        this.picnicarea_notes = picnicarea_notes;
    }

    /**
     * @return the lighting_score
     */
    public int getLighting_score() {
        return lighting_score;
    }

    /**
     * @param lighting_score the lighting_score to set
     */
    public void setLighting_score(int lighting_score) {
        this.lighting_score = lighting_score;
    }

    /**
     * @return the lighting_notes
     */
    public String getLighting_notes() {
        return lighting_notes;
    }

    /**
     * @param lighting_notes the lighting_notes to set
     */
    public void setLighting_notes(String lighting_notes) {
        this.lighting_notes = lighting_notes;
    }

    /**
     * @return the debris_score
     */
    public int getDebris_score() {
        return debris_score;
    }

    /**
     * @param debris_score the debris_score to set
     */
    public void setDebris_score(int debris_score) {
        this.debris_score = debris_score;
    }

    /**
     * @return the debris_notes
     */
    public String getDebris_notes() {
        return debris_notes;
    }

    /**
     * @param debris_notes the debris_notes to set
     */
    public void setDebris_notes(String debris_notes) {
        this.debris_notes = debris_notes;
    }

    /**
     * @return the retainingwalls_material
     */
    public String getRetainingwalls_material() {
        return retainingwalls_material;
    }

    /**
     * @param retainingwalls_material the retainingwalls_material to set
     */
    public void setRetainingwalls_material(String retainingwalls_material) {
        this.retainingwalls_material = retainingwalls_material;
    }

    /**
     * @return the grills_score
     */
    public int getGrills_score() {
        return grills_score;
    }

    /**
     * @param grills_score the grills_score to set
     */
    public void setGrills_score(int grills_score) {
        this.grills_score = grills_score;
    }

    /**
     * @return the grills_notes
     */
    public String getGrills_notes() {
        return grills_notes;
    }

    /**
     * @param grills_notes the grills_notes to set
     */
    public void setGrills_notes(String grills_notes) {
        this.grills_notes = grills_notes;
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
     * @return the dogpark_fence_score
     */
    public int getDogpark_fence_score() {
        return dogpark_fence_score;
    }

    /**
     * @param dogpark_fence_score the dogpark_fence_score to set
     */
    public void setDogpark_fence_score(int dogpark_fence_score) {
        this.dogpark_fence_score = dogpark_fence_score;
    }

    /**
     * @return the dogpark_fence_material
     */
    public String getDogpark_fence_material() {
        return dogpark_fence_material;
    }

    /**
     * @param dogpark_fence_material the dogpark_fence_material to set
     */
    public void setDogpark_fence_material(String dogpark_fence_material) {
        this.dogpark_fence_material = dogpark_fence_material;
    }

    /**
     * @return the dogpark_fence_notes
     */
    public String getDogpark_fence_notes() {
        return dogpark_fence_notes;
    }

    /**
     * @param dogpark_fence_notes the dogpark_fence_notes to set
     */
    public void setDogpark_fence_notes(String dogpark_fence_notes) {
        this.dogpark_fence_notes = dogpark_fence_notes;
    }
    
    
}
