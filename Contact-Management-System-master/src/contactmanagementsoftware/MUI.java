/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactmanagementsoftware;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ritz619
 */
public class MUI extends javax.swing.JFrame implements ObserverSubject {

    /**
     * Creates new form MUI
     */
    private MUI mg;
    private ArrayList<ArrayList<Acquaintances>> a;
    private ArrayList<ArrayList<Acquaintances>> temp;
    private int x;
    private int num;
    private boolean flag;
    private boolean dflag;
    private String op;
    private String str;
    private ArrayList<Observer> observers = new ArrayList<>();
    private static MUI instance;
    private String buttonState = "";
    AcquaintancesFactory af = new AcquaintancesFactory();

    public void setMg(MUI mg) {
        this.mg = mg;
    }

    public void setA(ArrayList<ArrayList<Acquaintances>> a) {
        this.a = a;
    }

    public void setDescription() {
        name.setText("");
        mobile.setText("");
        email.setText("");
        one.setText("");
        two.setText("");
        three.setText("");
        if (!dflag) {
            name.setEditable(true);
            mobile.setEditable(true);
            email.setEditable(true);
            one.setEditable(true);
            two.setEditable(true);
            three.setEditable(true);
        }
        if (flag) {
            op = "Add";
        } else {
            op = "Edit";
        }
        if (!flag) {
            jButton10.setText("Save");
            Acquaintances e = a.get(x).get(num);
            // Set text for basic information
            name.setText(e.getName());
            mobile.setText(e.getMobileNo());
            email.setText(e.getEmail());
            //Execute Strategy
            e.executeStrategy(e);
        }
        jButton10.setVisible(true);
        jButton11.setVisible(true);
        if (flag) {
            jButton10.setText("Add");
        }
        switch (x) {
            case 0:
                two.setVisible(true);
                three.setVisible(true);
                jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, op + " Personal Friends Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DialogInput", 1, 16)));
                jLabel7.setText("Specific Events:");
                jLabel8.setText("First Acquaintance Context:");
                jLabel9.setVisible(true);
                jLabel3.setVisible(true);
                jLabel8.setVisible(true);
                jLabel7.setVisible(true);
                jScrollPane5.setVisible(true);
                jScrollPane4.setVisible(true);
                jLabel9.setText("<html>First Acquaintance Date:<br>(dd/mm/yyyy)</html>");
                break;
            case 1:
                jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, op + " Relatives Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DialogInput", 1, 16)));
                jLabel7.setText("<html>Relatives Birthday:<br> (dd/mm/yyyy)</html>");
                jLabel8.setVisible(true);
                jLabel7.setVisible(true);
                two.setVisible(true);
                jLabel8.setText("<html>Last Date met:<br> (dd/mm/yyyy)</html>");
                jLabel9.setVisible(false);
                three.setVisible(false);
                jScrollPane4.setVisible(true);
                jScrollPane5.setVisible(false);
                break;
            case 2:
                jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, op + " Professional Friends Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DialogInput", 1, 16)));
                jLabel7.setText("Common Interests: ");
                jLabel7.setVisible(true);
                jLabel8.setVisible(false);
                two.setVisible(false);
                jScrollPane4.setVisible(false);
                jLabel9.setVisible(false);
                three.setVisible(false);
                jScrollPane5.setVisible(false);
                break;
            case 3:
                jScrollPane5.setVisible(true);
                jScrollPane4.setVisible(true);
                two.setVisible(true);
                three.setVisible(true);
                jLabel7.setVisible(true);
                jLabel8.setVisible(true);
                jLabel9.setVisible(true);
                jLabel7.setText("First meeting time & location:");
                jLabel8.setText("First meeting CIrcumstances:");
                jLabel9.setText("Other useful information:");
                break;
            default:
                break;
        }
        if (dflag) {
            name.setEditable(false);
            mobile.setEditable(false);
            email.setEditable(false);
            one.setEditable(false);
            two.setEditable(false);
            three.setEditable(false);
            jButton10.setText("Back to main menu");
            jButton11.setVisible(false);
            jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Display Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DialogInput", 1, 16)));
        }
    }

    private MUI() {
        initComponents();
        String[] columnNames = {"S.No", "Name", "Mobile", " Email"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        jXTable1.setModel(model);
        setUpTableData();

        //Adding Observer to the ObserverList
        addObserver(new ButtonListener(this));
    }

    public static synchronized MUI getInstance() {
        if (instance == null) {
            instance = new MUI();
        }
        return instance;
    }

    public final void setUpTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) jXTable1.getModel();
        tableModel.setRowCount(0);
        ArrayList<Acquaintances> list;
        try {
            list = a.get(jList1.getSelectedIndex());
        } catch (Exception e) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            String[] data = new String[4];
            data[0] = Integer.toString(i + 1);
            data[1] = list.get(i).getName();
            data[2] = list.get(i).getMobileNo();
            data[3] = list.get(i).getEmail();
            tableModel.addRow(data);
        }
        jXTable1.setModel(tableModel);
        tableModel.fireTableDataChanged();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        details = new javax.swing.JTextPane();
        jButton9 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        two = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        three = new javax.swing.JTextArea();
        jButton10 = new javax.swing.JButton();
        mobile = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        one = new javax.swing.JTextArea();
        jButton11 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jLabel2.setFont(new java.awt.Font("Ubuntu Medium", 0, 20)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("<html><u>Contact Management System</u></html>");

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Exit");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Personal Friends", "Relatives", "Professional Friends", "Casual Acquaintances" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jXTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "S.No", "Name", "Mobile No", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jXTable1);

        jLabel1.setFont(new java.awt.Font("Ubuntu Medium", 0, 17)); // NOI18N
        jLabel1.setText("Select Category:");

        jLabel3.setFont(new java.awt.Font("Ubuntu Medium", 0, 17)); // NOI18N
        jLabel3.setText("Details:");

        jButton5.setText("Edit");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("View full detail");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Read from file");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Save as file");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(jButton5)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jButton8))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton6)
                        .addComponent(jButton7)))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, "card2");

        details.setEditable(false);
        jScrollPane3.setViewportView(details);

        jButton9.setText("Back to main menu");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(jButton9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton9)
                .addGap(21, 21, 21))
        );

        getContentPane().add(jPanel2, "card3");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Casual Acquaintance Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DialogInput", 1, 16))); // NOI18N

        jLabel4.setText("Name:");

        jLabel5.setText("Mobile No:");

        jLabel6.setText("Email:");

        jLabel7.setText("First meeting time & location:");

        jLabel8.setText("First meeting CIrcumstances:");

        jLabel9.setText("Other useful information:");

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        two.setColumns(20);
        two.setRows(5);
        two.setAutoscrolls(false);
        jScrollPane4.setViewportView(two);

        three.setColumns(20);
        three.setRows(5);
        jScrollPane5.setViewportView(three);

        jButton10.setText("Add");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        one.setColumns(20);
        one.setRows(5);
        jScrollPane6.setViewportView(one);

        jButton11.setText("Cancel");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mobile, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                        .addGap(132, 132, 132)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(mobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton11))
                .addGap(3, 3, 3))
        );

        getContentPane().add(jPanel3, "card4");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        javax.swing.JButton button = (javax.swing.JButton) evt.getSource();
        System.out.println(button.getText());
//        Add button
        setButtonState("Add");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        javax.swing.JButton button = (javax.swing.JButton) evt.getSource();
        System.out.println(button.getText());
//        Delete button
        setButtonState("Delete");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        javax.swing.JButton button = (javax.swing.JButton) evt.getSource();
//        View full detail button
        System.out.println(button.getText());
        setButtonState("Search");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        javax.swing.JButton button = (javax.swing.JButton) evt.getSource();
        System.out.println(button.getText());
//        Exit button
        setButtonState("Exit");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        setUpTableData();
    }//GEN-LAST:event_jList1ValueChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        javax.swing.JButton button = (javax.swing.JButton) evt.getSource();
        System.out.println(button.getText());
//        Edit Button
        setButtonState("Edit");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        javax.swing.JButton button = (javax.swing.JButton) evt.getSource();
//        View full detail button
        System.out.println(button.getText());
        setButtonState("View");
    }//GEN-LAST:event_jButton6ActionPerformed

    public void runn() {
        String s = "<html> <b>Search results:</b><br>Found!<br><br>Acquaintance Details: <br>";
        int j = 0;
        for (int i = 0; i < a.get(0).size(); i++) {
            if (a.get(0).get(i).getName().matches(str)) {
                j++;
                PersonalFriends perF = (PersonalFriends) a.get(0).get(i);
                if (j == 1) {
                    s = s.concat("<br>I. Personal Friends<br>");
                }
                s = s.concat(j + ". Name: " + perF.getName() + "<br>");
                s = s.concat("Mobile No: " + perF.getMobileNo() + "<br>");
                s = s.concat("Email: " + perF.getEmail() + "<br>");
                s = s.concat("Specific events: " + perF.getThirdInfo() + "<br>");
                s = s.concat("First Acquaintance context: " + perF.getFirstInfo() + "<br>");
                s = s.concat("First Acquaintance date: " + perF.getSecondInfo() + "<br>");
            }
        }
        j = 0;
        for (int i = 0; i < a.get(1).size(); i++) {
            if (a.get(1).get(i).getName().matches(str)) {
                j++;
                Relatives rel = (Relatives) a.get(1).get(i);
                if (j == 1) {
                    s = s.concat("<br>II. Relatives<br>");
                }
                s = s.concat(j + ". Name: " + rel.getName() + "<br>");
                s = s.concat("Mobile No: " + rel.getMobileNo() + "<br>");
                s = s.concat("Email: " + rel.getEmail() + "<br>");
                s = s.concat("Relatives Birthday: " + rel.getFirstInfo() + "<br>");
                s = s.concat("Last met date: " + rel.getSecondInfo() + "<br>");
            }
        }
        j = 0;
        for (int i = 0; i < a.get(2).size(); i++) {
            if (a.get(2).get(i).getName().matches(str)) {
                j++;
                ProfessionalFriends proF = (ProfessionalFriends) a.get(2).get(i);
                if (j == 1) {
                    s = s.concat("<br>III. Professional Friends<br>");
                }
                s = s.concat(j + ". Name: " + proF.getName() + "<br>");
                s = s.concat("Mobile No: " + proF.getMobileNo() + "<br>");
                s = s.concat("Email: " + proF.getEmail() + "<br>");
                s = s.concat("Common Interests: " + proF.getFirstInfo() + "<br>");
            }
        }
        j = 0;
        for (int i = 0; i < a.get(3).size(); i++) {
            if (a.get(3).get(i).getName().matches(str)) {
                j++;
                CasualAcquaintances ca = (CasualAcquaintances) a.get(3).get(i);
                if (j == 1) {
                    s = s.concat("<br>IV. Casual Acquaintances<br>");
                }
                s = s.concat(j + ". Name: " + ca.getName() + "<br>");
                s = s.concat("Mobile No: " + ca.getMobileNo() + "<br>");
                s = s.concat("Email: " + ca.getEmail() + "<br>");
                s = s.concat("First met location & time: " + ca.getFirstInfo() + "<br>");
                s = s.concat("First met circumstances: " + ca.getSecondInfo() + "<br>");
                s = s.concat("Other useful information: " + ca.getThirdInfo() + "<br>");
            }
        }
        if (s.matches("<html> <b>Search results:</b><br>Found!<br><br>Acquaintance Details: <br>")) {
            s = "<html>No result found</html>";
        } else {
            s = s.concat("</html>");
        }
        details.setText(s);
    }

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        javax.swing.JButton button = (javax.swing.JButton) evt.getSource();
//        Read from file button
        System.out.println(button.getText());
        setButtonState("Read");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        javax.swing.JButton button = (javax.swing.JButton) evt.getSource();
//        Save as file button
        System.out.println(button.getText());
        setButtonState("Save");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        javax.swing.JButton button = (javax.swing.JButton) evt.getSource();
        System.out.println(button.getText());
        setButtonState("jButton9");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    public boolean MobileNoChecker(String str) {
        int x;
        if (str.isEmpty() || str.length() < 6 || str.length() > 15) {
            return false;
        }
        for (int j = 0; j < str.length(); j++) {
            x = (int) str.charAt(j);
            if (x < 48 || x > 57) {
                return false;
            }
        }
        return true;
    }

    public boolean validDate(String Date) {
        String pattern = "[0-3][0-9]/[0-1][0-9]/[0-9]{4}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(Date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (!m.find()) {
            JOptionPane.showMessageDialog(mg, "Enter a valid date");
            return false;
        } else {
            return true;
        }
    }

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        javax.swing.JButton button = (javax.swing.JButton) evt.getSource();
        System.out.println(button.getText());
        setButtonState("jButton10");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        javax.swing.JButton button = (javax.swing.JButton) evt.getSource();
        System.out.println(button.getText());
        setButtonState("jButton11");
    }//GEN-LAST:event_jButton11ActionPerformed

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
            java.util.logging.Logger.getLogger(MUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new MUI().setVisible(true);
                getInstance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane details;
    private javax.swing.JTextField email;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private org.jdesktop.swingx.JXTable jXTable1;
    private javax.swing.JTextField mobile;
    private javax.swing.JTextField name;
    private javax.swing.JTextArea one;
    private javax.swing.JTextArea three;
    private javax.swing.JTextArea two;
    // End of variables declaration//GEN-END:variables

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

//    perform(String action) runs all the action based on the buttons clicked (Observer design pattern)
    @Override
    public void perform(String action) {
        int index;
        int tindex;
        String s;
        switch (action) {
            case "Add":
//                jButton1ActionPerformed
//                Action when add button is clicked
                index = jList1.getSelectedIndex();
                if (index < 0) {
                    JOptionPane.showMessageDialog(mg, "Select a category!");
                    return;
                }
                jPanel1.setVisible(false);
                jPanel3.setVisible(true);
                x = index;
                flag = true;
                dflag = false;
                setDescription();
                break;
            case "Delete":
//                jButton2ActionPerformed
//                Action when delete button is clicked
                index = jList1.getSelectedIndex();
                if (index < 0) {
                    JOptionPane.showMessageDialog(mg, "Select a category!");
                    return;
                }
                tindex = jXTable1.getSelectedRow();
                if (tindex < 0) {
                    JOptionPane.showMessageDialog(mg, "Select an entry!");
                    return;
                }
                int n = JOptionPane.showConfirmDialog(
                        mg,
                        "Are you sure you want to delete this?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    a.get(index).remove(tindex);
                    JOptionPane.showMessageDialog(mg, "Successfully Deleted");
                    mg.setUpTableData();
                }
                break;
            case "Edit":
//                jButton5ActionPerformed
//                Action when edit button is clicked
                index = jList1.getSelectedIndex();
                if (index < 0) {
                    JOptionPane.showMessageDialog(mg, "Select a category!");
                    return;
                }
                tindex = jXTable1.getSelectedRow();
                if (tindex < 0) {
                    JOptionPane.showMessageDialog(mg, "Select an entry!");
                    return;
                }
                num = tindex;
                flag = false;
                dflag = false;
                x = index;
                setDescription();
                jPanel1.setVisible(false);
                jPanel3.setVisible(true);
                break;
            case "Search":
//                jButton3ActionPerformed
//                Action when search button is clicked
                s = (String) JOptionPane.showInputDialog(
                        mg,
                        "Enter name: ",
                        "Input",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "");
                if (s == null) {
                    return;
                }
                jPanel1.setVisible(false);
                jPanel2.setVisible(true);
                str = s;
                details.setContentType("text/html");
                runn();
                break;
            case "View":
//                jButton6ActionPerformed
//                Action when View full detail button is clicked 
                index = jList1.getSelectedIndex();
                if (index < 0) {
                    JOptionPane.showMessageDialog(mg, "Select a category!");
                    return;
                }
                tindex = jXTable1.getSelectedRow();
                if (tindex < 0) {
                    JOptionPane.showMessageDialog(mg, "Select an entry!");
                    return;
                }
                num = tindex;
                flag = false;
                x = index;
                jPanel1.setVisible(false);
                jPanel3.setVisible(true);
                dflag = true;
                setDescription();
                break;
            case "Read":
//                jButton7ActionPerformed
//                Action when Read from file button is clicked
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        temp = (ArrayList<ArrayList<Acquaintances>>) SerializationUtil.deserialize(selectedFile);
                    } catch (ClassNotFoundException | IOException e) {
                        JOptionPane.showMessageDialog(mg, "Error");
                        return;
                    }
                } else {
                    return;
                }
                try {
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < temp.get(i).size(); j++) {
                            a.get(i).add(temp.get(i).get(j));
                        }
                    }
                } catch (Exception e) {

                }
                mg.setUpTableData();
                break;
            case "Save":
//                jButton8ActionPerformed
//                Action done when Save as file button is clicked
                s = (String) JOptionPane.showInputDialog(
                        mg,
                        "Enter file name: (*.ser)",
                        "Input",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "output.ser");
                if (s == null) {
                    return;
                }
                if (!s.endsWith(".ser")) {
                    JOptionPane.showMessageDialog(mg, "File name should end with .ser");
                    return;
                }
                File[] fileList = (new File(".")).listFiles((File pathname) -> pathname.getName().endsWith(".ser"));
                boolean flag = false;
                for (File f : fileList) {
                    if (f.getName().matches(s)) {
                        flag = true;
                    }
                }
                if (flag) {
                    int q = JOptionPane.showConfirmDialog(mg, s + " already exists:\nAre you sure you want to overwrite?");
                    if (q != 0) {
                        return;
                    }
                }
                try {
                    SerializationUtil.serialize(a, s);
                } catch (IOException e) {
                    return;
                }
                JOptionPane.showMessageDialog(mg, s + " saved successfully");
                break;
            case "Exit":
//                jButton4ActionPerformed
//                Action done when Exit button is clicked
                System.exit(0);
                break;
            case "jButton9":
//                jButton9ActionPerformed
                jPanel2.setVisible(false);
                jPanel1.setVisible(true);
                break;
            case "jButton10":
//                jButton10ActionPerformed
                dflag = true;
                String errMsg = ""; //String to store all error messages
                String Name = name.getText();
                if (Name.isEmpty()) {
                    errMsg += "Enter a name\n";
                }
                String Mobile = mobile.getText();
                if (!MobileNoChecker(Mobile)) {
                    errMsg += "Enter a valid mobile number (6-15 digits)\n";
                }
                String Email = email.getText();
                if (!Email.contains("@")) {
                    errMsg += "Enter a valid email\n";
                }
                String One,
                 Two,
                 Three;
                Acquaintances ac;

                One = one.getText();
                Two = two.getText();
                Three = three.getText();
                System.out.println(One);
                System.out.println(Two);
                System.out.println(Three);

                //Append the error messages
                if (x == 3) {
                    if (One.isEmpty() || One.length() > 300) {
                        errMsg += "Enter a valid value ( 1 to 300 chars) for First Meeting Time & Location\n";
                    }
                    if (Two.isEmpty() || Two.length() > 300) {
                        errMsg += "Enter a valid value ( 1 to 300 chars) for First Meeting Circumstances\n";
                    }
                    if (Three.isEmpty() || Three.length() > 300) {
                        errMsg += "Enter a valid value ( 1 to 300 chars) for Other Useful Information\n";
                    }
                }
                if (x == 2) {
                    if (One.isEmpty() || One.length() > 300) {
                        errMsg += "Enter a valid value ( 1 to 300 chars) for Common Interests\n";
                    }
                }
                if (x == 1) {
                    if (One.isEmpty() || One.length() > 300) {
                        errMsg += "Enter a valid date for Relative's Birthday\n";
                    } else if (!validDate(One)) {
                        return;
                    }
                    if (Two.isEmpty() || Two.length() > 300) {
                        errMsg += "Enter a valid date for Last Date Met\n";
                    } else if (!validDate(Two)) {
                        return;
                    }
                }
                if (x == 0) {
                    if (One.isEmpty() || One.length() > 300) {
                        errMsg += "Enter a valid value ( 1 to 300 chars) for Specific Events\n";
                    }
                    if (Two.isEmpty() || Two.length() > 300) {
                        errMsg += "Enter a valid value  ( 1 to 300 chars) for First Acquaintance Context\n";
                    }
                    if (Three.isEmpty() || Three.length() > 300) {
                        errMsg += "Enter a valid date for First Acquaintance Date\n";
                    } else if (!validDate(Three)) {
                        return;
                    }
                }

                //display all error messages if they exists
                if (!errMsg.isEmpty()) {
                    JOptionPane.showMessageDialog(mg, errMsg);
                    return;
                }

                if (this.flag) {
                    ac = af.Factory2(x, Name, Mobile, Email, One, Two, Three);
                } else {
                    ac = af.existingAcquaintances(x, a, num, Name, Mobile, Email, One, Two, Three);
                }
                if (this.flag) {
                    a.get(x).add(ac);
                }
                jPanel1.setVisible(true);
                jPanel3.setVisible(false);
                mg.setUpTableData();
                break;
            case "jButton11":
//                jButton11ActionPerformed
                jPanel1.setVisible(true);
                jPanel3.setVisible(false);
                break;
        }
        setButtonState("");
    }

    //Access to the private JTextArea one
    public void setOne(String s) {
        this.one.setText(s);
        this.one.setVisible(true);
    }

    //Access to the private JTextArea two
    public void setTwo(String s) {
        two.setText(s);
        two.setVisible(true);
    }

    //Access to the private JTextArea three
    public void setThree(String s) {
        three.setText(s);
        three.setVisible(true);
    }

    @Override
    public void setButtonState(String action) {
        this.buttonState = action;
        if (!action.equals("")) {
            System.out.println("Notifying all observers");
            notifyAllObservers();
        }

    }

    @Override
    public String getButtonState() {
        return this.buttonState;
    }
}
