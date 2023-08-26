package com.nirengi.kapnews.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nirengi.kapnews.data.entity.DisclosureEntity;
import com.nirengi.kapnews.data.repository.DisclosureRepository;
import com.nirengi.kapnews.dto.DisclosureDto;

@Service
public class DisclosureServiceImpl implements DisclosureService {

    public static List<DisclosureDto> dailyDisclosures = new ArrayList<>();

    @Autowired
    DisclosureRepository disclosureRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<DisclosureDto> getAllDisclosures() {
        List<DisclosureDto> responseList = new ArrayList<>();

        Iterable<DisclosureEntity> entities = disclosureRepository.findAll();

        for (DisclosureEntity disclosureEntity : entities) {
            responseList.add(entityToDto(disclosureEntity));
        }
        return responseList;
    }

    @Override
    public ResponseEntity saveDisclosure(DisclosureDto disclosureDto) {
        for (DisclosureDto disclosure : dailyDisclosures) {
            if (disclosure.getDisclosureId().equals(disclosureDto.getDisclosureId())) {
                return ResponseEntity.ok().build();
            }
        }

        DisclosureEntity disclosureEntity = dtoToEntity(disclosureDto);

        dailyDisclosures.add(disclosureDto);

        DisclosureEntity responseEntity = disclosureRepository.save(disclosureEntity);
        return ResponseEntity.ok(entityToDto(responseEntity));
    }

    @Override
    public ResponseEntity<DisclosureDto> getDisclosureById(Long id) {
        return null;
    }

    @Override
    public DisclosureDto entityToDto(DisclosureEntity disclosureEntity) {
        DisclosureDto disclosureDto = modelMapper.map(disclosureEntity, DisclosureDto.class);
        return disclosureDto;
    }

    @Override
    public DisclosureEntity dtoToEntity(DisclosureDto disclosureDto) {
        DisclosureEntity disclosureEntity = modelMapper.map(disclosureDto, DisclosureEntity.class);
        return disclosureEntity;
    }
}
