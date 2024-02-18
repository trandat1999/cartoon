package com.thd.cartoon.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author DatNuclear 15/02/2024
 * @project cartoon
 */
@Entity
@Table(name = "tbl_movie_link")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MovieLink extends BaseEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "orderNumber")
    private Integer orderNumber;
    @Column(name = "embedded_link")
    private String embeddedLink;
    @ManyToOne
    @JoinColumn(name = "file_id")
    private File file;
    @ManyToOne
    @JoinColumn(name = "movie_episode_id")
    private MovieEpisode episode;
}
