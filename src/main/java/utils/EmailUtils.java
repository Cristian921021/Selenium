package utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class EmailUtils {

    public static void sendTestReport(String reportPath){
        final String senderEmail = "alfontesting16297@gmail.com";
        final String appPassword = "tyioqrrnnoserfqa";
        final String recipientEmail = "alfontesting16297@gmail.com";

        //SMTP Server Properties
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        //create session with authentication
        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(senderEmail, appPassword);
            }
        });
        System.out.println(session.getProperties());
        session.setDebug(true);

        try {

            //create email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Test Email from Java with Attach");
            //message.setText("Hello /n This is a Test Email from java /n Regards, /n QA Team");

            //Email Part
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Hello,\nThis email contains the report for the automated test cases.");

            //attachment
            MimeBodyPart attachmentPart= new MimeBodyPart();
            //String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            //String filePath = System.getProperty("user.dir")+"/reports/ExtentReport_"+timestamp+".html";
            System.out.println("Attachment Path is "+reportPath);
            attachmentPart.attachFile(new File(reportPath));

            //combine body and attachment parts

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);

            //send email

            Transport.send(message);
            System.out.println("******Email Sent Successfully******");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
