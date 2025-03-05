package com.co.jm.employee.management.persistence.impl;

import com.co.jm.employee.management.data.EmployeeData;
import com.co.jm.employee.management.persistence.EmployeeRepository;
import com.co.jm.employee.management.persistence.jpa.EmployeeJpaRepository;
import com.co.jm.employee.management.persistence.mapper.EmployeeMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

  private final EmployeeJpaRepository employeeJpaRepository;

  @Override
  public Optional<List<EmployeeData>> getEmployeesInformation() {
    return Optional.ofNullable(employeeJpaRepository.findAllEmployees())
        .map(EmployeeMapper.INSTANCE::toDataList);
  }

  @Override
  public Optional<EmployeeData> getEmployeeInformationById(Integer employeeId) {
    return Optional.ofNullable(employeeJpaRepository.findEmployeeById(employeeId))
        .map(EmployeeMapper.INSTANCE::toData);
  }
}
