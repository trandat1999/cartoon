package com.thd.cartoon.common.repository;

import com.thd.cartoon.common.entity.MovieLink;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DatNuclear 16/02/2024
 * @project cartoon
 */
public interface MovieLinkRepository extends JpaRepository<MovieLink,Long> {
}
