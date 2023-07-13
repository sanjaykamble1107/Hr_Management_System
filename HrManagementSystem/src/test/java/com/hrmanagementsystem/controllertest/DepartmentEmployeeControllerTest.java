package com.hrmanagementsystem.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrmanagementsystem.HrManagementSystemApplication;
import com.hrmanagementsystem.dto.DepartmentEmployeeDto;
import com.hrmanagementsystem.exception.UniversalExceptionHandler;
import com.hrmanagementsystem.serviceimpl.DepartmentEmployeeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HrManagementSystemApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "Raashid", roles = "ADMIN")
public class DepartmentEmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DepartmentEmployeeServiceImpl departmentEmployeeService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSaveDepartment() throws Exception {
		// Create a sample DepartmentEmployeeDto for the test
		DepartmentEmployeeDto dto = new DepartmentEmployeeDto();
		dto.setEmpNo(10001);
		dto.setDeptNo("d002");
		// Set the properties of the DepartmentEmployeeDto as needed for your test
		// scenario

		// Create a sample response string for the mock response
		String response = "Department employee added successfully";
		// Set the response string as needed for your test scenario

		when(departmentEmployeeService.addDepartmentEmployee(dto)).thenReturn(response);

		// Perform the POST request and validate the response using MockMvc
		MvcResult result = mockMvc.perform(post("/deptemp/adddeptemp").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(dto))).andExpect(status().isOk()).andReturn();

		// Extract the response content from the MvcResult
		String responseContent = result.getResponse().getContentAsString();

		// Validate the response content using assertEquals
		assertEquals(response, responseContent);

		// Verify that the departmentEmployeeService method was called with the correct
		// dto
		verify(departmentEmployeeService, times(1)).addDepartmentEmployee(dto);
	}

	@Test
	public void testGetAllDepartmentEmployee() throws Exception {
		// Create a sample list of DepartmentEmployeeDto for the test
		List<DepartmentEmployeeDto> deptempList = new ArrayList<>();
		// Add sample DepartmentEmployeeDto objects to the list as needed for your test
		// scenario

		when(departmentEmployeeService.getAllDepartmentEmployee()).thenReturn(deptempList);

		// Perform the GET request and validate the response using MockMvc
		mockMvc.perform(get("/deptemp")).andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()").value(deptempList.size()));
		// Add more assertions as needed for your test scenario

		// Verify that the departmentEmployeeService method was called
		verify(departmentEmployeeService, times(1)).getAllDepartmentEmployee();
	}

	@Test
	public void testGetDepartmentEmployeeByDeptNoAndFromDate() throws Exception {
		String deptNo = "dept001";
		LocalDate fromDate = LocalDate.now();

		// Create a sample list of DepartmentEmployeeDto for the test
		List<DepartmentEmployeeDto> deptempList = new ArrayList<>();
		// Add sample DepartmentEmployeeDto objects to the list as needed for your test
		// scenario

		when(departmentEmployeeService.getDepartmentEmployeeByDeptNoAndFromDate(deptNo, fromDate))
				.thenReturn(deptempList);

		// Perform the GET request and validate the response using MockMvc
		mockMvc.perform(get("/deptemp/deptno/{deptno}/fromdate/{fromdate}", deptNo, fromDate))
				.andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()").value(deptempList.size()));
		// Add more assertions as needed for your test scenario

		// Verify that the departmentEmployeeService method was called
		verify(departmentEmployeeService, times(1)).getDepartmentEmployeeByDeptNoAndFromDate(deptNo, fromDate);
	}

	@Test
	public void testGetDepartmentEmployeeByEmpNoAndFromDate() throws Exception {
		int empNo = 123;
		LocalDate fromDate = LocalDate.of(2023, 1, 1);

		DepartmentEmployeeDto dto1 = new DepartmentEmployeeDto();
		dto1.setDeptNo("IT");
		dto1.setFromDate(LocalDate.of(2000, 01, 01));

		when(departmentEmployeeService.getDepartmentEmployeeByEmpNoAndFromDate(empNo, fromDate)).thenReturn(dto1);

		mockMvc.perform(get("/deptemp/empno/{empno}/fromdate/{fromdate}", empNo, fromDate)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.deptNo").value("IT"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.fromDate").value("2000-01-01"));

	}

	@Test
	public void testGetDepartmentEmployeeByEmpNoAndDeptNo() throws Exception {
		int empNo = 123;
		String deptNo = "IT";

		DepartmentEmployeeDto dto = new DepartmentEmployeeDto();
		when(departmentEmployeeService.getDepartmentEmployeeByEmpNoAndDeptNo(empNo, deptNo)).thenReturn(dto);
		mockMvc.perform(
				get("/deptemp/empno/{empno}/deptno/{deptno}", empNo, deptNo).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.empNo").value(dto.getEmpNo()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.deptNo").value(dto.getDeptNo()));
	}

	@Test
	public void testGetDepartmentManagerByEmpNoAndDeptNoAndFromDate() throws Exception {
		int empNo = 123;
		String deptNo = "D001";
		LocalDate fromDate = LocalDate.of(2023, 1, 1);

		DepartmentEmployeeDto dto1 = new DepartmentEmployeeDto();
		DepartmentEmployeeDto dto2 = new DepartmentEmployeeDto();

		List<DepartmentEmployeeDto> deptEmployeeList = new ArrayList<>();
		deptEmployeeList.add(dto1);
		deptEmployeeList.add(dto2);

		when(departmentEmployeeService.getDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate))
				.thenReturn(deptEmployeeList);

		mockMvc.perform(get("/deptemp/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}", empNo, deptNo, fromDate)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].empNo").value(dto1.getEmpNo()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].deptNo").value(dto1.getDeptNo()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].fromDate").value(dto2.getFromDate()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].toDate").value(dto2.getToDate()));
	}

	@Test
	public void testUpdateDepartmentEmployeeByEmpNoAndDeptNo() throws Exception {
		Integer empNo = 1001;
		String deptNo = "dept001";
		DepartmentEmployeeDto departmentEmployeeDto = new DepartmentEmployeeDto();
		// Set the necessary fields in the departmentEmployeeDto for the test

		// Mock the behavior of the departmentEmployeeService
		String expectedResponse = "Department Employee updated successfully";
		when(departmentEmployeeService.updateDepartmentEmployeeByEmpNoAndDeptNo(departmentEmployeeDto, empNo, deptNo))
				.thenReturn(expectedResponse);
		MvcResult result = mockMvc.perform(put("/deptemp/{empno}/{deptno}", empNo, deptNo)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(departmentEmployeeDto)))
				.andExpect(status().isOk()).andReturn();

		String responseContent = result.getResponse().getContentAsString();
		assertEquals("Department Employee updated successfully", responseContent);
		verify(departmentEmployeeService, times(1)).updateDepartmentEmployeeByEmpNoAndDeptNo(departmentEmployeeDto,
				empNo, deptNo);

	}

	// Utility method to convert object to JSON string
	private static String asJsonString(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

	@Test
	public void testUpdateDepartmentEmployeeByEmpNoAndFromDate() throws Exception {
		Integer empNo = 1001;
		LocalDate fromDate = LocalDate.now();
		DepartmentEmployeeDto departmentEmployeeDto = new DepartmentEmployeeDto();
		// Set the necessary fields in the departmentEmployeeDto for the test

		String expectedResponse = "Department Employee updated successfully";
		when(departmentEmployeeService.updateDepartmentEmployeeByEmpNoAndFromDate(departmentEmployeeDto, empNo,
				fromDate)).thenReturn(expectedResponse);

		// Perform the PUT request and validate the response
		MvcResult result = mockMvc
				.perform(put("/deptemp/empno/{empno}/fromdate/{fromdate}", empNo, fromDate)
						.contentType(MediaType.APPLICATION_JSON).content(asJsonString(departmentEmployeeDto)))
				.andExpect(status().isOk()).andReturn();

		String responseContent = result.getResponse().getContentAsString();
		assertEquals("Department Employee updated successfully", responseContent);

		// Verify that the departmentEmployeeService method was called
		verify(departmentEmployeeService, times(1)).updateDepartmentEmployeeByEmpNoAndFromDate(departmentEmployeeDto,
				empNo, fromDate);
	}

	@Test
	public void testUpdateDepartmentEmployeeByDeptNoAndFromDate() throws Exception {
		String deptNo = "D001";
		LocalDate fromDate = LocalDate.now();
		DepartmentEmployeeDto departmentEmployeeDto = new DepartmentEmployeeDto();
		// Set the necessary fields in the departmentEmployeeDto for the test

		List<DepartmentEmployeeDto> expectedDeptEmpList = new ArrayList<>();
		departmentEmployeeDto.setDeptNo("d002");
		departmentEmployeeDto.setEmpNo(1001);
		expectedDeptEmpList.add(departmentEmployeeDto);

		when(departmentEmployeeService.updateDepartmentEmployeeByDeptNoAndFromDate(departmentEmployeeDto, deptNo,
				fromDate)).thenReturn(expectedDeptEmpList);

		// Perform the PUT request and validate the response
		mockMvc.perform(put("/deptemp/deptno/{deptno}/fromdate/{fromdate}", deptNo, fromDate)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(departmentEmployeeDto)))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].deptNo").value("d002"))
				.andExpect(jsonPath("$[0].empNo").value(1001));

		// Verify that the departmentEmployeeService method was called
		verify(departmentEmployeeService, times(1)).updateDepartmentEmployeeByDeptNoAndFromDate(departmentEmployeeDto,
				deptNo, fromDate);
	}

	@Test
	public void testUpdateDepartmentEmployeeByEmpNoAndDeptNoAndFromDate() throws Exception {
		Integer empNo = 1001;
		String deptNo = "d001";
		LocalDate fromDate = LocalDate.now();
		DepartmentEmployeeDto departmentEmployeeDto = new DepartmentEmployeeDto();
		// Set the necessary fields in the departmentEmployeeDto for the test

		// Mock the behavior of the departmentEmployeeService
		String expectedResponse = "Department employee updated successfully";
		when(departmentEmployeeService.updateDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(departmentEmployeeDto, empNo,
				deptNo, fromDate)).thenReturn(expectedResponse);

		// Perform the PUT request and validate the response
		mockMvc.perform(put("/deptemp/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}", empNo, deptNo, fromDate)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(departmentEmployeeDto)))
				.andExpect(status().isOk()).andExpect(jsonPath("$").value("Department employee updated successfully"));

		// Verify that the departmentEmployeeService method was called
		verify(departmentEmployeeService, times(1))
				.updateDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(departmentEmployeeDto, empNo, deptNo, fromDate);
	}

	@Test
	public void testSaveDepartment_InvalidInput() throws Exception {
		// Prepare the request body with invalid data
		String requestBody = "{\"empNo\": , \"deptNo\": \"\", \"fromDate\": \"2023-01-01\"}";

		// Perform the POST request and assert the response
		mockMvc.perform(MockMvcRequestBuilders.post("/deptemp/adddeptemp").contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)).andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

	@Test
	public void testGetAllDepartmentEmployee_InvalidInput() throws Exception {
	    // Mock the behavior of the departmentEmployeeService to return an empty list
	    when(departmentEmployeeService.getAllDepartmentEmployee()).thenThrow(new UniversalExceptionHandler("Not Found!"));

	    // Perform the GET request and validate the response
	    mockMvc.perform(get("/deptemp"))
	            .andExpect(status().isNotFound());

	    // Verify that the departmentEmployeeService method was called
	    verify(departmentEmployeeService, times(1)).getAllDepartmentEmployee();
	}

	@Test
	public void testGetDepartmentEmployeeByDeptNoAndFromDate_Invalid() throws Exception {
		// Test data
		String deptNo = "D001";
		LocalDate fromDate = LocalDate.of(2023, 1, 1);

		// Stub the service method to return null
		when(departmentEmployeeService.getDepartmentEmployeeByDeptNoAndFromDate(deptNo, fromDate))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the GET request and check for status code
		mockMvc.perform(get("/deptemp/deptno/{deptno}/fromdate/{fromdate}", deptNo, fromDate))
				.andExpect(status().isNotFound());

		// Verify the service method was called with the correct parameters
		verify(departmentEmployeeService, times(1)).getDepartmentEmployeeByDeptNoAndFromDate(deptNo, fromDate);
	}

	@Test
	public void testGetDepartmentEmployeeByEmpNoAndFromDate_InvalidInput() throws Exception {
		// Test data
		Integer empNo = 123;
		LocalDate fromDate = LocalDate.of(2023, 1, 1);

		// Stub the service method to return null
		when(departmentEmployeeService.getDepartmentEmployeeByEmpNoAndFromDate(empNo, fromDate))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the GET request and check for status code
		mockMvc.perform(get("/deptemp/empno/{empno}/fromdate/{fromdate}", empNo, fromDate))
				.andExpect(status().isNotFound());

		// Verify the service method was called with the correct parameters
		verify(departmentEmployeeService, times(1)).getDepartmentEmployeeByEmpNoAndFromDate(empNo, fromDate);
	}

	@Test
	public void testGetDepartmentEmployeeByEmpNoAndDeptNo_InvalidInput() throws Exception {
		// Test data
		Integer empNo = 123;
		String deptNo = "D001";

		// Stub the service method to return null
		when(departmentEmployeeService.getDepartmentEmployeeByEmpNoAndDeptNo(empNo, deptNo))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the GET request and check for status code
		mockMvc.perform(get("/deptemp/empno/{empno}/deptno/{deptno}", empNo, deptNo)).andExpect(status().isNotFound());

		// Verify the service method was called with the correct parameters
		verify(departmentEmployeeService, times(1)).getDepartmentEmployeeByEmpNoAndDeptNo(empNo, deptNo);
	}

	@Test
	public void testGetDepartmentEmployeeByEmpNoAndDeptNoAndFromDate_InvalidInput() throws Exception {
		// Test data
		Integer empNo = 123;
		String deptNo = "D001";
		LocalDate fromDate = LocalDate.of(2023, 1, 1);

		// Stub the service method to return an empty list
		when(departmentEmployeeService.getDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));
		// Perform the GET request and check for status code
		mockMvc.perform(get("/deptemp/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}", empNo, deptNo, fromDate))
				.andExpect(status().isNotFound());

		// Verify the service method was called with the correct parameters
		verify(departmentEmployeeService, times(1)).getDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(empNo, deptNo,
				fromDate);
	}

	@Test
	public void testUpdateDepartmentEmployeeByEmpNoAndDeptNo_InvalidInput() throws Exception {
		// Prepare test data
		DepartmentEmployeeDto departmentEmployeeDto = new DepartmentEmployeeDto();
		// Set the necessary values in the departmentEmployeeDto

		Integer empNo = 123;
		String deptNo = "D001";

		// Mock the behavior of the departmentEmployeeService
		when(departmentEmployeeService.updateDepartmentEmployeeByEmpNoAndDeptNo(departmentEmployeeDto, empNo, deptNo))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the PUT request and validate the response
		mockMvc.perform(put("/deptemp/{empno}/{deptno}", empNo, deptNo).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(departmentEmployeeDto)))
				.andExpect(status().isNotFound());

		// Verify that the departmentEmployeeService method was called
		verify(departmentEmployeeService, times(1)).updateDepartmentEmployeeByEmpNoAndDeptNo(departmentEmployeeDto,
				empNo, deptNo);
	}

	@Test
	public void testUpdateDepartmentEmployeeByEmpNoAndFromDate_InvalidInput() throws Exception {
		// Prepare test data
		DepartmentEmployeeDto departmentEmployeeDto = new DepartmentEmployeeDto();
		// Set the necessary values in the departmentEmployeeDto

		Integer empNo = 123;
		LocalDate fromDate = LocalDate.of(2023, 1, 1);

		// Mock the behavior of the departmentEmployeeService
		when(departmentEmployeeService.updateDepartmentEmployeeByEmpNoAndFromDate(departmentEmployeeDto, empNo,
				fromDate)).thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the PUT request and validate the response
		mockMvc.perform(put("/deptemp/empno/{empno}/fromdate/{fromdate}", empNo, fromDate)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(departmentEmployeeDto)))
				.andExpect(status().isNotFound());

		// Verify that the departmentEmployeeService method was called
		verify(departmentEmployeeService, times(1)).updateDepartmentEmployeeByEmpNoAndFromDate(departmentEmployeeDto,
				empNo, fromDate);
	}

	@Test
	public void testUpdateDepartmentEmployeeByDeptNoAndFromDate_InvalidInput() throws Exception {
		// Prepare test data
		DepartmentEmployeeDto departmentEmployeeDto = new DepartmentEmployeeDto();
		// Set the necessary values in the departmentEmployeeDto

		String deptNo = "D001";
		LocalDate fromDate = LocalDate.of(2023, 1, 1);

		// Mock the behavior of the departmentEmployeeService
		when(departmentEmployeeService.updateDepartmentEmployeeByDeptNoAndFromDate(departmentEmployeeDto, deptNo,
				fromDate)).thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the PUT request and validate the response
		mockMvc.perform(put("/deptemp/deptno/{deptno}/fromdate/{fromdate}", deptNo, fromDate)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(departmentEmployeeDto)))
				.andExpect(status().isNotFound());

		// Verify that the departmentEmployeeService method was called
		verify(departmentEmployeeService, times(1)).updateDepartmentEmployeeByDeptNoAndFromDate(departmentEmployeeDto,
				deptNo, fromDate);
	}

	@Test
	public void testUpdateDepartmentEmployeeByEmpNoAndDeptNoAndFromDate_InvalidInput() throws Exception {
		// Prepare test data
		DepartmentEmployeeDto departmentEmployeeDto = new DepartmentEmployeeDto();
		// Set the necessary values in the departmentEmployeeDto

		Integer empNo = 123;
		String deptNo = "D001";
		LocalDate fromDate = LocalDate.of(2023, 1, 1);

		// Mock the behavior of the departmentEmployeeService
		when(departmentEmployeeService.updateDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(departmentEmployeeDto, empNo,
				deptNo, fromDate)).thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the PUT request and validate the response
		mockMvc.perform(put("/deptemp/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}", empNo, deptNo, fromDate)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(departmentEmployeeDto)))
				.andExpect(status().isNotFound());

		// Verify that the departmentEmployeeService method was called
		verify(departmentEmployeeService, times(1))
				.updateDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(departmentEmployeeDto, empNo, deptNo, fromDate);
	}
}
