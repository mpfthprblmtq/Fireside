package parser;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

public class MainFrame extends javax.swing.JFrame {

    // debug to console
    boolean debugToConsole = true;

    // output path
    String home = System.getProperty("user.home");
    String folderPath = home + "\\Downloads\\Happy Inspector Reports";

    // objects for interior/exterior input/output
    static IntInput intInput;
    static ExtInput extInput;
    IntOutput intOutput;
    ExtOutput extOutput;

    // variables for the filechooser
    JFileChooser fc = new JFileChooser();
    File file;
    static String path;

    // some graphic variables
    String consoleText = "";
    String selectedUnitNum;
    String selectedBuildingName;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {

        // init the components
        initComponents();

        // format the current DateTime to the format right here \/
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HHmm");
        Date date = new Date();

        // add on the date to the folderpath
        this.folderPath += "\\" + dateFormat.format(date);
        new File(folderPath).mkdirs();

        // create the output objects with the new folderpath
        intOutput = new IntOutput(folderPath);
        extOutput = new ExtOutput(folderPath);

        // create the input objects without the new folderpath
        intInput = new IntInput();
        extInput = new ExtInput();

        // if debugToConsole is enabled, things don't go to the log file
        if(!debugToConsole) {

            // set console out/err output to text file logs folder
            String logName = "log " + dateFormat.format(date);
            PrintStream out = null;
            try {
                out = new PrintStream(new FileOutputStream("logs//" + logName + ".txt"));
                System.setOut(out);
                System.setErr(out);
            } catch (FileNotFoundException ex) {
                System.err.println(ex);
            }

            // set the error and log output
            intInput.setOut(out);
            extInput.setOut(out);
            intOutput.setOut(out);
            extOutput.setOut(out);
        }
    }

    /**
     * Updates the text area on the frame by appending the new string to the end
     * @param str, the string to append
     */
    public void updateConsole(String str) {
        consoleText += str;
        console.setText(consoleText);
        console.update(console.getGraphics());
    }

    /**
     * Populates the ListModel for the units list from the units list from IntInput
     * @return the ListModel to populate the JList
     */
    public ListModel populateInteriorList() {

        // create some lists
        HashMap<String, Unit> list = getUnits();
        DefaultListModel<String> listModel = new DefaultListModel<>();

        // sorting
        List<Unit> unitsSorted = new ArrayList<>(list.values());
        Collections.sort(unitsSorted, (Unit o1, Unit o2) -> o1.getUnitNum().compareTo(o2.getUnitNum()));
        for(int i = 0; i < unitsSorted.size(); i++) {
            listModel.addElement(unitsSorted.get(i).getUnitNum());
        }

        // return the listModel to populate the jList
        return listModel;
    }

    /**
     * Resets the Interior JList
     */
    public void clearInteriorList() {
        unitsList.setModel(new DefaultListModel<>());
    }

    /**
     * Populates the ListModel for the buildings list from the units list from ExtInput
     * @return the ListModel to populate the JList
     */
    public ListModel populateExteriorList() {

        // create some lists
        HashMap<String, Building> list = getBuildings();
        DefaultListModel<String> listModel = new DefaultListModel<>();

        // sorting
        List<Building> buildingsSorted = new ArrayList<>(list.values());
        Collections.sort(buildingsSorted, (Building o1, Building o2) -> o1.getName().compareTo(o2.getName()));
        for(int i = 0; i < buildingsSorted.size(); i++) {
            listModel.addElement(buildingsSorted.get(i).getName());
        }

        // return the listModel to populate the jList
        return listModel;
    }

    /**
     * Resets the Exterior JList
     */
    public void clearExteriorList() {
        buildingsList.setModel(new DefaultListModel<>());
    }

    /**
     * @return the units from IntInput
     */
    public HashMap<String, Unit> getUnits() {
        return intInput.getUnits();
    }

    /**
     * @return the buildings from ExtInput
     */
    public HashMap<String, Building> getBuildings() {
        return extInput.getBuildings();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        interiorPanel = new javax.swing.JPanel();
        unitsLabel = new javax.swing.JLabel();
        unitsListSP = new javax.swing.JScrollPane();
        unitsList = new javax.swing.JList<>();
        int_uploadButton = new javax.swing.JButton();
        int_ConsolidatedReportButton = new javax.swing.JButton();
        int_CapexReportButton = new javax.swing.JButton();
        int_SingleReportButton = new javax.swing.JButton();
        int_uploadCheck = new javax.swing.JLabel();
        int_generateCheck = new javax.swing.JLabel();
        int_capexCheck = new javax.swing.JLabel();
        int_singleCheck = new javax.swing.JLabel();
        int_openConsolidatedInteriorButton = new javax.swing.JButton();
        int_openCapexReportButton = new javax.swing.JButton();
        int_CILabel = new javax.swing.JLabel();
        int_CELabel = new javax.swing.JLabel();
        exteriorPanel = new javax.swing.JPanel();
        buildingsLabel = new javax.swing.JLabel();
        buildingsListSP = new javax.swing.JScrollPane();
        buildingsList = new javax.swing.JList<>();
        ext_uploadButton = new javax.swing.JButton();
        ext_ConsolidatedReportButton = new javax.swing.JButton();
        ext_CapexReportButton = new javax.swing.JButton();
        ext_SingleReportButton = new javax.swing.JButton();
        ext_uploadCheck = new javax.swing.JLabel();
        ext_generateCheck = new javax.swing.JLabel();
        ext_capexCheck = new javax.swing.JLabel();
        ext_singleCheck = new javax.swing.JLabel();
        ext_openConsolidatedInteriorButton = new javax.swing.JButton();
        ext_openCapexReportButton = new javax.swing.JButton();
        ext_CILabel = new javax.swing.JLabel();
        ext_CELabel = new javax.swing.JLabel();
        menu = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        resetMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();
        guideMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HI-Parser");
        setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon.png"));
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        console.setEditable(false);
        console.setColumns(20);
        console.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        console.setLineWrap(true);
        console.setRows(5);
        console.setWrapStyleWord(true);
        jScrollPane2.setViewportView(console);

        unitsLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        unitsLabel.setText("Units");

        unitsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        unitsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                unitsListMouseClicked(evt);
            }
        });
        unitsListSP.setViewportView(unitsList);

        int_uploadButton.setText("Upload Inspection Data");
        int_uploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int_uploadButtonActionPerformed(evt);
            }
        });

        int_ConsolidatedReportButton.setText("Generate Consolidated Report");
        int_ConsolidatedReportButton.setEnabled(false);
        int_ConsolidatedReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int_ConsolidatedReportButtonActionPerformed(evt);
            }
        });

        int_CapexReportButton.setText("Generate CapEx Report");
        int_CapexReportButton.setEnabled(false);
        int_CapexReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int_CapexReportButtonActionPerformed(evt);
            }
        });

        int_SingleReportButton.setText("Generate Single Report");
        int_SingleReportButton.setEnabled(false);
        int_SingleReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int_SingleReportButtonActionPerformed(evt);
            }
        });

        int_uploadCheck.setText(" ");
        int_uploadCheck.setMaximumSize(new java.awt.Dimension(23, 23));
        int_uploadCheck.setMinimumSize(new java.awt.Dimension(23, 23));
        int_uploadCheck.setPreferredSize(new java.awt.Dimension(23, 23));

        int_generateCheck.setText(" ");
        int_generateCheck.setMaximumSize(new java.awt.Dimension(23, 23));
        int_generateCheck.setMinimumSize(new java.awt.Dimension(23, 23));
        int_generateCheck.setPreferredSize(new java.awt.Dimension(23, 23));

        int_capexCheck.setText(" ");
        int_capexCheck.setMaximumSize(new java.awt.Dimension(23, 23));
        int_capexCheck.setMinimumSize(new java.awt.Dimension(23, 23));
        int_capexCheck.setPreferredSize(new java.awt.Dimension(23, 23));

        int_singleCheck.setText(" ");
        int_singleCheck.setMaximumSize(new java.awt.Dimension(23, 23));
        int_singleCheck.setMinimumSize(new java.awt.Dimension(23, 23));
        int_singleCheck.setPreferredSize(new java.awt.Dimension(23, 23));

        int_openConsolidatedInteriorButton.setIcon(new javax.swing.ImageIcon("U:\\NetBeansProjects\\HI-Parser v3\\img\\excel.png")); // NOI18N
        int_openConsolidatedInteriorButton.setEnabled(false);
        int_openConsolidatedInteriorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int_openConsolidatedInteriorButtonActionPerformed(evt);
            }
        });

        int_openCapexReportButton.setIcon(new javax.swing.ImageIcon("U:\\NetBeansProjects\\HI-Parser v3\\img\\excel.png")); // NOI18N
        int_openCapexReportButton.setEnabled(false);
        int_openCapexReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int_openCapexReportButtonActionPerformed(evt);
            }
        });

        int_CILabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        int_CILabel.setText("<html><div align=\"center\">Open Consolidated Interior</div></html>");
        int_CILabel.setEnabled(false);

        int_CELabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        int_CELabel.setText("<html><div align=\"center\">Open CapEx Report</div></html>");
        int_CELabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        int_CELabel.setEnabled(false);

        javax.swing.GroupLayout interiorPanelLayout = new javax.swing.GroupLayout(interiorPanel);
        interiorPanel.setLayout(interiorPanelLayout);
        interiorPanelLayout.setHorizontalGroup(
            interiorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, interiorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(interiorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(int_singleCheck, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(int_uploadCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(int_capexCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(int_generateCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(interiorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(interiorPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(int_SingleReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(interiorPanelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(interiorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(int_CapexReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(int_uploadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(int_ConsolidatedReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(interiorPanelLayout.createSequentialGroup()
                                .addGroup(interiorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(int_CILabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(int_openConsolidatedInteriorButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(interiorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(int_openCapexReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(int_CELabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(interiorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(unitsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(unitsListSP, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        interiorPanelLayout.setVerticalGroup(
            interiorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(interiorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(unitsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(interiorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(interiorPanelLayout.createSequentialGroup()
                        .addGroup(interiorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(int_uploadCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(int_uploadButton, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(interiorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(int_generateCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(int_ConsolidatedReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(interiorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(int_capexCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(int_CapexReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(interiorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(int_openConsolidatedInteriorButton)
                            .addComponent(int_openCapexReportButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(interiorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(int_CILabel)
                            .addComponent(int_CELabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(interiorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(int_singleCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(int_SingleReportButton)))
                    .addComponent(unitsListSP, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Interior", interiorPanel);

        buildingsLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buildingsLabel.setText("Buildings");

        buildingsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        buildingsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buildingsListMouseClicked(evt);
            }
        });
        buildingsListSP.setViewportView(buildingsList);

        ext_uploadButton.setText("Upload Inspection Data");
        ext_uploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ext_uploadButtonActionPerformed(evt);
            }
        });

        ext_ConsolidatedReportButton.setText("Generate Consolidated Report");
        ext_ConsolidatedReportButton.setEnabled(false);
        ext_ConsolidatedReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ext_ConsolidatedReportButtonActionPerformed(evt);
            }
        });

        ext_CapexReportButton.setText("Generate CapEx Report");
        ext_CapexReportButton.setEnabled(false);
        ext_CapexReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ext_CapexReportButtonActionPerformed(evt);
            }
        });

        ext_SingleReportButton.setText("Generate Single Report");
        ext_SingleReportButton.setEnabled(false);
        ext_SingleReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ext_SingleReportButtonActionPerformed(evt);
            }
        });

        ext_uploadCheck.setText(" ");
        ext_uploadCheck.setMaximumSize(new java.awt.Dimension(23, 23));
        ext_uploadCheck.setMinimumSize(new java.awt.Dimension(23, 23));
        ext_uploadCheck.setPreferredSize(new java.awt.Dimension(23, 23));

        ext_generateCheck.setText(" ");
        ext_generateCheck.setMaximumSize(new java.awt.Dimension(23, 23));
        ext_generateCheck.setMinimumSize(new java.awt.Dimension(23, 23));
        ext_generateCheck.setPreferredSize(new java.awt.Dimension(23, 23));

        ext_capexCheck.setText(" ");
        ext_capexCheck.setMaximumSize(new java.awt.Dimension(23, 23));
        ext_capexCheck.setMinimumSize(new java.awt.Dimension(23, 23));
        ext_capexCheck.setPreferredSize(new java.awt.Dimension(23, 23));

        ext_singleCheck.setText(" ");
        ext_singleCheck.setMaximumSize(new java.awt.Dimension(23, 23));
        ext_singleCheck.setMinimumSize(new java.awt.Dimension(23, 23));
        ext_singleCheck.setPreferredSize(new java.awt.Dimension(23, 23));

        ext_openConsolidatedInteriorButton.setIcon(new javax.swing.ImageIcon("U:\\NetBeansProjects\\HI-Parser v3\\img\\excel.png")); // NOI18N
        ext_openConsolidatedInteriorButton.setEnabled(false);
        ext_openConsolidatedInteriorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ext_openConsolidatedInteriorButtonActionPerformed(evt);
            }
        });

        ext_openCapexReportButton.setIcon(new javax.swing.ImageIcon("U:\\NetBeansProjects\\HI-Parser v3\\img\\excel.png")); // NOI18N
        ext_openCapexReportButton.setEnabled(false);
        ext_openCapexReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ext_openCapexReportButtonActionPerformed(evt);
            }
        });

        ext_CILabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ext_CILabel.setText("<html><div align=\"center\">Open Consolidated Exterior</div></html>");
        ext_CILabel.setEnabled(false);

        ext_CELabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ext_CELabel.setText("<html><div align=\"center\">Open CapEx Report</div></html>");
        ext_CELabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        ext_CELabel.setEnabled(false);

        javax.swing.GroupLayout exteriorPanelLayout = new javax.swing.GroupLayout(exteriorPanel);
        exteriorPanel.setLayout(exteriorPanelLayout);
        exteriorPanelLayout.setHorizontalGroup(
            exteriorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exteriorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(exteriorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ext_singleCheck, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(ext_uploadCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ext_capexCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ext_generateCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(exteriorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(exteriorPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ext_SingleReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(exteriorPanelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(exteriorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ext_CapexReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ext_uploadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ext_ConsolidatedReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(exteriorPanelLayout.createSequentialGroup()
                                .addGroup(exteriorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ext_CILabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(ext_openConsolidatedInteriorButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(exteriorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ext_openCapexReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ext_CELabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(exteriorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buildingsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(buildingsListSP, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        exteriorPanelLayout.setVerticalGroup(
            exteriorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exteriorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buildingsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(exteriorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(exteriorPanelLayout.createSequentialGroup()
                        .addGroup(exteriorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ext_uploadCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ext_uploadButton, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(exteriorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ext_generateCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ext_ConsolidatedReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(exteriorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ext_capexCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ext_CapexReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(exteriorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ext_openConsolidatedInteriorButton)
                            .addComponent(ext_openCapexReportButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(exteriorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ext_CILabel)
                            .addComponent(ext_CELabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(exteriorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ext_singleCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ext_SingleReportButton)))
                    .addComponent(buildingsListSP, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Exterior", exteriorPanel);

        fileMenu.setText("File");

        resetMenuItem.setText("Reset");
        resetMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(resetMenuItem);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menu.add(fileMenu);

        helpMenu.setText("Help");

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        guideMenuItem.setText("Open Guide");
        guideMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guideMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(guideMenuItem);

        menu.add(helpMenu);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jTabbedPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**********************/
    /****** INTERIOR ******/
    /**********************/

    /**
     * What happens when you click on the interior upload button
     * Prompts for file input, then initializes the intInput object
     * @param evt
     */
    private void int_uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_int_uploadButtonActionPerformed

        int_uploadCheck.setIcon(new ImageIcon("img/ellipses.png"));
        int_uploadCheck.update(int_uploadCheck.getGraphics());
        updateConsole("[INTERIOR] Selecting file...");

        path = intInput.openWorkbook();

        if (path != null) {

            updateConsole("Done\n");
            int_uploadCheck.setIcon(new ImageIcon("img/check.png"));

            int_ConsolidatedReportButton.setEnabled(true);
            int_CapexReportButton.setEnabled(true);
            updateConsole("[INTERIOR] Importing units...");

            intInput.initInterior();
            intInput.fillUnits();

            unitsList.setModel(populateInteriorList());

            updateConsole("Done\n");

            // close the workbook
            intInput.closeWorkbook();

        } else {
            updateConsole("No file chosen.\n");
            int_uploadCheck.setIcon(null);
        }

    }//GEN-LAST:event_int_uploadButtonActionPerformed

    /**
     * What happens when the INTERIOR Consolidated Report button is pressed
     * Takes the units and parses them through the IntOutput object and creates
     * the Excel report
     * @param evt
     */
    private void int_ConsolidatedReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_int_ConsolidatedReportButtonActionPerformed

        // update graphics
        int_generateCheck.setIcon(new ImageIcon("img/ellipses.png"));
        int_generateCheck.update(int_generateCheck.getGraphics());
        updateConsole("[INTERIOR] Exporting data...");

        // start exporting
        intOutput.setUnits(intInput.getUnits());
        intOutput.outputData();

        // done, update graphics
        int_generateCheck.setIcon(new ImageIcon("img/check.png"));
        updateConsole("Done\n[INTERIOR] File created at: " + folderPath + "\\Interior\n");
        int_openConsolidatedInteriorButton.setEnabled(true);
        int_CILabel.setEnabled(true);
    }//GEN-LAST:event_int_ConsolidatedReportButtonActionPerformed

    /**
     * What happens when you click on the interior capex button
     * Parses through the units and outputs them to the spreadsheet
     * @param evt
     */
    private void int_CapexReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_int_CapexReportButtonActionPerformed
        // capEx
        int_capexCheck.setIcon(new ImageIcon("img/ellipses.png"));
        int_capexCheck.update(int_capexCheck.getGraphics());
        updateConsole("[INTERIOR] Creating CapEx report...");

        intOutput.setUnits(intInput.getUnits());
        intOutput.outputCapEx();

        updateConsole("Done\n[INTERIOR] File created at: " + folderPath + "\\Interior\n");
        int_capexCheck.setIcon(new ImageIcon("img/check.png"));

        int_openCapexReportButton.setEnabled(true);
        int_CELabel.setEnabled(true);
    }//GEN-LAST:event_int_CapexReportButtonActionPerformed

    /**
     * Opens the Internal Consolidated Interior spreadsheet with Excel
     * @param evt
     */
    private void int_openConsolidatedInteriorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_int_openConsolidatedInteriorButtonActionPerformed
        try {
            Desktop.getDesktop().open(intOutput.getConsolidatedFile());
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }//GEN-LAST:event_int_openConsolidatedInteriorButtonActionPerformed

    /**
     * Opens the Internal CapEx spreadsheet with Excel
     * @param evt
     */
    private void int_openCapexReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_int_openCapexReportButtonActionPerformed
        try {
            Desktop.getDesktop().open(new File(intOutput.capexOutputPath));
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }//GEN-LAST:event_int_openCapexReportButtonActionPerformed

    /**
     * What happens when you click on the single report button
     * Opens a modal that says not implemented yet
     * @param evt
     */
    private void int_SingleReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_int_SingleReportButtonActionPerformed
        JOptionPane.showMessageDialog(this, "Single Unit generation for unit " + selectedUnitNum + "\nSingle report generation not implemented yet.", "Single Report Generation", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_int_SingleReportButtonActionPerformed

    /**
     * What happens when an item in the list is selected
     * Enables the button and stores the name of the unit
     * @param evt
     */
    private void unitsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unitsListMouseClicked
        selectedUnitNum = unitsList.getSelectedValue();
        int_SingleReportButton.setEnabled(true);
    }//GEN-LAST:event_unitsListMouseClicked

    /**********************/
    /****** EXTERIOR ******/
    /**********************/

    /**
     * What happens when you click on the exterior upload button
     * Prompts for file input, then initializes the extInput object
     * @param evt
     */
    private void ext_uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ext_uploadButtonActionPerformed
        ext_uploadCheck.setIcon(new ImageIcon("img/ellipses.png"));
        ext_uploadCheck.update(int_uploadCheck.getGraphics());
        updateConsole("[EXTERIOR] Selecting file...");

        path = extInput.openWorkbook();

        if (path != null) {

            updateConsole("Done\n");
            ext_uploadCheck.setIcon(new ImageIcon("img/check.png"));

            ext_ConsolidatedReportButton.setEnabled(true);
            ext_CapexReportButton.setEnabled(true);
            updateConsole("[EXTERIOR] Importing units...");

            extInput.initExterior();
            extInput.fillBuildings();

            buildingsList.setModel(populateExteriorList());

            updateConsole("Done\n");

            // close the workbook
            extInput.closeWorkbook();

        } else {
            updateConsole("No file chosen.\n");
            ext_uploadCheck.setIcon(null);
        }
    }//GEN-LAST:event_ext_uploadButtonActionPerformed

    /**
     * What happens when the EXTERIOR Consolidated Report button is pressed
     * Takes the buildings and parses them through the ExtOutput object and creates
     * the Excel report
     * @param evt
     */
    private void ext_ConsolidatedReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ext_ConsolidatedReportButtonActionPerformed

        // update graphics
        ext_generateCheck.setIcon(new ImageIcon("img/ellipses.png"));
        ext_generateCheck.update(int_generateCheck.getGraphics());
        updateConsole("[EXTERIOR] Exporting data...");

        // start exporting
        extOutput.setBuildings(extInput.getBuildings());
        extOutput.outputData();

        // done, update graphics
        ext_generateCheck.setIcon(new ImageIcon("img/check.png"));
        updateConsole("Done\n[EXTERIOR] File created at: " + folderPath + "\\Exterior\n");
        ext_openConsolidatedInteriorButton.setEnabled(true);
        ext_CILabel.setEnabled(true);
    }//GEN-LAST:event_ext_ConsolidatedReportButtonActionPerformed

    /**
     * What happens when you click on the interior capex button
     * Parses through the units and outputs them to the spreadsheet
     * @param evt
     */
    private void ext_CapexReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ext_CapexReportButtonActionPerformed
        // capEx
        ext_capexCheck.setIcon(new ImageIcon("img/ellipses.png"));
        ext_capexCheck.update(ext_capexCheck.getGraphics());
        updateConsole("[EXTERIOR] Creating CapEx report...");

        extOutput.outputCapEx();

        updateConsole("Done\n[EXTERIOR] File created at: " + folderPath + "\\Exterior\n");
        ext_capexCheck.setIcon(new ImageIcon("img/check.png"));

        ext_openCapexReportButton.setEnabled(true);
        ext_CELabel.setEnabled(true);
    }//GEN-LAST:event_ext_CapexReportButtonActionPerformed

    /**
     * Opens the External Consolidated spreadsheet with Excel
     * @param evt
     */
    private void ext_openConsolidatedInteriorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ext_openConsolidatedInteriorButtonActionPerformed
        try {
            Desktop.getDesktop().open(extOutput.getConsolidatedFile());
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }//GEN-LAST:event_ext_openConsolidatedInteriorButtonActionPerformed

    /**
     * Opens the External CapEx spreadsheet with Excel
     * @param evt
     */
    private void ext_openCapexReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ext_openCapexReportButtonActionPerformed
        try {
            Desktop.getDesktop().open(extOutput.getCapExFile());
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }//GEN-LAST:event_ext_openCapexReportButtonActionPerformed

    /**
     * What happens when you click on the single report button
     * Opens a modal that says not implemented yet
     * @param evt
     */
    private void ext_SingleReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ext_SingleReportButtonActionPerformed
        JOptionPane.showMessageDialog(this, "Single Building generation for unit " + selectedBuildingName + "\nSingle report generation not implemented yet.", "Single Report Generation", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_ext_SingleReportButtonActionPerformed

    /**
     * What happens when an item in the list is selected
     * Enables the button and stores the name of the building
     * @param evt
     */
    private void buildingsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buildingsListMouseClicked
        selectedBuildingName = buildingsList.getSelectedValue();
        ext_SingleReportButton.setEnabled(true);
    }//GEN-LAST:event_buildingsListMouseClicked

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        int_openCapexReportButton.setIcon(new ImageIcon("img/excel.png"));
        int_openConsolidatedInteriorButton.setIcon(new ImageIcon("img/excel.png"));
        ext_openCapexReportButton.setIcon(new ImageIcon("img/excel.png"));
        ext_openConsolidatedInteriorButton.setIcon(new ImageIcon("img/excel.png"));
    }//GEN-LAST:event_formComponentShown

    /**
     * Exits the application
     * @param evt
     */
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    /**
     * Resets the form by closing it then reopening it
     * @param evt
     */
    private void resetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetMenuItemActionPerformed
        this.dispose();
        main(null);
    }//GEN-LAST:event_resetMenuItemActionPerformed

    /**
     * Opens an About dialog
     * @param evt 
     */
    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        JOptionPane.showMessageDialog(this, "Version: 1.0\n© Pat Ripley, 2017", "About HI-Parser", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    /**
     * Opens the Due Diligence Procedure pdf at the Data Consolidation page
     * @param evt 
     */
    private void guideMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guideMenuItemActionPerformed
        try {
            Process p = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Adobe\\Acrobat Reader DC\\Reader\\AcroRd32.exe /A \"page=12\" \"G:/IT - Public Folder/Happy Inspector/Due Diligence Procedure.pdf\"");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }//GEN-LAST:event_guideMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JLabel buildingsLabel;
    private javax.swing.JList<String> buildingsList;
    private javax.swing.JScrollPane buildingsListSP;
    private javax.swing.JTextArea console;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JLabel ext_CELabel;
    private javax.swing.JLabel ext_CILabel;
    private javax.swing.JButton ext_CapexReportButton;
    private javax.swing.JButton ext_ConsolidatedReportButton;
    private javax.swing.JButton ext_SingleReportButton;
    private javax.swing.JLabel ext_capexCheck;
    private javax.swing.JLabel ext_generateCheck;
    private javax.swing.JButton ext_openCapexReportButton;
    private javax.swing.JButton ext_openConsolidatedInteriorButton;
    private javax.swing.JLabel ext_singleCheck;
    private javax.swing.JButton ext_uploadButton;
    private javax.swing.JLabel ext_uploadCheck;
    private javax.swing.JPanel exteriorPanel;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem guideMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel int_CELabel;
    private javax.swing.JLabel int_CILabel;
    private javax.swing.JButton int_CapexReportButton;
    private javax.swing.JButton int_ConsolidatedReportButton;
    private javax.swing.JButton int_SingleReportButton;
    private javax.swing.JLabel int_capexCheck;
    private javax.swing.JLabel int_generateCheck;
    private javax.swing.JButton int_openCapexReportButton;
    private javax.swing.JButton int_openConsolidatedInteriorButton;
    private javax.swing.JLabel int_singleCheck;
    private javax.swing.JButton int_uploadButton;
    private javax.swing.JLabel int_uploadCheck;
    private javax.swing.JPanel interiorPanel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem resetMenuItem;
    private javax.swing.JLabel unitsLabel;
    private javax.swing.JList<String> unitsList;
    private javax.swing.JScrollPane unitsListSP;
    // End of variables declaration//GEN-END:variables
}
