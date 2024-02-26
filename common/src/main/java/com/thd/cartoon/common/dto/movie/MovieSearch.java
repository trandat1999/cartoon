package com.thd.cartoon.common.dto.movie;

import com.thd.cartoon.common.dto.SearchRequest;
import lombok.Data;

/**
 * @author DatNuclear 16/02/2024
 * @project cartoon
 */
@Data
public class MovieSearch extends SearchRequest {
    private Long movieId;
}
