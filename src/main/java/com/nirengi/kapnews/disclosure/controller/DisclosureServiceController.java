package com.nirengi.kapnews.disclosure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nirengi.kapnews.disclosure.dto.DisclosureDto;
import com.nirengi.kapnews.disclosure.service.DisclosureService;
import com.nirengi.kapnews.disclosure.dto.DisclosureEntity;

@RestController
@RequestMapping("/disclosures")
public class DisclosureServiceController {

    @Autowired
    DisclosureService disclosureService;

    @GetMapping
    public List<DisclosureDto> getAllDisclosures() {
        return disclosureService.getAllDisclosures();
    }
    @GetMapping(value = "/getAllData")
    public List<DisclosureEntity> getAllDisclosureEntities() {
        return disclosureService.getAllDisclosureEntities();
    }
}
