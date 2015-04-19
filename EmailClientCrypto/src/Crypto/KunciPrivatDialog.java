package Crypto;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.*;

/**
 *
 * @author Andarias Silvanus
 */
public class KunciPrivatDialog extends javax.swing.JDialog {
    private BigInteger privateKey = BigInteger.ZERO;
    /**
     * Creates new form KunciPrivatDialog
     */
    public KunciPrivatDialog(Dialog parent) throws IOException {
        super(parent, true);
        setLocation (320,100);
        setTitle("Input Kunci Privat");
        initComponents();
        File f = new File(".pri");
        if(f.exists() && !f.isDirectory()) {
            readPrivateKey();
            PrivatField.setText(privateKey.toString());
        }
    }
    
    private void actionConnect() {
        if (PrivatField.getText().trim().length() < 1){
            JOptionPane.showMessageDialog(this,
                    "Mohon isikan kunci privat",
                    "Missing Setting(s)", JOptionPane.ERROR_MESSAGE);
            return;
        }
        privateKey = new BigInteger(PrivatField.getText());
        // Close dialog.
        dispose();
    }
    
    public BigInteger getPrivatKey() {
        return privateKey;
    }
    
    //Read private key from .pri file
    private void readPrivateKey() throws IOException{
        String file = ".pri";
        Path path = Paths.get(file);
        byte[] bytes = Files.readAllBytes(path);
        privateKey = new BigInteger(bytes);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PrivatField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        SubmitBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Masukkan Kunci Privat Anda:");

        SubmitBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SubmitBtn.setText("Submit");
        SubmitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(SubmitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(PrivatField, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PrivatField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SubmitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SubmitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitBtnActionPerformed
        actionConnect();
    }//GEN-LAST:event_SubmitBtnActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField PrivatField;
    private javax.swing.JButton SubmitBtn;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
