package com.example.springexam.controller;


import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.AttachmentDTO;
import com.example.springexam.utils.AppConstant;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@RequestMapping(AttachmentController.ATTACHMENT_CONTROLLER_PATH)
public interface AttachmentController {
    String ATTACHMENT_CONTROLLER_PATH = AppConstant.BASE_PATH + "/attachment/";
    String UPLOAD_PATH = "upload";
    String DOWNLOAD_PATH = "download";

    @PostMapping(UPLOAD_PATH)
    ApiResult<AttachmentDTO> upload(MultipartHttpServletRequest request) throws Exception;

    @GetMapping(DOWNLOAD_PATH + "/{id}")
    void download(@PathVariable Integer id,
                  @RequestParam(defaultValue = "false") boolean inline,
                  HttpServletResponse response);
}
