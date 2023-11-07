package com.nirengi.kapnews.disclosure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nirengi.kapnews.disclosure.dto.DisclosureEntity;

@Repository
public interface DisclosureRepository extends JpaRepository<DisclosureEntity,Long> {
}
