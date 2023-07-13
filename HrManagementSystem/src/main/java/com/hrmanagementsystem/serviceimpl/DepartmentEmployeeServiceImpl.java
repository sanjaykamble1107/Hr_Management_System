package com.hrmanagementsystem.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrmanagementsystem.dto.DepartmentEmployeeDto;
import com.hrmanagementsystem.entity.DepartmentEmployee;
import com.hrmanagementsystem.exception.UniversalExceptionHandler;
import com.hrmanagementsystem.repository.DepartmentEmployeeRepository;
import com.hrmanagementsystem.service.DepartmentEmployeeService;

@Service
public class DepartmentEmployeeServiceImpl implements DepartmentEmployeeService {

	@Autowired
	private DepartmentEmployeeRepository departmentEmployeeRepository;

	// Method to add new department employee
	@Override
	public String addDepartmentEmployee(DepartmentEmployeeDto dto) {

		DepartmentEmployee deptemp = new DepartmentEmployee();
		BeanUtils.copyProperties(dto, deptemp);
		try {
			departmentEmployeeRepository.save(deptemp);
			return "New employee assigned  to department Successfully";
		} catch (Exception ex) {
			throw new UniversalExceptionHandler("Validation failed");
		}
	}

	// Method to retrieves all department employees
	@Override
	public List<DepartmentEmployeeDto> getAllDepartmentEmployee() {
		List<DepartmentEmployee> deptempList = departmentEmployeeRepository.findAll();
		List<DepartmentEmployeeDto> deptempdtoList = new ArrayList<>();
		for (DepartmentEmployee deptemp : deptempList) {
			DepartmentEmployeeDto deptempDto = new DepartmentEmployeeDto();
			BeanUtils.copyProperties(deptemp, deptempDto);
			deptempDto.setEmpNo(deptemp.getEmployees().getEmpNo());
			deptempDto.setDeptNo(deptemp.getDepartments().getDeptNo());
			deptempdtoList.add(deptempDto);
		}
		return deptempdtoList;
	}

	// Method to retrieves all department employees by deptNo and fromDate
	@Override
	public List<DepartmentEmployeeDto> getDepartmentEmployeeByDeptNoAndFromDate(String deptno, LocalDate fromdate) {
		List<DepartmentEmployee> deptempList = departmentEmployeeRepository.findByDeptNoAndFromDate(deptno, fromdate);
		List<DepartmentEmployeeDto> deptempDtoList = new ArrayList<>();
		for (DepartmentEmployee deptemp : deptempList) {
			DepartmentEmployeeDto deptempDto = new DepartmentEmployeeDto();
			BeanUtils.copyProperties(deptemp, deptempDto);
			deptempDto.setEmpNo(deptemp.getEmployees().getEmpNo());
			deptempDto.setDeptNo(deptemp.getDepartments().getDeptNo());
			deptempDtoList.add(deptempDto);
		}

		return deptempDtoList;
	}

	// Method to retrieves all department employees empNo and fromDate
	@Override
	public DepartmentEmployeeDto getDepartmentEmployeeByEmpNoAndFromDate(Integer empno, LocalDate fromdate) {
		DepartmentEmployee deptemp = departmentEmployeeRepository.findByEmpNoAndFromDate(empno, fromdate);
		DepartmentEmployeeDto deptempDto = new DepartmentEmployeeDto();
		BeanUtils.copyProperties(deptemp, deptempDto);
		deptempDto.setEmpNo(deptemp.getEmployees().getEmpNo());
		deptempDto.setDeptNo(deptemp.getDepartments().getDeptNo());

		return deptempDto;
	}

	// Method to retrieves all department employees by empNo and deptNo
	@Override
	public DepartmentEmployeeDto getDepartmentEmployeeByEmpNoAndDeptNo(Integer empno, String deptno) {
		DepartmentEmployee deptemp = departmentEmployeeRepository.findByEmpNoAndDeptNo(empno, deptno);
		DepartmentEmployeeDto deptempdto = new DepartmentEmployeeDto();
		BeanUtils.copyProperties(deptemp, deptempdto);
		deptempdto.setEmpNo(deptemp.getEmployees().getEmpNo());
		deptempdto.setDeptNo(deptemp.getDepartments().getDeptNo());
		return deptempdto;
	}

	// Method to retrieves all department employees by empNo and deptNo and
	// fromDates
	@Override
	public List<DepartmentEmployeeDto> getDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(Integer empno, String deptno,
			LocalDate fromdate) {
		List<DepartmentEmployee> deptempList = departmentEmployeeRepository.findByEmpNoAndDeptNoAndFromDate(empno,
				deptno, fromdate);
		List<DepartmentEmployeeDto> deptempDtoList = new ArrayList<>();
		for (DepartmentEmployee deptemp : deptempList) {
			DepartmentEmployeeDto deptempDto = new DepartmentEmployeeDto();
			BeanUtils.copyProperties(deptemp, deptempDto);
			deptempDto.setEmpNo(deptemp.getEmployees().getEmpNo());
			deptempDto.setDeptNo(deptemp.getDepartments().getDeptNo());
			deptempDtoList.add(deptempDto);
		}

		return deptempDtoList;
	}

	// Method to update department employees by empNo and deptNo
	@Override
	public String updateDepartmentEmployeeByEmpNoAndDeptNo(DepartmentEmployeeDto dto, Integer empno, String deptno) {
		try {
			DepartmentEmployee deptemp = departmentEmployeeRepository.findByEmpNoAndDeptNo(empno, deptno);
			deptemp.setFromDate(dto.getFromDate());
			deptemp.setToDate(dto.getToDate());
			departmentEmployeeRepository.save(deptemp);
			return "employee assigned  to department  updated Successfully";

		} catch (Exception e) {
			throw new UniversalExceptionHandler("Validation failed");
		}
	}

	// Method to update department employees by empNo and fromDate
	public String updateDepartmentEmployeeByEmpNoAndFromDate(DepartmentEmployeeDto dto, Integer empno,
			LocalDate fromdate) {
		try {
			DepartmentEmployee findByEmpNoAndFromDate = departmentEmployeeRepository.findByEmpNoAndFromDate(empno,
					fromdate);
			findByEmpNoAndFromDate.setFromDate(dto.getFromDate());
			findByEmpNoAndFromDate.setToDate(dto.getToDate());
			departmentEmployeeRepository.save(findByEmpNoAndFromDate);

			return "employee with no and fromdate  updated Successfully";

		} catch (Exception e) {
			throw new UniversalExceptionHandler("Validation failed");
		}
	}

	// Method to update department employees by deptNo and fromDate
	public List<DepartmentEmployeeDto> updateDepartmentEmployeeByDeptNoAndFromDate(DepartmentEmployeeDto dto,
			String deptno, LocalDate fromdate) {
		List<DepartmentEmployee> deptemplist = departmentEmployeeRepository.findByDeptNoAndFromDate(deptno, fromdate);
		List<DepartmentEmployeeDto> deptempdtolist = new ArrayList<>();
		for (DepartmentEmployee deptemp : deptemplist) {
			DepartmentEmployeeDto deptempdto = new DepartmentEmployeeDto();
			deptemp.setFromDate(dto.getFromDate());
			deptemp.setToDate(dto.getToDate());
			departmentEmployeeRepository.save(deptemp);
			BeanUtils.copyProperties(deptemp, deptempdto);
			deptempdto.setEmpNo(deptemp.getEmployees().getEmpNo());
			deptempdto.setDeptNo(deptemp.getDepartments().getDeptNo());
			deptempdtolist.add(deptempdto);

		}
		return deptempdtolist;
	}

	// Method to update department employees by empNo and deptNo and fromDate
	public String updateDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(DepartmentEmployeeDto dto, Integer empno,
			String deptno, LocalDate fromdate) {

		List<DepartmentEmployee> deptemplist = departmentEmployeeRepository.findByEmpNoAndDeptNoAndFromDate(empno,
				deptno, fromdate);
		List<DepartmentEmployeeDto> deptempdtolist = new ArrayList<>();
		for (DepartmentEmployee deptemp : deptemplist) {
			DepartmentEmployeeDto deptempdto = new DepartmentEmployeeDto();
			deptemp.setFromDate(dto.getFromDate());
			deptemp.setToDate(dto.getToDate());
			departmentEmployeeRepository.save(deptemp);
			BeanUtils.copyProperties(deptemp, deptempdto);
			deptempdto.setEmpNo(deptemp.getEmployees().getEmpNo());
			deptempdto.setDeptNo(deptemp.getDepartments().getDeptNo());
			deptempdtolist.add(deptempdto);

		}
		return "deptemp updated Successfully";
	}

}
