package com.co.jm.employee.management.persistence.mapper;

import com.co.jm.employee.management.data.EmployeeData;
import com.co.jm.employee.management.persistence.entity.EmployeeEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {

  EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

  @Named("toData")
  EmployeeData toData(EmployeeEntity employeeEntity);

  @IterableMapping(qualifiedByName = "toData")
  List<EmployeeData> toDataList(List<EmployeeEntity> employeeEntityList);
}