package model;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {

	public Email() {

	}

	private Session createSessionMail() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.debug", "true");
		String[] ls = util.Facade.lerArquivoEmail();

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ls[0], ls[1]);
			}
		});
		return session;
	}

	public void sendEmail(String assunto, String mensagem, String fromEmail, String nomeTo) {
		try {
			String[] ls = util.Facade.lerArquivoEmail();
			MimeMessage msg = new MimeMessage(this.createSessionMail());
			msg.setFrom(new InternetAddress(ls[0]));
			msg.setRecipients(Message.RecipientType.TO, fromEmail);
			msg.setSubject(assunto);
			msg.setSentDate(new Date());
			msg.setText(mensagem);
			Transport.send(msg);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public void sendEmailWithAttachment(String assunto, String mensagem, String fromEmail, String nomeTo, String filename) {
		String[] ls = util.Facade.lerArquivoEmail();
		try {
			MimeMessage msg = new MimeMessage(this.createSessionMail());
			msg.setFrom(new InternetAddress(ls[0]));
			msg.setRecipients(Message.RecipientType.TO, fromEmail);
			msg.setSubject(assunto);
			msg.setSentDate(new Date());

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(mensagem);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);
			
			Transport.send(msg);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}