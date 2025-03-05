package com.co.jm.employee.management.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class EmployeeData implements Serializable {
  private Integer employeeId;
  private String name;
  private String lastName;
  private BigDecimal salary;
}
