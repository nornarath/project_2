/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1_inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.scene.input.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author DELL
 */
public class sale extends javax.swing.JPanel {
//for connect barcode
    public static String barcode = "0";
    public static String customer_id = "0";
    public Double stock_qty = 0.0;
    public static String Select_product_name = null;
    public static String Select_product_barcode = null;
    public static String Select_customer_name = null;
    public static String Select_customer_id = null;
    
    public sale() {
        initComponents();
        data_load();
        setTime();
    }
//    for date and time
    public void setTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(sale.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Date date = new Date();
                    SimpleDateFormat tf = new SimpleDateFormat("h:mm:ss aa");
                    SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MMM-yyyy");
                    String time1 = tf.format(date);
                    time.setText(time1.split(" ")[0] + " " + time1.split(" ")[1]);
                    date1.setText(df.format(date));
                }
            }
        }).start();
    }
    public void data_load(){
//        load customer
        try {
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM customer_tbl");
            Vector v = new Vector();
            while(rs.next()){
                v.add(rs.getString("name"));
                DefaultComboBoxModel com = new DefaultComboBoxModel(v);
                txt_customer.setModel(com);
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
//    cacolation 
    public void totalprice(){
            Double qty = Double.valueOf(txt_qty.getText());
            Double price = Double.valueOf(jl_unit.getText());
            Double total;
            total = qty * price;
            jl_total.setText(String.valueOf(total));
    }
    public void cart_total(){
        int numberfrow = jTable1.getRowCount();
        double total = 0;
        for (int i = 0; i < numberfrow; i++) {
            double value = Double.valueOf(jTable1.getValueAt(i, 6).toString());
            total += value;
        }
        txt_totalamount.setText(Double.toString(total));
//        Bill total qty
        int numfrow = jTable1.getRowCount();
        double totals = 0;
        for (int i = 0; i < numfrow; i++) {
            double values = Double.valueOf(jTable1.getValueAt(i, 4).toString());
            totals += values;
        }
        txt_totalqty.setText(Double.toString(totals));
        
    }
    public void total_paid(){ // Total calculation
        
        DecimalFormat df = new DecimalFormat("000.00");
        
        Double paidamount = Double.valueOf(txt_paid.getText());
        Double totalamount = Double.valueOf(txt_totalamount.getText());
        Double shippcost = Double.valueOf(txt_shippcost.getText());
        Double discount = Double.valueOf(txt_discount.getText());
        Double tax = Double.valueOf(txt_tax.getText());
//      calculate shipping cost 
        Double shipp  = Double.valueOf(txt_shippcost.getText());
//        txt_shippcost.setText(shipping_cost.getText());
        shipping_cost.setText(String.valueOf(shipp));
        
        
//      calculate tax amount   
        Double Tax_amount = totalamount * tax /100.00 ;   
        t_taxamount.setText(String.valueOf(Tax_amount));
        
//      calculate discount amount   
        Double discount_amount = totalamount * discount /100.00 ;  
        t_discountamount.setText(String.valueOf(discount_amount));
        
         //      Gramd Total Amount 
        Double grandamn = (totalamount + shipp + Tax_amount ) - discount_amount;
        grandamount.setText(df.format(grandamn));
//        grandamount.setText(String.valueOf(grandamn));
//      Balance Amount 
        Double due;
        due = paidamount - grandamn;
        txt_balance.setText(df.format(due));
    }
    public void stockup(){
//        get all table product id
        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        int rc = dt.getRowCount();
        for (int i = 0; i < rc ; i++) {
            String barcode = dt.getValueAt(i, 3).toString();
            String sold_qty = dt.getValueAt(i, 4).toString();
            
            System.out.println(barcode);
            System.out.println(sold_qty);
            try {
                Statement s = db.mycon().createStatement();
                ResultSet rs = s.executeQuery("SELECT qty FROM product_tbl WHERE barcode = '"+barcode+"'");
                
                if (rs.next()) {
                    stock_qty = Double.valueOf(rs.getString("qty"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            Double stock = stock_qty;
            Double stock_sold = Double.valueOf(sold_qty);
            // stock sold
            Double sold = stock - stock_sold;
            String soldqty = String.valueOf(sold);
            
            try {
                Statement ss = db.mycon().createStatement();
                ss.executeUpdate("UPDATE product_tbl SET qty ='"+soldqty+"' WHERE barcode ='"+barcode+"'");
            } catch (Exception e) {
                System.out.println(e);
            }
           
            
        }
    }
    public void dataadd_to_table(){
        Double sold_qty = Double.valueOf(txt_qty.getText());
        Double stock_qty = Double.valueOf(jl_stock.getText());
        
        if(sold_qty<=stock_qty){
        
        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        Vector v = new Vector();
        v.add(inid.getText()); // for inid
        v.add(txt_product.getSelectedItem().toString());//for product
        v.add(txt_customer.getSelectedItem().toString());
        v.add(jl_item.getText());//for barcode
        v.add(txt_qty.getText());//for product
        v.add(jl_unit.getText()); //for unit/pricce
        v.add(jl_total.getText()); // for total price
        v.add(jl_type.getText()); // for customer type
        
        dt.addRow(v);
        cart_total();
        total_paid();
        }else{
        JOptionPane.showMessageDialog(null, "Stock Have"+stock_qty+" Only");
    
    }
    }
    public void Customer_balance_check(){
        String id = Select_customer_id;
        try {
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT SUM(balance) FROM sale_tbl WHERE sale_id = '"+id+"'");
            if (rs.next()) {
                txt_balancecus.setText(rs.getString("SUM(balance)"));
                
            }
        } catch (Exception e) {
        }
    } 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jl_unit = new javax.swing.JLabel();
        txt_qty = new javax.swing.JTextField();
        txt_customer = new javax.swing.JComboBox();
        txt_product = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jl_total = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jl_item = new javax.swing.JLabel();
        jl_item1 = new javax.swing.JLabel();
        jl_type = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jl_stock = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_barcodekey = new javax.swing.JTextField();
        jl_item2 = new javax.swing.JLabel();
        txt_balancecus = new javax.swing.JLabel();
        jl_location = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txt_idcus = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        date1 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txt_paid = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_totalqty = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_shippcost = new javax.swing.JTextField();
        txt_discount = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_totalqty1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txt_tax = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txt_totalamount = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        shipping_cost = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        t_discountamount = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        grandamount = new javax.swing.JTextField();
        txt_balance = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        t_taxamount = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        inid = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setAutoscrolls(true);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Customer :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Product :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Qty :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Unit/Price :");

        jl_unit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jl_unit.setText("00.00");

        txt_qty.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_qty.setText("0");
        txt_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_qtyKeyReleased(evt);
            }
        });

        txt_customer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_customer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Customer Name", " " }));
        txt_customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_customerActionPerformed(evt);
            }
        });

        txt_product.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_product.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Product Name" }));
        txt_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_productActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Total/Price :");

        jl_total.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jl_total.setText("00.00");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Barcode :");

        jl_item.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jl_item.setText("0");
        jl_item.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jl_item1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jl_item1.setText("Type :");
        jl_item1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jl_type.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jl_type.setText("Show type.....");
        jl_type.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Stock :");

        jl_stock.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jl_stock.setText("0");
        jl_stock.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel14.setText("Barcode :");

        txt_barcodekey.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        txt_barcodekey.setForeground(new java.awt.Color(0, 102, 153));
        txt_barcodekey.setText("0");
        txt_barcodekey.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_barcodekeyKeyReleased(evt);
            }
        });

        jl_item2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jl_item2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1_inventory/img/icons8_ledger_50px_1.png"))); // NOI18N
        jl_item2.setText("Balance :");
        jl_item2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_balancecus.setBackground(new java.awt.Color(255, 255, 255));
        txt_balancecus.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_balancecus.setForeground(new java.awt.Color(255, 0, 51));
        txt_balancecus.setText("0");
        txt_balancecus.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txt_balancecus.setOpaque(true);

        jl_location.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jl_location.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1_inventory/img/icons8_Location_30px.png"))); // NOI18N
        jl_location.setText("City(Customer)");

        jPanel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("ID(Customer) :");

        txt_idcus.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_idcus.setText("0");
        txt_idcus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idcusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel21)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(txt_idcus)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_idcus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel13)
                    .addComponent(jl_item1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel14)
                    .addComponent(jl_item2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jl_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jl_item, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jl_stock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jl_type, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_product, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jl_unit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_balancecus, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jl_location, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_qty, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_barcodekey, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txt_customer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txt_barcodekey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_customer)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_product)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jl_unit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jl_total))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jl_item))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jl_stock))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_type)
                    .addComponent(jl_item1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_balancecus)
                    .addComponent(jl_item2)
                    .addComponent(jl_location, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Add to Cart");
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setFont(new java.awt.Font("Khmer OS System", 0, 20)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "INID", "Product Name", "Customer Name", "Bar Code", "Qty", "Unit/Price", "Total Price"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setText("Invoice NO :");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setText("Date :");

        date1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        date1.setText("0");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setText("Time :");

        time.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        time.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(356, 356, 356)
                    .addComponent(jLabel26)
                    .addContainerGap(357, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(date1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(232, 232, 232)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addGap(233, 233, 233)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Paid Amount :");

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

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Shipping Cost :");

        txt_shippcost.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_shippcost.setText("0");
        txt_shippcost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_shippcostKeyReleased(evt);
            }
        });

        txt_discount.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_discount.setText("15");
        txt_discount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_discountKeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("Discount :");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ACLEDA PAY", "ABA PAY", "WING PAY ", "STAPNA PAY", " ", " " }));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Payment type :");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("No of Items   : ");

        txt_totalqty1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_totalqty1.setText("0");
        txt_totalqty1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalqty1ActionPerformed(evt);
            }
        });
        txt_totalqty1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_totalqty1KeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("%");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Tax :");

        txt_tax.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_tax.setText("10");
        txt_tax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_taxKeyReleased(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 51, 51));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("%");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(jLabel12)
                    .addComponent(jLabel8)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_paid)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_shippcost)
                            .addComponent(txt_totalqty))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_totalqty1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txt_discount, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tax)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_totalqty)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txt_shippcost)
                    .addComponent(txt_totalqty1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txt_discount)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23)
                    .addComponent(txt_tax)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_paid))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Sub Total :");

        txt_totalamount.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_totalamount.setText("00.00");
        txt_totalamount.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Shipping Cost :");

        shipping_cost.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        shipping_cost.setText("00.00");
        shipping_cost.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("Discount Amount:");

        t_discountamount.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        t_discountamount.setText("00.00");
        t_discountamount.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("Grand Total Amount :");

        grandamount.setBackground(new java.awt.Color(0, 102, 153));
        grandamount.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        grandamount.setForeground(new java.awt.Color(255, 255, 255));
        grandamount.setText("00.00");
        grandamount.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_balance.setBackground(new java.awt.Color(204, 0, 51));
        txt_balance.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_balance.setForeground(new java.awt.Color(255, 255, 255));
        txt_balance.setText("00.00");
        txt_balance.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("Balance/Due:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setText("Tax Amount :");

        t_taxamount.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        t_taxamount.setText("00.00");
        t_taxamount.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_balance)
                    .addComponent(grandamount)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(t_discountamount, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(t_taxamount))
                    .addComponent(shipping_cost)
                    .addComponent(txt_totalamount, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_totalamount, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(shipping_cost, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(t_discountamount, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(t_taxamount, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(grandamount, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txt_balance, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project1_inventory/img/icons8_print_35px.png"))); // NOI18N
        jButton4.setText("Pay & Print");
        jButton4.setAutoscrolls(true);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Invoice NO :");

        inid.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        inid.setForeground(new java.awt.Color(0, 102, 153));
        inid.setText("01");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(31, 31, 31)
                                .addComponent(inid, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inid)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                        .addGap(23, 23, 23)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO btn Add to cart
        dataadd_to_table();
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
        total_paid();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO tbn remove all
        try {
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            dt.setRowCount(0);
        } catch (Exception e) {
        }
        cart_total();
        total_paid();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO btn pay and print for save to database
        //        do check balance customer
        if(Select_customer_id == null){
            JOptionPane.showMessageDialog(null, "Please Select Customer");
        }else{
        try {
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            int rc = dt.getRowCount();
            for (int i = 0; i < rc; i++) {
                String inid = dt.getValueAt(i, 0).toString();
                String pro_name = dt.getValueAt(i, 1).toString();
                String cus_name = dt.getValueAt(i, 2).toString();
                String barcode = dt.getValueAt(i, 3).toString();
                String qty = dt.getValueAt(i, 4).toString();
                String unit_price = dt.getValueAt(i, 5).toString();
                String total_price = dt.getValueAt(i, 6).toString();
//                String cus_type = dt.getValueAt(i, 7).toString();
                
                Statement s = db.mycon().createStatement();
                s.executeUpdate("INSERT INTO cart_tbl (inid,product_name,customer_name,barcode_1,qty_1,unit_price,total_price) VALUES ('"+inid+"','"+pro_name+"','"+cus_name+"','"+barcode+"','"+qty+"','"+unit_price+"','"+total_price+"')");
//              sale tbl 
                
                
            }
//                JOptionPane.showMessageDialog(null, "Data saved");
        } catch (SQLException e) {
            System.out.println(e);
        }
        
//        sale tbl
        try {
            String inv_id = inid.getText();
            String cus_name = Select_customer_name;//txt_customer.getSelectedItem().toString();
            String total_qty = txt_qty.getText();
            String totalbill = txt_totalamount.getText();
            String balance = txt_balance.getText();
            String shippcost = shipping_cost.getText();
            String disper = txt_discount.getText();
            String taxper = txt_tax.getText();
            String paidamd = txt_paid.getText();
            Object paytype = jComboBox1.getSelectedItem();
            String subtotal = txt_totalamount.getText();
            String discount = t_discountamount.getText();
            String tax = t_taxamount.getText();
            String grandamd = grandamount.getText();
//            String Status = "Paid";
//            Paid Check
            Double total = Double.valueOf(txt_totalamount.getText());
            Double pid = Double.valueOf(txt_paid.getText());
            String Status = null;
            if(pid.equals(0.0)){
                Status = "UnPaid";
            }
            else if(total>pid){
                Status = "Partail";
            }
            else if(total<=pid){
                Status = "Paid";
            }
            
            Statement ss = db.mycon().createStatement();
                ss.executeUpdate("INSERT INTO sale_tbl (inid,cart_id,customer_name,total_qty,total_bill,balance,status,shipp_cost,discount_per,tax_per,paid_amount,pay_type,sub_total,discoumt,tax,grand_amd) VALUES ('"+inv_id+"','"+Select_customer_id+"','"+cus_name+"','"+total_qty+"','"+totalbill+"','"+balance+"','"+Status+"','"+shippcost+"','"+disper+"','"+taxper+"','"+paidamd+"','"+paytype+"','"+subtotal+"','"+discount+"','"+tax+"','"+grandamd+"')");
        } catch (NumberFormatException | SQLException e) {
            System.out.println(e);
        }
//        for extra tbl save last invoice number
        try {
            String id = inid.getText();
            Statement s = db.mycon().createStatement();
            s.executeUpdate("UPDATE extra_tbl set val = '"+id+"' WHERE id = 1");
        } catch (SQLException e) {
            System.out.println(e);
         }
        } // close for check balance
        
//        make btn print or invoice
//            HashMap para = new HashMap();
//            para.put("inv_id", inid.getText()); // parameter name for ireport 
//            ReportView r = new ReportView("src\\reports\\report3.jasper",para);
//            r.setVisible(true);
          showReport("C:\\Users\\DELL\\Desktop\\Java Learn\\JAVA\\Learn from research\\Project1_inventory\\src\\reports\\reportinvoice.jrxml");
        
        
        stockup();//sold qty and stock update
    }//GEN-LAST:event_jButton4ActionPerformed
    void showReport(String reportflie){
        try{   
            JasperReport pathReport = 
            JasperCompileManager.compileReport(reportflie);
            // Prepare the parameters
            Map<String, Object> param=new HashMap<String, Object>();
            param.put("invoice_id",inid.getText());
            // Fill the report
            JasperPrint JPrint=JasperFillManager.fillReport(pathReport,param,db.mycon());
            JasperViewer.viewReport(JPrint,false);//If true is close the form   
            db.mycon().close();
  }  catch(Exception e){e.printStackTrace(); }
 }
    private void txt_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_productActionPerformed
        // TODO load unit/price
        String name = txt_product.getSelectedItem().toString();

        try {
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT barcode,price,qty,name FROM product_tbl WHERE name = '"+name+"'");
            if(rs.next()){
                jl_unit.setText(rs.getString("price"));
                jl_item.setText(rs.getString("barcode"));
//              Select_product_barcode = (rs.getString("barcode"));
                jl_stock.setText(rs.getString("qty"));
//                Select_customer_id = (rs.getString("id"));
                Select_customer_name = (rs.getString("name"));
//                txt_barcodekey.setText(rs.getString("barcode")); 
//                Select_product_name = rs.getString("name");// for name sale
                
            /// 
            
            }
            
            totalprice();
//            caculation for sale 
//            Double qty = Double.valueOf(txt_qty.getText());
//            Double price = Double.valueOf(jl_unit.getText());
//            Double total;
//            total = qty * price;
//            jl_total.setText(String.valueOf(total));
        } catch (SQLException e) {
            System.out.println(e);
        }
         
//      After select go back to qty      
            txt_qty.selectAll();
            txt_qty.requestFocus();
        
    }//GEN-LAST:event_txt_productActionPerformed

    private void txt_qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_qtyKeyReleased
        // TODO load caculation
        totalprice();
//      Add Product to table   
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            dataadd_to_table();
//      After go back to Bacode Scanner      
            txt_barcodekey.setText(""); 
            txt_barcodekey.requestFocus();
        }
    }//GEN-LAST:event_txt_qtyKeyReleased

    private void txt_customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_customerActionPerformed
        // TODO select customer name
        String customer  = txt_customer.getSelectedItem().toString();
        try {
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT id,name,status,city FROM customer_tbl WHERE name = '"+customer+"'");
            if(rs.next()){
                txt_idcus.setText(rs.getString("id"));
                jl_type.setText(rs.getString("status")); 
                jl_location.setText(rs.getString("city"));
                Select_customer_id = (rs.getString("id"));
                Select_customer_name = (rs.getString("name"));
            }            
            totalprice();
        } catch (SQLException e) {
            System.out.println(e);
        }
//        check balance
Customer_balance_check();
////        for get cid
         String name = txt_customer.getSelectedItem().toString();
//        String customer  = txt_customer.getSelectedItem().toString();
        try {
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT id,name FROM customer_tbl WHERE name = '"+name+"'");
            if(rs.next()){
                customer_id = (rs.getString("id"));
                
//                barcode = rs.getString("barcode");
            ///
            
            }
            
            totalprice();
//            caculation for sale 
//            Double qty = Double.valueOf(txt_qty.getText());
//            Double price = Double.valueOf(jl_unit.getText());
//            Double total;
//            total = qty * price;
//            jl_total.setText(String.valueOf(total));
        } catch (SQLException e) {
            System.out.println(e);
        }
        //        check balnce customer
        Customer_balance_check();
    }//GEN-LAST:event_txt_customerActionPerformed

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
        total_paid();
        
    }//GEN-LAST:event_txt_paidKeyReleased

    private void txt_barcodekeyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_barcodekeyKeyReleased
         // TODO key barcode use Scanner 
        String barcode = txt_barcodekey.getText();
//        String customer  = txt_customer.getSelectedItem().toString();
        try {
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT name,barcode,price,qty FROM product_tbl WHERE barcode = '"+barcode+"'");
            if(rs.next()){
                jl_unit.setText(rs.getString("price"));
                jl_item.setText(rs.getString("barcode"));
                jl_stock.setText(rs.getString("qty"));
                txt_product.setSelectedItem(rs.getString("name"));
                
//                This Code for use for Scanner Device//
//                Vector v = new Vector();
//                v.add(rs.getString("name"));
//                DefaultComboBoxModel com = new DefaultComboBoxModel(v);
//                txt_product.setModel(com);
            }
            
            totalprice();
//            caculation for sale 
//            Double qty = Double.valueOf(txt_qty.getText());
//            Double price = Double.valueOf(jl_unit.getText());
//            Double total;
//            total = qty * price;
//            jl_total.setText(String.valueOf(total));
        } catch (SQLException e) {
            System.out.println(e);
        }
//        Press Enter to key Qty on textfeld 
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            txt_qty.selectAll();
            txt_qty.requestFocus();
        }
        
        
    }//GEN-LAST:event_txt_barcodekeyKeyReleased

    private void txt_shippcostKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_shippcostKeyReleased
        // TODO add your handling code here:
        total_paid();
    }//GEN-LAST:event_txt_shippcostKeyReleased

    private void txt_discountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_discountKeyReleased
        // TODO add your handling code here:
        total_paid();
    }//GEN-LAST:event_txt_discountKeyReleased

    private void txt_totalqty1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalqty1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalqty1ActionPerformed

    private void txt_totalqty1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_totalqty1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalqty1KeyReleased

    private void txt_taxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_taxKeyReleased
        // TODO add your handling code here:
        total_paid();
    }//GEN-LAST:event_txt_taxKeyReleased

    private void txt_idcusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idcusActionPerformed
        // TODO key ID customer for check balance
         String idcustomer  = txt_idcus.getText();
        try {
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT id,name,status,city FROM customer_tbl WHERE id = '"+idcustomer+"'");
            if(rs.next()){
                txt_idcus.setText(rs.getString("id"));
                jl_type.setText(rs.getString("status")); 
                jl_location.setText(rs.getString("city"));
//                Select_customer_id = (rs.getString("id"));
//                Select_customer_name = (rs.getString("name"));
            }            
            totalprice();
        } catch (SQLException e) {
            System.out.println(e);
        }
//        check_
        Customer_balance_check();
    }//GEN-LAST:event_txt_idcusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date1;
    private javax.swing.JTextField grandamount;
    private javax.swing.JLabel inid;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jl_item;
    private javax.swing.JLabel jl_item1;
    private javax.swing.JLabel jl_item2;
    private javax.swing.JLabel jl_location;
    private javax.swing.JLabel jl_stock;
    private javax.swing.JLabel jl_total;
    private javax.swing.JLabel jl_type;
    private javax.swing.JLabel jl_unit;
    private javax.swing.JTextField shipping_cost;
    private javax.swing.JTextField t_discountamount;
    private javax.swing.JTextField t_taxamount;
    private javax.swing.JLabel time;
    private javax.swing.JTextField txt_balance;
    private javax.swing.JLabel txt_balancecus;
    private javax.swing.JTextField txt_barcodekey;
    private javax.swing.JComboBox txt_customer;
    private javax.swing.JTextField txt_discount;
    private javax.swing.JTextField txt_idcus;
    private javax.swing.JTextField txt_paid;
    private javax.swing.JComboBox txt_product;
    private javax.swing.JTextField txt_qty;
    private javax.swing.JTextField txt_shippcost;
    private javax.swing.JTextField txt_tax;
    private javax.swing.JTextField txt_totalamount;
    private javax.swing.JTextField txt_totalqty;
    private javax.swing.JTextField txt_totalqty1;
    // End of variables declaration//GEN-END:variables
}
