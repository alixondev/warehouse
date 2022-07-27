package com.example.springexam.service;


import com.example.springexam.payload.SendMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;


    @Override
    public boolean sendMessage(SendMessageDTO sendMessageDTO) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setTo(sendMessageDTO.getToEmail());
            simpleMailMessage.setSubject(sendMessageDTO.getTitle());
            simpleMailMessage.setFrom(sendMessageDTO.getFromEmail());
            simpleMailMessage.setText(sendMessageDTO.getText());

            javaMailSender.send(simpleMailMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
