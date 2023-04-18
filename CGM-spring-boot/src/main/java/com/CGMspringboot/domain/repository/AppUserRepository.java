package com.CGMspringboot.domain.repository;

import com.CGMspringboot.domain.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Integer>{
}
