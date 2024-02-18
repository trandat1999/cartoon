package com.thd.cartoon.common.dto.movie;

import com.thd.cartoon.common.entity.MovieEpisode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DatNuclear 16/02/2024
 * @project cartoon
 */
@Data
@NoArgsConstructor
public class MovieEpisodeDto {
    private Long id;
    @Valid
    @NotNull(message = "{cartoon.validation.NotNull}")
    private MovieDto movie;
    @NotNull(message = "{cartoon.validation.NotNull}")
    @NotBlank(message = "{cartoon.validation.NotBlank}")
    private Integer episode;
    @NotNull(message = "{cartoon.validation.NotNull}")
    @NotBlank(message = "{cartoon.validation.NotBlank}")
    private String code;
    @NotNull(message = "{cartoon.validation.NotNull}")
    @NotBlank(message = "{cartoon.validation.NotBlank}")
    private String name;
    private String previousEpisode;

    public MovieEpisodeDto(MovieEpisode entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.episode = entity.getEpisode();
            this.code = entity.getCode();
            this.name = entity.getName();
            this.previousEpisode = entity.getPreviousEpisode();
            if (entity.getMovie() != null) {
                this.movie = new MovieDto(entity.getMovie(), true);
            }
        }
    }
}
