package com.thd.cartoon.business.rest;

import com.thd.cartoon.business.service.FileService;
import com.thd.cartoon.common.dto.file.FileDto;
import com.thd.cartoon.common.dto.response.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author DatNuclear 17/01/2024
 * @project cartoon-movie
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/publish")
public class PublicController {
    private final FileService fileService;
    @GetMapping(value = "/files/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable("id") Long id){
        BaseResponse response = fileService.getById(id);
        if(response.getBody() != null){
            FileDto fileResponse = (FileDto) response.getBody();
            Resource resource = fileService.getFileByPath(fileResponse.getPath());
            if(ObjectUtils.isEmpty(resource)){
                return ResponseEntity.badRequest().build();
            }
            String headerValue = "attachment; filename=\"" + fileResponse.getName() + "\"";
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                    .contentLength(fileResponse.getSize())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        }
        return ResponseEntity.ok(response);
    }
}
