package com.hrmanagementsystem.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hrmanagementsystem.HrManagementSystemApplication;
import com.hrmanagementsystem.dto.EmployeesDto;
import com.hrmanagementsystem.entity.Employees;
import com.hrmanagementsystem.entity.Gender;
import com.hrmanagementsystem.exception.UniversalExceptionHandler;
import com.hrmanagementsystem.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HrManagementSystemApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "Raashid", roles = "ADMIN")
public class EmployeesControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetEmployeeById() throws Exception {
		EmployeesDto employee = new EmployeesDto();
		employee.setEmpNo(1);
		employee.setFirstName("John");
		employee.setLastName("Doe");

		when(employeeService.getEmployeeByNo(1)).thenReturn(employee);

		mockMvc.perform(get("/employees/{id}", 1)).andExpect(status().isOk()).andExpect(jsonPath("$.empNo").value(1))
				.andExpect(jsonPath("$.firstName").value("John")).andExpect(jsonPath("$.lastName").value("Doe"));
	}

	@Test
	public void testGetEmployeeByFirstName() throws Exception {
		EmployeesDto employee = new EmployeesDto();
		employee.setEmpNo(1);
		employee.setFirstName("John");
		employee.setLastName("Doe");
		List<EmployeesDto> employeelist = new ArrayList<>();
		employeelist.add(employee);

		when(employeeService.getEmployeeByFirstName("John")).thenReturn(employeelist);

		mockMvc.perform(get("/employees/firstname/{fn}", "John")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].empNo").value(1)).andExpect(jsonPath("$[0].firstName").value("John"))
				.andExpect(jsonPath("$[0].lastName").value("Doe"));
	}

	@Test
	public void testGetEmployeeByLastName() throws Exception {
		EmployeesDto employee = new EmployeesDto();
		employee.setEmpNo(1);
		employee.setFirstName("John");
		employee.setLastName("Doe");
		List<EmployeesDto> employeelist = new ArrayList<>();
		employeelist.add(employee);

		when(employeeService.getEmployeeByLastName("Doe")).thenReturn(employeelist);

		mockMvc.perform(get("/employees/lastname/{fn}", "Doe")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].empNo").value(1)).andExpect(jsonPath("$[0].firstName").value("John"))
				.andExpect(jsonPath("$[0].lastName").value("Doe"));
	}

	@Test
	public void testGetEmployeeByHireDate() throws Exception {
		EmployeesDto employee = new EmployeesDto();
		employee.setEmpNo(1);
		employee.setFirstName("John");
		employee.setLastName("Doe");
		employee.setHireDate(LocalDate.of(1999, 01, 01));
		List<EmployeesDto> employeelist = new ArrayList<>();
		employeelist.add(employee);

		when(employeeService.getEmployeeByHireDate(LocalDate.of(1999, 01, 01))).thenReturn(employeelist);

		mockMvc.perform(get("/employees/hiredate/{hiredate}", "1999-01-01")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].empNo").value(1)).andExpect(jsonPath("$[0].firstName").value("John"))
				.andExpect(jsonPath("$[0].lastName").value("Doe"))
				.andExpect(jsonPath("$[0].hireDate").value("1999-01-01"));
	}

	@Test
	public void testGetEmployeeByBirthDate() throws Exception {
		EmployeesDto employee = new EmployeesDto();
		employee.setEmpNo(1);
		employee.setFirstName("John");
		employee.setLastName("Doe");
		employee.setBirthDate(LocalDate.of(1999, 01, 01));
		List<EmployeesDto> employeelist = new ArrayList<>();
		employeelist.add(employee);

		when(employeeService.getEmployeeByBirthDate(LocalDate.of(1999, 01, 01))).thenReturn(employeelist);

		mockMvc.perform(get("/employees/birthdate/{birthdate}", "1999-01-01")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].empNo").value(1)).andExpect(jsonPath("$[0].firstName").value("John"))
				.andExpect(jsonPath("$[0].lastName").value("Doe"))
				.andExpect(jsonPath("$[0].birthDate").value("1999-01-01"));
	}

	@Test
	public void testGetEmployeeByGender() throws Exception {
		EmployeesDto employee = new EmployeesDto();
		employee.setEmpNo(1);
		employee.setFirstName("John");
		employee.setLastName("Doe");
		employee.setGender(Gender.M);
		List<EmployeesDto> employeelist = new ArrayList<>();
		employeelist.add(employee);
		Pageable pageable = PageRequest.of(0, 20);

		when(employeeService.getEmployeeByGender(Gender.M, pageable)).thenReturn(employeelist);

		mockMvc.perform(get("/employees/gender/{gender}", Gender.M)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].empNo").value(1)).andExpect(jsonPath("$[0].firstName").value("John"))
				.andExpect(jsonPath("$[0].lastName").value("Doe")).andExpect(jsonPath("$[0].gender").value("M"));
	}

	@Test
	public void testUpdateEmployeeHireDate() throws Exception {
		Integer empNo = 1;
		LocalDate newHireDate = LocalDate.of(2023, 7, 1);

		EmployeesDto existingEmployee = new EmployeesDto();
		existingEmployee.setEmpNo(empNo);
		existingEmployee.setFirstName("John");
		existingEmployee.setLastName("Doe");
		existingEmployee.setHireDate(LocalDate.of(2021, 1, 1));

		EmployeesDto updatedEmployee = new EmployeesDto();
		updatedEmployee.setEmpNo(empNo);
		updatedEmployee.setFirstName("John");
		updatedEmployee.setLastName("Doe");
		updatedEmployee.setHireDate(newHireDate);

		when(employeeService.updateEmployeeHireDate(any(), eq(empNo))).thenReturn(updatedEmployee);

		mockMvc.perform(put("/employees/hiredate/{empno}", empNo).contentType(MediaType.APPLICATION_JSON)
				.content("{\"hireDate\":\"2023-07-01\"}")).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("John")).andExpect(jsonPath("$.lastName").value("Doe"))
				.andExpect(jsonPath("$.hireDate").value("2023-07-01"));

		verify(employeeService, times(1)).updateEmployeeHireDate(any(), eq(empNo));
	}

	@Test
	public void testUpdateEmployeeBirthDate() throws Exception {
		Integer empNo = 1;
		LocalDate newBirthDate = LocalDate.of(2023, 7, 1);

		EmployeesDto existingEmployee = new EmployeesDto();
		existingEmployee.setEmpNo(empNo);
		existingEmployee.setFirstName("John");
		existingEmployee.setLastName("Doe");
		existingEmployee.setBirthDate(LocalDate.of(2021, 1, 1));

		EmployeesDto updatedEmployee = new EmployeesDto();
		updatedEmployee.setEmpNo(empNo);
		updatedEmployee.setFirstName("John");
		updatedEmployee.setLastName("Doe");
		updatedEmployee.setBirthDate(newBirthDate);

		when(employeeService.updateEmployeeBirthDate(any(), eq(empNo))).thenReturn(updatedEmployee);

		mockMvc.perform(put("/employees/birthdate/{empno}", empNo).contentType(MediaType.APPLICATION_JSON)
				.content("{\"birthDate\":\"2023-07-01\"}")).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("John")).andExpect(jsonPath("$.lastName").value("Doe"))
				.andExpect(jsonPath("$.birthDate").value("2023-07-01"));

		verify(employeeService, times(1)).updateEmployeeBirthDate(any(), eq(empNo));
	}

	@Test
	public void testSaveEmployee() throws Exception {
		EmployeesDto employeesDto = new EmployeesDto();
		employeesDto.setEmpNo(1001);
		employeesDto.setFirstName("John");
		employeesDto.setLastName("Doe");
		employeesDto.setBirthDate(LocalDate.of(1999, 01, 01));
		employeesDto.setHireDate(LocalDate.of(2012, 01, 01));

		// Mock the behavior of employeeService.addEmployee() method
		when(employeeService.addEmployee(employeesDto)).thenReturn("New Employee Added Successfully");

		String jsonRequest = asJsonString(employeesDto);

		MvcResult result = mockMvc
				.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
				.andExpect(status().isOk()).andReturn();

		String responseContent = result.getResponse().getContentAsString();
		assertEquals("New Employee Added Successfully", responseContent);
	}

	private String asJsonString(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void testSaveEmployee_InvalidData() throws Exception {
		// Prepare invalid test data
		EmployeesDto empDto = new EmployeesDto();
		// Set invalid values in the empDto

		// Perform the POST request and validate the response
		mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(empDto))).andExpect(status().isBadRequest());

		// Verify that the employeeService method was not called
		verify(employeeService, never()).addEmployee(empDto);
	}

	@Test

    public void testGetAllEmployees_NoEmployeesFound() throws Exception {
        // Stub the employeeService.getAllEmployees method to return an empty page
        Pageable pageable = PageRequest.of(0, 20);
        Page<Employees> emptyPage = new PageImpl<>(Collections.emptyList());
        when(employeeService.getAllEmployees(pageable)).thenReturn(emptyPage);

        // Perform the GET request and check for status code
        mockMvc.perform(get("/employees/getallemployees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(0));
        // Verify that the employeeService.getAllEmployees method was called
        verify(employeeService, times(1)).getAllEmployees(pageable);

    }

	@Test
	public void testGetEmployeeById_NoDataFound() throws Exception {
		// Test data
		Integer empNo = 123;

		// Stub the service method to return null
		when(employeeService.getEmployeeByNo(empNo)).thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the GET request and check for status code
		mockMvc.perform(get("/employees/{empno}", empNo)).andExpect(status().isNotFound());

		// Verify the service method was called with the correct parameters
		verify(employeeService, times(1)).getEmployeeByNo(empNo);
	}

	@Test
	public void testGetEmployeeByFirstName_NoDataFound() throws Exception {
		// Test data
		String firstName = "John";

		// Stub the service method to return an empty list
		when(employeeService.getEmployeeByFirstName(firstName)).thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the GET request and check for status code
		mockMvc.perform(get("/employees/firstname/{fn}", firstName)).andExpect(status().isNotFound());

		// Verify the service method was called with the correct parameters
		verify(employeeService, times(1)).getEmployeeByFirstName(firstName);
	}

	@Test
	public void testGetEmployeeByLastName_NoDataFound() throws Exception {
		// Test data
		String lastName = "Doe";

		// Stub the service method to return an empty list
		when(employeeService.getEmployeeByLastName(lastName)).thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the GET request and check for status code
		mockMvc.perform(get("/employees/lastname/{ln}", lastName)).andExpect(status().isNotFound());

		// Verify the service method was called with the correct parameters
		verify(employeeService, times(1)).getEmployeeByLastName(lastName);
	}

	@Test
	public void testUpdateEmployeeLastName_ExceptionThrown() throws Exception {
		// Prepare test data
		EmployeesDto empDto = new EmployeesDto();
		// Set the necessary values in the empDto

		Integer empNo = 123;

		// Stub the service method to throw an exception
		when(employeeService.updateEmployeeLastName(empDto, empNo))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the PUT request and validate the response
		mockMvc.perform(put("/employees/lastname/{empno}", empNo).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(empDto))).andExpect(status().isNotFound());

		// Verify that the employeeService method was called with the correct parameters
		verify(employeeService, times(1)).updateEmployeeLastName(empDto, empNo);
	}

	@Test
	public void testUpdateEmployeeFirstName_ExceptionThrown() throws Exception {
		// Prepare test data
		EmployeesDto empDto = new EmployeesDto();
		// Set the necessary values in the empDto

		Integer empNo = 123;

		// Stub the service method to throw an exception
		when(employeeService.updateEmployeeFirstName(empDto, empNo))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the PUT request and validate the response
		mockMvc.perform(put("/employees/firstname/{empno}", empNo).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(empDto))).andExpect(status().isNotFound());

		// Verify that the employeeService method was called with the correct parameters
		verify(employeeService, times(1)).updateEmployeeFirstName(empDto, empNo);
	}

	@Test
	public void testGetEmployeeByHireDate_NoDataFound() throws Exception {
		// Test data
		LocalDate hireDate = LocalDate.of(2023, 1, 1);

		// Stub the service method to return an empty list
		when(employeeService.getEmployeeByHireDate(hireDate)).thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the GET request and check for status code
		mockMvc.perform(get("/employees/hiredate/{hiredate}", hireDate)).andExpect(status().isNotFound());

		// Verify the service method was called with the correct parameters
		verify(employeeService, times(1)).getEmployeeByHireDate(hireDate);
	}

	@Test
	public void testGetEmployeeByBirthDate_NoDataFound() throws Exception {
		// Test data
		LocalDate birthDate = LocalDate.of(2000, 1, 1);

		// Stub the service method to return an empty list
		when(employeeService.getEmployeeByBirthDate(birthDate)).thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the GET request and check for status code
		mockMvc.perform(get("/employees/birthdate/{birthdate}", birthDate)).andExpect(status().isNotFound());

		// Verify the service method was called with the correct parameters
		verify(employeeService, times(1)).getEmployeeByBirthDate(birthDate);
	}

	@Test
	public void testUpdateEmployeeHireDate_ExceptionThrown() throws Exception {
		// Prepare test data
		EmployeesDto empDto = new EmployeesDto();
		// Set the necessary values in the empDto

		Integer empNo = 123;

		// Stub the service method to throw an exception
		when(employeeService.updateEmployeeHireDate(empDto, empNo))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the PUT request and validate the response
		mockMvc.perform(put("/employees/hiredate/{empno}", empNo).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(empDto))).andExpect(status().isNotFound());

		// Verify that the employeeService method was called with the correct parameters
		verify(employeeService, times(1)).updateEmployeeHireDate(empDto, empNo);
	}

	@Test
	public void testUpdateEmployeeBirthDate_ExceptionThrown() throws Exception {
		// Prepare test data
		EmployeesDto empDto = new EmployeesDto();
		// Set the necessary values in the empDto

		Integer empNo = 123;

		// Stub the service method to throw an exception
		when(employeeService.updateEmployeeBirthDate(empDto, empNo))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the PUT request and validate the response
		mockMvc.perform(put("/employees/birthdate/{empno}", empNo).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(empDto))).andExpect(status().isNotFound());

		// Verify that the employeeService method was called with the correct parameters
		verify(employeeService, times(1)).updateEmployeeBirthDate(empDto, empNo);
	}
}
