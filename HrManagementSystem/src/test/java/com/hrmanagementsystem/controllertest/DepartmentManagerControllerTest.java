package com.hrmanagementsystem.controllertest;

import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrmanagementsystem.HrManagementSystemApplication;
import com.hrmanagementsystem.dto.DepartmentManagerDto;
import com.hrmanagementsystem.exception.UniversalExceptionHandler;
import com.hrmanagementsystem.service.DepartmentManagerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HrManagementSystemApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "Raashid", roles = "ADMIN")
public class DepartmentManagerControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DepartmentManagerService departmentManagerService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllDepartmentManager() throws Exception {
		List<DepartmentManagerDto> departmentManagerList = new ArrayList<>();
		// Add test data to the departmentManagerList

		when(departmentManagerService.getAllDepartmentManager()).thenReturn(departmentManagerList);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/deptmanager").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(departmentManagerList.size()));
	}

	@Test
	public void testGetDepartmentManagerByEmpNoAndDeptNo() throws Exception {
		int empNo = 123;
		String deptNo = "IT";

		DepartmentManagerDto dto = new DepartmentManagerDto();
		when(departmentManagerService.getDepartmentManagerByEmpNoAndDeptNo(empNo, deptNo)).thenReturn(dto);
		mockMvc.perform(MockMvcRequestBuilders.get("/deptmanager/empno/{empno}/deptno/{deptno}", empNo, deptNo)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.empNo").value(dto.getEmpNo()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.deptNo").value(dto.getDeptNo()));
	}

	@Test
	public void testGetDepartmentManagerByDeptNoAndFromDate() throws Exception {
		String deptNo = "IT";
		LocalDate fromDate = LocalDate.of(2023, 1, 1);

		DepartmentManagerDto dto1 = new DepartmentManagerDto();
		DepartmentManagerDto dto2 = new DepartmentManagerDto();

		List<DepartmentManagerDto> deptManagerList = new ArrayList<>();
		deptManagerList.add(dto1);
		deptManagerList.add(dto2);

		when(departmentManagerService.getDepartmentManagerByDeptNoAndFromDate(deptNo, fromDate))
				.thenReturn(deptManagerList);

		mockMvc.perform(MockMvcRequestBuilders.get("/deptmanager/deptno/{deptno}/fromdate/{fromdate}", deptNo, fromDate)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].empNo").value(dto1.getEmpNo()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].deptNo").value(dto1.getDeptNo()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].fromDate").value(dto2.getFromDate()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].toDate").value(dto2.getToDate()));
	}

	@Test
	public void testGetDepartmentManagerByEmpNoAndDeptNoAndFromDate() throws Exception {
		int empNo = 123;
		String deptNo = "D001";
		LocalDate fromDate = LocalDate.of(2023, 1, 1);

		DepartmentManagerDto dto1 = new DepartmentManagerDto();
		DepartmentManagerDto dto2 = new DepartmentManagerDto();

		List<DepartmentManagerDto> deptManagerList = new ArrayList<>();
		deptManagerList.add(dto1);
		deptManagerList.add(dto2);

		when(departmentManagerService.getDepartmentManagerByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate))
				.thenReturn(deptManagerList);

		mockMvc.perform(MockMvcRequestBuilders
				.get("/deptmanager/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}", empNo, deptNo, fromDate)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].empNo").value(dto1.getEmpNo()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].deptNo").value(dto1.getDeptNo()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].fromDate").value(dto2.getFromDate()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].toDate").value(dto2.getToDate()));
	}

	@Test
	public void testGetDepartmentManagerByEmpNoAndFromDate() throws Exception {
		int empNo = 123;
		LocalDate fromDate = LocalDate.of(2023, 1, 1);

		DepartmentManagerDto dto1 = new DepartmentManagerDto();
		DepartmentManagerDto dto2 = new DepartmentManagerDto();

		List<DepartmentManagerDto> deptManagerList = new ArrayList<>();
		deptManagerList.add(dto1);
		deptManagerList.add(dto2);

		when(departmentManagerService.getDepartmentManagerByEmpNoAndFromDate(empNo, fromDate))
				.thenReturn(deptManagerList);

		mockMvc.perform(MockMvcRequestBuilders.get("/deptmanager/empno/{empno}/fromdate/{fromdate}", empNo, fromDate)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].empNo").value(dto1.getEmpNo()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].deptNo").value(dto1.getDeptNo()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].fromDate").value(dto2.getFromDate()))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].toDate").value(dto2.getToDate()));
	}

	@Test
	public void testSaveDepartment() throws Exception {
		DepartmentManagerDto departmentManagerDto = new DepartmentManagerDto();
		// Set the necessary fields in the departmentManagerDto for the test

		departmentManagerDto.setDeptNo("d001");
		departmentManagerDto.setEmpNo(10001);

		String expectedResponse = "Department manager saved successfully";
		when(departmentManagerService.addDepartmentManager(departmentManagerDto)).thenReturn(expectedResponse);

		// Perform the POST request and validate the response
		mockMvc.perform(post("/deptmanager/adddeptmanager").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(departmentManagerDto))).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(expectedResponse));

		// Verify that the departmentManagerService method was called
		verify(departmentManagerService, times(1)).addDepartmentManager(departmentManagerDto);
	}

	// Helper method to convert an object to JSON string
	private String asJsonString(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

	@Test
	public void testUpdateDepartmentEmployeeByEmpNoAndDeptNo() throws Exception {
		DepartmentManagerDto departmentManagerDto = new DepartmentManagerDto();
		// Set the necessary fields in the departmentManagerDto for the test
		Integer empNo = 123;
		String deptNo = "dept001";

		String expectedResponse = "Department manager updated successfully";
		when(departmentManagerService.updateDepartmentManagerByEmpNoAndDeptNo(departmentManagerDto, empNo, deptNo))
				.thenReturn(expectedResponse);

		// Perform the PUT request and validate the response
		mockMvc.perform(put("/deptmanager/{empno}/{deptno}", empNo, deptNo).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(departmentManagerDto))).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(expectedResponse));

		// Verify that the departmentManagerService method was called
		verify(departmentManagerService, times(1)).updateDepartmentManagerByEmpNoAndDeptNo(departmentManagerDto, empNo,
				deptNo);
	}

	@Test
	public void testUpdateDepartmentManagerByDeptNoAndFromDate() throws Exception {

		// Set the necessary fields in the departmentManagerDto for the test
		String deptNo = "d002";
		LocalDate fromDate = LocalDate.now();
		DepartmentManagerDto dto = new DepartmentManagerDto();
		dto.setDeptNo("d001");
		dto.setEmpNo(1001);

		List<DepartmentManagerDto> expectedList = new ArrayList<>();
		expectedList.add(dto);
		// Add the necessary DepartmentManagerDto objects to the expectedList

		when(departmentManagerService.updateDepartmentManagerByDeptNoAndFromDate(dto, deptNo, fromDate))
				.thenReturn(expectedList);

		// Perform the PUT request and validate the response
		mockMvc.perform(put("/deptmanager/deptno/{deptno}/fromdate/{fromdate}", deptNo, fromDate)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(dto))).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].deptNo").value("d001")).andExpect(jsonPath("$[0].empNo").value(1001));

		// Verify that the departmentManagerService method was called
		verify(departmentManagerService, times(1)).updateDepartmentManagerByDeptNoAndFromDate(dto, deptNo, fromDate);
	}

	@Test
	public void testUpdateDepartmentEmployeeByEmpNoAndDeptNoAndFromDate() throws Exception {
		// Prepare test data
		DepartmentManagerDto departmentManagerDto = new DepartmentManagerDto();
		Integer empNo = 123;
		String deptNo = "dept001";
		LocalDate fromDate = LocalDate.now();

		// Mock the behavior of the departmentManagerService
		String expectedResponse = "Update successful";

		when(departmentManagerService.updateDepartmentManagerByEmpNoAndDeptNoAndFromDate(departmentManagerDto, empNo,
				deptNo, fromDate)).thenReturn(expectedResponse);

		// Perform the PUT request and validate the response
		String content = new ObjectMapper().writeValueAsString(departmentManagerDto);
		mockMvc.perform(put("/deptmanager/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}", empNo, deptNo, fromDate)
				.contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").value(expectedResponse));

		// Verify that the departmentManagerService method was called
		verify(departmentManagerService, times(1))
				.updateDepartmentManagerByEmpNoAndDeptNoAndFromDate(departmentManagerDto, empNo, deptNo, fromDate);
	}

	@Test
	public void testUpdateDepartmentEmployeeByEmpNoAndFromDate() throws Exception {
		// Prepare test data
		DepartmentManagerDto departmentManagerDto = new DepartmentManagerDto();
		Integer empNo = 123;
		LocalDate fromDate = LocalDate.now();

		// Mock the behavior of the departmentManagerService
		String expectedResponse = "Update successful";

		when(departmentManagerService.updateDepartmentManagerByEmpNoAndFromDate(departmentManagerDto, empNo, fromDate))
				.thenReturn(expectedResponse);

		// Perform the PUT request and validate the response
		String content = new ObjectMapper().writeValueAsString(departmentManagerDto);
		mockMvc.perform(put("/deptmanager/empno/{empno}/fromdate/{fromdate}", empNo, fromDate)
				.contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").value(expectedResponse));

		// Verify that the departmentManagerService method was called
		verify(departmentManagerService, times(1)).updateDepartmentManagerByEmpNoAndFromDate(departmentManagerDto,
				empNo, fromDate);
	}

	@Test
	public void testGetAllDepartmentManager_InvalidInput() throws Exception {
	    // Mock the behavior of the departmentManagerService to return an empty list
	    when(departmentManagerService.getAllDepartmentManager())
	    .thenThrow(new UniversalExceptionHandler("Not Found!"));

	    // Perform the GET request and validate the response
	    mockMvc.perform(get("/deptmanager"))
	    .andExpect(MockMvcResultMatchers.status().isNotFound());
	    // Verify that the departmentManagerService method was called
	    verify(departmentManagerService, times(1)).getAllDepartmentManager();
	}

	@Test
	public void testSaveDepartment_InvalidInput() throws Exception {
		// Prepare test data
		DepartmentManagerDto departmentManagerDto = new DepartmentManagerDto();
		// Set invalid or missing values in the departmentManagerDto

		// Perform the POST request and validate the response
		mockMvc.perform(post("/adddeptmanager").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(departmentManagerDto))).andExpect(status().isNotFound());

		// Verify that the departmentManagerService method was not called
		verify(departmentManagerService, never()).addDepartmentManager(any(DepartmentManagerDto.class));
	}

	@Test
	public void testGetDepartmentManagerByEmpNoAndDeptNo_NoDataFound() throws Exception {
		// Test data
		Integer empNo = 1;
		String deptNo = "D001";

		// Stub the service method to return null
		when(departmentManagerService.getDepartmentManagerByEmpNoAndDeptNo(empNo, deptNo))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the GET request and check for status code
		mockMvc.perform(get("/deptmanager/empno/{empno}/deptno/{deptno}", empNo, deptNo))
				.andExpect(status().isNotFound());

		// Verify the service method was called with the correct parameters
		verify(departmentManagerService, times(1)).getDepartmentManagerByEmpNoAndDeptNo(empNo, deptNo);
	}

	@Test
	public void testGetDepartmentManagerByDeptNoAndFromDate_NoDataFound() throws Exception {
		// Test data
		String deptNo = "D001";
		LocalDate fromDate = LocalDate.of(2023, 1, 1);

		// Stub the service method to return an empty list
		when(departmentManagerService.getDepartmentManagerByDeptNoAndFromDate(deptNo, fromDate))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the GET request and check for status code
		mockMvc.perform(get("/deptmanager/deptno/{deptno}/fromdate/{fromdate}", deptNo, fromDate))
				.andExpect(status().isNotFound());

		// Verify the service method was called with the correct parameters
		verify(departmentManagerService, times(1)).getDepartmentManagerByDeptNoAndFromDate(deptNo, fromDate);
	}

	@Test
	public void testGetDepartmentManagerByEmpNoAndFromDate_NoDataFound() throws Exception {
		// Test data
		Integer empNo = 1;
		LocalDate fromDate = LocalDate.of(2023, 1, 1);

		// Stub the service method to return null
		when(departmentManagerService.getDepartmentManagerByEmpNoAndFromDate(empNo, fromDate))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the GET request and check for status code
		mockMvc.perform(get("/deptmanager/empno/{empno}/fromdate/{fromdate}", empNo, fromDate))
				.andExpect(status().isNotFound());

		// Verify the service method was called with the correct parameters
		verify(departmentManagerService, times(1)).getDepartmentManagerByEmpNoAndFromDate(empNo, fromDate);
	}

	@Test
	public void testGetDepartmentManagerByEmpNoAndDeptNoAndFromDate_NoDataFound() throws Exception {
		// Test data
		Integer empNo = 1;
		String deptNo = "D001";
		LocalDate fromDate = LocalDate.of(2023, 1, 1);

		// Stub the service method to return an empty list
		when(departmentManagerService.getDepartmentManagerByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the GET request and check for status code
		mockMvc.perform(get("/deptmanager/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}", empNo, deptNo, fromDate))
				.andExpect(status().isNotFound());

		// Verify the service method was called with the correct parameters
		verify(departmentManagerService, times(1)).getDepartmentManagerByEmpNoAndDeptNoAndFromDate(empNo, deptNo,
				fromDate);
	}

	@Test
	public void testUpdateDepartmentEmployeeByEmpNoAndDeptNo_NoDataFound() throws Exception {
		// Prepare test data
		DepartmentManagerDto departmentManagerDto = new DepartmentManagerDto();
		// Set the necessary values in the departmentManagerDto

		Integer empNo = 123;
		String deptNo = "D001";

		// Mock the behavior of the departmentManagerService
		when(departmentManagerService.updateDepartmentManagerByEmpNoAndDeptNo(departmentManagerDto, empNo, deptNo))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the PUT request and validate the response
		mockMvc.perform(put("/deptmanager/{empno}/{deptno}", empNo, deptNo).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(departmentManagerDto))).andExpect(status().isNotFound());

		// Verify that the departmentManagerService method was called
		verify(departmentManagerService, times(1)).updateDepartmentManagerByEmpNoAndDeptNo(departmentManagerDto, empNo,
				deptNo);
	}

	@Test
	public void testUpdateDepartmentManagerByDeptNoAndFromDate_NoDataFound() throws Exception {
		// Prepare test data
		DepartmentManagerDto departmentManagerDto = new DepartmentManagerDto();
		// Set the necessary values in the departmentManagerDto

		String deptNo = "D001";
		LocalDate fromDate = LocalDate.of(2023, 1, 1);

		// Mock the behavior of the departmentManagerService
		when(departmentManagerService.updateDepartmentManagerByDeptNoAndFromDate(departmentManagerDto, deptNo,
				fromDate)).thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the PUT request and validate the response
		mockMvc.perform(put("/deptmanager/deptno/{deptno}/fromdate/{fromdate}", deptNo, fromDate)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(departmentManagerDto))).andExpect(status().isNotFound());

		// Verify that the departmentManagerService method was called
		verify(departmentManagerService, times(1)).updateDepartmentManagerByDeptNoAndFromDate(departmentManagerDto,
				deptNo, fromDate);
	}

	@Test
	public void testUpdateDepartmentManagerByEmpNoAndDeptNoAndFromDate_NoDataFound() throws Exception {
		// Prepare test data
		DepartmentManagerDto departmentManagerDto = new DepartmentManagerDto();
		// Set the necessary values in the departmentManagerDto

		Integer empNo = 123;
		String deptNo = "D001";
		LocalDate fromDate = LocalDate.of(2023, 1, 1);

		// Mock the behavior of the departmentManagerService
		when(departmentManagerService.updateDepartmentManagerByEmpNoAndDeptNoAndFromDate(departmentManagerDto, empNo,
				deptNo, fromDate)).thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the PUT request and validate the response
		mockMvc.perform(put("/deptmanager/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}", empNo, deptNo, fromDate)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(departmentManagerDto))).andExpect(status().isNotFound());

		// Verify that the departmentManagerService method was called
		verify(departmentManagerService, times(1))
				.updateDepartmentManagerByEmpNoAndDeptNoAndFromDate(departmentManagerDto, empNo, deptNo, fromDate);
	}

	@Test
	public void testUpdateDepartmentManagerByEmpNoAndFromDate_NoDataFound() throws Exception {
		// Prepare test data
		DepartmentManagerDto departmentManagerDto = new DepartmentManagerDto();
		// Set the necessary values in the departmentManagerDto

		Integer empNo = 123;
		LocalDate fromDate = LocalDate.of(2023, 1, 1);

		// Mock the behavior of the departmentManagerService
		when(departmentManagerService.updateDepartmentManagerByEmpNoAndFromDate(departmentManagerDto, empNo, fromDate))
				.thenThrow(new UniversalExceptionHandler("Not Found!"));

		// Perform the PUT request and validate the response
		mockMvc.perform(put("/deptmanager/empno/{empno}/fromdate/{fromdate}", empNo, fromDate)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(departmentManagerDto))).andExpect(status().isNotFound());

		// Verify that the departmentManagerService method was called
		verify(departmentManagerService, times(1)).updateDepartmentManagerByEmpNoAndFromDate(departmentManagerDto,
				empNo, fromDate);
	}

}
