package com.co.jm.employee.management.controller.impl;

import com.co.jm.employee.management.controller.EmployeeController;
import com.co.jm.employee.management.controller.dto.EmployeeDto;
import com.co.jm.employee.management.controller.mapper.EmployeeMapper;
import com.co.jm.employee.management.service.EmployeeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
@RequiredArgsConstructor
public class EmployeeControllerImpl implements EmployeeController {

  private final EmployeeService employeeService;

  @Override
  @GetMapping("/employees")
  public List<EmployeeDto> getEmployeeInformation() {
    log.info("Start GET /employees");
    return EmployeeMapper.INSTANCE.toDtoList(employeeService.getEmployeeInformation());
  }

  @Override
  @GetMapping("/employee/{employeeId}")
  public EmployeeDto getEmployeeInformationById(@PathVariable("employeeId") Integer employeeId) {
    log.info("Start GET customer-info/{customerId}");
    return EmployeeMapper.INSTANCE.toDto(employeeService.getEmployeeInformationById(employeeId));
  }
}
