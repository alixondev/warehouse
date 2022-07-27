package com.example.springexam.service;

import com.example.springexam.entity.User;
import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.ArticleDTO;

import java.util.List;

public interface ArticleService {


    ApiResult<List<ArticleDTO>> getAll(User user);

    ApiResult<ArticleDTO> getOne(Integer id);

    ApiResult<ArticleDTO> add(ArticleDTO articleDTO);

    ApiResult<String> delete(Integer id);
}
