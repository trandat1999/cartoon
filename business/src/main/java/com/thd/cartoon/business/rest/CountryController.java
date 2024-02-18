package com.thd.cartoon.business.rest;

import com.thd.cartoon.business.service.CountryService;
import com.thd.cartoon.common.dto.country.CountryDto;
import com.thd.cartoon.common.dto.country.CountrySearch;
import com.thd.cartoon.common.dto.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author DatNuclear 05/02/2024
 * @project cartoon-movie
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/countries")
public class CountryController {
    private final CountryService countryService;
    @GetMapping
    public ResponseEntity<BaseResponse> getAll(){
        return ResponseEntity.ok(countryService.getAll());
    }
    @PreAuthorize("hasAnyAuthority(T(com.thd.cartoon.common.util.ConstUtil).ADMIN_ROLE)")
    @PostMapping
    public ResponseEntity<BaseResponse> save(@RequestBody CountryDto request){
        return ResponseEntity.ok(countryService.saveOrUpdate(request,request.getId()));
    }
    @PostMapping("/pages")
    public ResponseEntity<BaseResponse> search(@RequestBody CountrySearch request){
        return ResponseEntity.ok(countryService.search(request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> search(@PathVariable("id") Long id){
        return ResponseEntity.ok(countryService.getById(id));
    }
    @PreAuthorize("hasAnyAuthority(T(com.thd.cartoon.common.util.ConstUtil).ADMIN_ROLE)")
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(countryService.deleteById(id));
    }
    @PreAuthorize("hasAnyAuthority(T(com.thd.cartoon.common.util.ConstUtil).ADMIN_ROLE)")
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@RequestBody CountryDto request,@PathVariable("id") Long id){
        return ResponseEntity.ok(countryService.saveOrUpdate(request,id));
    }
}
