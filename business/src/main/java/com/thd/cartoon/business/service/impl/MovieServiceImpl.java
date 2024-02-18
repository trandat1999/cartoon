package com.thd.cartoon.business.service.impl;

import com.thd.cartoon.business.service.MovieService;
import com.thd.cartoon.common.dto.category.CategoryDto;
import com.thd.cartoon.common.dto.category.CategorySearch;
import com.thd.cartoon.common.dto.movie.MovieDto;
import com.thd.cartoon.common.dto.movie.MovieSearch;
import com.thd.cartoon.common.dto.response.BaseResponse;
import com.thd.cartoon.common.entity.Category;
import com.thd.cartoon.common.entity.Country;
import com.thd.cartoon.common.entity.Movie;
import com.thd.cartoon.common.repository.CategoryRepository;
import com.thd.cartoon.common.repository.CountryRepository;
import com.thd.cartoon.common.repository.FileRepository;
import com.thd.cartoon.common.repository.MovieRepository;
import com.thd.cartoon.common.util.SystemMessage;
import com.thd.cartoon.common.util.SystemVariable;
import lombok.AllArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Optional;

/**
 * @author DatNuclear 16/02/2024
 * @project cartoon
 */
@Service
@AllArgsConstructor
public class MovieServiceImpl extends BaseService implements MovieService {
    private final MovieRepository movieRepository;
    private final CountryRepository countryRepository;
    private final FileRepository fileRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public BaseResponse getAll() {
        return getResponse200(movieRepository.getAll(),getMessage(SystemMessage.SUCCESS));
    }
    @Override
    public BaseResponse getById(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()){
            return getResponse200(new MovieDto(movieOptional.get()),getMessage(SystemMessage.SUCCESS));
        }
        return getResponse400(getMessage(SystemMessage.NOT_FOUND, getMessage(SystemVariable.MOVIE)));
    }

    @Override
    public BaseResponse saveOrUpdate(MovieDto request, Long id) {
        var validator = validation(request);
        if(!validator.isEmpty()){
            return getResponse400(getMessage(SystemMessage.BAD_REQUEST),validator);
        }
        var checkCode = movieRepository.countExistByCode(request.getCode(),id);
        if(checkCode>0){
            validator.put(SystemVariable.CODE,getMessage(SystemMessage.VALUE_EXIST,request.getCode()));
            return getResponse400(getMessage(SystemMessage.BAD_REQUEST),validator);
        }
        Movie entity = null;
        if(id!=null){
            Optional<Movie> movieOptional = movieRepository.findById(id);
            if (movieOptional.isEmpty()){
                return getResponse400(getMessage(SystemMessage.NOT_FOUND, getMessage(SystemVariable.CATEGORY)));
            }
            entity = movieOptional.get();
        }
        if(entity==null){
            entity = new Movie();
        }
        entity.setVoided(request.getVoided());
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setOtherName(request.getOtherName());
        entity.setPublishYear(request.getPublishYear());
        if(request.getCountry()!=null && request.getCountry().getId()!=null){
            entity.setCountry(countryRepository.findById(request.getCountry().getId()).orElse(null));
        }
        if(request.getFile()!=null && request.getFile().getId()!=null){
            entity.setFile(fileRepository.findById(request.getFile().getId()).orElse(null));
        }
        entity.getCategories().clear();
        if(!CollectionUtils.isEmpty(request.getCategories())){
            for(CategoryDto categoryDto : request.getCategories()){
                Category category = categoryRepository.findById(categoryDto.getId()).orElse(null);
                if(category!=null){
                    entity.getCategories().add(category);
                }
            }
        }
        entity = movieRepository.save(entity);
        return getResponse200(new MovieDto(entity),getMessage(SystemMessage.SUCCESS));
    }

    @Override
    public BaseResponse deleteById(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isEmpty()){
            return getResponse400(getMessage(SystemMessage.NOT_FOUND, getMessage(SystemVariable.CATEGORY)));
        }
        var entity = movieOptional.get();
        entity.setVoided(true);
        entity = movieRepository.save(entity);
        return getResponse200(new MovieDto(entity),getMessage(SystemMessage.SUCCESS));
    }

    @Override
    public BaseResponse search(MovieSearch search) {
        if(search.getVoided()!=null && !search.getVoided()){
            search.setVoided(null);
        }
        return getResponse200(movieRepository.search(search.getKeyword(),search.getVoided(),getPageable(search)),
                getMessage(SystemMessage.SUCCESS));
    }

    @Override
    public BaseResponse getByCode(String code) {
        Optional<Movie> movieOptional = movieRepository.findByCode(code);
        if (movieOptional.isPresent()){
            return getResponse200(new MovieDto(movieOptional.get()),getMessage(SystemMessage.SUCCESS));
        }
        return getResponse400(getMessage(SystemMessage.NOT_FOUND, getMessage(SystemVariable.MOVIE)));
    }
}
