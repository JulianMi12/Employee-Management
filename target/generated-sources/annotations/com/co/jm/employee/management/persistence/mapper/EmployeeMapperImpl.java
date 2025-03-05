package com.co.jm.employee.management.persistence.mapper;

import com.co.jm.employee.management.data.EmployeeData;
import com.co.jm.employee.management.persistence.entity.EmployeeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-05T13:28:47-0500",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeData toData(EmployeeEntity employeeEntity) {
        if ( employeeEntity == null ) {
            return null;
        }

        EmployeeData.EmployeeDataBuilder employeeData = EmployeeData.builder();

        if ( employeeEntity.getEmployeeId() != null ) {
            employeeData.employeeId( employeeEntity.getEmployeeId() );
        }
        if ( employeeEntity.getName() != null ) {
            employeeData.name( employeeEntity.getName() );
        }
        if ( employeeEntity.getLastName() != null ) {
            employeeData.lastName( employeeEntity.getLastName() );
        }
        if ( employeeEntity.getSalary() != null ) {
            employeeData.salary( employeeEntity.getSalary() );
        }

        return employeeData.build();
    }

    @Override
    public List<EmployeeData> toDataList(List<EmployeeEntity> employeeEntityList) {
        if ( employeeEntityList == null ) {
            return null;
        }

        List<EmployeeData> list = new ArrayList<EmployeeData>( employeeEntityList.size() );
        for ( EmployeeEntity employeeEntity : employeeEntityList ) {
            list.add( toData( employeeEntity ) );
        }

        return list;
    }
}
