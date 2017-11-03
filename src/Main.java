import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        String subject = "Test Message";
        String address = "";//email
        String username = "";//username
        String password = "";//password
        String to = "";//send to
        String host = "smtp.gmail.com";
        String port = "587";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.password", password);

        Session session = Session.getInstance(props);

        try {

            InternetAddress from = new InternetAddress(address);

            MimeMessage msg = new MimeMessage(session);
            msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setFrom(from);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText("This Message was sent using Java!");

            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            System.out.println("Transport: " + transport.toString());
            transport.sendMessage(msg, msg.getAllRecipients());


        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
