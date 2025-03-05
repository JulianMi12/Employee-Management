package com.co.jm.employee.management.service.impl;

import com.co.jm.employee.management.data.EmployeeData;
import com.co.jm.employee.management.persistence.EmployeeRepository;
import com.co.jm.employee.management.service.SalaryCalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

  @InjectMocks private EmployeeServiceImpl employeeService;

  @Mock private EmployeeRepository employeeRepository;
  @Mock private SalaryCalculatorService salaryCalculatorService;

  @Test
  void getEmployeeInformation_WhenEmployeesExist_ShouldReturnListWithAnnualSalary() {
    List<EmployeeData> mockEmployees =
        Arrays.asList(
            createEmployeeData(123, "John", "Doe", new BigDecimal("1000.0")),
            createEmployeeData(456, "Jane", "Dae", new BigDecimal("2000.0")));

    when(employeeRepository.getEmployeesInformation()).thenReturn(Optional.of(mockEmployees));

    List<EmployeeData> result = employeeService.getEmployeeInformation();

    assertNotNull(result);
    assertEquals(2, result.size());
    verify(employeeRepository).getEmployeesInformation();
  }

  @Test
  void getEmployeeInformation_WhenNoEmployeesFound_ShouldThrowException() {
    when(employeeRepository.getEmployeesInformation()).thenReturn(Optional.empty());

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> employeeService.getEmployeeInformation());

    assertEquals("Employees not found", exception.getMessage());
    verify(employeeRepository).getEmployeesInformation();
  }

  @Test
  void getEmployeeInformation_WhenRepositoryReturnsEmptyList_ShouldReturnEmptyList() {
    when(employeeRepository.getEmployeesInformation()).thenReturn(Optional.of(new ArrayList<>()));

    List<EmployeeData> result = employeeService.getEmployeeInformation();

    assertNotNull(result);
    assertTrue(result.isEmpty());
    verify(employeeRepository).getEmployeesInformation();
  }

  @Test
  void getEmployeeInformation_ShouldCalculateAnnualSalaryCorrectly() {
    List<EmployeeData> mockEmployees =
        Arrays.asList(
            createEmployeeData(123, "John", "Doe", new BigDecimal("1500.0")),
            createEmployeeData(456, "Jane", "Dae", new BigDecimal("2500.0")));

    when(employeeRepository.getEmployeesInformation()).thenReturn(Optional.of(mockEmployees));

    List<EmployeeData> result = employeeService.getEmployeeInformation();

    assertNotNull(result);
    assertEquals(2, result.size());
    verify(employeeRepository).getEmployeesInformation();
  }

  @Test
  void getEmployeeInformation_WhenSalaryIsNull_ShouldHandleGracefully() {
    List<EmployeeData> mockEmployees =
        Arrays.asList(createEmployeeData(123, "John", "Doe", null));

    when(employeeRepository.getEmployeesInformation()).thenReturn(Optional.of(mockEmployees));

    List<EmployeeData> result = employeeService.getEmployeeInformation();

    assertNotNull(result);
    assertEquals(1, result.size());
    verify(employeeRepository).getEmployeesInformation();
  }

  private EmployeeData createEmployeeData(
      Integer id, String name, String lastName, BigDecimal salary) {
    return EmployeeData.builder()
        .employeeId(id)
        .name(name)
        .lastName(lastName)
        .salary(salary)
        .build();
  }

  @Test
  void getEmployeeInformationById_WhenEmployeeExists_ShouldReturnWithAnnualSalary() {
    Integer employeeId = 1;
    EmployeeData mockEmployee = createEmployeeData(employeeId, "John" ,"Doe", new BigDecimal("1500.0"));

    when(employeeRepository.getEmployeeInformationById(employeeId))
        .thenReturn(Optional.of(mockEmployee));

    EmployeeData result = employeeService.getEmployeeInformationById(employeeId);

    assertNotNull(result);
    verify(employeeRepository).getEmployeeInformationById(employeeId);
  }

  @Test
  void getEmployeeInformationById_WhenEmployeeNotFound_ShouldThrowException() {
    Integer employeeId = 999;

    when(employeeRepository.getEmployeeInformationById(employeeId))
        .thenReturn(Optional.empty());

    RuntimeException exception = assertThrows(
        RuntimeException.class,
        () -> employeeService.getEmployeeInformationById(employeeId)
    );

    assertEquals(
        String.format("Employee with ID %d not found", employeeId),
        exception.getMessage()
    );
    verify(employeeRepository).getEmployeeInformationById(employeeId);
  }
}
