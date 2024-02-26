package com.thd.cartoon.business.service.impl;

import com.thd.cartoon.business.service.MovieEpisodeService;
import com.thd.cartoon.common.dto.movie.MovieEpisodeDto;
import com.thd.cartoon.common.dto.movie.MovieLinkDto;
import com.thd.cartoon.common.dto.movie.MovieSearch;
import com.thd.cartoon.common.dto.response.BaseResponse;
import com.thd.cartoon.common.entity.File;
import com.thd.cartoon.common.entity.MovieEpisode;
import com.thd.cartoon.common.entity.MovieLink;
import com.thd.cartoon.common.repository.FileRepository;
import com.thd.cartoon.common.repository.MovieEpisodeRepository;
import com.thd.cartoon.common.repository.MovieRepository;
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
    private final MovieRepository movieRepository;
    private final FileRepository fileRepository;
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
        if(request.getMovie()==null || request.getMovie().getId()==null){
            validator.put(SystemVariable.MOVIE_FIELD,getMessage(SystemMessage.VALIDATION_NOTNULL_SV));
            return getResponse400(getMessage(SystemMessage.BAD_REQUEST),validator);
        }else{
            var optionalMovie = movieRepository.findById(request.getMovie().getId());
            if(optionalMovie.isEmpty()){
                validator.put(SystemVariable.MOVIE_FIELD,getMessage(SystemMessage.NOT_FOUND,getMessage(SystemVariable.MOVIE)));
                return getResponse400(getMessage(SystemMessage.BAD_REQUEST),validator);
            }
            entity.setMovie(optionalMovie.get());
        }
        entity.setEpisode(request.getEpisode());
        entity.setPart(request.getPart());
        entity.setVoided(request.getVoided());
        entity.setCode(request.getCode());
        entity.setPreviousEpisode(request.getPreviousEpisode());
        entity.setName(request.getName());
        entity.getLinks().clear();
        for(MovieLinkDto movieLinkDto : request.getLinks()){
            MovieLink movieLink = new MovieLink();
            movieLink.setEpisode(entity);
            movieLink.setName(movieLinkDto.getName());
            movieLink.setEmbeddedLink(movieLinkDto.getEmbeddedLink());
            movieLink.setVoided(movieLinkDto.getVoided());
            movieLink.setOrderNumber(movieLinkDto.getOrderNumber());
            if(movieLinkDto.getFile()!=null && movieLinkDto.getFile().getId()!=null) {
                movieLink.setFile(fileRepository.findById(movieLinkDto.getFile().getId()).orElse(null));
                if(movieLink.getFile()==null&& movieLink.getEmbeddedLink()==null) {
                    continue;
                }
            }
            entity.getLinks().add(movieLink);
        }
        entity = movieEpisodeRepository.save(entity);
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
        if(search.getVoided()!=null && !search.getVoided()){
            search.setVoided(null);
        }
        return getResponse200(movieEpisodeRepository.search(search.getKeyword(),search.getMovieId(),search.getVoided(),
                        getPageable(search)),
                getMessage(SystemMessage.SUCCESS));
    }
}
