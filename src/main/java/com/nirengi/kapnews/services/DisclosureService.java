package com.nirengi.kapnews.services;

import com.nirengi.kapnews.data.entity.DisclosureEntity;
import com.nirengi.kapnews.dto.DisclosureDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DisclosureService {
    public List<DisclosureDto> getAllDisclosures();

    public ResponseEntity<DisclosureDto> saveDisclosure(DisclosureDto disclosureDto);


    public ResponseEntity<DisclosureDto> getDisclosureById(Long id);


    //Model Wrapper
    public  DisclosureDto entityToDto(DisclosureEntity disclosureEntity);
    public DisclosureEntity dtoToEntity(DisclosureDto disclosureDto);
}
