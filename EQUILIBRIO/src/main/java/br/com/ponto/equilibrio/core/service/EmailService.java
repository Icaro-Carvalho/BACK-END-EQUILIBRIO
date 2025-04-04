package br.com.ponto.equilibrio.core.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmailRecuperacao(String email, String codigo) throws MessagingException {
        MimeMessage menMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(menMessage, true);
        helper.setTo(email);
        helper.setSubject("Código de Recuperação de Senha");
        helper.setText("Seu código para redifinir senha é: " + codigo);
        mailSender.send(menMessage);
    }
    
}
