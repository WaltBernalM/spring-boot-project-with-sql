package com.walterbernal.projectwithsql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walterbernal.projectwithsql.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
  
}
