package com.co.jm.employee.management.controller.mapper;

import com.co.jm.employee.management.controller.dto.EmployeeDto;
import com.co.jm.employee.management.data.EmployeeData;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-05T13:28:47-0500",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDto toDto(EmployeeData employeeData) {
        if ( employeeData == null ) {
            return null;
        }

        BigDecimal annualSalary = null;
        Integer employeeId = null;
        String name = null;
        String lastName = null;

        if ( employeeData.getSalary() != null ) {
            annualSalary = employeeData.getSalary();
        }
        if ( employeeData.getEmployeeId() != null ) {
            employeeId = employeeData.getEmployeeId();
        }
        if ( employeeData.getName() != null ) {
            name = employeeData.getName();
        }
        if ( employeeData.getLastName() != null ) {
            lastName = employeeData.getLastName();
        }

        EmployeeDto employeeDto = new EmployeeDto( employeeId, name, lastName, annualSalary );

        return employeeDto;
    }

    @Override
    public List<EmployeeDto> toDtoList(List<EmployeeData> employeeDataList) {
        if ( employeeDataList == null ) {
            return null;
        }

        List<EmployeeDto> list = new ArrayList<EmployeeDto>( employeeDataList.size() );
        for ( EmployeeData employeeData : employeeDataList ) {
            list.add( toDto( employeeData ) );
        }

        return list;
    }
}
