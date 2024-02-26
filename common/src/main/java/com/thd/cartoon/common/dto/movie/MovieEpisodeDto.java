package com.thd.cartoon.common.dto.movie;

import com.thd.cartoon.common.entity.MovieEpisode;
import com.thd.cartoon.common.entity.MovieLink;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DatNuclear 16/02/2024
 * @project cartoon
 */
@Data
@NoArgsConstructor
public class MovieEpisodeDto {
    private Long id;
    @NotNull(message = "{cartoon.validation.NotNull}")
    private MovieDto movie;
    @NotNull(message = "{cartoon.validation.NotNull}")
    private Integer episode;
    @NotNull(message = "{cartoon.validation.NotNull}")
    @NotBlank(message = "{cartoon.validation.NotBlank}")
    private String code;
    @NotNull(message = "{cartoon.validation.NotNull}")
    @NotBlank(message = "{cartoon.validation.NotBlank}")
    private String name;
    private String previousEpisode;
    private Integer part;
    @NotNull(message = "{cartoon.validation.NotNull}")
    @NotEmpty(message = "{cartoon.validation.NotEmpty}")
    @Valid
    private List<MovieLinkDto> links = new ArrayList<>();
    private Boolean voided;

    public MovieEpisodeDto(MovieEpisode entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.episode = entity.getEpisode();
            this.code = entity.getCode();
            this.name = entity.getName();
            this.previousEpisode = entity.getPreviousEpisode();
            this.part = entity.getPart();
            this.voided = entity.getVoided();
            if (entity.getMovie() != null) {
                this.movie = new MovieDto(entity.getMovie(), true);
            }
            if(!CollectionUtils.isEmpty(entity.getLinks())){
                for(MovieLink movieLink : entity.getLinks()) {
                    this.links.add(new MovieLinkDto(movieLink));
                }
            }
        }
    }
    public MovieEpisodeDto(MovieEpisode entity,boolean simple) {
        if (entity != null) {
            this.id = entity.getId();
            this.episode = entity.getEpisode();
            this.code = entity.getCode();
            this.name = entity.getName();
            this.previousEpisode = entity.getPreviousEpisode();
            this.part = entity.getPart();
            this.voided = entity.getVoided();
            if (entity.getMovie() != null) {
                this.movie = new MovieDto(entity.getMovie(), true);
            }
        }
    }
}
