/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import ecdsa.ECDSA;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Andarias Silvanus
 */
public class EmailClient extends javax.swing.JFrame {
    ECDSA ecdsa;
    MailReader MR;
    int numColumn = 3;
    String username = "";
    String password = "";
    String recipient = "";
    String subject = "";
    String content = "";
    int MsgIdx = 0;
    int inbox = 1;
    int sentMail = 2;
    int drafts = 3;
    int spam = 4;
    
    /**
     * Creates new form EmailClient
     */
    public EmailClient() throws MessagingException, IOException {
        setLocation (320,120);
        setTitle("GMail Email Client");
        initComponents();
        LoginDialog dialog = new LoginDialog(this);
        dialog.show();
        username = dialog.getUsername();
        password = dialog.getPassword();
        MR = new MailReader(username, password);
        ecdsa = new ECDSA();
        MsgIdx = MR.getMessages().length-1;
        FillMsgArea(true);
        
        MsgTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public String toString(Message message) throws MessagingException, IOException {
                Object content=message.getContent();
                if (content instanceof MimeMultipart) {
                    MimeMultipart multipart=(MimeMultipart)content;
                    if (multipart.getCount() > 0) {
                      BodyPart part=multipart.getBodyPart(0);
                      content=part.getContent();
                    }
                }
                if (content != null) {
                    return content.toString();
                }
                else
                    return "Selain text, isi pesan tidak dapat ditampilkan";
            }
            public void valueChanged(ListSelectionEvent e) {
                int row = MsgTable.getSelectedRow();
                MsgArea.setText("");
                try {
//                    if(MR.getMessages()[row-1] instanceof MimeMessage)
//                    {
//                        MimeMessage m = (MimeMessage)MR.getMessages()[row-1];
//                        Object contentObject = m.getContent();
//                        if(contentObject instanceof Multipart)
//                        {
//                            BodyPart clearTextPart = null;
//                            BodyPart htmlTextPart = null;
//                            Multipart content = (Multipart)contentObject;
//                            int count = content.getCount();
//                            for(int i=0; i<count; i++)
//                            {
//                                BodyPart part =  content.getBodyPart(i);
//                                if(part.isMimeType("text/plain"))
//                                {
//                                    clearTextPart = part;
//                                    break;
//                                }
//                                else if(part.isMimeType("text/html"))
//                                {
//                                    htmlTextPart = part;
//                                }
//                            }
//                            if(clearTextPart!=null)
//                            {
//                                result = (String) clearTextPart.getContent();
//                            }
//                            else if (htmlTextPart!=null)
//                            {
//                                String html = (String) htmlTextPart.getContent();
//                                result = Jsoup.parse(html).text();
//                            }
//
//                        }
//                         else if (contentObject instanceof String) // a simple text message
//                        {
//                            result = (String) contentObject;
//                        }
//                        else // not a mime message
//                        {
//                            logger.log(Level.WARNING,"notme part or multipart {0}",message.toString());
//                            result = null;
//                        }
//                    }
                    String content = toString(MR.getMessages()[row-1]);
                    MsgArea.setText(content);
//                    if (MR.getMessages()[row-1].getContentType() == "TEXT/PLAIN; charset=UTF-8") {
//                        String content = toString(MR.getMessages()[row-1]);
//                        MsgArea.setText(content);
//                    }
//                    else
//                        MsgArea.setText("Selain text, isi pesan tidak dapat ditampilkan");
//                    System.out.println(MR.getMessages()[row-1].getContentType());
                } catch (MessagingException ex) {
                    Logger.getLogger(EmailClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(EmailClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
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
        jList1 = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        MsgTable = new javax.swing.JTable();
        KirimBtn = new javax.swing.JButton();
        InboxBtn = new javax.swing.JButton();
        SentBtn = new javax.swing.JButton();
        DraftBtn = new javax.swing.JButton();
        SPAMBtn = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        PublikBtn = new javax.swing.JButton();
        KunciBtn = new javax.swing.JButton();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MsgTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Pengirim", "Subject", "Tanggal"
            }
        ));
        jScrollPane1.setViewportView(MsgTable);

        KirimBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        KirimBtn.setText("Kirim Email");
        KirimBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KirimBtnActionPerformed(evt);
            }
        });

        InboxBtn.setText("Kotak Masuk");
        InboxBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InboxBtnActionPerformed(evt);
            }
        });

        SentBtn.setText("Kotak Keluar");
        SentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SentBtnActionPerformed(evt);
            }
        });

        DraftBtn.setText("Kotak Draft");
        DraftBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DraftBtnActionPerformed(evt);
            }
        });

        SPAMBtn.setText("Kotak SPAM");
        SPAMBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SPAMBtnActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton6.setText("EXIT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        PublikBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        PublikBtn.setText("Verifikasi Email");
        PublikBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PublikBtnActionPerformed(evt);
            }
        });
        
        KunciBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        KunciBtn.setText("Kunci Anda");
        KunciBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PublikBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(KirimBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(InboxBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SentBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DraftBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SPAMBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PublikBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(KunciBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MsgArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(KirimBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(InboxBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DraftBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SPAMBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(MsgArea, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(KunciBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PublikBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Tombol Inbox
    private void InboxBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InboxBtnActionPerformed
        FillMsgArea2(inbox);
    }//GEN-LAST:event_InboxBtnActionPerformed

    // Tombol Sent Box
    private void SentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SentBtnActionPerformed
        FillMsgArea2(sentMail);
    }//GEN-LAST:event_SentBtnActionPerformed

    // Tombol Draft
    private void DraftBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DraftBtnActionPerformed
        FillMsgArea2(drafts);
    }//GEN-LAST:event_DraftBtnActionPerformed

    // Tombol SPAM
    private void SPAMBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SPAMBtnActionPerformed
        FillMsgArea2(spam);
    }//GEN-LAST:event_SPAMBtnActionPerformed

    // Tombol Exit
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    // Tombol Kirim
    private void KirimBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KirimBtnActionPerformed
        ComposeDialog dialog = new ComposeDialog(this);
        dialog.show();
        recipient = dialog.getRecipient();
        subject = dialog.getSubject();
        content = dialog.getContent();
        SendGmail SG = new SendGmail();
        String[] to = {recipient};
        SG.sendFromGMail(username, password, to, subject, content);
    }//GEN-LAST:event_KirimBtnActionPerformed

    // Tombol Verifikasi Email
    private void PublikBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PublikBtnActionPerformed
        KunciPublikDialog dialog = new KunciPublikDialog(this);
        dialog.show();
        String kunciPublik;
        kunciPublik = dialog.getPublikKey();
    }//GEN-LAST:event_PublikBtnActionPerformed
    
    // Tombol Generate Kunci
    private void KunciBtnActionPerformed(java.awt.event.ActionEvent evt) throws IOException {//GEN-FIRST:event_PublikBtnActionPerformed
        KunciDialog dialog = new KunciDialog(this);
        dialog.show();
        ecdsa.setdA(dialog.getPriKey());
        ecdsa.setQA(dialog.getPubKey());
    }//GEN-LAST:event_PublikBtnActionPerformed
    
    private void FillMsgArea2 (int type) {
        MR.ReadFolder(type);
        MsgIdx = MR.getMessages().length-1;
        if (MR.getMessages().length<100)
            while (MsgTable.getRowCount()>MR.getMessages().length)
                ((DefaultTableModel)MsgTable.getModel()).removeRow(MsgTable.getRowCount()-1);
        else
            ((DefaultTableModel)MsgTable.getModel()).setRowCount(100);
        try {
            if (type==2)
                FillMsgArea(false);
            else
                FillMsgArea(true);
        }
        catch (MessagingException e) {
        }
        catch (IOException e) {
        }
    }
    
    private void FillMsgArea (boolean tipe) throws MessagingException, IOException {
        // tipe = false u/ sent mail
        // tipe = true u/ inbox, draft, SPAM
        int row = 0;
        while (row<100) {
            for (int j=0; j<3; j++) {
                if (j==0)
//                    MsgTable.setValueAt(MR.SenderName.get(MsgIdx), row, j);
                    if (tipe)
                        MsgTable.setValueAt(MR.SenderName.get(row), row, j);
                    else
                        MsgTable.setValueAt(MR.ReceiverName.get(row), row, j);
                else if (j==1)
                    MsgTable.setValueAt(MR.getMessages()[MsgIdx].getSubject(), row, j);
                else if (j==2)
                    MsgTable.setValueAt(MR.getMessages()[MsgIdx].getReceivedDate(), row, j);
            }
            row ++;
            MsgIdx--;
        }
    }
    
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
            java.util.logging.Logger.getLogger(EmailClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmailClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmailClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmailClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new EmailClient().setVisible(true);
                EmailClient EC = null;
                try {
                    EC = new EmailClient();
                } catch (MessagingException ex) {
                    Logger.getLogger(EmailClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(EmailClient.class.getName()).log(Level.SEVERE, null, ex);
                }
                EC.setVisible(true);
                
//                EC.MsgTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
//                    public void valueChanged(ListSelectionEvent event) {
//                        // do some actions here, for example
//                        // print first column value from selected row
//                        System.out.println(EC.MsgTable.getValueAt(EC.MsgTable.getSelectedRow(), 0).toString());
//                        EC.MsgArea.setText("");
//                        
//                    }
//                });
                
//                TableColumnModelListener tableColumnModelListener;
//                tableColumnModelListener = new TableColumnModelListener() {
//                    @Override
//                    public void columnSelectionChanged(ListSelectionEvent e) {
//                        SwingUtilities.invokeLater(new Runnable() {
//                            @Override
//                            public void run() {
////                                System.out.println(EC.MsgTable.getSelectedColumn()); // this is correct
////                                System.out.println(EC.MsgTable.getSelectedRow());  // -1 on first click in JTable
//                                EC.MsgArea.setText("");
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void columnAdded(TableColumnModelEvent e) {
//                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                    }
//
//                    @Override
//                    public void columnRemoved(TableColumnModelEvent e) {
//                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                    }
//
//                    @Override
//                    public void columnMoved(TableColumnModelEvent e) {
//                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                    }
//
//                    @Override
//                    public void columnMarginChanged(ChangeEvent e) {
//                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                    }
//                };
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DraftBtn;
    private javax.swing.JButton InboxBtn;
    private javax.swing.JButton KirimBtn;
    private final java.awt.TextArea MsgArea = new java.awt.TextArea();
    private javax.swing.JTable MsgTable;
    private javax.swing.JButton PublikBtn;
    private javax.swing.JButton SPAMBtn;
    private javax.swing.JButton SentBtn;
    private javax.swing.JButton jButton6;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton KunciBtn;
    // End of variables declaration//GEN-END:variables
}
