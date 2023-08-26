package com.nirengi.kapnews.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nirengi.kapnews.dto.DisclosureDto;
import com.nirengi.kapnews.services.DisclosureService;

@RestController
@RequestMapping("/disclosures")
public class DisclosureServiceController {

    @Autowired
    DisclosureService disclosureService;

    @GetMapping
    public List<DisclosureDto> getAllDisclosures() {
        return disclosureService.getAllDisclosures();
    }
    
}
