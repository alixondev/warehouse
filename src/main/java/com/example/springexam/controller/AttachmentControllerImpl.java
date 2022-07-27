package com.example.springexam.controller;

import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.AttachmentDTO;
import com.example.springexam.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class AttachmentControllerImpl implements AttachmentController {
    private final AttachmentService attachmentService;


    @Override
    public ApiResult<AttachmentDTO> upload(MultipartHttpServletRequest request) throws Exception {
        return attachmentService.uploadDb(request);
    }

    @Override
    public void download(Integer id, boolean inline, HttpServletResponse response) {
        attachmentService.downloadDb(id, inline, response);
    }
}
