package com.hrmanagementsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hrmanagementsystem.dto.DepartmentEmployeeDto;
import com.hrmanagementsystem.dto.DepartmentManagerDto;
import com.hrmanagementsystem.dto.DepartmentsDto;
import com.hrmanagementsystem.dto.EmployeesDto;
import com.hrmanagementsystem.dto.GetEmployeeJoinedAfterHireDate;
import com.hrmanagementsystem.dto.RequestDto;
import com.hrmanagementsystem.dto.ResponseDto;
import com.hrmanagementsystem.dto.TitlesDto;

@Service
public interface AdminHrmsConsumerService {



	List<GetEmployeeJoinedAfterHireDate> getEmployeeByLastNYear(Integer noofyear);

	Integer getCountEmployeeByLastNYear(Integer noofyear);

	List<EmployeesDto> getEmployeeListByNJoinYear(Integer year);

	Integer getEmployeeCountByNJoinYear(Integer year);

	ResponseDto saveDeptEmp(DepartmentEmployeeDto dto);

	ResponseDto saveDeptMgr(DepartmentManagerDto dto);

	ResponseDto assignTitle(TitlesDto dto);

	ResponseDto addDepartment(DepartmentsDto dto);

	ResponseDto addEmployee(EmployeesDto dto);

	List<EmployeesDto> getExperiencedEmployeeByYear(Integer noofyear);

	Integer getEmployeeCountByGender(String gender);


	ResponseDto updateSalaryByRating(RequestDto dto, Integer empno);

	ResponseDto deleteEmployee(Integer empno);

}
