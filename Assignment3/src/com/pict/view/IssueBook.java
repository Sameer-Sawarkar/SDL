/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pict.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import library.server.User;

/**
 *
 * @author DELL_3542
 */
public class IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form issueBook
     */
    User lu;
    public IssueBook(User lu) {
        initComponents();
        this.lu=lu;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bcode = new javax.swing.JTextField();
        sroll = new javax.swing.JTextField();
        issue = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText("ISSUE A BOOK");

        jLabel2.setText("BOOK CODE");

        jLabel3.setText("ROLL NO");

        sroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                srollActionPerformed(evt);
            }
        });

        issue.setText("ISSUE");
        issue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bcode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sroll, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(issue, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bcode, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(sroll, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(issue, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void srollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_srollActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_srollActionPerformed

    private void issueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueActionPerformed
        // TODO add your handling code here:
        try
        {  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/swarali","root","root");  
            Statement stmt=con.createStatement(); 
            
            int bookCode=Integer.parseInt(bcode.getText().trim());
            int rollno=Integer.parseInt(sroll.getText().trim());
            //Date date=new Date();
           // String issueDate=date.getYear()+"/"+date.getMonth()+"/"+date.getDay();
           long millis=System.currentTimeMillis();
           java.sql.Date date=new java.sql.Date(millis);
            ResultSet rs=stmt.executeQuery("select status from Books where BookCode="+bookCode);
            if(rs.next())
            {
                String stat=rs.getString("status");
                if(stat.equals("no"))
                {
                    int flag=stmt.executeUpdate("insert into issueTable values("+bookCode+","+rollno+",'"+date+"')");
                    JOptionPane.showMessageDialog(this, "Issued successfully");
                    if(flag>0)
                    {
                        stmt.executeUpdate("update Books set status='yes' where BookCode="+bookCode);
                    }
                }
                else
                {
                   JOptionPane.showMessageDialog(this, "Book has already issued"); 
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Insertion failed");
            }
            
           //int st=stmt.executeUpdate("insert into issueTable values("+bookCode+","+rollno+",'"+issueDate+"')");
           con.close();
           
        }
        catch(Exception e)
        {
                System.out.println(e);
        }
        
        
    }//GEN-LAST:event_issueActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bcode;
    private javax.swing.JButton issue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField sroll;
    // End of variables declaration//GEN-END:variables
}
