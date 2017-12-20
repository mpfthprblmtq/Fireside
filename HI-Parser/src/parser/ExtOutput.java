/**
 * File:    ExtOutput.java
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import javax.imageio.ImageIO;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

// class ExtOutput
public class ExtOutput {

    // ivars
    private Map<String, Building> buildings;

    // declarations for the excel file reading
    File file = new File("templates\\consolidated_exterior_template.xlsx");
    InputStream fs;
    XSSFWorkbook wb;
    XSSFSheet scores_sheet;
    XSSFSheet details_sheet;
    XSSFSheet notes_sheet;
    XSSFSheet photos_sheet;
    XSSFRow row;
    XSSFCell cell;
    
    File fileCE = new File("templates//consolidated_exterior_capex_template.xlsx");
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
     * Empty constructor
     * @param folderPath
     */
    public ExtOutput(String folderPath) {
        this.folderPath = folderPath + "\\Exterior\\";
        this.consolidatedOutputPath = this.folderPath + "Consolidated Exterior.xlsx";
        this.capexOutputPath = this.folderPath + "Exterior CapEx Report.xlsx";
        this.imagesPath = this.folderPath + "\\Images";
    }

    /**
     * @return the units
     */
    public Map<String, Building> getBuildlings() {
        return buildings;
    }

    /**
     * @param buildings
     */
    public void setBuildings(Map<String, Building> buildings) {
        this.buildings = buildings;
    }
    
    /**
     * @return the buildings
     */
    public File getConsolidatedFile() {
        return new File(consolidatedOutputPath);
    }
    
    /**
     * @return the buildings
     */
    public File getCapExFile() {
        return new File(capexOutputPath);
    }

    /**
     * Function that controls the output of all the data
     */
    public void outputData() {
        
        // try with all your might
        try {

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
            }

            // thing(s) went wrong
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {

        }
    } // end outputData()
    
    /**
     * Helper function used to recursively delete files in the images folder
     * to avoid duplicates
     * @param f
     * @throws FileNotFoundException 
     */
    public void delete(File f) throws FileNotFoundException {
        if (f.isDirectory()) {
            for (File c : f.listFiles()) {
                delete(c);
            }
        }
    }

    /**
     * Outputs the scores to the Scores worksheet
     */
    public void outputScores() {

        // update global starting point
        r = 4;

        // create a set of keys and parse through the buildings
        Set<String> keys = buildings.keySet();
        for (String key : keys) {
            row = scores_sheet.getRow(r);

            // put unit number in the side-header
            XSSFCreationHelper helper = wb.getCreationHelper();
            XSSFHyperlink url = helper.createHyperlink(HyperlinkType.URL);
            url.setAddress("https://manage.happyco.com/folder/44290/inspections/" + buildings.get(key).getID());
            row.getCell(1).setCellValue(buildings.get(key).getName());
            row.getCell(1).setHyperlink(url);

            // This huge block of code basically uses getNum to put the scores
            // into the specified spots in the sheet
            // <editor-fold desc="huge block of code" defaultstate="collapsed">
            // interior hallway
            row.getCell(2).setCellValue(getNum(buildings.get(key).interiorhallway.getFloor_score()));
            row.getCell(3).setCellValue(getNum(buildings.get(key).interiorhallway.getDoors_score()));
            row.getCell(4).setCellValue(getNum(buildings.get(key).interiorhallway.getDoor_hardware_score()));
            row.getCell(5).setCellValue(getNum(buildings.get(key).interiorhallway.getWallsceilings_score()));
            row.getCell(6).setCellValue(getNum(buildings.get(key).interiorhallway.getLightfixture_score()));
            row.getCell(7).setCellValue(getNum(buildings.get(key).interiorhallway.getHandrails_score()));
            row.getCell(8).setCellValue(getNum(buildings.get(key).interiorhallway.getMoldings_score()));
            row.getCell(9).setCellValue(getNum(buildings.get(key).interiorhallway.getSmoke_detectors_score()));
            row.getCell(10).setCellValue(getNum(buildings.get(key).interiorhallway.getFire_extinguisher_score()));
            row.getCell(11).setCellValue(getNum(buildings.get(key).interiorhallway.getStaircoverings_score()));
            row.getCell(12).setCellValue(getNum(buildings.get(key).interiorhallway.getDoorknockers_score()));
            row.getCell(13).setCellValue(getNum(buildings.get(key).interiorhallway.getUnitnumbers_score()));

            // dumpsters
            row.getCell(14).setCellValue(getNum(buildings.get(key).dumpsters.getFencepickets_score()));
            row.getCell(15).setCellValue(getNum(buildings.get(key).dumpsters.getGates_score()));
            row.getCell(16).setCellValue(getNum(buildings.get(key).dumpsters.getGateholes_score()));
            row.getCell(17).setCellValue(getNum(buildings.get(key).dumpsters.getBallasts_score()));

            // main supplies
            row.getCell(18).setCellValue(getNum(buildings.get(key).mainsupplies.getWatershutoff_score()));
            row.getCell(19).setCellValue(getNum(buildings.get(key).mainsupplies.getSumppumps_score()));
            row.getCell(20).setCellValue(getNum(buildings.get(key).mainsupplies.getSewercleanout_score()));
            row.getCell(21).setCellValue(getNum(buildings.get(key).mainsupplies.getElectricmeter_score()));
            row.getCell(22).setCellValue(getNum(buildings.get(key).mainsupplies.getGasmeter_score()));
            row.getCell(23).setCellValue(getNum(buildings.get(key).mainsupplies.getFirehydrants_score()));
            row.getCell(24).setCellValue(getNum(buildings.get(key).mainsupplies.getHvacshutoff_score()));

            // landscaping
            row.getCell(25).setCellValue(getNum(buildings.get(key).landscaping.getTrees_score()));
            row.getCell(26).setCellValue(getNum(buildings.get(key).landscaping.getBushes_score()));
            row.getCell(27).setCellValue(getNum(buildings.get(key).landscaping.getMulchgravel_score()));
            row.getCell(28).setCellValue(getNum(buildings.get(key).landscaping.getRetainingwalls_score()));
            row.getCell(29).setCellValue(getNum(buildings.get(key).landscaping.getIrrigation_score()));
            row.getCell(30).setCellValue(getNum(buildings.get(key).landscaping.getDrainage_score()));
            row.getCell(31).setCellValue(getNum(buildings.get(key).landscaping.getWaterpooling_score()));
            row.getCell(32).setCellValue(getNum(buildings.get(key).landscaping.getWeeds_score()));
            row.getCell(33).setCellValue(getNum(buildings.get(key).landscaping.getPicnicarea_score()));
            row.getCell(34).setCellValue(getNum(buildings.get(key).landscaping.getLighting_score()));
            row.getCell(35).setCellValue(getNum(buildings.get(key).landscaping.getDebris_score()));
            row.getCell(36).setCellValue(getNum(buildings.get(key).landscaping.getGrills_score()));
            row.getCell(37).setCellValue(getNum(buildings.get(key).landscaping.getDogpark_score()));
            row.getCell(38).setCellValue(getNum(buildings.get(key).landscaping.getDogpark_fence_score()));
            
            // building exterior
            row.getCell(39).setCellValue(getNum(buildings.get(key).buildingexterior.getGutters_score()));
            row.getCell(40).setCellValue(getNum(buildings.get(key).buildingexterior.getDownspouts_score()));
            row.getCell(41).setCellValue(getNum(buildings.get(key).buildingexterior.getSoffit_score()));
            row.getCell(42).setCellValue(getNum(buildings.get(key).buildingexterior.getFascia_score()));
            row.getCell(43).setCellValue(getNum(buildings.get(key).buildingexterior.getSiding_score()));
            row.getCell(44).setCellValue(getNum(buildings.get(key).buildingexterior.getRoofs_score()));
            row.getCell(45).setCellValue(getNum(buildings.get(key).buildingexterior.getVentpipescaging_score()));
            row.getCell(46).setCellValue(getNum(buildings.get(key).buildingexterior.getGutterdrains_score()));
            row.getCell(47).setCellValue(getNum(buildings.get(key).buildingexterior.getDebris_score()));
            row.getCell(48).setCellValue(getNum(buildings.get(key).buildingexterior.getWindows_score()));
            row.getCell(49).setCellValue(getNum(buildings.get(key).buildingexterior.getWindowweatherstripping_score()));
            row.getCell(50).setCellValue(getNum(buildings.get(key).buildingexterior.getFoundationcracks_score()));
            row.getCell(51).setCellValue(getNum(buildings.get(key).buildingexterior.getFoundationleaks_score()));
            row.getCell(52).setCellValue(getNum(buildings.get(key).buildingexterior.getPorchposts_score()));
            row.getCell(53).setCellValue(getNum(buildings.get(key).buildingexterior.getSpindles_score()));
            row.getCell(54).setCellValue(getNum(buildings.get(key).buildingexterior.getMailbox_score()));
            row.getCell(55).setCellValue(getNum(buildings.get(key).buildingexterior.getBrickwork_score()));
            row.getCell(56).setCellValue(getNum(buildings.get(key).buildingexterior.getTermites_score()));
            row.getCell(57).setCellValue(getNum(buildings.get(key).buildingexterior.getDoors_score()));
            row.getCell(58).setCellValue(getNum(buildings.get(key).buildingexterior.getDoorhardware_score()));
            row.getCell(59).setCellValue(getNum(buildings.get(key).buildingexterior.getSatellites_score()));
            row.getCell(60).setCellValue(getNum(buildings.get(key).buildingexterior.getExposedwiring_score()));
            row.getCell(61).setCellValue(getNum(buildings.get(key).buildingexterior.getDecks_score()));
            row.getCell(62).setCellValue(getNum(buildings.get(key).buildingexterior.getDeckflashing_score()));
            row.getCell(63).setCellValue(getNum(buildings.get(key).buildingexterior.getLighting_score()));
            row.getCell(64).setCellValue(getNum(buildings.get(key).buildingexterior.getStairs_score()));
            row.getCell(65).setCellValue(getNum(buildings.get(key).buildingexterior.getHandrails_score()));
            row.getCell(66).setCellValue(getNum(buildings.get(key).buildingexterior.getExteriorunitnumbers_score()));
            row.getCell(67).setCellValue(getNum(buildings.get(key).buildingexterior.getHvac_score()));
            row.getCell(68).setCellValue(getNum(buildings.get(key).buildingexterior.getWindowwells_score()));
            row.getCell(69).setCellValue(getNum(buildings.get(key).buildingexterior.getExterioraccess_score()));

            // asphalt/concrete
            row.getCell(70).setCellValue(getNum(buildings.get(key).asphaltconcrete.getOverlay_score()));
            row.getCell(71).setCellValue(getNum(buildings.get(key).asphaltconcrete.getParkingbumpers_score()));
            row.getCell(72).setCellValue(getNum(buildings.get(key).asphaltconcrete.getStriping_score()));
            row.getCell(73).setCellValue(getNum(buildings.get(key).asphaltconcrete.getSealing_score()));
            row.getCell(74).setCellValue(getNum(buildings.get(key).asphaltconcrete.getCrackfillings_score()));
            row.getCell(75).setCellValue(getNum(buildings.get(key).asphaltconcrete.getPotholes_score()));
            row.getCell(76).setCellValue(getNum(buildings.get(key).asphaltconcrete.getSidewalks_score()));
            row.getCell(77).setCellValue(getNum(buildings.get(key).asphaltconcrete.getTriphazards_score()));
            row.getCell(78).setCellValue(getNum(buildings.get(key).asphaltconcrete.getHandicapparking_score()));

            // fencing
            row.getCell(79).setCellValue(getNum(buildings.get(key).fencing.getFencepickets_score()));
            row.getCell(80).setCellValue(getNum(buildings.get(key).fencing.getLockslatches_score()));
            row.getCell(81).setCellValue(getNum(buildings.get(key).fencing.getGates_score()));

            // signage
            row.getCell(82).setCellValue(getNum(buildings.get(key).signage.getEntrance_score()));
            row.getCell(83).setCellValue(getNum(buildings.get(key).signage.getOffice_score()));
            row.getCell(84).setCellValue(getNum(buildings.get(key).signage.getStreet_score()));
            row.getCell(85).setCellValue(getNum(buildings.get(key).signage.getParking_score()));
            row.getCell(86).setCellValue(getNum(buildings.get(key).signage.getHandicapparking_score()));
            row.getCell(87).setCellValue(getNum(buildings.get(key).signage.getPool_score()));
            row.getCell(88).setCellValue(getNum(buildings.get(key).signage.getAdamarking_score()));
            row.getCell(89).setCellValue(getNum(buildings.get(key).signage.getDogpark_score()));
            row.getCell(90).setCellValue(getNum(buildings.get(key).signage.getDumpster_score()));
            row.getCell(91).setCellValue(getNum(buildings.get(key).signage.getFirelane_score()));

            // </editor-fold>
            // update global
            r++;
        }

    } // end outputScores()
    
    /**
     * Function that controls the output of all the data
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

            outputScoresForCapEx();

            // close the sheet
            try {
                fs_ce.close();
            } catch (IOException ex) {
                System.err.println(ex);
            }

            // write all of this to the new file
            try (FileOutputStream outFile = new FileOutputStream(capexOutputPath)) {
                wb_ce.write(outFile);
            }

            // thing(s) went wrong
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {

        }
    } // end outputData()
    
    public void outputScoresForCapEx() {

        // update global starting point
        r = 7;

        // create a set of keys and parse through the units
        Set<String> keys = buildings.keySet();
        for (String key : keys) {
            row_ce = scores_sheet_ce.getRow(r);

            // put unit number in the side-header
            XSSFCreationHelper helper = wb_ce.getCreationHelper();
            XSSFHyperlink url = helper.createHyperlink(HyperlinkType.URL);
            url.setAddress("https://manage.happyco.com/folder/41917/inspections/" + buildings.get(key).getID());
            row_ce.getCell(1).setCellValue(buildings.get(key).getName());
            row_ce.getCell(1).setHyperlink(url);

            // This huge block of code basically uses getNum to put the scores
            // into the specified spots in the sheet
            // <editor-fold desc="huge block of code" defaultstate="collapsed">
            
            // entry
            row_ce.getCell(2).setCellValue(getNum(buildings.get(key).interiorhallway.getFloor_score()));
            row_ce.getCell(3).setCellValue(getNum(buildings.get(key).interiorhallway.getDoors_score()));
            row_ce.getCell(4).setCellValue(getNum(buildings.get(key).interiorhallway.getDoor_hardware_score()));
            row_ce.getCell(5).setCellValue(getNum(buildings.get(key).interiorhallway.getWallsceilings_score()));
            row_ce.getCell(6).setCellValue(getNum(buildings.get(key).interiorhallway.getLightfixture_score()));
            row_ce.getCell(7).setCellValue(getNum(buildings.get(key).interiorhallway.getHandrails_score()));
            row_ce.getCell(8).setCellValue(getNum(buildings.get(key).interiorhallway.getMoldings_score()));
            row_ce.getCell(9).setCellValue(getNum(buildings.get(key).interiorhallway.getSmoke_detectors_score()));
            row_ce.getCell(10).setCellValue(getNum(buildings.get(key).interiorhallway.getFire_extinguisher_score()));
            row_ce.getCell(11).setCellValue(getNum(buildings.get(key).interiorhallway.getStaircoverings_score()));
            row_ce.getCell(12).setCellValue(getNum(buildings.get(key).interiorhallway.getDoorknockers_score()));
            row_ce.getCell(13).setCellValue(getNum(buildings.get(key).interiorhallway.getUnitnumbers_score()));

            // dumpsters
            row_ce.getCell(14).setCellValue(getNum(buildings.get(key).dumpsters.getFencepickets_score()));
            row_ce.getCell(15).setCellValue(getNum(buildings.get(key).dumpsters.getGates_score()));
            row_ce.getCell(16).setCellValue(getNum(buildings.get(key).dumpsters.getGateholes_score()));
            row_ce.getCell(17).setCellValue(getNum(buildings.get(key).dumpsters.getBallasts_score()));

            // main supplies
            row_ce.getCell(18).setCellValue(getNum(buildings.get(key).mainsupplies.getWatershutoff_score()));
            row_ce.getCell(19).setCellValue(getNum(buildings.get(key).mainsupplies.getSumppumps_score()));
            row_ce.getCell(20).setCellValue(getNum(buildings.get(key).mainsupplies.getSewercleanout_score()));
            row_ce.getCell(21).setCellValue(getNum(buildings.get(key).mainsupplies.getElectricmeter_score()));
            row_ce.getCell(22).setCellValue(getNum(buildings.get(key).mainsupplies.getGasmeter_score()));
            row_ce.getCell(23).setCellValue(getNum(buildings.get(key).mainsupplies.getFirehydrants_score()));
            row_ce.getCell(24).setCellValue(getNum(buildings.get(key).mainsupplies.getHvacshutoff_score()));

            // landscaping
            row_ce.getCell(25).setCellValue(getNum(buildings.get(key).landscaping.getTrees_score()));
            row_ce.getCell(26).setCellValue(getNum(buildings.get(key).landscaping.getBushes_score()));
            row_ce.getCell(27).setCellValue(getNum(buildings.get(key).landscaping.getMulchgravel_score()));
            row_ce.getCell(28).setCellValue(getNum(buildings.get(key).landscaping.getRetainingwalls_score()));
            row_ce.getCell(29).setCellValue(getNum(buildings.get(key).landscaping.getIrrigation_score()));
            row_ce.getCell(30).setCellValue(getNum(buildings.get(key).landscaping.getDrainage_score()));
            row_ce.getCell(31).setCellValue(getNum(buildings.get(key).landscaping.getWaterpooling_score()));
            row_ce.getCell(32).setCellValue(getNum(buildings.get(key).landscaping.getWeeds_score()));
            row_ce.getCell(33).setCellValue(getNum(buildings.get(key).landscaping.getPicnicarea_score()));
            row_ce.getCell(34).setCellValue(getNum(buildings.get(key).landscaping.getLighting_score()));
            row_ce.getCell(35).setCellValue(getNum(buildings.get(key).landscaping.getDebris_score()));
            row_ce.getCell(36).setCellValue(getNum(buildings.get(key).landscaping.getGrills_score()));
            row_ce.getCell(37).setCellValue(getNum(buildings.get(key).landscaping.getDogpark_score()));
            row_ce.getCell(38).setCellValue(getNum(buildings.get(key).landscaping.getDogpark_fence_score()));
            
            // building exterior
            row_ce.getCell(39).setCellValue(getNum(buildings.get(key).buildingexterior.getGutters_score()));
            row_ce.getCell(40).setCellValue(getNum(buildings.get(key).buildingexterior.getDownspouts_score()));
            row_ce.getCell(41).setCellValue(getNum(buildings.get(key).buildingexterior.getSoffit_score()));
            row_ce.getCell(42).setCellValue(getNum(buildings.get(key).buildingexterior.getFascia_score()));
            row_ce.getCell(43).setCellValue(getNum(buildings.get(key).buildingexterior.getSiding_score()));
            row_ce.getCell(44).setCellValue(getNum(buildings.get(key).buildingexterior.getRoofs_score()));
            row_ce.getCell(45).setCellValue(getNum(buildings.get(key).buildingexterior.getVentpipescaging_score()));
            row_ce.getCell(46).setCellValue(getNum(buildings.get(key).buildingexterior.getGutterdrains_score()));
            row_ce.getCell(47).setCellValue(getNum(buildings.get(key).buildingexterior.getDebris_score()));
            row_ce.getCell(48).setCellValue(getNum(buildings.get(key).buildingexterior.getWindows_score()));
            row_ce.getCell(49).setCellValue(getNum(buildings.get(key).buildingexterior.getWindowweatherstripping_score()));
            row_ce.getCell(50).setCellValue(getNum(buildings.get(key).buildingexterior.getFoundationcracks_score()));
            row_ce.getCell(51).setCellValue(getNum(buildings.get(key).buildingexterior.getFoundationleaks_score()));
            row_ce.getCell(52).setCellValue(getNum(buildings.get(key).buildingexterior.getPorchposts_score()));
            row_ce.getCell(53).setCellValue(getNum(buildings.get(key).buildingexterior.getSpindles_score()));
            row_ce.getCell(54).setCellValue(getNum(buildings.get(key).buildingexterior.getMailbox_score()));
            row_ce.getCell(55).setCellValue(getNum(buildings.get(key).buildingexterior.getBrickwork_score()));
            row_ce.getCell(56).setCellValue(getNum(buildings.get(key).buildingexterior.getTermites_score()));
            row_ce.getCell(57).setCellValue(getNum(buildings.get(key).buildingexterior.getDoors_score()));
            row_ce.getCell(58).setCellValue(getNum(buildings.get(key).buildingexterior.getDoorhardware_score()));
            row_ce.getCell(59).setCellValue(getNum(buildings.get(key).buildingexterior.getSatellites_score()));
            row_ce.getCell(60).setCellValue(getNum(buildings.get(key).buildingexterior.getExposedwiring_score()));
            row_ce.getCell(61).setCellValue(getNum(buildings.get(key).buildingexterior.getDecks_score()));
            row_ce.getCell(62).setCellValue(getNum(buildings.get(key).buildingexterior.getDeckflashing_score()));
            row_ce.getCell(63).setCellValue(getNum(buildings.get(key).buildingexterior.getLighting_score()));
            row_ce.getCell(64).setCellValue(getNum(buildings.get(key).buildingexterior.getStairs_score()));
            row_ce.getCell(65).setCellValue(getNum(buildings.get(key).buildingexterior.getHandrails_score()));
            row_ce.getCell(66).setCellValue(getNum(buildings.get(key).buildingexterior.getExteriorunitnumbers_score()));
            row_ce.getCell(67).setCellValue(getNum(buildings.get(key).buildingexterior.getHvac_score()));
            row_ce.getCell(68).setCellValue(getNum(buildings.get(key).buildingexterior.getWindowwells_score()));
            row_ce.getCell(69).setCellValue(getNum(buildings.get(key).buildingexterior.getExterioraccess_score()));

            // asphalt/concrete
            row_ce.getCell(70).setCellValue(getNum(buildings.get(key).asphaltconcrete.getOverlay_score()));
            row_ce.getCell(71).setCellValue(getNum(buildings.get(key).asphaltconcrete.getParkingbumpers_score()));
            row_ce.getCell(72).setCellValue(getNum(buildings.get(key).asphaltconcrete.getStriping_score()));
            row_ce.getCell(73).setCellValue(getNum(buildings.get(key).asphaltconcrete.getSealing_score()));
            row_ce.getCell(74).setCellValue(getNum(buildings.get(key).asphaltconcrete.getCrackfillings_score()));
            row_ce.getCell(75).setCellValue(getNum(buildings.get(key).asphaltconcrete.getPotholes_score()));
            row_ce.getCell(76).setCellValue(getNum(buildings.get(key).asphaltconcrete.getSidewalks_score()));
            row_ce.getCell(77).setCellValue(getNum(buildings.get(key).asphaltconcrete.getTriphazards_score()));
            row_ce.getCell(78).setCellValue(getNum(buildings.get(key).asphaltconcrete.getHandicapparking_score()));

            // fencing
            row_ce.getCell(79).setCellValue(getNum(buildings.get(key).fencing.getFencepickets_score()));
            row_ce.getCell(80).setCellValue(getNum(buildings.get(key).fencing.getLockslatches_score()));
            row_ce.getCell(81).setCellValue(getNum(buildings.get(key).fencing.getGates_score()));

            // signage
            row_ce.getCell(82).setCellValue(getNum(buildings.get(key).signage.getEntrance_score()));
            row_ce.getCell(83).setCellValue(getNum(buildings.get(key).signage.getOffice_score()));
            row_ce.getCell(84).setCellValue(getNum(buildings.get(key).signage.getStreet_score()));
            row_ce.getCell(85).setCellValue(getNum(buildings.get(key).signage.getParking_score()));
            row_ce.getCell(86).setCellValue(getNum(buildings.get(key).signage.getHandicapparking_score()));
            row_ce.getCell(87).setCellValue(getNum(buildings.get(key).signage.getPool_score()));
            row_ce.getCell(88).setCellValue(getNum(buildings.get(key).signage.getAdamarking_score()));
            row_ce.getCell(89).setCellValue(getNum(buildings.get(key).signage.getDogpark_score()));
            row_ce.getCell(90).setCellValue(getNum(buildings.get(key).signage.getDumpster_score()));
            row_ce.getCell(91).setCellValue(getNum(buildings.get(key).signage.getFirelane_score()));

            // </editor-fold>
            // update global
            r++;
        }
        
        // do the formula calculation
            //XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
        
    } // end outputScores()

    /**
     * Outputs the details to the Details worksheet
     */
    public void outputDetails() {

        // update global starting point
        r = 5;

        // create a set of keys and parse through the buildings
        Set<String> keys = buildings.keySet();
        for (String key : keys) {
            row = details_sheet.getRow(r);

            // put the building name in the side-header
            row.getCell(1).setCellValue(buildings.get(key).getName());

            // This huge block of code basically just puts the values of the
            // locations and number fields in the specified cells in the sheet
            
            // interior hallway
            row.getCell(2).setCellValue(buildings.get(key).interiorhallway.getFloor_type());
            row.getCell(3).setCellValue(buildings.get(key).interiorhallway.getDoors_type());
            row.getCell(4).setCellValue(buildings.get(key).interiorhallway.getDoors_variety());
            row.getCell(5).setCellValue(buildings.get(key).interiorhallway.getDoors_material());
            row.getCell(6).setCellValue(buildings.get(key).interiorhallway.getWalls_type());
            row.getCell(7).setCellValue(buildings.get(key).interiorhallway.getCeiling_type());
            row.getCell(8).setCellValue(buildings.get(key).interiorhallway.getLightfixture_type());
            row.getCell(9).setCellValue(buildings.get(key).interiorhallway.getHandrails_type());
            row.getCell(10).setCellValue(buildings.get(key).interiorhallway.getStaircoverings_type());
            
            // fencing
            row.getCell(11).setCellValue(buildings.get(key).fencing.getFencepickets_type());
            
            // dumpsters
            row.getCell(12).setCellValue(buildings.get(key).dumpsters.getFencepickets_material());
            
            // landscaping
            row.getCell(13).setCellValue(buildings.get(key).landscaping.getRetainingwalls_material());
            row.getCell(14).setCellValue(buildings.get(key).landscaping.getDogpark_fence_material());
            
            // building exterior
            row.getCell(15).setCellValue(buildings.get(key).buildingexterior.getPorchposts_material());
            row.getCell(16).setCellValue(buildings.get(key).buildingexterior.getDoors_type());
            row.getCell(17).setCellValue(buildings.get(key).buildingexterior.getDoors_variety());
            row.getCell(18).setCellValue(buildings.get(key).buildingexterior.getDoors_material());
            row.getCell(19).setCellValue(buildings.get(key).buildingexterior.getWindows_type());
            row.getCell(20).setCellValue(buildings.get(key).buildingexterior.getWindows_number());
            row.getCell(21).setCellValue(buildings.get(key).buildingexterior.getStairs_material());
            row.getCell(22).setCellValue(buildings.get(key).buildingexterior.getHandrails_material());
            
            // main supplies
            row.getCell(23).setCellValue(buildings.get(key).mainsupplies.getWatershutoff_location());
            row.getCell(24).setCellValue(buildings.get(key).mainsupplies.getWatershutoff_number());
            row.getCell(25).setCellValue(buildings.get(key).mainsupplies.getSumppumps_location());
            row.getCell(26).setCellValue(buildings.get(key).mainsupplies.getSumppumps_number());
            row.getCell(27).setCellValue(buildings.get(key).mainsupplies.getSewercleanout_location());
            row.getCell(28).setCellValue(buildings.get(key).mainsupplies.getSewercleanout_number());
            row.getCell(29).setCellValue(buildings.get(key).mainsupplies.getElectricmeter_location());
            row.getCell(30).setCellValue(buildings.get(key).mainsupplies.getElectricmeter_number());
            row.getCell(31).setCellValue(buildings.get(key).mainsupplies.getGasmeter_location());
            row.getCell(32).setCellValue(buildings.get(key).mainsupplies.getGasmeter_number());
            row.getCell(33).setCellValue(buildings.get(key).mainsupplies.getFirehydrants_location());
            row.getCell(34).setCellValue(buildings.get(key).mainsupplies.getFirehydrants_number());
            row.getCell(35).setCellValue(buildings.get(key).mainsupplies.getHvacshutoff_location());
            row.getCell(36).setCellValue(buildings.get(key).mainsupplies.getHvacshutoff_number());
            
            // update global
            r++;
        }
    }// end outputDetails()

    /**
     * Outputs the notes to the Notes worksheet
     */
    public void outputNotes() {

        // make some variables
        r = 3;
        Building building;

        // create a set of keys and parse through the buildings
        Set<String> keys = buildings.keySet();
        for (String key : keys) {

            // more variables
            building = buildings.get(key);
            notePut = false;
            
            // This huge block of code basically just checks to see if there's a
            // note in the field
            // If there is, put it in, else skip it
            // <editor-fold desc="huge block of code" defaultstate="collapsed">
            
            // interior hallway
            if (checkForNote(building.interiorhallway.getFloor_notes())) {
                putNote(building, "Interior Hallway", "Floor", building.interiorhallway.getFloor_notes());
            }

            if (checkForNote(building.interiorhallway.getDoors_notes())) {
                putNote(building, "Interior Hallway", "Doors", building.interiorhallway.getDoors_notes());
            }

            if (checkForNote(building.interiorhallway.getDoor_hardware_notes())) {
                putNote(building, "Interior Hallway", "Door Hardware", building.interiorhallway.getDoor_hardware_notes());
            }

            if (checkForNote(building.interiorhallway.getWallsceilings_notes())) {
                putNote(building, "Interior Hallway", "Walls/Ceiling", building.interiorhallway.getWallsceilings_notes());
            }

            if (checkForNote(building.interiorhallway.getLightfixture_notes())) {
                putNote(building, "Interior Hallway", "Light Fixtures", building.interiorhallway.getLightfixture_notes());
            }

            if (checkForNote(building.interiorhallway.getHandrails_notes())) {
                putNote(building, "Interior Hallway", "Handrails", building.interiorhallway.getHandrails_notes());
            }

            if (checkForNote(building.interiorhallway.getMoldings_notes())) {
                putNote(building, "Interior Hallway", "Moldings", building.interiorhallway.getMoldings_notes());
            }

            if (checkForNote(building.interiorhallway.getSmoke_detectors_notes())) {
                putNote(building, "Interior Hallway", "Smoke Detectors", building.interiorhallway.getSmoke_detectors_notes());
            }

            if (checkForNote(building.interiorhallway.getFire_extinguisher_notes())) {
                putNote(building, "Interior Hallway", "Fire Extinguishers", building.interiorhallway.getFire_extinguisher_notes());
            }

            if (checkForNote(building.interiorhallway.getStaircoverings_notes())) {
                putNote(building, "Interior Hallway", "Stair Covering", building.interiorhallway.getStaircoverings_notes());
            }

            if (checkForNote(building.interiorhallway.getDoorknockers_notes())) {
                putNote(building, "Interior Hallway", "Door Knockers", building.interiorhallway.getDoorknockers_notes());
            }

            if (checkForNote(building.interiorhallway.getUnitnumbers_notes())) {
                putNote(building, "Interior Hallway", "Unit Numbers on Doors", building.interiorhallway.getUnitnumbers_notes());
            }

            if (checkForNote(building.interiorhallway.getHandicapaccessible_notes())) {
                putNote(building, "Interior Hallway", "Handicap Accessible", building.interiorhallway.getHandicapaccessible_notes());
            }

            // dumpsters
            if (checkForNote(building.dumpsters.getFencepickets_notes())) {
                putNote(building, "Dumpsters", "Fence Pickets", building.dumpsters.getFencepickets_notes());
            }

            if (checkForNote(building.dumpsters.getGates_notes())) {
                putNote(building, "Dumpsters", "Gates", building.dumpsters.getGates_notes());
            }

            if (checkForNote(building.dumpsters.getGateholes_notes())) {
                putNote(building, "Dumpsters", "Gate Holes in Ground", building.dumpsters.getGateholes_notes());
            }

            if (checkForNote(building.dumpsters.getBallasts_notes())) {
                putNote(building, "Dumpsters", "Ballasts/Bumpers", building.dumpsters.getBallasts_notes());
            }

            // main supplies
            if (checkForNote(building.mainsupplies.getWatershutoff_notes())) {
                putNote(building, "Main Supplies", "Water Shutoff", building.mainsupplies.getWatershutoff_notes());
            }

            if (checkForNote(building.mainsupplies.getSumppumps_notes())) {
                putNote(building, "Main Supplies", "Sump Pumps", building.mainsupplies.getSumppumps_notes());
            }

            if (checkForNote(building.mainsupplies.getSewercleanout_notes())) {
                putNote(building, "Main Supplies", "Sewer Cleanout", building.mainsupplies.getSewercleanout_notes());
            }

            if (checkForNote(building.mainsupplies.getElectricmeter_notes())) {
                putNote(building, "Main Supplies", "Electric Meter", building.mainsupplies.getElectricmeter_notes());
            }

            if (checkForNote(building.mainsupplies.getGasmeter_notes())) {
                putNote(building, "Main Supplies", "Gas Meter", building.mainsupplies.getElectricmeter_notes());
            }

            if (checkForNote(building.mainsupplies.getFirehydrants_notes())) {
                putNote(building, "Main Supplies", "Fire Hydrants", building.mainsupplies.getFirehydrants_notes());
            }

            if (checkForNote(building.mainsupplies.getHvacshutoff_notes())) {
                putNote(building, "Main Supplies", "HVAC Shutoff", building.mainsupplies.getHvacshutoff_notes());
            }

            // landscaping
            if (checkForNote(building.landscaping.getTrees_notes())) {
                putNote(building, "Landscaping", "Trees", building.landscaping.getTrees_notes());
            }

            if (checkForNote(building.landscaping.getBushes_notes())) {
                putNote(building, "Landscaping", "Bushes", building.landscaping.getBushes_notes());
            }

            if (checkForNote(building.landscaping.getMulchgravel_notes())) {
                putNote(building, "Landscaping", "Mulch/Gravel", building.landscaping.getMulchgravel_notes());
            }

            if (checkForNote(building.landscaping.getRetainingwalls_notes())) {
                putNote(building, "Landscaping", "Retaining Walls", building.landscaping.getRetainingwalls_notes());
            }

            if (checkForNote(building.landscaping.getIrrigation_notes())) {
                putNote(building, "Landscaping", "Irrigation", building.landscaping.getIrrigation_notes());
            }

            if (checkForNote(building.landscaping.getDrainage_notes())) {
                putNote(building, "Landscaping", "Drainage", building.landscaping.getDrainage_notes());
            }

            if (checkForNote(building.landscaping.getWaterpooling_notes())) {
                putNote(building, "Landscaping", "Water Pooling", building.landscaping.getWaterpooling_notes());
            }

            if (checkForNote(building.landscaping.getWeeds_notes())) {
                putNote(building, "Landscaping", "Weeds", building.landscaping.getWeeds_notes());
            }

            if (checkForNote(building.landscaping.getPicnicarea_notes())) {
                putNote(building, "Landscaping", "Picnic/Sitting Area", building.landscaping.getPicnicarea_notes());
            }

            if (checkForNote(building.landscaping.getLighting_notes())) {
                putNote(building, "Landscaping", "Lighting", building.landscaping.getLighting_notes());
            }

            if (checkForNote(building.landscaping.getDebris_notes())) {
                putNote(building, "Landscaping", "Debris", building.landscaping.getDebris_notes());
            }

            // building exterior
            if (checkForNote(building.buildingexterior.getGutters_notes())) {
                putNote(building, "Building Exterior", "Gutters", building.buildingexterior.getGutters_notes());
            }

            if (checkForNote(building.buildingexterior.getDownspouts_notes())) {
                putNote(building, "Building Exterior", "Down Spouts", building.buildingexterior.getDownspouts_notes());
            }

            if (checkForNote(building.buildingexterior.getSoffit_notes())) {
                putNote(building, "Building Exterior", "Soffit", building.buildingexterior.getSoffit_notes());
            }

            if (checkForNote(building.buildingexterior.getFascia_notes())) {
                putNote(building, "Building Exterior", "Fascia", building.buildingexterior.getFascia_notes());
            }

            if (checkForNote(building.buildingexterior.getSiding_notes())) {
                putNote(building, "Building Exterior", "Siding/Stucco", building.buildingexterior.getSiding_notes());
            }

            if (checkForNote(building.buildingexterior.getRoofs_notes())) {
                putNote(building, "Building Exterior", "Roofs", building.buildingexterior.getRoofs_notes());
            }

            if (checkForNote(building.buildingexterior.getVentpipescaging_notes())) {
                putNote(building, "Building Exterior", "Vent Pipes & Caging", building.buildingexterior.getVentpipescaging_notes());
            }

            if (checkForNote(building.buildingexterior.getGutterdrains_notes())) {
                putNote(building, "Building Exterior", "Gutter Drains on Roofs", building.buildingexterior.getGutterdrains_notes());
            }

            if (checkForNote(building.buildingexterior.getDebris_notes())) {
                putNote(building, "Building Exterior", "Debris on Roofs", building.buildingexterior.getDebris_notes());
            }

            if (checkForNote(building.buildingexterior.getWindows_notes())) {
                putNote(building, "Building Exterior", "Windows", building.buildingexterior.getWindows_notes());
            }

            if (checkForNote(building.buildingexterior.getWindowweatherstripping_notes())) {
                putNote(building, "Building Exterior", "Window Weather Stripping", building.buildingexterior.getWindowweatherstripping_notes());
            }

            if (checkForNote(building.buildingexterior.getFoundationcracks_notes())) {
                putNote(building, "Building Exterior", "Foundation Cracks", building.buildingexterior.getFoundationcracks_notes());
            }

            if (checkForNote(building.buildingexterior.getFoundationleaks_notes())) {
                putNote(building, "Building Exterior", "Foundation Leaks", building.buildingexterior.getFoundationleaks_notes());
            }

            if (checkForNote(building.buildingexterior.getPorchposts_notes())) {
                putNote(building, "Building Exterior", "Porch Posts", building.buildingexterior.getPorchposts_notes());
            }

            if (checkForNote(building.buildingexterior.getMailbox_notes())) {
                putNote(building, "Building Exterior", "Mailbox", building.buildingexterior.getMailbox_notes());
            }

            if (checkForNote(building.buildingexterior.getBrickwork_notes())) {
                putNote(building, "Building Exterior", "Brickwork", building.buildingexterior.getBrickwork_notes());
            }

            if (checkForNote(building.buildingexterior.getTermites_notes())) {
                putNote(building, "Building Exterior", "Termites", building.buildingexterior.getTermites_notes());
            }

            if (checkForNote(building.buildingexterior.getDoors_notes())) {
                putNote(building, "Building Exterior", "Doors", building.buildingexterior.getDoors_notes());
            }

            if (checkForNote(building.buildingexterior.getDoorhardware_notes())) {
                putNote(building, "Building Exterior", "Door Hardware", building.buildingexterior.getDoorhardware_notes());
            }

            if (checkForNote(building.buildingexterior.getSatellites_notes())) {
                putNote(building, "Building Exterior", "Satellites", building.buildingexterior.getSatellites_notes());
            }

            if (checkForNote(building.buildingexterior.getExposedwiring_notes())) {
                putNote(building, "Building Exterior", "Exposed Wiring/Cables", building.buildingexterior.getExposedwiring_notes());
            }

            if (checkForNote(building.buildingexterior.getDecks_notes())) {
                putNote(building, "Building Exterior", "Decks", building.buildingexterior.getDecks_notes());
            }

            if (checkForNote(building.buildingexterior.getDeckflashing_notes())) {
                putNote(building, "Building Exterior", "Deck Flashing", building.buildingexterior.getDeckflashing_notes());
            }

            if (checkForNote(building.buildingexterior.getLighting_notes())) {
                putNote(building, "Building Exterior", "Lighting", building.buildingexterior.getLighting_notes());
            }

            if (checkForNote(building.buildingexterior.getHandicapaccessible_notes())) {
                putNote(building, "Building Exterior", "Handicap Accessible", building.buildingexterior.getHandicapaccessible_notes());
            }

            if (checkForNote(building.buildingexterior.getStairs_notes())) {
                putNote(building, "Building Exterior", "Stairs", building.buildingexterior.getStairs_notes());
            }

            if (checkForNote(building.buildingexterior.getHandrails_notes())) {
                putNote(building, "Building Exterior", "Handrails", building.buildingexterior.getHandrails_notes());
            }

            if (checkForNote(building.buildingexterior.getExteriorunitnumbers_notes())) {
                putNote(building, "Building Exterior", "Exterior Unit Numbers", building.buildingexterior.getExteriorunitnumbers_notes());
            }

            if (checkForNote(building.buildingexterior.getHvac_notes())) {
                putNote(building, "Building Exterior", "HVAC/AC", building.buildingexterior.getHvac_notes());
            }

            if (checkForNote(building.buildingexterior.getWindowwells_notes())) {
                putNote(building, "Building Exterior", "Window Wells", building.buildingexterior.getWindowwells_notes());
            }

            if (checkForNote(building.buildingexterior.getExterioraccess_notes())) {
                putNote(building, "Building Exterior", "Exterior Access", building.buildingexterior.getExterioraccess_notes());
            }

            // asphalt/concrete
            if (checkForNote(building.asphaltconcrete.getOverlay_notes())) {
                putNote(building, "Asphalt/Concrete", "Overlay", building.asphaltconcrete.getOverlay_notes());
            }

            if (checkForNote(building.asphaltconcrete.getParkingbumpers_notes())) {
                putNote(building, "Asphalt/Concrete", "Parking Bumpers", building.asphaltconcrete.getParkingbumpers_notes());
            }

            if (checkForNote(building.asphaltconcrete.getStriping_notes())) {
                putNote(building, "Asphalt/Concrete", "Striping", building.asphaltconcrete.getStriping_notes());
            }

            if (checkForNote(building.asphaltconcrete.getSealing_notes())) {
                putNote(building, "Asphalt/Concrete", "Sealing", building.asphaltconcrete.getSealing_notes());
            }

            if (checkForNote(building.asphaltconcrete.getCrackfillings_notes())) {
                putNote(building, "Asphalt/Concrete", "Crack Fillings", building.asphaltconcrete.getCrackfillings_notes());
            }

            if (checkForNote(building.asphaltconcrete.getPotholes_notes())) {
                putNote(building, "Asphalt/Concrete", "Potholes", building.asphaltconcrete.getPotholes_notes());
            }

            if (checkForNote(building.asphaltconcrete.getSidewalks_notes())) {
                putNote(building, "Asphalt/Concrete", "Sidewalks", building.asphaltconcrete.getSidewalks_notes());
            }

            if (checkForNote(building.asphaltconcrete.getTriphazards_notes())) {
                putNote(building, "Asphalt/Concrete", "Trip Hazards", building.asphaltconcrete.getTriphazards_notes());
            }

            if (checkForNote(building.asphaltconcrete.getHandicapparking_notes())) {
                putNote(building, "Asphalt/Concrete", "Handicap Parking", building.asphaltconcrete.getHandicapparking_notes());
            }

            // fencing
            if (checkForNote(building.fencing.getFencepickets_notes())) {
                putNote(building, "Fencing", "Fence Pickets", building.fencing.getFencepickets_notes());
            }

            if (checkForNote(building.fencing.getLockslatches_notes())) {
                putNote(building, "Fencing", "Locks/Latches", building.fencing.getLockslatches_notes());
            }

            if (checkForNote(building.fencing.getGates_notes())) {
                putNote(building, "Fencing", "Gates", building.fencing.getGates_notes());
            }

            // signage
            if (checkForNote(building.signage.getEntrance_notes())) {
                putNote(building, "Signage", "Entrace Sign", building.signage.getEntrance_notes());
            }

            if (checkForNote(building.signage.getOffice_notes())) {
                putNote(building, "Signage", "Office Signage", building.signage.getOffice_notes());
            }

            if (checkForNote(building.signage.getStreet_notes())) {
                putNote(building, "Signage", "Street Signs", building.signage.getStreet_notes());
            }

            if (checkForNote(building.signage.getParking_notes())) {
                putNote(building, "Signage", "Parking Signs", building.signage.getParking_notes());
            }

            if (checkForNote(building.signage.getHandicapparking_notes())) {
                putNote(building, "Signage", "Handicap Parking", building.signage.getHandicapparking_notes());
            }

            if (checkForNote(building.signage.getPool_notes())) {
                putNote(building, "Signage", "Pool Signage", building.signage.getPool_notes());
            }

            if (checkForNote(building.signage.getAdamarking_notes())) {
                putNote(building, "Signage", "ADA Marking on Pavement", building.signage.getAdamarking_notes());
            }

            if (checkForNote(building.signage.getDogpark_notes())) {
                putNote(building, "Signage", "Dog Park Signs", building.signage.getDogpark_notes());
            }

            if (checkForNote(building.signage.getDumpster_notes())) {
                putNote(building, "Signage", "Dumpster Signs", building.signage.getDumpster_notes());
            }

            if (checkForNote(building.signage.getFirelane_notes())) {
                putNote(building, "Signage", "Fire Lane Signs", building.signage.getFirelane_notes());
            }

            // overall
            if (checkForNote(building.overall.getGeneral_comments())) {
                putNote(building, "Overall", "General Comments", building.overall.getGeneral_comments());
            }
            
            // </editor-fold>

            // update global
            if (notePut) {
                r++;
            }
        }
    } // end outputNotes()
    
    /**
     * Function that exports the photos from the links and downloads them
     */
    public void outputPhotos() {
        // make some variables
        Building building;
        r = 2;

        // create a set of keys and parse through the units
        Set<String> keys = buildings.keySet();
        for (String key : keys) {
            
            // more variables
            building = buildings.get(key);
            photoPut = false;
            URL url;

            // iterate through the pictures
            if (building.getPictures().size() > 0) {
                for (int i = 0; i < building.getPictures().size(); i++) {

                    // create a picture object that has the needed info in it
                    Building.Picture pic = (Building.Picture) building.getPictures().get(i);

                    // create each unit directory
                    File newDir = new File(imagesPath + "\\" + building.getName());
                    newDir.mkdirs();

                    try {
                        
                        // get the url from the pic object and prepare the buffered image stream
                        url = new URL(pic.getUrl());
                        BufferedImage img = ImageIO.read(url);
                        
                        // create the base file name
                        String filename = imagesPath + "\\" + building.getName() + "\\"
                                + pic.getSection().replace("/", "").replace(" ", "")
                                + "_" + pic.getItem().replace("/", "").replace(" ", "") + ".jpg";

                        // check to see if there's any duplicates
                        File f = new File(getNewFileName(filename));

                        // write the image
                        ImageIO.write(img, "jpg", f);
                        
                        // write the info into the spreadsheet
                        putPhoto(building, pic, f);

                    } catch (MalformedURLException ex) {
                        System.err.println(ex);
                    } catch (IOException ex) {
                        System.err.println(ex);
                    }
                }
            }
        }
    }
    
    /**
     * Helper function to read existing files and check for duplicates
     * Shamelessly stolen from StackOverflow
     * 
     * @param filename
     * @return
     * @throws IOException 
     */
    public String getNewFileName(String filename) throws IOException {
        File aFile = new File(filename);
        int fileNo = 0;
        String newFileName = "";
        if (aFile.exists() && !aFile.isDirectory()) {

            while (aFile.exists()) {
                fileNo++;
                aFile = new File(filename.replace(".jpg", "_" + fileNo + ".jpg"));
                newFileName = filename.replace(".jpg", "_" + fileNo + ".jpg");
            }

        } else if (!aFile.exists()) {
            aFile.createNewFile();
            newFileName = filename;
        }
        return newFileName;
    }

    /**
     * Function to get the String version of the score Returns a blank if the
     * score is 0
     * @param num, the number to parse
     * @return the string of the int
     */
    public String getNum(int num) {
        if (num == 0) {
            return "";
        } else {
            return Integer.toString(num);
        }
    }

    /**
     * Checks to see if there is a note field in the object.  If there's nothing there,
     * return false, else return true
     * @param s
     * @return 
     */
    public boolean checkForNote(String s) {
        if (s == null) {
            return false;
        } else {
            return !s.equals("");
        }
    }

    /**
     * Handles putting the note into the "Notes" worksheet, and also handles
     * the formatting of new cell (calibri 12, center formatting, etc)
     * @param building
     * @param section
     * @param item
     * @param note 
     */
    public void putNote(Building building, String section, String item, String note) {

        // get the row
        row = notes_sheet.createRow(r);

        // fill the cells
        row.createCell(0).setCellValue(building.getName());
        row.createCell(1).setCellValue(section);
        row.createCell(2).setCellValue(item);
        row.createCell(3).setCellValue(note);

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
    
    public void putPhoto(Building building, Building.Picture picture, File file) {
        // get the row
        row = photos_sheet.createRow(r);

        // fill the cells
        row.createCell(0).setCellValue(building.getName());
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
    }
} // end ExtOutput
