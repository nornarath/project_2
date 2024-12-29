/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1_inventory;

import java.awt.geom.Arc2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class Gm extends javax.swing.JPanel {

    /**
     * Creates new form Gm
     */
    public Gm() {
        initComponents();
        data_load();
        cart_total();
        discount();
        
    }
    public void data_load(){
//        load supplier
        
        try {
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM supplier_tbl");
            Vector v = new Vector();
            while(rs.next()){
                v.add(rs.getString("name"));
                DefaultComboBoxModel com = new DefaultComboBoxModel(v);
                txt_supplier.setModel(com);
                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
//        load product
         try {
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM product_tbl");
            Vector v = new Vector();
            while(rs.next()){
                v.add(rs.getString("name"));
                DefaultComboBoxModel com = new DefaultComboBoxModel(v);
                txt_product.setModel(com);
                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }  
//        laod last invoice number
         try {
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM extra_tbl WHERE id = 1");
            if(rs.next()){
                inid.setText(rs.getString("val"));
            } 
        } catch (Exception e) {
        }
//         pluse new invoice 
         int i = Integer.valueOf(inid.getText());
         i++;
         inid.setText(String.valueOf(i));
         
    }
   
     public void cart_total(){
            int numberfrow = jTable1.getRowCount();
            double total = 0;
            for (int i = 0; i < numberfrow; i++) {
                double value = Double.valueOf(jTable1.getValueAt(i, 7).toString());
                total += value;
            }
        txt_subtotal.setText(Double.toString(total));
         }
    
    public void discount(){
      try {
            Double discount  = Double.valueOf(txt_dispercent.getText());
            Double dis = 0.0;
            Double total  = Double.valueOf(txt_subtotal.getText());
             
            dis = total * discount /100;
            txt_dis.setText(String.valueOf(dis));
                
        } catch (Exception e) { 
            System.out.println(e);
        }
      totalcal();
    }
    
    public void totalcal(){
//        Price discount already
        Double subtotal = Double.valueOf(txt_subtotal.getText());
        Double discount = Double.valueOf(txt_dis.getText());
        Double netotal = subtotal - discount;
         
        txt_total.setText(String.valueOf(netotal));
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inid = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_supplier = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txt_product = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txt_qty = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_expdate = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txt_baroode = new javax.swing.JTextField();
        txt_cost = new javax.swing.JTextField();
        txt_sell = new javax.swing.JTextField();
        jl_sid = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txt_paid = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_totalqty = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txt_subtotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_dis = new javax.swing.JTextField();
        txt_dispercent = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("GRN NO :");

        inid.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        inid.setText("01");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(inid, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "GRNID", "Product Name", "Customer Name", "Bar Code", "Qty", "Cost Price", "Sell Price", "Total Price", "Expire Date"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Supplier Name :");

        txt_supplier.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_supplier.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Customer Name", " " }));
        txt_supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_supplierActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Product :");

        txt_product.setEditable(true);
        txt_product.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_product.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Product Name" }));
        txt_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_productActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Qty :");

        txt_qty.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_qty.setText("0");
        txt_qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_qtyActionPerformed(evt);
            }
        });
        txt_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_qtyKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Sell Price :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Cost Price :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Exp Date :");

        txt_expdate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_expdate.setText("2024-05-16");
        txt_expdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_expdateActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Add ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("Remove");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setText("Remove All");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Barcode :");

        txt_baroode.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_baroode.setText("00");

        txt_cost.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_cost.setText("00");

        txt_sell.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_sell.setText("00");

        jl_sid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jl_sid.setText("00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_expdate)
                                    .addComponent(txt_product, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_qty)
                                    .addComponent(txt_cost)
                                    .addComponent(txt_sell, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txt_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jl_sid, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(65, 65, 65)
                                .addComponent(txt_baroode)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_supplier)
                    .addComponent(jLabel3)
                    .addComponent(jl_sid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_product)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_baroode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_sell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_expdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Paid Amount :");

        txt_paid.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_paid.setText("0");
        txt_paid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_paidActionPerformed(evt);
            }
        });
        txt_paid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_paidKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Total Qty :");

        txt_totalqty.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_totalqty.setText("0");
        txt_totalqty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalqtyActionPerformed(evt);
            }
        });
        txt_totalqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_totalqtyKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_totalqty)
                    .addComponent(txt_paid))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_paid))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_totalqty)))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Sub Total :");

        txt_subtotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_subtotal.setText("00.00");
        txt_subtotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Net Total :");

        txt_total.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_total.setText("00.00");
        txt_total.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Discount(%) :");

        txt_dis.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_dis.setText("00.00");
        txt_dis.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_dispercent.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_dispercent.setForeground(new java.awt.Color(255, 0, 0));
        txt_dispercent.setText("5");
        txt_dispercent.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txt_dispercent.setCaretColor(new java.awt.Color(255, 0, 51));
        txt_dispercent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dispercentActionPerformed(evt);
            }
        });
        txt_dispercent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_dispercentKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Discount(%)");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_subtotal)
                    .addComponent(txt_total)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(txt_dispercent, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_dis, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_subtotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txt_dis)
                        .addComponent(txt_dispercent))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_total))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setText("Save");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_supplierActionPerformed
        // TODO load supplier 
        String name =txt_supplier.getSelectedItem().toString();
        try {
             Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM supplier_tbl WHERE name = '"+name+"'");
            if(rs.next()){
                jl_sid.setText(rs.getString("id"));
            }
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_txt_supplierActionPerformed

    private void txt_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_productActionPerformed
        // TODO load unit/price
        // TODO load unit/price
        String name = txt_product.getSelectedItem().toString();
//        String customer  = txt_customer.getSelectedItem().toString();
        try {
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT barcode,price,sell_price,qty FROM product_tbl WHERE name = '"+name+"'");
            if(rs.next()){
                txt_cost.setText(rs.getString("price"));
                txt_sell.setText(rs.getString("sell_price"));
                txt_qty.setText(rs.getString("qty"));
                txt_baroode.setText(rs.getString("barcode"));
                
            /// 
            
            }
            
//            total_cal();
//            caculation for sale 
//            Double qty = Double.valueOf(txt_qty.getText());
//            Double price = Double.valueOf(jl_unit.getText());
//            Double total;
//            total = qty * price;
//            jl_total.setText(String.valueOf(total));
        } catch (SQLException e) {
            System.out.println(e);
        }
       
    }//GEN-LAST:event_txt_productActionPerformed

    private void txt_qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_qtyKeyReleased
        // TODO load caculation
//        total_cal();
    }//GEN-LAST:event_txt_qtyKeyReleased

    private void txt_paidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_paidActionPerformed
        // TODO add your handling code here:
        //        total_paid();
    }//GEN-LAST:event_txt_paidActionPerformed

    private void txt_paidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_paidKeyReleased
        // TODO Key Paid amount
        //        Double paid = Double.valueOf(txt_paid.getText());
        //        Double total = Double.valueOf(txt_totalamount.getText());
        //        Double due;
        //        due = paid -total;
        //        txt_balance.setText(String.valueOf(due)); for reference
       

    }//GEN-LAST:event_txt_paidKeyReleased

    private void txt_totalqtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalqtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalqtyActionPerformed

    private void txt_totalqtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_totalqtyKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalqtyKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO btn Add to cart
        // total price
//        int qty = Integer.valueOf(txt_qty.getText());
//        int cost = Integer.valueOf(txt_cost.getText());
//        int sum = qty * cost;
        Double qty = Double.valueOf(txt_qty.getText());
        Double price = Double.valueOf(txt_cost.getText());
        Double total;
        total = qty * price;
        
        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        Vector v = new Vector();
        v.add(inid.getText()); // for inid
        v.add(txt_product.getSelectedItem().toString());//for product
        v.add(txt_supplier.getSelectedItem().toString());
        v.add(txt_baroode.getText());//for barcode
        v.add(txt_qty.getText());//for product
        v.add(txt_cost.getText()); //for unit/pricce
        v.add(txt_sell.getText()); // for total price
        v.add(total); // for total price
        v.add(txt_expdate.getText()); // for expdate
        
        dt.addRow(v);
        cart_total();
        discount();
        totalcal();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO remove item
         try {
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            int row = jTable1.getSelectedRow();
            dt.removeRow(row);
        } catch (Exception e) {
        }  
        cart_total();
        discount();
        totalcal();
//        total_paid();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO tbn remove all
         try {
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            dt.setRowCount(0);
        } catch (Exception e) {
        }
        cart_total();
        discount();
        totalcal();
//        total_paid();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_qtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_qtyActionPerformed

    private void txt_dispercentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dispercentKeyReleased
        // TODO add your handling code here:
        discount();
    }//GEN-LAST:event_txt_dispercentKeyReleased

    private void txt_dispercentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dispercentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dispercentActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add btn save data to database
        try {
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            int rc = dt.getRowCount();
            for (int i = 0; i < rc; i++) {
                String inid = dt.getValueAt(i, 0).toString();
                String barcode = dt.getValueAt(i, 3).toString();
                String item_name = dt.getValueAt(i, 1).toString();
                String qty = dt.getValueAt(i, 4).toString();
                String costprice = dt.getValueAt(i, 5).toString();
                String sellprice = dt.getValueAt(i, 6).toString();
                String totalprice = dt.getValueAt(i, 7).toString();
                String exp = dt.getValueAt(i, 8).toString();
                String subtotal = txt_subtotal.getText();
                String discount = txt_dis.getText();
                String nettotal = txt_total.getText();
                String sid = jl_sid.getText();
                
                Statement s = db.mycon().createStatement();
                s.executeUpdate("INSERT INTO grn_tbl (grnno,sid,barcode,item_name,qty,cost_price,sell_price,total_price,exp,subtotal,discount,nettotal) VALUES ('"+inid+"','"+sid+"','"+barcode+"','"+item_name+"','"+qty+"','"+costprice+"','"+sellprice+"','"+totalprice+"','"+exp+"','"+subtotal+"','"+discount+"','"+nettotal+"')");
//              sale tbl 
                
                
            }
                JOptionPane.showMessageDialog(null, "Data saved");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txt_expdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_expdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_expdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel inid;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jl_sid;
    private javax.swing.JTextField txt_baroode;
    private javax.swing.JTextField txt_cost;
    private javax.swing.JTextField txt_dis;
    private javax.swing.JTextField txt_dispercent;
    private javax.swing.JTextField txt_expdate;
    private javax.swing.JTextField txt_paid;
    private javax.swing.JComboBox txt_product;
    private javax.swing.JTextField txt_qty;
    private javax.swing.JTextField txt_sell;
    private javax.swing.JTextField txt_subtotal;
    private javax.swing.JComboBox txt_supplier;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_totalqty;
    // End of variables declaration//GEN-END:variables
}
