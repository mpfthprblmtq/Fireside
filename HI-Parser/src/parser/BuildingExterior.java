/**
 * File:    BuildingExterior.java    
 * Author:  Pat Ripley
 * 
 * Program Description:    
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 * 
 * Class Description:
 * Building Exterior object class.
 * 
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// class BuildingExterior
public class BuildingExterior {

    // ivars
    private int gutters_score;
    private String gutters_notes;
    
    private int downspouts_score;
    private String downspouts_notes;
    
    private int soffit_score;
    private String soffit_notes;
    
    private int fascia_score;
    private String fascia_notes;
    
    private int siding_score;
    private String siding_notes;
    
    private int roofs_score;
    private String roofs_notes;
    
    private int ventpipescaging_score;
    private String ventpipescaging_notes;
    
    private int gutterdrains_score;
    private String gutterdrains_notes;
    
    private int debris_score;
    private String debris_notes;
    
    private int windows_score;
    private String windows_number;
    private String windows_type;
    private String windows_notes;
    
    private int windowweatherstripping_score;
    private String windowweatherstripping_notes;
    
    private int foundationcracks_score;
    private String foundationcracks_notes;
    
    private int foundationleaks_score;
    private String foundationleaks_notes;
    
    private int porchposts_score;
    private String porchposts_material;
    private String porchposts_notes;
    
    private int spindles_score;
    private String spindles_notes;
    
    private int mailbox_score;
    private String mailbox_notes;
    
    private int brickwork_score;
    private String brickwork_notes;
    
    private int termites_score;
    private String termites_notes;
    
    private int doors_score;
    private String doors_type;
    private String doors_variety;
    private String doors_material;
    private String doors_notes;
    
    private int doorhardware_score;
    private String doorhardware_notes;
    
    private int satellites_score;
    private String satellites_notes;
    
    private int exposedwiring_score;
    private String exposedwiring_notes;
    
    private int decks_score;
    private String decks_notes;
    
    private int deckflashing_score;
    private String deckflashing_notes;
    
    private int lighting_score;
    private String lighting_notes;
    
    private String handicapaccessible;
    private String handicapaccessible_notes;
    
    private int stairs_score;
    private String stairs_material;
    private String stairs_notes;
    
    private int handrails_score;
    private String handrails_material;
    private String handrails_notes;
    
    private int exteriorunitnumbers_score;
    private String exteriorunitnumbers_notes;
    
    private int hvac_score;
    private String hvac_notes;
    
    private int windowwells_score;
    private String windowwells_notes;
    
    private int exterioraccess_score;
    private String exterioraccess_notes;
    
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
     * Default constructor
     */
    public BuildingExterior() {
        // empty
    }

    /**
     * @return the gutters_score
     */
    public int getGutters_score() {
        return gutters_score;
    }

    /**
     * @param gutters_score the gutters_score to set
     */
    public void setGutters_score(int gutters_score) {
        this.gutters_score = gutters_score;
    }

    /**
     * @return the gutters_notes
     */
    public String getGutters_notes() {
        return gutters_notes;
    }

    /**
     * @param gutters_notes the gutters_notes to set
     */
    public void setGutters_notes(String gutters_notes) {
        this.gutters_notes = gutters_notes;
    }

    /**
     * @return the downspouts_score
     */
    public int getDownspouts_score() {
        return downspouts_score;
    }

    /**
     * @param downspouts_score the downspouts_score to set
     */
    public void setDownspouts_score(int downspouts_score) {
        this.downspouts_score = downspouts_score;
    }

    /**
     * @return the downspouts_notes
     */
    public String getDownspouts_notes() {
        return downspouts_notes;
    }

    /**
     * @param downspouts_notes the downspouts_notes to set
     */
    public void setDownspouts_notes(String downspouts_notes) {
        this.downspouts_notes = downspouts_notes;
    }

    /**
     * @return the soffit_score
     */
    public int getSoffit_score() {
        return soffit_score;
    }

    /**
     * @param soffit_score the soffit_score to set
     */
    public void setSoffit_score(int soffit_score) {
        this.soffit_score = soffit_score;
    }

    /**
     * @return the soffit_notes
     */
    public String getSoffit_notes() {
        return soffit_notes;
    }

    /**
     * @param soffit_notes the soffit_notes to set
     */
    public void setSoffit_notes(String soffit_notes) {
        this.soffit_notes = soffit_notes;
    }

    /**
     * @return the fascia_score
     */
    public int getFascia_score() {
        return fascia_score;
    }

    /**
     * @param fascia_score the fascia_score to set
     */
    public void setFascia_score(int fascia_score) {
        this.fascia_score = fascia_score;
    }

    /**
     * @return the fascia_notes
     */
    public String getFascia_notes() {
        return fascia_notes;
    }

    /**
     * @param fascia_notes the fascia_notes to set
     */
    public void setFascia_notes(String fascia_notes) {
        this.fascia_notes = fascia_notes;
    }

    /**
     * @return the siding_score
     */
    public int getSiding_score() {
        return siding_score;
    }

    /**
     * @param siding_score the siding_score to set
     */
    public void setSiding_score(int siding_score) {
        this.siding_score = siding_score;
    }

    /**
     * @return the siding_notes
     */
    public String getSiding_notes() {
        return siding_notes;
    }

    /**
     * @param siding_notes the siding_notes to set
     */
    public void setSiding_notes(String siding_notes) {
        this.siding_notes = siding_notes;
    }

    /**
     * @return the roofs_score
     */
    public int getRoofs_score() {
        return roofs_score;
    }

    /**
     * @param roofs_score the roofs_score to set
     */
    public void setRoofs_score(int roofs_score) {
        this.roofs_score = roofs_score;
    }

    /**
     * @return the roofs_notes
     */
    public String getRoofs_notes() {
        return roofs_notes;
    }

    /**
     * @param roofs_notes the roofs_notes to set
     */
    public void setRoofs_notes(String roofs_notes) {
        this.roofs_notes = roofs_notes;
    }

    /**
     * @return the ventpipescaging_score
     */
    public int getVentpipescaging_score() {
        return ventpipescaging_score;
    }

    /**
     * @param ventpipescaging_score the ventpipescaging_score to set
     */
    public void setVentpipescaging_score(int ventpipescaging_score) {
        this.ventpipescaging_score = ventpipescaging_score;
    }

    /**
     * @return the ventpipescaging_notes
     */
    public String getVentpipescaging_notes() {
        return ventpipescaging_notes;
    }

    /**
     * @param ventpipescaging_notes the ventpipescaging_notes to set
     */
    public void setVentpipescaging_notes(String ventpipescaging_notes) {
        this.ventpipescaging_notes = ventpipescaging_notes;
    }

    /**
     * @return the gutterdrains_score
     */
    public int getGutterdrains_score() {
        return gutterdrains_score;
    }

    /**
     * @param gutterdrains_score the gutterdrains_score to set
     */
    public void setGutterdrains_score(int gutterdrains_score) {
        this.gutterdrains_score = gutterdrains_score;
    }

    /**
     * @return the gutterdrains_notes
     */
    public String getGutterdrains_notes() {
        return gutterdrains_notes;
    }

    /**
     * @param gutterdrains_notes the gutterdrains_notes to set
     */
    public void setGutterdrains_notes(String gutterdrains_notes) {
        this.gutterdrains_notes = gutterdrains_notes;
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
     * @return the windowweatherstripping_score
     */
    public int getWindowweatherstripping_score() {
        return windowweatherstripping_score;
    }

    /**
     * @param windowweatherstripping_score the windowweatherstripping_score to set
     */
    public void setWindowweatherstripping_score(int windowweatherstripping_score) {
        this.windowweatherstripping_score = windowweatherstripping_score;
    }

    /**
     * @return the windowweatherstripping_notes
     */
    public String getWindowweatherstripping_notes() {
        return windowweatherstripping_notes;
    }

    /**
     * @param windowweatherstripping_notes the windowweatherstripping_notes to set
     */
    public void setWindowweatherstripping_notes(String windowweatherstripping_notes) {
        this.windowweatherstripping_notes = windowweatherstripping_notes;
    }

    /**
     * @return the foundationcracks_score
     */
    public int getFoundationcracks_score() {
        return foundationcracks_score;
    }

    /**
     * @param foundationcracks_score the foundationcracks_score to set
     */
    public void setFoundationcracks_score(int foundationcracks_score) {
        this.foundationcracks_score = foundationcracks_score;
    }

    /**
     * @return the foundationcracks_notes
     */
    public String getFoundationcracks_notes() {
        return foundationcracks_notes;
    }

    /**
     * @param foundationcracks_notes the foundationcracks_notes to set
     */
    public void setFoundationcracks_notes(String foundationcracks_notes) {
        this.foundationcracks_notes = foundationcracks_notes;
    }

    /**
     * @return the porchposts_score
     */
    public int getPorchposts_score() {
        return porchposts_score;
    }

    /**
     * @param porchposts_score the porchposts_score to set
     */
    public void setPorchposts_score(int porchposts_score) {
        this.porchposts_score = porchposts_score;
    }

    /**
     * @return the porchposts_notes
     */
    public String getPorchposts_notes() {
        return porchposts_notes;
    }

    /**
     * @param porchposts_notes the porchposts_notes to set
     */
    public void setPorchposts_notes(String porchposts_notes) {
        this.porchposts_notes = porchposts_notes;
    }

    /**
     * @return the mailbox_score
     */
    public int getMailbox_score() {
        return mailbox_score;
    }

    /**
     * @param mailbox_score the mailbox_score to set
     */
    public void setMailbox_score(int mailbox_score) {
        this.mailbox_score = mailbox_score;
    }

    /**
     * @return the mailbox_notes
     */
    public String getMailbox_notes() {
        return mailbox_notes;
    }

    /**
     * @param mailbox_notes the mailbox_notes to set
     */
    public void setMailbox_notes(String mailbox_notes) {
        this.mailbox_notes = mailbox_notes;
    }

    /**
     * @return the brickwork_score
     */
    public int getBrickwork_score() {
        return brickwork_score;
    }

    /**
     * @param brickwork_score the brickwork_score to set
     */
    public void setBrickwork_score(int brickwork_score) {
        this.brickwork_score = brickwork_score;
    }

    /**
     * @return the brickwork_notes
     */
    public String getBrickwork_notes() {
        return brickwork_notes;
    }

    /**
     * @param brickwork_notes the brickwork_notes to set
     */
    public void setBrickwork_notes(String brickwork_notes) {
        this.brickwork_notes = brickwork_notes;
    }

    /**
     * @return the termites_score
     */
    public int getTermites_score() {
        return termites_score;
    }

    /**
     * @param termites_score the termites_score to set
     */
    public void setTermites_score(int termites_score) {
        this.termites_score = termites_score;
    }

    /**
     * @return the termites_notes
     */
    public String getTermites_notes() {
        return termites_notes;
    }

    /**
     * @param termites_notes the termites_notes to set
     */
    public void setTermites_notes(String termites_notes) {
        this.termites_notes = termites_notes;
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
     * @return the doorhardware_score
     */
    public int getDoorhardware_score() {
        return doorhardware_score;
    }

    /**
     * @param doorhardware_score the doorhardware_score to set
     */
    public void setDoorhardware_score(int doorhardware_score) {
        this.doorhardware_score = doorhardware_score;
    }

    /**
     * @return the doorhardware_notes
     */
    public String getDoorhardware_notes() {
        return doorhardware_notes;
    }

    /**
     * @param doorhardware_notes the doorhardware_notes to set
     */
    public void setDoorhardware_notes(String doorhardware_notes) {
        this.doorhardware_notes = doorhardware_notes;
    }

    /**
     * @return the satellites_score
     */
    public int getSatellites_score() {
        return satellites_score;
    }

    /**
     * @param satellites_score the satellites_score to set
     */
    public void setSatellites_score(int satellites_score) {
        this.satellites_score = satellites_score;
    }

    /**
     * @return the satellites_notes
     */
    public String getSatellites_notes() {
        return satellites_notes;
    }

    /**
     * @param satellites_notes the satellites_notes to set
     */
    public void setSatellites_notes(String satellites_notes) {
        this.satellites_notes = satellites_notes;
    }

    /**
     * @return the exposedwiring_score
     */
    public int getExposedwiring_score() {
        return exposedwiring_score;
    }

    /**
     * @param exposedwiring_score the exposedwiring_score to set
     */
    public void setExposedwiring_score(int exposedwiring_score) {
        this.exposedwiring_score = exposedwiring_score;
    }

    /**
     * @return the exposedwiring_notes
     */
    public String getExposedwiring_notes() {
        return exposedwiring_notes;
    }

    /**
     * @param exposedwiring_notes the exposedwiring_notes to set
     */
    public void setExposedwiring_notes(String exposedwiring_notes) {
        this.exposedwiring_notes = exposedwiring_notes;
    }

    /**
     * @return the decks_score
     */
    public int getDecks_score() {
        return decks_score;
    }

    /**
     * @param decks_score the decks_score to set
     */
    public void setDecks_score(int decks_score) {
        this.decks_score = decks_score;
    }

    /**
     * @return the decks_notes
     */
    public String getDecks_notes() {
        return decks_notes;
    }

    /**
     * @param decks_notes the decks_notes to set
     */
    public void setDecks_notes(String decks_notes) {
        this.decks_notes = decks_notes;
    }

    /**
     * @return the deckflashing_score
     */
    public int getDeckflashing_score() {
        return deckflashing_score;
    }

    /**
     * @param deckflashing_score the deckflashing_score to set
     */
    public void setDeckflashing_score(int deckflashing_score) {
        this.deckflashing_score = deckflashing_score;
    }

    /**
     * @return the deckflashing_notes
     */
    public String getDeckflashing_notes() {
        return deckflashing_notes;
    }

    /**
     * @param deckflashing_notes the deckflashing_notes to set
     */
    public void setDeckflashing_notes(String deckflashing_notes) {
        this.deckflashing_notes = deckflashing_notes;
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
     * @return the exteriorunitnumbers_score
     */
    public int getExteriorunitnumbers_score() {
        return exteriorunitnumbers_score;
    }

    /**
     * @param exteriorunitnumbers_score the exteriorunitnumbers_score to set
     */
    public void setExteriorunitnumbers_score(int exteriorunitnumbers_score) {
        this.exteriorunitnumbers_score = exteriorunitnumbers_score;
    }

    /**
     * @return the exteriorunitnumbers_notes
     */
    public String getExteriorunitnumbers_notes() {
        return exteriorunitnumbers_notes;
    }

    /**
     * @param exteriorunitnumbers_notes the exteriorunitnumbers_notes to set
     */
    public void setExteriorunitnumbers_notes(String exteriorunitnumbers_notes) {
        this.exteriorunitnumbers_notes = exteriorunitnumbers_notes;
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
     * @return the windowwells_score
     */
    public int getWindowwells_score() {
        return windowwells_score;
    }

    /**
     * @param windowwells_score the windowwells_score to set
     */
    public void setWindowwells_score(int windowwells_score) {
        this.windowwells_score = windowwells_score;
    }

    /**
     * @return the windowwells_notes
     */
    public String getWindowwells_notes() {
        return windowwells_notes;
    }

    /**
     * @param windowwells_notes the windowwells_notes to set
     */
    public void setWindowwells_notes(String windowwells_notes) {
        this.windowwells_notes = windowwells_notes;
    }

    /**
     * @return the exterioraccess_score
     */
    public int getExterioraccess_score() {
        return exterioraccess_score;
    }

    /**
     * @param exterioraccess_score the exterioraccess_score to set
     */
    public void setExterioraccess_score(int exterioraccess_score) {
        this.exterioraccess_score = exterioraccess_score;
    }

    /**
     * @return the exterioraccess_notes
     */
    public String getExterioraccess_notes() {
        return exterioraccess_notes;
    }

    /**
     * @param exterioraccess_notes the exterioraccess_notes to set
     */
    public void setExterioraccess_notes(String exterioraccess_notes) {
        this.exterioraccess_notes = exterioraccess_notes;
    }

    /**
     * @return the foundationleaks_notes
     */
    public String getFoundationleaks_notes() {
        return foundationleaks_notes;
    }

    /**
     * @param foundationleaks_notes the foundationleaks_notes to set
     */
    public void setFoundationleaks_notes(String foundationleaks_notes) {
        this.foundationleaks_notes = foundationleaks_notes;
    }

    /**
     * @return the foundationleaks_score
     */
    public int getFoundationleaks_score() {
        return foundationleaks_score;
    }

    /**
     * @param foundationleaks_score the foundationleaks_score to set
     */
    public void setFoundationleaks_score(int foundationleaks_score) {
        this.foundationleaks_score = foundationleaks_score;
    }

    /**
     * @return the porchposts_material
     */
    public String getPorchposts_material() {
        return porchposts_material;
    }

    /**
     * @param porchposts_material the porchposts_material to set
     */
    public void setPorchposts_material(String porchposts_material) {
        this.porchposts_material = porchposts_material;
    }

    /**
     * @return the spindles_score
     */
    public int getSpindles_score() {
        return spindles_score;
    }

    /**
     * @param spindles_score the spindles_score to set
     */
    public void setSpindles_score(int spindles_score) {
        this.spindles_score = spindles_score;
    }

    /**
     * @return the spindles_notes
     */
    public String getSpindles_notes() {
        return spindles_notes;
    }

    /**
     * @param spindles_notes the spindles_notes to set
     */
    public void setSpindles_notes(String spindles_notes) {
        this.spindles_notes = spindles_notes;
    }

    /**
     * @return the stairs_material
     */
    public String getStairs_material() {
        return stairs_material;
    }

    /**
     * @param stairs_material the stairs_material to set
     */
    public void setStairs_material(String stairs_material) {
        this.stairs_material = stairs_material;
    }

    /**
     * @return the handrails_material
     */
    public String getHandrails_material() {
        return handrails_material;
    }

    /**
     * @param handrails_material the handrails_material to set
     */
    public void setHandrails_material(String handrails_material) {
        this.handrails_material = handrails_material;
    }

    /**
     * @return the windows_number
     */
    public String getWindows_number() {
        return windows_number;
    }

    /**
     * @param windows_number the windows_number to set
     */
    public void setWindows_number(String windows_number) {
        this.windows_number = windows_number;
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
    
    
    
}
