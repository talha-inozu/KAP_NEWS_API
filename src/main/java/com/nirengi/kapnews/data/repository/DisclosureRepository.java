package com.nirengi.kapnews.data.repository;

import com.nirengi.kapnews.data.entity.DisclosureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisclosureRepository extends JpaRepository<DisclosureEntity,Long> {
}
