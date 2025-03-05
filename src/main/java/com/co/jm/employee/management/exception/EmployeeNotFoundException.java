package com.co.jm.employee.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {

  public EmployeeNotFoundException() {
    super("Employees not found");
  }

  public EmployeeNotFoundException(Integer employeeId) {
    super(String.format("Employee with ID %d not found", employeeId));
  }
}
