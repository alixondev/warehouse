package com.example.springexam.controller;

import com.example.springexam.entity.User;
import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.ArticleDTO;
import com.example.springexam.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleControllerImpl implements ArticleController {
    private final ArticleService articleService;


    @Override
    public ApiResult<List<ArticleDTO>> getAll(User user) {
        return articleService.getAll(user);
    }


    @Override
    public ApiResult<ArticleDTO> getOne(Integer id) {
        return articleService.getOne(id);
    }


    @Override
    public ApiResult<ArticleDTO> add(ArticleDTO articleDTO) {
        return articleService.add(articleDTO);
    }


    @Override
    public ApiResult<String> delete(Integer id) {
        return articleService.delete(id);
    }
}
