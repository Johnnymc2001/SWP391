/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import DAO.AccountDAO;
import DAO.AccountDTO;
import DAO.VerificationDAO;
import DAO.VerificationDTO;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author JohnnyMC
 */
public class MailUtils {

    private static String USER_NAME = "academyblog.fpt@gmail.com";
    private static String PASSWORD = "Ka563^qvw!2K#sL@aXvL";

    public static boolean sendVerification(int accountId) throws SQLException {
        AccountDAO accDao = new AccountDAO();
        VerificationDAO veriDao = new VerificationDAO();

        AccountDTO dto = accDao.getAccountFromAcoountID(accountId);
        VerificationDTO code = veriDao.GetCodeUsingAccountId(accountId);

        String from = USER_NAME;
        String pass = PASSWORD;

        String[] to = new String[100];
        to[0] = dto.getEmail();
        String subject = "Academy Blog Account Verification";
        String body = "Please verify your account with this link : http://localhost:8081/verify?id=" + code.getCode();
        System.out.println(body);
        sendFromGMail(from, pass, to, subject, body, to[0]);
        return true;
    }

    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body, String toEmail) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_NAME, PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);

        MailUtils.sendEmail(session, toEmail, subject, body);
    }

    public static void sendEmail(Session session, String toEmail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");
            String text = "<h1>Please Verify Your Account</h1>";
            text +=  "<p>Your Email : " + toEmail + "</p>\n";
            text += body;
            
//            msg.setText(text, "UTF-8", "html");
            msg.setContent(text, "text/html");
            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            sendVerification(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
