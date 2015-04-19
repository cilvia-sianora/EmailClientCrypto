package Crypto;

import ecdsa.ECDSA;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Andarias Silvanus
 */
public class ComposeDialog extends javax.swing.JDialog {
    private ECDSA ecdsa;
    /**
     * Creates new form ComposeDialog
     */
    public ComposeDialog(Frame parent) {
        super(parent, true);
        setLocation (320,100);
        setTitle("Compose Email");
        initComponents();
        ecdsa = new ECDSA();
    }
    
    private void actionConnect() {
        if (ToField.getText().trim().length() < 1){
            JOptionPane.showMessageDialog(this,
                    "Mohon isikan alamat penerima email",
                    "Missing Setting(s)", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Close dialog.
        dispose();
    }
    
    public String getRecipient() {
        return ToField.getText();
    }
    
    public String getSubject() {
        return SubjectField.getText();
    }
    
    public String getContent() {
        return ContentArea.getText();
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
        ToField = new javax.swing.JTextField();
        SubjectField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ContentArea = new javax.swing.JTextArea();
        ComposeBtn = new javax.swing.JButton();
        EncryptBox = new javax.swing.JCheckBox();
        DigitalSignBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("To:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Subject:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Content:");

        ContentArea.setColumns(20);
        ContentArea.setRows(5);
        jScrollPane1.setViewportView(ContentArea);

        ComposeBtn.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        ComposeBtn.setText("Kirim");
        ComposeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComposeBtnActionPerformed(evt);
            }
        });

        EncryptBox.setText("Enkripsi Pesan");
        EncryptBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EncryptBoxActionPerformed(evt);
            }
        });

        DigitalSignBox.setText("Pasang Tanda Tangan Digital");
        DigitalSignBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DigitalSignBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SubjectField)
                            .addComponent(ToField)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ComposeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 25, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(EncryptBox)
                                .addGap(18, 18, 18)
                                .addComponent(DigitalSignBox))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ToField, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(SubjectField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EncryptBox)
                    .addComponent(DigitalSignBox))
                .addGap(18, 18, 18)
                .addComponent(ComposeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComposeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComposeBtnActionPerformed
        actionConnect();
    }//GEN-LAST:event_ComposeBtnActionPerformed

    // CheckBox Digital Signature
    private void DigitalSignBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DigitalSignBoxActionPerformed
        if (ContentArea.getText().trim().length() < 1){
            JOptionPane.showMessageDialog(this,
                    "Mohon isikan pesan Anda",
                    "Missing Setting(s)", JOptionPane.ERROR_MESSAGE);
            return;
        }
        KunciPrivatDialog dialog;
        try {
            dialog = new KunciPrivatDialog(this);
            dialog.show();
            ecdsa.setdA(dialog.getPrivatKey());
            ecdsa.generatePubKey();
            String sign = ecdsa.signingMessage(ContentArea.getText());
            ContentArea.setText(ContentArea.getText() + "<<" + sign + ">>");
        } catch (IOException ex) {
            Logger.getLogger(ComposeDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ComposeDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_DigitalSignBoxActionPerformed
    public String key;
    private void EncryptBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EncryptBoxActionPerformed
        // TODO add your handling code here:
        KunciEnkripsiDialog dialog = new KunciEnkripsiDialog(this);
        dialog.show();
        key = dialog.getKey();
    }//GEN-LAST:event_EncryptBoxActionPerformed


    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ComposeBtn;
    private javax.swing.JTextArea ContentArea;
    private javax.swing.JCheckBox DigitalSignBox;
    private javax.swing.JCheckBox EncryptBox;
    private javax.swing.JTextField SubjectField;
    private javax.swing.JTextField ToField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
