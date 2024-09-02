package hodau.backendapi.com.shopnoithat.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import hodau.backendapi.com.shopnoithat.payload.request.EmailRequest;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        // Chuyển danh sách địa chỉ email thành mảng và thiết lập
        message.setTo(emailRequest.getToEmails().toArray(new String[0]));
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getBody());
        message.setFrom("nguyenvandung01052002@gmail.com");

        mailSender.send(message);
    }
}