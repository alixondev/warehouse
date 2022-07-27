package com.example.springexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendMessageDTO {

    private String toEmail;

    private String fromEmail;

    private String title;

    private String text;

}
