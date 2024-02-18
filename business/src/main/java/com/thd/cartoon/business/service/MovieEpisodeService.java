package com.thd.cartoon.business.service;

import com.thd.cartoon.common.dto.movie.MovieEpisodeDto;
import com.thd.cartoon.common.dto.movie.MovieSearch;
import com.thd.cartoon.common.dto.response.BaseResponse;

/**
 * @author DatNuclear 16/02/2024
 * @project cartoon
 */
public interface MovieEpisodeService {
    BaseResponse getAllByMovie(String code);
    BaseResponse getById(Long id);
    BaseResponse saveOrUpdate(MovieEpisodeDto dto, Long id);
    BaseResponse deleteById(Long id);
    BaseResponse search(MovieSearch search);
}
