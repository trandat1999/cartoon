package com.thd.cartoon.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author DatNuclear 26/01/2024
 * @project cartoon-movie
 */
@Entity
@Table(name = "tbl_attribute")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MovieAttribute extends BaseEntity{
    @Column(name = "code")
    private String code;
    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @Column(name = "value",columnDefinition = "text")
    private String value;
}
