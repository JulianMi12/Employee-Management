package com.co.jm.employee.management.controller.mapper;

import com.co.jm.employee.management.controller.dto.EmployeeDto;
import com.co.jm.employee.management.data.EmployeeData;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {

  EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

  @Mapping(target = "annualSalary", source = "salary")
  @Named("toDto")
  EmployeeDto toDto(EmployeeData employeeData);

  @IterableMapping(qualifiedByName = "toDto")
  List<EmployeeDto> toDtoList(List<EmployeeData> employeeDataList);
}
