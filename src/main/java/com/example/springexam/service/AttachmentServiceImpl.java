package com.example.springexam.service;

import com.example.springexam.controller.AttachmentController;
import com.example.springexam.entity.Attachment;
import com.example.springexam.entity.AttachmentContent;
import com.example.springexam.exception.RestException;
import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.AttachmentDTO;
import com.example.springexam.repository.AttachmentContentRepository;
import com.example.springexam.repository.AttachmentRepository;
import com.example.springexam.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    @Override
    public ApiResult<AttachmentDTO> uploadDb(MultipartHttpServletRequest request) throws Exception {

        long millis = System.currentTimeMillis();

        try {

            Iterator<String> fileNames = request.getFileNames();

            MultipartFile multipartFile = request.getFile(fileNames.next());

            String contentType = multipartFile.getContentType();//  = image/png

            String originalFilename = multipartFile.getOriginalFilename();

            long size = multipartFile.getSize();

            byte[] bytes = multipartFile.getBytes();

            Attachment attachment = new Attachment(
                    originalFilename,
                    size,
                    contentType
            );

            attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent(
                    bytes,
                    attachment
            );

            attachmentContentRepository.save(attachmentContent);

            String url = AttachmentController.ATTACHMENT_CONTROLLER_PATH
                    + AttachmentController.DOWNLOAD_PATH +
                    "/" + attachment.getId();

            AttachmentDTO attachmentDTO = new AttachmentDTO(
                    Math.toIntExact(attachment.getId()),
                    attachment.getOriginalName(),
                    attachment.getSize(),
                    attachment.getContentType(),
                    url
            );

            System.err.println("Upload db => " + (System.currentTimeMillis() - millis));
            return ApiResult.successResponse(attachmentDTO);

        } catch (IOException e) {
            e.printStackTrace();
            throw RestException.maker("Fayl yuklashda xatolik", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ApiResult<AttachmentDTO> uploadSystem(MultipartHttpServletRequest request) throws Exception {
        long millis = System.currentTimeMillis();
        try {

            Iterator<String> fileNames = request.getFileNames();

            MultipartFile multipartFile = request.getFile(fileNames.next());

            String contentType = multipartFile.getContentType();//  = image/png

            String originalFilename = multipartFile.getOriginalFilename();

            long size = multipartFile.getSize();

            byte[] bytes = multipartFile.getBytes();

            String[] strings = originalFilename.split("\\.");

            String name = UUID.randomUUID().toString() + "." + strings[strings.length - 1];
            //name = 6f51de2b-0f92-40d5-9ef3-9171a46beab9.jpeg

            Attachment attachment = new Attachment(
                    originalFilename,
                    size,
                    contentType,
                    name
            );

            attachmentRepository.save(attachment);

            File outFile = CommonUtils.getOutFile(name);

            FileCopyUtils.copy(bytes, outFile);

            String url = AttachmentController.ATTACHMENT_CONTROLLER_PATH
                    + AttachmentController.DOWNLOAD_PATH +
                    "/" + attachment.getId();

            AttachmentDTO attachmentDTO = new AttachmentDTO(
                    Math.toIntExact(attachment.getId()),
                    attachment.getOriginalName(),
                    attachment.getSize(),
                    attachment.getContentType(),
                    url
            );

            System.err.println("System upload => " +(System.currentTimeMillis() - millis));
            return ApiResult.successResponse(attachmentDTO);

        } catch (IOException e) {
            e.printStackTrace();
            throw RestException.maker("Fayl yuklashda xatolik", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void downloadDb(Integer id, boolean inline, HttpServletResponse response) {
        long millis = System.currentTimeMillis();
        try {
            Attachment attachment = getAttachmentByIdOrElseThrow(id);

            AttachmentContent attachmentContent = getAttachmentContentByIdOrElseThrow(Math.toIntExact(attachment.getId()));

            String disposition = inline ? "inline" : "attachment";

            response.setHeader("Content-Disposition",
                    disposition + "; filename=\"" + attachment.getOriginalName() + "\"");
            response.setContentType(attachment.getContentType());
            response.setHeader("Cache-Control", "max-age=8640000");
            response.setContentLength((int) attachment.getSize());

            FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());

            System.err.println("Download db => "+(System.currentTimeMillis() - millis));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downloadSystem(Integer id, boolean inline, HttpServletResponse response) {
        long millis = System.currentTimeMillis();
        try {
            Attachment attachment = getAttachmentByIdOrElseThrow(id);

            String name = attachment.getName();

            String disposition = inline ? "inline" : "attachment";

            response.setHeader("Content-Disposition",
                    disposition + "; filename=\"" + attachment.getOriginalName() + "\"");
            response.setContentType(attachment.getContentType());
            response.setHeader("Cache-Control", "max-age=8640000");
            response.setContentLength((int) attachment.getSize());

            File outFile = CommonUtils.getOutFile(attachment.getName());

            InputStream inputStream = new FileInputStream(outFile);

            FileCopyUtils.copy(inputStream, response.getOutputStream());
            System.err.println("System download = > "+(System.currentTimeMillis() - millis));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Attachment getAttachmentByIdOrElseThrow(Integer id) {
        return attachmentRepository.findById(id).orElseThrow(() -> RestException.notFound("Attachment"));
    }

    private AttachmentContent getAttachmentContentByIdOrElseThrow(Integer id) {
        return attachmentContentRepository.findByAttachmentId(id).orElseThrow(() -> RestException.notFound("Attachment content"));
    }


}
