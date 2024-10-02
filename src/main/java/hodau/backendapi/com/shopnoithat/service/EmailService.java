package hodau.backendapi.com.shopnoithat.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import hodau.backendapi.com.shopnoithat.entity.EmailLog;
import hodau.backendapi.com.shopnoithat.payload.request.EmailRequest;
import hodau.backendapi.com.shopnoithat.responsitory.EmailLogRepository;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailLogRepository emailLogRepository;

    public void sendSimpleEmail(EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getToEmails().toArray(new String[0]));
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getBody());
        message.setFrom("vifiretek.smtp.test@gmail.com");

        // Gửi email
        mailSender.send(message);

        // Lưu thông tin email vào cơ sở dữ liệu với một bản ghi cho tất cả người nhận
        EmailLog emailLog = new EmailLog();
        emailLog.setRecipients(String.join(", ", emailRequest.getToEmails())); // Lưu tất cả người nhận
        emailLog.setSubject(emailRequest.getSubject());
        emailLog.setBody(emailRequest.getBody());
        emailLog.setSentAt(LocalDateTime.now());
        emailLogRepository.save(emailLog);
    }
    public List<EmailLog> getAllEmails() {
        return emailLogRepository.findAll();
    }
}
