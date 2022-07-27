package com.example.springexam.service;



import com.example.springexam.payload.SendMessageDTO;

public interface MailService {

    boolean sendMessage(SendMessageDTO sendMessageDTO);
}
