package com.thd.cartoon.common.repository;

import com.thd.cartoon.common.entity.EmailNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author DatNuclear 19/01/2024
 * @project cartoon-movie
 */
@Repository
public interface EmailNotificationRepository extends JpaRepository<EmailNotification,Long>  {
}
