package com.co.jm.employee.management.service;

import com.co.jm.employee.management.data.EmployeeData;
import java.util.List;

public interface EmployeeService {
  List<EmployeeData> getEmployeeInformation();

  EmployeeData getEmployeeInformationById(Integer employeeId);
}
