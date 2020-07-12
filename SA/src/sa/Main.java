/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa;



import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import java.sql.Connection;
import javax.swing.JInternalFrame;
import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author ADMIN
 */
public class Main extends javax.swing.JFrame {

    Connection c = Conection.getConnection();
    String cus_id ;
    String date;
    /*String[] Value_quantity = {"","",""} ;
    String[] Value_stock = {"","",""} ;*/
    int countinsert = 0;
    double totalresource =0;
    String Product_no;
            
    
    public Main() {
        initComponents();
        bartap();
        
        
        
    }
     public Main(String user){
        initComponents();
        name_Em.setText(user);
        showemployee();
        
        
     }
     
    public void bartap(){
        Bar.setVisible(false);
        Bar.setEnabled(false);
        Closetap.setVisible(false);
        Closetap.setEnabled(false);
        Bar_Tap.setVisible(true);
        Bar_Tap.setEnabled(true);
    }
    public void active(){
        Bar.setVisible(true);
        Bar.setEnabled(true);
        Closetap.setVisible(true);
        Closetap.setEnabled(true);
        Bar_Tap.setVisible(false);
        Bar_Tap.setEnabled(false);
    }
    public void activer(JInternalFrame fr){
        Order.setVisible(false);
        Choice_manage.setVisible(false);
        Payment.setVisible(false);
        Manage.setVisible(false);
        Register.setVisible(false);
        Export_excel.setVisible(false);
        dataanalyze.setVisible(false);
        fr.setVisible(true);
    }
    
    public void showemployee(){
        try{
                
               
                String sql = "SELECT Log_name,Status FROM login where username = ?";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, name_Em.getText());
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) { 
                name_em3.setText(rs.getString("Log_name"));
                name_em2.setText(rs.getString("Status"));
                
           }
                String status = name_em2.getText();
                if("CEO".equals(status)){
                    jButton14.setEnabled(true);
                }
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void showproduct(){
        try{
              
                String sql = "SELECT * FROM products";
                PreparedStatement stmt = c.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                jTableupdate.setModel(DbUtils.resultSetToTableModel(rs));
                
           }catch(Exception e){e.printStackTrace();}
    }
    
    public void showTable(){
        try{
              
                String sql = "select orders.CategoryID,orders.ProductName,orders.Price,Quantity,Total,Stock "
                + " from orders INNER JOIN products "
                + " ON orders.ProductName = products.ProductName "
                + " where Cus_id = ? and Date = ? ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, cus_id);
                stmt.setString(2, date);
                ResultSet rs = stmt.executeQuery();
                jTableconfirme.setModel(DbUtils.resultSetToTableModel(rs));
                
           }catch(Exception e){e.printStackTrace();}
       
    } 
    public void showTablepayment(){
        try{
               
                String sql = "select orders.CategoryID,orders.ProductName,orders.Price,Quantity,Total "
                + " from orders INNER JOIN products "
                + " ON orders.ProductName = products.ProductName "
                + " where Cus_id = ? and Date = ? ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, cus_id);
                stmt.setString(2, date);
                ResultSet rs = stmt.executeQuery();
                jTablepayment.setModel(DbUtils.resultSetToTableModel(rs));
                int row = 0;
                for(int n=0;n<countinsert;n++){ //วนตามตาราง/////////////////////////////////////////////////////////
            String total = jTableconfirme.getModel().getValueAt(row,4).toString();
            totalresource += Double.parseDouble(total);
            row++;
            }
            paymenttxt.setText(""+totalresource);
            //countinsert=0;
           }catch(Exception e){e.printStackTrace();}
       
    } 
    public void Bill(){
        String Employee_n = name_em3.getText();
        String payment = paymenttxt.getText();
        String money_in = payment_input.getText();
        String money_out = payment_output.getText();
        String cus_name = nameproduct.getText();
        String phone = findphone.getText();
         DefaultTableModel model = new DefaultTableModel();
         model = (DefaultTableModel)jTablepayment.getModel();
         
         showbill.setText(showbill.getText() + "-------------------------------------------------------------------------------\n");
         showbill.setText(showbill.getText() + "ร้านขายอะไหล่ปลีกส่งรถจักรยานยนตร์(Motorcycle parts store)                       \n");
         showbill.setText(showbill.getText() + "\n");
         showbill.setText(showbill.getText() + "                     ใบเสร็จรับเงินแบบย่อ              วันที่ขาย :"+date +"      \n");
         showbill.setText(showbill.getText() + "-------------------------------------------------------------------------------\n");
         showbill.setText(showbill.getText() + "                        รายการสินค้า                    \n");
         showbill.setText(showbill.getText() + "-------------------------------------------------------------------------------\n");
         showbill.setText(showbill.getText() +"Quantity" + "\t" + "ProductName" + "\t" + "Price" + "\t" + "Total" +"\n");
         int row =0;
         for(int i =0; i < countinsert; i++)
         {  
            String name1 = jTablepayment.getModel().getValueAt(row,1).toString();
            String price1 = jTablepayment.getModel().getValueAt(row,2).toString();
            String quantity1 = jTablepayment.getModel().getValueAt(row,3).toString();
            String total1 = jTablepayment.getModel().getValueAt(row,4).toString();
         showbill.setText(showbill.getText() + quantity1  + "\t" +  name1+ "\t" + price1  + "\t"+ total1 +"\n");   
            row++;
         }
         showbill.setText(showbill.getText() + "\n");
         showbill.setText(showbill.getText() + "\n");
         
         
         showbill.setText(showbill.getText() + "\t" + "\t" + "\t" + "ยอดชำระ :" + totalresource + "\n");
         showbill.setText(showbill.getText() + "\t" + "\t" + "\t" + "รับเงิน :" + money_in + "\n");
         showbill.setText(showbill.getText() + "\t" + "\t" + "\t" + "เงินทอน :" + money_out + "\n");
         showbill.setText(showbill.getText() + "\n");
         showbill.setText(showbill.getText() + "ชื่อลูกค้า : "+cus_name+"\n");
         showbill.setText(showbill.getText() + "เบอร์โทร : "+phone+"\n");
         showbill.setText(showbill.getText() + "ชื่อพนักงานคิดเงิน : "+Employee_n+"\n");
         showbill.setText(showbill.getText() + "\n");
         showbill.setText(showbill.getText() + "-------------------------THANK YOU-----------------------------------\n");
         countinsert=0;
         totalresource=0;
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        Bar_Tap = new javax.swing.JLabel();
        Bar = new javax.swing.JPanel();
        Profile = new javax.swing.JLabel();
        Band = new javax.swing.JLabel();
        Name_Em = new javax.swing.JLabel();
        name_Em = new javax.swing.JLabel();
        Closetap = new javax.swing.JLabel();
        Status = new javax.swing.JLabel();
        name_em2 = new javax.swing.JLabel();
        name_em3 = new javax.swing.JLabel();
        Status1 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        Band1 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        Mainmenu = new javax.swing.JPanel();
        tap = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Black = new javax.swing.JPanel();
        gg = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        Order = new javax.swing.JInternalFrame();
        SetOrder = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jRadioButton17 = new javax.swing.JRadioButton();
        jRadioButton18 = new javax.swing.JRadioButton();
        jRadioButton19 = new javax.swing.JRadioButton();
        jRadioButton20 = new javax.swing.JRadioButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        findphone = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        nameproduct = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        Order_type = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        Order_name = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        Order_price = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        Order_quantity = new javax.swing.JSpinner();
        jLabel67 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        date_chooser = new com.toedter.calendar.JDateChooser();
        jButton13 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        Payment = new javax.swing.JInternalFrame();
        Setpayment = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablepayment = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        paymenttxt = new javax.swing.JTextField();
        payment_input = new javax.swing.JTextField();
        payment_output = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        showbill = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        Manage = new javax.swing.JInternalFrame();
        Setmanagement = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableupdate = new javax.swing.JTable();
        name1 = new javax.swing.JTextField();
        price1 = new javax.swing.JTextField();
        stock1 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        type1 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        searchtxt = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        typename = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        Register = new javax.swing.JInternalFrame();
        Setregister = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        phone = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        address = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        Export_excel = new javax.swing.JInternalFrame();
        Setexcel = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableexcel = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        famelogo = new javax.swing.JLabel();
        fameblack = new javax.swing.JLabel();
        Confirm = new javax.swing.JInternalFrame();
        Confirmed = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableconfirme = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        Confirm_name = new javax.swing.JTextField();
        Confirm_quantity = new javax.swing.JTextField();
        Confirm_total = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        Confirm_price = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Choice_manage = new javax.swing.JInternalFrame();
        Setchoicemanage = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        Input_product = new javax.swing.JInternalFrame();
        inputdata = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        input = new javax.swing.JTextField();
        quantity = new javax.swing.JTextField();
        price = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel49 = new javax.swing.JLabel();
        dataanalyze = new javax.swing.JInternalFrame();
        Setanalyze = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableanalyze = new javax.swing.JTable();
        jLabel44 = new javax.swing.JLabel();
        sumquantity = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        sumtotal = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel46 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        year1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Bar_Tap.setBackground(new java.awt.Color(255, 255, 255));
        Bar_Tap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/Picture/menu (1).png"))); // NOI18N
        Bar_Tap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Bar_TapMouseClicked(evt);
            }
        });
        getContentPane().add(Bar_Tap, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 70, 60));

        Bar.setBackground(new java.awt.Color(0, 0, 0));
        Bar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/Picture/man (2).png"))); // NOI18N
        Bar.add(Profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 269, 226));

        Band.setBackground(new java.awt.Color(255, 255, 255));
        Band.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        Band.setForeground(new java.awt.Color(255, 153, 0));
        Band.setText("parts store");
        Band.setToolTipText("");
        Bar.add(Band, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 170, 80));

        Name_Em.setBackground(new java.awt.Color(255, 255, 255));
        Name_Em.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        Name_Em.setForeground(new java.awt.Color(255, 255, 255));
        Name_Em.setText("Employee Name");
        Bar.add(Name_Em, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, -1, 27));

        name_Em.setBackground(new java.awt.Color(255, 255, 255));
        name_Em.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        name_Em.setForeground(new java.awt.Color(0, 255, 0));
        name_Em.setText(".");
        Bar.add(name_Em, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 480, 110, 27));

        Closetap.setBackground(new java.awt.Color(255, 255, 255));
        Closetap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/Picture/back.png"))); // NOI18N
        Closetap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClosetapMouseClicked(evt);
            }
        });
        Bar.add(Closetap, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 40, 70));

        Status.setBackground(new java.awt.Color(255, 255, 255));
        Status.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        Status.setForeground(new java.awt.Color(255, 255, 255));
        Status.setText("Username");
        Bar.add(Status, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 480, 120, 27));

        name_em2.setBackground(new java.awt.Color(255, 255, 255));
        name_em2.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        name_em2.setForeground(new java.awt.Color(51, 255, 0));
        name_em2.setText(".");
        Bar.add(name_em2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 560, 110, 27));

        name_em3.setBackground(new java.awt.Color(255, 255, 255));
        name_em3.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        name_em3.setForeground(new java.awt.Color(51, 255, 0));
        name_em3.setText(".");
        Bar.add(name_em3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 520, 110, 27));

        Status1.setBackground(new java.awt.Color(255, 255, 255));
        Status1.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        Status1.setForeground(new java.awt.Color(255, 255, 255));
        Status1.setText("Status");
        Bar.add(Status1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 560, 70, 27));
        Bar.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, -1));

        Band1.setBackground(new java.awt.Color(0, 0, 0));
        Band1.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        Band1.setForeground(new java.awt.Color(255, 255, 255));
        Band1.setText("Motorcycle ");
        Band1.setToolTipText("");
        Bar.add(Band1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 260, 80));

        jLabel43.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(102, 255, 255));
        jLabel43.setText("LOGOUT");
        jLabel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel43MouseClicked(evt);
            }
        });
        Bar.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 650, -1, -1));

        getContentPane().add(Bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 690));

        Mainmenu.setBackground(new java.awt.Color(255, 255, 255));
        Mainmenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tap.setBackground(new java.awt.Color(0, 153, 153));
        tap.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/Picture/setup (1).png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        tap.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, 90));

        jLabel7.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Management");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        tap.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 460, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/Picture/note (1).png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        tap.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 120, 140));

        jLabel12.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Register");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        tap.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 140, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/Picture/order (2).png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        tap.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 90, 110));

        jLabel5.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Order");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        tap.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 110, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/Picture/money.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        tap.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 70, 90));

        jLabel6.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Payment");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        tap.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/Picture/excel (1).png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        tap.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, -1, 70));

        jLabel10.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Export to excel");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        tap.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 570, 220, -1));

        Mainmenu.add(tap, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 740));

        Black.setBackground(new java.awt.Color(0, 102, 153));

        javax.swing.GroupLayout BlackLayout = new javax.swing.GroupLayout(Black);
        Black.setLayout(BlackLayout);
        BlackLayout.setHorizontalGroup(
            BlackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1310, Short.MAX_VALUE)
        );
        BlackLayout.setVerticalGroup(
            BlackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );

        Mainmenu.add(Black, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 610, -1, 130));

        gg.setBackground(new java.awt.Color(255, 255, 255));

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDesktopPane1mouseclicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jDesktopPane1mouseentred(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jDesktopPane1mouseexited(evt);
            }
        });
        jDesktopPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Order.setBackground(new java.awt.Color(255, 255, 255));
        Order.setVisible(false);
        Order.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SetOrder.setBackground(new java.awt.Color(51, 51, 51));
        SetOrder.setPreferredSize(new java.awt.Dimension(860, 590));
        SetOrder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("หมวดสินค้า");
        SetOrder.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));
        SetOrder.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 218, -1, -1));

        jRadioButton17.setBackground(new java.awt.Color(0, 153, 153));
        buttonGroup1.add(jRadioButton17);
        jRadioButton17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton17.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton17.setText("ชุดอะไหล่เครื่อง");
        jRadioButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton17ActionPerformed(evt);
            }
        });
        SetOrder.add(jRadioButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, -1, -1));

        jRadioButton18.setBackground(new java.awt.Color(0, 153, 153));
        buttonGroup1.add(jRadioButton18);
        jRadioButton18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton18.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton18.setText("ชุดแต่งภายนอก");
        jRadioButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton18ActionPerformed(evt);
            }
        });
        SetOrder.add(jRadioButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, -1, -1));

        jRadioButton19.setBackground(new java.awt.Color(0, 153, 153));
        buttonGroup1.add(jRadioButton19);
        jRadioButton19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton19.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton19.setText("ชุดไฟ");
        jRadioButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton19ActionPerformed(evt);
            }
        });
        SetOrder.add(jRadioButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, -1, -1));

        jRadioButton20.setBackground(new java.awt.Color(0, 153, 153));
        buttonGroup1.add(jRadioButton20);
        jRadioButton20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton20.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton20.setText("อุปกรณ์เสริม");
        jRadioButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton20ActionPerformed(evt);
            }
        });
        SetOrder.add(jRadioButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 330, -1, -1));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        SetOrder.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 540, 220));

        jPanel2.setBackground(new java.awt.Color(255, 204, 51));

        jLabel70.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Order");

        jLabel54.setBackground(new java.awt.Color(255, 255, 255));
        jLabel54.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("X");
        jLabel54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel54MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 694, Short.MAX_VALUE)
                .addComponent(jLabel54)
                .addGap(87, 87, 87))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        SetOrder.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 70));

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton7.setText("ค้นหา");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        findphone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel71.setText("เบอร์โทรลูกค้า");

        id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel68.setText("รหัสลูกค้า :");

        nameproduct.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nameproduct.setRequestFocusEnabled(false);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("ชื่อลูกค้า :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel71, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel68, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameproduct, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(findphone, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton7)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(findphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id)
                    .addComponent(jLabel68))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameproduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        SetOrder.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 540, 220));

        jPanel4.setBackground(new java.awt.Color(0, 102, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("หมวดสินค้า :");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        Order_type.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel4.add(Order_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 150, -1));

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setText("ชื่อสินค้า :");
        jPanel4.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        Order_name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel4.add(Order_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 150, -1));

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setText("ราคา :");
        jPanel4.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        Order_price.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel4.add(Order_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 150, -1));

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("จำนวนที่ต้องาร :");
        jPanel4.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        Order_quantity.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                Order_quantityStateChanged(evt);
            }
        });
        jPanel4.add(Order_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 150, 30));

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("ราคารวม :");
        jPanel4.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, -1));

        total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        total.setRequestFocusEnabled(false);
        jPanel4.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 150, -1));

        jLabel72.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("วันที่ขาย :");
        jPanel4.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, -1, -1));

        date_chooser.setDateFormatString("yyyy-MM-dd");
        date_chooser.setMaxSelectableDate(new java.util.Date(253370743302000L));
        date_chooser.setMinSelectableDate(new java.util.Date(-62135791098000L));
        jPanel4.add(date_chooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 150, 30));

        jButton13.setBackground(new java.awt.Color(255, 255, 255));
        jButton13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton13.setText("เพิ่มรายการ");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 150, -1));

        SetOrder.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, 340, 430));

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        SetOrder.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 540, 40));

        jPanel7.setBackground(new java.awt.Color(0, 153, 153));

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton9.setText("ยืนยันออเดอร์");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(105, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        SetOrder.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 520, 340, 80));

        Order.getContentPane().add(SetOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 640));

        jDesktopPane1.add(Order, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -30, 1150, 800));

        Payment.setBackground(new java.awt.Color(255, 255, 255));
        Payment.setPreferredSize(new java.awt.Dimension(906, 604));
        Payment.setVisible(false);
        Payment.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Setpayment.setBackground(new java.awt.Color(0, 0, 51));
        Setpayment.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTablepayment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTablepayment);

        Setpayment.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 590, 180));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("จำนวนเงินที่ต้องชำระ");
        Setpayment.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("รับเงินมา");
        Setpayment.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, -1, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("เงินทอน");
        Setpayment.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, -1, -1));

        paymenttxt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Setpayment.add(paymenttxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 140, -1));

        payment_input.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Setpayment.add(payment_input, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, 140, -1));

        payment_output.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Setpayment.add(payment_output, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 430, 140, -1));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton5.setText("จ่ายเงิน");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        Setpayment.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 500, 140, 70));

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton8.setText("พิมใบเสร็จ");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        Setpayment.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 500, 140, 70));

        showbill.setColumns(20);
        showbill.setRows(5);
        jScrollPane4.setViewportView(showbill);

        Setpayment.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 330, 365, 252));

        jPanel8.setBackground(new java.awt.Color(102, 102, 255));

        jLabel74.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setText("PAYMENT");

        jLabel56.setBackground(new java.awt.Color(255, 255, 255));
        jLabel56.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("X");
        jLabel56.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel56MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 605, Short.MAX_VALUE)
                .addComponent(jLabel56)
                .addGap(81, 81, 81))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel56)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Setpayment.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 70));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/Picture/mastercard.png"))); // NOI18N
        Setpayment.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 150, -1, -1));

        Payment.getContentPane().add(Setpayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 620));

        jDesktopPane1.add(Payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -30, 1150, 800));

        Manage.setBackground(new java.awt.Color(255, 255, 255));
        Manage.setVisible(false);
        Manage.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Setmanagement.setBackground(new java.awt.Color(51, 0, 51));
        Setmanagement.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ลบ");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        Setmanagement.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 450, 130, 70));

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setText("แก้ไข");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        Setmanagement.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 450, 130, 70));

        jTableupdate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableupdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableupdateMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableupdate);

        Setmanagement.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 520, 370));

        name1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Setmanagement.add(name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, 139, 29));

        price1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Setmanagement.add(price1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 320, 139, 29));

        stock1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Setmanagement.add(stock1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, 139, 29));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("คงเหลือในคลัง");
        Setmanagement.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 370, -1, -1));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("ราคา");
        Setmanagement.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 320, -1, -1));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("ชื่อสินค้า");
        Setmanagement.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, -1, -1));

        type1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Setmanagement.add(type1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 150, 139, 29));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("รหัสหมวดสินค้า");
        Setmanagement.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("ชื่อสินค้า");
        Setmanagement.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));

        searchtxt.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        searchtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchtxtKeyReleased(evt);
            }
        });
        Setmanagement.add(searchtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 180, -1));

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("ชนิดสินค้า");
        Setmanagement.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, -1, -1));

        typename.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Setmanagement.add(typename, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, 139, 29));

        jPanel11.setBackground(new java.awt.Color(153, 153, 255));

        jLabel76.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("Management");

        jLabel55.setBackground(new java.awt.Color(255, 255, 255));
        jLabel55.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("X");
        jLabel55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel55MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 564, Short.MAX_VALUE)
                .addComponent(jLabel55)
                .addGap(95, 95, 95))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel55)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Setmanagement.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 70));

        Manage.getContentPane().add(Setmanagement, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 630));

        jDesktopPane1.add(Manage, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -30, 1150, 800));

        Register.setBackground(new java.awt.Color(255, 255, 255));
        Register.setPreferredSize(new java.awt.Dimension(906, 604));
        Register.setVisible(false);
        Register.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Setregister.setBackground(new java.awt.Color(51, 51, 51));
        Setregister.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Address");
        Setregister.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        jLabel15.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Name");
        Setregister.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, -1, -1));

        jLabel18.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Phnoe");
        Setregister.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, -1, -1));
        Setregister.add(phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 176, 37));
        Setregister.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 176, 30));

        address.setColumns(20);
        address.setRows(5);
        jScrollPane1.setViewportView(address);

        Setregister.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 353, 209));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jButton3.setText("confirm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        Setregister.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 510, 160, 34));

        jPanel1.setBackground(new java.awt.Color(0, 204, 102));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Register");

        jLabel57.setBackground(new java.awt.Color(255, 255, 255));
        jLabel57.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("X");
        jLabel57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel57MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 644, Short.MAX_VALUE)
                .addComponent(jLabel57)
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 29, Short.MAX_VALUE)
                .addComponent(jLabel13))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Setregister.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 70));

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/Picture/sign-up.png"))); // NOI18N
        Setregister.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 480, 470));

        Register.getContentPane().add(Setregister, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 620));

        jDesktopPane1.add(Register, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -30, 1150, 800));

        Export_excel.setBackground(new java.awt.Color(255, 255, 255));
        Export_excel.setPreferredSize(new java.awt.Dimension(906, 604));
        Export_excel.setVisible(false);
        Export_excel.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Setexcel.setBackground(new java.awt.Color(0, 51, 51));
        Setexcel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableexcel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(jTableexcel);

        Setexcel.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 680, 360));

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton10.setText("Export");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        Setexcel.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 520, 140, 49));

        jRadioButton5.setBackground(new java.awt.Color(0, 51, 51));
        buttonGroup3.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jRadioButton5.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton5.setText("Customer");
        jRadioButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton5MouseClicked(evt);
            }
        });
        Setexcel.add(jRadioButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        jRadioButton6.setBackground(new java.awt.Color(0, 51, 51));
        buttonGroup3.add(jRadioButton6);
        jRadioButton6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jRadioButton6.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton6.setText("Login");
        jRadioButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton6MouseClicked(evt);
            }
        });
        Setexcel.add(jRadioButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jRadioButton7.setBackground(new java.awt.Color(0, 51, 51));
        buttonGroup3.add(jRadioButton7);
        jRadioButton7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jRadioButton7.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton7.setText("Orders");
        jRadioButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton7MouseClicked(evt);
            }
        });
        Setexcel.add(jRadioButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        jRadioButton8.setBackground(new java.awt.Color(0, 51, 51));
        buttonGroup3.add(jRadioButton8);
        jRadioButton8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jRadioButton8.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton8.setText("Products");
        jRadioButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton8MouseClicked(evt);
            }
        });
        Setexcel.add(jRadioButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, -1, -1));

        jPanel13.setBackground(new java.awt.Color(255, 102, 102));

        jLabel78.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("Export Excel");

        jLabel58.setBackground(new java.awt.Color(255, 255, 255));
        jLabel58.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("X");
        jLabel58.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel58MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 553, Short.MAX_VALUE)
                .addComponent(jLabel58)
                .addGap(104, 104, 104))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel58)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Setexcel.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 70));

        Export_excel.getContentPane().add(Setexcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 630));

        jDesktopPane1.add(Export_excel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -30, 1150, 800));

        jLabel42.setBackground(new java.awt.Color(255, 255, 255));
        jLabel42.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("X");
        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel42MouseClicked(evt);
            }
        });
        jDesktopPane1.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, 30, 30));

        jLabel50.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 36)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("About");
        jDesktopPane1.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));

        jLabel51.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 24)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Motorcycle parts store - 1.0.1\n");
        jDesktopPane1.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, -1, -1));

        jLabel52.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 24)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("version : 1.0.1 SE");
        jDesktopPane1.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, -1, -1));

        famelogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/Picture/scooter.png"))); // NOI18N
        jDesktopPane1.add(famelogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 330, 270, 240));

        fameblack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/Picture/black2.png"))); // NOI18N
        jDesktopPane1.add(fameblack, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 790));

        Confirm.setBackground(new java.awt.Color(255, 255, 255));
        Confirm.setPreferredSize(new java.awt.Dimension(906, 604));
        Confirm.setVisible(false);
        Confirm.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Confirmed.setBackground(new java.awt.Color(0, 51, 51));
        Confirmed.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableconfirme.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableconfirme.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableconfirmeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableconfirme);

        Confirmed.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 570, 263));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("ชื่อ");
        Confirmed.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("จำนวน");
        Confirmed.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 510, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("ราคารวม");
        Confirmed.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 560, -1, -1));

        Confirm_name.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Confirm_name.setRequestFocusEnabled(false);
        Confirmed.add(Confirm_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, 160, -1));

        Confirm_quantity.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Confirmed.add(Confirm_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 500, 160, -1));

        Confirm_total.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Confirm_total.setRequestFocusEnabled(false);
        Confirmed.add(Confirm_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, 160, -1));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("ยืนยันรายการ");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Confirmed.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 420, 140, 150));

        jButton12.setBackground(new java.awt.Color(255, 255, 255));
        jButton12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton12.setText("แก้ไข");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        Confirmed.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, 120, 60));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("ราคา");
        Confirmed.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 450, -1, -1));

        Confirm_price.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Confirm_price.setRequestFocusEnabled(false);
        Confirmed.add(Confirm_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 160, -1));

        jButton17.setBackground(new java.awt.Color(255, 255, 255));
        jButton17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton17.setText("ลบ");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        Confirmed.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 510, 120, 60));

        jPanel6.setBackground(new java.awt.Color(255, 102, 102));

        jLabel73.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("Confirmed");

        jLabel59.setBackground(new java.awt.Color(255, 255, 255));
        jLabel59.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("X");
        jLabel59.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel59MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 583, Short.MAX_VALUE)
                .addComponent(jLabel59)
                .addGap(63, 63, 63))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Confirmed.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 70));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/Picture/contact-form.png"))); // NOI18N
        Confirmed.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, -1, -1));

        Confirm.getContentPane().add(Confirmed, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 620));

        jDesktopPane1.add(Confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -30, 1150, 800));

        Choice_manage.setBackground(new java.awt.Color(255, 255, 255));
        Choice_manage.setPreferredSize(new java.awt.Dimension(906, 604));
        Choice_manage.setVisible(false);
        Choice_manage.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Setchoicemanage.setBackground(new java.awt.Color(0, 51, 51));

        jButton4.setBackground(new java.awt.Color(0, 51, 51));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/Picture/box (1).png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ลบ/แก้ไขข้อมูล");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("เพิ่มสินค้าเข้าคลังสินค้า");

        jButton14.setBackground(new java.awt.Color(0, 51, 51));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/Picture/3d.png"))); // NOI18N
        jButton14.setBorder(null);
        jButton14.setEnabled(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(0, 51, 51));
        jButton15.setForeground(new java.awt.Color(0, 51, 51));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/Picture/data-storage.png"))); // NOI18N
        jButton15.setBorder(null);
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton15MouseClicked(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("วิเคราะห์ยอดขาย");

        jLabel60.setBackground(new java.awt.Color(255, 255, 255));
        jLabel60.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("X");
        jLabel60.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel60MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout SetchoicemanageLayout = new javax.swing.GroupLayout(Setchoicemanage);
        Setchoicemanage.setLayout(SetchoicemanageLayout);
        SetchoicemanageLayout.setHorizontalGroup(
            SetchoicemanageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SetchoicemanageLayout.createSequentialGroup()
                .addGroup(SetchoicemanageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SetchoicemanageLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SetchoicemanageLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(SetchoicemanageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SetchoicemanageLayout.createSequentialGroup()
                        .addComponent(jButton15)
                        .addGap(59, 59, 59)
                        .addComponent(jButton14)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SetchoicemanageLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SetchoicemanageLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel60)
                .addGap(41, 41, 41))
        );
        SetchoicemanageLayout.setVerticalGroup(
            SetchoicemanageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SetchoicemanageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addGroup(SetchoicemanageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(SetchoicemanageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(118, 118, 118))
        );

        Choice_manage.getContentPane().add(Setchoicemanage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 620));

        jDesktopPane1.add(Choice_manage, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -30, 1150, 800));

        Input_product.setBackground(new java.awt.Color(255, 255, 255));
        Input_product.setVisible(false);
        Input_product.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputdata.setBackground(new java.awt.Color(51, 51, 51));
        inputdata.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        inputdata.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 29, -1, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("ชื่อสินค้า");
        inputdata.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, 80, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("จำนวน");
        inputdata.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, 60, -1));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("ราคา");
        inputdata.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 50, -1));

        input.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        inputdata.add(input, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, 180, 29));

        quantity.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        inputdata.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 330, 180, 30));

        price.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        inputdata.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 240, 180, 29));

        jButton16.setBackground(new java.awt.Color(255, 255, 255));
        jButton16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton16.setText("เพิ่มรายการ");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        inputdata.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 430, 150, 50));

        jPanel9.setBackground(new java.awt.Color(204, 0, 255));

        jLabel75.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("Management");

        jLabel61.setBackground(new java.awt.Color(255, 255, 255));
        jLabel61.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("X");
        jLabel61.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel61MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 567, Short.MAX_VALUE)
                .addComponent(jLabel61)
                .addGap(92, 92, 92))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel61)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        inputdata.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 70));

        jPanel10.setBackground(new java.awt.Color(0, 204, 204));
        jPanel10.setForeground(new java.awt.Color(204, 204, 255));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("หมวด");

        jRadioButton1.setBackground(new java.awt.Color(0, 204, 204));
        buttonGroup2.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setText("อะไหล่ภายใน");

        jRadioButton2.setBackground(new java.awt.Color(0, 204, 204));
        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setText("อะไหล่ภายนอก");
        jRadioButton2.setActionCommand("อะไหล่ภายใน");

        jRadioButton3.setBackground(new java.awt.Color(0, 204, 204));
        buttonGroup2.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setText("ชุดไฟ");
        jRadioButton3.setActionCommand("อะไหล่ภายใน");

        jRadioButton4.setBackground(new java.awt.Color(0, 204, 204));
        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton4.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton4.setText("อะไหล่แต่งเสริม");
        jRadioButton4.setActionCommand("อะไหล่ภายใน");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addGap(31, 31, 31)
                .addComponent(jRadioButton1)
                .addGap(19, 19, 19)
                .addComponent(jRadioButton2)
                .addGap(19, 19, 19)
                .addComponent(jRadioButton3)
                .addGap(19, 19, 19)
                .addComponent(jRadioButton4)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        inputdata.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 220, 290));

        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/Picture/management.png"))); // NOI18N
        inputdata.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, -1, -1));

        Input_product.getContentPane().add(inputdata, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 630));

        jDesktopPane1.add(Input_product, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -30, 1150, 800));

        dataanalyze.setBackground(new java.awt.Color(255, 255, 255));
        dataanalyze.setPreferredSize(new java.awt.Dimension(906, 604));
        dataanalyze.setVisible(false);
        dataanalyze.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Setanalyze.setBackground(new java.awt.Color(0, 51, 51));
        Setanalyze.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableanalyze.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(jTableanalyze);

        Setanalyze.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 620, 240));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("จำนวนสินค้าที่ขายได้");
        Setanalyze.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 240, -1));

        sumquantity.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Setanalyze.add(sumquantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 440, 170, -1));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("รายได้รวม");
        Setanalyze.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, -1, -1));

        sumtotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Setanalyze.add(sumtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 500, 170, -1));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "             SELECT MONTH", "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        Setanalyze.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 300, -1));

        jLabel46.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("YEAR");
        Setanalyze.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, -1, -1));

        jPanel12.setBackground(new java.awt.Color(0, 204, 204));

        jLabel77.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText(" Analyze");

        jLabel62.setBackground(new java.awt.Color(255, 255, 255));
        jLabel62.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("X");
        jLabel62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel62MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 572, Short.MAX_VALUE)
                .addComponent(jLabel62)
                .addGap(87, 87, 87))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel62)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Setanalyze.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 70));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/Picture/home (1).png"))); // NOI18N
        Setanalyze.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 280, 320, 330));

        jLabel53.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("MONTH");
        Setanalyze.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, -1, -1));

        year1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Setanalyze.add(year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, 110, 30));

        dataanalyze.getContentPane().add(Setanalyze, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 620));

        jDesktopPane1.add(dataanalyze, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -30, 1150, 800));

        javax.swing.GroupLayout ggLayout = new javax.swing.GroupLayout(gg);
        gg.setLayout(ggLayout);
        ggLayout.setHorizontalGroup(
            ggLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ggLayout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
                .addContainerGap())
        );
        ggLayout.setVerticalGroup(
            ggLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );

        Mainmenu.add(gg, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 950, 610));

        getContentPane().add(Mainmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 690));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Bar_TapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bar_TapMouseClicked
       active();
        
    }//GEN-LAST:event_Bar_TapMouseClicked

    private void ClosetapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClosetapMouseClicked
       bartap();
       
    }//GEN-LAST:event_ClosetapMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        activer(Order);
        showbill.setText("");
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jDesktopPane1mouseclicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDesktopPane1mouseclicked

    }//GEN-LAST:event_jDesktopPane1mouseclicked

    private void jDesktopPane1mouseentred(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDesktopPane1mouseentred
     
    }//GEN-LAST:event_jDesktopPane1mouseentred

    private void jDesktopPane1mouseexited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDesktopPane1mouseexited
     

    }//GEN-LAST:event_jDesktopPane1mouseexited

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
         activer(Order);
         showbill.setText("");
         payment_input.setText("");
         payment_output.setText("");
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        activer(Register);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        activer(Register);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        activer(Payment);
        showTablepayment();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        activer(Payment);
        showTablepayment();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        activer(Choice_manage);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        activer(Choice_manage);
       
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
         activer(Export_excel);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
         activer(Export_excel);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        
        ////////////////////////////// เพิ่มข้อมูลสินค้า ////////////////////////////////////////
        try{ 
            if (jRadioButton1.isSelected()){
            int type1 = 1;
            String name1 = "อะไหล่เครื่อง";
            String sql = "INSERT INTO products (CategoryID,CategoryName,ProductName,Price,Stock) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, ""+type1);
                stmt.setString(2, name1);
                stmt.setString(3, input.getText());
                stmt.setString(4, price.getText());
                stmt.setString(5, quantity.getText());
                stmt.executeUpdate();
            }
            if (jRadioButton2.isSelected()){
            int type2 = 2;
            String name2 = "ชุดแต่งภายนอก";
            String sql = "INSERT INTO products (CategoryID,CategoryName,ProductName,Price,Stock) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, ""+type2);
                stmt.setString(2, name2);
                stmt.setString(3, input.getText());
                stmt.setString(4, price.getText());
                stmt.setString(5, quantity.getText());
                stmt.executeUpdate();
            }
            if (jRadioButton3.isSelected()){
            int type3 = 3;
            String name3 = "ชุดไฟ";
            String sql = "INSERT INTO products (CategoryID,CategoryName,ProductName,Price,Stock) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = c.prepareStatement(sql);   
                stmt.setString(1, ""+type3);
                stmt.setString(2, name3);
                stmt.setString(3, input.getText());
                stmt.setString(4, price.getText());
                stmt.setString(5, quantity.getText());
                stmt.executeUpdate();
            }
            if (jRadioButton4.isSelected()){
            int type4 = 4;
            String name4 = "อุปกรณ์เสริม";
            String sql = "INSERT INTO products (CategoryID,CategoryName,ProductName,Price,Stock) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = c.prepareStatement(sql);   
                stmt.setString(1, ""+type4);
                stmt.setString(2, name4);
                stmt.setString(3, input.getText());
                stmt.setString(4, price.getText());
                stmt.setString(5, quantity.getText());
                stmt.executeUpdate();
            }
            
            JOptionPane.showMessageDialog(null, "การเพิ่มข้อมูลสำเร็จ","เพิ่มข้อมูลสินค้า",JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {e.printStackTrace();}
         input.setText("");
         price.setText("");
         quantity.setText("");
        
        
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        activer(Input_product);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jRadioButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton17ActionPerformed
        
        //////////////////////////////// สินค้าประเภท ภายใน ////////////////////////
        try{
                String sql = "SELECT CategoryID,ProductName,Price,Stock FROM products where CategoryID = 1 HAVING Stock >= 1";
                PreparedStatement stmt = c.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
                }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_jRadioButton17ActionPerformed

    private void jRadioButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton18ActionPerformed
       
        //////////////////////////////// สินค้าประเภท ภายนอก ////////////////////////
        try{
                String sql = "SELECT CategoryID,ProductName,Price,Stock FROM products where CategoryID = 2 HAVING Stock >= 1";
                PreparedStatement stmt = c.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
                }catch(Exception e){e.printStackTrace();}  
    }//GEN-LAST:event_jRadioButton18ActionPerformed

    private void jRadioButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton19ActionPerformed
       
        //////////////////////////////// สินค้าประเภท ชุดไฟ ////////////////////////
        try{
                String sql = "SELECT CategoryID,ProductName,Price,Stock FROM products where CategoryID = 3 HAVING Stock >= 1";
                PreparedStatement stmt = c.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
                }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_jRadioButton19ActionPerformed

    private void jRadioButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton20ActionPerformed
        
        //////////////////////////////// สินค้าประเภท อุปกรณ์เสริม ////////////////////////
        try{
                String sql = "SELECT CategoryID,ProductName,Price,Stock FROM products where CategoryID = 4 HAVING Stock >= 1";
                PreparedStatement stmt = c.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
                }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_jRadioButton20ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          
        //////////////////////////////// สินค้าประเภท เก็บประวัติลูกค้า ////////////////////////
        try{
               String sql = "INSERT INTO customer (Cus_name,Phone,Address) VALUES (?,?,?)";
            PreparedStatement stmt = c.prepareStatement(sql);   
                stmt.setString(1, name.getText());
                stmt.setString(2, phone.getText());
                stmt.setString(3, address.getText());
                stmt.executeUpdate();
           
           JOptionPane.showMessageDialog(null, "การเพิ่มข้อมูลสำเร็จ","กรอกข้อมูลลูกค้า",JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {e.printStackTrace();}
         name.setText("");
         phone.setText("");
         address.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       
        //////////////////////////////// สินค้าประเภท ค้นหาข้อมูลลูกค้า ////////////////////////
        try
       {
        String sql = "select Cus_id,Cus_name from customer where Phone = ?";
        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setString(1, findphone.getText());
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) { 
            id.setText(rs.getString("Cus_id"));
            nameproduct.setText(rs.getString("Cus_name"));
           
        }
        else{
            JOptionPane.showMessageDialog(this,"เบอร์โทรศัพท์ไม่ถูกต้อง");
        }
           
       }catch(Exception e){ 
           JOptionPane.showMessageDialog(null,e);}
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        
        //////////////////////////////// สินค้าประเภท เลือกสินค้าในตาราง Product ////////////////////////
        
        DefaultTableModel model = (DefaultTableModel)jTable5.getModel();
        int row =  jTable5.getSelectedRow();
       Order_type.setText(model.getValueAt(row, 0).toString());
       Order_name.setText(model.getValueAt(row, 1).toString());
       Order_price.setText(model.getValueAt(row, 2).toString());
       
    }//GEN-LAST:event_jTable5MouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        
        //////////////////////////////// เพิ่มสินค้าไปยัง table : Order ////////////////////////
        
        date = ((JTextField)date_chooser.getDateEditor().getUiComponent()).getText();
        cus_id = id.getText();
        int qty = Integer.parseInt(Order_quantity.getValue().toString());
        try{ 
             
           String sql = "INSERT INTO orders (CategoryID,ProductName,Price,Quantity,Total,Date,Cus_id) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, Order_type.getText());
            stmt.setString(2, Order_name.getText());
            stmt.setString(3, Order_price.getText());
            stmt.setString(4, ""+qty);
            stmt.setString(5, total.getText());
            
            stmt.setString(6, date);
            stmt.setString(7, cus_id);
            stmt.executeUpdate();
            countinsert++;
            JOptionPane.showMessageDialog(null, "ทำรายการสำเร็จ","รายการสั่งซื้อ",JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {e.printStackTrace();
        JOptionPane.showMessageDialog(null, "การทำรายการผิดพลาด","เกิดข้อผิดพลาด",JOptionPane.INFORMATION_MESSAGE);
        }
         Order_type.setText("");
         Order_name.setText("");
         total.setText("");
         
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
         
        
        //////////////////////////////// Confirm สินค้า  ////////////////////////
        activer(Confirm);
         try 
         {
          showTable();
        
       }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTableconfirmeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableconfirmeMouseClicked
       
        //////////////////////////////// เลือกรายการใน jTableconfirm ////////////////////////
       
        DefaultTableModel model = (DefaultTableModel)jTableconfirme.getModel();
        int row =  jTableconfirme.getSelectedRow();
       Confirm_name.setText(model.getValueAt(row, 1).toString());
       Confirm_price.setText(model.getValueAt(row, 2).toString());
       Confirm_quantity.setText(model.getValueAt(row, 3).toString());
       Confirm_total.setText(model.getValueAt(row, 4).toString());
            
    }//GEN-LAST:event_jTableconfirmeMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
         
        //////////////////////////////// ยืนยันออเดอร์ ////////////////////////
        try{
         int row = 0;
         String sql = "UPDATE `products` SET `Stock`= ? where ProductName = ?";
        PreparedStatement stmt = c.prepareStatement(sql);
            for(int n=0;n<countinsert;n++){ 
                
            String Name = jTableconfirme.getModel().getValueAt(row,1).toString();
            String quantity = jTableconfirme.getModel().getValueAt(row,3).toString();
            String stock = jTableconfirme.getModel().getValueAt(row,5).toString();
            Double numstock = Double.parseDouble(stock);
            Double numquantity = Double.parseDouble(quantity);
            double sum = numstock-numquantity;
            stmt.setString(1, ""+sum);
            stmt.setString(2, Name);
            stmt.execute();
            row++;
            }
            
        JOptionPane.showMessageDialog(null, "ส่งรายการออเดอร์แล้ว");
         
         
     }catch (Exception e) {e.printStackTrace();}  
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        
        //////////////////////////////// แก้ไขรายการสินค้าใน หน้า Confirm ////////////////////////
        try{  
        int quantity = Integer.parseInt(Confirm_quantity.getText());
        int price = Integer.parseInt(Confirm_price.getText());
        double total = quantity*price;
        String sql = "UPDATE `orders` SET `Quantity`= ?,`Total`= ? where ProductName = ?";
        PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, Confirm_quantity.getText());
            stmt.setString(2, ""+total);
            stmt.setString(3, Confirm_name.getText());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "แก้ไขรายการแล้ว");
              
           }catch (Exception e) {e.printStackTrace();}    
        showTable();
        Confirm_name.setText("");
        Confirm_price.setText("");
        Confirm_total.setText("");
        Confirm_quantity.setText("");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        
        //////////////////////////////// ชำระเงินพร้อม ////////////////////////
        double money_in = Double.parseDouble(payment_input.getText());
        double money_out = money_in-totalresource;
        payment_output.setText(""+money_out);
        Bill();
        
        
        
    }//GEN-LAST:event_jButton5MouseClicked

    private void Order_quantityStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_Order_quantityStateChanged
        //////////////// คิดราคาสินค้าอัตโนมัต ////////////////
        int qty = Integer.parseInt(Order_quantity.getValue().toString());
        int price = Integer.parseInt(Order_price.getText());
        double total1 = qty*price;
        total.setText(String.valueOf(total1));   
    }//GEN-LAST:event_Order_quantityStateChanged

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       try
       {    
          
           showbill.print();
           
           JOptionPane.showMessageDialog(null, "ออกใบเสร็จแล้ว");
       }catch(Exception e){}
       payment_input.setText("");
       payment_output.setText("");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseClicked
      activer(Manage);   
      showproduct();
    }//GEN-LAST:event_jButton15MouseClicked

    private void jTableupdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableupdateMouseClicked
        ////////////////// แก้ไข คลังสินค้า ///////////////////////
         DefaultTableModel model = (DefaultTableModel)jTableupdate.getModel();
        int row =  jTableupdate.getSelectedRow();
       Product_no = jTableupdate.getModel().getValueAt(row,0).toString();
       type1.setText(model.getValueAt(row, 1).toString());
       typename.setText(model.getValueAt(row, 2).toString());
       name1.setText(model.getValueAt(row, 3).toString());
       price1.setText(model.getValueAt(row,4).toString());
       stock1.setText(model.getValueAt(row, 5).toString());
        
        
        
        
        
    }//GEN-LAST:event_jTableupdateMouseClicked

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
       /////// อัพเดต table jtableupdate ////////////
        try{
        
        String sql = "UPDATE `products` SET `CategoryID`= ?,`CategoryName`= ?,`ProductName`= ? ,`Price`= ?,`Stock`= ? WHERE Product_no = ?";
        PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, type1.getText());
            stmt.setString(2, typename.getText());
            stmt.setString(3, name1.getText());
            stmt.setString(4, price1.getText());
            stmt.setString(5, stock1.getText());
            stmt.setString(6, Product_no);
        stmt.execute();
        JOptionPane.showMessageDialog(null, "แก้ไขข้อมูลสินค้าแล้ว");
              
           }catch (Exception e) {e.printStackTrace();}                 
           showproduct();         
           type1.setText("");
           name1.setText("");
           price1.setText("");
           stock1.setText("");
           typename.setText("");
        
        
        
        
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
       /////// ลบสินค้า ในตาราง jTableupdate ///////////
       try{
           int row = jTableupdate.getSelectedRow();
           String pname = jTableupdate.getModel().getValueAt(row,2).toString();
           String sql = "DELETE FROM `products` WHERE ProductName = ? ";
           PreparedStatement stmt = c.prepareStatement(sql);
           stmt.setString(1, pname);
           stmt.execute();
           JOptionPane.showMessageDialog(null, "ลบข้อมูลสินค้าแล้ว");
           }catch (Exception e) {e.printStackTrace();}   
           showproduct();
        
        
        
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void searchtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtxtKeyReleased
        /////////////////  ค้นหา /////////////////////
        String txt = searchtxt.getText();
        try{
                if(txt.isEmpty()){
                    showproduct();
                }else{
                String sql = "SELECT * FROM products where ProductName = ?";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1, txt);
                ResultSet rs = stmt.executeQuery();
                jTableupdate.setModel(DbUtils.resultSetToTableModel(rs));
                }
           }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_searchtxtKeyReleased

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        ////////////////////////// ลบสินค้า หน้า confrime //////////////////////////
        try{
           int row = jTableconfirme.getSelectedRow();
           String pname = jTableconfirme.getModel().getValueAt(row,1).toString();
           String sql = "DELETE FROM `orders` WHERE ProductName = ? ";
           PreparedStatement stmt = c.prepareStatement(sql);
           stmt.setString(1, pname);
           stmt.execute();
           JOptionPane.showMessageDialog(null, "ลบข้อมูลสินค้าแล้ว");
           }catch (Exception e) {e.printStackTrace();}   
           showTable();
           countinsert--;
        
        
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton5MouseClicked
        try{
                String sql = "SELECT * FROM customer";
                PreparedStatement stmt = c.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                jTableexcel.setModel(DbUtils.resultSetToTableModel(rs));
                }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_jRadioButton5MouseClicked

    private void jRadioButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton6MouseClicked
        try{
                String sql = "SELECT * FROM login";
                PreparedStatement stmt = c.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                jTableexcel.setModel(DbUtils.resultSetToTableModel(rs));
                }catch(Exception e){e.printStackTrace();}
        
    }//GEN-LAST:event_jRadioButton6MouseClicked

    private void jRadioButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton7MouseClicked
        try{
                String sql = "SELECT * FROM orders";
                PreparedStatement stmt = c.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                jTableexcel.setModel(DbUtils.resultSetToTableModel(rs));
                }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_jRadioButton7MouseClicked

    private void jRadioButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton8MouseClicked
        try{
                String sql = "SELECT * FROM products";
                PreparedStatement stmt = c.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                jTableexcel.setModel(DbUtils.resultSetToTableModel(rs));
                }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_jRadioButton8MouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
         //////////////////// export /////////////////
        try{
        XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("sheet1");
            DefaultTableModel dtm = (DefaultTableModel) jTableexcel.getModel();
            if(jRadioButton5.isSelected()){
            String[] colName = {"Cus_id", "Cus_name", "Address", "Phone"};
            for (int col = 0; col < 1; col++) {
                Row row = sheet.createRow(col);
                for (int col2 = 0; col2 < colName.length; col2++) {
                    String v = (String) colName[col2];
                    Cell c = row.createCell(col2);
                    c.setCellValue(v);
                }
            }
            }
            if(jRadioButton6.isSelected()){
            String[] colName = {"Log_id", "Log_name", "Username", "Password", "Status"};
            for (int col = 0; col < 1; col++) {
                Row row = sheet.createRow(col);
                for (int col2 = 0; col2 < colName.length; col2++) {
                    String v = (String) colName[col2];
                    Cell c = row.createCell(col2);
                    c.setCellValue(v);
                }
            }
            }
            if(jRadioButton7.isSelected()){
            String[] colName = {"Order_id", "CategoryID", "ProductName", "Price", "Quantity", "Total", "Date", "Cus_id"};
            for (int col = 0; col < 1; col++) {
                Row row = sheet.createRow(col);
                for (int col2 = 0; col2 < colName.length; col2++) {
                    String v = (String) colName[col2];
                    Cell c = row.createCell(col2);
                    c.setCellValue(v);
                }
            }
            }
            if(jRadioButton8.isSelected()){
            String[] colName = {"Product_no", "CategoryID", "CategoryName", "ProductName", "Price", "Stock"};
            for (int col = 0; col < 1; col++) {
                Row row = sheet.createRow(col);
                for (int col2 = 0; col2 < colName.length; col2++) {
                    String v = (String) colName[col2];
                    Cell c = row.createCell(col2);
                    c.setCellValue(v);
                }
            }
            }
            for (int i = 1; i < dtm.getRowCount()+1; i++) {

                Row row = sheet.createRow(i);
                for (int j = 0; j < dtm.getColumnCount(); j++) {
                    String v = String.valueOf(dtm.getValueAt(i - 1, j));
                    Cell c = row.createCell(j);
                    c.setCellValue(v);
                }
            }
      
            String FILE_NAME = "D:/export file excel.xlsx";
            FileOutputStream fos =  new FileOutputStream(FILE_NAME);
            workbook.write(fos);
            workbook.close();
            JOptionPane.showMessageDialog(rootPane, "เก็บข้อมูลไปยัง D:/export file excel.xlsx");
            }catch(Exception e){e.printStackTrace();}
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        activer(dataanalyze);
         try{
                String sql = "SELECT * FROM orders";
                PreparedStatement stmt = c.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                jTableanalyze.setModel(DbUtils.resultSetToTableModel(rs));
                }catch(Exception e){e.printStackTrace();}
        
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
         int Jan = 1;
         int Feb = 2;
         int March = 3;
         int April =4;
         int May =5;
         int June =6;
         int July = 7;
         int Au = 8;
         int Sep =9;
         int Oc =10;
         int Nov =11;
         int De =12;
        
     if(jComboBox1.getSelectedIndex()==0){
       try
                {
             
                String sql = " SELECT SUM(Total),SUM(Quantity) FROM orders WHERE YEAR(Date) = ? ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,year1.getText());
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    sumquantity.setText(rs.getString("SUM(Quantity)"));
                    sumtotal.setText(rs.getString("SUM(Total)"));
                }
                }catch(Exception e){e.printStackTrace();}
       try{
                String sql = "SELECT * FROM orders where YEAR(Date) = ?";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,year1.getText());
                ResultSet rs = stmt.executeQuery();
                jTableanalyze.setModel(DbUtils.resultSetToTableModel(rs));
                
                }catch(Exception e){e.printStackTrace();}    
     }else if(jComboBox1.getSelectedIndex()==1){       
        try
        {
             
                String sql = " SELECT SUM(Total),SUM(Quantity) FROM orders WHERE MONTH(Date) = ? and YEAR(Date) = ? ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+Jan);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    sumquantity.setText(rs.getString("SUM(Quantity)"));
                    sumtotal.setText(rs.getString("SUM(Total)"));
                }
                }catch(Exception e){e.printStackTrace();}
       try{
                String sql = "SELECT * FROM orders where MONTH(Date) = ? and YEAR(Date) = ?";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+Jan);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                jTableanalyze.setModel(DbUtils.resultSetToTableModel(rs));
                
                }catch(Exception e){e.printStackTrace();}    
       
     }else if(jComboBox1.getSelectedIndex()==2){       
        try
        {
             
                String sql = " SELECT SUM(Total),SUM(Quantity) FROM orders WHERE MONTH(Date) = ? and YEAR(Date) = ? ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+Feb);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    sumquantity.setText(rs.getString("SUM(Quantity)"));
                    sumtotal.setText(rs.getString("SUM(Total)"));
                }
                }catch(Exception e){e.printStackTrace();}
       try{
                String sql = "SELECT * FROM orders where MONTH(Date) = ? and YEAR(Date) = ?";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+Feb);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                jTableanalyze.setModel(DbUtils.resultSetToTableModel(rs));
                
                }catch(Exception e){e.printStackTrace();}   
       
       }else if(jComboBox1.getSelectedIndex()==3){       
        try
        {
             
                String sql = " SELECT SUM(Total),SUM(Quantity) FROM orders WHERE MONTH(Date) = ? and YEAR(Date) = ? ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+March);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    sumquantity.setText(rs.getString("SUM(Quantity)"));
                    sumtotal.setText(rs.getString("SUM(Total)"));
                }
                }catch(Exception e){e.printStackTrace();}
       try{
                String sql = "SELECT * FROM orders where MONTH(Date) = ? and YEAR(Date) = ?";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+March);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                jTableanalyze.setModel(DbUtils.resultSetToTableModel(rs));
                
                }catch(Exception e){e.printStackTrace();}   
       
       }else if(jComboBox1.getSelectedIndex()==4){       
        try
        {
             
                String sql = " SELECT SUM(Total),SUM(Quantity) FROM orders WHERE MONTH(Date) = ? and YEAR(Date) = ? ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+April);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    sumquantity.setText(rs.getString("SUM(Quantity)"));
                    sumtotal.setText(rs.getString("SUM(Total)"));
                }
                }catch(Exception e){e.printStackTrace();}
       try{
                String sql = "SELECT * FROM orders where MONTH(Date) = ? and YEAR(Date) = ?";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+April);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                jTableanalyze.setModel(DbUtils.resultSetToTableModel(rs));
                
                }catch(Exception e){e.printStackTrace();}    
       
       }else if(jComboBox1.getSelectedIndex()==5){       
        try
        {
             
                String sql = " SELECT SUM(Total),SUM(Quantity) FROM orders WHERE MONTH(Date) = ? and YEAR(Date) = ? ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+May);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    sumquantity.setText(rs.getString("SUM(Quantity)"));
                    sumtotal.setText(rs.getString("SUM(Total)"));
                }
                }catch(Exception e){e.printStackTrace();}
       try{
                String sql = "SELECT * FROM orders where MONTH(Date) = ? and YEAR(Date) = ?";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+May);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                jTableanalyze.setModel(DbUtils.resultSetToTableModel(rs));
                
                }catch(Exception e){e.printStackTrace();}    
      
       }else if(jComboBox1.getSelectedIndex()==6){       
        try
        {
             
                String sql = " SELECT SUM(Total),SUM(Quantity) FROM orders WHERE MONTH(Date) = ? and YEAR(Date) = ? ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+June);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    sumquantity.setText(rs.getString("SUM(Quantity)"));
                    sumtotal.setText(rs.getString("SUM(Total)"));
                }
                }catch(Exception e){e.printStackTrace();}
       try{
                String sql = "SELECT * FROM orders where MONTH(Date) = ? and YEAR(Date) = ?";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+June);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                jTableanalyze.setModel(DbUtils.resultSetToTableModel(rs));
                
                }catch(Exception e){e.printStackTrace();}    
        
       }else if(jComboBox1.getSelectedIndex()==7){       
        try
        {
             
                String sql = " SELECT SUM(Total),SUM(Quantity) FROM orders WHERE MONTH(Date) = ? and YEAR(Date) = ? ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+July);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    sumquantity.setText(rs.getString("SUM(Quantity)"));
                    sumtotal.setText(rs.getString("SUM(Total)"));
                }
                }catch(Exception e){e.printStackTrace();}
       try{
                String sql = "SELECT * FROM orders where MONTH(Date) = ? and YEAR(Date) = ?";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+July);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                jTableanalyze.setModel(DbUtils.resultSetToTableModel(rs));
                
                }catch(Exception e){e.printStackTrace();}    
        
       }else if(jComboBox1.getSelectedIndex()==8){       
        try
        {
             
                String sql = " SELECT SUM(Total),SUM(Quantity) FROM orders WHERE MONTH(Date) = ? and YEAR(Date) = ? ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+Au);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    sumquantity.setText(rs.getString("SUM(Quantity)"));
                    sumtotal.setText(rs.getString("SUM(Total)"));
                }
                }catch(Exception e){e.printStackTrace();}
       try{
                String sql = "SELECT * FROM orders where MONTH(Date) = ? and YEAR(Date) = ?";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+Au);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                jTableanalyze.setModel(DbUtils.resultSetToTableModel(rs));
                
                }catch(Exception e){e.printStackTrace();}    
       
       
       }else if(jComboBox1.getSelectedIndex()==9){       
        try
        {
             
                String sql = " SELECT SUM(Total),SUM(Quantity) FROM orders WHERE MONTH(Date) = ? and YEAR(Date) = ? ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+Sep);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    sumquantity.setText(rs.getString("SUM(Quantity)"));
                    sumtotal.setText(rs.getString("SUM(Total)"));
                }
                }catch(Exception e){e.printStackTrace();}
       try{
                String sql = "SELECT * FROM orders where MONTH(Date) = ? and YEAR(Date) = ?";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+Sep);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                jTableanalyze.setModel(DbUtils.resultSetToTableModel(rs));
                
                }catch(Exception e){e.printStackTrace();}   
       
       }else if(jComboBox1.getSelectedIndex()==10){       
        try
        {
             
                String sql = " SELECT SUM(Total),SUM(Quantity) FROM orders WHERE MONTH(Date) = ? and YEAR(Date) = ? ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+Oc);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    sumquantity.setText(rs.getString("SUM(Quantity)"));
                    sumtotal.setText(rs.getString("SUM(Total)"));
                }
                }catch(Exception e){e.printStackTrace();}
       try{
                String sql = "SELECT * FROM orders where MONTH(Date) = ? and YEAR(Date) = ?";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+Oc);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                jTableanalyze.setModel(DbUtils.resultSetToTableModel(rs));
                
                }catch(Exception e){e.printStackTrace();}   
       
       }else if(jComboBox1.getSelectedIndex()==11){       
        try
        {
             
                String sql = " SELECT SUM(Total),SUM(Quantity) FROM orders WHERE MONTH(Date) = ? and YEAR(Date) = ? ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+Nov);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    sumquantity.setText(rs.getString("SUM(Quantity)"));
                    sumtotal.setText(rs.getString("SUM(Total)"));
                }
                }catch(Exception e){e.printStackTrace();}
       try{
                String sql = "SELECT * FROM orders where MONTH(Date) = ? and YEAR(Date) = ?";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+Nov);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                jTableanalyze.setModel(DbUtils.resultSetToTableModel(rs));
                
                }catch(Exception e){e.printStackTrace();}  
       
       }else if(jComboBox1.getSelectedIndex()==12){       
        try
        {
             
                String sql = " SELECT SUM(Total),SUM(Quantity) FROM orders WHERE MONTH(Date) = ? and YEAR(Date) = ? ";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+De);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    sumquantity.setText(rs.getString("SUM(Quantity)"));
                    sumtotal.setText(rs.getString("SUM(Total)"));
                }
                }catch(Exception e){e.printStackTrace();}
       try{
                String sql = "SELECT * FROM orders where MONTH(Date) = ? and YEAR(Date) = ?";
                PreparedStatement stmt = c.prepareStatement(sql);
                stmt.setString(1,""+De);
                stmt.setString(2,year1.getText());
                ResultSet rs = stmt.executeQuery();
                jTableanalyze.setModel(DbUtils.resultSetToTableModel(rs));
                
                }catch(Exception e){e.printStackTrace();}   
       }
    }//GEN-LAST:event_jComboBox1ActionPerformed
    
    private void jLabel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseClicked
        Login_sa login = new Login_sa();
        login.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jLabel43MouseClicked

    private void jLabel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel42MouseClicked

    private void jLabel55MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel55MouseClicked
         System.exit(0);
    }//GEN-LAST:event_jLabel55MouseClicked

    private void jLabel54MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel54MouseClicked
       System.exit(0);
    }//GEN-LAST:event_jLabel54MouseClicked

    private void jLabel56MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel56MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel56MouseClicked

    private void jLabel57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel57MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel57MouseClicked

    private void jLabel58MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel58MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel58MouseClicked

    private void jLabel59MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel59MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel59MouseClicked

    private void jLabel60MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel60MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel60MouseClicked

    private void jLabel61MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel61MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel61MouseClicked

    private void jLabel62MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel62MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel62MouseClicked
        
 
     
     
     
     
     
     
     
    
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Band;
    private javax.swing.JLabel Band1;
    private javax.swing.JPanel Bar;
    private javax.swing.JLabel Bar_Tap;
    private javax.swing.JPanel Black;
    private javax.swing.JInternalFrame Choice_manage;
    private javax.swing.JLabel Closetap;
    private javax.swing.JInternalFrame Confirm;
    private javax.swing.JTextField Confirm_name;
    private javax.swing.JTextField Confirm_price;
    private javax.swing.JTextField Confirm_quantity;
    private javax.swing.JTextField Confirm_total;
    private javax.swing.JPanel Confirmed;
    private javax.swing.JInternalFrame Export_excel;
    private javax.swing.JInternalFrame Input_product;
    private javax.swing.JPanel Mainmenu;
    private javax.swing.JInternalFrame Manage;
    private javax.swing.JLabel Name_Em;
    private javax.swing.JInternalFrame Order;
    private javax.swing.JTextField Order_name;
    private javax.swing.JTextField Order_price;
    private javax.swing.JSpinner Order_quantity;
    private javax.swing.JTextField Order_type;
    private javax.swing.JInternalFrame Payment;
    private javax.swing.JLabel Profile;
    private javax.swing.JInternalFrame Register;
    private javax.swing.JPanel SetOrder;
    private javax.swing.JPanel Setanalyze;
    private javax.swing.JPanel Setchoicemanage;
    private javax.swing.JPanel Setexcel;
    private javax.swing.JPanel Setmanagement;
    private javax.swing.JPanel Setpayment;
    private javax.swing.JPanel Setregister;
    private javax.swing.JLabel Status;
    private javax.swing.JLabel Status1;
    private javax.swing.JTextArea address;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JInternalFrame dataanalyze;
    private com.toedter.calendar.JDateChooser date_chooser;
    private javax.swing.JLabel fameblack;
    private javax.swing.JLabel famelogo;
    private javax.swing.JTextField findphone;
    private javax.swing.JPanel gg;
    private javax.swing.JLabel id;
    private javax.swing.JTextField input;
    private javax.swing.JPanel inputdata;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JDesktopPane jDesktopPane1;
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
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton17;
    private javax.swing.JRadioButton jRadioButton18;
    private javax.swing.JRadioButton jRadioButton19;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton20;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTableanalyze;
    private javax.swing.JTable jTableconfirme;
    private javax.swing.JTable jTableexcel;
    private javax.swing.JTable jTablepayment;
    private javax.swing.JTable jTableupdate;
    private javax.swing.JTextField name;
    private javax.swing.JTextField name1;
    private javax.swing.JLabel name_Em;
    private javax.swing.JLabel name_em2;
    private javax.swing.JLabel name_em3;
    private javax.swing.JTextField nameproduct;
    private javax.swing.JTextField payment_input;
    private javax.swing.JTextField payment_output;
    private javax.swing.JTextField paymenttxt;
    private javax.swing.JTextField phone;
    private javax.swing.JTextField price;
    private javax.swing.JTextField price1;
    private javax.swing.JTextField quantity;
    private javax.swing.JTextField searchtxt;
    private javax.swing.JTextArea showbill;
    private javax.swing.JTextField stock1;
    private javax.swing.JTextField sumquantity;
    private javax.swing.JTextField sumtotal;
    private javax.swing.JPanel tap;
    private javax.swing.JTextField total;
    private javax.swing.JTextField type1;
    private javax.swing.JTextField typename;
    private javax.swing.JTextField year1;
    // End of variables declaration//GEN-END:variables
 
}
