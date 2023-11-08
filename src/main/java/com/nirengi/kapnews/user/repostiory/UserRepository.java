package com.nirengi.kapnews.user.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nirengi.kapnews.user.dto.UserEntity;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity,Long>  {
    public UserEntity findByUsername(String username);
}
