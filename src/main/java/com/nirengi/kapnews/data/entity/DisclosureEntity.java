package com.nirengi.kapnews.data.entity;


import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

@Entity
@Table(name = "disclosures")
public class DisclosureEntity extends BaseEntity {

    @Column(name = "disclosureId")
    private String disclosureId;
    @Column(name = "stockCode")
    private String stockCode;
    @Column(name = "title")
    private String title;
    @Column(name = "summary")
    private String summary;
    @Column(name = "publishDate")
    private String publishDate;


}
