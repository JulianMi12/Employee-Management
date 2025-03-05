package com.co.jm.employee.management.persistence;

import com.co.jm.employee.management.data.EmployeeData;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
  Optional<List<EmployeeData>> getEmployeesInformation();

  Optional<EmployeeData> getEmployeeInformationById(Integer employeeId);
}
