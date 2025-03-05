package com.co.jm.employee.management.service;

import java.math.BigDecimal;

public interface SalaryCalculatorService {
  BigDecimal calculateAnnualSalary(BigDecimal monthlySalary);
}
