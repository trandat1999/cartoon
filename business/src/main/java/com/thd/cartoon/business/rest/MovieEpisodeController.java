package com.thd.cartoon.business.rest;

import com.thd.cartoon.business.service.MovieEpisodeService;
import com.thd.cartoon.common.dto.movie.MovieEpisodeDto;
import com.thd.cartoon.common.dto.movie.MovieSearch;
import com.thd.cartoon.common.dto.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author DatNuclear 19/02/2024
 * @project cartoon
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movie-episodes")
@PreAuthorize("hasAnyAuthority(T(com.thd.cartoon.common.util.ConstUtil).ADMIN_ROLE)")
public class MovieEpisodeController {
    private final MovieEpisodeService movieEpisodeService;
    @GetMapping("/movie/{code}")
    public ResponseEntity<BaseResponse> getAllByMovieCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(movieEpisodeService.getAllByMovie(code));
    }
    @PostMapping
    public ResponseEntity<BaseResponse> save(@RequestBody MovieEpisodeDto request){
        return ResponseEntity.ok(movieEpisodeService.saveOrUpdate(request,request.getId()));
    }
    @PostMapping("/pages")
    public ResponseEntity<BaseResponse> search(@RequestBody MovieSearch request){
        return ResponseEntity.ok(movieEpisodeService.search(request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> search(@PathVariable("id") Long id){
        return ResponseEntity.ok(movieEpisodeService.getById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(movieEpisodeService.deleteById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@RequestBody MovieEpisodeDto request,@PathVariable("id") Long id){
        return ResponseEntity.ok(movieEpisodeService.saveOrUpdate(request,id));
    }
}
