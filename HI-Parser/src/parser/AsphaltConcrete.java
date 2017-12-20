/**
 * File:    AsphaltConcrete.java    
 * Author:  Pat Ripley
 * 
 * Program Description:    
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 * 
 * Class Description:
 * Asphalt/Concrete object class.
 * 
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// class AsphaltConcrete
public class AsphaltConcrete {
    
    private int overlay_score;
    private String overlay_notes;
    
    private int parkingbumpers_score;
    private String parkingbumpers_notes;
    
    private int striping_score;
    private String striping_notes;
    
    private int sealing_score;
    private String sealing_notes;
    
    private int crackfillings_score;
    private String crackfillings_notes;
    
    private int potholes_score;
    private String potholes_notes;
    
    private int sidewalks_score;
    private String sidewalks_notes;
    
    private int triphazards_score;
    private String triphazards_notes;
    
    private int handicapparking_score;
    private String handicapparking_notes;
    
    /**
     * Default constructor
     */
    public AsphaltConcrete() {
        // empty
    }

    /**
     * @return the overlay_score
     */
    public int getOverlay_score() {
        return overlay_score;
    }

    /**
     * @param overlay_score the overlay_score to set
     */
    public void setOverlay_score(int overlay_score) {
        this.overlay_score = overlay_score;
    }

    /**
     * @return the overlay_notes
     */
    public String getOverlay_notes() {
        return overlay_notes;
    }

    /**
     * @param overlay_notes the overlay_notes to set
     */
    public void setOverlay_notes(String overlay_notes) {
        this.overlay_notes = overlay_notes;
    }

    /**
     * @return the parkingbumpers_score
     */
    public int getParkingbumpers_score() {
        return parkingbumpers_score;
    }

    /**
     * @param parkingbumpers_score the parkingbumpers_score to set
     */
    public void setParkingbumpers_score(int parkingbumpers_score) {
        this.parkingbumpers_score = parkingbumpers_score;
    }

    /**
     * @return the parkingbumpers_notes
     */
    public String getParkingbumpers_notes() {
        return parkingbumpers_notes;
    }

    /**
     * @param parkingbumpers_notes the parkingbumpers_notes to set
     */
    public void setParkingbumpers_notes(String parkingbumpers_notes) {
        this.parkingbumpers_notes = parkingbumpers_notes;
    }

    /**
     * @return the striping_score
     */
    public int getStriping_score() {
        return striping_score;
    }

    /**
     * @param striping_score the striping_score to set
     */
    public void setStriping_score(int striping_score) {
        this.striping_score = striping_score;
    }

    /**
     * @return the striping_notes
     */
    public String getStriping_notes() {
        return striping_notes;
    }

    /**
     * @param striping_notes the striping_notes to set
     */
    public void setStriping_notes(String striping_notes) {
        this.striping_notes = striping_notes;
    }

    /**
     * @return the sealing_score
     */
    public int getSealing_score() {
        return sealing_score;
    }

    /**
     * @param sealing_score the sealing_score to set
     */
    public void setSealing_score(int sealing_score) {
        this.sealing_score = sealing_score;
    }

    /**
     * @return the sealing_notes
     */
    public String getSealing_notes() {
        return sealing_notes;
    }

    /**
     * @param sealing_notes the sealing_notes to set
     */
    public void setSealing_notes(String sealing_notes) {
        this.sealing_notes = sealing_notes;
    }

    /**
     * @return the crackfillings_score
     */
    public int getCrackfillings_score() {
        return crackfillings_score;
    }

    /**
     * @param crackfillings_score the crackfillings_score to set
     */
    public void setCrackfillings_score(int crackfillings_score) {
        this.crackfillings_score = crackfillings_score;
    }

    /**
     * @return the crackfillings_notes
     */
    public String getCrackfillings_notes() {
        return crackfillings_notes;
    }

    /**
     * @param crackfillings_notes the crackfillings_notes to set
     */
    public void setCrackfillings_notes(String crackfillings_notes) {
        this.crackfillings_notes = crackfillings_notes;
    }

    /**
     * @return the potholes_score
     */
    public int getPotholes_score() {
        return potholes_score;
    }

    /**
     * @param potholes_score the potholes_score to set
     */
    public void setPotholes_score(int potholes_score) {
        this.potholes_score = potholes_score;
    }

    /**
     * @return the potholes_notes
     */
    public String getPotholes_notes() {
        return potholes_notes;
    }

    /**
     * @param potholes_notes the potholes_notes to set
     */
    public void setPotholes_notes(String potholes_notes) {
        this.potholes_notes = potholes_notes;
    }

    /**
     * @return the sidewalks_score
     */
    public int getSidewalks_score() {
        return sidewalks_score;
    }

    /**
     * @param sidewalks_score the sidewalks_score to set
     */
    public void setSidewalks_score(int sidewalks_score) {
        this.sidewalks_score = sidewalks_score;
    }

    /**
     * @return the sidewalks_notes
     */
    public String getSidewalks_notes() {
        return sidewalks_notes;
    }

    /**
     * @param sidewalks_notes the sidewalks_notes to set
     */
    public void setSidewalks_notes(String sidewalks_notes) {
        this.sidewalks_notes = sidewalks_notes;
    }

    /**
     * @return the triphazards_score
     */
    public int getTriphazards_score() {
        return triphazards_score;
    }

    /**
     * @param triphazards_score the triphazards_score to set
     */
    public void setTriphazards_score(int triphazards_score) {
        this.triphazards_score = triphazards_score;
    }

    /**
     * @return the triphazards_notes
     */
    public String getTriphazards_notes() {
        return triphazards_notes;
    }

    /**
     * @param triphazards_notes the triphazards_notes to set
     */
    public void setTriphazards_notes(String triphazards_notes) {
        this.triphazards_notes = triphazards_notes;
    }

    /**
     * @return the handicapparking_score
     */
    public int getHandicapparking_score() {
        return handicapparking_score;
    }

    /**
     * @param handicapparking_score the handicapparking_score to set
     */
    public void setHandicapparking_score(int handicapparking_score) {
        this.handicapparking_score = handicapparking_score;
    }

    /**
     * @return the handicapparking_notes
     */
    public String getHandicapparking_notes() {
        return handicapparking_notes;
    }

    /**
     * @param handicapparking_notes the handicapparking_notes to set
     */
    public void setHandicapparking_notes(String handicapparking_notes) {
        this.handicapparking_notes = handicapparking_notes;
    }
    
    
    
    
}
