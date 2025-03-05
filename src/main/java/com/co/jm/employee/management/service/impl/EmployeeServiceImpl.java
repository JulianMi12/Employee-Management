package com.co.jm.employee.management.service.impl;

import com.co.jm.employee.management.data.EmployeeData;
import com.co.jm.employee.management.exception.EmployeeNotFoundException;
import com.co.jm.employee.management.persistence.EmployeeRepository;
import com.co.jm.employee.management.service.EmployeeService;
import com.co.jm.employee.management.service.SalaryCalculatorService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final SalaryCalculatorService salaryCalculatorService;

  @Override
  public List<EmployeeData> getEmployeeInformation() {
    log.info("<<<< Start getEmployeeInformation");

    List<EmployeeData> employeeDataList =
        employeeRepository
            .getEmployeesInformation()
            .orElseThrow(
                EmployeeNotFoundException::new);

    List<EmployeeData> updatedEmployeeDataList =
        employeeDataList.stream()
            .map(this::mapEmployeeWithAnnualSalary)
            .collect(Collectors.toList());

    log.info("<<<< End getEmployeeInformation");
    return updatedEmployeeDataList;
  }

  @Override
  public EmployeeData getEmployeeInformationById(Integer employeeId) {
    log.info("<<<< Start getEmployeeInformationById - employeeId {}", employeeId);
    EmployeeData employeeData =
        employeeRepository
            .getEmployeeInformationById(employeeId)
            .map(this::mapEmployeeWithAnnualSalary)
            .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    log.info("<<<< End getEmployeeInformationById - employeeId {}", employeeId);
    return employeeData;
  }

  private EmployeeData mapEmployeeWithAnnualSalary(EmployeeData employee) {
    return EmployeeData.builder()
        .employeeId(employee.getEmployeeId())
        .name(employee.getName())
        .lastName(employee.getLastName())
        .salary(salaryCalculatorService.calculateAnnualSalary(employee.getSalary()))
        .build();
  }
}
