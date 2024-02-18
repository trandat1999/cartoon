package com.thd.cartoon.common.repository;

import com.thd.cartoon.common.dto.movie.MovieEpisodeDto;
import com.thd.cartoon.common.entity.MovieEpisode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author DatNuclear 16/02/2024
 * @project cartoon
 */
public interface MovieEpisodeRepository extends JpaRepository<MovieEpisode,Long> {
    @Query(value = "select new com.thd.cartoon.common.dto.movie.MovieEpisodeDto(entity) from MovieEpisode entity " +
            "where entity.voided <> true " +
            "and entity.movie.code = :movieCode")
    List<MovieEpisodeDto> getAllByMovieCode(String movieCode);
    @Query(value = "select count(1) from MovieEpisode entity where entity.code = :code " +
            "and (:id is null or entity.id != :id) " +
            "and (entity.movie.code =:movieCode)")
    long countExistByCode(String code, Long id, String movieCode);

}
