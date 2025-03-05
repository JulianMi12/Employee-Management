package com.co.jm.employee.management.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SalaryCalculatorServiceImplTest {

  @InjectMocks private SalaryCalculatorServiceImpl salaryCalculatorService;

  @Test
  void calculateAnnualSalary_WhenPositiveMonthlySalary_ShouldCalculateCorrectly() {
    BigDecimal monthlySalary = new BigDecimal("5000.00");
    BigDecimal expectedAnnualSalary = new BigDecimal("60000.00");

    BigDecimal result = salaryCalculatorService.calculateAnnualSalary(monthlySalary);

    assertEquals(
        0,
        expectedAnnualSalary.compareTo(result),
        "Annual salary should be monthly salary multiplied by 12");
  }

  @Test
  void calculateAnnualSalary_WhenSalaryIsNull_ShouldThrowException() {
    BigDecimal monthlySalary = null;

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> salaryCalculatorService.calculateAnnualSalary(monthlySalary));

    assertEquals("Salary cannot be null", exception.getMessage());
  }

  @Test
  void calculateAnnualSalary_WhenZeroMonthlySalary_ShouldReturnZero() {
    BigDecimal monthlySalary = BigDecimal.ZERO;
    BigDecimal expectedAnnualSalary = BigDecimal.ZERO;

    BigDecimal result = salaryCalculatorService.calculateAnnualSalary(monthlySalary);

    assertEquals(
        0,
        expectedAnnualSalary.compareTo(result),
        "Annual salary should be zero when monthly salary is zero");
  }

  @Test
  void calculateAnnualSalary_WhenDecimalMonthlySalary_ShouldCalculateCorrectly() {
    BigDecimal monthlySalary = new BigDecimal("1234.56");
    BigDecimal expectedAnnualSalary = new BigDecimal("14814.72");

    BigDecimal result = salaryCalculatorService.calculateAnnualSalary(monthlySalary);

    assertEquals(
        0,
        expectedAnnualSalary.compareTo(result),
        "Annual salary should handle decimal values correctly");
  }

  @Test
  void calculateAnnualSalary_WhenLargeMonthlySalary_ShouldCalculateCorrectly() {
    BigDecimal monthlySalary = new BigDecimal("999999.99");
    BigDecimal expectedAnnualSalary = new BigDecimal("11999999.88");

    BigDecimal result = salaryCalculatorService.calculateAnnualSalary(monthlySalary);

    assertEquals(
        0,
        expectedAnnualSalary.compareTo(result),
        "Annual salary should handle large values correctly");
  }

  @Test
  void calculateAnnualSalary_WhenNegativeMonthlySalary_ShouldCalculateCorrectly() {
    BigDecimal monthlySalary = new BigDecimal("-5000.00");
    BigDecimal expectedAnnualSalary = new BigDecimal("-60000.00");

    BigDecimal result = salaryCalculatorService.calculateAnnualSalary(monthlySalary);

    assertEquals(
        0,
        expectedAnnualSalary.compareTo(result),
        "Annual salary should handle negative values correctly");
  }

  @Test
  void calculateAnnualSalary_WhenSmallDecimalSalary_ShouldMaintainPrecision() {
    BigDecimal monthlySalary = new BigDecimal("1000.001");
    BigDecimal expectedAnnualSalary = new BigDecimal("12000.012");

    BigDecimal result = salaryCalculatorService.calculateAnnualSalary(monthlySalary);

    assertEquals(
        0,
        expectedAnnualSalary.compareTo(result),
        "Annual salary should maintain decimal precision");
  }

  @Test
  void calculateAnnualSalary_WhenMaxValue_ShouldCalculateCorrectly() {
    BigDecimal monthlySalary = new BigDecimal(Double.MAX_VALUE);
    BigDecimal expectedAnnualSalary = monthlySalary.multiply(new BigDecimal("12"));

    BigDecimal result = salaryCalculatorService.calculateAnnualSalary(monthlySalary);

    assertEquals(
        0,
        expectedAnnualSalary.compareTo(result),
        "Annual salary should handle maximum values correctly");
  }
}
