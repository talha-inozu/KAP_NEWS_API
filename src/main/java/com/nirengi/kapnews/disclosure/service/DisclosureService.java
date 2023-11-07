package com.nirengi.kapnews.disclosure.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.nirengi.kapnews.disclosure.dto.DisclosureEntity;
import com.nirengi.kapnews.disclosure.dto.DisclosureDto;

public interface DisclosureService {

    public List<DisclosureDto> getAllDisclosures();

    public ResponseEntity<DisclosureDto> saveDisclosure(DisclosureDto disclosureDto);

    public ResponseEntity<DisclosureDto> getDisclosureById(Long id);

    //Model Wrapper
    public DisclosureDto entityToDto(DisclosureEntity disclosureEntity);

    public DisclosureEntity dtoToEntity(DisclosureDto disclosureDto);

    public List<DisclosureEntity> getAllDisclosureEntities();
}
