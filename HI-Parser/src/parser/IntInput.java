/**
 * File:    IntInput.java
 * Author:  Pat Ripley
 *
 * Program Description:
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 *
 * Class Description:
 * Takes input from external data sheet and formats it into objects
 *
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// imports
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// class IntInput
public class IntInput {

    private int count;
    
    private final int SECTION = 20;
    private final int ITEM = 21;
    private final int CAT = 22;
    private final int CEILING_TYPE = 23;
    private final int DOG = 24;
    private final int DOOR_MATERIAL = 25;
    private final int DOOR_TYPE = 26;
    private final int DOOR_VARIETY = 27;
    private final int FINISH = 28;
    private final int FLOORING = 29;
    private final int GAS_ELECTRIC = 30;
    private final int GRADE = 31;
    private final int HOARDER = 32;
    private final int ICE_MAKER = 33;
    private final int LIGHTING_TYPE = 34;
    private final int MATERIAL = 35;
    private final int MODEL = 36;
    private final int NONE = 37;
    private final int NUMBER = 38;
    private final int OTHER = 39;
    private final int PLASTIC_TILE = 40;
    private final int R = 41;
    private final int SINGLE_DOUBLE = 42;
    private final int SMOKE_ALARMS = 43;
    private final int SMOKER = 44;
    private final int TYPE = 45;
    private final int VACANT_OCCUPIED = 46;
    private final int W_D_HOOKUP = 47;
    private final int WALLS_TYPE = 48;
    private final int WATER_DAMAGE = 49;
    private final int NOTES = 50;
    private final int PHOTOS_START = 51;

    // ivars
    private Unit unit;

    // hashmap that contains all the units
    private HashMap<String, Unit> units = new HashMap<>();

    // declarations for the excel file reading
    static InputStream fs;
    static XSSFWorkbook wb;
    static XSSFSheet sheet;
    static XSSFRow row;

    // variables for the filechooser
    JFileChooser fc = new JFileChooser();
    File file;

    /**
     * Default constructor
     */
    public IntInput() {

    }

    /**
     * @return the units
     */
    public HashMap<String, Unit> getUnits() {
        return units;
    }

    /**
     * @param aUnits the units to set
     */
    public void setUnits(HashMap<String, Unit> aUnits) {
        units = aUnits;
    }

    /**
     * Function to open workbook/spreadsheet
     * @return 
     */
    public String openWorkbook() {

        // set working directory for filechooser
        File workingDirectory = new File(System.getProperty("user.dir"));
        fc.setCurrentDirectory(workingDirectory);

        // set default file types to xlsx
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx");
        fc.setFileFilter(filter);

        // set title of filechooser
        fc.setDialogTitle("Choose a Excel document to import...");

        // show filechooser
        //System.out.println("Choosing Excel file to parse...");
        int returnVal = fc.showOpenDialog(null);

        // what to do after user chooses an option
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            
            file = fc.getSelectedFile();
            MainFrame.setPath(file.getAbsolutePath());
            //System.out.println("Opening " + file.getName() + "...");
        } else {
            //System.err.println("No file chosen.");

        }

        // read in the workbook, get inspection matrix sheet
        try {
            fs = new FileInputStream(file);
            wb = new XSSFWorkbook(fs);
            sheet = wb.getSheetAt(0);

        } catch (IOException e) {
            // something went wrong
            System.err.println(e);
        } catch (NullPointerException e) {
            return null;
        }
        return file.getAbsolutePath();
    } // end openWorkbook() 

    /**
     * Function to close the workbook/spreadsheet
     */
    public void closeWorkbook() {
        try {
            // close the connection to the workbook
            wb.close();
        } catch (IOException ex) {
            // no escape
            System.err.println(ex);
        }
    } // end closeWorkbook()

    /**
     * Initializer function, creates the units and determine the amount of rows
     * each unit has
     */
    public void initInterior() {

        // temp variable
        String temp = "";
        
        // iterate through the sheet
        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {

            // get the row and assetID
            row = sheet.getRow(i);
            
            if (row.getCell(0).toString().equals(temp)) {
                // do nothing, continue
            } else {
                // get values
                try {
                    String id = row.getCell(0).toString();
                    String street = row.getCell(13).toString();
                    String unitNum = row.getCell(14).toString();
                    String city = row.getCell(15).toString();
                    String state = row.getCell(16).toString();
                    String zip = row.getCell(17).toString();
                    String floorPlan = row.getCell(9).toString();
                    String building = row.getCell(10).toString();
                    String notes = row.getCell(11).toString();
                    getUnits().put(id, new Unit(id, street, unitNum, city, state, zip, floorPlan, building, notes));
                    
                    temp = id;
                } catch (NullPointerException e) {
                    // nothing
                }
                
            }
        }
    } // end initInterior()
    
    /**
     * Function that traverses the whole sheet and fills values based on values
     */
    public void fillUnits() {

        String section;
        String item;
        
        for (int i = 1; i < sheet.getLastRowNum()+1; i++) {
            
            // get the row and assetID
            row = sheet.getRow(i);
            
            // unit for reference
            unit = units.get(row.getCell(0).toString());
            
            // read in the section and item and do stuff based on that
            section = row.getCell(20).toString();
            item = row.getCell(21).toString();
            
            // switcheroo to find out what import
            switch(section) {
                case "Entry/Hall":
                    switch(item) {
                        case "Doors":
                            unit.entry.setDoor_score(Integer.valueOf(getData(R)));
                            unit.entry.setDoor_material(getData(DOOR_MATERIAL));
                            unit.entry.setDoor_type(getData(DOOR_TYPE));
                            unit.entry.setDoor_variety(getData(DOOR_VARIETY));
                            break;
                        case "Floor":
                            unit.entry.setFloor_score(Integer.valueOf(getData(R)));
                            unit.entry.setFloor_type(getData(FLOORING));
                            unit.entry.setFloor_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Stairs":
                            unit.entry.setStairs_score(Integer.valueOf(getData(R)));
                            unit.entry.setStairs_flooring(getData(FLOORING));
                            break;
                        case "Walls/Ceiling":
                            unit.entry.setWallsceiling_score(Integer.valueOf(getData(R)));
                            unit.entry.setWalls_type(getData(WALLS_TYPE));
                            unit.entry.setCeilings_type(getData(CEILING_TYPE));
                            unit.entry.setWallsceiling_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Closet":
                            unit.entry.setCloset_score(Integer.valueOf(getData(R)));
                            unit.entry.setCloset_door_material(getData(DOOR_MATERIAL));
                            unit.entry.setCloset_door_type(getData(DOOR_TYPE));
                            unit.entry.setCloset_door_variety(getData(DOOR_VARIETY));
                            break;
                        case "Windows":
                            unit.entry.setWindows_score(Integer.valueOf(getData(R)));
                            unit.entry.setWindows_type(getData(TYPE));
                            try {
                                unit.entry.setWindows_number(Integer.valueOf(getData(NUMBER)));
                            } catch (NumberFormatException e) {
                                 unit.entry.setWindows_number(0);
                            }
                            unit.entry.setWindows_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Blinds/Drapes":
                            unit.entry.setBlindsdrapes_score(Integer.valueOf(getData(R)));
                            unit.entry.setBlindsdrapes_type(getData(TYPE));
                            break;
                        case "Light Fixture":
                            unit.entry.setLight_score(Integer.valueOf(getData(R)));
                            unit.entry.setLight_type(getData(LIGHTING_TYPE));
                            unit.entry.setLight_finish(getData(FINISH));
                            break;
                    }
                    break;
                case "Living Room":
                    switch(item) {
                        case "Floor":
                            unit.livingroom.setFloor_score(Integer.valueOf(getData(R)));
                            unit.livingroom.setFloor_type(getData(FLOORING));
                            unit.livingroom.setFloor_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Walls/Ceiling":
                            unit.livingroom.setWallsceiling_score(Integer.valueOf(getData(R)));
                            unit.livingroom.setWalls_type(getData(WALLS_TYPE));
                            unit.livingroom.setCeiling_type(getData(CEILING_TYPE));
                            unit.livingroom.setWallsceiling_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Doors":
                            unit.livingroom.setDoor_score(Integer.valueOf(getData(R)));
                            unit.livingroom.setDoor_material(getData(DOOR_MATERIAL));
                            unit.livingroom.setDoor_type(getData(DOOR_TYPE));
                            unit.livingroom.setDoor_variety(getData(DOOR_VARIETY));
                            break;
                        case "Windows":
                            unit.livingroom.setWindows_score(Integer.valueOf(getData(R)));
                            unit.livingroom.setWindows_type(getData(TYPE));
                            try {
                                unit.livingroom.setWindows_number(Integer.valueOf(getData(NUMBER)));
                            } catch (NumberFormatException e) {
                                 unit.livingroom.setWindows_number(0);
                            }
                            unit.livingroom.setWindows_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Blinds/Drapes":
                            unit.livingroom.setBlindsdrapes_score(Integer.valueOf(getData(R)));
                            unit.livingroom.setBlindsdrapes_type(getData(TYPE));
                            break;
                        case "Light Fixture":
                            unit.livingroom.setLight_score(Integer.valueOf(getData(R)));
                            unit.livingroom.setLight_type(getData(LIGHTING_TYPE));
                            unit.livingroom.setLight_finish(getData(FINISH));
                            break;
                        case "Ceiling Fan":
                            unit.livingroom.setCeilingfan_score(Integer.valueOf(getData(R)));
                            break;
                        case "Patio/Bal":
                            unit.livingroom.setPatio_score(Integer.valueOf(getData(R)));
                            unit.livingroom.setPatio_door_material(getData(DOOR_MATERIAL));
                            unit.livingroom.setPatio_door_type(getData(DOOR_TYPE));
                            unit.livingroom.setPatio_door_variety(getData(DOOR_VARIETY));
                            break;
                        case "Fireplace":
                            unit.livingroom.setFireplace_score(Integer.valueOf(getData(R)));
                            unit.livingroom.setFireplace_type(getData(TYPE));
                            break;
                        
                    }
                    break;
                case "Dining Room":
                    switch(item) {
                        case "Floor":
                            unit.diningroom.setFloor_score(Integer.valueOf(getData(R)));
                            unit.diningroom.setFloor_type(getData(FLOORING));
                            unit.diningroom.setFloor_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Walls/Ceiling":
                            unit.diningroom.setWallsceiling_score(Integer.valueOf(getData(R)));
                            unit.diningroom.setWalls_type(getData(WALLS_TYPE));
                            unit.diningroom.setCeiling_type(getData(CEILING_TYPE));
                            unit.diningroom.setWallsceiling_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Doors":
                            unit.diningroom.setDoor_score(Integer.valueOf(getData(R)));
                            unit.diningroom.setDoor_material(getData(DOOR_MATERIAL));
                            unit.diningroom.setDoor_type(getData(DOOR_TYPE));
                            unit.diningroom.setDoor_variety(getData(DOOR_VARIETY));
                            break;
                        case "Closet":
                            unit.diningroom.setCloset_score(Integer.valueOf(getData(R)));
                            unit.diningroom.setCloset_door_material(getData(DOOR_MATERIAL));
                            unit.diningroom.setCloset_door_type(getData(DOOR_TYPE));
                            unit.diningroom.setCloset_door_variety(getData(DOOR_VARIETY));
                            break;
                        case "Windows":
                            unit.diningroom.setWindows_score(Integer.valueOf(getData(R)));
                            unit.diningroom.setWindows_type(getData(TYPE));
                            try {
                                unit.diningroom.setWindows_number(Integer.valueOf(getData(NUMBER)));
                            } catch (NumberFormatException e) {
                                 unit.diningroom.setWindows_number(0);
                            }
                            unit.diningroom.setWindows_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Blinds/Drapes":
                            unit.diningroom.setBlindsdrapes_score(Integer.valueOf(getData(R)));
                            unit.diningroom.setBlindsdrapes_type(getData(TYPE));
                            break;
                        case "Light Fixture":
                            unit.diningroom.setLight_score(Integer.valueOf(getData(R)));
                            unit.diningroom.setLight_type(getData(LIGHTING_TYPE));
                            unit.diningroom.setLight_finish(getData(FINISH));
                            break;
                        case "Ceiling Fan":
                            unit.diningroom.setCeilingfan_score(Integer.valueOf(getData(R)));
                            break;
                    }
                    break;
                case "Kitchen":
                    switch(item) {
                        case "Floor":
                            unit.kitchen.setFloor_score(Integer.valueOf(getData(R)));
                            unit.kitchen.setFloor_type(getData(FLOORING));
                            unit.kitchen.setFloor_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Walls/Ceiling":
                            unit.kitchen.setWallsceiling_score(Integer.valueOf(getData(R)));
                            unit.kitchen.setWalls_type(getData(WALLS_TYPE));
                            unit.kitchen.setCeiling_type(getData(CEILING_TYPE));
                            unit.kitchen.setWallsceiling_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Windows":
                            unit.kitchen.setWindows_score(Integer.valueOf(getData(R)));
                            unit.kitchen.setWindows_type(getData(TYPE));
                            try {
                                unit.kitchen.setWindows_number(Integer.valueOf(getData(NUMBER)));
                            } catch (NumberFormatException e) {
                                 unit.kitchen.setWindows_number(0);
                            }
                            unit.kitchen.setWindows_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Blinds/Drapes":
                            unit.kitchen.setBlindsdrapes_score(Integer.valueOf(getData(R)));
                            unit.kitchen.setBlindsdrapes_type(getData(TYPE));
                            break;
                        case "Doors":
                            unit.kitchen.setDoor_score(Integer.valueOf(getData(R)));
                            unit.kitchen.setDoor_material(getData(DOOR_MATERIAL));
                            unit.kitchen.setDoor_type(getData(DOOR_TYPE));
                            unit.kitchen.setDoor_variety(getData(DOOR_VARIETY));
                            break;
                        case "Pantry":
                            unit.kitchen.setPantry_score(Integer.valueOf(getData(R)));
                            unit.kitchen.setPantry_door_material(getData(DOOR_MATERIAL));
                            unit.kitchen.setPantry_door_type(getData(DOOR_TYPE));
                            unit.kitchen.setPantry_door_variety(getData(DOOR_VARIETY));
                            break;
                        case "Light Fixture":
                            unit.kitchen.setLight_score(Integer.valueOf(getData(R)));
                            unit.kitchen.setLight_type(getData(LIGHTING_TYPE));
                            unit.kitchen.setLight_finish(getData(FINISH));
                            break;
                        case "Sink":
                            unit.kitchen.setSink_score(Integer.valueOf(getData(R)));
                            unit.kitchen.setSink_material(getData(MATERIAL));
                            unit.kitchen.setSink_type(getData(SINGLE_DOUBLE));
                            break;
                        case "Cabinets":
                            unit.kitchen.setCabinets_score(Integer.valueOf(getData(R)));
                            unit.kitchen.setCabinets_type(getData(MATERIAL));
                            break;
                        case "Countertop":
                            unit.kitchen.setCountertops_score(Integer.valueOf(getData(R)));
                            unit.kitchen.setCountertops_type(getData(MATERIAL));
                            break;
                        case "Pass/Bar":
                            unit.kitchen.setPassbar_score(Integer.valueOf(getData(R)));
                            unit.kitchen.setPassbar_type(getData(MATERIAL));
                            break;
                        
                    }
                    break;
                case "Mechanical":
                    switch(item) {
                        case "Range":
                            unit.mechanical.setRange_score(Integer.valueOf(getData(R)));
                            unit.mechanical.setRange_model(getData(MODEL));
                            unit.mechanical.setRange_type(getData(GAS_ELECTRIC));
                            unit.mechanical.setRange_finish(getData(FINISH));
                            break;
                        case "Refridgerator":
                            unit.mechanical.setFridge_score(Integer.valueOf(getData(R)));
                            unit.mechanical.setFridge_model(getData(MODEL));
                            unit.mechanical.setFridge_finish(getData(FINISH));
                            unit.mechanical.setFridge_icemaker(getData(ICE_MAKER));
                            break;
                        case "Dishwasher":
                            unit.mechanical.setDishwasher_score(Integer.valueOf(getData(R)));
                            unit.mechanical.setDishwasher_model(getData(MODEL));
                            unit.mechanical.setDishwasher_finish(getData(FINISH));
                            break;
                        case "Microwave":
                            unit.mechanical.setMicrowave_score(Integer.valueOf(getData(R)));
                            unit.mechanical.setMicrowave_model(getData(MODEL));
                            unit.mechanical.setMicrowave_finish(getData(FINISH));
                            break;
                        case "Hood/Vent":
                            unit.mechanical.setHood_score(Integer.valueOf(getData(R)));
                            unit.mechanical.setHood_model(getData(MODEL));
                            unit.mechanical.setHood_finish(getData(FINISH));
                            break;
                        case "HVAC":
                            unit.mechanical.setHvac_score(Integer.valueOf(getData(R)));
                            unit.mechanical.setHvac_model(getData(MODEL));
                            break;
                        case "Water Heater":
                            unit.mechanical.setWater_heater_score(Integer.valueOf(getData(R)));
                            unit.mechanical.setWater_heater_model(getData(MODEL));
                            break;
                        case "W/D":
                            unit.mechanical.setWasher_dryer_score(Integer.valueOf(getData(R)));
                            unit.mechanical.setWasher_dryer_model(getData(MODEL));
                            unit.mechanical.setHasHookup((getData(W_D_HOOKUP).equals("W/D Hookup")));
                            unit.mechanical.setWasher_dryer_finish(getData(FINISH));
                            break;
                        case "Disposal":
                            unit.mechanical.setDisposal_score(Integer.valueOf(getData(R)));
                            break;
                        
                    }
                    break;
                case "Utility/Storage":
                    switch(item) {
                        case "Door":
                            unit.utility.setDoor_score(Integer.valueOf(getData(R)));
                            unit.utility.setDoor_material(getData(DOOR_MATERIAL));
                            unit.utility.setDoor_type(getData(DOOR_TYPE));
                            unit.utility.setDoor_variety(getData(DOOR_VARIETY));
                            break;
                        case "Floor":
                            unit.utility.setFloor_score(Integer.valueOf(getData(R)));
                            unit.utility.setFloor_type(getData(FLOORING));
                            unit.utility.setFloor_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Walls/Ceiling":
                            unit.utility.setWallsceiling_score(Integer.valueOf(getData(R)));
                            unit.utility.setWalls_type(getData(WALLS_TYPE));
                            unit.utility.setCeiling_type(getData(CEILING_TYPE));
                            unit.utility.setWallsceiling_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Sump":
                            unit.utility.setSump_score(Integer.valueOf(getData(R)));
                            break;
                        case "Light Fixture":
                            unit.utility.setLight_score(Integer.valueOf(getData(R)));
                            unit.utility.setLight_type(getData(LIGHTING_TYPE));
                            unit.utility.setLight_finish(getData(FINISH));
                            break;
                        
                    }
                    break;
                case "Bedroom #1":
                    switch(item) {
                        case "Floor":
                            unit.bedroom1.setFloor_score(Integer.valueOf(getData(R)));
                            unit.bedroom1.setFloor_type(getData(FLOORING));
                            unit.bedroom1.setFloor_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Walls/Ceiling":
                            unit.bedroom1.setWallsceiling_score(Integer.valueOf(getData(R)));
                            unit.bedroom1.setWalls_type(getData(WALLS_TYPE));
                            unit.bedroom1.setCeiling_type(getData(CEILING_TYPE));
                            unit.bedroom1.setWallsceiling_type(getData(WATER_DAMAGE));
                            break;
                        case "Doors":
                            unit.bedroom1.setDoor_score(Integer.valueOf(getData(R)));
                            unit.bedroom1.setDoor_material(getData(DOOR_MATERIAL));
                            unit.bedroom1.setDoor_type(getData(DOOR_TYPE));
                            unit.bedroom1.setDoor_variety(getData(DOOR_VARIETY));
                            break;
                        case "Closet":
                            unit.bedroom1.setCloset_score(Integer.valueOf(getData(R)));
                            unit.bedroom1.setCloset_door_material(getData(DOOR_MATERIAL));
                            unit.bedroom1.setCloset_door_type(getData(DOOR_TYPE));
                            unit.bedroom1.setCloset_door_variety(getData(DOOR_VARIETY));
                            break;
                        case "Windows":
                            unit.bedroom1.setWindows_score(Integer.valueOf(getData(R)));
                            unit.bedroom1.setWindows_type(getData(TYPE));
                            try {
                                unit.bedroom1.setWindows_number(Integer.valueOf(getData(NUMBER)));
                            } catch (NumberFormatException e) {
                                 unit.bedroom1.setWindows_number(0);
                            }
                            unit.bedroom1.setWindows_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Blinds/Drapes":
                            unit.bedroom1.setBlindsdrapes_score(Integer.valueOf(getData(R)));
                            unit.bedroom1.setBlindsdrapes_type(getData(TYPE));
                            break;
                        case "Light Fixture":
                            unit.bedroom1.setLight_score(Integer.valueOf(getData(R)));
                            unit.bedroom1.setLight_type(getData(LIGHTING_TYPE));
                            unit.bedroom1.setLight_finish(getData(FINISH));
                            break;
                        case "Ceiling Fan":
                            unit.bedroom1.setCeilingfan_score(Integer.valueOf(getData(R)));
                            break;
                        
                    }
                    break;
                case "Bedroom #2":
                    switch(item) {
                        case "Floor":
                            unit.bedroom2.setFloor_score(Integer.valueOf(getData(R)));
                            unit.bedroom2.setFloor_type(getData(FLOORING));
                            unit.bedroom2.setFloor_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Walls/Ceiling":
                            unit.bedroom2.setWallsceiling_score(Integer.valueOf(getData(R)));
                            unit.bedroom2.setWalls_type(getData(WALLS_TYPE));
                            unit.bedroom2.setCeiling_type(getData(CEILING_TYPE));
                            unit.bedroom2.setWallsceiling_type(getData(WATER_DAMAGE));
                            break;
                        case "Doors":
                            unit.bedroom2.setDoor_score(Integer.valueOf(getData(R)));
                            unit.bedroom2.setDoor_material(getData(DOOR_MATERIAL));
                            unit.bedroom2.setDoor_type(getData(DOOR_TYPE));
                            unit.bedroom2.setDoor_variety(getData(DOOR_VARIETY));
                            break;
                        case "Closet":
                            unit.bedroom2.setCloset_score(Integer.valueOf(getData(R)));
                            unit.bedroom2.setCloset_door_material(getData(DOOR_MATERIAL));
                            unit.bedroom2.setCloset_door_type(getData(DOOR_TYPE));
                            unit.bedroom2.setCloset_door_variety(getData(DOOR_VARIETY));
                            break;
                        case "Windows":
                            unit.bedroom2.setWindows_score(Integer.valueOf(getData(R)));
                            unit.bedroom2.setWindows_type(getData(TYPE));
                            try {
                                unit.bedroom2.setWindows_number(Integer.valueOf(getData(NUMBER)));
                            } catch (NumberFormatException e) {
                                 unit.bedroom2.setWindows_number(0);
                            }
                            unit.bedroom2.setWindows_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Blinds/Drapes":
                            unit.bedroom2.setBlindsdrapes_score(Integer.valueOf(getData(R)));
                            unit.bedroom2.setBlindsdrapes_type(getData(TYPE));
                            break;
                        case "Light Fixture":
                            unit.bedroom2.setLight_score(Integer.valueOf(getData(R)));
                            unit.bedroom2.setLight_type(getData(LIGHTING_TYPE));
                            unit.bedroom2.setLight_finish(getData(FINISH));
                            break;
                        case "Ceiling Fan":
                            unit.bedroom2.setCeilingfan_score(Integer.valueOf(getData(R)));
                            break;
                        
                    }
                    break;
                case "Bedroom #3":
                    switch(item) {
                        case "Floor":
                            unit.bedroom3.setFloor_score(Integer.valueOf(getData(R)));
                            unit.bedroom3.setFloor_type(getData(FLOORING));
                            unit.bedroom3.setFloor_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Walls/Ceiling":
                            unit.bedroom3.setWallsceiling_score(Integer.valueOf(getData(R)));
                            unit.bedroom3.setWalls_type(getData(WALLS_TYPE));
                            unit.bedroom3.setCeiling_type(getData(CEILING_TYPE));
                            unit.bedroom3.setWallsceiling_type(getData(WATER_DAMAGE));
                            break;
                        case "Doors":
                            unit.bedroom3.setDoor_score(Integer.valueOf(getData(R)));
                            unit.bedroom3.setDoor_material(getData(DOOR_MATERIAL));
                            unit.bedroom3.setDoor_type(getData(DOOR_TYPE));
                            unit.bedroom3.setDoor_variety(getData(DOOR_VARIETY));
                            break;
                        case "Closet":
                            unit.bedroom3.setCloset_score(Integer.valueOf(getData(R)));
                            unit.bedroom3.setCloset_door_material(getData(DOOR_MATERIAL));
                            unit.bedroom3.setCloset_door_type(getData(DOOR_TYPE));
                            unit.bedroom3.setCloset_door_variety(getData(DOOR_VARIETY));
                            break;
                        case "Windows":
                            unit.bedroom3.setWindows_score(Integer.valueOf(getData(R)));
                            unit.bedroom3.setWindows_type(getData(TYPE));
                            try {
                                unit.bedroom3.setWindows_number(Integer.valueOf(getData(NUMBER)));
                            } catch (NumberFormatException e) {
                                 unit.bedroom3.setWindows_number(0);
                            }
                            unit.bedroom3.setWindows_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Blinds/Drapes":
                            unit.bedroom3.setBlindsdrapes_score(Integer.valueOf(getData(R)));
                            unit.bedroom3.setBlindsdrapes_type(getData(TYPE));
                            break;
                        case "Light Fixture":
                            unit.bedroom3.setLight_score(Integer.valueOf(getData(R)));
                            unit.bedroom3.setLight_type(getData(LIGHTING_TYPE));
                            unit.bedroom3.setLight_finish(getData(FINISH));
                            break;
                        case "Ceiling Fan":
                            unit.bedroom3.setCeilingfan_score(Integer.valueOf(getData(R)));
                            break;
                        
                    }
                    break;
                case "Bathroom #1":
                    switch(item) {
                        case "Floor":
                            unit.bathroom1.setFloor_score(Integer.valueOf(getData(R)));
                            unit.bathroom1.setFloor_type(getData(FLOORING));
                            unit.bathroom1.setFloor_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Walls/Ceiling":
                            unit.bathroom1.setWallsceiling_score(Integer.valueOf(getData(R)));
                            unit.bathroom1.setWalls_type(getData(WALLS_TYPE));
                            unit.bathroom1.setCeiling_type(getData(CEILING_TYPE));
                            unit.bathroom1.setWallsceiling_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Doors":
                            unit.bathroom1.setDoor_score(Integer.valueOf(getData(R)));
                            unit.bathroom1.setDoor_material(getData(DOOR_MATERIAL));
                            unit.bathroom1.setDoor_type(getData(DOOR_TYPE));
                            unit.bathroom1.setDoor_variety(getData(DOOR_VARIETY));
                            break;
                        case "Windows":
                            unit.bathroom1.setWindows_score(Integer.valueOf(getData(R)));
                            unit.bathroom1.setWindows_type(getData(TYPE));
                            try {
                                unit.bathroom1.setWindows_number(Integer.valueOf(getData(NUMBER)));
                            } catch (NumberFormatException e) {
                                 unit.bathroom1.setWindows_number(0);
                            }
                            unit.bathroom1.setWindows_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Blinds/Drapes":
                            unit.bathroom1.setBlindsdrapes_score(Integer.valueOf(getData(R)));
                            unit.bathroom1.setBlindsdrapes_type(getData(TYPE));
                            break;
                        case "Light Fixture":
                            unit.bathroom1.setLight_score(Integer.valueOf(getData(R)));
                            unit.bathroom1.setLight_type(getData(LIGHTING_TYPE));
                            unit.bathroom1.setLight_finish(getData(FINISH));
                            break;
                        case "Sink":
                            unit.bathroom1.setSink_score(Integer.valueOf(getData(R)));
                            unit.bathroom1.setSink_material(getData(MATERIAL));
                            unit.bathroom1.setSink_type(getData(SINGLE_DOUBLE));
                            break;
                        case "Tub/Shower":
                            unit.bathroom1.setTubshower_score(Integer.valueOf(getData(R)));
                            unit.bathroom1.setTubshower_material(getData(PLASTIC_TILE));
                            unit.bathroom1.setTubshower_type(getData(TYPE));
                            break;
                        case "Commode":
                            unit.bathroom1.setCommode_score(Integer.valueOf(getData(R)));
                            break;
                        case "Medicine Cabinet":
                            unit.bathroom1.setMedicinecabinet_score(Integer.valueOf(getData(R)));
                            break;
                        case "Mirror":
                            unit.bathroom1.setMirror_score(Integer.valueOf(getData(R)));
                            break;
                        
                    }
                    break;
                case "Bathroom #2":
                    switch(item) {
                        case "Floor":
                            unit.bathroom2.setFloor_score(Integer.valueOf(getData(R)));
                            unit.bathroom2.setFloor_type(getData(FLOORING));
                            unit.bathroom2.setFloor_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Walls/Ceiling":
                            unit.bathroom2.setWallsceiling_score(Integer.valueOf(getData(R)));
                            unit.bathroom2.setWalls_type(getData(WALLS_TYPE));
                            unit.bathroom2.setCeiling_type(getData(CEILING_TYPE));
                            unit.bathroom2.setWallsceiling_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Doors":
                            unit.bathroom2.setDoor_score(Integer.valueOf(getData(R)));
                            unit.bathroom2.setDoor_material(getData(DOOR_MATERIAL));
                            unit.bathroom2.setDoor_type(getData(DOOR_TYPE));
                            unit.bathroom2.setDoor_variety(getData(DOOR_VARIETY));
                            break;
                        case "Windows":
                            unit.bathroom2.setWindows_score(Integer.valueOf(getData(R)));
                            unit.bathroom2.setWindows_type(getData(TYPE));
                            try {
                                unit.bathroom2.setWindows_number(Integer.valueOf(getData(NUMBER)));
                            } catch (NumberFormatException e) {
                                 unit.bathroom2.setWindows_number(0);
                            }
                            unit.bathroom2.setWindows_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Blinds/Drapes":
                            unit.bathroom2.setBlindsdrapes_score(Integer.valueOf(getData(R)));
                            unit.bathroom2.setBlindsdrapes_type(getData(TYPE));
                            break;
                        case "Light Fixture":
                            unit.bathroom2.setLight_score(Integer.valueOf(getData(R)));
                            unit.bathroom2.setLight_type(getData(LIGHTING_TYPE));
                            unit.bathroom2.setLight_finish(getData(FINISH));
                            break;
                        case "Sink":
                            unit.bathroom2.setSink_score(Integer.valueOf(getData(R)));
                            unit.bathroom2.setSink_material(getData(MATERIAL));
                            unit.bathroom2.setSink_type(getData(SINGLE_DOUBLE));
                            break;
                        case "Tub/Shower":
                            unit.bathroom2.setTubshower_score(Integer.valueOf(getData(R)));
                            unit.bathroom2.setTubshower_material(getData(PLASTIC_TILE));
                            unit.bathroom2.setTubshower_type(getData(TYPE));
                            break;
                        case "Commode":
                            unit.bathroom2.setCommode_score(Integer.valueOf(getData(R)));
                            break;
                        case "Medicine Cabinet":
                            unit.bathroom2.setMedicinecabinet_score(Integer.valueOf(getData(R)));
                            break;
                        case "Mirror":
                            unit.bathroom2.setMirror_score(Integer.valueOf(getData(R)));
                            break;
                        
                    }
                    break;
                case "Bathroom #3":
                    switch(item) {
                        case "Floor":
                            unit.bathroom3.setFloor_score(Integer.valueOf(getData(R)));
                            unit.bathroom3.setFloor_type(getData(FLOORING));
                            unit.bathroom3.setFloor_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Walls/Ceiling":
                            unit.bathroom3.setWallsceiling_score(Integer.valueOf(getData(R)));
                            unit.bathroom3.setWalls_type(getData(WALLS_TYPE));
                            unit.bathroom3.setCeiling_type(getData(CEILING_TYPE));
                            unit.bathroom3.setWallsceiling_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Doors":
                            unit.bathroom3.setDoor_score(Integer.valueOf(getData(R)));
                            unit.bathroom3.setDoor_material(getData(DOOR_MATERIAL));
                            unit.bathroom3.setDoor_type(getData(DOOR_TYPE));
                            unit.bathroom3.setDoor_variety(getData(DOOR_VARIETY));
                            break;
                        case "Windows":
                            unit.bathroom3.setWindows_score(Integer.valueOf(getData(R)));
                            unit.bathroom3.setWindows_type(getData(TYPE));
                            try {
                                unit.bathroom3.setWindows_number(Integer.valueOf(getData(NUMBER)));
                            } catch (NumberFormatException e) {
                                 unit.bathroom3.setWindows_number(0);
                            }
                            unit.bathroom3.setWindows_waterdamage(getData(WATER_DAMAGE));
                            break;
                        case "Blinds/Drapes":
                            unit.bathroom3.setBlindsdrapes_score(Integer.valueOf(getData(R)));
                            unit.bathroom3.setBlindsdrapes_type(getData(TYPE));
                            break;
                        case "Light Fixture":
                            unit.bathroom3.setLight_score(Integer.valueOf(getData(R)));
                            unit.bathroom3.setLight_type(getData(LIGHTING_TYPE));
                            unit.bathroom3.setLight_finish(getData(FINISH));
                            break;
                        case "Sink":
                            unit.bathroom3.setSink_score(Integer.valueOf(getData(R)));
                            unit.bathroom3.setSink_material(getData(MATERIAL));
                            unit.bathroom3.setSink_type(getData(SINGLE_DOUBLE));
                            break;
                        case "Tub/Shower":
                            unit.bathroom3.setTubshower_score(Integer.valueOf(getData(R)));
                            unit.bathroom3.setTubshower_material(getData(PLASTIC_TILE));
                            unit.bathroom3.setTubshower_type(getData(TYPE));
                            break;
                        case "Commode":
                            unit.bathroom3.setCommode_score(Integer.valueOf(getData(R)));
                            break;
                        case "Medicine Cabinet":
                            unit.bathroom3.setMedicinecabinet_score(Integer.valueOf(getData(R)));
                            break;
                        case "Mirror":
                            unit.bathroom3.setMirror_score(Integer.valueOf(getData(R)));
                            break;
                        
                    }
                    break;
                case "Grading":
                    switch(item) {
                        case "Occupancy":
                            unit.overall.setOccupancy(getData(VACANT_OCCUPIED));
                            break;
                        case "Cleanliness":
                            unit.overall.setCleanlinesss_score(Integer.valueOf(getData(R)));
                            break;
                        case "Furnishings":
                            unit.overall.setFurnishings_score(Integer.valueOf(getData(R)));
                            break;
                        case "Pets":
                            unit.overall.setPets_none(getData(NONE).equals("None"));
                            unit.overall.setPets_dog(getData(DOG).equals("Dog"));
                            unit.overall.setPets_cat(getData(CAT).equals("Cat"));
                            unit.overall.setPets_other(getData(OTHER).equals("Other"));
                            unit.overall.setPets_number(getData(NUMBER));
                            break;
                        case "Other":
                            if(getData(SMOKE_ALARMS).equals("Smoke Alarms")) { unit.overall.setHasSmokeDetectors(true); }
                            if(getData(SMOKER).equals("Smoker")) { unit.overall.setSmoker(true); }
                            if(getData(HOARDER).equals("Hoarder")) { unit.overall.setHoarder(true); }
                            break;
                        case "Overall Grade":
                            unit.overall.setLetter_grade(getData(GRADE));
                            break;
                    }
                    break;
                case "Footer":
                    switch(item) {
                        case "General Comments":
                            unit.overall.setGeneral_comments(getData(NOTES));
                            break;
                    }
                    break;
                default:
                    fillInteriorNote(unit, section, item, parseRowIntoString());
            }
            
        }

        // NOTES
        //int r = 1;
        Unit _unit = null;

        section = "";
        item = "";

        String note = "";

        for (int i = 1; i < sheet.getLastRowNum()+1; i++) {
            row = sheet.getRow(i);
            String id = row.getCell(0).toString();

            Set<String> keys = getUnits().keySet();
            for (String _key : keys) {
                if (getUnits().get(_key).getID().equals(id)) {
                    _unit = getUnits().get(_key);

                    section = row.getCell(SECTION).toString();
                    item = row.getCell(ITEM).toString();

                    note = row.getCell(NOTES).toString();
                }
            }
            fillInteriorNote(_unit, section, item, note);
        }

        // PICTURES
        for (int i = 1; i < sheet.getLastRowNum()+1; i++) {
            row = sheet.getRow(i);
            String id = row.getCell(0).toString();

            Set<String> keys = getUnits().keySet();
            for (String _key : keys) {
                if (getUnits().get(_key).getID().equals(id)) {
                    _unit = getUnits().get(_key);

                    section = row.getCell(SECTION).toString();
                    item = row.getCell(ITEM).toString();

                    int counter = 0;
                    while (true) {
                        try {
                            String url = row.getCell(PHOTOS_START + counter).toString();
                            _unit.addPicture(section, item, url);

                            counter++;
                        } catch (NullPointerException ex) {
                            break;
                        }
                    }
                }
            }
        }
    } // end fillUnits()

    public String parseRowIntoString() {
        
        String str = row.getCell(20).toString() + "/" + row.getCell(21).toString() + ": ";
        for (int i = 22; i < row.getLastCellNum(); i++) {
            String cellData = getData(i);
            if (!cellData.equals("")) {
                XSSFRow tRow = sheet.getRow(0);
                String header = tRow.getCell(i).toString();
                str += " " + header + ": " + cellData + "\n";
            }
        }
        
        //System.out.println(str);
        return str;
    }
    
    /**
     * Function that gets the individual note from fillUnits and puts it in its
     * proper place. Basically switches all over the place
     *
     * @param unit
     * @param section
     * @param item
     * @param note
     */
    public void fillInteriorNote(Unit unit, String section, String item, String note) {
        switch (section) {
            case "Entry/Hall":
                switch (item) {
                    case "Entry/Hall Comments":
                        unit.entry.setEntry_comments(note);
                        break;
                    case "Doors":
                        unit.entry.setDoor_notes(note);
                        break;
                    case "Floor":
                        unit.entry.setFloor_notes(note);
                        break;
                    case "Stairs":
                        unit.entry.setStairs_notes(note);
                        break;
                    case "Walls/Ceiling":
                        unit.entry.setWallsceiling_notes(note);
                        break;
                    case "Closet":
                        unit.entry.setCloset_notes(note);
                        break;
                    case "Windows":
                        unit.entry.setWindows_notes(note);
                        break;
                    case "Blinds/Drapes":
                        unit.entry.setBlindsdrapes_notes(note);
                        break;
                    case "Light Fixture":
                        unit.entry.setLight_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Living Room":
                switch (item) {
                    case "Living Room Comments":
                        unit.livingroom.setLivingroom_comments(note);
                        break;
                    case "Floor":
                        unit.livingroom.setFloor_notes(note);
                        break;
                    case "Walls/Ceiling":
                        unit.livingroom.setWallsceiling_notes(note);
                        break;
                    case "Doors":
                        unit.livingroom.setDoor_notes(note);
                        break;
                    case "Windows":
                        unit.livingroom.setWindows_notes(note);
                        break;
                    case "Blinds/Drapes":
                        unit.livingroom.setBlindsdrapes_notes(note);
                        break;
                    case "Light Fixture":
                        unit.livingroom.setLight_notes(note);
                        break;
                    case "Ceiling Fan":
                        unit.livingroom.setCeilingfan_notes(note);
                        break;
                    case "Patio/Bal":
                        unit.livingroom.setPatio_notes(note);
                        break;
                    case "Fireplace":
                        unit.livingroom.setFireplace_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Dining Room":
                switch (item) {
                    case "Dining Room Comments":
                        unit.diningroom.setDiningroom_comments(note);
                        break;
                    case "Floor":
                        unit.diningroom.setFloor_notes(note);
                        break;
                    case "Walls/Ceiling":
                        unit.diningroom.setWallsceiling_notes(note);
                        break;
                    case "Doors":
                        unit.diningroom.setDoor_notes(note);
                        break;
                    case "Closet":
                        unit.diningroom.setCloset_notes(note);
                        break;
                    case "Windows":
                        unit.diningroom.setWindows_notes(note);
                        break;
                    case "Blinds/Drapes":
                        unit.diningroom.setBlindsdrapes_notes(note);
                        break;
                    case "Light Fixture":
                        unit.diningroom.setLight_notes(note);
                        break;
                    case "Ceiling Fan":
                        unit.diningroom.setCeilingfan_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Kitchen":
                switch (item) {
                    case "Kitchen Comments":
                        unit.kitchen.setKitchen_comments(note);
                        break;
                    case "Floor":
                        unit.kitchen.setFloor_notes(note);
                        break;
                    case "Walls/Ceiling":
                        unit.kitchen.setWallsceiling_notes(note);
                        break;
                    case "Windows":
                        unit.kitchen.setWindows_notes(note);
                        break;
                    case "Doors":
                        unit.kitchen.setDoor_notes(note);
                        break;
                    case "Pantry":
                        unit.kitchen.setPantry_notes(note);
                        break;
                    case "Blinds/Drapes":
                        unit.kitchen.setBlindsdrapes_notes(note);
                        break;
                    case "Light Fixture":
                        unit.kitchen.setLight_notes(note);
                        break;
                    case "Sink":
                        unit.kitchen.setSink_notes(note);
                        break;
                    case "Cabinets":
                        unit.kitchen.setCabinets_notes(note);
                        break;
                    case "Countertop":
                        unit.kitchen.setCountertops_notes(note);
                        break;
                    case "Pass/Bar":
                        unit.kitchen.setPassbar_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Mechanical":
                switch (item) {
                    case "Mechanical Comments":
                        unit.mechanical.setMechanical_comments(note);
                        break;
                    case "Range":
                        unit.mechanical.setRange_notes(note);
                        break;
                    case "Refridgerator":
                        unit.mechanical.setFridge_notes(note);
                        break;
                    case "Dishwasher":
                        unit.mechanical.setDishwasher_notes(note);
                        break;
                    case "Microwave":
                        unit.mechanical.setMicrowave_notes(note);
                        break;
                    case "Hood/Vent":
                        unit.mechanical.setHood_notes(note);
                        break;
                    case "HVAC":
                        unit.mechanical.setHvac_notes(note);
                        break;
                    case "Water Heater":
                        unit.mechanical.setWater_heater_notes(note);
                        break;
                    case "W/D":
                        unit.mechanical.setWasher_dryer_notes(note);
                        break;
                    case "Disposal":
                        unit.mechanical.setDisposal_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Utility/Storage":
                switch (item) {
                    case "Utility/Storage Comments":
                        unit.utility.setUtility_comments(note);
                        break;
                    case "Door":
                        unit.utility.setDoor_notes(note);
                        break;
                    case "Floor":
                        unit.utility.setFloor_notes(note);
                        break;
                    case "Walls/Ceiling":
                        unit.utility.setWallsceiling_notes(note);
                        break;
                    case "Sump":
                        unit.utility.setSump_notes(note);
                        break;
                    case "Light Fixture":
                        unit.utility.setLight_notes(note);
                    default:
                        break;
                }
                break;
            case "Bedroom #1":
                switch (item) {
                    case "Bedroom #1 Comments":
                        unit.bedroom1.setBedroom_comments(note);
                        break;
                    case "Floor":
                        unit.bedroom1.setFloor_notes(note);
                        break;
                    case "Walls/Ceiling":
                        unit.bedroom1.setWallsceiling_notes(note);
                        break;
                    case "Doors":
                        unit.bedroom1.setDoor_notes(note);
                        break;
                    case "Closet":
                        unit.bedroom1.setCloset_notes(note);
                        break;
                    case "Windows":
                        unit.bedroom1.setWindows_notes(note);
                        break;
                    case "Blinds/Drapes":
                        unit.bedroom1.setBlindsdrapes_notes(note);
                        break;
                    case "Light Fixture":
                        unit.bedroom1.setLight_notes(note);
                    case "Ceiling Fan":
                        unit.bedroom1.setCeilingfan_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Bedroom #2":
                switch (item) {
                    case "Bedroom #2 Comments":
                        unit.bedroom2.setBedroom_comments(note);
                        break;
                    case "Floor":
                        unit.bedroom2.setFloor_notes(note);
                        break;
                    case "Walls/Ceiling":
                        unit.bedroom2.setWallsceiling_notes(note);
                        break;
                    case "Doors":
                        unit.bedroom2.setDoor_notes(note);
                        break;
                    case "Closet":
                        unit.bedroom2.setCloset_notes(note);
                        break;
                    case "Windows":
                        unit.bedroom2.setWindows_notes(note);
                        break;
                    case "Blinds/Drapes":
                        unit.bedroom2.setBlindsdrapes_notes(note);
                        break;
                    case "Light Fixture":
                        unit.bedroom2.setLight_notes(note);
                    case "Ceiling Fan":
                        unit.bedroom2.setCeilingfan_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Bedroom #3":
                switch (item) {
                    case "Bedroom #3 Comments":
                        unit.bedroom3.setBedroom_comments(note);
                        break;
                    case "Floor":
                        unit.bedroom3.setFloor_notes(note);
                        break;
                    case "Walls/Ceiling":
                        unit.bedroom3.setWallsceiling_notes(note);
                        break;
                    case "Doors":
                        unit.bedroom3.setDoor_notes(note);
                        break;
                    case "Closet":
                        unit.bedroom3.setCloset_notes(note);
                        break;
                    case "Windows":
                        unit.bedroom3.setWindows_notes(note);
                        break;
                    case "Blinds/Drapes":
                        unit.bedroom3.setBlindsdrapes_notes(note);
                        break;
                    case "Light Fixture":
                        unit.bedroom3.setLight_notes(note);
                    case "Ceiling Fan":
                        unit.bedroom3.setCeilingfan_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Bathroom #1":
                switch (item) {
                    case "Bathroom #1 Comments":
                        unit.bathroom1.setBathroom_comments(note);
                        break;
                    case "Floor":
                        unit.bathroom1.setFloor_notes(note);
                        break;
                    case "Walls/Ceiling":
                        unit.bathroom1.setWallsceiling_notes(note);
                        break;
                    case "Doors":
                        unit.bathroom1.setDoor_notes(note);
                        break;
                    case "Windows":
                        unit.bathroom1.setWindows_notes(note);
                        break;
                    case "Blinds/Drapes":
                        unit.bathroom1.setBlindsdrapes_notes(note);
                        break;
                    case "Light Fixture":
                        unit.bathroom1.setLight_notes(note);
                        break;
                    case "Sink":
                        unit.bathroom1.setSink_notes(note);
                        break;
                    case "Tub/Shower":
                        unit.bathroom1.setTubshower_notes(note);
                        break;
                    case "Commode":
                        unit.bathroom1.setCommode_notes(note);
                        break;
                    case "Medicine Cabinet":
                        unit.bathroom1.setMedicinecabinet_notes(note);
                        break;
                    case "Mirror":
                        unit.bathroom1.setMirror_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Bathroom #2":
                switch (item) {
                    case "Bathroom #2 Comments":
                        unit.bathroom2.setBathroom_comments(note);
                        break;
                    case "Floor":
                        unit.bathroom2.setFloor_notes(note);
                        break;
                    case "Walls/Ceiling":
                        unit.bathroom2.setWallsceiling_notes(note);
                        break;
                    case "Doors":
                        unit.bathroom2.setDoor_notes(note);
                        break;
                    case "Windows":
                        unit.bathroom2.setWindows_notes(note);
                        break;
                    case "Blinds/Drapes":
                        unit.bathroom2.setBlindsdrapes_notes(note);
                        break;
                    case "Light Fixture":
                        unit.bathroom2.setLight_notes(note);
                        break;
                    case "Sink":
                        unit.bathroom2.setSink_notes(note);
                        break;
                    case "Tub/Shower":
                        unit.bathroom2.setTubshower_notes(note);
                        break;
                    case "Commode":
                        unit.bathroom2.setCommode_notes(note);
                        break;
                    case "Medicine Cabinet":
                        unit.bathroom2.setMedicinecabinet_notes(note);
                        break;
                    case "Mirror":
                        unit.bathroom2.setMirror_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Bathroom #3":
                switch (item) {
                    case "Bathroom #3 Comments":
                        unit.bathroom3.setBathroom_comments(note);
                        break;
                    case "Floor":
                        unit.bathroom3.setFloor_notes(note);
                        break;
                    case "Walls/Ceiling":
                        unit.bathroom3.setWallsceiling_notes(note);
                        break;
                    case "Doors":
                        unit.bathroom3.setDoor_notes(note);
                        break;
                    case "Windows":
                        unit.bathroom3.setWindows_notes(note);
                        break;
                    case "Blinds/Drapes":
                        unit.bathroom3.setBlindsdrapes_notes(note);
                        break;
                    case "Light Fixture":
                        unit.bathroom3.setLight_notes(note);
                        break;
                    case "Sink":
                        unit.bathroom3.setSink_notes(note);
                        break;
                    case "Tub/Shower":
                        unit.bathroom3.setTubshower_notes(note);
                        break;
                    case "Commode":
                        unit.bathroom3.setCommode_notes(note);
                        break;
                    case "Medicine Cabinet":
                        unit.bathroom3.setMedicinecabinet_notes(note);
                        break;
                    case "Mirror":
                        unit.bathroom3.setMirror_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Grading":
                switch (item) {
                    case "Cleanliness":
                        unit.overall.setCleanliness_notes(note);
                        break;
                    case "Furnishings":
                        unit.overall.setFurnishings_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Footer":
                unit.overall.setGeneral_comments(note);
                break;
            default:
                
                if(unit.overall.getUnaccounted_sections()== null) {
                    unit.overall.setUnaccounted_sections(note);
                } else {
                    unit.overall.setUnaccounted_sections(unit.overall.getUnaccounted_sections() + note);
                }
                break;
        }
    }

    /**
     * Function to return data from cell Tests for a Null value to prevent NPE
     *
     * @param col, the column of the row to return
     * @return the value of the cell
     */
    public String getData(int col) {
        if (row.getCell(col) == null) {
            return "";
        } else if (row.getCell(col).toString().equals("")) {
            return "";
        } else {
            if (row.getCell(col).toString().endsWith(".0")) {
                return row.getCell(col).toString().substring(0, 1);
            } else if (col == NUMBER) {
                return "0";
            } else {
                return row.getCell(col).toString();
            }
        }
    } // end getData()

    /**
     * Function to return data from cell Tests for null to prevent NPE
     *
     * @param col, the column of the row to return
     * @return the value of the cell
     */
    public String getString(int col) {
        if (row.getCell(col) == null) {
            return "";
        } else {
            return row.getCell(col).toString();
        }
    }
    // <editor-fold desc="huge block of code that might be now unnecessary" defaultstate="collapsed">
    
//    /**
//     * Function to find the starting points of each room
//     */
//    public void findStartingPoints() {
//        for (int i = 0; i < headers.length; i++) {
//            String temp = "";
//            if (headers[i].equals(temp)) {
//                // do nothing, continue
//            } else {
//                switch (headers[i]) {
//                    case "Bathroom #1":
//                        bathroom1_SP = i - 10;
//                        break;
//                    case "Bathroom #2":
//                        bathroom2_SP = i - 10;
//                        break;
//                    case "Bathroom #3":
//                        bathroom3_SP = i - 10;
//                        break;
//                    case "Bedroom #1":
//                        bedroom1_SP = i - 7;
//                        break;
//                    case "Bedroom #2":
//                        bedroom2_SP = i - 7;
//                        break;
//                    case "Bedroom #3":
//                        bedroom3_SP = i - 7;
//                        break;
//                    case "Dining Room":
//                        diningroom_SP = i - 7;
//                        break;
//                    case "Entry/Hall":
//                        entryhall_SP = i - 7;
//                        break;
//                    case "Grading":
//                        grading_SP = i - 5;
//                        break;
//                    case "Kitchen":
//                        kitchen_SP = i - 10;
//                        break;
//                    case "Living Room":
//                        livingroom_SP = i - 8;
//                        break;
//                    case "Mechanical":
//                        mechanical_SP = i - 8;
//                        break;
//                    case "Utility/Storage":
//                        utility_SP = i - 3;
//                        break;
//                    default:
//                        System.err.println("Error in findStartingPoints()");
//                        break;
//                }
//            }
//        }
//    }
//
//    /**
//     * Import a 1x1 unit
//     */
//    public void import1x1() {
//
//        // reset the counter
//        count = 0;
//
//        // do the import
//        importEntry();
//        importLivingRoom();
//        importDiningRoom();
//        importKitchen();
//        importMechanical();
//        importUtility();
//        importBedroom1();
//        importBathroom1();
//        importOverall();
//    }
//
//    /**
//     * Import a 2x1 unit
//     */
//    public void import2x1() {
//
//        // reset the counter
//        count = 0;
//
//        // do the import
//        importEntry();
//        importLivingRoom();
//        importDiningRoom();
//        importKitchen();
//        importMechanical();
//        importUtility();
//        importBedroom1();
//        importBedroom2();
//        importBathroom1();
//        importOverall();
//    }
//
//    /**
//     * Import a 2x2 unit
//     */
//    public void import2x2() {
//
//        // reset the counter
//        count = 0;
//
//        // do the import
//        importEntry();
//        importLivingRoom();
//        importDiningRoom();
//        importKitchen();
//        importMechanical();
//        importUtility();
//        importBedroom1();
//        importBedroom2();
//        importBathroom1();
//        importBathroom2();
//        importOverall();
//    }
//
//    /**
//     * Import a 3x2 unit
//     */
//    public void import3x2() {
//
//        // reset the counter
//        count = 0;
//
//        // do the import
//        importEntry();
//        importLivingRoom();
//        importDiningRoom();
//        importKitchen();
//        importMechanical();
//        importUtility();
//        importBedroom1();
//        importBedroom2();
//        importBedroom3();
//        importBathroom1();
//        importBathroom2();
//        importOverall();
//    }
//
//    /**
//     * Import a 1x1 unit
//     */
//    public void import3x3() {
//
//        // reset the counter
//        count = 0;
//
//        // do the import
//        importEntry();
//        importLivingRoom();
//        importDiningRoom();
//        importKitchen();
//        importMechanical();
//        importUtility();
//        importBedroom1();
//        importBedroom2();
//        importBedroom3();
//        importBathroom1();
//        importBathroom2();
//        importBathroom3();
//        importOverall();
//    }
//
//    /**
//     * Function to import entry/hall
//     */
//    public void importEntry() {
//
//        // start it at entry
//        count = entryhall_SP;
//
//        // make an array
//        String[] arr;
//
//        // blinds/drapes
//        arr = getWindow_Blinds(data[count]);
//        unit.entry.setBlindsdrapes_score(Integer.valueOf(arr[0]));
//        unit.entry.setBlindsdrapes_type(arr[1]);
//        count++;
//
//        // closet
//        arr = getDoor(data[count]);
//        unit.entry.setCloset_door_type(arr[0]);
//        unit.entry.setCloset_door_variety(arr[1]);
//        unit.entry.setCloset_score(Integer.valueOf(arr[2]));
//        count++;
//
//        // doors
//        arr = getDoor(data[count]);
//        unit.entry.setDoor_type(arr[0]);
//        unit.entry.setDoor_variety(arr[1]);
//        unit.entry.setDoor_score(Integer.valueOf(arr[2]));
//        count++;
//
//        // floor
//        arr = getFloor(data[count]);
//        unit.entry.setFloor_type(arr[0]);
//        unit.entry.setFloor_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // light fixture
//        arr = getLighting(data[count]);
//        unit.entry.setLight_type(arr[0]);
//        unit.entry.setLight_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // stairs
//        arr = getFloor(data[count]);
//        unit.entry.setStairs_flooring(arr[0]);
//        unit.entry.setStairs_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // walls/ceiling
//        arr = getWallsCeiling(data[count]);
//        unit.entry.setWallsceiling_score(Integer.valueOf(arr[0]));
//        unit.entry.setWallsceiling_type(arr[1]);
//        count++;
//
//        // windows
//        arr = getWindow_Blinds(data[count]);
//        unit.entry.setWindows_score(Integer.valueOf(arr[0]));
//        unit.entry.setWindows_type(arr[1]);
//        count++;
//
//    } // end importEntry()
//
//    /**
//     * Function to import living room
//     */
//    public void importLivingRoom() {
//
//        // start it at living room
//        count = livingroom_SP;
//
//        // make an array
//        String[] arr;
//
//        // blinds/drapes
//        arr = getWindow_Blinds(data[count]);
//        unit.livingroom.setBlindsdrapes_score(Integer.valueOf(arr[0]));
//        unit.livingroom.setBlindsdrapes_type(arr[1]);
//        count++;
//
//        // ceiling fan
//        unit.livingroom.setCeilingfan_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // doors
//        arr = getDoor(data[count]);
//        unit.livingroom.setDoor_type(arr[0]);
//        unit.livingroom.setDoor_variety(arr[1]);
//        unit.livingroom.setDoor_score(Integer.valueOf(arr[2]));
//        count++;
//
//        // fireplace
//        unit.livingroom.setFireplace_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // floor
//        arr = getFloor(data[count]);
//        unit.livingroom.setFloor_type(arr[0]);
//        unit.livingroom.setFloor_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // lighting fixture
//        arr = getLighting(data[count]);
//        unit.livingroom.setLight_type(arr[0]);
//        unit.livingroom.setLight_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // patio/bal
//        unit.livingroom.setPatio_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // walls/ceiling
//        arr = getWallsCeiling(data[count]);
//        unit.livingroom.setWallsceiling_score(Integer.valueOf(arr[0]));
//        unit.livingroom.setWallsceiling_type(arr[1]);
//        count++;
//
//        // windows
//        arr = getWindow_Blinds(data[count]);
//        unit.livingroom.setWindows_score(Integer.valueOf(arr[0]));
//        unit.livingroom.setWindows_type(arr[1]);
//        count++;
//    } // end importLivingRoom()
//
//    /**
//     * Function to import dining room
//     */
//    public void importDiningRoom() {
//
//        // start it at dining room
//        count = diningroom_SP;
//
//        // make an array
//        String[] arr;
//
//        // blinds/drapes
//        arr = getWindow_Blinds(data[count]);
//        unit.diningroom.setBlindsdrapes_type(arr[1]);
//        unit.diningroom.setBlindsdrapes_score(Integer.valueOf(arr[0]));
//        count++;
//
//        // ceiling fan
//        unit.diningroom.setCeilingfan_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // closet
//        arr = getDoor(data[count]);
//        unit.diningroom.setCloset_door_type(arr[0]);
//        unit.diningroom.setCloset_door_variety(arr[1]);
//        unit.diningroom.setCloset_score(Integer.valueOf(arr[2]));
//        count++;
//
//        // doors
//        arr = getDoor(data[count]);
//        unit.diningroom.setDoor_type(arr[0]);
//        unit.diningroom.setDoor_variety(arr[1]);
//        unit.diningroom.setDoor_score(Integer.valueOf(arr[2]));
//        count++;
//
//        // floor
//        arr = getFloor(data[count]);
//        unit.diningroom.setFloor_type(arr[0]);
//        unit.diningroom.setFloor_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // light fixture
//        arr = getLighting(data[count]);
//        unit.diningroom.setLight_type(arr[0]);
//        unit.diningroom.setLight_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // walls/ceiling
//        arr = getWallsCeiling(data[count]);
//        unit.diningroom.setWallsceiling_score(Integer.valueOf(arr[0]));
//        unit.diningroom.setWallsceiling_type(arr[1]);
//        count++;
//
//        // windows
//        arr = getWindow_Blinds(data[count]);
//        unit.diningroom.setWindows_score(Integer.valueOf(arr[0]));
//        unit.diningroom.setWindows_type(arr[1]);
//        count++;
//
//    } // end importDiningRoom()
//
//    /**
//     * Function to import kitchen
//     */
//    public void importKitchen() {
//
//        // start it at kitchen
//        count = kitchen_SP;
//
//        // make an arry
//        String[] arr;
//
//        // blinds/drapes
//        arr = getWindow_Blinds(data[count]);
//        unit.kitchen.setBlindsdrapes_type(arr[1]);
//        unit.kitchen.setBlindsdrapes_score(Integer.valueOf(arr[0]));
//        count++;
//
//        // cabinets
//        arr = getMaterial(data[count]);
//        unit.kitchen.setCabinets_type(arr[0]);
//        unit.kitchen.setCabinets_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // countertop
//        arr = getMaterial(data[count]);
//        unit.kitchen.setCountertops_type(arr[0]);
//        unit.kitchen.setCountertops_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // doors
//        arr = getDoor(data[count]);
//        unit.kitchen.setDoor_type(arr[0]);
//        unit.kitchen.setDoor_variety(arr[1]);
//        unit.kitchen.setDoor_score(Integer.valueOf(arr[2]));
//        count++;
//
//        // floor
//        arr = getFloor(data[count]);
//        unit.kitchen.setFloor_type(arr[0]);
//        unit.kitchen.setFloor_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // light fixture
//        arr = getLighting(data[count]);
//        unit.kitchen.setLight_type(arr[0]);
//        unit.kitchen.setLight_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // pantry
//        arr = getDoor(data[count]);
//        unit.kitchen.setPantry_door_type(arr[0]);
//        unit.kitchen.setPantry_door_variety(arr[1]);
//        unit.kitchen.setPantry_score(Integer.valueOf(arr[2]));
//        count++;
//
//        // pass/bar
//        arr = getMaterial(data[count]);
//        unit.kitchen.setPassbar_type(arr[0]);
//        unit.kitchen.setPassbar_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // sink
//        arr = getSink_TubShower(data[count]);
//        unit.kitchen.setSink_score(Integer.valueOf(arr[0]));
//        unit.kitchen.setSink_type(arr[1]);
//        count++;
//
//        // walls/ceiling
//        arr = getWallsCeiling(data[count]);
//        unit.kitchen.setWallsceiling_score(Integer.valueOf(arr[0]));
//        unit.kitchen.setWallsceiling_type(arr[1]);
//        count++;
//
//        // windows
//        arr = getWindow_Blinds(data[count]);
//        unit.kitchen.setWindows_score(Integer.valueOf(arr[0]));
//        unit.kitchen.setWindows_type(arr[1]);
//        count++;
//
//    } // end importKitchen()
//
//    /**
//     * Function to import mechanical
//     */
//    public void importMechanical() {
//
//        // start it at entry
//        count = mechanical_SP;
//
//        // make an array
//        String[] arr;
//
//        // dishwasher
//        arr = getMechanical(data[count]);
//        unit.mechanical.setDishwasher_model(arr[0]);
//        unit.mechanical.setDishwasher_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // disposal
//        unit.mechanical.setDisposal_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // HVAC
//        arr = getMechanical(data[count]);
//        unit.mechanical.setHvac_model(arr[0]);
//        unit.mechanical.setHvac_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // hood/vent
//        arr = getMechanical(data[count]);
//        unit.mechanical.setHood_model(arr[0]);
//        unit.mechanical.setHood_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // microwave
//        arr = getMechanical(data[count]);
//        unit.mechanical.setMicrowave_model(arr[0]);
//        unit.mechanical.setMicrowave_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // range
//        arr = getRange(data[count]);
//        unit.mechanical.setRange_model(arr[0]);
//        unit.mechanical.setRange_model(arr[1]);
//        unit.mechanical.setRange_score(Integer.valueOf(arr[2]));
//        count++;
//
//        // fridge
//        arr = getMechanical(data[count]);
//        unit.mechanical.setFridge_model(arr[0]);
//        unit.mechanical.setFridge_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // w/d
//        arr = getWD(data[count]);
//        unit.mechanical.setWasher_dryer_model(arr[0]);
//        unit.mechanical.setWasher_dryer_score(Integer.valueOf(arr[1]));
//        unit.mechanical.setHasHookup(Boolean.parseBoolean(arr[2]));
//        count++;
//
//        // water heater
//        arr = getMechanical(data[count]);
//        unit.mechanical.setWater_heater_model(arr[0]);
//        unit.mechanical.setWater_heater_score(Integer.valueOf(arr[1]));
//        count++;
//
//    } // end importMechanical()
//
//    /**
//     * Function to import utility/storage
//     */
//    public void importUtility() {
//
//        // start it at entry
//        count = utility_SP;
//
//        // make an array
//        String[] arr;
//
//        // doors
//        arr = getDoor(data[count]);
//        unit.utility.setDoor_type(arr[0]);
//        unit.utility.setDoor_variety(arr[1]);
//        unit.utility.setDoor_score(Integer.valueOf(arr[2]));
//        count++;
//
//        // floor
//        arr = getFloor(data[count]);
//        unit.utility.setFloor_type(arr[0]);
//        unit.utility.setFloor_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // sump
//        unit.utility.setSump_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // walls/ceiling
//        arr = getWallsCeiling(data[count]);
//        unit.utility.setWallsceiling_score(Integer.valueOf(arr[0]));
//        unit.utility.setWallsceiling_type(arr[1]);
//        count++;
//    } // end importUtilty()
//
//    /**
//     * Function to import bedroom
//     */
//    public void importBedroom1() {
//
//        // start it at bedroom1
//        count = bedroom1_SP;
//
//        // make an array
//        String[] arr;
//
//        // blinds/drapes
//        arr = getWindow_Blinds(data[count]);
//        unit.bedroom1.setBlindsdrapes_type(arr[1]);
//        unit.bedroom1.setBlindsdrapes_score(Integer.valueOf(arr[0]));
//        count++;
//
//        // ceiling fan
//        unit.bedroom1.setCeilingfan_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // closet
//        arr = getDoor(data[count]);
//        unit.bedroom1.setCloset_door_type(arr[0]);
//        unit.bedroom1.setCloset_door_variety(arr[1]);
//        unit.bedroom1.setCloset_score(Integer.valueOf(arr[2]));
//        count++;
//
//        // doors
//        arr = getDoor(data[count]);
//        unit.bedroom1.setDoor_type(arr[0]);
//        unit.bedroom1.setDoor_variety(arr[1]);
//        unit.bedroom1.setDoor_score(Integer.valueOf(arr[2]));
//        count++;
//
//        // floor
//        arr = getFloor(data[count]);
//        unit.bedroom1.setFloor_type(arr[0]);
//        unit.bedroom1.setFloor_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // light fixture
//        arr = getLighting(data[count]);
//        unit.bedroom1.setLight_type(arr[0]);
//        unit.bedroom1.setLight_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // walls/ceiling
//        arr = getWallsCeiling(data[count]);
//        unit.bedroom1.setWallsceiling_score(Integer.valueOf(arr[0]));
//        unit.bedroom1.setWallsceiling_type(arr[1]);
//        count++;
//
//        // windows
//        arr = getWindow_Blinds(data[count]);
//        unit.bedroom1.setWindows_score(Integer.valueOf(arr[0]));
//        unit.bedroom1.setWindows_type(arr[1]);
//        count++;
//    } // end importBedroom()
//
//    /**
//     * Function to import bedroom
//     */
//    public void importBedroom2() {
//
//        // start it at bedroom2
//        count = bedroom2_SP;
//
//        // make an array
//        String[] arr;
//
//        // blinds/drapes
//        arr = getWindow_Blinds(data[count]);
//        unit.bedroom2.setBlindsdrapes_type(arr[1]);
//        unit.bedroom2.setBlindsdrapes_score(Integer.valueOf(arr[0]));
//        count++;
//
//        // ceiling fan
//        unit.bedroom2.setCeilingfan_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // closet
//        arr = getDoor(data[count]);
//        unit.bedroom2.setCloset_door_type(arr[0]);
//        unit.bedroom2.setCloset_door_variety(arr[1]);
//        unit.bedroom2.setCloset_score(Integer.valueOf(arr[2]));
//        count++;
//
//        // doors
//        arr = getDoor(data[count]);
//        unit.bedroom2.setDoor_type(arr[0]);
//        unit.bedroom2.setDoor_variety(arr[1]);
//        unit.bedroom2.setDoor_score(Integer.valueOf(arr[2]));
//        count++;
//
//        // floor
//        arr = getFloor(data[count]);
//        unit.bedroom2.setFloor_type(arr[0]);
//        unit.bedroom2.setFloor_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // light fixture
//        arr = getLighting(data[count]);
//        unit.bedroom2.setLight_type(arr[0]);
//        unit.bedroom2.setLight_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // walls/ceiling
//        arr = getWallsCeiling(data[count]);
//        unit.bedroom2.setWallsceiling_score(Integer.valueOf(arr[0]));
//        unit.bedroom2.setWallsceiling_type(arr[1]);
//        count++;
//
//        // windows
//        arr = getWindow_Blinds(data[count]);
//        unit.bedroom2.setWindows_score(Integer.valueOf(arr[0]));
//        unit.bedroom2.setWindows_type(arr[1]);
//        count++;
//    } // end importBedroom()
//
//    /**
//     * Function to import bedroom
//     */
//    public void importBedroom3() {
//
//        // start it at bedroom3
//        count = bedroom3_SP;
//
//        // make an array
//        String[] arr;
//
//        // blinds/drapes
//        arr = getWindow_Blinds(data[count]);
//        unit.bedroom3.setBlindsdrapes_type(arr[1]);
//        unit.bedroom3.setBlindsdrapes_score(Integer.valueOf(arr[0]));
//        count++;
//
//        // ceiling fan
//        unit.bedroom3.setCeilingfan_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // closet
//        arr = getDoor(data[count]);
//        unit.bedroom3.setCloset_door_type(arr[0]);
//        unit.bedroom3.setCloset_door_variety(arr[1]);
//        unit.bedroom3.setCloset_score(Integer.valueOf(arr[2]));
//        count++;
//
//        // doors
//        arr = getDoor(data[count]);
//        unit.bedroom3.setDoor_type(arr[0]);
//        unit.bedroom3.setDoor_variety(arr[1]);
//        unit.bedroom3.setDoor_score(Integer.valueOf(arr[2]));
//        count++;
//
//        // floor
//        arr = getFloor(data[count]);
//        unit.bedroom3.setFloor_type(arr[0]);
//        unit.bedroom3.setFloor_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // light fixture
//        arr = getLighting(data[count]);
//        unit.bedroom3.setLight_type(arr[0]);
//        unit.bedroom3.setLight_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // walls/ceiling
//        arr = getWallsCeiling(data[count]);
//        unit.bedroom3.setWallsceiling_score(Integer.valueOf(arr[0]));
//        unit.bedroom3.setWallsceiling_type(arr[1]);
//        count++;
//
//        // windows
//        arr = getWindow_Blinds(data[count]);
//        unit.bedroom3.setWindows_score(Integer.valueOf(arr[0]));
//        unit.bedroom3.setWindows_type(arr[1]);
//        count++;
//    } // end importBedroom()
//
//    /**
//     * Function to import bathroom
//     */
//    public void importBathroom1() {
//
//        // start it at bathroom1
//        count = bathroom1_SP;
//
//        // make an array
//        String[] arr;
//
//        // blinds/drapes
//        arr = getWindow_Blinds(data[count]);
//        unit.bathroom1.setBlindsdrapes_type(arr[1]);
//        unit.bathroom1.setBlindsdrapes_score(Integer.valueOf(arr[0]));
//        count++;
//
//        // commode
//        unit.bathroom1.setCommode_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // doors
//        arr = getDoor(data[count]);
//        unit.bathroom1.setDoor_type(arr[0]);
//        unit.bathroom1.setDoor_variety(arr[1]);
//        unit.bathroom1.setDoor_score(Integer.valueOf(arr[2]));
//        count++;
//
//        // floor
//        arr = getFloor(data[count]);
//        unit.bathroom1.setFloor_type(arr[0]);
//        unit.bathroom1.setFloor_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // light fixture
//        arr = getLighting(data[count]);
//        unit.bathroom1.setLight_type(arr[0]);
//        unit.bathroom1.setLight_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // medicine cabinet
//        unit.bathroom1.setMedicinecabinet_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // mirror
//        unit.bathroom1.setMirror_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // sink
//        arr = getSink_TubShower(data[count]);
//        unit.bathroom1.setSink_score(Integer.valueOf(arr[0]));
//        unit.bathroom1.setSink_type(arr[1]);
//        count++;
//
//        // tub/shower
//        arr = getSink_TubShower(data[count]);
//        unit.bathroom1.setTubshower_score(Integer.valueOf(arr[0]));
//        unit.bathroom1.setTubshower_type(arr[1]);
//        count++;
//
//        // walls/ceiling
//        arr = getWallsCeiling(data[count]);
//        unit.bathroom1.setWallsceiling_score(Integer.valueOf(arr[0]));
//        unit.bathroom1.setWallsceiling_type(arr[1]);
//        count++;
//
//        // windows
//        arr = getWindow_Blinds(data[count]);
//        unit.bathroom1.setWindows_score(Integer.valueOf(arr[0]));
//        unit.bathroom1.setWindows_type(arr[1]);
//        count++;
//
//    } // end importBathroom()
//
//    /**
//     * Function to import bathroom
//     */
//    public void importBathroom2() {
//
//        // start it at bathroom2
//        count = bathroom2_SP;
//
//        // make an array
//        String[] arr;
//
//        // blinds/drapes
//        arr = getWindow_Blinds(data[count]);
//        unit.bathroom2.setBlindsdrapes_type(arr[1]);
//        unit.bathroom2.setBlindsdrapes_score(Integer.valueOf(arr[0]));
//        count++;
//
//        // commode
//        unit.bathroom2.setCommode_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // doors
//        arr = getDoor(data[count]);
//        unit.bathroom2.setDoor_type(arr[0]);
//        unit.bathroom2.setDoor_variety(arr[1]);
//        unit.bathroom2.setDoor_score(Integer.valueOf(arr[2]));
//        count++;
//
//        // floor
//        arr = getFloor(data[count]);
//        unit.bathroom2.setFloor_type(arr[0]);
//        unit.bathroom2.setFloor_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // light fixture
//        arr = getLighting(data[count]);
//        unit.bathroom2.setLight_type(arr[0]);
//        unit.bathroom2.setLight_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // medicine cabinet
//        unit.bathroom2.setMedicinecabinet_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // mirror
//        unit.bathroom2.setMirror_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // sink
//        arr = getSink_TubShower(data[count]);
//        unit.bathroom2.setSink_score(Integer.valueOf(arr[0]));
//        unit.bathroom2.setSink_type(arr[1]);
//        count++;
//
//        // tub/shower
//        arr = getSink_TubShower(data[count]);
//        unit.bathroom2.setTubshower_score(Integer.valueOf(arr[0]));
//        unit.bathroom2.setTubshower_type(arr[1]);
//        count++;
//
//        // walls/ceiling
//        arr = getWallsCeiling(data[count]);
//        unit.bathroom2.setWallsceiling_score(Integer.valueOf(arr[0]));
//        unit.bathroom2.setWallsceiling_type(arr[1]);
//        count++;
//
//        // windows
//        arr = getWindow_Blinds(data[count]);
//        unit.bathroom2.setWindows_score(Integer.valueOf(arr[0]));
//        unit.bathroom2.setWindows_type(arr[1]);
//        count++;
//    } // end importBathroom()
//
//    /**
//     * Function to import bathroom
//     */
//    public void importBathroom3() {
//
//        // start it at bathroom3
//        count = bathroom3_SP;
//
//        // make an array
//        String[] arr;
//
//        // blinds/drapes
//        arr = getWindow_Blinds(data[count]);
//        unit.bathroom3.setBlindsdrapes_type(arr[1]);
//        unit.bathroom3.setBlindsdrapes_score(Integer.valueOf(arr[0]));
//        count++;
//
//        // commode
//        unit.bathroom3.setCommode_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // doors
//        arr = getDoor(data[count]);
//        unit.bathroom3.setDoor_type(arr[0]);
//        unit.bathroom3.setDoor_variety(arr[1]);
//        unit.bathroom3.setDoor_score(Integer.valueOf(arr[2]));
//        count++;
//
//        // floor
//        arr = getFloor(data[count]);
//        unit.bathroom3.setFloor_type(arr[0]);
//        unit.bathroom3.setFloor_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // light fixture
//        arr = getLighting(data[count]);
//        unit.bathroom3.setLight_type(arr[0]);
//        unit.bathroom3.setLight_score(Integer.valueOf(arr[1]));
//        count++;
//
//        // medicine cabinet
//        unit.bathroom3.setMedicinecabinet_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // mirror
//        unit.bathroom3.setMirror_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // sink
//        arr = getSink_TubShower(data[count]);
//        unit.bathroom3.setSink_score(Integer.valueOf(arr[0]));
//        unit.bathroom3.setSink_type(arr[1]);
//        count++;
//
//        // tub/shower
//        arr = getSink_TubShower(data[count]);
//        unit.bathroom3.setTubshower_score(Integer.valueOf(arr[0]));
//        unit.bathroom3.setTubshower_type(arr[1]);
//        count++;
//
//        // walls/ceiling
//        arr = getWallsCeiling(data[count]);
//        unit.bathroom3.setWallsceiling_score(Integer.valueOf(arr[0]));
//        unit.bathroom3.setWallsceiling_type(arr[1]);
//        count++;
//
//        // windows
//        arr = getWindow_Blinds(data[count]);
//        unit.bathroom3.setWindows_score(Integer.valueOf(arr[0]));
//        unit.bathroom3.setWindows_type(arr[1]);
//        count++;
//    } // end importBathroom()
//
//    /**
//     * Function to import overall
//     */
//    public void importOverall() {
//
//        // start it at overall/grading
//        count = grading_SP;
//
//        // make an array
//        String[] arr;
//
//        // cleanliness
//        unit.overall.setCleanlinesss_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // furnishings
//        unit.overall.setFurnishings_score(Integer.valueOf(getStandard(data[count])));
//        count++;
//
//        // occupancy
//        unit.overall.setOccupancy(getOccupancy(data[count]));
//        count++;
//
//        // other
//        arr = getOther(data[count]);
//        unit.overall.setSmoker(Boolean.parseBoolean(arr[0]));
//        unit.overall.setHoarder(Boolean.parseBoolean(arr[1]));
//        unit.overall.setHasSmokeDetectors(Boolean.parseBoolean(arr[2]));
//        count++;
//
//        // grade
//        unit.overall.setLetter_grade(getGrade(data[count]));
//        count++;
//
//        // pets
//        arr = getPets(data[count]);
//        unit.overall.setPets_none(Boolean.parseBoolean(arr[0]));
//        unit.overall.setPets_dog(Boolean.parseBoolean(arr[1]));
//        unit.overall.setPets_cat(Boolean.parseBoolean(arr[2]));
//        unit.overall.setPets_other(Boolean.parseBoolean(arr[3]));
//        unit.overall.setPets_number(arr[4]);
//        count++;
//
//    } // end importOverall()
//
//    /**
//     * Function to get a standard score
//     *
//     * @param s
//     * @return
//     */
//    public String getStandard(String s) {
//        if (s.equals("")) {
//            return "";
//        } else {
//            return s.substring(3);
//        }
//    }
//
//    /**
//     * Function to get variables for door
//     *
//     * @param s
//     * @return
//     */
//    public String[] getDoor(String s) {
//        String[] arr = new String[3];
//        arr[0] = "";
//        arr[1] = "";
//        arr[2] = "0";
//        String[] sarr = s.split(", ");
//
//        if (s.contains("Type") && s.contains("Variety") && s.contains("R:")) {
//            arr[0] = sarr[0].substring(11);
//            arr[1] = sarr[1].substring(14);
//            arr[2] = sarr[2].substring(3);
//        } else if (s.contains("Type") && s.contains("Variety") && !s.contains("R:")) {
//            arr[0] = sarr[0].substring(11);
//            arr[1] = sarr[1].substring(14);
//            arr[2] = "0";
//        } else if (s.contains("Type") && !s.contains("Variety") && s.contains("R:")) {
//            arr[0] = sarr[0].substring(11);
//            arr[1] = "";
//            arr[2] = sarr[1].substring(3);
//        } else if (!s.contains("Type") && s.contains("Variety") && s.contains("R:")) {
//            arr[0] = "";
//            arr[1] = sarr[0].substring(14);
//            arr[2] = sarr[1].substring(3);
//        } else if (s.contains("Type") && !s.contains("Variety") && !s.contains("R:")) {
//            arr[0] = sarr[0].substring(11);
//            arr[1] = "";
//            arr[2] = "0";
//        } else if (!s.contains("Type") && s.contains("Variety") && !s.contains("R:")) {
//            arr[0] = "";
//            arr[1] = sarr[0].substring(14);
//            arr[2] = "0";
//        } else if (!s.contains("Type") && !s.contains("Variety") && s.contains("R:")) {
//            arr[0] = "";
//            arr[1] = "";
//            arr[2] = sarr[0].substring(3);
//        } else {
//            // do nothing
//        }
//
//        return arr;
//    }
//
//    /**
//     * Function to get variables for Windows
//     *
//     * @param s
//     * @return
//     */
//    public String[] getWindow_Blinds(String s) {
//        String[] arr = new String[2];
//        arr[0] = "0";
//        arr[1] = "";
//        String[] sarr = s.split(", ");
//
//        if (s.contains("Type") && s.contains("R:")) {
//            arr[0] = sarr[0].substring(3);
//            arr[1] = sarr[1].substring(6);
//        } else if (s.contains("Type") && !s.contains("R:")) {
//            arr[0] = "0";
//            arr[1] = sarr[0].substring(6);
//        } else if (!s.contains("Type") && s.contains("R:")) {
//            arr[0] = sarr[0].substring(3);
//            arr[1] = "";
//        } else {
//            // do nothing
//        }
//
//        return arr;
//    }
//
//    /**
//     * Function to get variables for floor
//     *
//     * @param s
//     * @return
//     */
//    public String[] getFloor(String s) {
//        String[] arr = new String[2];
//        arr[0] = "";
//        arr[1] = "0";
//        String[] sarr = s.split(", ");
//
//        if (s.contains("Flooring") && s.contains("R:")) {
//            arr[0] = sarr[0].substring(10);
//            arr[1] = sarr[1].substring(3);
//        } else if (s.contains("Flooring") && !s.contains("R:")) {
//            arr[0] = sarr[0].substring(10);
//            arr[1] = "0";
//        } else if (!s.contains("Flooring") && s.contains("R:")) {
//            arr[0] = "";
//            arr[1] = sarr[0].substring(3);
//        } else {
//            // do nothing
//        }
//
//        return arr;
//    }
//
//    /**
//     * Function to get variables for lighting
//     *
//     * @param s
//     * @return
//     */
//    public String[] getLighting(String s) {
//        String[] arr = new String[2];
//        arr[0] = "";
//        arr[1] = "0";
//        String[] sarr = s.split(", ");
//
//        if (s.contains("Lighting Type") && s.contains("R:")) {
//            arr[0] = sarr[0].substring(15);
//            arr[1] = sarr[1].substring(3);
//        } else if (s.contains("Lighting Type") && !s.contains("R:")) {
//            arr[0] = sarr[0].substring(15);
//            arr[1] = "0";
//        } else if (!s.contains("Lighting Type") && s.contains("R:")) {
//            arr[0] = "";
//            arr[1] = sarr[0].substring(3);
//        } else {
//            // do nothing
//        }
//
//        return arr;
//    }
//
//    /**
//     * Function to get variables for sink, tub/shower
//     *
//     * @param s
//     * @return
//     */
//    public String[] getSink_TubShower(String s) {
//        String[] arr = new String[2];
//        arr[0] = "0";
//        arr[1] = "";
//
//        if (s.equals("")) {
//            return arr;
//        }
//
//        if (s.contains(", ")) {
//            String[] sarr = s.split(", ");
//            arr[0] = sarr[0].substring(3);
//            arr[1] = sarr[1];
//        } else {
//            arr[0] = s.substring(3);
//        }
//
//        return arr;
//    }
//
//    /**
//     * Function to get variables for walls/ceiling
//     *
//     * @param s
//     * @return
//     */
//    public String[] getWallsCeiling(String s) {
//        String[] arr = new String[2];
//        arr[0] = "0";
//        arr[1] = "";
//
//        if (s.contains(",")) {
//            String[] sarr = s.split(", ");
//            arr[0] = sarr[0].substring(3);
//            arr[1] = sarr[1].substring(14);
//        } else {
//            arr[0] = s.substring(3);
//        }
//
//        return arr;
//    }
//
//    /**
//     * Function to get variables for mechanical
//     *
//     * @param s
//     * @return
//     */
//    public String[] getMechanical(String s) {
//        String[] arr = new String[2];
//        arr[0] = "";
//        arr[1] = "0";
//        String[] sarr = s.split(", ");
//
//        if (s.contains("Model") && s.contains("R:")) {
//            arr[0] = sarr[0].substring(7);
//            arr[1] = sarr[1].substring(3);
//        } else if (s.contains("Model") && !s.contains("R:")) {
//            arr[0] = sarr[0].substring(7);
//            arr[1] = "0";
//        } else if (!s.contains("Model") && s.contains("R:")) {
//            arr[0] = "";
//            arr[1] = sarr[0].substring(3);
//        } else {
//            // do nothing
//        }
//
//        return arr;
//    }
//
//    /**
//     * Function to get variables for range
//     *
//     * @param s
//     * @return
//     */
//    public String[] getRange(String s) {
//        String[] arr = new String[3];
//        arr[0] = "";
//        arr[1] = "";
//        arr[2] = "0";
//        String[] sarr = s.split(", ");
//
//        if (sarr.length == 3) {
//            arr[0] = sarr[0];
//            arr[1] = sarr[1].substring(7);
//            arr[2] = sarr[2].substring(3);
//        } else if (sarr.length == 2) {
//            if (s.contains("Model") && s.contains("R:")) {
//                arr[1] = sarr[0].substring(7);
//                arr[2] = sarr[1].substring(3);
//            } else if (s.contains("Model") && !s.contains("R:")) {
//                arr[1] = sarr[0].substring(7);
//                arr[2] = "0";
//            } else if (!s.contains("Model") && s.contains("R:")) {
//                arr[1] = "";
//                arr[2] = sarr[0].substring(3);
//            } else {
//                // do nothing
//            }
//        }
//
//        return arr;
//    }
//
//    /**
//     * Function to get variables for W/D
//     *
//     * @param s
//     * @return
//     */
//    public String[] getWD(String s) {
//        String[] arr = new String[3];
//        arr[0] = "";
//        arr[1] = "0";
//        arr[2] = "false";
//        String[] sarr = s.split(", ");
//
//        if (s.contains("Model") && s.contains("R:") && s.contains("Hookup")) {
//            arr[0] = sarr[0].substring(7);
//            arr[1] = sarr[1].substring(3);
//            arr[2] = "true";
//        } else if (s.contains("Model") && s.contains("R:") && !s.contains("Hookup")) {
//            arr[0] = sarr[0].substring(7);
//            arr[1] = sarr[1].substring(3);
//            arr[2] = "false";
//        } else if (s.contains("Model") && !s.contains("R:") && s.contains("Hookup")) {
//            arr[0] = sarr[0].substring(7);
//            arr[1] = "0";
//            arr[2] = "true";
//        } else if (!s.contains("Model") && s.contains("R:") && s.contains("Hookup")) {
//            arr[0] = "";
//            arr[1] = sarr[0].substring(3);
//            arr[2] = "true";
//        } else if (s.contains("Model") && !s.contains("R:") && !s.contains("Hookup")) {
//            arr[0] = sarr[0].substring(7);
//            arr[1] = "";
//            arr[2] = "false";
//        } else if (!s.contains("Model") && s.contains("R:") && !s.contains("Hookup")) {
//            arr[0] = "";
//            arr[1] = sarr[0].substring(3);
//            arr[2] = "false";
//        } else if (!s.contains("Model") && !s.contains("R:") && s.contains("Hookup")) {
//            arr[0] = "";
//            arr[1] = "";
//            arr[2] = "true";
//        } else {
//            // do nothing
//        }
//
//        return arr;
//    }
//
//    /**
//     * Function to get variables for material
//     *
//     * @param s
//     * @return
//     */
//    public String[] getMaterial(String s) {
//        String[] arr = new String[2];
//        arr[0] = "";
//        arr[1] = "0";
//        String[] sarr = s.split(", ");
//
//        if (s.contains("Material") && s.contains("R:")) {
//            arr[0] = sarr[0].substring(10);
//            arr[1] = sarr[1].substring(3);
//        } else if (s.contains("Material") && !s.contains("R:")) {
//            arr[0] = sarr[0].substring(10);
//            arr[1] = "0";
//        } else if (!s.contains("Material") && s.contains("R:")) {
//            arr[0] = "";
//            arr[1] = sarr[0].substring(3);
//        } else {
//            // do nothing
//        }
//
//        return arr;
//    }
//
//    /**
//     * Function to get variables for occupancy
//     *
//     * @param s
//     * @return
//     */
//    public String getOccupancy(String s) {
//        if (s.equals("")) {
//            return s;
//        } else {
//            return s.substring(17);
//        }
//    }
//
//    /**
//     * Function to get variables for overall grade
//     *
//     * @param s
//     * @return
//     */
//    public String getGrade(String s) {
//        if (s.equals("")) {
//            return s;
//        } else {
//            return s.substring(7);
//        }
//    }
//
//    /**
//     * Function to get variables for other (smoker, hoarder, etc)
//     *
//     * @param s
//     * @return
//     */
//    public String[] getOther(String s) {
//        String[] arr = new String[3];
//        arr[0] = "false";
//        arr[1] = "false";
//        arr[2] = "false";
//
//        if (s.contains("Smoker")) {
//            arr[0] = "true";
//        }
//        if (s.contains("Hoarder")) {
//            arr[1] = "true";
//        }
//        if (s.contains("Smoke Alarms")) {
//            arr[2] = "true";
//        }
//
//        return arr;
//    }
//
//    /**
//     * Function to get variables for pets
//     *
//     * @param s
//     * @return
//     */
//    public String[] getPets(String s) {
//        String[] arr = {"", "", "", "", ""};
//
//        if (s.equals("")) {
//            arr[0] = "true";
//            return arr;
//        }
//        if (s.contains("None")) {
//            arr[0] = "true";
//            return arr;
//        }
//        if (s.contains("Dog")) {
//            arr[0] = "false";
//            arr[1] = "true";
//        }
//        if (s.contains("Cat")) {
//            arr[0] = "false";
//            arr[2] = "true";
//        }
//        if (s.contains("Other")) {
//            arr[0] = "false";
//            arr[3] = "true";
//        }
//        if (s.contains("Number:")) {
//            String[] sarr = s.split(": ");
//            if (sarr[1].contains("+")) {
//                arr[4] = "3+";
//            } else {
//                arr[4] = sarr[1].substring(0, 1);
//            }
//        }
//
//        return arr;
//    }
    // </editor-fold>
    
}
