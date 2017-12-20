/**
 * File:    Overall.java    
 * Author:  Pat Ripley
 * 
 * Program Description:    
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 * 
 * Class Description:
 * Overall object class.
 * 
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// class Overall
public class Overall {
    
    // ivars
    private String occupancy;
    
    private int cleanlinesss_score;
    private String cleanliness_notes;
    
    private int furnishings_score;
    private String furnishings_notes;
    
    private boolean pets_none = false;
    private boolean pets_dog = false;
    private boolean pets_cat = false;
    private boolean pets_other = false;
    private String pets_number;
    
    private boolean smoker = false;
    private boolean hoarder = false;
    private boolean hasSmokeDetectors = false;
    
    private String letter_grade;
    
    private String general_comments;
    
    private String unaccounted_sections;
    
    /**
     * Default constructor
     */
    public Overall() {
        // empty
    }

    /**
     * @return the cleanlinesss_score
     */
    public int getCleanlinesss_score() {
        return cleanlinesss_score;
    }

    /**
     * @param cleanlinesss_score the cleanlinesss_score to set
     */
    public void setCleanlinesss_score(int cleanlinesss_score) {
        this.cleanlinesss_score = cleanlinesss_score;
    }

    /**
     * @return the cleanliness_notes
     */
    public String getCleanliness_notes() {
        return cleanliness_notes;
    }

    /**
     * @param cleanliness_notes the cleanliness_notes to set
     */
    public void setCleanliness_notes(String cleanliness_notes) {
        this.cleanliness_notes = cleanliness_notes;
    }

    /**
     * @return the furnishings_score
     */
    public int getFurnishings_score() {
        return furnishings_score;
    }

    /**
     * @param furnishings_score the furnishings_score to set
     */
    public void setFurnishings_score(int furnishings_score) {
        this.furnishings_score = furnishings_score;
    }

    /**
     * @return the furnishings_notes
     */
    public String getFurnishings_notes() {
        return furnishings_notes;
    }

    /**
     * @param furnishings_notes the furnishings_notes to set
     */
    public void setFurnishings_notes(String furnishings_notes) {
        this.furnishings_notes = furnishings_notes;
    }

    /**
     * @return the pets_none
     */
    public boolean isPets_none() {
        return pets_none;
    }

    /**
     * @param pets_none the pets_none to set
     */
    public void setPets_none(boolean pets_none) {
        this.pets_none = pets_none;
    }

    /**
     * @return the pets_dog
     */
    public boolean isPets_dog() {
        return pets_dog;
    }

    /**
     * @param pets_dog the pets_dog to set
     */
    public void setPets_dog(boolean pets_dog) {
        this.pets_dog = pets_dog;
    }

    /**
     * @return the pets_cat
     */
    public boolean isPets_cat() {
        return pets_cat;
    }

    /**
     * @param pets_cat the pets_cat to set
     */
    public void setPets_cat(boolean pets_cat) {
        this.pets_cat = pets_cat;
    }

    /**
     * @return the pets_other
     */
    public boolean isPets_other() {
        return pets_other;
    }

    /**
     * @param pets_other the pets_other to set
     */
    public void setPets_other(boolean pets_other) {
        this.pets_other = pets_other;
    }

    /**
     * @return the pets_number
     */
    public String getPets_number() {
        return pets_number;
    }

    /**
     * @param pets_number the pets_number to set
     */
    public void setPets_number(String pets_number) {
        this.pets_number = pets_number;
    }

    /**
     * @return the smoker
     */
    public boolean isSmoker() {
        return smoker;
    }

    /**
     * @param smoker the smoker to set
     */
    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    /**
     * @return the hoarder
     */
    public boolean isHoarder() {
        return hoarder;
    }

    /**
     * @param hoarder the hoarder to set
     */
    public void setHoarder(boolean hoarder) {
        this.hoarder = hoarder;
    }

    /**
     * @return the hasSmokeDetectors
     */
    public boolean isHasSmokeDetectors() {
        return hasSmokeDetectors;
    }

    /**
     * @param hasSmokeDetectors the hasSmokeDetectors to set
     */
    public void setHasSmokeDetectors(boolean hasSmokeDetectors) {
        this.hasSmokeDetectors = hasSmokeDetectors;
    }

    /**
     * @return the letter_grade
     */
    public String getLetter_grade() {
        return letter_grade;
    }

    /**
     * @param letter_grade the letter_grade to set
     */
    public void setLetter_grade(String letter_grade) {
        this.letter_grade = letter_grade;
    }

    /**
     * @return the general_comments
     */
    public String getGeneral_comments() {
        return general_comments;
    }

    /**
     * @param general_comments the general_comments to set
     */
    public void setGeneral_comments(String general_comments) {
        this.general_comments = general_comments;
    }

    /**
     * @return the occupancy
     */
    public String getOccupancy() {
        return occupancy;
    }

    /**
     * @param occupancy the occupancy to set
     */
    public void setOccupancy(String occupancy) {
        this.occupancy = occupancy;
    }

    /**
     * @return the unaccounted_sections
     */
    public String getUnaccounted_sections() {
        return unaccounted_sections;
    }

    /**
     * @param unaccounted_sections the unaccounted_sections to set
     */
    public void setUnaccounted_sections(String unaccounted_sections) {
        this.unaccounted_sections = unaccounted_sections;
    }
}
