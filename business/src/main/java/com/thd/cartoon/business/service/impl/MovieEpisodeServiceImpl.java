package com.thd.cartoon.business.service.impl;

import com.thd.cartoon.business.service.MovieEpisodeService;
import com.thd.cartoon.common.dto.movie.MovieEpisodeDto;
import com.thd.cartoon.common.dto.movie.MovieSearch;
import com.thd.cartoon.common.dto.response.BaseResponse;
import com.thd.cartoon.common.entity.MovieEpisode;
import com.thd.cartoon.common.repository.MovieEpisodeRepository;
import com.thd.cartoon.common.util.SystemMessage;
import com.thd.cartoon.common.util.SystemVariable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author DatNuclear 16/02/2024
 * @project cartoon
 */
@Service
@AllArgsConstructor
public class MovieEpisodeServiceImpl extends BaseService implements MovieEpisodeService {
    private final MovieEpisodeRepository movieEpisodeRepository;
    @Override
    public BaseResponse getAllByMovie(String code) {
        return getResponse200(movieEpisodeRepository.getAllByMovieCode(code),getMessage(SystemMessage.SUCCESS));
    }

    @Override
    public BaseResponse getById(Long id) {
        Optional<MovieEpisode> movieEpisodeOptional = movieEpisodeRepository.findById(id);
        if (movieEpisodeOptional.isPresent()){
            return getResponse200(new MovieEpisodeDto(movieEpisodeOptional.get()),getMessage(SystemMessage.SUCCESS));
        }
        return getResponse400(getMessage(SystemMessage.NOT_FOUND, getMessage(SystemVariable.MOVIE)));
    }

    @Override
    public BaseResponse saveOrUpdate(MovieEpisodeDto request, Long id) {
        var validator = validation(request);
        if(!validator.isEmpty()){
            return getResponse400(getMessage(SystemMessage.BAD_REQUEST),validator);
        }
        var checkCode = movieEpisodeRepository.countExistByCode(request.getCode(),id,request.getMovie().getCode());
        if(checkCode>0){
            validator.put(SystemVariable.CODE,getMessage(SystemMessage.VALUE_EXIST,request.getCode()));
            return getResponse400(getMessage(SystemMessage.BAD_REQUEST),validator);
        }
        MovieEpisode entity = null;
        if(id!=null){
            Optional<MovieEpisode> movieEpisodeOptional = movieEpisodeRepository.findById(id);
            if (movieEpisodeOptional.isEmpty()){
                return getResponse400(getMessage(SystemMessage.NOT_FOUND, getMessage(SystemVariable.MOVIE)));
            }
            entity = movieEpisodeOptional.get();
        }
        if(entity==null){
            entity = new MovieEpisode();
        }

        return getResponse200(new MovieEpisodeDto(entity),getMessage(SystemMessage.SUCCESS));
    }

    @Override
    public BaseResponse deleteById(Long id) {
        Optional<MovieEpisode> movieEpisodeOptional = movieEpisodeRepository.findById(id);
        if (movieEpisodeOptional.isEmpty()){
            return getResponse400(getMessage(SystemMessage.NOT_FOUND, SystemVariable.CATEGORY));

        }
        var entity = movieEpisodeOptional.get();
        entity.setVoided(true);
        entity = movieEpisodeRepository.save(entity);
        return getResponse200(new MovieEpisodeDto(entity),getMessage(SystemMessage.SUCCESS));
    }

    @Override
    public BaseResponse search(MovieSearch search) {
        return null;
    }
}
