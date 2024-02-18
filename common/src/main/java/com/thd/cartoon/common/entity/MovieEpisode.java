package com.thd.cartoon.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DatNuclear 15/02/2024
 * @project cartoon
 */
@Entity
@Table(name = "tbl_movie_episode")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MovieEpisode extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @Column(name = "episode")
    private Integer episode;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "previous_episode")
    private String previousEpisode;
    @OneToMany(orphanRemoval = true,mappedBy = "episode",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @OrderBy("orderNumber asc ")
    private List<MovieLink> links = new ArrayList<>();
}
