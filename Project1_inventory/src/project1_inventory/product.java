/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1_inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sun.java2d.pipe.SpanShapeRenderer;

/**
 *
 * @author DELL
 */
public class product extends javax.swing.JPanel {
    
    String id; // For use with ID from table
    String expdate;
    public product() {
        initComponents();
        tb_load();
        supplier_load();
        loadcetagory(); // load show table cetagory
        comloadcetagory(); // load for select cetagory
//        setDefaulddata(); // for search expdate
    }
    public void tb_load(){
        try {
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            dt.setRowCount(0);
            
            Statement s = db.mycon().createStatement();
            ResultSet rs =  s.executeQuery("SELECT * FROM product_tbl ");
            while (rs.next()){
                Vector v = new Vector();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                v.add(rs.getString(7));
                v.add(rs.getString(8));
                v.add(rs.getString(9));
                v.add(rs.getString(10));
                v.add(rs.getString(11));
                v.add(rs.getString(12));
                v.add(rs.getString(13));
                
                dt.addRow(v);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void supplier_load(){
//        All load supplier Id 
        try {
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM supplier_tbl");
            Vector v = new Vector();
            while(rs.next()){
                v.add(rs.getString("name"));
                DefaultComboBoxModel cm = new DefaultComboBoxModel(v);
                com_name.setModel(cm);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
//    Load for select cetagory name
    public void comloadcetagory(){       
        try {
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM cetagory_tbl");
            Vector v = new Vector();
            while(rs.next()){
                v.add(rs.getString("name"));
                DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
                com_cettagory.setModel(dcm);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // for search name or barcode or exp date
    public void searchProduct(){
         String name = txt_search_text.getText();
        String barcode = txt_search_barcode.getText();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mmm-dd");
//        String expdate = sdf.format(txt_expdate.getDate());
        try {
           DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
           dt.setRowCount(0);
           Statement s = db.mycon().createStatement();
           ResultSet rs = s.executeQuery("SELECT * FROM product_tbl WHERE name LIKE '%"+name+"%' AND barcode LIKE '%"+barcode+"%'");
           
           while(rs.next()){
                Vector v = new Vector();
           
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                v.add(rs.getString(7));
                v.add(rs.getString(8));
                v.add(rs.getString(9));
                v.add(rs.getString(10));
                v.add(rs.getString(11));
                v.add(rs.getString(12));
                v.add(rs.getString(13));
           
                dt.addRow(v);
           
           }
        } catch (Exception e) {
            System.out.println(e);
            tb_load();
        }
    }
//    public void setDefaulddata(){
//        Date d = new Date();
//        txt_expdate.setDate(d);
//    } // for search expdate
    
    
    
    // load for cetagory table
    public void loadcetagory(){
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM cetagory_tbl");
            while(rs.next()){
                Vector v = new Vector();
                
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                
                dtm.addRow(v);
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    
    
//    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txt_search = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_search1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_search2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_search3 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_barcode = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_price = new javax.swing.JTextField();
        txt_qty = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_sup_id = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_status = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        txt_sellprice = new javax.swing.JTextField();
        com_name = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txt_cetagory = new javax.swing.JTextField();
        com_cettagory = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_descrip = new javax.swing.JTextArea();
        exp_date = new com.toedter.calendar.JDateChooser();
        mfd_date = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txt_namecet = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txt_search_text = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txt_search_barcode = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txt_expdate = new com.toedter.calendar.JDateChooser();
        jButton8 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_search.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_search.setText("0");
        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Search Item :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("Product Information");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Search ID :");

        txt_search1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_search1.setText("0");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Search ID :");

        txt_search2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_search2.setText("0");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Search ID :");

        txt_search3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_search3.setText("0");

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1_inventory/img/icons8_search_20px.png"))); // NOI18N
        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jButton3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_search1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_search2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_search3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_search3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_search2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_search1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Barcode :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Name :");

        txt_barcode.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_barcode.setText("0");

        txt_name.setFont(new java.awt.Font("Khmer OS System", 1, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Price :");

        txt_price.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_price.setText("0");

        txt_qty.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_qty.setText("0");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Qty :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Supplier_name :");

        txt_sup_id.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_sup_id.setText("0");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Status :");

        txt_status.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Discountinue", "Delete" }));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Sell price :");

        txt_sellprice.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_sellprice.setText("0");

        com_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                com_nameActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Supplier_id :");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1_inventory/img/icons8_downloading_updates_20px.png"))); // NOI18N
        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1_inventory/img/icons8_update_20px.png"))); // NOI18N
        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1_inventory/img/icons8_Delete_20px.png"))); // NOI18N
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_barcode, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                            .addComponent(txt_price)
                            .addComponent(txt_qty)
                            .addComponent(txt_sup_id)
                            .addComponent(txt_name)
                            .addComponent(txt_sellprice)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel11))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(40, 40, 40)
                                .addComponent(jButton4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_status, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(com_name, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 4, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_sellprice, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_sup_id, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(com_name, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_status, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Cetagory :");

        txt_cetagory.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_cetagory.setText("0");

        com_cettagory.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        com_cettagory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Cetagories", " " }));
        com_cettagory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                com_cettagoryActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("MFD Date :");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("EXP Date :");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("Description :");

        txt_descrip.setColumns(20);
        txt_descrip.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txt_descrip.setRows(5);
        jScrollPane3.setViewportView(txt_descrip);

        exp_date.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        mfd_date.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(txt_cetagory, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(com_cettagory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(mfd_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(exp_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cetagory, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(com_cettagory, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(mfd_date, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(exp_date, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add Product", jPanel4);

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("Name :");

        txt_namecet.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_namecet.setText("0");

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1_inventory/img/icons8_downloading_updates_20px.png"))); // NOI18N
        jButton5.setText("Save");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1_inventory/img/icons8_update_20px.png"))); // NOI18N
        jButton6.setText("Update");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1_inventory/img/icons8_Delete_20px.png"))); // NOI18N
        jButton7.setText("Delete");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setText("ID :");

        txt_id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_id.setText("0");
        txt_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_idKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_namecet)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txt_namecet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(463, 463, 463))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable2.setFont(new java.awt.Font("Khmer OS System", 0, 18)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID ", "Name Cetagory"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Add Cetagory", jPanel5);

        jTable1.setFont(new java.awt.Font("Khmer OS System", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", " Name", "Barcode", "Price", "Sell Price", "Qty", "Supplier_id", "Supplier Name", "Status", "Cetagory", "MFD Date", "EXP Date", "Description"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Search by name :");

        txt_search_text.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_search_text.setText("0");
        txt_search_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_search_textKeyReleased(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setText(" Barcode :");

        txt_search_barcode.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_search_barcode.setText("0");
        txt_search_barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_search_barcodeKeyReleased(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setText("EXP Date :");

        txt_expdate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_expdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txt_expdateMouseReleased(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton8.setText("Search");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search_text, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(txt_expdate, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1107, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txt_search_text, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22)
                        .addComponent(txt_search_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23))
                    .addComponent(txt_expdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Search Product", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO btn save
        String name = txt_name.getText();
        String barcode = txt_barcode.getText();
        String price = txt_price.getText();
        String sellprice = txt_sellprice.getText();
        String qty = txt_qty.getText();
        String supid = txt_sup_id.getText();
        Object status = txt_status.getSelectedItem();
        String supname = com_name.getSelectedItem().toString();//txt_supname.getText();
        String cetagory = txt_cetagory.getText();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
        String mfddate = sdf.format(mfd_date.getDate());
        String expdate = sdf.format(exp_date.getDate());
        
        String descrip = txt_descrip.getText();
        try {
            Statement s = db.mycon().createStatement();
            s.executeUpdate("INSERT INTO product_tbl(name,barcode,price,sell_price,qty,supplier_id,status,sup_name,cetagory,mfd_date,exp_date,description) VALUES ('"+name+"','"+barcode+"','"+price+"','"+sellprice+"','"+qty+"','"+supid+"','"+status+"','"+supname+"','"+cetagory+"','"+mfddate+"','"+expdate+"','"+descrip+"')");
            JOptionPane.showMessageDialog(null," Data Saved");
        } catch (SQLException e) {
            System.out.println(e);
        }
        tb_load();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO Search
//        String search = txt_search.getText();
//        try {
//            Statement s = db.mycon().createStatement();
//            ResultSet rs = s.executeQuery("SELECT * FROM product_tbl WHERE barcode = '"+search+"'");
//            
//            if(rs.next()){
//                txt_name.setText(rs.getString("name"));
//                txt_barcode.setText(rs.getString("barcode"));
//                txt_price.setText(rs.getString("price"));
//                txt_sellprice.setText(rs.getString("sell_price"));
//                txt_qty.setText(rs.getString("qty"));
//                txt_sup_id.setText(rs.getString("supplier_id"));
//                txt_status.setSelectedItem(rs.getString("status"));
//                com_name.setSelectedItem(rs.getString("sup_name"));
//                txt_cetagory.setText(rs.getString("cetagory"));
//                
//                try {
//                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
//                 mfd_date.setDate(sdf.parse(rs.getString("mfd_date")));
//                  exp_date.setDate(sdf.parse(rs.getString("exp_date")));
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//                
//                txt_descrip.setText(rs.getString("description"));
//                
//            }
////            JOptionPane.showMessageDialog(null," Data Search");
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        tb_load();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO update
        String name = txt_name.getText();
        String barcode = txt_barcode.getText();
        String price = txt_price.getText();
        String sellprice = txt_sellprice.getText();
        String qty = txt_qty.getText();
        String supid = txt_sup_id.getText();
        Object status = txt_status.getSelectedItem();
        String search = txt_search.getText();
        String supname = com_name.getSelectedItem().toString();//txt_supname.getText();
        String cetagory = txt_cetagory.getText();
        
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MMM-dd");
        String mfddate = sdf.format(mfd_date.getDate());
        String expdate = sdf.format(exp_date.getDate());
        
        String descrip = txt_descrip.getText();
        try {
            Statement s = db.mycon().createStatement();
            s.executeUpdate("UPDATE product_tbl SET name = '"+name+"',barcode = '"+barcode+"',price = '"+price+"',sell_price = '"+sellprice+"',qty = '"+qty+"',supplier_id = '"+supid+"',status = '"+status+"',sup_name = '"+supname+"',cetagory = '"+cetagory+"',mfd_date = '"+mfddate+"',exp_date = '"+expdate+"',description = '"+descrip+"' WHERE id = '"+search+"'");
            JOptionPane.showMessageDialog(null," Data Updated");
        } catch (SQLException e) {
            System.out.println(e);
        }
        tb_load();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO delete btn
        String search = txt_search.getText();
        try {
            Statement s = db.mycon().createStatement();
            s.executeUpdate("DELETE FROM product_tbl WHERE id = '"+search+"'");
            JOptionPane.showMessageDialog(null,"Data Delete");
        } catch (SQLException e) {
            System.out.println(e);
        }
        tb_load();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add mouse click
        int r = jTable1.getSelectedRow();
        
        String id = jTable1.getValueAt(r, 0).toString();
        String name = jTable1.getValueAt(r, 1).toString();
        String barcode = jTable1.getValueAt(r, 2).toString();
        String price = jTable1.getValueAt(r, 3).toString();
        String sellprice = jTable1.getValueAt(r, 4).toString();
        String qty = jTable1.getValueAt(r, 5).toString();
        String supid = jTable1.getValueAt(r, 6).toString();
        String supname = jTable1.getValueAt(r, 8).toString();
        String status = jTable1.getValueAt(r, 7).toString();
        
        txt_search.setText(id);
        txt_name.setText(name);
        txt_barcode.setText(barcode);
        txt_price.setText(price);
        txt_sellprice.setText(sellprice);
        txt_qty.setText(qty);
        txt_sup_id.setText(supid);
        txt_status.setSelectedItem(status);
        com_name.setToolTipText(supname);
    }//GEN-LAST:event_jTable1MouseClicked

    private void txt_search_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_search_textKeyReleased
        // TODO search by name 
        searchProduct();
//        String name = txt_search_text.getText();
//        String barcode = txt_search_barcode.getText();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mmm-dd");
//        String expdate = sdf.format(txt_expdate.getDate());
//        try {
//           DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
//           dt.setRowCount(0);
//           Statement s = db.mycon().createStatement();
//           ResultSet rs = s.executeQuery("SELECT * FROM product_tbl WHERE name LIKE '%"+name+"%' OR barcode LIKE '%"+barcode+"%'OR exp_date LIKE '%"+expdate+"%'");
//           
//           while(rs.next()){
//                Vector v = new Vector();
//           
//                v.add(rs.getString(1));
//                v.add(rs.getString(2));
//                v.add(rs.getString(3));
//                v.add(rs.getString(4));
//                v.add(rs.getString(5));
//                v.add(rs.getString(6));
//                v.add(rs.getString(7));
//                v.add(rs.getString(8));
//                 v.add(rs.getString(9));
//           
//                dt.addRow(v);
//           
//           }
//        } catch (Exception e) {
//            tb_load();
//        }
    }//GEN-LAST:event_txt_search_textKeyReleased

    private void com_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_nameActionPerformed
      //   TODO btn select
        try {
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery(("SELECT * FROM supplier_tbl WHERE name = '"+com_name.getSelectedItem().toString()+"' "));
            if(rs.next()){
                txt_sup_id.setText(rs.getString("supid"));
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_com_nameActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add btn save
//        String id = txt_id.getText();
        String namecet  = txt_namecet.getText();
        try {
            Statement s = db.mycon().createStatement();
            s.executeUpdate("INSERT INTO cetagory_tbl(name) VALUES('"+namecet+"')");
            JOptionPane.showMessageDialog(null, "Data Saved");
            loadcetagory();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO btn update
        String namecet = txt_namecet.getText();
        String id = txt_id.getText();
        try {
            Statement s = db.mycon().createStatement();
            s.executeUpdate("UPDATE cetagory_tbl SET name = '"+namecet+"' WHERE id = '"+id+"'");
            JOptionPane.showMessageDialog(null, "Updated");
            txt_namecet.setText("");    
            loadcetagory();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add btn delete
        try {
            Statement s = db.mycon().createStatement();
            s.executeUpdate("DELETE FROM cetagory_tbl WHERE id = '"+id+"'");
            JOptionPane.showMessageDialog(null, "Data DELETED");
            txt_namecet.setText("");
            loadcetagory();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void com_cettagoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_cettagoryActionPerformed
        // TODO add select cetagory
        txt_cetagory.setText(com_cettagory.getSelectedItem().toString());
        
    }//GEN-LAST:event_com_cettagoryActionPerformed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO mouse click
        int r = jTable2.getSelectedRow();
        
//        cid = (int) jTable2.getValueAt(r, 1);
        String name = jTable2.getValueAt(r, 1).toString();
        String id = jTable2.getValueAt(r, 0).toString();
        txt_namecet.setText(name);
        txt_id.setText(id);
    }//GEN-LAST:event_jTable2MouseClicked

    private void txt_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idKeyReleased

    private void txt_search_barcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_search_barcodeKeyReleased
        // TODO add your handling code here:
        searchProduct();
    }//GEN-LAST:event_txt_search_barcodeKeyReleased

    private void txt_expdateMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_expdateMouseReleased
        // TODO add your handling code here:
        searchProduct();
    }//GEN-LAST:event_txt_expdateMouseReleased

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO to btm search expdate
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mmm-dd");
        String expdate = sdf.format(txt_expdate.getDate());
    }//GEN-LAST:event_jButton8ActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        // TODO Search barcode by keyrelease
        String search = txt_search.getText();
        try {
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM product_tbl WHERE barcode = '"+search+"'");
            
            if(rs.next()){
                txt_name.setText(rs.getString("name"));
                txt_barcode.setText(rs.getString("barcode"));
                txt_price.setText(rs.getString("price"));
                txt_sellprice.setText(rs.getString("sell_price"));
                txt_qty.setText(rs.getString("qty"));
                txt_sup_id.setText(rs.getString("supplier_id"));
                txt_status.setSelectedItem(rs.getString("status"));
                com_name.setSelectedItem(rs.getString("sup_name"));
                txt_cetagory.setText(rs.getString("cetagory"));
                
                try {
                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
                 mfd_date.setDate(sdf.parse(rs.getString("mfd_date")));
                  exp_date.setDate(sdf.parse(rs.getString("exp_date")));
                } catch (Exception e) {
                    System.out.println(e);
                }
                
                txt_descrip.setText(rs.getString("description"));
                
            }
//            JOptionPane.showMessageDialog(null," Data Search");
        } catch (SQLException e) {
            System.out.println(e);
        }
//        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
////            dataadd_to_table();
////      After go back to Bacode Scanner      
//            txt_search.selectAll();
//            txt_search.requestFocus();
//        }
        tb_load();
    }//GEN-LAST:event_txt_searchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox com_cettagory;
    private javax.swing.JComboBox com_name;
    private com.toedter.calendar.JDateChooser exp_date;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private com.toedter.calendar.JDateChooser mfd_date;
    private javax.swing.JTextField txt_barcode;
    private javax.swing.JTextField txt_cetagory;
    private javax.swing.JTextArea txt_descrip;
    private com.toedter.calendar.JDateChooser txt_expdate;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_namecet;
    private javax.swing.JTextField txt_price;
    private javax.swing.JTextField txt_qty;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_search1;
    private javax.swing.JTextField txt_search2;
    private javax.swing.JTextField txt_search3;
    private javax.swing.JTextField txt_search_barcode;
    private javax.swing.JTextField txt_search_text;
    private javax.swing.JTextField txt_sellprice;
    private javax.swing.JComboBox txt_status;
    private javax.swing.JTextField txt_sup_id;
    // End of variables declaration//GEN-END:variables

    private Vector Vector() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
