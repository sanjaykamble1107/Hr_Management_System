package com.hrmanagementsystem.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrmanagementsystem.HrManagementSystemApplication;
import com.hrmanagementsystem.dto.DepartmentsDto;
import com.hrmanagementsystem.serviceimpl.DepartmentServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HrManagementSystemApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "Raashid", roles = "ADMIN")
public class DepartmentsControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DepartmentServiceImpl departmentService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSaveDepartment() throws Exception {
		DepartmentsDto departmentDto = new DepartmentsDto();
		departmentDto.setDeptNo("d003");
		departmentDto.setDeptName("Clerk");

		when(departmentService.addDepartment(departmentDto)).thenReturn("New department added successfully");

		String jsonRequest = asJsonString(departmentDto);

		MvcResult result = mockMvc
				.perform(
						post("/departments/adddepartment").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
				.andExpect(status().isOk()).andReturn();

		String responseContent = result.getResponse().getContentAsString();
		assertEquals("New department added successfully", responseContent);
	}

	@Test
	public void testSaveDepartment_Invalid() throws Exception {
		DepartmentsDto departmentDto = new DepartmentsDto();
		departmentDto.setDeptNo(null); // Set a required field to null to make the data invalid
		departmentDto.setDeptName("Clerk");

		when(departmentService.addDepartment(departmentDto)).thenReturn("New department added successfully");

		String jsonRequest = asJsonString(departmentDto);

		mockMvc.perform(post("/departments/adddepartment").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
				.andExpect(status().isBadRequest());
	}

	private String asJsonString(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void testGetDepartmentByNo() throws Exception {
		DepartmentsDto departmentDto = new DepartmentsDto();
		departmentDto.setDeptNo("d003");
		departmentDto.setDeptName("Clerk");

		when(departmentService.getDepartmentByNo("d003")).thenReturn(departmentDto);

		mockMvc.perform(get("/departments/{deptno}", "d003")).andExpect(status().isOk())
				.andExpect(jsonPath("$.deptNo").value("d003")).andExpect(jsonPath("$.deptName").value("Clerk"));

	}

	@Test
	public void testGetDepartmentByNo_Invalid() throws Exception {
		mockMvc.perform(get("/departments/{deptno}", "")).andExpect(status().isNotFound());
	}

	@Test
	public void testGetDepartmentByName() throws Exception {
		DepartmentsDto departmentDto = new DepartmentsDto();
		departmentDto.setDeptNo("d003");
		departmentDto.setDeptName("Clerk");

		when(departmentService.getDepartmentByName("Clerk")).thenReturn(departmentDto);

		mockMvc.perform(get("/departments/name/{deptname}", "Clerk")).andExpect(status().isOk())
				.andExpect(jsonPath("$.deptNo").value("d003")).andExpect(jsonPath("$.deptName").value("Clerk"));
	}

	@Test
	public void testGetDepartmentByName_InvalidDeptName() throws Exception {
		mockMvc.perform(get("/departments/name/{deptname}", "")).andExpect(status().isNotFound());
	}

	@Test
	public void testUpdateDepartmentByNo() throws Exception {
		String deptNo = "d003";
		String deptName = "Senior Clerk";
		DepartmentsDto existingDepartmentDto = new DepartmentsDto();
		existingDepartmentDto.setDeptNo("d003");
		existingDepartmentDto.setDeptName("Clerk");

		DepartmentsDto updatedDepartmentDto = new DepartmentsDto();
		updatedDepartmentDto.setDeptNo(deptNo);
		updatedDepartmentDto.setDeptName(deptName);

		when(departmentService.updateDepartmentByNo(any(), eq(deptNo))).thenReturn("Department updated successfully");
		String jsonRequest = asJsonString(updatedDepartmentDto);
		MvcResult result = mockMvc.perform(
				put("/departments/{deptno}", deptNo).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
				.andExpect(status().isOk()).andReturn();

		String responseContent = result.getResponse().getContentAsString();
		assertEquals("Department updated successfully", responseContent);
		verify(departmentService, times(1)).updateDepartmentByNo(any(), eq(deptNo));
	}

	@Test
	public void testUpdateDepartmentByNo_Invalid() throws Exception {
		DepartmentsDto departmentDto = new DepartmentsDto();
		departmentDto.setDeptNo("d004");
		departmentDto.setDeptName("Senior Clerk");

		String jsonRequest = asJsonString(null);

		mockMvc.perform(
				put("/departments/{deptno}", "d003").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testUpdateDepartmentByName() throws Exception {
		String deptNo = "d003";
		String deptName = "Senior Clerk";
		DepartmentsDto existingDepartmentDto = new DepartmentsDto();
		existingDepartmentDto.setDeptNo("d003");
		existingDepartmentDto.setDeptName("Clerk");

		DepartmentsDto updatedDepartmentDto = new DepartmentsDto();
		updatedDepartmentDto.setDeptNo(deptNo);
		updatedDepartmentDto.setDeptName(deptName);

		when(departmentService.updateDepartmentByName(any(), eq(deptName)))
				.thenReturn("Department updated successfully");
		String jsonRequest = asJsonString(updatedDepartmentDto);
		MvcResult result = mockMvc.perform(put("/departments/name/{deptname}", deptName)
				.contentType(MediaType.APPLICATION_JSON).content(jsonRequest)).andExpect(status().isOk()).andReturn();

		String responseContent = result.getResponse().getContentAsString();
		assertEquals("Department updated successfully", responseContent);
		verify(departmentService, times(1)).updateDepartmentByName(any(), eq(deptName));
	}

	@Test
	public void testUpdateDepartmentByName_Invalid() throws Exception {
		DepartmentsDto departmentDto = new DepartmentsDto();
		departmentDto.setDeptNo("d004");
		departmentDto.setDeptName("Senior Clerk");

		String jsonRequest = asJsonString(null);

		mockMvc.perform(put("/departments/{deptno}", "Senior Clerk").contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)).andExpect(status().isBadRequest());
	}

	@Test
	public void testGetAllDepartments() throws Exception {
		// Mock the service method to return a list of departments
		List<DepartmentsDto> departmentList = new ArrayList<>();
		DepartmentsDto existingDepartmentDto = new DepartmentsDto();
		existingDepartmentDto.setDeptNo("d003");
		existingDepartmentDto.setDeptName("Clerk");

		DepartmentsDto updatedDepartmentDto = new DepartmentsDto();
		updatedDepartmentDto.setDeptNo("d004");
		updatedDepartmentDto.setDeptName("Senior Clerk");
		departmentList.add(existingDepartmentDto);
		departmentList.add(updatedDepartmentDto);
		when(departmentService.getAllDepartments()).thenReturn(departmentList);

		// Call the method under test
		List<DepartmentsDto> responseDepartmentsList = departmentService.getAllDepartments();

		// Assertions
		assertEquals(departmentList.size(), responseDepartmentsList.size());
		assertEquals(departmentList.get(0).getDeptNo(), responseDepartmentsList.get(0).getDeptNo());
		assertEquals(departmentList.get(0).getDeptName(), responseDepartmentsList.get(0).getDeptName());
		assertEquals(departmentList.get(1).getDeptNo(), responseDepartmentsList.get(1).getDeptNo());
		assertEquals(departmentList.get(1).getDeptName(), responseDepartmentsList.get(1).getDeptName());
	}

	@Test
	public void testGetAllDepartments_Invalid() throws Exception{
	    // Assume that an error occurs while retrieving the departments
	    when(departmentService.getAllDepartments()).thenThrow(new RuntimeException("Error retrieving departments"));

	    // Call the method under test
	    try {
	        departmentService.getAllDepartments();
	        fail("Expected exception was not thrown");
	    } catch (RuntimeException ex) {
	        // Assertion for the expected exception
	        assertEquals("Error retrieving departments", ex.getMessage());
	    }
	}
}
