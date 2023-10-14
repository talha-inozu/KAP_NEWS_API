package com.nirengi.kapnews.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.nirengi.kapnews.data.entity.DisclosureEntity;
import com.nirengi.kapnews.dto.DisclosureDto;

public interface DisclosureService {

    public List<DisclosureDto> getAllDisclosures();

    public ResponseEntity<DisclosureDto> saveDisclosure(DisclosureDto disclosureDto);

    public ResponseEntity<DisclosureDto> getDisclosureById(Long id);

    //Model Wrapper
    public DisclosureDto entityToDto(DisclosureEntity disclosureEntity);

    public DisclosureEntity dtoToEntity(DisclosureDto disclosureDto);

    public List<DisclosureEntity> getAllDisclosureEntities();
}
