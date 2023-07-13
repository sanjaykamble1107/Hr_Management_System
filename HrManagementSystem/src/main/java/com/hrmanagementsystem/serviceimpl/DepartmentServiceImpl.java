package com.hrmanagementsystem.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrmanagementsystem.dto.DepartmentsDto;
import com.hrmanagementsystem.entity.Departments;
import com.hrmanagementsystem.exception.UniversalExceptionHandler;
import com.hrmanagementsystem.repository.DepartmentsRepository;
import com.hrmanagementsystem.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentsRepository departmentRepository;

	// Method to add new department
	@Override
	public String addDepartment(DepartmentsDto departmentdto) {
		Departments department = new Departments();
		BeanUtils.copyProperties(departmentdto, department);
		try {
			departmentRepository.save(department);
			return "New department added successfully";
		} catch (Exception ex) {
			throw new UniversalExceptionHandler("Validation failed");
		}
	}

	// Retrieve all departments from the repository
	@Override
	public List<DepartmentsDto> getAllDepartments() {
		List<Departments> departmentslist = departmentRepository.findAll();
		List<DepartmentsDto> departmentsdtolist = new ArrayList<>();
		for (Departments dept : departmentslist) {
			DepartmentsDto deptdto = new DepartmentsDto();
			BeanUtils.copyProperties(dept, deptdto);
			departmentsdtolist.add(deptdto);
		}
		return departmentsdtolist;
	}

	// Method to retrieve an department by deptno
	public DepartmentsDto getDepartmentByNo(String deptno) {
		Optional<Departments> department = departmentRepository.findById(deptno);
		DepartmentsDto departmentdto = new DepartmentsDto();
		BeanUtils.copyProperties(department.get(), departmentdto);

		return departmentdto;
	}

	// Method to retrieve an department by deptname
	@Override
	public DepartmentsDto getDepartmentByName(String deptname) {
		Departments department = departmentRepository.findByDeptName(deptname);
		DepartmentsDto departmentdto = new DepartmentsDto();
		BeanUtils.copyProperties(department, departmentdto);

		return departmentdto;
	}

	// Method to update department by deptno
	public String updateDepartmentByNo(DepartmentsDto deptdto, String deptno) {
		try {
			Optional<Departments> dept = departmentRepository.findById(deptno);
			dept.get().setDeptName(deptdto.getDeptName());
			departmentRepository.save(dept.get());
			return "Department updated successfully";
		} catch (Exception e) {
			throw new UniversalExceptionHandler("Validation Failed");
		}
	}

	// Method to update department by deptname
	public String updateDepartmentByName(DepartmentsDto deptdto, String deptname) {
		try {
			DepartmentsDto departmentdto = getDepartmentByName(deptname);
			departmentdto.setDeptName(deptdto.getDeptName());
			Departments department = new Departments();
			BeanUtils.copyProperties(departmentdto, department);
			departmentRepository.save(department);
			return "Department updated successfully";
		} catch (Exception e) {
			throw new UniversalExceptionHandler("Validation Failed");
		}
	}

}
