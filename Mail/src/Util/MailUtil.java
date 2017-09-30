package Util;

import Dto.Mail;
import Dto.Props;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Date;
import java.util.Properties;

public class MailUtil {
    public static void sendMail(Props props, Mail mail,String port,Boolean sshFlag) {
        try {
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host", props.getHost());
            properties.setProperty("mail.smtp.port", port);
            properties.setProperty("mail.smtp.auth", "true");
            properties.setProperty("mail.transport.protocol","smtp");

            if (sshFlag) {
                MailSSLSocketFactory sf = new MailSSLSocketFactory();
                sf.setTrustAllHosts(true);
                properties.put("mail.smtp.ssl.enable","true");
                properties.put("mail.smtp.ssl.socketFactory",sf);
            }

            Session session = Session.getDefaultInstance(properties, new Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(props.getUsername(), props.getPassword());
                }});
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(props.getFrom()));
            Address to[] = new InternetAddress[mail.getToMails().length];
            for(int i=0;i<mail.getToMails().length;i++){
                to[i] = new InternetAddress(mail.getToMails()[i]);
            }
            message.setRecipients(javax.mail.Message.RecipientType.TO,to);
            message.setSubject(MimeUtility.encodeText(mail.getSubject(),MimeUtility.mimeCharset("gb2312"), null)); // 标题
            message.setContent(mail.getContent(), "text/html;charset=utf-8");

            message.setSentDate(new Date());
            message.saveChanges();

            Transport.send(message);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void sendMailWithFile(Props props, Mail mail,String port,Boolean sshFlag) {
        try {
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host", props.getHost());
            properties.setProperty("mail.smtp.port", port);
            properties.setProperty("mail.smtp.auth", "true");
            properties.setProperty("mail.transport.protocol","smtp");

            if (sshFlag) {
                MailSSLSocketFactory sf = new MailSSLSocketFactory();
                sf.setTrustAllHosts(true);
                properties.put("mail.smtp.ssl.enable","true");
                properties.put("mail.smtp.ssl.socketFactory",sf);
            }

            Session session = Session.getDefaultInstance(properties, new Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(props.getUsername(), props.getPassword());
                }});
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(props.getFrom()));
            Address to[] = new InternetAddress[mail.getToMails().length];
            for(int i=0;i<mail.getToMails().length;i++){
                to[i] = new InternetAddress(mail.getToMails()[i]);
            }
            message.setRecipients(javax.mail.Message.RecipientType.TO,to);
            message.setSubject(MimeUtility.encodeText(mail.getSubject(),MimeUtility.mimeCharset("gb2312"), null)); // 标题


            // 添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(mail.getContent(), "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);

            // 添加附件
            if (mail.getFile() != null) {
                System.out.println("exit file");
                File file = mail.getFile();
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(file);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(file.getName());
                multipart.addBodyPart(attachmentBodyPart);
            }


            message.setContent(multipart);

            message.setSentDate(new Date());
            message.saveChanges();

            Transport.send(message);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
