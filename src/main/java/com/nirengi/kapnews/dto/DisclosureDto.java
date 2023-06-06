package com.nirengi.kapnews.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
public class DisclosureDto {

    private Long id;
    private String disclosureId;
    private String stockCode;
    private String title;
    private String summary;
    private Date publishDate;

}
