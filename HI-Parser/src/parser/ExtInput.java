/**
 * File:    ExtInput.java
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
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// class ExtInput
public class ExtInput {
    
    // constants, used to get the specific column number on the big nasty 
    // spreadsheet from Happy Inspector
    private final int SECTION = 20;
    private final int ITEM = 21;
    private final static int CEILING_TYPE = 22;
    private final static int DOOR_MATERIAL = 23;
    private final static int DOOR_TYPE = 24;
    private final static int DOOR_VARIETY = 25;
    private final static int LIGHTING_TYPE = 26;
    private final static int LOCATION = 27;
    private final static int MATERIAL = 28;
    private final static int NUMBER = 29;
    private final static int R = 30;
    private final static int TYPE = 32;
    private final static int WALLS_TYPE = 33;
    private final static int YES_NO = 34;
    private final static int NOTES = 35;
    private final static int PHOTOS = 36;
    
    // ivars
    private Building building;
    
    // hashmap that contains all the buildings
    private HashMap<String, Building> buildings = new HashMap<>();
    
    // declarations for the excel file reading
    InputStream fs;
    XSSFWorkbook wb;
    XSSFSheet sheet;
    XSSFRow row;
    int rowNum;

    // variables for the filechooser
    JFileChooser fc = new JFileChooser();
    File file;
    
    /**
     * Default constructor
     */
    public ExtInput() {
        // empty
    }
    
    /**
     * Set the err and out streams to the text file
     * @param ps 
     */
    public void setOut(PrintStream ps) {
        System.setErr(ps);
        System.setOut(ps);
    }

    /**
     * @param aBuildings the buildings to set
     */
    public void setBuildings(HashMap<String, Building> aBuildings) {
        buildings = aBuildings;
    }

    /**
     * @return the buildings
     */
    public HashMap<String, Building> getBuildings() {
        return buildings;
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
        int returnVal = fc.showOpenDialog(null);

        // what to do after user chooses an option
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        } else {
            // no file chosen
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
     * Function to initialize the exterior buildings/units
     */
    public void initExterior() {
        
        // temp variable
        String temp = "";
        
        // iterate through the sheet
        for (int i = 2; i < sheet.getLastRowNum()+1; i++) {
            
            // get the row and assetID
            row = sheet.getRow(i);
            
            if(row.getCell(0).toString().equals(temp)) {
                // do nothing, continue
            } else {
                // get values
                try {
                    String id = row.getCell(0).toString();
                    String buildingName = row.getCell(14).toString();
                    String street = row.getCell(13).toString();
                    String city = row.getCell(15).toString();
                    String state = row.getCell(16).toString();
                    String zip = row.getCell(18).toString();
                    String notes = row.getCell(11).toString();

                    // put a new building into the hashmap
                    getBuildings().put(id, new Building(id, buildingName, street, city, state, zip, notes));

                    temp = id;
                } catch (NullPointerException e) {
                    // nothing
                }
            }
        }
    } // end initExterior()
    
    /**
     * Function that traverses the whole sheet and fills values based on values
     */
    public void fillBuildings() {
        
        String section = "";
        String item = "";
        
        for(int i = 1; i < sheet.getLastRowNum()+1; i++) {
            
            // get the row
            row = sheet.getRow(i);
            
            // building for reference
            building = buildings.get(row.getCell(0).toString());
            
            // read in the section and item and do stuff based on that
            section = row.getCell(20).toString();
            item = row.getCell(21).toString();
            
            // <editor-fold desc="large switch to determine what to import" defaultstate="collapsed">
            switch(section) {
                case "Interior Hallway":
                    switch (item) {
                        case "Floor":
                            building.interiorhallway.setFloor_score(Integer.valueOf(getData(R)));
                            building.interiorhallway.setFloor_type(getData(TYPE));
                            break;
                        case "Doors":
                            building.interiorhallway.setDoors_score(Integer.valueOf(getData(R)));
                            building.interiorhallway.setDoors_type(getData(DOOR_TYPE));
                            building.interiorhallway.setDoors_variety(getData(DOOR_VARIETY));
                            building.interiorhallway.setDoors_material(getData(DOOR_MATERIAL));
                            break;
                        case "Door Hardware":
                            building.interiorhallway.setDoor_hardware_score(Integer.valueOf(getData(R)));
                            break;
                        case "Walls/Ceilings":
                            building.interiorhallway.setWallsceilings_score(Integer.valueOf(getData(R)));
                            building.interiorhallway.setWalls_type(getData(WALLS_TYPE));
                            building.interiorhallway.setCeiling_type(getData(CEILING_TYPE));
                            break;
                        case "Light Fixtures":
                            building.interiorhallway.setLightfixture_score(Integer.valueOf(getData(R)));
                            building.interiorhallway.setLightfixture_type(getData(LIGHTING_TYPE));
                            break;
                        case "Handrails":
                            building.interiorhallway.setHandrails_score(Integer.valueOf(getData(R)));
                            building.interiorhallway.setHandrails_type(getData(MATERIAL));
                            break;
                        case "Moldings":
                            building.interiorhallway.setMoldings_score(Integer.valueOf(getData(R)));
                            break;
                        case "Smoke Detectors":
                            building.interiorhallway.setSmoke_detectors_score(Integer.valueOf(getData(R)));
                            break;
                        case "Fire Extinguisher":
                            building.interiorhallway.setFire_extinguisher_score(Integer.valueOf(getData(R)));
                            break;
                        case "Stair Coverings":
                            building.interiorhallway.setStaircoverings_score(Integer.valueOf(getData(R)));
                            building.interiorhallway.setStaircoverings_type(getData(MATERIAL));
                            break;
                        case "Door Knockers":
                            building.interiorhallway.setDoorknockers_score(Integer.valueOf(getData(R)));
                            break;
                        case "Unit Numbers on Doors":
                            building.interiorhallway.setUnitnumbers_score(Integer.valueOf(getData(R)));
                            break;
                        case "Handicap Accessible":
                            building.interiorhallway.setHandicapaccessible(getData(YES_NO));
                            break;
                        default:
                            if(building.interiorhallway.getUnaccounted_items() == null) {
                                building.interiorhallway.setUnaccounted_items(parseRowIntoStringForItem());
                            } else {
                                building.interiorhallway.setUnaccounted_items("\n" + building.interiorhallway.getUnaccounted_items() + parseRowIntoStringForItem());
                            }
                            break;
                    }
                    break;
                case "Dumpsters":
                    switch (item) {
                        case "Fence Pickets":
                            building.dumpsters.setFencepickets_score(Integer.valueOf(getData(R)));
                            building.dumpsters.setFencepickets_material(getData(MATERIAL));
                            break;
                        case "Gates":
                            building.dumpsters.setGates_score(Integer.valueOf(getData(R)));
                            break;
                        case "Gate Holes in Ground":
                            building.dumpsters.setGateholes_score(Integer.valueOf(getData(R)));
                            break;
                        case "Ballasts/Bumpers":
                            building.dumpsters.setBallasts_score(Integer.valueOf(getData(R)));
                            break;
                        default:
                            if(building.dumpsters.getUnaccounted_items() == null) {
                                building.dumpsters.setUnaccounted_items(parseRowIntoStringForItem());
                            } else {
                                building.dumpsters.setUnaccounted_items("\n" + building.dumpsters.getUnaccounted_items() + parseRowIntoStringForItem());
                            }
                            break;
                    }
                    break;
                case "Main Supplies":
                    switch (item) {
                        case "Water Shutoff":
                            building.mainsupplies.setWatershutoff_score(Integer.valueOf(getData(R)));
                            building.mainsupplies.setWatershutoff_location(getData(LOCATION));
                            building.mainsupplies.setWatershutoff_number(getData(NUMBER));
                            break;
                        case "Sump Pumps":
                            building.mainsupplies.setSumppumps_score(Integer.valueOf(getData(R)));
                            building.mainsupplies.setSumppumps_location(getData(LOCATION));
                            building.mainsupplies.setSumppumps_number(getData(NUMBER));
                            break;
                        case "Sewer Cleanout":
                            building.mainsupplies.setSewercleanout_score(Integer.valueOf(getData(R)));
                            building.mainsupplies.setSewercleanout_location(getData(LOCATION));
                            building.mainsupplies.setSewercleanout_number(getData(NUMBER));
                            break;
                        case "Electric Meter":
                            building.mainsupplies.setElectricmeter_score(Integer.valueOf(getData(R)));
                            building.mainsupplies.setElectricmeter_location(getData(LOCATION));
                            building.mainsupplies.setElectricmeter_number(getData(NUMBER));
                            break;
                        case "Gas Meter":
                            building.mainsupplies.setGasmeter_score(Integer.valueOf(getData(R)));
                            building.mainsupplies.setGasmeter_location(getData(LOCATION));
                            building.mainsupplies.setGasmeter_number(getData(NUMBER));
                            break;
                        case "Fire Hydrants":
                            building.mainsupplies.setFirehydrants_score(Integer.valueOf(getData(R)));
                            building.mainsupplies.setFirehydrants_location(getData(LOCATION));
                            building.mainsupplies.setFirehydrants_number(getData(NUMBER));
                            break;
                        case "HVAC Shutoff Locations":
                            building.mainsupplies.setHvacshutoff_score(Integer.valueOf(getData(R)));
                            building.mainsupplies.setHvacshutoff_location(getData(LOCATION));
                            building.mainsupplies.setHvacshutoff_number(getData(NUMBER));
                            break;
                        default:
                            if(building.mainsupplies.getUnaccounted_items() == null) {
                                building.mainsupplies.setUnaccounted_items(parseRowIntoStringForItem());
                            } else {
                                building.mainsupplies.setUnaccounted_items("\n" + building.mainsupplies.getUnaccounted_items() + parseRowIntoStringForItem());
                            }
                            break;
                    }
                    break;
                case "Landscaping":
                    switch (item) {
                        case "Trees":
                            building.landscaping.setTrees_score(Integer.valueOf(getData(R)));
                            break;
                        case "Bushes":
                            building.landscaping.setBushes_score(Integer.valueOf(getData(R)));
                            break;
                        case "Mulch/Gravel":
                            building.landscaping.setMulchgravel_score(Integer.valueOf(getData(R)));
                            break;
                        case "Retaining Walls":
                            building.landscaping.setRetainingwalls_score(Integer.valueOf(getData(R)));
                            building.landscaping.setRetainingwalls_material(getData(MATERIAL));
                            break;
                        case "Irrigation":
                            building.landscaping.setIrrigation_score(Integer.valueOf(getData(R)));
                            break;
                        case "Drainage":
                            building.landscaping.setDrainage_score(Integer.valueOf(getData(R)));
                            break;
                        case "Water Pooling":
                            building.landscaping.setWaterpooling_score(Integer.valueOf(getData(R)));
                            break;
                        case "Weeds":
                            building.landscaping.setWeeds_score(Integer.valueOf(getData(R)));
                            break;
                        case "Picnic/Sitting Area":
                            building.landscaping.setPicnicarea_score(Integer.valueOf(getData(R)));
                            break;
                        case "Lighting":
                            building.landscaping.setLighting_score(Integer.valueOf(getData(R)));
                            break;
                        case "Debris":
                            building.landscaping.setDebris_score(Integer.valueOf(getData(R)));
                            break;
                        case "Grills":
                            building.landscaping.setGrills_score(Integer.valueOf(getData(R)));
                            break;
                        case "Dog Park":
                            building.landscaping.setDogpark_score(Integer.valueOf(getData(R)));
                            break;
                        case "Dog Park Fence":
                            building.landscaping.setDogpark_fence_score(Integer.valueOf(getData(R)));
                            building.landscaping.setDogpark_fence_material(getData(MATERIAL));
                            break;
                        default:
                            if(building.landscaping.getUnaccounted_items() == null) {
                                building.landscaping.setUnaccounted_items(parseRowIntoStringForItem());
                            } else {
                                building.landscaping.setUnaccounted_items("\n" + building.landscaping.getUnaccounted_items() + parseRowIntoStringForItem());
                            }
                            break;
                    }
                    break;
                case "Building Exterior":
                    switch(item) {
                        case "Gutters":
                            building.buildingexterior.setGutters_score(Integer.valueOf(getData(R)));
                            break;
                        case "Down Spouts":
                            building.buildingexterior.setDownspouts_score(Integer.valueOf(getData(R)));
                            break;
                        case "Soffit":
                            building.buildingexterior.setSoffit_score(Integer.valueOf(getData(R)));
                            break;
                        case "Fascia":
                            building.buildingexterior.setFascia_score(Integer.valueOf(getData(R)));
                            break;
                        case "Siding/Stucco":
                            building.buildingexterior.setSiding_score(Integer.valueOf(getData(R)));
                            break;
                        case "Roofs":
                            building.buildingexterior.setRoofs_score(Integer.valueOf(getData(R)));
                            break;
                        case "Vent Pipes & Caging":
                            building.buildingexterior.setVentpipescaging_score(Integer.valueOf(getData(R)));
                            break;
                        case "Gutter Drains on Roofs":
                            building.buildingexterior.setGutterdrains_score(Integer.valueOf(getData(R)));
                            break;
                        case "Debris on Roofs":
                            building.buildingexterior.setDebris_score(Integer.valueOf(getData(R)));
                            break;
                        case "Windows":
                            building.buildingexterior.setWindows_score(Integer.valueOf(getData(R)));
                            building.buildingexterior.setWindows_number(getData(NUMBER));
                            building.buildingexterior.setWindows_type(getData(TYPE));
                            break;
                        case "Window Weather Stripping":
                            building.buildingexterior.setWindowweatherstripping_score(Integer.valueOf(getData(R)));
                            break;
                        case "Foundation Cracks":
                            building.buildingexterior.setFoundationcracks_score(Integer.valueOf(getData(R)));
                            break;
                        case "Foundation Leaks":
                            building.buildingexterior.setFoundationleaks_score(Integer.valueOf(getData(R)));
                            break;
                        case "Porch Posts":
                            building.buildingexterior.setPorchposts_score(Integer.valueOf(getData(R)));
                            building.buildingexterior.setPorchposts_material(getData(MATERIAL));
                            break;
                        case "Spindles":
                            building.buildingexterior.setSpindles_score(Integer.valueOf(getData(R)));
                            break;
                        case "Mailbox":
                            building.buildingexterior.setMailbox_score(Integer.valueOf(getData(R)));
                            break;
                        case "Brickwork":
                            building.buildingexterior.setBrickwork_score(Integer.valueOf(getData(R)));
                            break;
                        case "Termites":
                            building.buildingexterior.setTermites_score(Integer.valueOf(getData(R)));
                            break;
                        case "Doors":
                            building.buildingexterior.setDoors_score(Integer.valueOf(getData(R)));
                            building.buildingexterior.setDoors_type(getData(DOOR_TYPE));
                            building.buildingexterior.setDoors_variety(getData(DOOR_VARIETY));
                            building.buildingexterior.setDoors_material(getData(DOOR_MATERIAL));
                            break;
                        case "Door Hardware":
                            building.buildingexterior.setDoorhardware_score(Integer.valueOf(getData(R)));
                            break;
                        case "Satellites":
                            building.buildingexterior.setSatellites_score(Integer.valueOf(getData(R)));
                            break;
                        case "Exposed Wiring/Cables":
                            building.buildingexterior.setExposedwiring_score(Integer.valueOf(getData(R)));
                            break;
                        case "Decks":
                            building.buildingexterior.setDecks_score(Integer.valueOf(getData(R)));
                            break;
                        case "Deck Flashing":
                            building.buildingexterior.setDeckflashing_score(Integer.valueOf(getData(R)));
                            break;
                        case "Lighting":
                            building.buildingexterior.setLighting_score(Integer.valueOf(getData(R)));
                            break;
                        case "Handicap Accessible":
                            building.buildingexterior.setHandicapaccessible(getData(YES_NO));
                            break;
                        case "Stairs":
                            building.buildingexterior.setStairs_score(Integer.valueOf(getData(R)));
                            building.buildingexterior.setStairs_material(getData(MATERIAL));
                            break;
                        case "Handrails":
                            building.buildingexterior.setHandrails_score(Integer.valueOf(getData(R)));
                            building.buildingexterior.setHandrails_material(getData(MATERIAL));
                            break;
                        case "Exterior Unit Numbers":
                            building.buildingexterior.setExteriorunitnumbers_score(Integer.valueOf(getData(R)));
                            break;
                        case "HVAC/AC":
                            building.buildingexterior.setHvac_score(Integer.valueOf(getData(R)));
                            break;
                        case "Window Wells":
                            building.buildingexterior.setWindowwells_score(Integer.valueOf(getData(R)));
                            break;
                        case "Exterior Access":
                            building.buildingexterior.setExterioraccess_score(Integer.valueOf(getData(R)));
                            break;
                        default:
                            if(building.buildingexterior.getUnaccounted_items() == null) {
                                building.buildingexterior.setUnaccounted_items(parseRowIntoStringForItem());
                            } else {
                                building.buildingexterior.setUnaccounted_items("\n" + building.buildingexterior.getUnaccounted_items() + parseRowIntoStringForItem());
                            }
                            break;
                    }
                    break;
                case "Asphalt/Concrete":
                    switch(item) {
                        case "Overlay":
                            building.asphaltconcrete.setOverlay_score(Integer.valueOf(getData(R)));
                            break;
                        case "Parking Bumpers":
                            building.asphaltconcrete.setParkingbumpers_score(Integer.valueOf(getData(R)));
                            break;
                        case "Striping":
                            building.asphaltconcrete.setStriping_score(Integer.valueOf(getData(R)));
                            break;
                        case "Sealing":
                            building.asphaltconcrete.setSealing_score(Integer.valueOf(getData(R)));
                            break;
                        case "Crack Fillings":
                            building.asphaltconcrete.setCrackfillings_score(Integer.valueOf(getData(R)));
                            break;
                        case "Potholes":
                            building.asphaltconcrete.setPotholes_score(Integer.valueOf(getData(R)));
                            break;
                        case "Sidewalks":
                            building.asphaltconcrete.setSidewalks_score(Integer.valueOf(getData(R)));
                            break;
                        case "Trip Hazards":
                            building.asphaltconcrete.setTriphazards_score(Integer.valueOf(getData(R)));
                            break;
                        case "Handicap Parking":
                            building.asphaltconcrete.setHandicapparking_score(Integer.valueOf(getData(R)));
                            break;
                        default:
                            if(building.asphaltconcrete.getUnaccounted_items() == null) {
                                building.asphaltconcrete.setUnaccounted_items(parseRowIntoStringForItem());
                            } else {
                                building.asphaltconcrete.setUnaccounted_items("\n" + building.asphaltconcrete.getUnaccounted_items() + parseRowIntoStringForItem());
                            }
                            break;
                    }
                    break;
                case "Fencing":
                    switch(item) {
                        case "Fence Pickets":
                            building.fencing.setFencepickets_score(Integer.valueOf(getData(R)));
                            building.fencing.setFencepickets_type(getData(MATERIAL));
                            break;
                        case "Locks/Latches":
                            building.fencing.setLockslatches_score(Integer.valueOf(getData(R)));
                            break;
                        case "Gates":
                            building.fencing.setGates_score(Integer.valueOf(getData(R)));
                            break;
                        default:
                            if(building.fencing.getUnaccounted_items() == null) {
                                building.fencing.setUnaccounted_items(parseRowIntoStringForItem());
                            } else {
                                building.fencing.setUnaccounted_items("\n" + building.fencing.getUnaccounted_items() + parseRowIntoStringForItem());
                            }
                            break;
                    }
                    break;
                case "Signage":
                    switch(item) {
                        case "Entrance Sign":
                            building.signage.setEntrance_score(Integer.valueOf(getData(R)));
                            break;
                        case "Office Signage":
                            building.signage.setOffice_score(Integer.valueOf(getData(R)));
                            break;
                        case "Street Signs":
                            building.signage.setStreet_score(Integer.valueOf(getData(R)));
                            break;
                        case "Parking Signs":
                            building.signage.setParking_score(Integer.valueOf(getData(R)));
                            break;
                        case "Handicap Parking":
                            building.signage.setHandicapparking_score(Integer.valueOf(getData(R)));
                            break;
                        case "Pool Signage":
                            building.signage.setPool_score(Integer.valueOf(getData(R)));
                            break;
                        case "ADA Marking on Pavement":
                            building.signage.setAdamarking_score(Integer.valueOf(getData(R)));
                            break;
                        case "Dog Park Signs":
                            building.signage.setDogpark_score(Integer.valueOf(getData(R)));
                            break;
                        case "Dumpster Signage":
                            building.signage.setDumpster_score(Integer.valueOf(getData(R)));
                            break;
                        case "Fire Lane Signs":
                            building.signage.setFirelane_score(Integer.valueOf(getData(R)));
                            break;
                        default:
                            if(building.signage.getUnaccounted_items() == null) {
                                building.signage.setUnaccounted_items(parseRowIntoStringForItem());
                            } else {
                                building.signage.setUnaccounted_items("\n" + building.signage.getUnaccounted_items() + parseRowIntoStringForItem());
                            }
                            break;
                    }
                    break;
                default:
                    fillExteriorNote(building, section, item, parseRowIntoStringForSection());
            }
            // </editor-fold>
        }
        
        // NOTES
        building = null;
        String note = "";
        
        for(int i = 1; i < sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            String id = row.getCell(0).toString();

            Set<String> keys = getBuildings().keySet();
            for(String _key : keys) {
                if(getBuildings().get(_key).getID().equals(id)) {
                    building = getBuildings().get(_key);
                    section = row.getCell(SECTION).toString();
                    item = row.getCell(ITEM).toString();
                    note = row.getCell(NOTES).toString();
                }
            }
            fillExteriorNote(building, section, item, note);
        }
        
        // PICTURES
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            String id = row.getCell(0).toString();

            Set<String> keys = getBuildings().keySet();
            for (String _key : keys) {
                if (getBuildings().get(_key).getID().equals(id)) {
                    building = getBuildings().get(_key);
                    section = row.getCell(SECTION).toString();
                    item = row.getCell(ITEM).toString();

                    // number of pictures
                    int counter = 0;
                    while (true) {
                        try {
                            String url = row.getCell(PHOTOS + counter).toString();
                            building.addPicture(section, item, url);
                            counter++;
                        } catch (NullPointerException ex) {
                            break;
                        }
                    }
                }
            }
        }
    } // end fillBuildings()
    
    /**
     * Function that reads in a row and formats it into a string
     * @return the formatted string
     */
    public String parseRowIntoStringForSection() {
        
        String str = row.getCell(SECTION).toString() + "/" + row.getCell(ITEM).toString() + ": ";
        for (int i = 22; i < row.getLastCellNum(); i++) {
            String cellData = getData(i);
            if (!cellData.equals("")) {
                XSSFRow tRow = sheet.getRow(0);
                String header = tRow.getCell(i).toString();
                str += " " + header + ": " + cellData + "\n";
            }
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }
    
    /**
     * Function that reads in a row and formats it into a string
     * @return the formatted string
     */
    public String parseRowIntoStringForItem() {
        
        String str = row.getCell(SECTION).toString() + "/" + row.getCell(ITEM).toString() + ": ";
        for (int i = 22; i < row.getLastCellNum(); i++) {
            String cellData = getData(i);
            if (!cellData.equals("")) {
                XSSFRow tRow = sheet.getRow(0);
                String header = tRow.getCell(i).toString();
                str += " " + header + ": " + cellData + ",";
            }
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }

    /**
     * Function that gets the individual note from fillUnits and puts it in its proper place.
     * Basically switches all over the place
     * @param building
     * @param section
     * @param item
     * @param note 
     */
    public void fillExteriorNote(Building building, String section, String item, String note) {
        switch(section) {
            case "Interior Hallway":
                switch (item) {
                    case "Floor":
                        building.interiorhallway.setFloor_notes(note);
                        break;
                    case "Doors":
                        building.interiorhallway.setDoors_notes(note);
                        break;
                    case "Door Hardware":
                        building.interiorhallway.setDoor_hardware_notes(note);
                        break;
                    case "Walls/Ceilings":
                        building.interiorhallway.setWallsceilings_notes(note);
                        break;
                    case "Light Fixtures":
                        building.interiorhallway.setLightfixture_notes(note);
                        break;
                    case "Handrails":
                        building.interiorhallway.setHandrails_notes(note);
                        break;
                    case "Moldings":
                        building.interiorhallway.setMoldings_notes(note);
                        break;
                    case "Smoke Detectors":
                        building.interiorhallway.setSmoke_detectors_notes(note);
                        break;
                    case "Fire Extinguisher":
                        building.interiorhallway.setFire_extinguisher_notes(note);
                        break;
                    case "Stair Coverings":
                        building.interiorhallway.setStaircoverings_notes(note);
                        break;
                    case "Door Knockers":
                        building.interiorhallway.setDoorknockers_notes(note);
                        break;
                    case "Unit Numbers on Doors":
                        building.interiorhallway.setUnitnumbers_notes(note);
                        break;
                    case "Handicap Accessible":
                        building.interiorhallway.setHandicapaccessible_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Dumpsters":
                switch (item) {
                    case "Fence Pickets":
                        building.dumpsters.setFencepickets_notes(note);
                        break;
                    case "Gates":
                        building.dumpsters.setGates_notes(note);
                        break;
                    case "Gate Holes in Ground":
                        building.dumpsters.setGateholes_notes(note);
                        break;
                    case "Ballasts/Bumpers":
                        building.dumpsters.setBallasts_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Main Supplies":
                switch (item) {
                    case "Water Shutoff":
                        building.mainsupplies.setWatershutoff_notes(note);
                        break;
                    case "Sump Pumps":
                        building.mainsupplies.setSumppumps_notes(note);
                        break;
                    case "Sewer Cleanout":
                        building.mainsupplies.setSewercleanout_notes(note);
                        break;
                    case "Electric Meter":
                        building.mainsupplies.setElectricmeter_notes(note);
                        break;
                    case "Gas Meter":
                        building.mainsupplies.setGasmeter_notes(note);
                        break;
                    case "Fire Hydrants":
                        building.mainsupplies.setFirehydrants_notes(note);
                        break;
                    case "HVAC Shutoff Locations":
                        building.mainsupplies.setHvacshutoff_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Landscaping":
                switch (item) {
                    case "Trees":
                        building.landscaping.setTrees_notes(note);
                        break;
                    case "Bushes":
                        building.landscaping.setBushes_notes(note);
                        break;
                    case "Mulch/Gravel":
                        building.landscaping.setMulchgravel_notes(note);
                        break;
                    case "Retaining Walls":
                        building.landscaping.setRetainingwalls_notes(note);
                        break;
                    case "Irrigation":
                        building.landscaping.setIrrigation_notes(note);
                        break;
                    case "Drainage":
                        building.landscaping.setDrainage_notes(note);
                        break;
                    case "Water Pooling":
                        building.landscaping.setWaterpooling_notes(note);
                        break;
                    case "Weeds":
                        building.landscaping.setWeeds_notes(note);
                        break;
                    case "Picnic/Sitting Area":
                        building.landscaping.setPicnicarea_notes(note);
                        break;
                    case "Lighting":
                        building.landscaping.setLighting_notes(note);
                        break;
                    case "Debris":
                        building.landscaping.setDebris_notes(note);
                        break;
                    case "Grills":
                        building.landscaping.setGrills_notes(note);
                        break;
                    case "Dog Park":
                        building.landscaping.setDogpark_notes(note);
                        break;
                    case "Dog Park Fence":
                        building.landscaping.setDogpark_fence_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Building Exterior":
                switch(item) {
                    case "Gutters":
                        building.buildingexterior.setGutters_notes(note);
                        break;
                    case "Down Spouts":
                        building.buildingexterior.setDownspouts_notes(note);
                        break;
                    case "Soffit":
                        building.buildingexterior.setSoffit_notes(note);
                        break;
                    case "Fascia":
                        building.buildingexterior.setFascia_notes(note);
                        break;
                    case "Siding/Stucco":
                        building.buildingexterior.setSiding_notes(note);
                        break;
                    case "Roofs":
                        building.buildingexterior.setRoofs_notes(note);
                        break;
                    case "Vent Pipes & Caging":
                        building.buildingexterior.setVentpipescaging_notes(note);
                        break;
                    case "Gutter Drains on Roofs":
                        building.buildingexterior.setGutterdrains_notes(note);
                        break;
                    case "Debris on Roofs":
                        building.buildingexterior.setDebris_notes(note);
                        break;
                    case "Windows":
                        building.buildingexterior.setWindows_notes(note);
                        break;
                    case "Window Weather Stripping":
                        building.buildingexterior.setWindowweatherstripping_notes(note);
                        break;
                    case "Foundation Cracks":
                        building.buildingexterior.setFoundationcracks_notes(note);
                        break;
                    case "Foundation Leaks":
                        building.buildingexterior.setFoundationleaks_notes(note);
                        break;
                    case "Porch Posts":
                        building.buildingexterior.setPorchposts_notes(note);
                        break;
                    case "Spindles":
                        building.buildingexterior.setSpindles_notes(note);
                        break;
                    case "Mailbox":
                        building.buildingexterior.setMailbox_notes(note);
                        break;
                    case "Brickwork":
                        building.buildingexterior.setBrickwork_notes(note);
                        break;
                    case "Termites":
                        building.buildingexterior.setTermites_notes(note);
                        break;
                    case "Doors":
                        building.buildingexterior.setDoors_notes(note);
                        break;
                    case "Door Hardware":
                        building.buildingexterior.setDoorhardware_notes(note);
                        break;
                    case "Satellites":
                        building.buildingexterior.setSatellites_notes(note);
                        break;
                    case "Exposed Wiring/Cables":
                        building.buildingexterior.setExposedwiring_notes(note);
                        break;
                    case "Decks":
                        building.buildingexterior.setDecks_notes(note);
                        break;
                    case "Deck Flashing":
                        building.buildingexterior.setDeckflashing_notes(note);
                        break;
                    case "Lighting":
                        building.buildingexterior.setLighting_notes(note);
                        break;
                    case "Handicap Accessible":
                        building.buildingexterior.setHandicapaccessible_notes(note);
                        break;
                    case "Stairs":
                        building.buildingexterior.setStairs_notes(note);
                        break;
                    case "Handrails":
                        building.buildingexterior.setHandrails_notes(note);
                        break;
                    case "Exterior Unit Numbers":
                        building.buildingexterior.setExteriorunitnumbers_notes(note);
                        break;
                    case "HVAC/AC":
                        building.buildingexterior.setHvac_notes(note);
                        break;
                    case "Window Wells":
                        building.buildingexterior.setWindowwells_notes(note);
                        break;
                    case "Exterior Access":
                        building.buildingexterior.setExterioraccess_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Asphalt/Concrete":
                switch(item) {
                    case "Overlay":
                        building.asphaltconcrete.setOverlay_notes(note);
                        break;
                    case "Parking Bumpers":
                        building.asphaltconcrete.setParkingbumpers_notes(note);
                        break;
                    case "Striping":
                        building.asphaltconcrete.setStriping_notes(note);
                        break;
                    case "Sealing":
                        building.asphaltconcrete.setSealing_notes(note);
                        break;
                    case "Crack Fillings":
                        building.asphaltconcrete.setCrackfillings_notes(note);
                        break;
                    case "Potholes":
                        building.asphaltconcrete.setPotholes_notes(note);
                        break;
                    case "Sidewalks":
                        building.asphaltconcrete.setSidewalks_notes(note);
                        break;
                    case "Trip Hazards":
                        building.asphaltconcrete.setTriphazards_notes(note);
                        break;
                    case "Handicap Parking":
                        building.asphaltconcrete.setHandicapparking_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Fencing":
                switch(item) {
                    case "Fence Pickets":
                        building.fencing.setFencepickets_notes(note);
                        break;
                    case "Locks/Latches":
                        building.fencing.setLockslatches_notes(note);
                        break;
                    case "Gates":
                        building.fencing.setGates_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Signage":
                switch(item) {
                    case "Entrance Sign":
                        building.signage.setEntrance_notes(note);
                        break;
                    case "Office Signage":
                        building.signage.setOffice_notes(note);
                        break;
                    case "Street Signs":
                        building.signage.setStreet_notes(note);
                        break;
                    case "Parking Signs":
                        building.signage.setParking_notes(note);
                        break;
                    case "Handicap Parking":
                        building.signage.setHandicapparking_notes(note);
                        break;
                    case "Pool Signage":
                        building.signage.setPool_notes(note);
                        break;
                    case "ADA Marking on Pavement":
                        building.signage.setAdamarking_notes(note);
                        break;
                    case "Dog Park Signs":
                        building.signage.setDogpark_notes(note);
                        break;
                    case "Dumpster Signage":
                        building.signage.setDumpster_notes(note);
                        break;
                    case "Fire Lane Signs":
                        building.signage.setFirelane_notes(note);
                        break;
                    default:
                        break;
                }
                break;
            case "Footer":
                building.overall.setGeneral_comments(note);
                break;
            default:
                //System.err.println("Unaccounted section/item in fillExteriorNote():  " + section + "/" + item);
                //break;
                
                if(building.overall.getUnaccounted_sections()== null) {
                    building.overall.setUnaccounted_sections(note);
                } else {
                    building.overall.setUnaccounted_sections(building.overall.getUnaccounted_sections() + note);
                }
                break;
        }
    }
    
    /**
     * Function to return data from cell
     * Tests for a Null value to prevent NPE
     * @param col, the column of the row to return
     * @return the value of the cell
     */
    public String getData(int col) {
        if(row.getCell(col) == null) {
            return "";
        } else {
            if(row.getCell(col).toString().endsWith(".0")) {
                if(col == NUMBER) {
                    return row.getCell(col).toString().replace(".0", "");
                }
                return row.getCell(col).toString().substring(0, 1);
            } else {
                return row.getCell(col).toString();
            }
        }
    } // end getData()
} // end ExtInput.java
