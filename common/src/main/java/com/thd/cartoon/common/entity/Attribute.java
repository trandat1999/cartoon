package com.thd.cartoon.common.entity;

import com.thd.cartoon.common.type.AttributeType;
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
public class Attribute extends BaseEntity{
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AttributeType type;
}
