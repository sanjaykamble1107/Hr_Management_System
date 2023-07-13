package com.hrmanagementsystem.controllertest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
import org.mockito.Mockito;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hrmanagementsystem.HrManagementSystemApplication;
import com.hrmanagementsystem.dto.SalariesDto;
import com.hrmanagementsystem.exception.UniversalExceptionHandler;
import com.hrmanagementsystem.service.SalariesService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HrManagementSystemApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "Raashid", roles = "ADMIN")
public class SalriesControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SalariesService salariesService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	private static String asJsonString(Object obj) throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void testGetAllSalaries() throws Exception {

		List<SalariesDto> salList = new ArrayList<>();
		// Add test data to the departmentManagerList

		when(salariesService.getAllSalaries()).thenReturn(salList);

		// Act and Assert
		mockMvc.perform(get("/salaries").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(salList.size()));
	}

	@Test
	public void testGetSalariesByFromDate() throws Exception {

		LocalDate fromDate = LocalDate.of(2000, 01, 01);

		SalariesDto dto1 = new SalariesDto();
		SalariesDto dto2 = new SalariesDto();

		dto1.setFromDate(LocalDate.of(2000, 01, 01));

		dto2.setFromDate(LocalDate.of(2001, 01, 01));

		List<SalariesDto> deptempList = new ArrayList<>();
		deptempList.add(dto1);
		deptempList.add(dto2);

		when(salariesService.getSalariesByFromDate(fromDate)).thenReturn(deptempList);

		mockMvc.perform(get("/salaries/fromdate/{fromdate}", fromDate).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].fromDate").value("2000-01-01"))
				.andExpect(jsonPath("$[1].fromDate").value("2001-01-01"));
	}

	@Test
	public void testGetSalariesByEmpNoAndFromDate() throws Exception {
		// Test data
		Integer empNo = 1;
		LocalDate fromDate = LocalDate.of(2023, 1, 1);

		SalariesDto salariesDto = new SalariesDto();
		salariesDto.setEmpNo(empNo);
		salariesDto.setSalary(5000); // Set the necessary salary value
		salariesDto.setFromDate(fromDate);
		salariesDto.setToDate(LocalDate.of(2023, 12, 31)); // Set the necessary toDate value

		// Stub the service method
		when(salariesService.getSalariesByEmpNoAndFromDate(empNo, fromDate)).thenReturn(salariesDto);

		// Perform the GET request
		mockMvc.perform(get("/salaries/empno/{empno}/fromdate/{fromdate}", empNo, fromDate)).andExpect(status().isOk())
				.andExpect(jsonPath("$.empNo").value(salariesDto.getEmpNo()))
				.andExpect(jsonPath("$.salary").value(salariesDto.getSalary()))
				.andExpect(jsonPath("$.fromDate").value(salariesDto.getFromDate().toString()))
				.andExpect(jsonPath("$.toDate").value(salariesDto.getToDate().toString()));

		// Verify the service method was called with the correct parameters
		verify(salariesService, times(1)).getSalariesByEmpNoAndFromDate(empNo, fromDate);
	}

	@Test
	public void testGetSalariesByEmpNo() throws Exception {
		// Test data
		Integer empNo = 1;

		SalariesDto salariesDto1 = new SalariesDto();
		salariesDto1.setEmpNo(empNo);
		salariesDto1.setSalary(5000); // Set the necessary salary value
		salariesDto1.setFromDate(LocalDate.now());
		salariesDto1.setToDate(LocalDate.now().plusDays(1)); // Set the necessary toDate value

		SalariesDto salariesDto2 = new SalariesDto();
		salariesDto2.setEmpNo(empNo);
		salariesDto2.setSalary(6000); // Set the necessary salary value
		salariesDto2.setFromDate(LocalDate.now());
		salariesDto2.setToDate(LocalDate.now().plusDays(1)); // Set the necessary toDate value

		List<SalariesDto> salariesList = new ArrayList<>();
		salariesList.add(salariesDto1);
		salariesList.add(salariesDto2);

		// Stub the service method
		when(salariesService.getSalariesByEmpNo(empNo)).thenReturn(salariesList);

		// Perform the GET request
		mockMvc.perform(get("/salaries/empno/{empno}", empNo)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].empNo").value(salariesDto1.getEmpNo()))
				.andExpect(jsonPath("$[0].salary").value(salariesDto1.getSalary()))
				.andExpect(jsonPath("$[0].fromDate").value(salariesDto1.getFromDate().toString()))
				.andExpect(jsonPath("$[0].toDate").value(salariesDto1.getToDate().toString()))
				.andExpect(jsonPath("$[1].empNo").value(salariesDto2.getEmpNo()))
				.andExpect(jsonPath("$[1].salary").value(salariesDto2.getSalary()))
				.andExpect(jsonPath("$[1].fromDate").value(salariesDto2.getFromDate().toString()))
				.andExpect(jsonPath("$[1].toDate").value(salariesDto2.getToDate().toString()));

		// Verify the service method was called with the correct parameters
		verify(salariesService, times(1)).getSalariesByEmpNo(empNo);
	}

	@Test
	public void testAddSalary() throws Exception {
		// Create a sample SalariesDto object for testing
		SalariesDto dto = new SalariesDto();
		dto.setEmpNo(1001);
		dto.setFromDate(LocalDate.of(2000, 01, 01));
		dto.setSalary(28000);
		// Set properties of the dto object as needed for the test

		// Mock the response from the salariesService
		String expectedResponse = "Success";
		Mockito.when(salariesService.addSalary(dto)).thenReturn(expectedResponse);

		// Perform the request using MockMvc
		mockMvc.perform(post("/salaries").contentType(MediaType.APPLICATION_JSON).content(asJsonString(dto)))
				.andExpect(status().isOk());

		// Verify that the salariesService method was called with the correct argument
		Mockito.verify(salariesService).addSalary(dto);
	}

	// Utility method to convert object to JSON string

	@Test
	public void testGetSalariesBetweenMinAndMax() throws Exception {
		// Create sample input data
		Integer minSalary = 1000;
		Integer maxSalary = 5000;

		// Create a sample list of SalariesDto objects for testing
		List<SalariesDto> salariesList = new ArrayList<>();
		// Add some SalariesDto objects to the list

		Pageable pageable = PageRequest.of(0, 20);

		// Mock the response from the salariesService
		Mockito.when(salariesService.getSalariesBetweenMinAndMax(minSalary, maxSalary, pageable))
				.thenReturn(salariesList);

		// Perform the request using MockMvc
		mockMvc.perform(get("/salaries/salary/{minsalary}/{maxsalary}", minSalary, maxSalary, pageable)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()").value(salariesList.size()));

		// Verify that the salariesService method was called with the correct arguments
		Mockito.verify(salariesService).getSalariesBetweenMinAndMax(minSalary, maxSalary, pageable);
	}

	@Test
	public void testUpdateSalariesByFromDate() throws Exception {
		// Create a sample SalariesDto object for testing
		SalariesDto dto = new SalariesDto();
		// Set properties of the dto object as needed for the test

		// Create a sample LocalDate object for testing
		LocalDate fromDate = LocalDate.of(2023, 7, 1);

		// Create a sample list of SalariesDto objects for testing
		List<SalariesDto> salariesList = new ArrayList<>();
		// Add some SalariesDto objects to the list

		// Mock the response from the salariesService
		Mockito.when(salariesService.updateSalariesByFromDate(dto, fromDate)).thenReturn(salariesList);

		// Perform the request using MockMvc
		mockMvc.perform(put("/salaries/salary/fromdate/{fromdate}", fromDate).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(dto))).andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()").value(salariesList.size()));

		// Verify that the salariesService method was called with the correct arguments
		Mockito.verify(salariesService).updateSalariesByFromDate(dto, fromDate);
	}

	@Test
	public void testUpdateSalariesByEmpNo() throws Exception {
		// Create a sample SalariesDto object for testing
		SalariesDto dto = new SalariesDto();
		// Set properties of the dto object as needed for the test
		dto.setEmpNo(1001);
		dto.setSalary(26092);

		// Create a sample employee number for testing
		Integer empNo = 12345;

		// Create a sample list of SalariesDto objects for testing
		List<SalariesDto> salariesList = new ArrayList<>();
		salariesList.add(dto);
		// Add some SalariesDto objects to the list

		// Mock the response from the salariesService
		Mockito.when(salariesService.updateSalariesByEmpNo(dto, empNo)).thenReturn(salariesList);

		// Perform the request using MockMvc
		mockMvc.perform(put("/salaries/empno/{empno}", empNo).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(dto))).andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$.length()").value(salariesList.size()))
				.andExpect(jsonPath("$[0].empNo").value(1001)).andExpect(jsonPath("$[0].salary").value(26092));

		// Verify that the salariesService method was called with the correct arguments
		Mockito.verify(salariesService).updateSalariesByEmpNo(dto, empNo);
	}

	@Test
	public void testUpdateSalariesByEmpNoAndFromDate() throws Exception {
		// Prepare test data
		SalariesDto salariesDto = new SalariesDto();
		Integer empNo = 123;
		LocalDate fromDate = LocalDate.now();

		// Mock the behavior of the salariesService
		String expectedResponse = "Update successful";

		when(salariesService.updateSalariesByEmpNoAndFromDate(salariesDto, empNo, fromDate))
				.thenReturn(expectedResponse);

		// Perform the PUT request and validate the response
		String content = new ObjectMapper().writeValueAsString(salariesDto);
		mockMvc.perform(put("/salaries/empno/{empno}/fromdate/{fromdate}", empNo, fromDate)
				.contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isOk())
				.andExpect(jsonPath("$").value(expectedResponse));

		// Verify that the salariesService method was called
		verify(salariesService, times(1)).updateSalariesByEmpNoAndFromDate(salariesDto, empNo, fromDate);
	}

	@Test
	public void testGetAllSalaries_InvalidInput() throws Exception {
	    // Mock the salariesService.getAllSalaries() method to return an empty list
	    when(salariesService.getAllSalaries())
	    .thenThrow(new UniversalExceptionHandler("InvalidInput"));

	    // Perform the GET request to retrieve all salaries
	    mockMvc.perform(MockMvcRequestBuilders.get("/salaries"))
	            .andExpect(MockMvcResultMatchers.status().isNotFound());

	    // Verify that the salariesService.getAllSalaries() method was called exactly once
	    verify(salariesService, times(1)).getAllSalaries();
	}

	@Test
	public void testGetSalariesByFromDate_InvalidInput() throws Exception {
		// Set up the test data
		LocalDate fromDate = LocalDate.of(2022, 1, 1);

		// Mock the salariesService.getSalariesByFromDate() method to throw an exception
		when(salariesService.getSalariesByFromDate(fromDate)).thenThrow(new UniversalExceptionHandler("InvalidInput"));

		// Perform the GET request to retrieve salaries by from date
		mockMvc.perform(MockMvcRequestBuilders.get("/salaries/fromdate/{fromdate}", fromDate))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the salariesService.getSalariesByFromDate() method was called
		// with the correct argument
		verify(salariesService, times(1)).getSalariesByFromDate(fromDate);
	}

	@Test
	public void testGetSalariesByEmpNo_InvalidInput() throws Exception {
		// Set up the test data
		Integer empNo = 1001;

		// Mock the salariesService.getSalariesByEmpNo() method to return an empty list
		when(salariesService.getSalariesByEmpNo(empNo)).thenThrow(new UniversalExceptionHandler("InvalidInput"));

		// Perform the GET request to retrieve salaries by employee number
		mockMvc.perform(MockMvcRequestBuilders.get("/salaries/empno/{empno}", empNo))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

		verify(salariesService, times(1)).getSalariesByEmpNo(empNo);

	}

	@Test
	public void testGetSalariesByEmpNoAndFromDate_InvalidInput() throws Exception {
		// Set up the test data
		Integer empNo = 1001;
		LocalDate fromDate = LocalDate.of(2022, 1, 1);

		// Mock the salariesService.getSalariesByEmpNoAndFromDate() method to return
		// null
		when(salariesService.getSalariesByEmpNoAndFromDate(empNo, fromDate))
				.thenThrow(new UniversalExceptionHandler("InvalidInput"));

		// Perform the GET request to retrieve salaries by employee number and from date
		  mockMvc.perform(MockMvcRequestBuilders.get("/salaries/empno/{empno}/fromdate/{fromdate}", empNo, fromDate))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

		// Verify that the salariesService.getSalariesByEmpNoAndFromDate() method was
		// called with the correct arguments
		verify(salariesService, times(1)).getSalariesByEmpNoAndFromDate(empNo, fromDate);
	}

	@Test
	public void testUpdateSalariesByFromDate_InvalidInput() throws Exception {
		// Set up the test data
		SalariesDto salariesDto = new SalariesDto();
		salariesDto.setSalary(5000);
		salariesDto.setFromDate(LocalDate.of(2022, 1, 1));

		LocalDate fromDate = LocalDate.of(2022, 1, 1);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		// Mock the salariesService.updateSalariesByFromDate() method to throw an
		// exception
		when(salariesService.updateSalariesByFromDate(salariesDto, fromDate))
				.thenThrow(new UniversalExceptionHandler("InvalidInput"));

		// Perform the PUT request to update salaries by from date
		mockMvc.perform(MockMvcRequestBuilders.put("/salaries/salary/fromdate/{fromdate}", fromDate)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(salariesDto)))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the salariesService.updateSalariesByFromDate() method was called
		// with the correct arguments
		verify(salariesService, times(1)).updateSalariesByFromDate(salariesDto, fromDate);
	}

	@Test
	public void testUpdateSalariesByEmpNo_InvalidInput() throws Exception {
		// Set up the test data
		SalariesDto salariesDto = new SalariesDto();
		salariesDto.setSalary(5000);
		salariesDto.setFromDate(LocalDate.of(2022, 1, 1));

		Integer empNo = 1001;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		// Mock the salariesService.updateSalariesByEmpNo() method to throw an exception
		when(salariesService.updateSalariesByEmpNo(salariesDto, empNo))
				.thenThrow(new UniversalExceptionHandler("InvalidInput"));

		// Perform the PUT request to update salaries by employee number
		mockMvc.perform(MockMvcRequestBuilders.put("/salaries/empno/{empno}", empNo)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(salariesDto)))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the salariesService.updateSalariesByEmpNo() method was called
		// with the correct arguments
		verify(salariesService, times(1)).updateSalariesByEmpNo(salariesDto, empNo);
	}

	@Test
	public void testUpdateSalariesByEmpNoAndFromDate_InvalidInput() throws Exception {
		// Set up the test data
		SalariesDto salariesDto = new SalariesDto();
		salariesDto.setSalary(5000);

		Integer empNo = 1001;
		LocalDate fromDate = LocalDate.of(2022, 1, 1);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		// Mock the salariesService.updateSalariesByEmpNoAndFromDate() method to throw
		// an exception
		when(salariesService.updateSalariesByEmpNoAndFromDate(salariesDto, empNo, fromDate))
				.thenThrow(new UniversalExceptionHandler("InvalidInput"));

		// Perform the PUT request to update salaries by employee number and from date
		mockMvc.perform(MockMvcRequestBuilders.put("/salaries/empno/{empno}/fromdate/{fromdate}", empNo, fromDate)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(salariesDto)))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the salariesService.updateSalariesByEmpNoAndFromDate() method was
		// called with the correct arguments
		verify(salariesService, times(1)).updateSalariesByEmpNoAndFromDate(salariesDto, empNo, fromDate);
	}

	@Test
	public void testAddSalary_InvalidInput() throws Exception {
		// Create a SalariesDto object for the request body
		SalariesDto salariesDto = new SalariesDto();
		salariesDto.setEmpNo(1001);
		salariesDto.setSalary(5000);
		salariesDto.setFromDate(LocalDate.of(2000, 01, 01));
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		// Mock the salariesService.addSalary() method to throw an exception
		when(salariesService.addSalary(salariesDto)).thenThrow(new UniversalExceptionHandler("InvalidInput"));

		// Perform the POST request to add a salary
		mockMvc.perform(MockMvcRequestBuilders.post("/salaries").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(salariesDto)))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the salariesService.addSalary() method was called exactly once
		verify(salariesService, times(1)).addSalary(salariesDto);
	}

	@Test
	public void testGetSalariesBetweenMinAndMax_InvalidInput() throws Exception {
	    // Mock the salariesService.getSalariesBetweenMinAndMax() method to throw an exception
	    when(salariesService.getSalariesBetweenMinAndMax(anyInt(), anyInt(), any(Pageable.class)))
	            .thenThrow(new UniversalExceptionHandler("InvalidInput"));

	    // Perform the GET request to retrieve salaries within a range that is not found
	    mockMvc.perform(MockMvcRequestBuilders.get("/salaries/salary/{minsalary}/{maxsalary}", 5000, 6000))
	            .andExpect(MockMvcResultMatchers.status().isNotFound());

	    // Verify that the salariesService.getSalariesBetweenMinAndMax() method was called exactly once with the correct parameters
	    verify(salariesService, times(1)).getSalariesBetweenMinAndMax(5000, 6000, PageRequest.of(0, 20));
	}

}
