package com.thd.cartoon.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author DatNuclear 26/01/2024
 * @project cartoon-movie
 */
@Entity
@Table(name = "tbl_movie")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends BaseEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "other_name")
    private String otherName;
    @Column(name = "code")
    private String code;
    @Column(name = "description",columnDefinition = "text")
    private String description;
    @Column(name = "publish_year")
    private Integer publishYear;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "tbl_movie_category",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    @Where(clause = "voided = false or voided is null")
    private Set<Category> categories = new HashSet<>();
    @OneToMany
    @Where(clause = "(attribute.voided = false or attribute.voided is null)")
    private List<MovieAttribute> attributes = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "file_id")
    private File file;
}
