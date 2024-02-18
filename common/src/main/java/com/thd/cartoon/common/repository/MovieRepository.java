package com.thd.cartoon.common.repository;

import com.thd.cartoon.common.dto.country.CountryDto;
import com.thd.cartoon.common.dto.movie.MovieDto;
import com.thd.cartoon.common.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author DatNuclear 15/02/2024
 * @project cartoon
 */
public interface MovieRepository extends JpaRepository<Movie,Long> {
    @Query(value = "select count(1) from Movie entity where entity.code = :code " +
            "and (:id is null or entity.id != :id)")
    long countExistByCode(String code, Long id);

    @Query(value = "select new com.thd.cartoon.common.dto.movie.MovieDto(entity) " +
            "from Movie entity where entity.voided <> true")
    List<MovieDto> getAll();

    @Query(value = "select new com.thd.cartoon.common.dto.movie.MovieDto(entity) from Movie entity " +
            "where (:voided is null or entity.voided =:voided) " +
            "and (:keyword is null or :keyword = '' or entity.code like concat('%',:keyword,'%') " +
            "or entity.name like concat('%',:keyword,'%') or entity.otherName like concat('%',:keyword,'%') )")
    Page<MovieDto> search(String keyword, Boolean voided, Pageable pageable);
    Optional<Movie> findByCode(String code);
}
