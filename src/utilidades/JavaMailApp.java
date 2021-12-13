package utilidades;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

public class JavaMailApp {

    private String destinatario = "";
    private String assunto = "", texto = "";

    public JavaMailApp(String destinatario, String assunto, String texto) {
        this.destinatario = destinatario;
        this.assunto = assunto;
        this.texto = texto;
    }

    private String user = "", pass = "", host = "";
    private Properties props = null;
    private Session session = null;
    private Message message = null;
    private Address[] toUser = null;
    private Transport transport = null;

    public boolean enviarEmail() {
        try {
            user = "leonsdevnoreply@gmail.com";
            pass = "Printf(*246846*)";
            host = "smtp.gmail.com";
            props = new Properties();
            props.put("mail.smtp.user", user);
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.startssl.enable", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.secure", "ssl");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("leonsdevnoreply@gmail.com",
                            "Printf(*246846*)");
                }
            });
            session.setDebug(false);
            try {
                message = new MimeMessage(session);
                message.setFrom(new InternetAddress("leonsdevnoreply@gmail.com"));
                toUser = InternetAddress.parse(destinatario);
                message.setRecipients(Message.RecipientType.TO, toUser);
                message.setSubject(assunto);
                message.setText(texto);
                transport = session.getTransport("smtps");
                transport.connect("smtp.gmail.com", 465, user, pass);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                htmlSend(texto, assunto, destinatario);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            htmlSend(texto, assunto, destinatario);
            return false;
        }
    }
    private HtmlEmail htmlEmail = null;

    public void htmlSend(String txt, String assunto, String destinatario) {
        try {
            htmlEmail = new HtmlEmail();
            htmlEmail.setHtmlMsg(txt);
            htmlEmail.addTo(destinatario);
            htmlEmail.setSubject(assunto);
            htmlEmail.setFrom("leonsdevnoreply@gmail.com");
            htmlEmail.setHostName("smtp.gmail.com");
            htmlEmail.setSSLOnConnect(true);
            htmlEmail.setSmtpPort(465);
            htmlEmail.setAuthentication("leonsdevnoreply@gmail.com", "Printf(*246846*)");
            htmlEmail.setSslSmtpPort("465");
            htmlEmail.setSSLCheckServerIdentity(true);
            htmlEmail.setAuthenticator(new DefaultAuthenticator("leonsdevnoreply@gmail.com", "Printf(*246846*)"));
            htmlEmail.send();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage() + " " + e.getLocalizedMessage());
        }
    }

}
