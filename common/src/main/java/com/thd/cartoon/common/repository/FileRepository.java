package com.thd.cartoon.common.repository;

import com.thd.cartoon.common.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DatNuclear 15/02/2024
 * @project cartoon
 */
public interface FileRepository extends JpaRepository<File,Long> {
}
