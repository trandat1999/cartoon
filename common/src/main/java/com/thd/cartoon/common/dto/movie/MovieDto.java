package com.thd.cartoon.common.dto.movie;

import com.thd.cartoon.common.dto.category.CategoryDto;
import com.thd.cartoon.common.dto.country.CountryDto;
import com.thd.cartoon.common.dto.file.FileDto;
import com.thd.cartoon.common.entity.Category;
import com.thd.cartoon.common.entity.Movie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author DatNuclear 15/02/2024
 * @project cartoon
 */
@Data
@NoArgsConstructor
public class MovieDto {
    private Long id;
    @NotNull(message = "{cartoon.validation.NotNull}")
    @NotBlank(message = "{cartoon.validation.NotBlank}")
    private String name;
    @NotNull(message = "{cartoon.validation.NotNull}")
    @NotBlank(message = "{cartoon.validation.NotBlank}")
    private String code;
    @NotNull(message = "{cartoon.validation.NotNull}")
    @NotBlank(message = "{cartoon.validation.NotBlank}")
    private String description;
    @NotNull(message = "{cartoon.validation.NotNull}")
    private Integer publishYear;
    @NotNull(message = "{cartoon.validation.NotNull}")
    private CountryDto country;
    @NotNull(message = "{cartoon.validation.NotNull}")
    @NotEmpty(message = "{cartoon.validation.NotBlank}")
    private Set<CategoryDto> categories = new HashSet<>();
    @NotNull(message = "{cartoon.validation.NotNull}")
    private FileDto file;
    private String otherName;
    private Boolean voided;

    public MovieDto(Movie entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.otherName = entity.getOtherName();
            this.code = entity.getCode();
            this.description = entity.getDescription();
            this.publishYear = entity.getPublishYear();
            this.voided = entity.getVoided();
            if (entity.getCountry() != null) {
                this.country = new CountryDto(entity.getCountry());
            }
            if (entity.getFile() != null) {
                this.file = new FileDto(entity.getFile());
            }
            if (!CollectionUtils.isEmpty(entity.getCategories())) {
                for (Category category : entity.getCategories()) {
                    this.categories.add(new CategoryDto(category));
                }
            }
        }
    }

    public MovieDto(Movie entity, boolean simple) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.otherName = entity.getOtherName();
            this.code = entity.getCode();
            this.description = entity.getDescription();
            this.publishYear = entity.getPublishYear();
            this.voided = entity.getVoided();
        }
    }
}
