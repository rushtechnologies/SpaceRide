package com.rushtechnologies.spaceride.Mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Jorge Alberto Sánchez Ramos
 */
public class Mail {
    
    Properties prop;
    Session sesion;
    MimeMessage message;
    Transport transport;
    
    public boolean enviarCorreo(String de, String clave, String para, String mensaje, String asunto){
        boolean enviado = false;
            try{
                prop = System.getProperties();
                prop.put("mail.smtp.starttls.enable","true");
                prop.put("mail.smtp.host", "smtp.gmail.com,smtp-mail.outlook.com");
                prop.put("mail.smtp.user",de);
                prop.put("mail.smtp.password", clave);
                prop.put("mail.smtp.port","587");
                prop.put("mail.smtp.auth","true");
                sesion = Session.getDefaultInstance(prop,null);
                message = new MimeMessage(sesion);
                message.setFrom(new InternetAddress(de));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(para));
                message.setSubject(asunto);
                message.setText(mensaje);
                transport = sesion.getTransport("smtp");
                transport.connect("smtp.gmail.com",de,clave);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                enviado = true;
            } catch(MessagingException e){
                System.out.println(e.getMessage());
            }
        
        return enviado;
        
    }
    
}