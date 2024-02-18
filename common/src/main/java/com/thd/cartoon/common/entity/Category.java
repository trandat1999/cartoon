package com.thd.cartoon.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author DatNuclear 26/01/2024
 * @project cartoon-movie
 */
@Entity
@Table(name = "tbl_category")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "code")
    private String code;
}
