/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PlanetCatalogue;

import javax.swing.JOptionPane;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Mateusz So?tysiak <s195679@student.pg.edu.pl>
 */

//parts of this code were written with the invaluable assistance of:
//chat.openai.com - serialization, deserialization and file storage bit,
//as well as general purpose code, like checking whether string is empty

public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() { //<editor-fold desc="constructor for main window, runs creation code">
        retrieveFileContent();
        initComponents();
        refreshList();
        TextCurrentList.setText(list);
        TextWriteName.setEnabled(true);
        TextWriteMass.setEnabled(true);
        TextWritePeriod.setEnabled(false);
        SpinnerWriteMoon.setEnabled(true);
        TextWriteOrbit.setEnabled(false);
        TextWriteTemp.setEnabled(true);
        ButtonWriteConfirm.setEnabled(false);
        ButtonDelete.setEnabled(false);
    }//</editor-fold>
    
    //<editor-fold desc="variable declarations, DO MODIFY ;P">
    public Container container = new Container();
    public List<Sun> suns = new ArrayList<>();
    public List<Planet> planets = new ArrayList<>();
    public List<Moon> moons = new ArrayList<>();
    public List<String> sunItem = new ArrayList<>();
    public List<String> planetItem = new ArrayList<>();
    public List<String> moonItem = new ArrayList<>();
    public List<String> currentList = new ArrayList<>();
    public String list = new String();
    public boolean edit = false;
    //</editor-fold>
    
    public void retrieveFileContent(){ //<editor-fold desc="retrieves data from file and stores it to containers">
        try (FileInputStream fileIn = new FileInputStream("catalogue.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn)){
            // Read the container from the file
            container = (Container) in.readObject();
            in.close();

            // Access and process the objects from the deserialized container
            for (Object obj : container.objects) {
                if (obj instanceof Sun) {
                    suns.add((Sun) obj);
                    sunItem.add(((Sun) obj).getName());
                    for(Planet subj : ((Sun) obj).Satellites){
                        planets.add(subj);
                    }
                } else if (obj instanceof Planet) {
                    planets.add((Planet) obj);
                    planetItem.add(((Planet) obj).getName());
                    for(Moon subj : ((Planet) obj).Satellites){
                        moons.add(subj);
                    }
                } else if (obj instanceof Moon){
                    moons.add((Moon) obj);
                    moonItem.add(((Moon) obj).getName());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                        "File 'catalogue.ser' could not be opened.", "Warning!",
                        JOptionPane.WARNING_MESSAGE); 
            e.printStackTrace();
        }
    }//</editor-fold>
    
    public void refreshList(){ //<editor-fold desc="updates the lists of celestial bodies">
        //refreszes the contents of suns, planets, moons
        suns.clear();
        moons.clear();
        planets.clear();
        
        for (Object obj : container.objects) {
            if (obj instanceof Sun) {
                suns.add((Sun) obj);
                sunItem.add(((Sun) obj).getName());
                for(Planet subj : ((Sun) obj).Satellites){
                    planets.add(subj);
                    for(Moon moo : ((Planet)subj).Satellites){
                        moons.add(moo);
                    }
                }
            }
        }
//            } else if (obj instanceof Planet) {
//                planets.add((Planet) obj);
//                planetItem.add(((Planet) obj).getName());
//                for(Moon subj : ((Planet) obj).Satellites){
//                    moons.add(subj);
//                }
//            } else if (obj instanceof Moon){
//                moons.add((Moon) obj);
//                moonItem.add(((Moon) obj).getName());
//            }
//    }
        
        StringBuilder listSB = new StringBuilder();
        currentList.clear();
        
        listSB.append("Suns:\n");
        for (Object obj : suns){
            currentList.add(((Sun) obj).getName()+"\n");
            listSB.append(((Sun) obj).getName()+"\n");
        }
        
        listSB.append("Planets:\n");
        for (Object obj : planets){
            currentList.add(((Planet) obj).getName()+"\n");
            listSB.append(((Planet) obj).getName()+"\n");
        }
        
        listSB.append("Moons:\n");
        for (Object obj : moons){
            currentList.add(((Moon) obj).getName()+"\n");
            listSB.append(((Moon) obj).getName()+"\n");
        }
        
        list = listSB.toString();
        TextCurrentList.setText(list);
        ChooseWriteType.setEnabled(true);
        SpinnerWriteMoon.setEnabled(true);
        TextWriteSatNames.setEnabled(true);
        TextWriteTemp.setEnabled(true);
    }//</editor-fold>
    
    public void writeToFile(){ //<editor-fold desc="serializes and writes data from container to file">
            try {
                FileOutputStream fileOut = new FileOutputStream("catalogue.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                
                // Write the container to the file
                out.writeObject(container);
                
                out.close();
                JOptionPane.showMessageDialog(null,
                        "Data has been serialized and stored in file 'catalogue.ser'", "Action executed successfully",
                        JOptionPane.INFORMATION_MESSAGE); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//</editor-fold>
    
    public void resetButtons(){
        TextCurrentList.setText(list);
        ButtonWriteAdd.setEnabled(true);
        ButtonDelete.setEnabled(edit);
        ChooseWriteType.setEnabled(!edit);
        SpinnerWriteMoon.setEnabled(!edit);
        TextWriteSatNames.setEnabled(!edit);
        boolean temp = (ChooseWriteType.getSelectedIndex() == 0);
        TextWriteTemp.setEnabled(temp);
    }
    
    /**<editor-fold desc="who knows what that does">
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        PanelChooseDisp = new javax.swing.JPanel();
        ChooseTypeDisp = new javax.swing.JComboBox<>();
        LabelType = new javax.swing.JLabel();
        ButtonDisp = new javax.swing.JButton();
        ButtonClearDisp = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        TextDispName = new javax.swing.JTextField();
        ButtonEdit = new javax.swing.JButton();
        PanelWrite = new javax.swing.JPanel();
        LabelWriteType = new javax.swing.JLabel();
        ChooseWriteType = new javax.swing.JComboBox<>();
        LabelWriteName = new javax.swing.JLabel();
        TextWriteName = new javax.swing.JFormattedTextField();
        LabelWriteRadius = new javax.swing.JLabel();
        TextWriteRadius = new javax.swing.JFormattedTextField();
        LabelWriteMass = new javax.swing.JLabel();
        TextWriteMass = new javax.swing.JFormattedTextField();
        LabelWriteTest = new javax.swing.JLabel();
        TextWriteTemp = new javax.swing.JFormattedTextField();
        LabelWriteOrbit = new javax.swing.JLabel();
        TextWriteOrbit = new javax.swing.JFormattedTextField();
        LabelPeriodWrite = new javax.swing.JLabel();
        TextWritePeriod = new javax.swing.JFormattedTextField();
        LabelWriteMoons = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        SpinnerWriteMoon = new javax.swing.JSpinner();
        TextWriteSatNames = new javax.swing.JTextField();
        LabelWriteNames = new javax.swing.JLabel();
        ButtonWriteConfirm = new javax.swing.JButton();
        ButtonWriteClear = new javax.swing.JButton();
        ButtonWriteAdd = new javax.swing.JButton();
        ButtonDelete = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        TextWriteParent = new javax.swing.JTextField();
        ExitEdit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextDisp = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        TextCurrentList = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        ButtonSaveToFile = new javax.swing.JButton();
        LabelList = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuActions = new javax.swing.JMenu();
        MenuAbout = new javax.swing.JMenuItem();
        MenuClose = new javax.swing.JMenuItem();

        jScrollPane2.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ChooseTypeDisp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sun", "Planet", "Moon" }));
        ChooseTypeDisp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChooseTypeDispItemStateChanged(evt);
            }
        });

        LabelType.setText("Object type");

        ButtonDisp.setText("Display info");
        ButtonDisp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDispActionPerformed(evt);
            }
        });

        ButtonClearDisp.setText("Clear selection");
        ButtonClearDisp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonClearDispActionPerformed(evt);
            }
        });

        jLabel2.setText("Object name");

        ButtonEdit.setText("Edit object");
        ButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelChooseDispLayout = new javax.swing.GroupLayout(PanelChooseDisp);
        PanelChooseDisp.setLayout(PanelChooseDispLayout);
        PanelChooseDispLayout.setHorizontalGroup(
            PanelChooseDispLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChooseDispLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelChooseDispLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LabelType, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(ChooseTypeDisp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(PanelChooseDispLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(TextDispName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelChooseDispLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonClearDisp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonDisp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))
        );
        PanelChooseDispLayout.setVerticalGroup(
            PanelChooseDispLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelChooseDispLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(PanelChooseDispLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelType)
                    .addComponent(jLabel2)
                    .addComponent(ButtonDisp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelChooseDispLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChooseTypeDisp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextDispName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonClearDisp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonEdit)
                .addGap(7, 7, 7))
        );

        PanelWrite.setPreferredSize(new java.awt.Dimension(295, 295));

        LabelWriteType.setText("Object type");

        ChooseWriteType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sun", "Planet", "Moon" }));
        ChooseWriteType.setToolTipText("Choose object type");
        ChooseWriteType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChooseWriteTypeItemStateChanged(evt);
            }
        });
        ChooseWriteType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChooseWriteTypeActionPerformed(evt);
            }
        });

        LabelWriteName.setText("Name");

        TextWriteName.setText("NoName");
        TextWriteName.setToolTipText("Enter object name");
        TextWriteName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextWriteNameActionPerformed(evt);
            }
        });

        LabelWriteRadius.setText("Radius [km]");

        TextWriteRadius.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        TextWriteRadius.setText("0");
        TextWriteRadius.setToolTipText("Enter object radius in kilometres (decimal value)");
        TextWriteRadius.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        TextWriteRadius.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextWriteRadiusActionPerformed(evt);
            }
        });

        LabelWriteMass.setText("Mass [kg]");

        TextWriteMass.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0.00"))));
        TextWriteMass.setText("0");
        TextWriteMass.setToolTipText("Enter object's mass in kilogrammes (decimal value)");

        LabelWriteTest.setText("Surface temp [°C]");

        TextWriteTemp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0.00"))));
        TextWriteTemp.setText("0");
        TextWriteTemp.setToolTipText("For object of type Sun, enter temperature in degrees centigrade (decimal value)");

        LabelWriteOrbit.setText("Orbit radius [km]");

        TextWriteOrbit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0.00"))));
        TextWriteOrbit.setText("0");
        TextWriteOrbit.setToolTipText("Enter object's orbit radius in kilometres (decimal value)");

        LabelPeriodWrite.setText("Period [days]");

        TextWritePeriod.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0"))));
        TextWritePeriod.setText("0");
        TextWritePeriod.setToolTipText("Enter revolution period in days (integer value)");
        TextWritePeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextWritePeriodActionPerformed(evt);
            }
        });

        LabelWriteMoons.setText("Number of satellites");

        jLabel1.setText("Add a new celestial body or edit an existing one");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        SpinnerWriteMoon.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        SpinnerWriteMoon.setToolTipText("When creating an object you can create its satellites - choose number here");

        TextWriteSatNames.setToolTipText("Enter your object's satellites' names separated with commas");

        LabelWriteNames.setText("Satellite names");

        ButtonWriteConfirm.setText("Confirm");
        ButtonWriteConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonWriteConfirmActionPerformed(evt);
            }
        });

        ButtonWriteClear.setText("Clear");
        ButtonWriteClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonWriteClearActionPerformed(evt);
            }
        });

        ButtonWriteAdd.setText("Add");
        ButtonWriteAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonWriteAddActionPerformed(evt);
            }
        });

        ButtonDelete.setText("Delete");
        ButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDeleteActionPerformed(evt);
            }
        });

        jLabel4.setText("Parent");

        TextWriteParent.setToolTipText("Enter an existing parent's name: Sun's for a Planet, Planet's for a Moon");

        ExitEdit.setText("Exit edit mode");
        ExitEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelWriteLayout = new javax.swing.GroupLayout(PanelWrite);
        PanelWrite.setLayout(PanelWriteLayout);
        PanelWriteLayout.setHorizontalGroup(
            PanelWriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelWriteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelWriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelWriteLayout.createSequentialGroup()
                        .addGroup(PanelWriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelWriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(TextWriteName, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ChooseWriteType, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LabelWriteName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LabelWriteType, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LabelWriteRadius, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TextWriteRadius, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(LabelWriteMass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TextWriteMass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelWriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(TextWriteTemp, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(LabelWriteTest, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelWriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(TextWriteSatNames, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelWriteNames, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SpinnerWriteMoon, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextWritePeriod, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelPeriodWrite, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LabelWriteMoons, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LabelWriteOrbit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TextWriteOrbit, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TextWriteParent))
                        .addContainerGap())
                    .addGroup(PanelWriteLayout.createSequentialGroup()
                        .addGroup(PanelWriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ExitEdit)
                            .addGroup(PanelWriteLayout.createSequentialGroup()
                                .addComponent(ButtonWriteClear)
                                .addGap(18, 18, 18)
                                .addComponent(ButtonWriteAdd)
                                .addGap(18, 18, 18)
                                .addComponent(ButtonWriteConfirm)))
                        .addGap(18, 18, 18)
                        .addComponent(ButtonDelete)
                        .addGap(0, 40, Short.MAX_VALUE))))
        );
        PanelWriteLayout.setVerticalGroup(
            PanelWriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelWriteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addGroup(PanelWriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelWriteLayout.createSequentialGroup()
                        .addComponent(LabelPeriodWrite)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextWritePeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelWriteMoons)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SpinnerWriteMoon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(LabelWriteOrbit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextWriteOrbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelWriteLayout.createSequentialGroup()
                        .addComponent(LabelWriteType)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ChooseWriteType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelWriteName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextWriteName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(PanelWriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelWriteRadius)
                            .addComponent(LabelWriteNames))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelWriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextWriteRadius, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextWriteSatNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelWriteMass)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextWriteMass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelWriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelWriteTest)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelWriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextWriteTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextWriteParent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelWriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelWriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ButtonWriteClear)
                        .addComponent(ButtonWriteAdd))
                    .addGroup(PanelWriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ButtonWriteConfirm)
                        .addComponent(ButtonDelete)))
                .addGap(18, 18, 18)
                .addComponent(ExitEdit)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        TextDisp.setEditable(false);
        TextDisp.setColumns(20);
        TextDisp.setRows(5);
        TextDisp.setText("Remember to always save your work.\nOtherwise it will be lost.\n\nPlanets and moons are considered satellites.\n\nWhen creating an object with satellites,\nthe satellites' default names are their array indices.\n");
        jScrollPane1.setViewportView(TextDisp);

        TextCurrentList.setEditable(false);
        TextCurrentList.setColumns(20);
        TextCurrentList.setRows(5);
        jScrollPane3.setViewportView(TextCurrentList);

        jLabel3.setText("Mateusz Soltysiak 195679 WIMiO Mtr sem. 2");

        ButtonSaveToFile.setText("Save to file");
        ButtonSaveToFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSaveToFileActionPerformed(evt);
            }
        });

        LabelList.setText("List of existing celestial bodies");

        MenuActions.setText("Actions");

        MenuAbout.setText("About");
        MenuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAboutActionPerformed(evt);
            }
        });
        MenuActions.add(MenuAbout);

        MenuClose.setText("Close");
        MenuClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCloseActionPerformed(evt);
            }
        });
        MenuActions.add(MenuClose);

        jMenuBar1.add(MenuActions);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(PanelChooseDisp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonSaveToFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelWrite, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelChooseDisp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ButtonSaveToFile, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelWrite, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(LabelList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//</editor-fold>
    private void MenuAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuAboutActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, 
        "Author: Mateusz Soltysiak 195679", "About Programme",
        JOptionPane.INFORMATION_MESSAGE); 
    }//GEN-LAST:event_MenuAboutActionPerformed

    private void MenuCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCloseActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(null, 
        "Are you sure you want to exit the program?", "Exit Programme",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_MenuCloseActionPerformed

    private void ButtonClearDispActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonClearDispActionPerformed
        // TODO add your handling code here:
        ChooseTypeDisp.setSelectedIndex(0);
        TextDisp.setText("""
                         Remember to always save your work.
                         Otherwise it will be lost.
                         
                         Planets and moons are considered satellites.
                         
                         When creating an object with satellites,
                         the satellites' default names are their array indices.
                         
                         Removing satellites needs to be done one by one.
                         
                         """);
        TextDispName.setText("");
        resetButtons();
    }//GEN-LAST:event_ButtonClearDispActionPerformed

    private void ButtonDispActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDispActionPerformed
        // TODO add your handling code here:
        List<String> text = new ArrayList<>();
        
        refreshList();
        
        switch(ChooseTypeDisp.getSelectedIndex()){
            case 0 -> {
                //Suns
                for(Sun obj : suns){
                    if(obj.getName() == null ? TextDispName.getText() == null : obj.getName().equals(TextDispName.getText())){
                        text.add(obj.getData());
                        text.add(obj.getSatellites());
                    } 
                }
            }
            case 1 -> {
                //Planets
                for(Planet obj : planets){
                    if(obj.getName() == null ? TextDispName.getText() == null : obj.getName().equals(TextDispName.getText())){
                        text.add(obj.getData());
                        text.add(obj.getSatellites());
                    }
                }
            }
            case 2 -> {
                //Moons
                for(Moon obj : moons){
                    if(obj.getName() == null ? TextDispName.getText() == null : obj.getName().equals(TextDispName.getText())){
                        text.add(obj.getData());
                    }
                }
            }
        }
        
        String result = String.join("\n", text);
        if("".equals(result)){
            result = "The object you are trying to retrieve information about is not on the list.\nCheck spelling!";
        }
        TextDisp.setText(result);
        
    }//GEN-LAST:event_ButtonDispActionPerformed

    private void ChooseTypeDispItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChooseTypeDispItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChooseTypeDispItemStateChanged

    private void ButtonWriteAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonWriteAddActionPerformed
        // TODO add your handling code here:
        switch(ChooseWriteType.getSelectedIndex()){
            case 0 -> {
                //check whether name occurs only once
                int count = 0;
                for(Sun obj : suns){
                    if(obj.getName() == null ? TextWriteName.getText() == null : obj.getName().equals(TextWriteName.getText())){
                        count++;
                    }
                }
                if(count < 1){
                Sun newSun = new Sun(TextWriteName.getText());
                newSun.setRadius(Double.parseDouble(TextWriteRadius.getText()));
                newSun.setMass(Double.parseDouble(TextWriteMass.getText()));
                newSun.setTemp(Double.parseDouble(TextWriteTemp.getText()));
                try {
                    SpinnerWriteMoon.commitEdit();
                } catch ( java.text.ParseException e){}
                int value = (Integer) SpinnerWriteMoon.getValue();
                newSun.setSatNum(value);
                newSun.setSatellites(value,TextWriteSatNames.getText());
                container.addObject(newSun);
                }else{
                    JOptionPane.showMessageDialog(null, 
        "You tried to save a Sun under a pre-existing name.\nEnter a different name and try again.", "Warning!",
        JOptionPane.WARNING_MESSAGE);
                }
            }
            case 1 -> {
                
                //check whether name occurs only once
                int count = 0;
                for(Planet obj : planets){
                    if(obj.getName() == null ? TextWriteName.getText() == null : obj.getName().equals(TextWriteName.getText())){
                        count++;
                    }
                }
                
                boolean exists = false;
                Sun parent = new Sun();
                for(Sun obj : suns){
                    if(obj.getName().equals(TextWriteParent.getText())){
                        exists = true;
                        parent = obj;
                        break;
                    }
                }
                if(exists){
                    if(count < 1){
                Planet newPlanet = new Planet(TextWriteName.getText(),parent);
                newPlanet.setParent(parent);
                parent.addSatellite(newPlanet);
                newPlanet.setRadius(Double.parseDouble(TextWriteRadius.getText()));
                newPlanet.setMass(Double.parseDouble(TextWriteMass.getText()));
                newPlanet.setOrbit(Double.parseDouble(TextWriteOrbit.getText()));
                newPlanet.setPeriod(Integer.parseInt(TextWritePeriod.getText()));
                try {
                    SpinnerWriteMoon.commitEdit();
                } catch ( java.text.ParseException e){}
                int value = (Integer) SpinnerWriteMoon.getValue();
                newPlanet.setMoon(value);
                newPlanet.setSatellites(value,TextWriteSatNames.getText());
                container.addObject(newPlanet);
                    }else{
                        JOptionPane.showMessageDialog(null, 
        "You tried to save a Planet with a pre-existing name.\nEnter a different name and try again.", "Warning!",
        JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, 
        "You tried to create a Planet with no existing parent.\nEnter an existing Sun's name and try again.", "Warning!",
        JOptionPane.WARNING_MESSAGE); 
                }
            }
            case 2 -> {
                
                //check whether name occurs only once
                int count = 0;
                for(Moon obj : moons){
                    if(obj.getName() == null ? TextWriteName.getText() == null : obj.getName().equals(TextWriteName.getText())){
                        count++;
                    }
                }
                
                boolean exists = false;
                Planet parent = new Planet();
                for(Planet obj : planets){
                    if(obj.getName().equals(TextWriteParent.getText())){
                        exists = true;
                        parent = obj;
                        break;
                    }
                }
                if(exists){
                    if(count < 1){
                Moon newMoon = new Moon(TextWriteName.getText(),parent);
                newMoon.setParent(parent);
                parent.addSatellite(newMoon);
                newMoon.setRadius(Double.parseDouble(TextWriteRadius.getText()));
                newMoon.setMass(Double.parseDouble(TextWriteMass.getText()));
                newMoon.setOrbit(Double.parseDouble(TextWriteOrbit.getText()));
                newMoon.setPeriod(Integer.parseInt(TextWritePeriod.getText()));
                container.addObject(newMoon);
                    }else{
                        JOptionPane.showMessageDialog(null, 
        "You tried to save a Moon with a pre-existing name.\nEnter a different name and try again.", "Warning!",
        JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, 
        "You tried to create a Moon with no existing parent.\nEnter an existing Planet's name and try again.", "Warning!",
        JOptionPane.WARNING_MESSAGE); 
                }
            }
        }
        refreshList();
    }//GEN-LAST:event_ButtonWriteAddActionPerformed

    private void ButtonWriteClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonWriteClearActionPerformed
        // TODO add your handling code here:
        ChooseWriteType.setSelectedIndex(0);
        TextWriteName.setText("NoName");
        TextWriteRadius.setText("0");
        TextWriteMass.setText("0");
        TextWritePeriod.setText("0");
        SpinnerWriteMoon.setValue(0);
        TextWriteOrbit.setText("0");
        TextWriteTemp.setText("0");
        TextWriteName.setEnabled(true);
        TextWriteMass.setEnabled(true);
        TextWritePeriod.setEnabled(false);
        SpinnerWriteMoon.setEnabled(true);
        TextWriteOrbit.setEnabled(false);
        TextWriteTemp.setEnabled(true);
        resetButtons();
    }//GEN-LAST:event_ButtonWriteClearActionPerformed

    private void ButtonWriteConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonWriteConfirmActionPerformed
        // TODO add your handling code here:
        switch(ChooseWriteType.getSelectedIndex()){
            case 0 -> {
                //Suns
                
                //check whether name occurs only once
                int count = 0;
                for(Sun obj : suns){
                    if(obj.getName() == null ? TextWriteName.getText() == null : obj.getName().equals(TextWriteName.getText())){
                        count++;
                    }
                }
                if(count == 1){
                for(Sun obj : suns){
                    if((obj.getName() == null ? TextDispName.getText() == null : obj.getName().equals(TextDispName.getText())) && count == 1){
                        obj.setName(TextWriteName.getText());
                        obj.setMass(Double.parseDouble(TextWriteMass.getText()));
                        obj.setRadius(Double.parseDouble(TextWriteRadius.getText()));
                        obj.setTemp(Double.parseDouble(TextWriteTemp.getText()));
                    } 
                }
                }else{
                    JOptionPane.showMessageDialog(null, 
        "You tried to save a Sun under a pre-existing name.\nEnter a different name and try again.", "Warning!",
        JOptionPane.WARNING_MESSAGE);
                }
            }
            case 1 -> {
                //Planets
                
                //check whether name occurs only once
                int count = 0;
                for(Planet obj : planets){
                    if(obj.getName() == null ? TextWriteName.getText() == null : obj.getName().equals(TextWriteName.getText())){
                        count++;
                    }
                }
                
                //check whether parent existent
                boolean exists = false;
                Sun parent = new Sun();
                for(Sun obj : suns){
                    if(obj.getName().equals(TextWriteParent.getText())){
                        exists = true;
                        parent = obj;
                        break;
                    }
                }
                if(exists){
                    if(count == 1){
                for(Planet obj : planets){
                    if(obj.getName() == null ? TextDispName.getText() == null : obj.getName().equals(TextDispName.getText())){
                        obj.setName(TextWriteName.getText());
                        obj.setParent(parent);
                        obj.setMass(Double.parseDouble(TextWriteMass.getText()));
                        obj.setRadius(Double.parseDouble(TextWriteRadius.getText()));
                        obj.setPeriod(Integer.parseInt(TextWritePeriod.getText()));
                        obj.setOrbit(Double.parseDouble(TextWriteOrbit.getText()));
                    }
                }
                    }else{
                        JOptionPane.showMessageDialog(null, 
        "You tried to save a Planet with a pre-existing name.\nEnter a different name and try again.", "Warning!",
        JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, 
        "You tried to save a Planet with non-existent parent.\nEnter an existing Sun's name and try again.", "Warning!",
        JOptionPane.WARNING_MESSAGE); 
                }
            }
            case 2 -> {
                //Moons
                
                //check whether name occurs only once
                int count = 0;
                for(Moon obj : moons){
                    if(obj.getName() == null ? TextWriteName.getText() == null : obj.getName().equals(TextWriteName.getText())){
                        count++;
                    }
                }
                boolean exists = false;
                Planet parent = new Planet();
                for(Planet obj : planets){
                    if(obj.getName().equals(TextWriteParent.getText())){
                        exists = true;
                        parent = obj;
                        break;
                    }
                }
                if(exists){
                    if(count == 1){
                for(Moon obj : moons){
                    if(obj.getName() == null ? TextDispName.getText() == null : obj.getName().equals(TextDispName.getText())){
                        obj.setName(TextWriteName.getText());
                        obj.setMass(Double.parseDouble(TextWriteMass.getText()));
                        obj.setRadius(Double.parseDouble(TextWriteRadius.getText()));
                        obj.setPeriod(Integer.parseInt(TextWritePeriod.getText()));
                        obj.setOrbit(Double.parseDouble(TextWriteOrbit.getText()));
                    }
                }
                    }else{
                        JOptionPane.showMessageDialog(null, 
        "You tried to save a Moon with a pre-existing name.\nEnter a different name and try again.", "Warning!",
        JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, 
        "You tried to save a Moon with non-existent parent.\nEnter an existing Planet's name and try again.", "Warning!",
        JOptionPane.WARNING_MESSAGE); 
                }
            }
        }
        refreshList();
        ChooseWriteType.setEnabled(true);
        resetButtons();
//        ButtonWriteConfirm.setEnabled(false);
//        ButtonDelete.setEnabled(false);
    }//GEN-LAST:event_ButtonWriteConfirmActionPerformed
//<editor-fold defaultstate="collapsed" desc=" Unused ActionPerformed ">
    private void TextWritePeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextWritePeriodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextWritePeriodActionPerformed

    private void TextWriteRadiusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextWriteRadiusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextWriteRadiusActionPerformed

    private void TextWriteNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextWriteNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextWriteNameActionPerformed

    private void ChooseWriteTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChooseWriteTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChooseWriteTypeActionPerformed
//</editor-fold>
    private void ChooseWriteTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChooseWriteTypeItemStateChanged
        // TODO add your handling code here:
        switch (ChooseWriteType.getSelectedIndex()){
            case 0 -> {
                TextWriteName.setEnabled(true);
                TextWriteMass.setEnabled(true);
                TextWritePeriod.setEnabled(false);
                SpinnerWriteMoon.setEnabled(true);
                TextWriteOrbit.setEnabled(false);
                TextWriteTemp.setEnabled(true);
                TextWriteSatNames.setEnabled(true);
            }
            case 1 -> {
                TextWriteName.setEnabled(true);
                TextWriteMass.setEnabled(true);
                TextWritePeriod.setEnabled(true);
                SpinnerWriteMoon.setEnabled(true);
                TextWriteOrbit.setEnabled(true);
                TextWriteTemp.setEnabled(false);
                TextWriteSatNames.setEnabled(true);
            }
            case 2 -> {
                TextWriteName.setEnabled(true);
                TextWriteMass.setEnabled(true);
                TextWritePeriod.setEnabled(true);
                SpinnerWriteMoon.setEnabled(false);
                TextWriteOrbit.setEnabled(true);
                TextWriteTemp.setEnabled(false);
                TextWriteSatNames.setEnabled(false);
            }
        }
    }//GEN-LAST:event_ChooseWriteTypeItemStateChanged

    private void ButtonSaveToFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSaveToFileActionPerformed
        // TODO add your handling code here:
        writeToFile();
    }//GEN-LAST:event_ButtonSaveToFileActionPerformed

    private void ButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonEditActionPerformed
        // TODO add your handling code here:
        
        edit = true;
        refreshList();
        
        switch(ChooseTypeDisp.getSelectedIndex()){
            case 0 -> {
                //Suns
                for(Sun obj : suns){
                    if(obj.getName() == null ? TextDispName.getText() == null : obj.getName().equals(TextDispName.getText())){
                        //Enable appropriate form fields
                        ChooseWriteType.setSelectedIndex(0);
                        TextWriteSatNames.setEnabled(false);
                        SpinnerWriteMoon.setEnabled(false);
                        
                        //Put data into fields
                        TextWriteName.setText(obj.getName());
                        TextWriteRadius.setText(Double.toString(obj.getRadius()));
                        TextWriteMass.setText(Double.toString(obj.getMass()));
                        SpinnerWriteMoon.setValue(obj.SatNum);
                        TextWriteSatNames.setText(obj.getSatellites());
                        TextWriteTemp.setText(Double.toString(obj.getTemp()));
                    } 
                }
            }
            case 1 -> {
                //Planets
                for(Planet obj : planets){
                    if(obj.getName() == null ? TextDispName.getText() == null : obj.getName().equals(TextDispName.getText())){
                        //Enable
                        ChooseWriteType.setSelectedIndex(1);
                        TextWriteTemp.setEnabled(false);
                        TextWriteSatNames.setEnabled(false);
                        SpinnerWriteMoon.setEnabled(false);
                        
                        //Put
                        TextWriteName.setText(obj.getName());
                        TextWriteRadius.setText(Double.toString(obj.getRadius()));
                        TextWriteMass.setText(Double.toString(obj.getMass()));
                        SpinnerWriteMoon.setValue(obj.SatNum);
                        TextWriteSatNames.setText(obj.getSatellites());
                        TextWritePeriod.setText(Integer.toString(obj.getPeriod()));
                        TextWriteOrbit.setText(Double.toString(obj.getOrbit()));
                        TextWriteParent.setText(obj.getParent());
                    }
                }
            }
            case 2 -> {
                //Moons
                for(Moon obj : moons){
                    if(obj.getName() == null ? TextDispName.getText() == null : obj.getName().equals(TextDispName.getText())){
                        //Enable
                        ChooseWriteType.setSelectedIndex(2);
                        TextWriteTemp.setEnabled(false);
                        TextWriteSatNames.setEnabled(false);
                        SpinnerWriteMoon.setEnabled(false);
                        
                        //Put
                        TextWriteName.setText(obj.getName());
                        TextWriteRadius.setText(Double.toString(obj.getRadius()));
                        TextWriteMass.setText(Double.toString(obj.getMass()));
                        SpinnerWriteMoon.setValue(obj.SatNum);
                        TextWritePeriod.setText(Integer.toString(obj.getPeriod()));
                        TextWriteOrbit.setText(Double.toString(obj.getOrbit()));
                        TextWriteParent.setText(obj.getParent());
                    }
                }
            }
        }
        
        ChooseWriteType.setEnabled(false);
        ButtonWriteConfirm.setEnabled(true);
        ButtonDelete.setEnabled(true);
    }//GEN-LAST:event_ButtonEditActionPerformed

    private void ButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDeleteActionPerformed
        // TODO add your handling code here:
        Object deletion = new Object();
        switch(ChooseWriteType.getSelectedIndex()){
            case 0 -> {
                //Suns
                for(Sun obj : suns){
                    if(obj.getName() == null ? TextDispName.getText() == null : obj.getName().equals(TextDispName.getText())){
                        deletion = obj;
                    } 
                }
                suns.remove((Sun)deletion);
                container.objects.remove(deletion);
            }
            case 1 -> {
                //Planets
                for(Planet obj : planets){
                    if(obj.getName() == null ? TextDispName.getText() == null : obj.getName().equals(TextDispName.getText())){
                        deletion = obj;
                    }
                }
                planets.remove((Planet)deletion);
                container.objects.remove(deletion);
                for(Sun obj : suns){
                    obj.remSatellite((Planet) deletion);
                }
            }
            case 2 -> {
                //Moons
                for(Moon obj : moons){
                    if(obj.getName() == null ? TextDispName.getText() == null : obj.getName().equals(TextDispName.getText())){
                       deletion = obj;
                    }
                }
                moons.remove((Moon)deletion);
                container.objects.remove(deletion);
                for(Planet obj : planets){
                    obj.remSatellite((Moon) deletion);
                }
            }
        }
        refreshList();
        ChooseWriteType.setEnabled(true);
        ButtonWriteConfirm.setEnabled(false);
        ButtonDelete.setEnabled(false);
        
    }//GEN-LAST:event_ButtonDeleteActionPerformed

    private void ExitEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitEditActionPerformed
        // TODO add your handling code here:
        edit = false;
        ButtonWriteConfirm.setEnabled(edit);
        ButtonDelete.setEnabled(edit);
        resetButtons();
    }//GEN-LAST:event_ExitEditActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonClearDisp;
    private javax.swing.JButton ButtonDelete;
    private javax.swing.JButton ButtonDisp;
    private javax.swing.JButton ButtonEdit;
    private javax.swing.JButton ButtonSaveToFile;
    private javax.swing.JButton ButtonWriteAdd;
    private javax.swing.JButton ButtonWriteClear;
    private javax.swing.JButton ButtonWriteConfirm;
    private javax.swing.JComboBox<String> ChooseTypeDisp;
    private javax.swing.JComboBox<String> ChooseWriteType;
    private javax.swing.JButton ExitEdit;
    private javax.swing.JLabel LabelList;
    private javax.swing.JLabel LabelPeriodWrite;
    private javax.swing.JLabel LabelType;
    private javax.swing.JLabel LabelWriteMass;
    private javax.swing.JLabel LabelWriteMoons;
    private javax.swing.JLabel LabelWriteName;
    private javax.swing.JLabel LabelWriteNames;
    private javax.swing.JLabel LabelWriteOrbit;
    private javax.swing.JLabel LabelWriteRadius;
    private javax.swing.JLabel LabelWriteTest;
    private javax.swing.JLabel LabelWriteType;
    private javax.swing.JMenuItem MenuAbout;
    private javax.swing.JMenu MenuActions;
    private javax.swing.JMenuItem MenuClose;
    private javax.swing.JPanel PanelChooseDisp;
    private javax.swing.JPanel PanelWrite;
    private javax.swing.JSpinner SpinnerWriteMoon;
    private javax.swing.JTextArea TextCurrentList;
    private javax.swing.JTextArea TextDisp;
    private javax.swing.JTextField TextDispName;
    private javax.swing.JFormattedTextField TextWriteMass;
    private javax.swing.JFormattedTextField TextWriteName;
    private javax.swing.JFormattedTextField TextWriteOrbit;
    private javax.swing.JTextField TextWriteParent;
    private javax.swing.JFormattedTextField TextWritePeriod;
    private javax.swing.JFormattedTextField TextWriteRadius;
    private javax.swing.JTextField TextWriteSatNames;
    private javax.swing.JFormattedTextField TextWriteTemp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
