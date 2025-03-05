package com.co.jm.employee.management.controller.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.co.jm.employee.management.controller.dto.EmployeeDto;
import com.co.jm.employee.management.controller.mapper.EmployeeMapper;
import com.co.jm.employee.management.data.EmployeeData;
import com.co.jm.employee.management.service.EmployeeService;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerImplTest {

  @InjectMocks private EmployeeControllerImpl employeeController;

  @Mock private EmployeeService employeeService;

  @Mock private EmployeeMapper employeeMapper;

  @Test
  void getEmployeeInformation_WhenDataExists_ShouldReturnEmployeeList() {
    List<EmployeeData> employeeEntities = createEmployeeDataList();
    List<EmployeeDto> expectedDtos = createEmployeeDtos();

    when(employeeService.getEmployeeInformation()).thenReturn(employeeEntities);

    List<EmployeeDto> result = employeeController.getEmployeeInformation();

    assertNotNull(result);
    assertEquals(expectedDtos.size(), result.size());
    verify(employeeService).getEmployeeInformation();
    verifyEmployeeData(result, expectedDtos);
  }

  @Test
  void getEmployeeInformation_WhenNoData_ShouldReturnEmptyList() {
    when(employeeService.getEmployeeInformation()).thenReturn(Collections.emptyList());

    List<EmployeeDto> result = employeeController.getEmployeeInformation();

    assertNotNull(result);
    assertTrue(result.isEmpty());
    verify(employeeService).getEmployeeInformation();
  }

  @Test
  void getEmployeeInformation_WhenServiceFails_ShouldThrowException() {
    when(employeeService.getEmployeeInformation()).thenThrow(new RuntimeException("Service error"));

    assertThrows(RuntimeException.class, () -> employeeController.getEmployeeInformation());
    verify(employeeService).getEmployeeInformation();
  }

  private List<EmployeeData> createEmployeeDataList() {
    return Arrays.asList(
        EmployeeData.builder()
            .employeeId(123)
            .name("John")
            .lastName("Doe")
            .salary(new BigDecimal(5000000))
            .build(),
        EmployeeData.builder()
            .employeeId(456)
            .name("Jane")
            .lastName("Smith")
            .salary(new BigDecimal(6000000))
            .build());
  }

  private List<EmployeeDto> createEmployeeDtos() {
    return Arrays.asList(
        new EmployeeDto(123, "John", "Doe", new BigDecimal(5000000)),
        new EmployeeDto(456, "Jane", "Smith", new BigDecimal(6000000)));
  }

  private void verifyEmployeeData(List<EmployeeDto> actual, List<EmployeeDto> expected) {
    assertEquals(expected.size(), actual.size());
    for (int i = 0; i < expected.size(); i++) {
      EmployeeDto expectedDto = expected.get(i);
      EmployeeDto actualDto = actual.get(i);

      assertEquals(expectedDto.employeeId(), actualDto.employeeId());
      assertEquals(expectedDto.name(), actualDto.name());
      assertEquals(expectedDto.lastName(), actualDto.lastName());
      assertEquals(expectedDto.annualSalary(), actualDto.annualSalary());
    }
  }

  @Test
  void getEmployeeInformationById_WhenEmployeeExists_ShouldReturnEmployee() {
    Integer employeeId = 123;
    EmployeeData employee = createEmployeeData(employeeId);
    EmployeeDto expectedDto = createEmployeeDto(employeeId);

    when(employeeService.getEmployeeInformationById(employeeId)).thenReturn(employee);

    EmployeeDto result = employeeController.getEmployeeInformationById(employeeId);

    assertNotNull(result);
    assertEquals(expectedDto.employeeId(), result.employeeId());
    assertEquals(expectedDto.name(), result.name());
    assertEquals(expectedDto.lastName(), result.lastName());
    assertEquals(expectedDto.annualSalary(), result.annualSalary());
    verify(employeeService).getEmployeeInformationById(employeeId);
  }

  private EmployeeData createEmployeeData(Integer employeeId) {
    return EmployeeData.builder()
            .employeeId(employeeId)
            .name("John")
            .lastName("Doe")
            .salary(new BigDecimal(5000000))
            .build();
  }

  private EmployeeDto createEmployeeDto(Integer employeeId) {
    return new EmployeeDto(employeeId, "John", "Doe", new BigDecimal(5000000));
  }
}
