package com.thd.cartoon.business.rest;

import com.thd.cartoon.business.service.MovieService;
import com.thd.cartoon.common.dto.movie.MovieDto;
import com.thd.cartoon.common.dto.movie.MovieSearch;
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
@RequestMapping("/api/v1/movies")
@PreAuthorize("hasAnyAuthority(T(com.thd.cartoon.common.util.ConstUtil).ADMIN_ROLE)")
public class MovieController {
    private final MovieService movieService;
    @GetMapping
    public ResponseEntity<BaseResponse> getAll(){
        return ResponseEntity.ok(movieService.getAll());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> save(@RequestBody MovieDto request){
        return ResponseEntity.ok(movieService.saveOrUpdate(request,request.getId()));
    }
    @PostMapping("/pages")
    public ResponseEntity<BaseResponse> search(@RequestBody MovieSearch request){
        return ResponseEntity.ok(movieService.search(request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> search(@PathVariable("id") Long id){
        return ResponseEntity.ok(movieService.getById(id));
    }
    @GetMapping("/code/{code}")
    public ResponseEntity<BaseResponse> getByCode(@PathVariable("code") String code){
        return ResponseEntity.ok(movieService.getByCode(code));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(movieService.deleteById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@RequestBody MovieDto request,@PathVariable("id") Long id){
        return ResponseEntity.ok(movieService.saveOrUpdate(request,id));
    }
}
