package com.thd.cartoon.business.service;

import com.thd.cartoon.common.dto.country.CountryDto;
import com.thd.cartoon.common.dto.country.CountrySearch;
import com.thd.cartoon.common.dto.response.BaseResponse;

/**
 * @author DatNuclear 15/02/2024
 * @project cartoon
 */
public interface CountryService {
    BaseResponse getAll();
    BaseResponse getById(Long id);
    BaseResponse saveOrUpdate(CountryDto dto, Long id);
    BaseResponse deleteById(Long id);
    BaseResponse search(CountrySearch search);
}
