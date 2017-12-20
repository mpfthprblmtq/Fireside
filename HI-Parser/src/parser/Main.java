/**
 * File:    Main.java
 * Author:  Pat Ripley
 *
 * Program Description:
 * Program to take in a folder export from Happy Inspector and read it
 * in, then make another spreadsheet to fit our needs.
 *
 * Class Description:
 * Driver class, gets the spreadsheet and tells the program where to go.
 *
 * Copyright Pat Ripley, 2017
 */

// package
package parser;

// imports
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;

// class Main
public class Main {

    // ivar
    static boolean debug = false;
    
    // globals
    static boolean opened = false;
    static boolean imported = false;
    static boolean exported = false;

    static IntInput intInput = new IntInput();
    static ExtInput extInput = new ExtInput();

    //static IntOutput intOutput = new IntOutput();
    //static ExtOutput extOutput = new ExtOutput();
    
    static MainFrame main = new MainFrame();

    /**
     * Does the thing
     *
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        main.setLocationRelativeTo(null);
        main.setVisible(true);
        
        //System.out.println("Welcome to HI-Parser!");
        //awaitCommand();

    } // end main()

    /**
     * Function that takes the command and sends it to be parsed
     */
    public static void awaitCommand() {
        System.out.print("HI-Parser> ");

        Scanner keyboard = new Scanner(System.in);
        String s = keyboard.nextLine().toLowerCase();

        parseCommand(s);
    }

    /**
     * Determines what functionality to use based on command
     * @param s 
     */
    public static void parseCommand(String s) {
        String[] arr = s.split(" ");

        switch (arr.length) {
            case 0:
                // do nothing
                break;
            case 1:
                switch (arr[0]) {
                    case "interior":
                    case "exterior":
                        System.out.println("Incomplete command, perhaps you were wanting to use one of the following?\n"
                                + "   start\n"
                                + "   open\n"
                                + "   import\n"
                                + "   export");
                        break;
                    case "help":
                        System.out.println("");
                        System.out.println("Commands:");
                        System.out.println("\tinterior start\t\tPerforms whole operation of importing and exporting units automatically");
                        System.out.println("\texterior start\t\tPerforms whole operation of importing and exporting buildings automatically");
                        System.out.println("\topen interior import\tOpens the interior file given by Happy Inspector");
                        System.out.println("\topen exterior import\tOpens the exterior file given by Happy Inspector");
                        System.out.println("\topen interior export\tOpens the finished product");
                        System.out.println("\topen exterior export\tOpens the finished product");
                        System.out.println("");
                        System.out.println("If there are errors, check to see the templates match the original inspection template");
                        System.out.println("");
                        break;
                    case "exit":
                    case "logout":
                    case "stop":
                        System.exit(0);
                    default:
                        System.out.println("Command not recognized. Type \"help\" for a list of options");
                        break;
                }
                break;
            case 2:
                if (arr[0].equals("interior") && arr[1].equals("start")) {

                    // open the workbook to read in
                    intInput.openWorkbook();
                    
                    // start importing
                    System.out.print("Importing units...");
                    intInput.initInterior();
                    intInput.fillUnits();
                    System.out.println("Done");

                    // close the workbook
                    intInput.closeWorkbook();

                    // start exporting
                    System.out.print("Exporting data...");
                    //intOutput.setUnits(intInput.getUnits());
                    //intOutput.outputData();
                    System.out.println("Done");
                    System.out.println("Data was outputted to Consolidated Interior.xlsx");
                    
                    // capEx
                    System.out.print("Creating CapEx report...");
                   // intOutput.outputCapEx();
                    System.out.println("Done");
                    System.out.println("CapEx was outputted to CapEx Report.xlsx");
                    
                    
                } else if (arr[0].equals("exterior") && arr[1].equals("start")) {
                    // open the workbook to read in
                    extInput.openWorkbook();

                    // start importing
                    System.out.print("Importing buildings...");
                    extInput.initExterior();
                    extInput.fillBuildings();
                    System.out.println("Done");

                    // close the workbook
                    extInput.closeWorkbook();

                    // start exporting
                    //extOutput.setBuildings(extInput.getBuildings());
                    //extOutput.outputData();
                    System.out.println("Data was outputted to Consolidated Exterior.xlsx");
                    
                    // capEx
                    System.out.print("Creating CapEx report...");
                    //extOutput.outputCapEx();
                    System.out.println("Done");
                    System.out.println("CapEx was outputted to CapEx Report.xlsx");
                } else if (arr[0].equals("open") && arr[1].equals("file")) {
                    JFileChooser fc = new JFileChooser();
                    File file;

                    // set working directory for filechooser
                    File workingDirectory = new File(System.getProperty("user.dir"));
                    fc.setCurrentDirectory(workingDirectory);

                    // set title of filechooser
                    fc.setDialogTitle("Choose a file to open...");

                    // show filechooser
                    System.out.println("Choosing file to open...");
                    int returnVal = fc.showOpenDialog(null);

                    // what to do after user chooses an option
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        file = fc.getSelectedFile();
                        System.out.println("Opening " + file.getName() + "...");
                            if (Desktop.isDesktopSupported()) {
                            try {
                                Desktop desktop = Desktop.getDesktop();
                                desktop.open(file);
                            } catch (IOException ex) {
                                System.out.println("Could not open file.");
                            }
                        }
                    } else {
                        System.err.println("No file chosen.");
                    }
                }
                break;
            case 3:
                if(s.equals("open interior import")) {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            Desktop desktop = Desktop.getDesktop();
                            File myFile = new File(intInput.file.getAbsolutePath());
                            desktop.open(myFile);
                        } catch (IOException ex) {
                            System.out.println("Must run \"interior start\" first!");
                        }
                    }
                } else if (s.equals("open exterior import")) {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            Desktop desktop = Desktop.getDesktop();
                            File myFile = new File(extInput.file.getAbsolutePath());
                            desktop.open(myFile);
                        } catch (IOException ex) {
                            System.out.println("Must run \"exterior start\" first!");
                        }
                    }
                } else if (s.equals("open interior export")) {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            Desktop desktop = Desktop.getDesktop();
                            File myFile = new File("Consolidated Interior.xlsx");
                            desktop.open(myFile);
                        } catch (IOException ex) {
                            System.out.println("Must run \"exterior start\" first!");
                        }
                    }
                } else if (s.equals("open exterior export")) {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            Desktop desktop = Desktop.getDesktop();
                            File myFile = new File("Consolidated Exterior.xlsx");
                            desktop.open(myFile);
                        } catch (IOException ex) {
                            System.out.println("Must run \"exterior start\" first!");
                        }
                    }
                }
                break;
            default:
                System.out.println("Command not recognized. Type \"help\" for a list of options");
                break;
        }

        awaitCommand();
    }

} // end Main
