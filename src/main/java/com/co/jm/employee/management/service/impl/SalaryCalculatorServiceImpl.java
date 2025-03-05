package com.co.jm.employee.management.service.impl;

import com.co.jm.employee.management.service.SalaryCalculatorService;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class SalaryCalculatorServiceImpl implements SalaryCalculatorService {

  private static final BigDecimal ANNUAL_MONTHS = BigDecimal.valueOf(12);

  @Override
  public BigDecimal calculateAnnualSalary(BigDecimal monthlySalary) {
    if (monthlySalary == null) {
      throw new IllegalArgumentException("Salary cannot be null");
    }
    return monthlySalary.multiply(ANNUAL_MONTHS);
  }
}
