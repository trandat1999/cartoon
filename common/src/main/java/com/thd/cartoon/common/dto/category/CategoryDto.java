package com.thd.cartoon.common.dto.category;

import com.thd.cartoon.common.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author DatNuclear 03/02/2024
 * @project cartoon-movie
 */
@Data
public class CategoryDto {
    private Long id;
    private Boolean voided;
    @NotNull(message = "{cartoon.validation.NotNull}")
    @NotBlank(message = "{cartoon.validation.NotBlank}")
    private String name;
    @NotNull(message = "{cartoon.validation.NotNull}")
    @NotBlank(message = "{cartoon.validation.NotBlank}")
    private String code;
    private String description;

    public CategoryDto() {
    }
    public CategoryDto(Category entity) {
        if(entity != null){
            this.id = entity.getId();
            this.voided = entity.getVoided();
            this.name = entity.getName();
            this.code = entity.getCode();
            this.description = entity.getDescription();
        }
    }
}
