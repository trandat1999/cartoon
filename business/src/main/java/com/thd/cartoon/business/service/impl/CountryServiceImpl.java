package com.thd.cartoon.business.service.impl;

import com.thd.cartoon.business.service.CountryService;
import com.thd.cartoon.common.dto.country.CountryDto;
import com.thd.cartoon.common.dto.country.CountrySearch;
import com.thd.cartoon.common.dto.response.BaseResponse;
import com.thd.cartoon.common.entity.Country;
import com.thd.cartoon.common.repository.CountryRepository;
import com.thd.cartoon.common.util.SystemMessage;
import com.thd.cartoon.common.util.SystemVariable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author DatNuclear 15/02/2024
 * @project cartoon
 */
@Service
@RequiredArgsConstructor
public class CountryServiceImpl extends BaseService implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    public BaseResponse getAll() {
        return getResponse200(countryRepository.getAll(), getMessage(SystemMessage.SUCCESS));
    }

    @Override
    public BaseResponse getById(Long id) {
        Optional<Country> CountryOptional = countryRepository.findById(id);
        if (CountryOptional.isPresent()) {
            return getResponse200(new CountryDto(CountryOptional.get()), getMessage(SystemMessage.SUCCESS));
        }
        return getResponse400(getMessage(SystemMessage.NOT_FOUND, getMessage(SystemVariable.COUNTRY)));
    }

    @Override
    public BaseResponse saveOrUpdate(CountryDto request, Long id) {
        var validator = validation(request);
        if (!validator.isEmpty()) {
            return getResponse400(getMessage(SystemMessage.BAD_REQUEST), validator);
        }
        var checkCode = countryRepository.countExistByCode(request.getCode(), id);
        if (checkCode > 0) {
            validator.put(SystemVariable.CODE, getMessage(SystemMessage.VALUE_EXIST, request.getCode()));
            return getResponse400(getMessage(SystemMessage.BAD_REQUEST), validator);
        }
        Country entity = null;
        if (id != null) {
            Optional<Country> CountryOptional = countryRepository.findById(id);
            if (CountryOptional.isEmpty()) {
                return getResponse400(getMessage(SystemMessage.NOT_FOUND, getMessage(SystemVariable.COUNTRY)));
            }
            entity = CountryOptional.get();
        }
        if (entity == null) {
            entity = new Country();
        }
        entity.setVoided(request.getVoided());
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity = countryRepository.save(entity);
        return getResponse200(new CountryDto(entity), getMessage(SystemMessage.SUCCESS));
    }

    @Override
    public BaseResponse deleteById(Long id) {
        Optional<Country> countryOptional = countryRepository.findById(id);
        if (countryOptional.isEmpty()) {
            return getResponse400(getMessage(SystemMessage.NOT_FOUND, getMessage(SystemVariable.COUNTRY)));

        }
        var entity = countryOptional.get();
        entity.setVoided(true);
        entity = countryRepository.save(entity);
        return getResponse200(new CountryDto(entity), getMessage(SystemMessage.SUCCESS));
    }

    @Override
    public BaseResponse search(CountrySearch search) {
        if (search.getVoided() != null && !search.getVoided()) {
            search.setVoided(null);
        }
        return getResponse200(countryRepository.search(search.getKeyword(), search.getVoided(), getPageable(search)),
                getMessage(SystemMessage.SUCCESS));
    }
}
