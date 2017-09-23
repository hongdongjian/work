package Util;

import Dto.Mail;
import Dto.Props;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
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
}
