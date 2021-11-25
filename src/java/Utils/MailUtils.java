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

    public static boolean sendForgotPassword(int accountId) {
        try {
            AccountDAO accDao = new AccountDAO();
            VerificationDAO veriDao = new VerificationDAO();

            AccountDTO dto = accDao.getAccountFromAcoountID(accountId);
            VerificationDTO code = veriDao.GetVerificationDTOUsingAccountID(accountId, "FORGOT_PASSWORD");

            String from = USER_NAME;
            String pass = PASSWORD;

            System.out.println("[Forgot Password] Send to AccountID : " + accountId);
            String to = dto.getEmail();
            String subject = "Academy Blog Account Forgot Password";
            String body = "Please change your account password with this link : https://academyblog.azurewebsites.net/SWP391_Project/forgotPassword?code=" + code.getCode();
            sendFromGMail(subject, body, to);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean sendVerification(int accountId) {
        try {
            AccountDAO accDao = new AccountDAO();
            VerificationDAO veriDao = new VerificationDAO();

            AccountDTO dto = accDao.getAccountFromAcoountID(accountId);
            VerificationDTO code = veriDao.GetVerificationDTOUsingAccountID(accountId, "VERIFICATION");

            System.out.println("[Verify] Send to AccountID : " + accountId);
            String to = dto.getEmail();
            String subject = "Academy Blog Account Verification";
            String body = "Please verify your account with this link : https://academyblog.azurewebsites.net/SWP391_Project/verify?code=" + code.getCode();
            sendFromGMail(subject, body, to);
            return true;
        } catch (SQLException ex) {
            return false;
        }

    }

    private static void sendFromGMail(String subject, String body, String toEmail) {
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

            msg.setFrom(new InternetAddress("no_reply@academyblog.com", "NoReply"));

            msg.setReplyTo(InternetAddress.parse("no_reply@academyblog.com", false));

            msg.setSubject(subject, "UTF-8");
            String text = "<h1>Please Verify Your Account</h1>";
            text += "<p>Your Email : " + toEmail + "</p>\n";
            text += body;

//            msg.setText(text, "UTF-8", "html");
            msg.setContent(text, "text/html");
            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(msg);

            System.out.println("Email Sent Successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
