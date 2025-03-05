package com.co.jm.employee.management.controller.dto;

import java.math.BigDecimal;

public record EmployeeDto(
    Integer employeeId, String name, String lastName, BigDecimal annualSalary) {}
