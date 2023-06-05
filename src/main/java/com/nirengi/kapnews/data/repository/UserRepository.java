package com.nirengi.kapnews.data.repository;


import com.nirengi.kapnews.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity,Long> {
}
