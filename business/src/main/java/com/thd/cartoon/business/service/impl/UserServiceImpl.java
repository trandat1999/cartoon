package com.thd.cartoon.business.service.impl;

import com.thd.cartoon.common.dto.response.BaseResponse;
import com.thd.cartoon.common.dto.user.UserDto;
import com.thd.cartoon.common.entity.User;
import com.thd.cartoon.common.repository.UserRepository;
import com.thd.cartoon.business.service.UserService;
import com.thd.cartoon.common.util.SystemMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author DatNuclear 30/01/2024
 * @project cartoon-movie
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseService implements UserService{
    private final UserRepository userRepository;
    @Override
    public BaseResponse getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String username = authentication.getName();
            Optional<User> userOptional = userRepository.findByUsername(username);
            return getResponse200(new UserDto(userOptional.get()),getMessage(SystemMessage.SUCCESS));
        }
        return getResponse400(null);
    }
}
