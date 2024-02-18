package com.thd.cartoon.business.rest;

import com.thd.cartoon.common.dto.response.BaseResponse;
import com.thd.cartoon.business.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DatNuclear 30/01/2024
 * @project cartoon-movie
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/current")
    public ResponseEntity<BaseResponse> getCurrentUser(){
        return ResponseEntity.ok(userService.getCurrentUser());
    }
}
