package com.hrmanagementsystem.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrmanagementsystem.dto.DepartmentManagerDto;
import com.hrmanagementsystem.entity.DepartmentManager;
import com.hrmanagementsystem.exception.UniversalExceptionHandler;
import com.hrmanagementsystem.repository.DepartmentManagerRepository;
import com.hrmanagementsystem.service.DepartmentManagerService;

@Service
public class DepartmentManagerServiceImpl implements DepartmentManagerService {

	@Autowired
	private DepartmentManagerRepository departmentManagerRepository;

	// Method to add new department manager
	@Override
	public String addDepartmentManager(DepartmentManagerDto dto) {
		DepartmentManager deptmanager = new DepartmentManager();
		BeanUtils.copyProperties(dto, deptmanager);
		try {
			departmentManagerRepository.save(deptmanager);
		} catch (Exception e) {
			throw new UniversalExceptionHandler("Validation failed");
		}
		return "New employee assigned  as manager Successfully";
	}

	// Method to retrieves all department manager
	@Override
	public List<DepartmentManagerDto> getAllDepartmentManager() {
		List<DepartmentManager> deptmanagerList = departmentManagerRepository.findAll();
		List<DepartmentManagerDto> titlesdtolist = new ArrayList<>();
		for (DepartmentManager deptmanager : deptmanagerList) {
			DepartmentManagerDto dto = new DepartmentManagerDto();
			BeanUtils.copyProperties(deptmanager, dto);
			dto.setEmpNo(deptmanager.getEmployees().getEmpNo());
			dto.setDeptNo(deptmanager.getDepartments().getDeptNo());
			titlesdtolist.add(dto);
		}
		return titlesdtolist;
	}

	// Method to retrieves department manager by empNo and deptNo
	@Override
	public DepartmentManagerDto getDepartmentManagerByEmpNoAndDeptNo(Integer empno, String deptno) {
		DepartmentManager deptmanager = departmentManagerRepository.findByEmpNoAndDeptNo(empno, deptno);
		DepartmentManagerDto deptmanagerDto = new DepartmentManagerDto();
		BeanUtils.copyProperties(deptmanager, deptmanagerDto);
		deptmanagerDto.setEmpNo(deptmanager.getEmployees().getEmpNo());
		deptmanagerDto.setDeptNo(deptmanager.getDepartments().getDeptNo());
		return deptmanagerDto;
	}

	// Method to retrieves department manager by deptNo and fromDate
	@Override
	public List<DepartmentManagerDto> getDepartmentManagerByDeptNoAndFromDate(String deptno, LocalDate fromdate) {
		List<DepartmentManager> deptmanagerList = departmentManagerRepository.findByDeptNoAndFromDate(deptno, fromdate);
		List<DepartmentManagerDto> deptmanagerDtoList = new ArrayList<>();
		for (DepartmentManager deptmanager : deptmanagerList) {
			DepartmentManagerDto deptmanagerDto = new DepartmentManagerDto();
			BeanUtils.copyProperties(deptmanager, deptmanagerDto);
			deptmanagerDto.setEmpNo(deptmanager.getEmployees().getEmpNo());
			deptmanagerDto.setDeptNo(deptmanager.getDepartments().getDeptNo());
			deptmanagerDtoList.add(deptmanagerDto);
		}

		return deptmanagerDtoList;
	}

	// Method to retrieves department manager by empNo and fromDate
	@Override
	public List<DepartmentManagerDto> getDepartmentManagerByEmpNoAndFromDate(Integer empno, LocalDate fromdate) {
		List<DepartmentManager> deptmanagerList = departmentManagerRepository.findByEmpNoAndFromDate(empno, fromdate);
		List<DepartmentManagerDto> deptmanagerDtoList = new ArrayList<>();
		for (DepartmentManager deptmanager : deptmanagerList) {
			DepartmentManagerDto deptmanagerDto = new DepartmentManagerDto();
			BeanUtils.copyProperties(deptmanager, deptmanagerDto);
			deptmanagerDto.setEmpNo(deptmanager.getEmployees().getEmpNo());
			deptmanagerDto.setDeptNo(deptmanager.getDepartments().getDeptNo());
			deptmanagerDtoList.add(deptmanagerDto);
		}

		return deptmanagerDtoList;
	}

	// Method to retrieves department manager by empNo and deptNo and fromDate
	@Override
	public List<DepartmentManagerDto> getDepartmentManagerByEmpNoAndDeptNoAndFromDate(Integer empno, String deptno,
			LocalDate fromdate) {
		List<DepartmentManager> deptmanagerList = departmentManagerRepository.findByEmpNoAndDeptNoAndFromDate(empno,
				deptno, fromdate);
		List<DepartmentManagerDto> deptmanagerDtoList = new ArrayList<>();
		for (DepartmentManager deptmanager : deptmanagerList) {
			DepartmentManagerDto deptmanagerDto = new DepartmentManagerDto();
			BeanUtils.copyProperties(deptmanager, deptmanagerDto);
			deptmanagerDto.setEmpNo(deptmanager.getEmployees().getEmpNo());
			deptmanagerDto.setDeptNo(deptmanager.getDepartments().getDeptNo());
			deptmanagerDtoList.add(deptmanagerDto);
		}

		return deptmanagerDtoList;
	}

	// Method to update department manager by empNo and deptNo
	@Override
	public String updateDepartmentManagerByEmpNoAndDeptNo(DepartmentManagerDto dto, Integer empno, String deptno) {
		try {
			DepartmentManager deptmanager = departmentManagerRepository.findByEmpNoAndDeptNo(empno, deptno);
			deptmanager.setFromDate(dto.getFromDate());
			deptmanager.setToDate(dto.getToDate());
			departmentManagerRepository.save(deptmanager);
			return "employee assigned  to department  updated Successfully";

		} catch (Exception e) {
			throw new UniversalExceptionHandler("Validation failed");
		}
	}

	// Method to update department manager by empNo and deptNo and fromDate
	@Override
	public String updateDepartmentManagerByEmpNoAndDeptNoAndFromDate(DepartmentManagerDto dto, Integer empno,
			String deptno, LocalDate fromdate) {
		List<DepartmentManager> deptmanagerlist = departmentManagerRepository.findByEmpNoAndDeptNoAndFromDate(empno,
				deptno, fromdate);
		List<DepartmentManagerDto> deptmanagerDtoList = new ArrayList<>();
		for (DepartmentManager deptmanager : deptmanagerlist) {
			DepartmentManagerDto deptempdto = new DepartmentManagerDto();
			deptmanager.setFromDate(dto.getFromDate());
			deptmanager.setToDate(dto.getToDate());
			departmentManagerRepository.save(deptmanager);
			BeanUtils.copyProperties(deptmanager, deptempdto);
			deptempdto.setEmpNo(deptmanager.getEmployees().getEmpNo());
			deptempdto.setDeptNo(deptmanager.getDepartments().getDeptNo());
			deptmanagerDtoList.add(deptempdto);

		}
		return "deptmanager updated Successfully";
	}

	// Method to update department manager by deptNo and fromDate
	@Override
	public List<DepartmentManagerDto> updateDepartmentManagerByDeptNoAndFromDate(DepartmentManagerDto dto,
			String deptno, LocalDate fromdate) {
		List<DepartmentManager> deptmanagerList = departmentManagerRepository.findByDeptNoAndFromDate(deptno, fromdate);
		List<DepartmentManagerDto> deptmanagerDtoList = new ArrayList<>();
		for (DepartmentManager deptmanager : deptmanagerList) {
			DepartmentManagerDto deptmanagerDto = new DepartmentManagerDto();
			deptmanager.setFromDate(dto.getFromDate());
			deptmanager.setToDate(dto.getToDate());
			departmentManagerRepository.save(deptmanager);
			BeanUtils.copyProperties(deptmanager, deptmanagerDto);
			deptmanagerDto.setEmpNo(deptmanager.getEmployees().getEmpNo());
			deptmanagerDto.setDeptNo(deptmanager.getDepartments().getDeptNo());
			deptmanagerDtoList.add(deptmanagerDto);

		}
		return deptmanagerDtoList;
	}

	// Method to update department manager by empNo and fromDate
	@Override
	public String updateDepartmentManagerByEmpNoAndFromDate(DepartmentManagerDto dto, Integer empno,
			LocalDate fromdate) {
		List<DepartmentManager> deptmanagerlist = departmentManagerRepository.findByEmpNoAndFromDate(empno, fromdate);
		if (!deptmanagerlist.isEmpty()) {
			List<DepartmentManagerDto> deptmanagerDtoList = new ArrayList<>();
			for (DepartmentManager deptmanager : deptmanagerlist) {
				DepartmentManagerDto deptempdto = new DepartmentManagerDto();
				deptmanager.setFromDate(dto.getFromDate());
				deptmanager.setToDate(dto.getToDate());
				departmentManagerRepository.save(deptmanager);
				BeanUtils.copyProperties(deptmanager, deptempdto);
				deptempdto.setEmpNo(deptmanager.getEmployees().getEmpNo());
				deptempdto.setDeptNo(deptmanager.getDepartments().getDeptNo());
				deptmanagerDtoList.add(deptempdto);

			}
			return "employee with empno and fromdate  updated Successfully";
		}
		throw new UniversalExceptionHandler("Validation failed");
	}
}
