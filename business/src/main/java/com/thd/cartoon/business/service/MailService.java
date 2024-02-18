package com.thd.cartoon.business.service;

import com.thd.cartoon.common.entity.EmailNotification;
import com.thd.cartoon.common.entity.User;

import java.util.Map;

/**
 * @author DatNuclear 19/01/2024
 * @project cartoon-movie
 */
public interface MailService {
    void sendMail(EmailNotification notificationEmail, Map<String, Object> attributes);
    void sendMailActive(User user);
    void sendMailForgotPassword(User user);
}
