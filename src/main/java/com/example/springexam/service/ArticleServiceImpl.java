package com.example.springexam.service;

import com.example.springexam.entity.User;
import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.ArticleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private List<ArticleDTO> articleDTOList = new ArrayList<>(
            Arrays.asList(
                    new ArticleDTO(1,"Blomberg haqida ","Blomberg haqida"),
                    new ArticleDTO(2,"Blomberg haqida ","Blomberg haqida"),
                    new ArticleDTO(3,"Blomberg haqida","Blomberg haqida"),
                    new ArticleDTO(4,"Blomberg haqida","Blomberg haqida"),
                    new ArticleDTO(5,"Blomberg haqida","Blomberg haqida"),
                    new ArticleDTO(6,"Blomberg haqida","Blomberg haqida"),
                    new ArticleDTO(7,"Blomberg haqida","Blomberg haqida"),
                    new ArticleDTO(8,"Blomberg haqida","Blomberg haqida"),
                    new ArticleDTO(9,"Blomberg haqida","Blomberg haqida"),
                    new ArticleDTO(10,"Blomberg haqida","Blomberg haqida")
            )
    );

    @Override
    public ApiResult<List<ArticleDTO>> getAll(User user) {
        System.out.println(user);
        return ApiResult.successResponse(articleDTOList);
    }


    @Override
    public ApiResult<ArticleDTO> getOne(Integer id) {
        ArticleDTO articleDTO = articleDTOList
                .stream()
                .findFirst()
                .filter(article -> Objects.equals(id, article.getId()))
                .orElse(null);
        return ApiResult.successResponse(articleDTO);
    }

    @Override
    public ApiResult<ArticleDTO> add(ArticleDTO articleDTO) {
        articleDTOList.add(articleDTO);
        return ApiResult.successResponse(articleDTO);
    }

    @Override
    public ApiResult<String> delete(Integer id) {
        articleDTOList.removeIf(articleDTO -> Objects.equals(articleDTO.getId(),id));
        return ApiResult.successResponse("success");
    }
}
