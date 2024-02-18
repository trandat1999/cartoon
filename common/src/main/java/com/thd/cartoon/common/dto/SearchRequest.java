package com.thd.cartoon.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author DatNuclear 16/01/2024
 * @project cartoon-movie
 */
@Data
@EqualsAndHashCode
public class SearchRequest {
    protected Boolean voided;
    protected String keyword;
    protected Integer pageSize;
    protected Integer pageIndex;
}
