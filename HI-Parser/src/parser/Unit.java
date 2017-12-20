/**
 * File:    Unit.java    
 * Author:  Pat Ripley
 * 
 * Program Description:    
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 * 
 * Class Description:
 * Unit object class.
 * 
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// imports
import java.util.ArrayList;

// class Unit
public class Unit {
    
    public class Picture {
        private String url;
        private String section;
        private String item;
        public Picture(String section, String item, String url) {
            this.url = url;
            this.section = section;
            this.item = item;
        }
        public void setUrl(String url) {
            this.url = url;
        }
        public void setSection(String section) {
            this.section = section;
        }
        public void setItem(String item) {
            this.item = item;
        }
        public String getUrl() {
            return url;
        }
        public String getSection() {
            return section;
        }
        public String getItem() {
            return item;
        }
        @Override
        public String toString() {
            return section + " - " + item + ": " + url;
        }
    }
    
    // ivars
    private String id;
    private String street;
    private String unitNum;
    private String city;
    private String state;
    private String zip;
    private String floorPlan;
    private String building;
    private String notes;
    private ArrayList<Picture> pictures;
    private int rows;
    
    // room variables
    Bedroom bedroom1;
    Bedroom bedroom2;
    Bedroom bedroom3;
    Bathroom bathroom1;
    Bathroom bathroom2;
    Bathroom bathroom3;
    Kitchen kitchen;
    Mechanical mechanical;
    Utility utility;
    LivingRoom livingroom;
    DiningRoom diningroom;
    Entry entry;
    Overall overall;

    /**
     * Loaded constructor
     * @param id
     * @param street
     * @param unitNum
     * @param city
     * @param state
     * @param zip
     * @param floorPlan
     * @param building
     * @param notes 
     */
    public Unit(String id, String street, String unitNum, String city, String state, String zip, String floorPlan, String building, String notes) {
        this.id = id;
        this.street = street;
        this.unitNum = unitNum;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.floorPlan = floorPlan;
        this.building = building;
        this.notes = notes;
        this.pictures = new ArrayList<>();
        createRooms();
    }
    
    /**
     * Function to create the rooms based on a floorplan
     */
    private void createRooms() {
        
        // create common rooms
        kitchen = new Kitchen();
        mechanical = new Mechanical();
        utility = new Utility();
        livingroom = new LivingRoom();
        diningroom = new DiningRoom();
        entry = new Entry();
        overall = new Overall();
        
        bedroom1 = new Bedroom();
        bedroom2 = new Bedroom();
        bedroom3 = new Bedroom();
        
        bathroom1 = new Bathroom();
        bathroom2 = new Bathroom();
        bathroom3 = new Bathroom();
    }
    
    /**
     * Creates a picture object and adds it to the unit
     * @param url
     * @param section
     * @param item
     */
    public void addPicture(String section, String item, String url) {
        this.pictures.add(new Picture(section, item, url));
    }

    /**
     * @return the id
     */
    public String getID() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setID(String id) {
        this.id = id;
    }
    
    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the unitNum
     */
    public String getUnitNum() {
        return unitNum;
    }

    /**
     * @param unitNum the unitNum to set
     */
    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the floorPlan
     */
    public String getFloorPlan() {
        return floorPlan;
    }

    /**
     * @param floorPlan the floorPlan to set
     */
    public void setFloorPlan(String floorPlan) {
        this.floorPlan = floorPlan;
    }

    /**
     * @return the building
     */
    public String getBuilding() {
        return building;
    }

    /**
     * @param building the building to set
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(int rows) {
        this.rows = rows;
    }
    
    /**
     * @return the pictures
     */
    public ArrayList getPictures() {
        return pictures;
    }

    /**
     * @param pictures the pictures to set
     */
    public void setPictures(ArrayList pictures) {
        this.pictures = pictures;
    }

    /**
     * toString() used for debugging purposes
     * @return the object in string form
     */
    @Override
    public String toString() {
        return "Unit{" + "id=" + id + ", street=" + street + ", unitNum=" + unitNum + ", city=" + city + ", state=" + state + ", zip=" + zip + ", floorPlan=" + floorPlan + ", building=" + building + ", notes=" + notes + '}';
    }
}
