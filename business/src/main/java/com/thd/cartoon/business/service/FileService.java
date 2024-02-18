package com.thd.cartoon.business.service;

import com.thd.cartoon.common.dto.response.BaseResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author DatNuclear 15/02/2024
 * @project cartoon
 */
public interface FileService {
    BaseResponse save(MultipartFile multipartFile);
    BaseResponse getById(Long id);
    Resource getFileByPath(String path);
}
