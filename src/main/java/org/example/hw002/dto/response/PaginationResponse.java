package org.example.hw002.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationResponse {
    private Integer totalElements;
    private Integer currentPage;
    private  Integer pageSize;
    private Integer totalPages;
}
