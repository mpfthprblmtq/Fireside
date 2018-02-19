/**
 * File:    Building.java    
 * Author:  Pat Ripley
 * 
 * Program Description:    
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 * 
 * Class Description:
 * Building object class.
 * 
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// imports
import java.util.ArrayList;

// class Building
public class Building {
    
    // picture subclass, with standard getters and setters
    // used for exporting pictures with the URL and details
    public class Picture {
        // ivars
        private String url;
        private String section;
        private String item;
        // constructor
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
    } // end picture subclass
    
    // ivars
    private String id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String notes;
    private ArrayList<Picture> pictures;
    
    // aspect ivars
    InteriorHallway interiorhallway;
    Dumpsters dumpsters;
    MainSupplies mainsupplies;
    Landscaping landscaping;
    BuildingExterior buildingexterior;
    AsphaltConcrete asphaltconcrete;
    Fencing fencing;
    Signage signage;
    Overall overall;
    
    /**
     * Loaded constructor, creates all the aspects of the building
     * @param id
     * @param name
     * @param street
     * @param city
     * @param state
     * @param zipcode
     * @param notes
     */
    public Building(String id, String name, String street, String city, String state, String zipcode, String notes) {
        this.interiorhallway = new InteriorHallway();
        this.dumpsters = new Dumpsters();
        this.mainsupplies = new MainSupplies();
        this.landscaping = new Landscaping();
        this.buildingexterior = new BuildingExterior();
        this.asphaltconcrete = new AsphaltConcrete();
        this.fencing = new Fencing();
        this.signage = new Signage();
        this.overall = new Overall();
        
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.notes = notes;
        this.pictures = new ArrayList<>();
    }
    
    /**
     * Creates a picture object and adds it to the building
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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
     * @return the String representation of the object
     */
    @Override
    public String toString() {
        return "Building{" + "id=" + id + ", name=" + name + ", street=" + street + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode + ", notes=" + notes + ", interiorhallway=" + interiorhallway + ", dumpsters=" + dumpsters + ", mainsupplies=" + mainsupplies + ", landscaping=" + landscaping + ", buildingexterior=" + buildingexterior + ", asphaltconcrete=" + asphaltconcrete + ", fencing=" + fencing + ", signage=" + signage + '}';
    }
} // end Building.java
