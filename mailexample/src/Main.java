import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class Main {
    public static void main(String[] args) {
        Properties p =new Properties();
        p.put("mail.smtp.host","smtp.yandex.ru");
        p.put("mail.smtp.socketFactory.port",465);
        p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.auth","true");
        p.put("mail.smtp.port",465);

        Session s = Session.getDefaultInstance(p,
                new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication("java.application@yandex.ru","jevbspuzhvrfevkc");
                }
            }
        );

        try{
            Message mess = new MimeMessage(s);
            Multipart multipart = new MimeMultipart();
            MimeBodyPart bodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource("c:\\hello.txt\\");
            //bodyPart.setContent(mess,"text/html");
            bodyPart.setDataHandler(new DataHandler(source));
            bodyPart.setFileName("hello.txt");
            multipart.addBodyPart(bodyPart);
            MimeBodyPart secondPart = new MimeBodyPart();
            secondPart.setText("i send you hello.txt please check it");
            multipart.addBodyPart(secondPart);
            mess.setFrom(new InternetAddress("java.application@yandex.ru"));
            mess.setRecipients(Message.RecipientType.TO,InternetAddress.parse("alpuspaev.daniyar@gmail.com"));
            //mess.setSubject("testfromapp");
            mess.setContent(multipart);
           // mess.setText("check for message");
            Transport.send(mess);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
