package com.thd.cartoon.common.dto.country;

import com.thd.cartoon.common.entity.Country;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DatNuclear 15/02/2024
 * @project cartoon
 */
@Data
@NoArgsConstructor
public class CountryDto {
    private Long id;
    private String name;
    private String code;
    private Boolean voided;
    private String description;
    public CountryDto(Country entity) {
        if (entity != null){
            this.id = entity.getId();
            this.name = entity.getName();
            this.code = entity.getCode();
            this.voided = entity.getVoided();
            this.description = entity.getDescription();
        }
    }
}
