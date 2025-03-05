package com.co.jm.employee.management.controller;

import com.co.jm.employee.management.controller.dto.EmployeeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import java.util.List;

public interface EmployeeController {

  @Operation(
      summary = "Obtain employees information",
      description = "Service to obtain the employees information",
      servers = {
        @Server(url = "http://localhost:8080/api/v1/employees", description = "Local Host")
      },
      responses = {
        @ApiResponse(responseCode = "200", description = "Request executed successfully")
      })
  List<EmployeeDto> getEmployeeInformation();

  @Operation(
      summary = "Obtain employee information",
      description = "Service to obtain the information for a specific employee",
      servers = {
        @Server(
            url = "http://localhost:8080/api/v1/employee/{employeeId}",
            description = "Local Host")
      },
      responses = {
        @ApiResponse(responseCode = "200", description = "Request executed successfully")
      })
  EmployeeDto getEmployeeInformationById(Integer employeeId);
}
