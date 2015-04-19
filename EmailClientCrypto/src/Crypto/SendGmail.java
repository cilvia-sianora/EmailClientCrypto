package Crypto;

/**
 *
 * @author Andarias Silvanus
 */

import CipherBlock.Kristik;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendGmail {

    private static String USER_NAME = "your_accout";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "your_password"; // GMail password
    private static String RECIPIENT = "recipient@recipient.com";
    public static String KEY = "";
    public static void main(String[] args) {
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { RECIPIENT }; // list of recipient email addresses
        String subject = "Java send mail example";
        String body = "Welcome to JavaMail!";
        System.out.println("Waiting to Send...");
        sendFromGMail(from, pass, to, subject, body);
        System.out.println("Sent!");
    }

    static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];
            
            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }
            
            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            
            message.setSubject(subject);
            if (KEY != ""){
                Kristik cipher = new Kristik();
                cipher.setKey(KEY);
                body = cipher.encECB(body);
            }
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            System.out.println("Connecting to host...");
            transport.connect(host, from, pass);
            System.out.println("Sending message...");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Sent!");
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}