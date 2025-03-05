package com.co.jm.employee.management.persistence.impl;

import com.co.jm.employee.management.data.EmployeeData;
import com.co.jm.employee.management.persistence.entity.EmployeeEntity;
import com.co.jm.employee.management.persistence.jpa.EmployeeJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeRepositoryImplTest {

  @InjectMocks private EmployeeRepositoryImpl employeeRepository;

  @Mock
  private EmployeeJpaRepository employeeJpaRepository;

  @Test
  void getEmployeeInformationById_WhenEmployeeExists_ShouldReturnData() {
    Integer employeeId = 1;
    EmployeeEntity mockEntity = createEmployeeEntity(employeeId, "John", "Doe", new BigDecimal("5000.0"));

    when(employeeJpaRepository.findEmployeeById(employeeId))
        .thenReturn(mockEntity);

    Optional<EmployeeData> result = employeeRepository.getEmployeeInformationById(employeeId);

    assertTrue(result.isPresent());
    EmployeeData employeeData = result.get();
    assertEquals(employeeId, employeeData.getEmployeeId());
    assertEquals("John", employeeData.getName());
    assertEquals("Doe", employeeData.getLastName());
    assertEquals(new BigDecimal("5000.0"), employeeData.getSalary());
    verify(employeeJpaRepository).findEmployeeById(employeeId);
  }

  @Test
  void getEmployeeInformationById_WhenEmployeeNotFound_ShouldReturnEmpty() {
    Integer employeeId = 999;

    when(employeeJpaRepository.findEmployeeById(employeeId))
        .thenReturn(null);

    Optional<EmployeeData> result = employeeRepository.getEmployeeInformationById(employeeId);

    assertFalse(result.isPresent());
    verify(employeeJpaRepository).findEmployeeById(employeeId);
  }

  @Test
  void getEmployeesInformation_WhenEmployeesExist_ShouldReturnList() {
    List<EmployeeEntity> mockEntities =
        Arrays.asList(
            createEmployeeEntity(1, "John", "Doe", new BigDecimal("5000.0")),
            createEmployeeEntity(2, "Jane", "Smith", new BigDecimal("6000.0")));

    when(employeeJpaRepository.findAllEmployees())
        .thenReturn(mockEntities);

    Optional<List<EmployeeData>> result = employeeRepository.getEmployeesInformation();

    assertTrue(result.isPresent());
    List<EmployeeData> employeeDataList = result.get();
    assertEquals(2, employeeDataList.size());

    assertEquals(1, employeeDataList.get(0).getEmployeeId());
    assertEquals("John", employeeDataList.get(0).getName());
    assertEquals("Doe", employeeDataList.get(0).getLastName());
    assertEquals(new BigDecimal("5000.0"), employeeDataList.get(0).getSalary());

    assertEquals(2, employeeDataList.get(1).getEmployeeId());
    assertEquals("Jane", employeeDataList.get(1).getName());
    assertEquals("Smith", employeeDataList.get(1).getLastName());
    assertEquals(new BigDecimal("6000.0"), employeeDataList.get(1).getSalary());

    verify(employeeJpaRepository).findAllEmployees();
  }

  @Test
  void getEmployeesInformation_WhenNoEmployees_ShouldReturnEmpty() {
    when(employeeJpaRepository.findAllEmployees())
        .thenReturn(null);

    Optional<List<EmployeeData>> result = employeeRepository.getEmployeesInformation();

    assertFalse(result.isPresent());
    verify(employeeJpaRepository).findAllEmployees();
  }

  @Test
  void getEmployeesInformation_WhenEmptyList_ShouldReturnEmptyList() {
    when(employeeJpaRepository.findAllEmployees())
        .thenReturn(Collections.emptyList());

    Optional<List<EmployeeData>> result = employeeRepository.getEmployeesInformation();

    assertTrue(result.isPresent());
    assertTrue(result.get().isEmpty());
    verify(employeeJpaRepository).findAllEmployees();
  }

  @Test
  void getEmployeeInformationById_WhenNullId_ShouldReturnEmpty() {
    when(employeeJpaRepository.findEmployeeById(null))
        .thenReturn(null);

    Optional<EmployeeData> result = employeeRepository.getEmployeeInformationById(null);

    assertFalse(result.isPresent());
    verify(employeeJpaRepository).findEmployeeById(null);
  }

  @Test
  void getEmployeeInformationById_ShouldMapAllFieldsCorrectly() {
    Integer employeeId = 1;
    EmployeeEntity mockEntity = createCompleteEmployeeEntity();

    when(employeeJpaRepository.findEmployeeById(employeeId))
        .thenReturn(mockEntity);

    Optional<EmployeeData> result = employeeRepository.getEmployeeInformationById(employeeId);

    assertTrue(result.isPresent());
    EmployeeData employeeData = result.get();
    verifyEmployeeMapping(mockEntity, employeeData);
    verify(employeeJpaRepository).findEmployeeById(employeeId);
  }

  private EmployeeEntity createEmployeeEntity(Integer id, String name, String lastName, BigDecimal salary) {
    EmployeeEntity entity = new EmployeeEntity();
    entity.setEmployeeId(id);
    entity.setName(name);
    entity.setLastName(lastName);
    entity.setSalary(salary);
    return entity;
  }

  private EmployeeEntity createCompleteEmployeeEntity() {
    EmployeeEntity entity = new EmployeeEntity();
    entity.setEmployeeId(1);
    entity.setName("John Doe");
    entity.setSalary(new BigDecimal("5000.0"));
    return entity;
  }

  private void verifyEmployeeMapping(EmployeeEntity entity, EmployeeData data) {
    assertEquals(entity.getEmployeeId(), data.getEmployeeId());
    assertEquals(entity.getName(), data.getName());
    assertEquals(entity.getSalary(), data.getSalary());
  }
}
