package com.hrmanagementsystem.controllertest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrmanagementsystem.HrManagementSystemApplication;
import com.hrmanagementsystem.dto.DepartmentReportDto;
import com.hrmanagementsystem.dto.DepartmentReportDtoImpl;
import com.hrmanagementsystem.dto.DesignationReportDto;
import com.hrmanagementsystem.dto.DesignationReportDtoImpl;
import com.hrmanagementsystem.dto.EmployeeDTO;
import com.hrmanagementsystem.dto.EmployeeDesignationProjectionDto;
import com.hrmanagementsystem.dto.EmployeeManagerFromDateDto;
import com.hrmanagementsystem.dto.EmployeesDto;
import com.hrmanagementsystem.dto.ManagerDTO;
import com.hrmanagementsystem.dto.ResponseDto;
import com.hrmanagementsystem.entity.Employees;
import com.hrmanagementsystem.exception.UniversalExceptionHandler;
import com.hrmanagementsystem.service.EmployeeHrmsConsumerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HrManagementSystemApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "Raashid", roles = "ADMIN")
public class EmployeeHrmsConsumerControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeHrmsConsumerService employeehrmsconsumer;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetManagers() throws Exception {
		EmployeeManagerFromDateDto deptdto = new EmployeeManagerFromDateDto();
		deptdto.setEmpNo(1001);
		deptdto.setFirstName("John");
		deptdto.setLastName("Doe");
		deptdto.setHireDate(LocalDate.of(2000, 01, 01));

		List<EmployeeManagerFromDateDto> employeelist = new ArrayList<>();
		employeelist.add(deptdto);

		when(employeehrmsconsumer.getManagers(LocalDate.of(2000, 01, 01))).thenReturn(employeelist);

		mockMvc.perform(get("/employeehrmsconsumer/manager/{fromdate}", "2000-01-01")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].empNo").value(1001)).andExpect(jsonPath("$[0].firstName").value("John"))
				.andExpect(jsonPath("$[0].lastName").value("Doe"))
				.andExpect(jsonPath("$[0].hireDate").value("2000-01-01"));
	}

	@Test
	public void testGetDesignations() throws Exception {
		// Mock the service method to return a list of designations
		List<String> designations = Arrays.asList("Software Engineer", "Engineer", "Consultant");
		when(employeehrmsconsumer.getDesignations()).thenReturn(designations);

		// Perform the GET request and validate the response
		mockMvc.perform(get("/employeehrmsconsumer/designations").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0]").value(designations.get(0)))
				.andExpect(jsonPath("$[1]").value(designations.get(1)))
				.andExpect(jsonPath("$[2]").value(designations.get(2)));
	}

	@Test
	public void testUpdateEmployeeLastName() throws Exception {
		Integer empno = 123;
		// Create a sample EmployeesDto for the test
		EmployeesDto empdto = new EmployeesDto();
		// Set the properties of the EmployeesDto as needed for your test scenario

		// Create a sample ResponseDto for the mock response
		ResponseDto response = new ResponseDto();
		// Set the properties of the ResponseDto as needed for your test scenario

		// Mock the behavior of the employeeHRMSConsumer
		when(employeehrmsconsumer.updateEmployeeLastName(empdto, empno)).thenReturn(response);

		// Perform the PUT request and validate the response using MockMvc
		mockMvc.perform(put("/employeehrmsconsumer/employee/{empno}", 123).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(empdto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.response").value(response.getResponse()));

		// Add more assertions as needed for your test scenario

		// Verify that the employeeHRMSConsumer method was called with the correct
		// empdto and empno
		verify(employeehrmsconsumer, times(1)).updateEmployeeLastName(empdto, 123);
	}

	@Test
	public void testGetEmployeeDesignation() throws Exception {
		// Mock the service method to return a list of EmployeeDesignationProjectionDto
		String title = "Clerk";
		List<EmployeeDesignationProjectionDto> empList = new ArrayList<>();
		EmployeeDesignationProjectionDto empdto = new EmployeeDesignationProjectionDto();
		EmployeeDesignationProjectionDto empdto1 = new EmployeeDesignationProjectionDto();
		empdto.setDesignation("Clerk");
		empdto.setEmpNo(1001);
		empdto1.setDesignation("Clerk"); // Note the correction here: empdto1 should have its own values
		empdto1.setEmpNo(1002);
		empList.add(empdto);
		empList.add(empdto1);
		Pageable pageable = PageRequest.of(0, 20);
		when(employeehrmsconsumer.getEmployeeDesignation("Clerk", pageable)).thenReturn(empList);

		// Perform the GET request and validate the response
		mockMvc.perform(get("/employeehrmsconsumer/employees/titles/{title}", title)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].empNo").value(1001)).andExpect(jsonPath("$[0].designation").value("Clerk"))
				.andExpect(jsonPath("$[1].empNo").value(1002)).andExpect(jsonPath("$[1].designation").value("Clerk"));
	}

	@Test

	public void testGetMidAgeEmployees() throws Exception {

		// Mock the service method to return a list of Employees
		List<Employees> empList = new ArrayList<>();
		Employees emp1 = new Employees();
		emp1.setFirstName("John");
		emp1.setBirthDate(LocalDate.of(1990, 1, 1));
		empList.add(emp1);
		// ... Add more Employees to the list
		// Set up the desired page and size
		int page = 0;
		int size = 20;
		Pageable pageable = PageRequest.of(page, size);
		// Mock the service method call
		when(employeehrmsconsumer.getMidAgeEmployees(pageable)).thenReturn(empList);
		// Perform the GET request and validate the response
		mockMvc.perform(get("/employeehrmsconsumer/midageemp").param("page", String.valueOf(page))
				.param("size", String.valueOf(size)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(empList.size()))
				.andExpect(jsonPath("$[0].firstName").value("John"))
				.andExpect(jsonPath("$[0].birthDate").value("1990-01-01"));
		// ... Add more assertions for other Employees in the list

	}

	@Test
	public void testGetEmployeeWorkingSpecificDept() throws Exception {
		// Mock the service method to return a list of EmployeeDTO
		String deptNo = "d001";

		List<EmployeeDTO> empList = new ArrayList<>();
		EmployeeDTO emp1 = new EmployeeDTO();
		emp1.setEmpNo(1001);
		emp1.setFirstName("John");
		emp1.setDeptName("Technical");
		emp1.setFromDate(LocalDate.of(2020, 1, 15));
		EmployeeDTO emp2 = new EmployeeDTO();
		emp2.setEmpNo(1002);
		emp2.setFirstName("Doe");
		emp2.setDeptName("Clerk");
		emp2.setFromDate(LocalDate.of(2019, 5, 20));

		empList.add(emp1);
		empList.add(emp2);
		Pageable pageable = PageRequest.of(0, 20);
		Integer year = 1989;

		when(employeehrmsconsumer.getEmployeeWorkingSpecificDept(deptNo, year, pageable)).thenReturn(empList);

		// Perform the GET request and validate the response
		mockMvc.perform(get("/employeehrmsconsumer/employees/department/{deptno}/year/{year}", deptNo, year))
				.andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(2))
				.andExpect(jsonPath("$[0].empNo").value(1001)).andExpect(jsonPath("$[0].firstName").value("John"))
				.andExpect(jsonPath("$[0].deptName").value("Technical"))
				.andExpect(jsonPath("$[0].fromDate").value("2020-01-15")).andExpect(jsonPath("$[1].empNo").value(1002))
				.andExpect(jsonPath("$[1].firstName").value("Doe")).andExpect(jsonPath("$[1].deptName").value("Clerk"))
				.andExpect(jsonPath("$[1].fromDate").value("2019-05-20"));
	}

	@Test
	public void testGetEmployeeManager() throws Exception {
		// Mock the service method to return a list of ManagerDTO
		List<ManagerDTO> managerList = new ArrayList<>();
		ManagerDTO emp1 = new ManagerDTO();
		emp1.setFirstName("John");
		emp1.setEmpNo(1001);
		emp1.setDeptName("LND");
		ManagerDTO emp2 = new ManagerDTO();
		emp2.setFirstName("Doe");
		emp2.setEmpNo(1002);
		emp2.setDeptName("Finance");

		managerList.add(emp1);
		managerList.add(emp2);

		when(employeehrmsconsumer.getEmployeeManager()).thenReturn(managerList);

		// Perform the GET request and validate the response
		mockMvc.perform(get("/employeehrmsconsumer/manager")).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2)) // Assuming the service returns 2 managers in the list
				.andExpect(jsonPath("$[0].empNo").value(1001)).andExpect(jsonPath("$[0].firstName").value("John"))
				.andExpect(jsonPath("$[0].deptName").value("LND")).andExpect(jsonPath("$[1].empNo").value(1002))
				.andExpect(jsonPath("$[1].firstName").value("Doe"))
				.andExpect(jsonPath("$[1].deptName").value("Finance"));
	}

	// For this we have used seprate the class DesignationReportDtoImpl which
	// implements DesignationReportDto
	@Test
	public void testGetDesignationDetails() throws Exception {
		// Mock the service method to return a list of DesignationReportDto
		List<DesignationReportDto> designationList = new ArrayList<>();
		// Add some mock data
		designationList.add(new DesignationReportDtoImpl("Manager", 60000.0, 80000, 50000));
		designationList.add(new DesignationReportDtoImpl("Engineer", 55000.0, 75000, 45000));

		when(employeehrmsconsumer.getDesignationDetails()).thenReturn(designationList);

		// Perform the GET request and validate the response
		mockMvc.perform(get("/employeehrmsconsumer/designation/details")).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2)) // Assuming the service returns 2 designations in the list
				.andExpect(jsonPath("$[0].designation").value("Manager"))
				.andExpect(jsonPath("$[0].averageSalary").value(60000.0))
				.andExpect(jsonPath("$[0].maxSalary").value(80000)).andExpect(jsonPath("$[0].minSalary").value(50000))
				.andExpect(jsonPath("$[1].designation").value("Engineer"))
				.andExpect(jsonPath("$[1].averageSalary").value(55000.0))
				.andExpect(jsonPath("$[1].maxSalary").value(75000)).andExpect(jsonPath("$[1].minSalary").value(45000));
	}

	// For this we have used seprate the class DepartmentReportDtoImpl which
	// implements DepartmentReportDto
	@Test
	public void testGetDepartmentDetails() throws Exception {
		// Mock the service method to return a list of DepartmentReportDto
		List<DepartmentReportDto> departmentList = new ArrayList<>();
		// Add some mock data
		departmentList.add(new DepartmentReportDtoImpl("d001", 50000.0, 70000, 40000));
		departmentList.add(new DepartmentReportDtoImpl("d002", 55000.0, 75000, 45000));

		when(employeehrmsconsumer.getDepartmentDetails()).thenReturn(departmentList);

		// Perform the GET request and validate the response
		mockMvc.perform(get("/employeehrmsconsumer/department")).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2)) // Assuming the service returns 2 departments in the list
				.andExpect(jsonPath("$[0].deptNo").value("d001")).andExpect(jsonPath("$[0].avgSalary").value(50000.0))
				.andExpect(jsonPath("$[0].maxSalary").value(70000)).andExpect(jsonPath("$[0].minSalary").value(40000))
				.andExpect(jsonPath("$[1].deptNo").value("d002")).andExpect(jsonPath("$[1].avgSalary").value(55000.0))
				.andExpect(jsonPath("$[1].maxSalary").value(75000)).andExpect(jsonPath("$[1].minSalary").value(45000));
	}

	@Test
	public void testGetManagers_InvalidInput() throws Exception {
		// Test data
		LocalDate fromDate = LocalDate.now();

		// Stub the consumer method to return an empty list
		when(employeehrmsconsumer.getManagers(fromDate)).thenThrow(new UniversalExceptionHandler("Invalid data"));

		// Perform the GET request and check for status code
		mockMvc.perform(get("/employeehrmsconsumer/manager/{fromdate}", fromDate)).andExpect(status().isNotFound());

		// Verify that the consumer method was called with the correct parameters
		verify(employeehrmsconsumer, times(1)).getManagers(fromDate);
	}

	@Test
	public void testGetDesignations_InvalidInput() throws Exception {
	    // Stub the consumer method to return an empty list
	    when(employeehrmsconsumer.getDesignations())
	    .thenThrow(new UniversalExceptionHandler("Invalid data"));

	    // Perform the GET request and check for status code
	    mockMvc.perform(get("/employeehrmsconsumer/designations"))
	            .andExpect(status().isNotFound());

	    // Verify that the consumer method was called
	    verify(employeehrmsconsumer, times(1)).getDesignations();
	}

	@Test
	public void testUpdateEmployeeLastName_InvalidInput() throws Exception {
		// Test data
		Integer empNo = 123;
		EmployeesDto empDto = new EmployeesDto();
		empDto.setLastName("Doe");

		// Stub the consumer method to return null, indicating employee not found
		when(employeehrmsconsumer.updateEmployeeLastName(empDto, empNo))
				.thenThrow(new UniversalExceptionHandler("Invalid data"));

		// Perform the PUT request and check for status code
		mockMvc.perform(put("/employeehrmsconsumer/employee/{empno}", empNo).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(empDto))).andExpect(status().isNotFound());

		// Verify that the consumer method was called with the correct parameters
		verify(employeehrmsconsumer, times(1)).updateEmployeeLastName(empDto, empNo);
	}

	@Test
	public void testGetEmployeeDesignation_InvalidInput() throws Exception {
		// Test data
		String title = "Manager";
		Integer page = 0;
		Integer size = 20;

		// Stub the consumer method to return an empty list, indicating title not found
		when(employeehrmsconsumer.getEmployeeDesignation(title, PageRequest.of(page, size)))
				.thenThrow(new UniversalExceptionHandler("Invalid data"));

		// Perform the GET request and check for status code
		mockMvc.perform(get("/employeehrmsconsumer/employees/titles/{title}", title).param("page", String.valueOf(page))
				.param("size", String.valueOf(size))).andExpect(status().isNotFound());

		// Verify that the consumer method was called with the correct parameters
		verify(employeehrmsconsumer, times(1)).getEmployeeDesignation(title, PageRequest.of(page, size));
	}

	@Test
	public void testGetMidAgeEmployees_InvalidInput() throws Exception {
		// Test data
		Integer page = 0;
		Integer size = 20;

		// Stub the consumer method to return an empty list
		when(employeehrmsconsumer.getMidAgeEmployees(PageRequest.of(page, size)))
				.thenThrow(new UniversalExceptionHandler("Invalid data"));

		// Perform the GET request and check for status code
		mockMvc.perform(get("/employeehrmsconsumer/midageemp").param("page", String.valueOf(page)).param("size",
				String.valueOf(size))).andExpect(status().isNotFound());

		// Verify that the consumer method was called with the correct parameters
		verify(employeehrmsconsumer, times(1)).getMidAgeEmployees(PageRequest.of(page, size));
	}

	@Test
	public void testGetEmployeeWorkingSpecificDept_InvalidInput() throws Exception {
		// Test data
		String deptNo = "D001";
		Integer year = 2023;
		Pageable pageable = PageRequest.of(0, 10);

		// Stub the consumer method to throw an exception
		when(employeehrmsconsumer.getEmployeeWorkingSpecificDept(deptNo, year, pageable))
				.thenThrow(new UniversalExceptionHandler("Invalid data"));

		// Perform the GET request and check for status code
		mockMvc.perform(get("/employeehrmsconsumer/employees/department/{deptno}/year/{year}", deptNo, year)
				.param("page", "0").param("size", "10")).andExpect(status().isNotFound());

		// Verify that the consumer method was called with the correct parameters
		verify(employeehrmsconsumer, times(1)).getEmployeeWorkingSpecificDept(deptNo, year, pageable);
	}

	@Test
	public void testGetEmployeeManager_InvalidInput() throws Exception {
	    // Stub the consumer method to return an empty list
	    when(employeehrmsconsumer.getEmployeeManager())
	            .thenThrow(new UniversalExceptionHandler("Invalid data"));

	    // Perform the GET request and check for status code
	    mockMvc.perform(get("/employeehrmsconsumer/manager"))
	            .andExpect(status().isNotFound());

	    // Verify that the consumer method was called
	    verify(employeehrmsconsumer, times(1)).getEmployeeManager();
	}

	@Test
	public void testGetDesignationDetails_InvalidInput() throws Exception {
	    // Stub the consumer method to return an empty list
	    when(employeehrmsconsumer.getDesignationDetails())
	            .thenThrow(new UniversalExceptionHandler("Invalid data"));

	    // Perform the GET request and check for status code
	    mockMvc.perform(get("/employeehrmsconsumer/designation/details"))
	            .andExpect(status().isNotFound());

	    // Verify that the consumer method was called
	    verify(employeehrmsconsumer, times(1)).getDesignationDetails();
	}

	@Test
	public void testGetDepartmentDetails_InvalidInput() throws Exception {
	    // Stub the consumer method to return an empty list
	    when(employeehrmsconsumer.getDepartmentDetails())
	            .thenThrow(new UniversalExceptionHandler("Invalid data"));

	    // Perform the GET request and check for status code
	    mockMvc.perform(get("/employeehrmsconsumer/department"))
	            .andExpect(status().isNotFound());

	    // Verify that the consumer method was called
	    verify(employeehrmsconsumer, times(1)).getDepartmentDetails();
	}

}
