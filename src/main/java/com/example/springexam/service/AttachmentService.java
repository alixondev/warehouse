package com.example.springexam.service;

import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.AttachmentDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

public interface AttachmentService {
    ApiResult<AttachmentDTO> uploadDb(MultipartHttpServletRequest request) throws Exception;

    ApiResult<AttachmentDTO> uploadSystem(MultipartHttpServletRequest request) throws Exception;

    void downloadDb(Integer id, boolean inline, HttpServletResponse response);

    void downloadSystem(Integer id, boolean inline, HttpServletResponse response);
}
