package com.co.jm.employee.management.persistence.jpa;

import com.co.jm.employee.management.persistence.entity.EmployeeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, Integer> {

  @Query(
      """
      SELECT e
      FROM EmployeeEntity e
      """)
  List<EmployeeEntity> findAllEmployees();

  @Query(
      """
      SELECT e
      FROM EmployeeEntity e
      WHERE e.employeeId = :employeeId
      """)
  EmployeeEntity findEmployeeById(@Param("employeeId") Integer employeeId);
}
