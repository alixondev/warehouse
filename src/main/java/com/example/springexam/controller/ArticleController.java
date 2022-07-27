package com.example.springexam.controller;

import com.example.springexam.aop.CurrentUser;
import com.example.springexam.entity.User;
import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.ArticleDTO;
import com.example.springexam.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ArticleController.ARTICLE_CONTROLLER_PATH)
public interface ArticleController {
    String ARTICLE_CONTROLLER_PATH = AppConstant.BASE_PATH + "/article/";

    String ALL = "all";
    String ADD = "add";
    String DELETE = "delete";


    @PreAuthorize(value = "hasAuthority('VIEW_ARTICLE')")
    @GetMapping(ALL)
    ApiResult<List<ArticleDTO>> getAll(@CurrentUser User user);

    @PreAuthorize(value = "hasAuthority('VIEW_ARTICLE')")
    @GetMapping("/{id}")
    ApiResult<ArticleDTO> getOne(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('ADD_ARTICLE')")
    @PostMapping(ADD)
    ApiResult<ArticleDTO> add(@RequestBody ArticleDTO articleDTO);

    @PreAuthorize(value = "hasAuthority('DELETE_ARTICLE')")
    @DeleteMapping(DELETE + "/{id}")
    ApiResult<String> delete(@PathVariable Integer id);

}
