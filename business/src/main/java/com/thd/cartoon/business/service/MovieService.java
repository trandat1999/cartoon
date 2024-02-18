package com.thd.cartoon.business.service;

import com.thd.cartoon.common.dto.category.CategoryDto;
import com.thd.cartoon.common.dto.category.CategorySearch;
import com.thd.cartoon.common.dto.movie.MovieDto;
import com.thd.cartoon.common.dto.movie.MovieSearch;
import com.thd.cartoon.common.dto.response.BaseResponse;

/**
 * @author DatNuclear 16/02/2024
 * @project cartoon
 */
public interface MovieService {
    BaseResponse getAll();
    BaseResponse getById(Long id);
    BaseResponse saveOrUpdate(MovieDto category, Long id);
    BaseResponse deleteById(Long id);
    BaseResponse search(MovieSearch search);
    BaseResponse getByCode(String code);
}
