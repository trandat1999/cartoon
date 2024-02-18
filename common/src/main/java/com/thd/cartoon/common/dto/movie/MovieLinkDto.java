package com.thd.cartoon.common.dto.movie;

import com.thd.cartoon.common.dto.file.FileDto;
import com.thd.cartoon.common.entity.MovieLink;
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
public class MovieLinkDto {
    private Long id;
    @NotNull(message = "{cartoon.validation.NotNull}")
    @NotBlank(message = "{cartoon.validation.NotBlank}")
    private String name;
    @NotNull(message = "{cartoon.validation.NotNull}")
    private Integer orderNumber;
    private String embeddedLink;
    private FileDto file;
    private MovieEpisodeDto episode;

    public MovieLinkDto(MovieLink entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.orderNumber = entity.getOrderNumber();
            this.embeddedLink = entity.getEmbeddedLink();
            this.file = new FileDto(entity.getFile());
            this.episode = new MovieEpisodeDto(entity.getEpisode());
        }
    }
}
