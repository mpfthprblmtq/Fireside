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

// class Main
public class Main {
    
    // Frame object
    static MainFrame main = new MainFrame();

    /**
     * Does the thing
     * @param args
     */
    public static void main(String[] args) {
        main.setLocationRelativeTo(null);
        main.setVisible(true);
    } // end main()

} // end Main.java
