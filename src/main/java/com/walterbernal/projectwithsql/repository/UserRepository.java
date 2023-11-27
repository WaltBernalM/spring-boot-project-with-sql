package com.walterbernal.projectwithsql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walterbernal.projectwithsql.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
  
}
