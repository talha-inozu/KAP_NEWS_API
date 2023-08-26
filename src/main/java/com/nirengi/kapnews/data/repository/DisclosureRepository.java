package com.nirengi.kapnews.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nirengi.kapnews.data.entity.DisclosureEntity;

@Repository
public interface DisclosureRepository extends JpaRepository<DisclosureEntity,Long> {
}
