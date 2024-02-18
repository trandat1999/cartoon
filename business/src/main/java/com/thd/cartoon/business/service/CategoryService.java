package com.thd.cartoon.business.service;

import com.thd.cartoon.common.dto.category.CategoryDto;
import com.thd.cartoon.common.dto.category.CategorySearch;
import com.thd.cartoon.common.dto.response.BaseResponse;

/**
 * @author DatNuclear 05/02/2024
 * @project cartoon-movie
 */
public interface CategoryService {
    BaseResponse getAll();
    BaseResponse getById(Long id);
    BaseResponse saveOrUpdate(CategoryDto category, Long id);
    BaseResponse deleteById(Long id);
    BaseResponse search(CategorySearch search);
}
