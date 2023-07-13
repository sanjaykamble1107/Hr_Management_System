package com.hrmanagementsystem.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
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
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hrmanagementsystem.HrManagementSystemApplication;
import com.hrmanagementsystem.dto.DepartmentEmployeeDto;
import com.hrmanagementsystem.dto.DepartmentManagerDto;
import com.hrmanagementsystem.dto.DepartmentsDto;
import com.hrmanagementsystem.dto.EmployeesDto;
import com.hrmanagementsystem.dto.GetEmployeeJoinedAfterHireDate;
import com.hrmanagementsystem.dto.GetEmployeeJoinedAfterHireDateImpl;
import com.hrmanagementsystem.dto.RequestDto;
import com.hrmanagementsystem.dto.ResponseDto;
import com.hrmanagementsystem.dto.TitlesDto;
import com.hrmanagementsystem.exception.UniversalExceptionHandler;
import com.hrmanagementsystem.service.AdminHrmsConsumerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HrManagementSystemApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "Raashid", roles = "ADMIN")
public class AdminHrmsConsumerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AdminHrmsConsumerService adminhrmsconsumerservice;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetEmployeeByLastNYear() throws Exception {
		Integer noOfYears = 3; // Or any value you want to test with

		// Create a list of GetEmployeeJoinedAfterHireDate to be returned by the service
		List<GetEmployeeJoinedAfterHireDate> empList = new ArrayList<>();
		// Add some data to the expected list based on the test scenario
		GetEmployeeJoinedAfterHireDateImpl emp1 = new GetEmployeeJoinedAfterHireDateImpl();
		GetEmployeeJoinedAfterHireDateImpl emp2 = new GetEmployeeJoinedAfterHireDateImpl();

		emp1.setEmployeeNumber(1001);
		emp1.setFirstName("John");
		emp2.setEmployeeNumber(1002);
		emp2.setFirstName("Doe");
		empList.add(emp1);
		empList.add(emp2);
		// ... add more data as needed

		// Mock the behavior of the adminhrmsconsumerservice
		when(adminhrmsconsumerservice.getEmployeeByLastNYear(noOfYears)).thenReturn(empList);

		// Perform the GET request and validate the response using MockMvc
		mockMvc.perform(get("/adminhrmsconsumer/employees/{noofyear}", noOfYears)).andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$.length()").value(empList.size()))
				.andExpect(jsonPath("$[0].employeeNumber").value(1001))
				.andExpect(jsonPath("$[0].firstName").value("John"))
				.andExpect((ResultMatcher) jsonPath("$[1].employeeNumber").value(1002))
				.andExpect(jsonPath("$[1].firstName").value("Doe"));
		// Add more specific assertions for the data in each
		// GetEmployeeJoinedAfterHireDate object

		// Verify that the adminhrmsconsumerservice method was called with the correct
		// noOfYears
		verify(adminhrmsconsumerservice, times(1)).getEmployeeByLastNYear(noOfYears);
	}

	@Test
	public void testGetCountEmployeeByLastTenYear() throws Exception {
		Integer noOfYears = 10;

		int expectedCount = 100;
		when(adminhrmsconsumerservice.getCountEmployeeByLastNYear(noOfYears)).thenReturn(expectedCount);

		// Perform the GET request and validate the response using MockMvc
		mockMvc.perform(get("/adminhrmsconsumer/employees/count/{noofyear}", noOfYears)).andExpect(status().isOk())
				.andExpect(jsonPath("$").value(expectedCount));

		// Verify that the adminhrmsconsumerservice method was called with the correct
		// noOfYears that is we have assigned
		verify(adminhrmsconsumerservice, times(1)).getCountEmployeeByLastNYear(noOfYears);
	}

	@Test
	public void testGetEmployeeListByNJoinYear() throws Exception {
		Integer year = 2020; // Or any value you want to test with

		// Mock the behavior of the adminhrmsconsumerservice
		List<EmployeesDto> employeeList = new ArrayList<>();
		// Add some sample EmployeesDto objects to the list (replace with your own data)
		EmployeesDto emp1 = new EmployeesDto();
		emp1.setEmpNo(1001);
		emp1.setFirstName("John");
		emp1.setHireDate(LocalDate.of(2000, 11, 10));
		EmployeesDto emp2 = new EmployeesDto();
		emp2.setEmpNo(1002);
		emp2.setFirstName("Doe");
		emp2.setHireDate(LocalDate.of(2010, 11, 10));
		employeeList.add(emp1);
		employeeList.add(emp2);
		when(adminhrmsconsumerservice.getEmployeeListByNJoinYear(year)).thenReturn(employeeList);

		// Perform the GET request and validate the response using MockMvc
		mockMvc.perform(get("/adminhrmsconsumer/employees/year/{year}", year)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].empNo").value(1001)).andExpect(jsonPath("$[0].firstName").value("John"))
				.andExpect(jsonPath("$[0].hireDate").value("2000-11-10")).andExpect(jsonPath("$[1].empNo").value(1002))
				.andExpect(jsonPath("$[1].firstName").value("Doe"))
				.andExpect(jsonPath("$[1].hireDate").value("2010-11-10"));

		// Verify that the adminhrmsconsumerservice method was called with the correct
		// year
		verify(adminhrmsconsumerservice, times(1)).getEmployeeListByNJoinYear(year);
	}

	@Test
	public void testGetEmployeeCountByNJoinYear() throws Exception {
		Integer year = 2020; // Or any value you want to test with

		// Mock the behavior of the adminhrmsconsumerservice
		Integer expectedCount = 10; // Replace with your expected count
		when(adminhrmsconsumerservice.getEmployeeCountByNJoinYear(year)).thenReturn(expectedCount);

		// Perform the GET request and validate the response using MockMvc
		mockMvc.perform(get("/adminhrmsconsumer/employees/count/year/{year}", year)).andExpect(status().isOk())
				.andExpect(jsonPath("$").value(expectedCount));

		// Verify that the adminhrmsconsumerservice method was called with the correct
		// year
		verify(adminhrmsconsumerservice, times(1)).getEmployeeCountByNJoinYear(year);
	}

	@Test
	public void testSaveDeptEmp() throws Exception {
		// Create a sample DepartmentEmployeeDto for the test
		DepartmentEmployeeDto dto = new DepartmentEmployeeDto();
		// Set the properties of the DepartmentEmployeeDto as needed for your test
		// scenario

		// Create a sample ResponseDto for the mock response
		ResponseDto response = new ResponseDto();
		// Set the properties of the ResponseDto as needed for your test scenario

		// Mock the behavior of the adminhrmsconsumerservice
		when(adminhrmsconsumerservice.saveDeptEmp(dto)).thenReturn(response);

		// Perform the POST request and validate the response using MockMvc
		mockMvc.perform(post("/adminhrmsconsumer/assigndept").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(dto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.response").value(response.getResponse()));
		// Add more assertions as needed for your test scenario

		// Verify that the adminhrmsconsumerservice method was called with the correct
		// dto
		verify(adminhrmsconsumerservice, times(1)).saveDeptEmp(dto);
	}

	@Test
	public void testSaveDeptMgr() throws Exception {
		// Create a sample DepartmentManagerDto for the test
		DepartmentManagerDto dto = new DepartmentManagerDto();
		dto.setDeptNo("d001");
		dto.setEmpNo(1001);
		// Set the properties of the DepartmentManagerDto as needed for your test
		// scenario

		// Create a sample ResponseDto for the mock response
		ResponseDto response = new ResponseDto();
		// Set the properties of the ResponseDto as needed for your test scenario

		// Mock the behavior of the adminhrmsconsumerservice
		when(adminhrmsconsumerservice.saveDeptMgr(dto)).thenReturn(response);

		// Perform the POST request and validate the response using MockMvc
		mockMvc.perform(post("/adminhrmsconsumer/assignmgr").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(dto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.response").value(response.getResponse()));
		// Add more assertions as needed for your test scenario

		// Verify that the adminhrmsconsumerservice method was called with the correct
		// dto
		verify(adminhrmsconsumerservice, times(1)).saveDeptMgr(dto);
	}

	@Test
	public void testAssignTitle() throws Exception {
		// Create a TitlesDto object for the request body
		TitlesDto titlesDto = new TitlesDto();
		titlesDto.setEmpNo(10001);
		titlesDto.setFromDate(LocalDate.of(2000, 02, 02));
		titlesDto.setTitle("Engineer");

		// Create a ResponseDto object for the expected response
		ResponseDto expectedResponse = new ResponseDto();
		expectedResponse.setResponse("Title assigned successfully");

		// Mock the service method and define the expected response
		when(adminhrmsconsumerservice.assignTitle(titlesDto)).thenReturn(expectedResponse);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		// Perform the POST request and receive the response
		MvcResult result = mockMvc.perform(post("/adminhrmsconsumer/assigntitle")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(titlesDto)))
				.andExpect(status().isOk()).andReturn();

		// Get the response body
		String responseBody = result.getResponse().getContentAsString();

		// Convert the response body to a ResponseDto object
		ResponseDto actualResponse = objectMapper.readValue(responseBody, ResponseDto.class);

		// Assert the actual response with the expected response
		assertEquals(expectedResponse.getResponse(), actualResponse.getResponse());
	}

	@Test
	public void testAddDepartment() throws Exception {
		// Create a sample DepartmentsDto for the test
		DepartmentsDto dto = new DepartmentsDto();
		// Set the properties of the DepartmentsDto as needed for your test scenario
		dto.setDeptNo("d001");
		// Create a sample ResponseDto for the mock response
		ResponseDto response = new ResponseDto();
		// Set the properties of the ResponseDto as needed for your test scenario

		// Mock the behavior of the adminhrmsconsumerservice
		when(adminhrmsconsumerservice.addDepartment(dto)).thenReturn(response);

		// Perform the POST request and validate the response using MockMvc
		mockMvc.perform(post("/adminhrmsconsumer/department").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(dto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.response").value(response.getResponse()));

		// Add more assertions as needed for your test scenario

		// Verify that the adminhrmsconsumerservice method was called with the correct
		// dto
		verify(adminhrmsconsumerservice, times(1)).addDepartment(dto);
	}

	@Test
	public void testGetExperiencedEmployeeByYear() throws Exception {
		// Define the number of years for the test
		Integer noofyear = 5;

		// Create a list of EmployeesDto for the test
		List<EmployeesDto> empList = new ArrayList<>();
		EmployeesDto emp1 = new EmployeesDto();
		emp1.setEmpNo(1001);
		emp1.setFirstName("John");
		emp1.setLastName("Doe");
		// Set other properties of the employee as needed for the test
		empList.add(emp1);
		// Add more employees to the empList if needed for your test

		when(adminhrmsconsumerservice.getExperiencedEmployeeByYear(noofyear)).thenReturn(empList);

		// Perform the GET request and validate the response using MockMvc
		mockMvc.perform(get("/adminhrmsconsumer/employee/experience/{noofyear}", noofyear)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].empNo").value(1001)).andExpect(jsonPath("$[0].firstName").value("John"))
				.andExpect(jsonPath("$[0].lastName").value("Doe"));
		// Add more assertions as needed for your test scenario

		// Verify that the adminhrmsconsumerservice method was called with the correct
		// noofyear
		verify(adminhrmsconsumerservice, times(1)).getExperiencedEmployeeByYear(noofyear);
	}

	@Test
	public void testGetEmployeeCountByGender() throws Exception {
		// Define the gender for the test
		String gender = "M";

		// Define the expected count for the test
		Integer expectedCount = 10;

		when(adminhrmsconsumerservice.getEmployeeCountByGender(gender)).thenReturn(expectedCount);

		// Perform the GET request and validate the response using MockMvc
		mockMvc.perform(get("/adminhrmsconsumer/employee/gender/{gender}/count", gender)).andExpect(status().isOk())
				.andExpect(jsonPath("$").value(expectedCount));
		// Add more assertions as needed for your test scenario

		// Verify that the adminhrmsconsumerservice method was called with the correct
		// gender
		verify(adminhrmsconsumerservice, times(1)).getEmployeeCountByGender(gender);
	}

	@Test
	public void testUpdateSalaryByRating() throws Exception {
		Integer empno = 1001;
		// Create a sample RequestDto for the test
		RequestDto dto = new RequestDto();
		// Set the properties of the RequestDto as needed for your test scenario

		ResponseDto response = new ResponseDto();
		// Set the properties of the ResponseDto as needed for your test scenario

		// Mock the behavior of the adminhrmsconsumerservice
		when(adminhrmsconsumerservice.updateSalaryByRating(dto, empno)).thenReturn(response);

		// Perform the PUT request and validate the response using MockMvc
		mockMvc.perform(put("/adminhrmsconsumer/salaries/{empno}", empno).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(dto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.response").value(response.getResponse()));

		// Add more assertions as needed for your test scenario

		// Verify that the adminhrmsconsumerservice method was called with the correct
		// dto and empno
		verify(adminhrmsconsumerservice, times(1)).updateSalaryByRating(dto, empno);
	}

	@Test
	public void testAddEmployee() throws Exception {
		// Create a sample EmployeesDto for the test
		EmployeesDto dto = new EmployeesDto();
		dto.setBirthDate(LocalDate.of(1990, 01, 01));
		dto.setHireDate(LocalDate.of(1990, 01, 01));

		// Set the properties of the EmployeesDto as needed for test scenario

		// Create a sample ResponseDto for the mock response
		ResponseDto response = new ResponseDto();
		// Set the properties of the ResponseDto as needed for your test scenario

		when(adminhrmsconsumerservice.addEmployee(dto)).thenReturn(response);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		// Perform the POST request and validate the response using MockMvc
		mockMvc.perform(post("/adminhrmsconsumer/employee").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.response").value(response.getResponse()));

		// Add more assertions as needed for your test scenario

		// Verify that the adminhrmsconsumerservice method was called with the correct
		// dto
		verify(adminhrmsconsumerservice, times(1)).addEmployee(dto);
	}

	private String asJsonString(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void testAddEmployee_InvalidInput() throws Exception {
		// Prepare the request body with invalid data
		String requestBody = "{\"empNo\": , \"firstName\": \"\", \"lastName\": \"Doe\"}";

		// Perform the POST request and assert the response
		mockMvc.perform(MockMvcRequestBuilders.post("/adminhrmsconsumer/employee")
				.contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

	@Test
	public void testSaveDeptEmp_InvalidInput() throws Exception {
		// Prepare the request body with invalid data
		String requestBody = "{\"empNo\": , \"deptNo\": \"\", \"fromDate\": \"2023-01-01\"}";

		// Perform the POST request and assert the response
		mockMvc.perform(MockMvcRequestBuilders.post("/adminhrmsconsumer/assigndept")
				.contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

	@Test
	public void testSaveDeptMgr_InvalidInput() throws Exception {
		// Prepare the request body with invalid data
		String requestBody = "{\"empNo\": , \"deptNo\":, \"fromDate\": \"2023-01-01\"}";

		// Perform the POST request and assert the response
		mockMvc.perform(MockMvcRequestBuilders.post("/adminhrmsconsumer/assignmgr")
				.contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

	@Test
	public void testAddDepartment_InvalidInput() throws Exception {
		// Prepare the request body with invalid data
		String requestBody = "{\"deptNo\":, \"deptName\": \"HR\", \"location\": \"New York\"}";
		// Perform the POST request and assert the response
		mockMvc.perform(MockMvcRequestBuilders.post("/adminhrmsconsumer/department")
				.contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		// Add additional assertions as needed

	}

	@Test
	public void testGetEmployeeByLastNYear_InvalidInput() throws Exception {
		// Test data
		Integer noOfYears = 5;

		// Stub the service method to return an empty list
		when(adminhrmsconsumerservice.getEmployeeByLastNYear(noOfYears))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the GET request and check for status code
		mockMvc.perform(get("/adminhrmsconsumer/employees/{noofyear}", noOfYears)).andExpect(status().isNotFound());

		// Verify the service method was called with the correct parameters
		verify(adminhrmsconsumerservice, times(1)).getEmployeeByLastNYear(noOfYears);
	}

	@Test
	public void testGetCountEmployeeByLastTenYear_InvalidInput() throws Exception {
		// Test data
		Integer invalidInput = null;

		// Perform the GET request and expect an exception to be thrown
		mockMvc.perform(get("/adminhrmsconsumer/employees/count/{noofyear}", invalidInput))
				.andExpect(status().isNotFound());

		// Verify that the service method was not called
		verify(adminhrmsconsumerservice, never()).getCountEmployeeByLastNYear(anyInt());
	}

	@Test
	public void testGetEmployeeListByNJoinYear_InvalidInput() throws Exception {
		// Test data
		Integer invalidYear = null;

		// Perform the GET request and expect a 400 Bad Request status code
		mockMvc.perform(get("/adminhrmsconsumer/employees/year/{year}", invalidYear)).andExpect(status().isNotFound());

		// Verify that the service method was not called
		verify(adminhrmsconsumerservice, never()).getEmployeeListByNJoinYear(anyInt());
	}

	@Test
	public void testGetEmployeeCountByNJoinYear_InvalidInput() throws Exception {
		// Test data
		Integer invalidYear = null;

		// Perform the GET request and expect a 400 Bad Request status
		mockMvc.perform(get("/adminhrmsconsumer/employees/count/year/{year}", invalidYear))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testAssignTitle_InvalidInput() throws Exception {
		// Test data
		TitlesDto invalidDto = new TitlesDto();

		// Stub the service method to return an error response
		when(adminhrmsconsumerservice.assignTitle(any(TitlesDto.class)))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the POST request and expect a 400 Bad Request status
		mockMvc.perform(post("/adminhrmsconsumer/assigntitle").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(invalidDto))).andExpect(status().isBadRequest());
	}

	@Test
	public void testGetExperiencedEmployeeByYear_InvalidInput() throws Exception {
		// Test data
		int noOfYears = 5;

		// Stub the service method to return an empty list
		when(adminhrmsconsumerservice.getExperiencedEmployeeByYear(anyInt()))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the GET request and expect a 404 Not Found status
		mockMvc.perform(get("/adminhrmsconsumer/employee/experience/{noofyear}", noOfYears))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testGetEmployeeCountByGender_NoDataFound() throws Exception {
		// Test data
		String gender = "unknown";

		// Stub the service method to return 0 count
		when(adminhrmsconsumerservice.getEmployeeCountByGender(anyString()))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the GET request and expect a 404 Not Found status
		mockMvc.perform(get("/adminhrmsconsumer/employee/gender/{gender}/count", gender))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testUpdateSalaryByRating_InvalidEmployee() throws Exception {
		// Test data
		Integer empNo = 123;
		RequestDto requestDto = new RequestDto();
		requestDto.setRating(4);

		// Stub the service method to return a response indicating an invalid employee
		when(adminhrmsconsumerservice.updateSalaryByRating(any(RequestDto.class), any(Integer.class)))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the PUT request and expect a 400 Bad Request status
		mockMvc.perform(put("/adminhrmsconsumer/salaries/{empno}", empNo).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(requestDto))).andExpect(status().isNotFound());
	}

}
