/**
 * File:    Output.java
 * Author:  Pat Ripley
 *
 * Program Description:
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 *
 * Class Description:
 * Outputs the results of the parsing into a formatted excel document
 *
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// imports
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import parser.Unit.Picture;

// class IntOutput
public class IntOutput {

    // ivars
    private Map<String, Unit> units;

    // declarations for the excel file reading
    File file = new File("templates//consolidated_interior_template.xlsx");
    InputStream fs;
    XSSFWorkbook wb;
    XSSFSheet scores_sheet;
    XSSFSheet details_sheet;
    XSSFSheet notes_sheet;
    XSSFSheet photos_sheet;
    XSSFRow row;
    XSSFCell cell;
    
    File fileCE = new File("templates//consolidated_interior_capex_template.xlsx");
    InputStream fs_ce;
    XSSFWorkbook wb_ce;
    XSSFSheet scores_sheet_ce;
    XSSFRow row_ce;
    XSSFCell cell_ce;
    
    // outputs
    String folderPath;
    String consolidatedOutputPath;
    String capexOutputPath;
    String imagesPath;

    // globals
    boolean notePut = false;
    boolean photoPut = false;
    int count = 1;
    int r;

    /**
     * Default constructor
     * @param folderPath, the path to send the files to
     */
    public IntOutput(String folderPath) {
        this.folderPath = folderPath + "\\Interior\\";
        this.consolidatedOutputPath = this.folderPath + "Consolidated Interior.xlsx";
        this.capexOutputPath = this.folderPath + "Interior CapEx Report.xlsx";
        this.imagesPath = this.folderPath + "\\Images";
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
     * @return the units
     */
    public Map<String, Unit> getUnits() {
        return units;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(Map<String, Unit> units) {
        this.units = units;
    }
    
    /**
     * @return the Consolidated Interior spreadsheet
     */
    public File getConsolidatedFile() {
        return new File(consolidatedOutputPath);
    }
    
    /**
     * @return the Interior CapEx spreadsheet
     */
    public File getCapExFile() {
        return new File(capexOutputPath);
    }

    /**
     * Function that controls the output of all the data
     */
    public void outputData() {

        // make the sheets and get the workbook
        try {
            fs = new FileInputStream(file);
            wb = new XSSFWorkbook(fs);
            scores_sheet = wb.getSheet("Scores");
            details_sheet = wb.getSheet("Details");
            notes_sheet = wb.getSheet("Notes");
            photos_sheet = wb.getSheet("Photos");
            
        } catch (IOException e) {
            // something went wrong
            System.err.println(e);
        }
        
        // secondary functions to do the individual tasks
        outputScores();
        outputDetails();
        outputNotes();
        outputPhotos();
        
        // close the sheet
        try {
            fs.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
        
        // create the new directory
        File output = new File(folderPath);
        output.mkdirs();
        
        // write all of this to the new file
        try (FileOutputStream outFile = new FileOutputStream(consolidatedOutputPath)) {
            wb.write(outFile);
            
        // thing(s) went wrong    
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    } // end outputData()
    
    /**
     * Function that controls the output of all the capex data
     */
    public void outputCapEx() {

        // try with all your might
        try {

            // make the sheets and get the workbook
            try {
                fs_ce = new FileInputStream(fileCE);
                wb_ce = new XSSFWorkbook(fs_ce);
                scores_sheet_ce = wb_ce.getSheet("Scores");

            } catch (IOException e) {
                // something went wrong
                System.err.println(e);
            }

            // output the scores
            outputScoresForCapEx();

            // close the sheet
            try {
                fs_ce.close();
            } catch (IOException ex) {
                System.err.println(ex);
            }
            
            // create the new directory
            File output = new File(folderPath);
            output.mkdirs();

            // write all of this to the new file
            try (FileOutputStream outFile = new FileOutputStream(capexOutputPath)) {
                wb_ce.write(outFile);
            }

        // thing(s) went wrong
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    } // end outputCapEx()
    
    /**
     * Outputs the scores to the Scores worksheet
     */
    public void outputScores() {

        // update global starting point
        r = 7;

        // create a set of keys and parse through the units
        Set<String> keys = units.keySet();
        for (String key : keys) {
            row = scores_sheet.getRow(r);

            // put unit number in the side-header
            XSSFCreationHelper helper = wb.getCreationHelper();
            XSSFHyperlink url = helper.createHyperlink(HyperlinkType.URL);
            url.setAddress("https://manage.happyco.com/folder/41917/inspections/" + units.get(key).getID());
            row.getCell(1).setCellValue(units.get(key).getUnitNum());
            row.getCell(1).setHyperlink(url);

            // This huge block of code basically uses getNum to put the scores
            // into the specified spots in the sheet
            // <editor-fold desc="huge block of code" defaultstate="collapsed">
            
            // entry
            row.getCell(2).setCellValue(units.get(key).entry.getDoor_score());
            row.getCell(3).setCellValue(units.get(key).entry.getFloor_score());
            row.getCell(4).setCellValue(units.get(key).entry.getStairs_score());
            row.getCell(5).setCellValue(units.get(key).entry.getWallsceiling_score());
            row.getCell(6).setCellValue(units.get(key).entry.getCloset_score());
            row.getCell(7).setCellValue(units.get(key).entry.getWindows_score());
            row.getCell(8).setCellValue(units.get(key).entry.getBlindsdrapes_score());
            row.getCell(9).setCellValue(units.get(key).entry.getLight_score());

            // living room
            row.getCell(10).setCellValue(units.get(key).livingroom.getFloor_score());
            row.getCell(11).setCellValue(units.get(key).livingroom.getWallsceiling_score());
            row.getCell(12).setCellValue(units.get(key).livingroom.getDoor_score());
            row.getCell(13).setCellValue(units.get(key).livingroom.getWindows_score());
            row.getCell(14).setCellValue(units.get(key).livingroom.getBlindsdrapes_score());
            row.getCell(15).setCellValue(units.get(key).livingroom.getLight_score());
            row.getCell(16).setCellValue(units.get(key).livingroom.getPatio_score());
            row.getCell(17).setCellValue(units.get(key).livingroom.getFireplace_score());

            // dining room
            row.getCell(18).setCellValue(units.get(key).diningroom.getFloor_score());
            row.getCell(19).setCellValue(units.get(key).diningroom.getWallsceiling_score());
            row.getCell(20).setCellValue(units.get(key).diningroom.getDoor_score());
            row.getCell(21).setCellValue(units.get(key).diningroom.getWindows_score());
            row.getCell(22).setCellValue(units.get(key).diningroom.getBlindsdrapes_score());
            row.getCell(23).setCellValue(units.get(key).diningroom.getLight_score());

            // kitchen
            row.getCell(24).setCellValue(units.get(key).kitchen.getFloor_score());
            row.getCell(25).setCellValue(units.get(key).kitchen.getWallsceiling_score());
            row.getCell(26).setCellValue(units.get(key).kitchen.getWindows_score());
            row.getCell(27).setCellValue(units.get(key).kitchen.getBlindsdrapes_score());
            row.getCell(28).setCellValue(units.get(key).kitchen.getDoor_score());
            row.getCell(29).setCellValue(units.get(key).kitchen.getPantry_score());
            row.getCell(30).setCellValue(units.get(key).kitchen.getLight_score());
            row.getCell(31).setCellValue(units.get(key).kitchen.getSink_score());
            row.getCell(32).setCellValue(units.get(key).kitchen.getCabinets_score());
            row.getCell(33).setCellValue(units.get(key).kitchen.getCountertops_score());
            row.getCell(34).setCellValue(units.get(key).kitchen.getPassbar_score());

            // mechanical
            row.getCell(35).setCellValue(units.get(key).mechanical.getRange_score());
            row.getCell(36).setCellValue(units.get(key).mechanical.getFridge_score());
            row.getCell(37).setCellValue(units.get(key).mechanical.getDishwasher_score());
            row.getCell(38).setCellValue(units.get(key).mechanical.getHood_score());
            row.getCell(39).setCellValue(units.get(key).mechanical.getHvac_score());
            row.getCell(40).setCellValue(units.get(key).mechanical.getWater_heater_score());
            row.getCell(41).setCellValue(units.get(key).mechanical.getWasher_dryer_score());
            row.getCell(42).setCellValue(units.get(key).mechanical.getDisposal_score());

            // utility
            row.getCell(43).setCellValue(units.get(key).utility.getDoor_score());
            row.getCell(44).setCellValue(units.get(key).utility.getFloor_score());
            row.getCell(45).setCellValue(units.get(key).utility.getWallsceiling_score());
            row.getCell(46).setCellValue(units.get(key).utility.getLight_score());

            // bedroom 1
            row.getCell(47).setCellValue(units.get(key).bedroom1.getFloor_score());
            row.getCell(48).setCellValue(units.get(key).bedroom1.getWallsceiling_score());
            row.getCell(49).setCellValue(units.get(key).bedroom1.getDoor_score());
            row.getCell(50).setCellValue(units.get(key).bedroom1.getCloset_score());
            row.getCell(51).setCellValue(units.get(key).bedroom1.getWindows_score());
            row.getCell(52).setCellValue(units.get(key).bedroom1.getBlindsdrapes_score());
            row.getCell(53).setCellValue(units.get(key).bedroom1.getLight_score());

            // bathroom 1
            row.getCell(68).setCellValue(units.get(key).bathroom1.getFloor_score());
            row.getCell(69).setCellValue(units.get(key).bathroom1.getWallsceiling_score());
            row.getCell(70).setCellValue(units.get(key).bathroom1.getDoor_score());
            row.getCell(71).setCellValue(units.get(key).bathroom1.getLight_score());
            row.getCell(72).setCellValue(units.get(key).bathroom1.getCountertop_score());
            row.getCell(73).setCellValue(units.get(key).bathroom1.getTubshower_score());
            row.getCell(74).setCellValue(units.get(key).bathroom1.getCommode_score());
            row.getCell(75).setCellValue(units.get(key).bathroom1.getMedicinecabinet_score());
            row.getCell(76).setCellValue(units.get(key).bathroom1.getMirror_score());

            // bedroom 2 (try to add it, if it doesn't exist, fill it with blank slots)
            try {
                row.getCell(54).setCellValue(units.get(key).bedroom2.getFloor_score());
                row.getCell(55).setCellValue(units.get(key).bedroom2.getWallsceiling_score());
                row.getCell(56).setCellValue(units.get(key).bedroom2.getDoor_score());
                row.getCell(57).setCellValue(units.get(key).bedroom2.getCloset_score());
                row.getCell(58).setCellValue(units.get(key).bedroom2.getWindows_score());
                row.getCell(59).setCellValue(units.get(key).bedroom2.getBlindsdrapes_score());
                row.getCell(60).setCellValue(units.get(key).bedroom2.getLight_score());
            } catch (NullPointerException e) {
                row.getCell(54).setCellValue("");
                row.getCell(55).setCellValue("");
                row.getCell(56).setCellValue("");
                row.getCell(57).setCellValue("");
                row.getCell(58).setCellValue("");
                row.getCell(59).setCellValue("");
                row.getCell(60).setCellValue("");
            }

            // bedroom 3 (try to add it, if it doesn't exist, fill it with blank slots)
            try {
                row.getCell(61).setCellValue(units.get(key).bedroom3.getFloor_score());
                row.getCell(62).setCellValue(units.get(key).bedroom3.getWallsceiling_score());
                row.getCell(63).setCellValue(units.get(key).bedroom3.getDoor_score());
                row.getCell(64).setCellValue(units.get(key).bedroom3.getCloset_score());
                row.getCell(65).setCellValue(units.get(key).bedroom3.getWindows_score());
                row.getCell(66).setCellValue(units.get(key).bedroom3.getBlindsdrapes_score());
                row.getCell(67).setCellValue(units.get(key).bedroom3.getLight_score());
            } catch (NullPointerException e) {
                row.getCell(61).setCellValue("");
                row.getCell(62).setCellValue("");
                row.getCell(63).setCellValue("");
                row.getCell(64).setCellValue("");
                row.getCell(65).setCellValue("");
                row.getCell(66).setCellValue("");
                row.getCell(67).setCellValue("");
            }

            // bathroom 2 (try to add it, if it doesn't exist, fill it with blank slots)
            try {
                row.getCell(77).setCellValue(units.get(key).bathroom2.getFloor_score());
                row.getCell(78).setCellValue(units.get(key).bathroom2.getWallsceiling_score());
                row.getCell(79).setCellValue(units.get(key).bathroom2.getDoor_score());
                row.getCell(80).setCellValue(units.get(key).bathroom2.getLight_score());
                row.getCell(81).setCellValue(units.get(key).bathroom2.getCountertop_score());
                row.getCell(82).setCellValue(units.get(key).bathroom2.getTubshower_score());
                row.getCell(83).setCellValue(units.get(key).bathroom2.getCommode_score());
                row.getCell(84).setCellValue(units.get(key).bathroom2.getMedicinecabinet_score());
                row.getCell(85).setCellValue(units.get(key).bathroom2.getMirror_score());
            } catch (NullPointerException e) {
                row.getCell(77).setCellValue("");
                row.getCell(78).setCellValue("");
                row.getCell(79).setCellValue("");
                row.getCell(80).setCellValue("");
                row.getCell(81).setCellValue("");
                row.getCell(82).setCellValue("");
                row.getCell(83).setCellValue("");
                row.getCell(84).setCellValue("");
                row.getCell(85).setCellValue("");
            }

            // bathroom 3 (try to add it, if it doesn't exist, fill it with blank slots)
            try {
                row.getCell(86).setCellValue(units.get(key).bathroom3.getFloor_score());
                row.getCell(87).setCellValue(units.get(key).bathroom3.getWallsceiling_score());
                row.getCell(88).setCellValue(units.get(key).bathroom3.getDoor_score());
                row.getCell(89).setCellValue(units.get(key).bathroom3.getLight_score());
                row.getCell(90).setCellValue(units.get(key).bathroom3.getCountertop_score());
                row.getCell(91).setCellValue(units.get(key).bathroom3.getTubshower_score());
                row.getCell(92).setCellValue(units.get(key).bathroom3.getCommode_score());
                row.getCell(93).setCellValue(units.get(key).bathroom3.getMedicinecabinet_score());
                row.getCell(94).setCellValue(units.get(key).bathroom3.getMirror_score());
            } catch (NullPointerException e) {
                row.getCell(86).setCellValue("");
                row.getCell(87).setCellValue("");
                row.getCell(88).setCellValue("");
                row.getCell(89).setCellValue("");
                row.getCell(90).setCellValue("");
                row.getCell(91).setCellValue("");
                row.getCell(92).setCellValue("");
                row.getCell(93).setCellValue("");
                row.getCell(94).setCellValue("");
            }

            // occupied
            if(units.get(key).overall.getOccupancy() != null) {
                if (units.get(key).overall.getOccupancy().equals("Occupied")) {
                    row.getCell(95).setCellValue("X");
                } else {
                    row.getCell(95).setCellValue("");
                }
            

                // vacant
                if (units.get(key).overall.getOccupancy().equals("Vacant")) {
                    row.getCell(96).setCellValue("X");
                } else {
                    row.getCell(96).setCellValue("");
                }
            }

            // cleanliness
            row.getCell(97).setCellValue(units.get(key).overall.getCleanlinesss_score());

            // furnishings
            row.getCell(98).setCellValue(units.get(key).overall.getFurnishings_score());

            // dog
            if (units.get(key).overall.isPets_dog()) {
                row.getCell(99).setCellValue("X");
            } else {
                row.getCell(99).setCellValue("");
            }

            // cat
            if (units.get(key).overall.isPets_cat()) {
                row.getCell(100).setCellValue("X");
            } else {
                row.getCell(100).setCellValue("");
            }

            // other
            if (units.get(key).overall.isPets_other()) {
                row.getCell(101).setCellValue("X");
            } else {
                row.getCell(101).setCellValue("");
            }

            // number of pets
            row.getCell(102).setCellValue(units.get(key).overall.getPets_number());

            // smoker
            if (units.get(key).overall.isSmoker()) {
                row.getCell(103).setCellValue("X");
            } else {
                row.getCell(103).setCellValue("");
            }

            // hoarder
            if (units.get(key).overall.isHoarder()) {
                row.getCell(104).setCellValue("X");
            } else {
                row.getCell(104).setCellValue("");
            }

            // smoke detectors
            if (units.get(key).overall.isHasSmokeDetectors()) {
                row.getCell(105).setCellValue("X");
            } else {
                row.getCell(105).setCellValue("");
            }

            // letter grade
            row.getCell(106).setCellValue(units.get(key).overall.getLetter_grade());

            // </editor-fold>
            
            // update global
            r++;
        }
    } // end outputScores()
    
    /**
     * Outputs the scores to the Scores worksheet
     */
    public void outputScoresForCapEx() {

        // update global starting point
        r = 7;

        // create a set of keys and parse through the units
        Set<String> keys = units.keySet();
        for (String key : keys) {
            row_ce = scores_sheet_ce.getRow(r);

            // put unit number in the side-header
            XSSFCreationHelper helper = wb_ce.getCreationHelper();
            XSSFHyperlink url = helper.createHyperlink(HyperlinkType.URL);
            url.setAddress("https://manage.happyco.com/folder/41917/inspections/" + units.get(key).getID());
            row_ce.getCell(1).setCellValue(units.get(key).getUnitNum());
            row_ce.getCell(1).setHyperlink(url);

            // This huge block of code basically uses getNum to put the scores
            // into the specified spots in the sheet
            
            // <editor-fold desc="huge block of code" defaultstate="collapsed">
            
            // entry
            row_ce.getCell(2).setCellValue(units.get(key).entry.getDoor_score());
            row_ce.getCell(3).setCellValue(units.get(key).entry.getFloor_score());
            row_ce.getCell(4).setCellValue(units.get(key).entry.getStairs_score());
            row_ce.getCell(5).setCellValue(units.get(key).entry.getWallsceiling_score());
            row_ce.getCell(6).setCellValue(units.get(key).entry.getCloset_score());
            row_ce.getCell(7).setCellValue(units.get(key).entry.getWindows_score());
            row_ce.getCell(8).setCellValue(units.get(key).entry.getWindows_number());
            row_ce.getCell(9).setCellValue(units.get(key).entry.getBlindsdrapes_score());
            row_ce.getCell(10).setCellValue(units.get(key).entry.getLight_score());

            // living room
            row_ce.getCell(11).setCellValue(units.get(key).livingroom.getFloor_score());
            row_ce.getCell(12).setCellValue(units.get(key).livingroom.getWallsceiling_score());
            row_ce.getCell(13).setCellValue(units.get(key).livingroom.getDoor_score());
            row_ce.getCell(14).setCellValue(units.get(key).livingroom.getWindows_score());
            row_ce.getCell(15).setCellValue(units.get(key).livingroom.getWindows_number());
            row_ce.getCell(16).setCellValue(units.get(key).livingroom.getBlindsdrapes_score());
            row_ce.getCell(17).setCellValue(units.get(key).livingroom.getLight_score());
            row_ce.getCell(18).setCellValue(units.get(key).livingroom.getPatio_score());
            row_ce.getCell(19).setCellValue(units.get(key).livingroom.getFireplace_score());

            // dining room
            row_ce.getCell(20).setCellValue(units.get(key).diningroom.getFloor_score());
            row_ce.getCell(21).setCellValue(units.get(key).diningroom.getWallsceiling_score());
            row_ce.getCell(22).setCellValue(units.get(key).diningroom.getDoor_score());
            row_ce.getCell(23).setCellValue(units.get(key).diningroom.getWindows_score());
            row_ce.getCell(24).setCellValue(units.get(key).diningroom.getWindows_number());
            row_ce.getCell(25).setCellValue(units.get(key).diningroom.getBlindsdrapes_score());
            row_ce.getCell(26).setCellValue(units.get(key).diningroom.getLight_score());

            // kitchen
            row_ce.getCell(27).setCellValue(units.get(key).kitchen.getFloor_score());
            row_ce.getCell(28).setCellValue(units.get(key).kitchen.getWallsceiling_score());
            row_ce.getCell(29).setCellValue(units.get(key).kitchen.getWindows_score());
            row_ce.getCell(30).setCellValue(units.get(key).kitchen.getWindows_number());
            row_ce.getCell(31).setCellValue(units.get(key).kitchen.getBlindsdrapes_score());
            row_ce.getCell(32).setCellValue(units.get(key).kitchen.getDoor_score());
            row_ce.getCell(33).setCellValue(units.get(key).kitchen.getPantry_score());
            row_ce.getCell(34).setCellValue(units.get(key).kitchen.getLight_score());
            row_ce.getCell(35).setCellValue(units.get(key).kitchen.getSink_score());
            row_ce.getCell(36).setCellValue(units.get(key).kitchen.getCabinets_score());
            row_ce.getCell(37).setCellValue(units.get(key).kitchen.getCountertops_score());
            row_ce.getCell(38).setCellValue(units.get(key).kitchen.getPassbar_score());

            // mechanical
            row_ce.getCell(39).setCellValue(units.get(key).mechanical.getRange_score());
            row_ce.getCell(40).setCellValue(units.get(key).mechanical.getFridge_score());
            row_ce.getCell(41).setCellValue(units.get(key).mechanical.getDishwasher_score());
            row_ce.getCell(42).setCellValue(units.get(key).mechanical.getHood_score());
            row_ce.getCell(43).setCellValue(units.get(key).mechanical.getHvac_score());
            row_ce.getCell(44).setCellValue(units.get(key).mechanical.getWater_heater_score());
            row_ce.getCell(45).setCellValue(units.get(key).mechanical.getWasher_dryer_score());
            row_ce.getCell(46).setCellValue(units.get(key).mechanical.getDisposal_score());

            // utility
            row_ce.getCell(47).setCellValue(units.get(key).utility.getDoor_score());
            row_ce.getCell(48).setCellValue(units.get(key).utility.getFloor_score());
            row_ce.getCell(49).setCellValue(units.get(key).utility.getWallsceiling_score());
            row_ce.getCell(50).setCellValue(units.get(key).utility.getLight_score());

            // bedroom 1
            row_ce.getCell(51).setCellValue(units.get(key).bedroom1.getFloor_score());
            row_ce.getCell(52).setCellValue(units.get(key).bedroom1.getWallsceiling_score());
            row_ce.getCell(53).setCellValue(units.get(key).bedroom1.getDoor_score());
            row_ce.getCell(54).setCellValue(units.get(key).bedroom1.getCloset_score());
            row_ce.getCell(55).setCellValue(units.get(key).bedroom1.getWindows_score());
            row_ce.getCell(56).setCellValue(units.get(key).bedroom1.getWindows_number());
            row_ce.getCell(57).setCellValue(units.get(key).bedroom1.getBlindsdrapes_score());
            row_ce.getCell(58).setCellValue(units.get(key).bedroom1.getLight_score());

            // bathroom 1
            row_ce.getCell(75).setCellValue(units.get(key).bathroom1.getFloor_score());
            row_ce.getCell(76).setCellValue(units.get(key).bathroom1.getWallsceiling_score());
            row_ce.getCell(77).setCellValue(units.get(key).bathroom1.getDoor_score());
            row_ce.getCell(78).setCellValue(units.get(key).bathroom1.getLight_score());
            row_ce.getCell(79).setCellValue(units.get(key).bathroom1.getCountertop_score());
            row_ce.getCell(80).setCellValue(units.get(key).bathroom1.getTubshower_score());
            row_ce.getCell(81).setCellValue(units.get(key).bathroom1.getCommode_score());
            row_ce.getCell(82).setCellValue(units.get(key).bathroom1.getMedicinecabinet_score());
            row_ce.getCell(83).setCellValue(units.get(key).bathroom1.getMirror_score());

            // bedroom 2 (try to add it, if it doesn't exist, fill it with blank slots)
            try {
                row_ce.getCell(59).setCellValue(units.get(key).bedroom2.getFloor_score());
                row_ce.getCell(60).setCellValue(units.get(key).bedroom2.getWallsceiling_score());
                row_ce.getCell(61).setCellValue(units.get(key).bedroom2.getDoor_score());
                row_ce.getCell(62).setCellValue(units.get(key).bedroom2.getCloset_score());
                row_ce.getCell(63).setCellValue(units.get(key).bedroom2.getWindows_score());
                row_ce.getCell(64).setCellValue(units.get(key).bedroom2.getWindows_number());
                row_ce.getCell(65).setCellValue(units.get(key).bedroom2.getBlindsdrapes_score());
                row_ce.getCell(66).setCellValue(units.get(key).bedroom2.getLight_score());
            } catch (NullPointerException e) {
                row_ce.getCell(59).setCellValue("");
                row_ce.getCell(60).setCellValue("");
                row_ce.getCell(61).setCellValue("");
                row_ce.getCell(62).setCellValue("");
                row_ce.getCell(63).setCellValue("");
                row_ce.getCell(64).setCellValue("");
                row_ce.getCell(65).setCellValue("");
                row_ce.getCell(66).setCellValue("");
            }

            // bedroom 3 (try to add it, if it doesn't exist, fill it with blank slots)
            try {
                row_ce.getCell(67).setCellValue(units.get(key).bedroom3.getFloor_score());
                row_ce.getCell(68).setCellValue(units.get(key).bedroom3.getWallsceiling_score());
                row_ce.getCell(69).setCellValue(units.get(key).bedroom3.getDoor_score());
                row_ce.getCell(70).setCellValue(units.get(key).bedroom3.getCloset_score());
                row_ce.getCell(71).setCellValue(units.get(key).bedroom3.getWindows_score());
                row_ce.getCell(72).setCellValue(units.get(key).bedroom3.getWindows_score());
                row_ce.getCell(73).setCellValue(units.get(key).bedroom3.getBlindsdrapes_score());
                row_ce.getCell(74).setCellValue(units.get(key).bedroom3.getLight_score());
            } catch (NullPointerException e) {
                row_ce.getCell(67).setCellValue("");
                row_ce.getCell(68).setCellValue("");
                row_ce.getCell(69).setCellValue("");
                row_ce.getCell(70).setCellValue("");
                row_ce.getCell(71).setCellValue("");
                row_ce.getCell(72).setCellValue("");
                row_ce.getCell(73).setCellValue("");
                row_ce.getCell(73).setCellValue("");
            }

            // bathroom 2 (try to add it, if it doesn't exist, fill it with blank slots)
            try {
                row_ce.getCell(84).setCellValue(units.get(key).bathroom2.getFloor_score());
                row_ce.getCell(85).setCellValue(units.get(key).bathroom2.getWallsceiling_score());
                row_ce.getCell(86).setCellValue(units.get(key).bathroom2.getDoor_score());
                row_ce.getCell(87).setCellValue(units.get(key).bathroom2.getLight_score());
                row_ce.getCell(88).setCellValue(units.get(key).bathroom2.getCountertop_score());
                row_ce.getCell(89).setCellValue(units.get(key).bathroom2.getTubshower_score());
                row_ce.getCell(90).setCellValue(units.get(key).bathroom2.getCommode_score());
                row_ce.getCell(91).setCellValue(units.get(key).bathroom2.getMedicinecabinet_score());
                row_ce.getCell(92).setCellValue(units.get(key).bathroom2.getMirror_score());
            } catch (NullPointerException e) {
                row_ce.getCell(84).setCellValue("");
                row_ce.getCell(85).setCellValue("");
                row_ce.getCell(86).setCellValue("");
                row_ce.getCell(87).setCellValue("");
                row_ce.getCell(88).setCellValue("");
                row_ce.getCell(89).setCellValue("");
                row_ce.getCell(90).setCellValue("");
                row_ce.getCell(91).setCellValue("");
                row_ce.getCell(92).setCellValue("");
            }

            // bathroom 3 (try to add it, if it doesn't exist, fill it with blank slots)
            try {
                row_ce.getCell(93).setCellValue(units.get(key).bathroom3.getFloor_score());
                row_ce.getCell(94).setCellValue(units.get(key).bathroom3.getWallsceiling_score());
                row_ce.getCell(95).setCellValue(units.get(key).bathroom3.getDoor_score());
                row_ce.getCell(96).setCellValue(units.get(key).bathroom3.getLight_score());
                row_ce.getCell(97).setCellValue(units.get(key).bathroom3.getCountertop_score());
                row_ce.getCell(98).setCellValue(units.get(key).bathroom3.getTubshower_score());
                row_ce.getCell(99).setCellValue(units.get(key).bathroom3.getCommode_score());
                row_ce.getCell(100).setCellValue(units.get(key).bathroom3.getMedicinecabinet_score());
                row_ce.getCell(101).setCellValue(units.get(key).bathroom3.getMirror_score());
            } catch (NullPointerException e) {
                row_ce.getCell(93).setCellValue("");
                row_ce.getCell(94).setCellValue("");
                row_ce.getCell(95).setCellValue("");
                row_ce.getCell(96).setCellValue("");
                row_ce.getCell(97).setCellValue("");
                row_ce.getCell(98).setCellValue("");
                row_ce.getCell(99).setCellValue("");
                row_ce.getCell(100).setCellValue("");
                row_ce.getCell(101).setCellValue("");
            }

            // occupied
            if(units.get(key).overall.getOccupancy() != null) {
                if (units.get(key).overall.getOccupancy().equals("Occupied")) {
                    row_ce.getCell(102).setCellValue("X");
                } else {
                    row_ce.getCell(102).setCellValue("");
                }
            

                // vacant
                if (units.get(key).overall.getOccupancy().equals("Vacant")) {
                    row_ce.getCell(103).setCellValue("X");
                } else {
                    row_ce.getCell(103).setCellValue("");
                }
            }

            // cleanliness
            row_ce.getCell(104).setCellValue(units.get(key).overall.getCleanlinesss_score());

            // furnishings
            row_ce.getCell(105).setCellValue(units.get(key).overall.getFurnishings_score());

            // dog
            if (units.get(key).overall.isPets_dog()) {
                row_ce.getCell(106).setCellValue("X");
            } else {
                row_ce.getCell(106).setCellValue("");
            }

            // cat
            if (units.get(key).overall.isPets_cat()) {
                row_ce.getCell(107).setCellValue("X");
            } else {
                row_ce.getCell(107).setCellValue("");
            }

            // other
            if (units.get(key).overall.isPets_other()) {
                row_ce.getCell(108).setCellValue("X");
            } else {
                row_ce.getCell(108).setCellValue("");
            }

            // number of pets
            row_ce.getCell(109).setCellValue(units.get(key).overall.getPets_number());

            // smoker
            if (units.get(key).overall.isSmoker()) {
                row_ce.getCell(110).setCellValue("X");
            } else {
                row_ce.getCell(110).setCellValue("");
            }

            // hoarder
            if (units.get(key).overall.isHoarder()) {
                row_ce.getCell(111).setCellValue("X");
            } else {
                row_ce.getCell(111).setCellValue("");
            }

            // smoke detectors
            if (units.get(key).overall.isHasSmokeDetectors()) {
                row_ce.getCell(112).setCellValue("X");
            } else {
                row_ce.getCell(112).setCellValue("");
            }

            // letter grade
            row_ce.getCell(113).setCellValue(units.get(key).overall.getLetter_grade());

            // </editor-fold>
            
            // update global
            r++;
        }
    } // end outputScoresForCapEx()

    /**
     * Outputs the details to the Details worksheet
     */
    public void outputDetails() {

        // update global starting point
        r = 7;

        // create a set of keys and parse through the units
        Set<String> keys = units.keySet();
        for (String key : keys) {
            row = details_sheet.getRow(r);

            // put the unit number in the side-header
            row.getCell(1).setCellValue(units.get(key).getUnitNum());

            // This huge block of code basically just puts the values of the
            // "not scores" into the specified slots in the sheet
            
            // <editor-fold desc="huge block of code" defaultstate="collapsed">
            
            // entry
            row.getCell(2).setCellValue(units.get(key).entry.getDoor_type());
            row.getCell(3).setCellValue(units.get(key).entry.getDoor_variety());
            row.getCell(4).setCellValue(units.get(key).entry.getDoor_material());
            row.getCell(5).setCellValue(units.get(key).entry.getFloor_type());
            row.getCell(6).setCellValue(units.get(key).entry.getStairs_flooring());
            row.getCell(7).setCellValue(units.get(key).entry.getFloor_waterdamage());
            row.getCell(8).setCellValue(units.get(key).entry.getWallsceiling_waterdamage());
            row.getCell(9).setCellValue(units.get(key).entry.getWindows_waterdamage());
            row.getCell(10).setCellValue(units.get(key).entry.getWalls_type());
            row.getCell(11).setCellValue(units.get(key).entry.getCeilings_type());
            row.getCell(12).setCellValue(units.get(key).entry.getCloset_door_type());
            row.getCell(13).setCellValue(units.get(key).entry.getCloset_door_variety());
            row.getCell(14).setCellValue(units.get(key).entry.getCloset_door_material());
            row.getCell(15).setCellValue(units.get(key).entry.getWindows_type());
            row.getCell(16).setCellValue(units.get(key).entry.getBlindsdrapes_type());
            row.getCell(17).setCellValue(units.get(key).entry.getLight_type());
            row.getCell(18).setCellValue(units.get(key).entry.getLight_finish());

            // living room
            row.getCell(19).setCellValue(units.get(key).livingroom.getDoor_type());
            row.getCell(20).setCellValue(units.get(key).livingroom.getDoor_variety());
            row.getCell(21).setCellValue(units.get(key).livingroom.getDoor_material());
            row.getCell(22).setCellValue(units.get(key).livingroom.getFloor_type());
            row.getCell(23).setCellValue(units.get(key).livingroom.getFloor_waterdamage());
            row.getCell(24).setCellValue(units.get(key).livingroom.getWallsceiling_waterdamage());
            row.getCell(25).setCellValue(units.get(key).livingroom.getWindows_waterdamage());
            row.getCell(26).setCellValue(units.get(key).livingroom.getWalls_type());
            row.getCell(27).setCellValue(units.get(key).livingroom.getCeiling_type());
            row.getCell(28).setCellValue(units.get(key).livingroom.getWindows_type());
            row.getCell(29).setCellValue(units.get(key).livingroom.getBlindsdrapes_type());
            row.getCell(30).setCellValue(units.get(key).livingroom.getLight_type());
            row.getCell(31).setCellValue(units.get(key).livingroom.getLight_finish());
            row.getCell(32).setCellValue(units.get(key).livingroom.getPatio_door_type());
            row.getCell(33).setCellValue(units.get(key).livingroom.getPatio_balcony());
            row.getCell(34).setCellValue(units.get(key).livingroom.getFireplace_type());

            // dining room
            row.getCell(35).setCellValue(units.get(key).diningroom.getDoor_type());
            row.getCell(36).setCellValue(units.get(key).diningroom.getDoor_variety());
            row.getCell(37).setCellValue(units.get(key).diningroom.getDoor_material());
            row.getCell(38).setCellValue(units.get(key).diningroom.getFloor_type());
            row.getCell(39).setCellValue(units.get(key).diningroom.getFloor_waterdamage());
            row.getCell(40).setCellValue(units.get(key).diningroom.getWallsceiling_waterdamage());
            row.getCell(41).setCellValue(units.get(key).diningroom.getWindows_waterdamage());
            row.getCell(42).setCellValue(units.get(key).diningroom.getWalls_type());
            row.getCell(43).setCellValue(units.get(key).diningroom.getCeiling_type());
            row.getCell(44).setCellValue(units.get(key).diningroom.getWindows_type());
            row.getCell(45).setCellValue(units.get(key).diningroom.getBlindsdrapes_type());
            row.getCell(46).setCellValue(units.get(key).diningroom.getLight_type());
            row.getCell(47).setCellValue(units.get(key).diningroom.getLight_finish());

            // kitchen
            row.getCell(48).setCellValue(units.get(key).kitchen.getDoor_type());
            row.getCell(49).setCellValue(units.get(key).kitchen.getDoor_variety());
            row.getCell(50).setCellValue(units.get(key).kitchen.getDoor_material());
            row.getCell(51).setCellValue(units.get(key).kitchen.getFloor_type());
            row.getCell(52).setCellValue(units.get(key).kitchen.getFloor_waterdamage());
            row.getCell(53).setCellValue(units.get(key).kitchen.getWallsceiling_waterdamage());
            row.getCell(54).setCellValue(units.get(key).kitchen.getWindows_waterdamage());
            row.getCell(55).setCellValue(units.get(key).kitchen.getWalls_type());
            row.getCell(56).setCellValue(units.get(key).kitchen.getCeiling_type());
            row.getCell(57).setCellValue(units.get(key).kitchen.getWindows_type());
            row.getCell(58).setCellValue(units.get(key).kitchen.getBlindsdrapes_type());
            row.getCell(59).setCellValue(units.get(key).kitchen.getPantry_door_type());
            row.getCell(60).setCellValue(units.get(key).kitchen.getPantry_door_variety());
            row.getCell(61).setCellValue(units.get(key).kitchen.getPantry_door_material());
            row.getCell(62).setCellValue(units.get(key).kitchen.getLight_type());
            row.getCell(63).setCellValue(units.get(key).kitchen.getLight_finish());
            row.getCell(64).setCellValue(units.get(key).kitchen.getSink_type());
            row.getCell(65).setCellValue(units.get(key).kitchen.getCabinets_type());
            row.getCell(66).setCellValue(units.get(key).kitchen.getCountertops_type());
            row.getCell(67).setCellValue(units.get(key).kitchen.getPassbar_type());

            // mechanical
            row.getCell(68).setCellValue(units.get(key).mechanical.getRange_model());
            row.getCell(69).setCellValue(units.get(key).mechanical.getRange_finish());
            row.getCell(70).setCellValue(units.get(key).mechanical.getRange_type());
            row.getCell(71).setCellValue(units.get(key).mechanical.getFridge_model());
            row.getCell(72).setCellValue(units.get(key).mechanical.getFridge_finish());
            row.getCell(73).setCellValue(units.get(key).mechanical.getFridge_icemaker());
            row.getCell(74).setCellValue(units.get(key).mechanical.getDishwasher_model());
            row.getCell(75).setCellValue(units.get(key).mechanical.getDishwasher_finish());
            row.getCell(76).setCellValue(units.get(key).mechanical.getHood_microwave());
            row.getCell(77).setCellValue(units.get(key).mechanical.getHood_model());
            row.getCell(78).setCellValue(units.get(key).mechanical.getHood_finish());
            row.getCell(79).setCellValue(units.get(key).mechanical.getHvac_model());
            row.getCell(80).setCellValue(units.get(key).mechanical.getHvac_type());
            row.getCell(81).setCellValue(units.get(key).mechanical.getWater_heater_model());
            row.getCell(82).setCellValue(units.get(key).mechanical.getWater_heater_type());
            row.getCell(83).setCellValue(units.get(key).mechanical.getWasher_dryer_model());
            row.getCell(84).setCellValue(units.get(key).mechanical.getWasher_dryer_finish());
            row.getCell(85).setCellValue(units.get(key).mechanical.isHasHookup());

            // utility
            row.getCell(86).setCellValue(units.get(key).utility.getDoor_type());
            row.getCell(87).setCellValue(units.get(key).utility.getDoor_variety());
            row.getCell(88).setCellValue(units.get(key).utility.getDoor_material());
            row.getCell(89).setCellValue(units.get(key).utility.getFloor_type());
            row.getCell(90).setCellValue(units.get(key).utility.getFloor_waterdamage());
            row.getCell(91).setCellValue(units.get(key).utility.getWallsceiling_waterdamage());
            row.getCell(92).setCellValue(units.get(key).utility.getWalls_type());
            row.getCell(93).setCellValue(units.get(key).utility.getCeiling_type());
            row.getCell(94).setCellValue(units.get(key).utility.getLight_type());
            row.getCell(95).setCellValue(units.get(key).utility.getLight_finish());
            
            // bedroom 1
            row.getCell(96).setCellValue(units.get(key).bedroom1.getDoor_type());
            row.getCell(97).setCellValue(units.get(key).bedroom1.getDoor_variety());
            row.getCell(98).setCellValue(units.get(key).bedroom1.getFloor_type());
            row.getCell(99).setCellValue(units.get(key).bedroom1.getFloor_waterdamage());
            row.getCell(100).setCellValue(units.get(key).bedroom1.getWallsceiling_waterdamage());
            row.getCell(101).setCellValue(units.get(key).bedroom1.getWindows_waterdamage());
            row.getCell(102).setCellValue(units.get(key).bedroom1.getWalls_type());
            row.getCell(103).setCellValue(units.get(key).bedroom1.getCeiling_type());
            row.getCell(104).setCellValue(units.get(key).bedroom1.getCloset_door_type());
            row.getCell(105).setCellValue(units.get(key).bedroom1.getCloset_door_variety());
            row.getCell(106).setCellValue(units.get(key).bedroom1.getCloset_door_material());
            row.getCell(107).setCellValue(units.get(key).bedroom1.getWindows_type());
            row.getCell(108).setCellValue(units.get(key).bedroom1.getBlindsdrapes_type());
            row.getCell(109).setCellValue(units.get(key).bedroom1.getLight_type());
            row.getCell(110).setCellValue(units.get(key).bedroom1.getLight_finish());

            // bedroom 2 (Try to add it, if it doesn't exist, leave the fields blank)
            try {
                row.getCell(111).setCellValue(units.get(key).bedroom2.getDoor_type());
                row.getCell(112).setCellValue(units.get(key).bedroom2.getDoor_variety());
                row.getCell(113).setCellValue(units.get(key).bedroom2.getFloor_type());
                row.getCell(114).setCellValue(units.get(key).bedroom2.getFloor_waterdamage());
                row.getCell(115).setCellValue(units.get(key).bedroom2.getWallsceiling_waterdamage());
                row.getCell(116).setCellValue(units.get(key).bedroom2.getWindows_waterdamage());
                row.getCell(117).setCellValue(units.get(key).bedroom2.getWalls_type());
                row.getCell(118).setCellValue(units.get(key).bedroom2.getCeiling_type());
                row.getCell(119).setCellValue(units.get(key).bedroom2.getCloset_door_type());
                row.getCell(120).setCellValue(units.get(key).bedroom2.getCloset_door_variety());
                row.getCell(121).setCellValue(units.get(key).bedroom2.getCloset_door_material());
                row.getCell(122).setCellValue(units.get(key).bedroom2.getWindows_type());
                row.getCell(123).setCellValue(units.get(key).bedroom2.getBlindsdrapes_type());
                row.getCell(124).setCellValue(units.get(key).bedroom2.getLight_type());
                row.getCell(125).setCellValue(units.get(key).bedroom2.getLight_finish());
            } catch (NullPointerException ex) {
                // do nothing
            }

            // bedroom 3 (Try to add it, if it doesn't exist, leave the fields blank)
            try {
                row.getCell(126).setCellValue(units.get(key).bedroom3.getDoor_type());
                row.getCell(127).setCellValue(units.get(key).bedroom3.getDoor_variety());
                row.getCell(128).setCellValue(units.get(key).bedroom3.getFloor_type());
                row.getCell(129).setCellValue(units.get(key).bedroom3.getFloor_waterdamage());
                row.getCell(130).setCellValue(units.get(key).bedroom3.getWallsceiling_waterdamage());
                row.getCell(131).setCellValue(units.get(key).bedroom3.getWindows_waterdamage());
                row.getCell(132).setCellValue(units.get(key).bedroom3.getWalls_type());
                row.getCell(133).setCellValue(units.get(key).bedroom3.getCeiling_type());
                row.getCell(134).setCellValue(units.get(key).bedroom3.getCloset_door_type());
                row.getCell(135).setCellValue(units.get(key).bedroom3.getCloset_door_variety());
                row.getCell(136).setCellValue(units.get(key).bedroom3.getCloset_door_material());
                row.getCell(137).setCellValue(units.get(key).bedroom3.getWindows_type());
                row.getCell(138).setCellValue(units.get(key).bedroom3.getBlindsdrapes_type());
                row.getCell(139).setCellValue(units.get(key).bedroom3.getLight_type());
                row.getCell(140).setCellValue(units.get(key).bedroom3.getLight_finish());
            } catch (NullPointerException ex) {
                // do nothing
            }

            // bathroom 1
            row.getCell(141).setCellValue(units.get(key).bathroom1.getDoor_type());
            row.getCell(142).setCellValue(units.get(key).bathroom1.getDoor_variety());
            row.getCell(143).setCellValue(units.get(key).bathroom1.getFloor_type());
            row.getCell(144).setCellValue(units.get(key).bathroom1.getFloor_waterdamage());
            row.getCell(145).setCellValue(units.get(key).bathroom1.getWallsceiling_waterdamage());
            row.getCell(146).setCellValue(units.get(key).bathroom1.getWalls_type());
            row.getCell(147).setCellValue(units.get(key).bathroom1.getCeiling_type());
            row.getCell(148).setCellValue(units.get(key).bathroom1.getLight_type());
            row.getCell(149).setCellValue(units.get(key).bathroom1.getLight_finish());
            row.getCell(150).setCellValue(units.get(key).bathroom1.getCountertop_material());
            row.getCell(151).setCellValue(units.get(key).bathroom1.getTubshower_material());
            row.getCell(152).setCellValue(units.get(key).bathroom1.getTubshower_type());

            // bathroom 2 (Try to add it, if it doesn't exist, leave the fields blank)
            try {
                row.getCell(153).setCellValue(units.get(key).bathroom2.getDoor_type());
                row.getCell(154).setCellValue(units.get(key).bathroom2.getDoor_variety());
                row.getCell(155).setCellValue(units.get(key).bathroom2.getFloor_type());
                row.getCell(156).setCellValue(units.get(key).bathroom2.getFloor_waterdamage());
                row.getCell(157).setCellValue(units.get(key).bathroom2.getWallsceiling_waterdamage());
                row.getCell(158).setCellValue(units.get(key).bathroom2.getWalls_type());
                row.getCell(159).setCellValue(units.get(key).bathroom2.getCeiling_type());
                row.getCell(160).setCellValue(units.get(key).bathroom2.getLight_type());
                row.getCell(161).setCellValue(units.get(key).bathroom2.getLight_finish());
                row.getCell(162).setCellValue(units.get(key).bathroom2.getCountertop_material());
                row.getCell(163).setCellValue(units.get(key).bathroom2.getTubshower_material());
                row.getCell(164).setCellValue(units.get(key).bathroom2.getTubshower_type());
            } catch (NullPointerException ex) {
                // do nothing
            }

            // bathroom 3 (Try to add it, if it doesn't exist, leave the fields blank)
            try {
                row.getCell(165).setCellValue(units.get(key).bathroom3.getDoor_type());
                row.getCell(166).setCellValue(units.get(key).bathroom3.getDoor_variety());
                row.getCell(167).setCellValue(units.get(key).bathroom3.getFloor_type());
                row.getCell(168).setCellValue(units.get(key).bathroom3.getFloor_waterdamage());
                row.getCell(169).setCellValue(units.get(key).bathroom3.getWallsceiling_waterdamage());
                row.getCell(170).setCellValue(units.get(key).bathroom3.getWalls_type());
                row.getCell(171).setCellValue(units.get(key).bathroom3.getCeiling_type());
                row.getCell(172).setCellValue(units.get(key).bathroom3.getLight_type());
                row.getCell(173).setCellValue(units.get(key).bathroom3.getLight_finish());
                row.getCell(174).setCellValue(units.get(key).bathroom3.getCountertop_material());
                row.getCell(175).setCellValue(units.get(key).bathroom3.getTubshower_material());
                row.getCell(176).setCellValue(units.get(key).bathroom3.getTubshower_type());
            } catch (NullPointerException ex) {
                // do nothing
            }

            // </editor-fold>
            
            // update global
            r++;
        }
    } // end outputDetails()

    /**
     * Outputs the notes to the Notes worksheet
     */
    public void outputNotes() {

        // make some variables
        r = 3;
        Unit unit;

        // create a set of keys and parse through the units
        Set<String> keys = units.keySet();
        for (String key : keys) {

            // more variables
            unit = units.get(key);
            notePut = false;

            // This huge block of code basically just checks to see if there's a
            // note in the field
            // If there is, put it in, else skip it
            
            // <editor-fold desc="huge block of code" defaultstate="collapsed">
            
            // entry
            if (checkForNote(unit.entry.getEntry_comments())) {
                putNote(unit, "Entry/Hall", "Overall Comments", unit.entry.getEntry_comments());
            }
            
            if (checkForNote(unit.entry.getDoor_notes())) {
                putNote(unit, "Entry/Hall", "Doors", unit.entry.getDoor_notes());
            }

            if (checkForNote(unit.entry.getFloor_notes())) {
                putNote(unit, "Entry/Hall", "Floor", unit.entry.getFloor_notes());
            }

            if (checkForNote(unit.entry.getStairs_notes())) {
                putNote(unit, "Entry/Hall", "Stairs", unit.entry.getStairs_notes());
            }

            if (checkForNote(unit.entry.getWallsceiling_notes())) {
                putNote(unit, "Entry/Hall", "Walls/Ceiling", unit.entry.getWallsceiling_notes());
            }

            if (checkForNote(unit.entry.getCloset_notes())) {
                putNote(unit, "Entry/Hall", "Closet", unit.entry.getCloset_notes());
            }

            if (checkForNote(unit.entry.getWindows_notes())) {
                putNote(unit, "Entry/Hall", "Windows", unit.entry.getWindows_notes());
            }

            if (checkForNote(unit.entry.getBlindsdrapes_notes())) {
                putNote(unit, "Entry/Hall", "Blinds/Drapes", unit.entry.getBlindsdrapes_notes());
            }

            if (checkForNote(unit.entry.getLight_notes())) {
                putNote(unit, "Entry/Hall", "Light Fixture", unit.entry.getLight_notes());
            }
            
            if (checkForNote(unit.entry.getUnaccounted_items())) {
                putNote(unit, "Entry/Hall", "Unaccounted Items", unit.entry.getUnaccounted_items());
            }

            // living room
            if (checkForNote(unit.livingroom.getLivingroom_comments())) {
                putNote(unit, "Living Room", "Overall Comments", unit.livingroom.getLivingroom_comments());
            }
            
            if (checkForNote(unit.livingroom.getFloor_notes())) {
                putNote(unit, "Living Room", "Floor", unit.livingroom.getFloor_notes());
            }

            if (checkForNote(unit.livingroom.getWallsceiling_notes())) {
                putNote(unit, "Living Room", "Walls/Ceilings", unit.livingroom.getWallsceiling_notes());
            }

            if (checkForNote(unit.livingroom.getDoor_notes())) {
                putNote(unit, "Living Room", "Doors", unit.livingroom.getDoor_notes());
            }

            if (checkForNote(unit.livingroom.getWindows_notes())) {
                putNote(unit, "Living Room", "Windows", unit.livingroom.getWindows_notes());
            }

            if (checkForNote(unit.livingroom.getBlindsdrapes_notes())) {
                putNote(unit, "Living Room", "Blinds/Drapes", unit.livingroom.getBlindsdrapes_notes());
            }

            if (checkForNote(unit.livingroom.getLight_notes())) {
                putNote(unit, "Living Room", "Light Fixture", unit.livingroom.getLight_notes());
            }

            if (checkForNote(unit.livingroom.getPatio_notes())) {
                putNote(unit, "Living Room", "Patio/Bal", unit.livingroom.getPatio_notes());
            }

            if (checkForNote(unit.livingroom.getFireplace_notes())) {
                putNote(unit, "Living Room", "Fireplace", unit.livingroom.getFireplace_notes());
            }

            // dining room
            if (checkForNote(unit.diningroom.getDiningroom_comments())) {
                putNote(unit, "Dining Room", "Overall Comments", unit.diningroom.getDiningroom_comments());
            }
            
            if (checkForNote(unit.diningroom.getFloor_notes())) {
                putNote(unit, "Dining Room", "Floor", unit.diningroom.getFloor_notes());
            }

            if (checkForNote(unit.diningroom.getWallsceiling_notes())) {
                putNote(unit, "Dining Room", "Walls/Ceiling", unit.diningroom.getWallsceiling_notes());
            }

            if (checkForNote(unit.diningroom.getWindows_notes())) {
                putNote(unit, "Dining Room", "Windows", unit.diningroom.getWindows_notes());
            }

            if (checkForNote(unit.diningroom.getBlindsdrapes_notes())) {
                putNote(unit, "Dining Room", "Blinds/Drapes", unit.diningroom.getBlindsdrapes_notes());
            }

            if (checkForNote(unit.diningroom.getLight_notes())) {
                putNote(unit, "Dining Room", "Light Fixture", unit.diningroom.getLight_notes());
            }

            // kitchen
            if (checkForNote(unit.kitchen.getKitchen_comments())) {
                putNote(unit, "Kitchen", "Overall Comments", unit.kitchen.getKitchen_comments());
            }
            
            if (checkForNote(unit.kitchen.getFloor_notes())) {
                putNote(unit, "Kitchen", "Floor", unit.kitchen.getFloor_notes());
            }

            if (checkForNote(unit.kitchen.getWallsceiling_notes())) {
                putNote(unit, "Kitchen", "Walls/Ceiling", unit.kitchen.getWallsceiling_notes());
            }

            if (checkForNote(unit.kitchen.getWindows_notes())) {
                putNote(unit, "Kitchen", "Windows", unit.kitchen.getWindows_notes());
            }

            if (checkForNote(unit.kitchen.getBlindsdrapes_notes())) {
                putNote(unit, "Kitchen", "Blinds/Drapes", unit.kitchen.getBlindsdrapes_notes());
            }

            if (checkForNote(unit.kitchen.getDoor_notes())) {
                putNote(unit, "Kitchen", "Doors", unit.kitchen.getDoor_notes());
            }

            if (checkForNote(unit.kitchen.getPantry_notes())) {
                putNote(unit, "Kitchen", "Pantry", unit.kitchen.getPantry_notes());
            }

            if (checkForNote(unit.kitchen.getLight_notes())) {
                putNote(unit, "Kitchen", "Light Fixture", unit.kitchen.getLight_notes());
            }

            if (checkForNote(unit.kitchen.getSink_notes())) {
                putNote(unit, "Kitchen", "Sink", unit.kitchen.getSink_notes());
            }

            if (checkForNote(unit.kitchen.getCabinets_notes())) {
                putNote(unit, "Kitchen", "Cabinets", unit.kitchen.getCabinets_notes());
            }

            if (checkForNote(unit.kitchen.getCountertops_notes())) {
                putNote(unit, "Kitchen", "Countertops", unit.kitchen.getCountertops_notes());
            }

            if (checkForNote(unit.kitchen.getPassbar_notes())) {
                putNote(unit, "Kitchen", "Pass/Bar", unit.kitchen.getPassbar_notes());
            }

            // mechanical
            if (checkForNote(unit.mechanical.getMechanical_comments())) {
                putNote(unit, "Mechanical", "Overall Comments", unit.mechanical.getMechanical_comments());
            }
            
            if (checkForNote(unit.mechanical.getRange_notes())) {
                putNote(unit, "Mechanical", "Range", unit.mechanical.getRange_notes());
            }

            if (checkForNote(unit.mechanical.getFridge_notes())) {
                putNote(unit, "Mechanical", "Refridgerator", unit.mechanical.getFridge_notes());
            }

            if (checkForNote(unit.mechanical.getDishwasher_notes())) {
                putNote(unit, "Mechanical", "Dishwasher", unit.mechanical.getDishwasher_notes());
            }

            if (checkForNote(unit.mechanical.getHood_notes())) {
                putNote(unit, "Mechanical", "Hood/Vent", unit.mechanical.getHood_notes());
            }

            if (checkForNote(unit.mechanical.getHvac_notes())) {
                putNote(unit, "Mechanical", "HVAC", unit.mechanical.getHvac_notes());
            }

            if (checkForNote(unit.mechanical.getWater_heater_notes())) {
                putNote(unit, "Mechanical", "Water Heater", unit.mechanical.getWater_heater_notes());
            }

            if (checkForNote(unit.mechanical.getWasher_dryer_notes())) {
                putNote(unit, "Mechanical", "W/D", unit.mechanical.getWasher_dryer_notes());
            }

            if (checkForNote(unit.mechanical.getDisposal_notes())) {
                putNote(unit, "Mechanical", "Disposal", unit.mechanical.getDisposal_notes());
            }

            // utility/storage
            if (checkForNote(unit.utility.getUtility_comments())) {
                putNote(unit, "Utility/Storage", "Overall Comments", unit.utility.getUtility_comments());
            }
            
            if (checkForNote(unit.utility.getDoor_notes())) {
                putNote(unit, "Utility/Storage", "Door", unit.utility.getDoor_notes());
            }

            if (checkForNote(unit.utility.getWallsceiling_notes())) {
                putNote(unit, "Utility/Storage", "Walls/Ceiling", unit.utility.getWallsceiling_notes());
            }

            if (checkForNote(unit.utility.getFloor_notes())) {
                putNote(unit, "Utility/Storage", "Floor", unit.utility.getFloor_notes());
            }

            // bedroom 1
            if (checkForNote(unit.bedroom1.getBedroom_comments())) {
                putNote(unit, "Bedroom #1", "Overall Comments", unit.bedroom1.getBedroom_comments());
            }
            
            if (checkForNote(unit.bedroom1.getFloor_notes())) {
                putNote(unit, "Bedroom 1", "Floor", unit.bedroom1.getFloor_notes());
            }

            if (checkForNote(unit.bedroom1.getWallsceiling_notes())) {
                putNote(unit, "Bedroom 1", "Walls/Ceiling", unit.bedroom1.getWallsceiling_notes());
            }

            if (checkForNote(unit.bedroom1.getDoor_notes())) {
                putNote(unit, "Bedroom 1", "Door", unit.bedroom1.getDoor_notes());
            }

            if (checkForNote(unit.bedroom1.getCloset_notes())) {
                putNote(unit, "Bedroom 1", "Closet", unit.bedroom1.getCloset_notes());
            }

            if (checkForNote(unit.bedroom1.getWindows_notes())) {
                putNote(unit, "Bedroom 1", "Windows", unit.bedroom1.getWindows_notes());
            }

            if (checkForNote(unit.bedroom1.getBlindsdrapes_notes())) {
                putNote(unit, "Bedroom 1", "Blinds/Drapes", unit.bedroom1.getBlindsdrapes_notes());
            }

            if (checkForNote(unit.bedroom1.getLight_notes())) {
                putNote(unit, "Bedroom 1", "Light Fixture", unit.bedroom1.getLight_notes());
            }

            // bedroom 2 (try it, if it doesn't exist, don't do anything)
            try {
                if (checkForNote(unit.bedroom2.getBedroom_comments())) {
                    putNote(unit, "Bedroom #2", "Overall Comments", unit.bedroom2.getBedroom_comments());
                }
            
                if (checkForNote(unit.bedroom2.getFloor_notes())) {
                    putNote(unit, "Bedroom 2", "Floor", unit.bedroom2.getFloor_notes());
                }

                if (checkForNote(unit.bedroom2.getWallsceiling_notes())) {
                    putNote(unit, "Bedroom 2", "Walls/Ceiling", unit.bedroom2.getWallsceiling_notes());
                }

                if (checkForNote(unit.bedroom2.getDoor_notes())) {
                    putNote(unit, "Bedroom 2", "Door", unit.bedroom2.getDoor_notes());
                }

                if (checkForNote(unit.bedroom2.getCloset_notes())) {
                    putNote(unit, "Bedroom 2", "Closet", unit.bedroom2.getCloset_notes());
                }

                if (checkForNote(unit.bedroom2.getWindows_notes())) {
                    putNote(unit, "Bedroom 2", "Windows", unit.bedroom2.getWindows_notes());
                }

                if (checkForNote(unit.bedroom2.getBlindsdrapes_notes())) {
                    putNote(unit, "Bedroom 2", "Blinds/Drapes", unit.bedroom2.getBlindsdrapes_notes());
                }

                if (checkForNote(unit.bedroom2.getLight_notes())) {
                    putNote(unit, "Bedroom 2", "Light Fixture", unit.bedroom2.getLight_notes());
                }

            } catch (NullPointerException ex) {
                // do nothing
            }

            // bedroom 3 (try it, if it doesn't exist, don't do anything)
            try {
                if (checkForNote(unit.bedroom3.getBedroom_comments())) {
                    putNote(unit, "Bedroom #3", "Overall Comments", unit.bedroom3.getBedroom_comments());
                }
            
                if (checkForNote(unit.bedroom3.getFloor_notes())) {
                    putNote(unit, "Bedroom 3", "Floor", unit.bedroom3.getFloor_notes());
                }

                if (checkForNote(unit.bedroom3.getWallsceiling_notes())) {
                    putNote(unit, "Bedroom 3", "Walls/Ceiling", unit.bedroom3.getWallsceiling_notes());
                }

                if (checkForNote(unit.bedroom3.getDoor_notes())) {
                    putNote(unit, "Bedroom 3", "Door", unit.bedroom3.getDoor_notes());
                }

                if (checkForNote(unit.bedroom3.getCloset_notes())) {
                    putNote(unit, "Bedroom 3", "Closet", unit.bedroom3.getCloset_notes());
                }

                if (checkForNote(unit.bedroom3.getWindows_notes())) {
                    putNote(unit, "Bedroom 3", "Windows", unit.bedroom3.getWindows_notes());
                }

                if (checkForNote(unit.bedroom3.getBlindsdrapes_notes())) {
                    putNote(unit, "Bedroom 3", "Blinds/Drapes", unit.bedroom3.getBlindsdrapes_notes());
                }

                if (checkForNote(unit.bedroom3.getLight_notes())) {
                    putNote(unit, "Bedroom 3", "Light Fixture", unit.bedroom3.getLight_notes());
                }

            } catch (NullPointerException ex) {
                // do nothing
            }

            // bathroom 1
            if (checkForNote(unit.bathroom1.getBathroom_comments())) {
                putNote(unit, "Bathroom #1", "Overall Comments", unit.bathroom1.getBathroom_comments());
            }
            
            if (checkForNote(unit.bathroom1.getFloor_notes())) {
                putNote(unit, "Bathroom 1", "Floor", unit.bathroom1.getFloor_notes());
            }

            if (checkForNote(unit.bathroom1.getWallsceiling_notes())) {
                putNote(unit, "Bathroom 1", "Walls/Ceiling", unit.bathroom1.getWallsceiling_notes());
            }

            if (checkForNote(unit.bathroom1.getDoor_notes())) {
                putNote(unit, "Bathroom 1", "Door", unit.bathroom1.getDoor_notes());
            }

            if (checkForNote(unit.bathroom1.getLight_notes())) {
                putNote(unit, "Bathroom 1", "Light Fixture", unit.bathroom1.getLight_notes());
            }

            if (checkForNote(unit.bathroom1.getCountertop_notes())) {
                putNote(unit, "Bathroom 1", "Countertop", unit.bathroom1.getCountertop_notes());
            }

            if (checkForNote(unit.bathroom1.getTubshower_notes())) {
                putNote(unit, "Bathroom 1", "Tub/Shower", unit.bathroom1.getTubshower_notes());
            }

            if (checkForNote(unit.bathroom1.getCommode_notes())) {
                putNote(unit, "Bathroom 1", "Commode", unit.bathroom1.getCommode_notes());
            }

            if (checkForNote(unit.bathroom1.getMedicinecabinet_notes())) {
                putNote(unit, "Bathroom 1", "Medicine Cabinet", unit.bathroom1.getMedicinecabinet_notes());
            }

            if (checkForNote(unit.bathroom1.getMirror_notes())) {
                putNote(unit, "Bathroom 1", "Mirror", unit.bathroom1.getMirror_notes());
            }

            // bathroom 2 (try it, if it doesn't exist, don't do anything)
            try {
                if (checkForNote(unit.bathroom2.getBathroom_comments())) {
                    putNote(unit, "Bathroom #2", "Overall Comments", unit.bathroom2.getBathroom_comments());
                }
            
                if (checkForNote(unit.bathroom2.getFloor_notes())) {
                    putNote(unit, "Bathroom 2", "Floor", unit.bathroom2.getFloor_notes());
                }

                if (checkForNote(unit.bathroom2.getWallsceiling_notes())) {
                    putNote(unit, "Bathroom 2", "Walls/Ceiling", unit.bathroom2.getWallsceiling_notes());
                }

                if (checkForNote(unit.bathroom2.getDoor_notes())) {
                    putNote(unit, "Bathroom 2", "Door", unit.bathroom2.getDoor_notes());
                }

                if (checkForNote(unit.bathroom2.getLight_notes())) {
                    putNote(unit, "Bathroom 2", "Light Fixture", unit.bathroom2.getLight_notes());
                }

                if (checkForNote(unit.bathroom2.getCountertop_notes())) {
                    putNote(unit, "Bathroom 2", "Countertop", unit.bathroom2.getCountertop_notes());
                }

                if (checkForNote(unit.bathroom2.getTubshower_notes())) {
                    putNote(unit, "Bathroom 2", "Tub/Shower", unit.bathroom2.getTubshower_notes());
                }

                if (checkForNote(unit.bathroom2.getCommode_notes())) {
                    putNote(unit, "Bathroom 2", "Commode", unit.bathroom2.getCommode_notes());
                }

                if (checkForNote(unit.bathroom2.getMedicinecabinet_notes())) {
                    putNote(unit, "Bathroom 2", "Medicine Cabinet", unit.bathroom2.getMedicinecabinet_notes());
                }

                if (checkForNote(unit.bathroom2.getMirror_notes())) {
                    putNote(unit, "Bathroom 2", "Mirror", unit.bathroom2.getMirror_notes());
                }
            } catch (NullPointerException ex) {
                // do nothing
            }

            // bathroom 3 (try it, if it doesn't exist, don't do anything)
            try {
                if (checkForNote(unit.bathroom3.getBathroom_comments())) {
                    putNote(unit, "Bathroom #3", "Overall Comments", unit.bathroom3.getBathroom_comments());
                }
            
                if (checkForNote(unit.bathroom3.getFloor_notes())) {
                    putNote(unit, "Bathroom 3", "Floor", unit.bathroom3.getFloor_notes());
                }

                if (checkForNote(unit.bathroom3.getWallsceiling_notes())) {
                    putNote(unit, "Bathroom 3", "Walls/Ceiling", unit.bathroom3.getWallsceiling_notes());
                }

                if (checkForNote(unit.bathroom3.getDoor_notes())) {
                    putNote(unit, "Bathroom 3", "Door", unit.bathroom3.getDoor_notes());
                }

                if (checkForNote(unit.bathroom3.getLight_notes())) {
                    putNote(unit, "Bathroom 3", "Light Fixture", unit.bathroom3.getLight_notes());
                }

                if (checkForNote(unit.bathroom3.getCountertop_notes())) {
                    putNote(unit, "Bathroom 3", "Countertop", unit.bathroom3.getCountertop_notes());
                }

                if (checkForNote(unit.bathroom3.getTubshower_notes())) {
                    putNote(unit, "Bathroom 3", "Tub/Shower", unit.bathroom3.getTubshower_notes());
                }

                if (checkForNote(unit.bathroom3.getCommode_notes())) {
                    putNote(unit, "Bathroom 3", "Commode", unit.bathroom3.getCommode_notes());
                }

                if (checkForNote(unit.bathroom3.getMedicinecabinet_notes())) {
                    putNote(unit, "Bathroom 3", "Medicine Cabinet", unit.bathroom3.getMedicinecabinet_notes());
                }

                if (checkForNote(unit.bathroom3.getMirror_notes())) {
                    putNote(unit, "Bathroom 3", "Mirror", unit.bathroom3.getMirror_notes());
                }
            } catch (NullPointerException ex) {
                // do nothing
            }

            // overall
            if (checkForNote(unit.overall.getCleanliness_notes())) {
                putNote(unit, "Overall", "Cleanliness", unit.overall.getCleanliness_notes());
            }

            if (checkForNote(unit.overall.getFurnishings_notes())) {
                putNote(unit, "Overall", "Furnishings", unit.overall.getFurnishings_notes());
            }

            if (checkForNote(unit.overall.getGeneral_comments())) {
                putNote(unit, "Overall", "General Comments", unit.overall.getGeneral_comments());
            }
            
            if (checkForNote(unit.overall.getUnaccounted_sections())) {
                putNote (unit, "Overall", "Unaccounted Sections", unit.overall.getUnaccounted_sections());
            }

            // </editor-fold>
            
            // update global
            if (notePut) {
                r++;
            }
        }
    } // end outputNotes()
    
    /**
     * Function to outputs the photos based on a link
     * Downloads a local file and provides a web link to the file
     */
    public void outputPhotos() {

        // make some variables
        Unit unit;
        r = 3;
        
            // delete the old directory
            //deleteDir(new File("export\\interior\\images"));

        // create a set of keys and parse through the units
        Set<String> keys = units.keySet();
        for (String key : keys) {
            
            // more variables
            unit = units.get(key);
            photoPut = false;
            URL url;

            // iterate through the pictures
            if (unit.getPictures().size() > 0) {
                for (int i = 0; i < unit.getPictures().size(); i++) {

                    // create a picture object that has the needed info in it
                    Picture pic = (Picture) unit.getPictures().get(i);

                    // create each unit directory
                    File newDir = new File(imagesPath + "\\" + unit.getUnitNum());
                    newDir.mkdirs();

                    // actually do the picture creation
                    try {
                        // get the url from the pic object and prepare the buffered image stream
                        url = new URL(pic.getUrl());
                        BufferedImage img = ImageIO.read(url);
                        
                        // create the base file name
                        String filename = imagesPath + "\\" + unit.getUnitNum() + "\\"
                                + pic.getSection().replace("/", "").replace(" ", "")
                                + "_" + pic.getItem().replace("/", "").replace(" ", "") + ".jpg";

                        // check to see if there's any duplicates
                        File f = new File(getNewFileName(filename));

                        // write the image
                        ImageIO.write(img, "jpg", f);
                        
                        // write the info into the spreadsheet
                        putPhoto(unit, pic, f);

                    } catch (MalformedURLException ex) {
                        System.err.println(ex);
                    } catch (IOException ex) {
                        System.err.println(ex);
                    }
                }
            }
        }
    } // end outputPhotos()

    /**
     * Helper function to read existing files and check for duplicates
     * Shamelessly stolen from StackOverflow
     * 
     * @param filename
     * @return the new filename
     */
    public String getNewFileName(String filename) {
        
        // create some variables
        File aFile = new File(filename);
        int fileNo = 0;
        String newFileName = "";
        
        // if the file exists and it's not a directory
        if (aFile.exists() && !aFile.isDirectory()) {

            // while the file still already exists, keep adding another number
            // keeps iterating until the file name is valid
            while (aFile.exists()) {
                fileNo++;
                aFile = new File(filename.replace(".jpg", "_" + fileNo + ".jpg"));
                newFileName = filename.replace(".jpg", "_" + fileNo + ".jpg");
            }

        // else the file doesn't exist already
        } else if (!aFile.exists()) {
            
            // create the new file and filename
            try {
                aFile.createNewFile();
                newFileName = filename;
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
        
        // return the valid filename
        return newFileName;
    } // end getNewFileName()

    /**
     * Function to get the String version of the score 
     * Returns a blank if the score is 0
     *
     * @param num, the number to parse
     * @return the string of the int
     */
    public String getNum(int num) {
        if (num == 0) {
            return "";
        } else {
            return Integer.toString(num);
        }
    } // end getNum()

    /**
     * Checks to see if there is a note field in the object. If there's nothing
     * there, return false, else return true
     *
     * @param s, the string to check
     * @return boolean result
     */
    public boolean checkForNote(String s) {
        if (s == null) {
            return false;
        } else {
            return !s.equals("");
        }
    } // end checkForNote()

    /**
     * Handles putting the note into the "Notes" worksheet, and also handles the
     * formatting of new cell (calibri 12, center formatting, etc)
     *
     * @param unit
     * @param section
     * @param item
     * @param note
     */
    public void putNote(Unit unit, String section, String item, String note) {

        // get the row
        row = notes_sheet.createRow(r);

        // fill the cells
        row.createCell(0).setCellValue(unit.getUnitNum());
        row.createCell(1).setCellValue(section);
        row.createCell(2).setCellValue(item);
        row.createCell(3).setCellValue(note.trim());

        // get the style
        CellStyle style = row.getCell(0).getCellStyle();

        // set the text wrap to true and v-align to center
        style.setWrapText(true);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        // set the font to calibri 12
        XSSFFont font = wb.createFont();
        font.setFontHeight(12);
        font.setFontName("Calibri");
        style.setFont(font);

        // update globals
        notePut = true;
        r++;
    } // end putNote()

    /**
     * Handles putting the photo into the "Photos" worksheet
     * 
     * @param unit
     * @param picture
     * @param file 
     */
    public void putPhoto(Unit unit, Picture picture, File file) {
        
        // get the row
        row = photos_sheet.createRow(r);

        // fill the cells
        row.createCell(0).setCellValue(unit.getUnitNum());
        row.createCell(1).setCellValue(picture.getSection());
        row.createCell(2).setCellValue(picture.getItem());
        row.createCell(3).setCellValue(file.getName());
        row.createCell(4).setCellValue(picture.getUrl());

        // make the cells clickable
        XSSFHyperlink file_link = wb.getCreationHelper().createHyperlink(HyperlinkType.FILE);
        file_link.setTooltip("Click to open the file on your desktop");
        file_link.setAddress(file.toURI().toString());
        row.getCell(3).setHyperlink(file_link);

        XSSFHyperlink url_link = wb.getCreationHelper().createHyperlink(HyperlinkType.URL);
        url_link.setTooltip("Click to open the file on the web");
        url_link.setAddress(picture.getUrl());
        row.getCell(4).setHyperlink(url_link);

        // get the style
        CellStyle style = row.getCell(0).getCellStyle();

        // set the text wrap to true and v-align to center
        style.setWrapText(true);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        // set the font to calibri 12
        XSSFFont font = wb.createFont();
        font.setFontHeight(12);
        font.setFontName("Calibri");
        style.setFont(font);

        // update globals
        photoPut = true;
        r++;
    } // end putPhoto()
} // end IntOutput.java
