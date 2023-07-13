package com.hrmanagementsystem.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hrmanagementsystem.HrManagementSystemApplication;
import com.hrmanagementsystem.dto.TitlesDto;
import com.hrmanagementsystem.exception.UniversalExceptionHandler;
import com.hrmanagementsystem.service.TitlesService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HrManagementSystemApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "Raashid", roles = "ADMIN")
public class TitlesControllerTest {

	@MockBean
	private TitlesService titlesService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddTitle() throws Exception {
		// Create a TitlesDto object for the request body
		TitlesDto titlesDto = new TitlesDto();
		titlesDto.setTitle("Manager");
		titlesDto.setEmpNo(1001);
		titlesDto.setFromDate(LocalDate.of(2000, 01, 01));

		// Initialize the ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		// Mock the service method and define the expected response
		when(titlesService.addTitle(titlesDto)).thenReturn("Title added successfully");

		// Perform the POST request and receive the response
		MvcResult result = mockMvc.perform(post("/titles").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(titlesDto))).andExpect(status().isOk()).andReturn();

		// Get the response body
		String responseBody = result.getResponse().getContentAsString();

		// Assert the response body with the expected value
		assertEquals("Title added successfully", responseBody);

	}

	@Test
	public void testgetAllTitles() throws Exception {
		TitlesDto titlesDto = new TitlesDto();
		List<TitlesDto> titlesDtoList = Arrays.asList(titlesDto);

		when(titlesService.getAllTitles()).thenReturn(titlesDtoList);
		mockMvc.perform(MockMvcRequestBuilders.get("/titles")).andExpect(MockMvcResultMatchers.status().isOk());

		verify(titlesService, times(1)).getAllTitles();
	}

	@Test
	public void testgetTitlesByEmpNoAndFromDateAndTitle() throws Exception {
		Integer empno = 123;
		LocalDate fromdate = LocalDate.of(2023, 1, 1);
		String title = "Engineer";

		TitlesDto titles = new TitlesDto();
		when(titlesService.getTitlesByEmpNoAndFromDateAndTitle(empno, fromdate, title)).thenReturn(titles);

		mockMvc.perform(MockMvcRequestBuilders.get("/titles/empno/{empno}/fromdate/{fromdate}/title/{title}", empno,
				fromdate, title)).andExpect(status().isOk());
		verify(titlesService, times(1)).getTitlesByEmpNoAndFromDateAndTitle(empno, fromdate, title);
	}

	@Test
	public void testgetTitlesByFromDate() throws Exception {
		LocalDate fromdate = LocalDate.of(2023, 1, 1); // Change the arguments to the correct order: year, month, day
		List<TitlesDto> titlesList = Arrays.asList(new TitlesDto());

		when(titlesService.getTitlesByFromDate(fromdate)).thenReturn(titlesList);
		mockMvc.perform(MockMvcRequestBuilders.get("/titles/fromdate/{fromdate}", fromdate)).andExpect(status().isOk());
		verify(titlesService, times(1)).getTitlesByFromDate(fromdate);
	}

	@Test
	public void testgetTitlesByTitles() throws Exception {
		String title = "Engineer";
		List<TitlesDto> titlesList = Arrays.asList(new TitlesDto());
		when(titlesService.getTitlesByTitles(title)).thenReturn(titlesList);
		mockMvc.perform(MockMvcRequestBuilders.get("/titles/title/{title}", title)).andExpect(status().isOk());
		verify(titlesService, times(1)).getTitlesByTitles(title);
	}

	@Test
	public void testgetTitlesByTitlesAndFromDate() throws Exception {
		String title = "Engineer";
		LocalDate fromdate = LocalDate.of(2023, 1, 1);
		List<TitlesDto> titlesList = Arrays.asList(new TitlesDto());
		when(titlesService.getTitlesByTitlesAndFromDate(title, fromdate)).thenReturn(titlesList);
		mockMvc.perform(MockMvcRequestBuilders.get("/titles/title/{title}/fromdate/{fromdate}", title, fromdate))
				.andExpect(status().isOk());
		verify(titlesService, times(1)).getTitlesByTitlesAndFromDate(title, fromdate);
	}

	@Test
	public void testGetTitlesByTitlesAndFromDate_NoTitlesFound() throws Exception {
		// Set up the test data
		String title = "Manager";
		LocalDate fromDate = LocalDate.of(2022, 1, 1);

		// Mock the titlesService.getTitlesByTitlesAndFromDate() method to return an
		// empty list
		when(titlesService.getTitlesByTitlesAndFromDate(title, fromDate))
				.thenThrow(new UniversalExceptionHandler("InvalidInput"));

		// Perform the GET request to retrieve titles
		mockMvc.perform(MockMvcRequestBuilders.get("/titles/title/{title}/fromdate/{fromdate}", title, fromDate))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the titlesService.getTitlesByTitlesAndFromDate() method was
		// called with the correct arguments
		verify(titlesService, times(1)).getTitlesByTitlesAndFromDate(title, fromDate);
	}

	@Test
	public void testgetTitlesByEmpNoAndFromDate() throws Exception {

		Integer empno = 123;
		LocalDate fromdate = LocalDate.of(2023, 1, 1);
		List<TitlesDto> titlesList = Arrays.asList(new TitlesDto());

		when(titlesService.getTitlesByEmpNoAndFromDate(empno, fromdate)).thenReturn(titlesList);
		mockMvc.perform(MockMvcRequestBuilders.get("/titles/empno/{empno}/fromdate/{fromdate}", empno, fromdate))

				.andExpect(MockMvcResultMatchers.status().isOk());

		verify(titlesService, times(1)).getTitlesByEmpNoAndFromDate(empno, fromdate);
	}

	@Test
	public void testGetTitlesByEmpNoAndFromDate_NoTitlesFound() throws Exception {
		// Set up the test data
		Integer empno = 1001;
		LocalDate fromDate = LocalDate.of(2022, 1, 1);

		// Mock the titlesService.getTitlesByEmpNoAndFromDate() method to return an
		// empty list
		when(titlesService.getTitlesByEmpNoAndFromDate(empno, fromDate))
				.thenThrow(new UniversalExceptionHandler("InvalidInput"));

		// Perform the GET request to retrieve titles
		mockMvc.perform(MockMvcRequestBuilders.get("/titles/empno/{empno}/fromdate/{fromdate}", empno, fromDate))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the titlesService.getTitlesByEmpNoAndFromDate() method was called
		// with the correct arguments
		verify(titlesService, times(1)).getTitlesByEmpNoAndFromDate(empno, fromDate);
	}

	@Test
	public void testgetTitlesByEmpNoAndTitle() throws Exception {
		String title = "Employee";
		Integer empno = 123;
		TitlesDto titlesDto = new TitlesDto();
		List<TitlesDto> titlesList = Arrays.asList(titlesDto);
		when(titlesService.getTitlesByEmpNoAndTitle(empno, title)).thenReturn(titlesList);

		mockMvc.perform(MockMvcRequestBuilders.get("/titles/empno/{empno}/title/{title}", empno, title))
				.andExpect(MockMvcResultMatchers.status().isOk());

		verify(titlesService, times(1)).getTitlesByEmpNoAndTitle(empno, title);
	}

	@Test
	public void testGetTitlesByEmpNoAndTitle_NoTitlesFound() throws Exception {
		// Set up the test data
		Integer empno = 1001;
		String title = "Manager";

		// Mock the titlesService.getTitlesByEmpNoAndTitle() method to return an empty
		// list
		when(titlesService.getTitlesByEmpNoAndTitle(empno, title))
				.thenThrow(new UniversalExceptionHandler("InvalidInput"));

		// Perform the GET request to retrieve titles
		mockMvc.perform(MockMvcRequestBuilders.get("/titles/empno/{empno}/title/{title}", empno, title))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the titlesService.getTitlesByEmpNoAndTitle() method was called
		// with the correct arguments
		verify(titlesService, times(1)).getTitlesByEmpNoAndTitle(empno, title);
	}

	@Test
	public void testUpdateTitleByFromDate() throws Exception {
		LocalDate fromdate = LocalDate.of(2022, 1, 1);
		TitlesDto updatedTitleDto = new TitlesDto();
		updatedTitleDto.setTitle("Updated Title");
		List<TitlesDto> titlesList = Arrays.asList(updatedTitleDto);
		when(titlesService.updateTitleByFromDate(updatedTitleDto, fromdate)).thenReturn(titlesList);

		mockMvc.perform(MockMvcRequestBuilders.put("/titles/fromdate/{fromdate}", fromdate)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(updatedTitleDto)))
				.andExpect(MockMvcResultMatchers.status().isOk());

		verify(titlesService, times(1)).updateTitleByFromDate(updatedTitleDto, fromdate);
	}

	@Test
	public void testUpdateTitleByFromDate_TitleNotFound() throws Exception {
		// Set up the test data
		LocalDate fromDate = LocalDate.of(2022, 1, 1);
		TitlesDto dto = new TitlesDto();
		dto.setTitle("Manager");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		// Mock the titlesService.updateTitleByFromDate() method to return an empty list
		when(titlesService.updateTitleByFromDate(dto, fromDate))
				.thenThrow(new UniversalExceptionHandler("InvalidInput"));

		// Perform the PUT request to update the title
		mockMvc.perform(MockMvcRequestBuilders.put("/titles/fromdate/{fromdate}", fromDate)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto)))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the titlesService.updateTitleByFromDate() method was called with
		// the correct arguments
		verify(titlesService, times(1)).updateTitleByFromDate(dto, fromDate);
	}

	@Test
	public void testUpdateTitlesByTitle() throws Exception {
		String title = "Employee";
		TitlesDto updatedTitleDto = new TitlesDto();
		updatedTitleDto.setTitle("Updated Title");
		List<TitlesDto> titlesList = Arrays.asList(updatedTitleDto);
		when(titlesService.updateTitlesByTitle(updatedTitleDto, title)).thenReturn(titlesList);
		mockMvc.perform(
				MockMvcRequestBuilders.put("/titles/title/{title}", title).contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(updatedTitleDto)))
				.andExpect(MockMvcResultMatchers.status().isOk());
		verify(titlesService, times(1)).updateTitlesByTitle(updatedTitleDto, title);
	}

	@Test
	public void testUpdateTitlesByTitle_TitleNotFound() throws Exception {
		// Set up the test data
		String title = "Manager";
		TitlesDto dto = new TitlesDto();
		dto.setEmpNo(1001);
		dto.setFromDate(LocalDate.of(2022, 1, 1));
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		// Mock the titlesService.updateTitlesByTitle() method to return an empty list
		when(titlesService.updateTitlesByTitle(dto, title)).thenThrow(new UniversalExceptionHandler("InvalidInput"));

		// Perform the PUT request to update the titles
		mockMvc.perform(MockMvcRequestBuilders.put("/titles/title/{title}", title)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto)))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the titlesService.updateTitlesByTitle() method was called with
		// the correct arguments
		verify(titlesService, times(1)).updateTitlesByTitle(dto, title);
	}

	@Test
	public void testUpdateTitlesByEmpNo() throws Exception {
		Integer empno = 123;
		TitlesDto updatedTitleDto = new TitlesDto();
		updatedTitleDto.setTitle("Updated Title");

		List<TitlesDto> titlesList = Arrays.asList(updatedTitleDto);
		when(titlesService.updateTitlesByEmpNo(updatedTitleDto, empno)).thenReturn(titlesList);

		String requestBody = "{\"title\":\"Updated Title\"}";

		mockMvc.perform(MockMvcRequestBuilders.put("/titles/{empno}", empno).contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)).andExpect(status().isOk());

		verify(titlesService, times(1)).updateTitlesByEmpNo(updatedTitleDto, empno);
	}

	@Test
	public void testUpdateTitlesByEmpNo_EmpNoNotFound() throws Exception {
		// Set up the test data
		Integer empno = 1001;
		TitlesDto dto = new TitlesDto();
		dto.setTitle("Manager");
		dto.setFromDate(LocalDate.of(2022, 1, 1));
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		// Mock the titlesService.updateTitlesByEmpNo() method to return an empty list
		when(titlesService.updateTitlesByEmpNo(dto, empno)).thenThrow(new UniversalExceptionHandler("InvalidInput"));

		// Perform the PUT request to update the titles
		mockMvc.perform(MockMvcRequestBuilders.put("/titles/{empno}", empno).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto))).andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the titlesService.updateTitlesByEmpNo() method was called with
		// the correct arguments
		verify(titlesService, times(1)).updateTitlesByEmpNo(dto, empno);
	}

	@Test
	public void testUpdateTitlesByEmpNoFromDateTitle() throws Exception {
		Integer empno = 123;
		LocalDate fromdate = LocalDate.of(2022, 1, 1);
		String title = "Updated Title";

		TitlesDto updatedTitleDto = new TitlesDto();
		updatedTitleDto.setTitle(title);

		String expectedResponse = "title updated Successfully";
		when(titlesService.updateTitlesByEmpNoFromDateTitle(eq(updatedTitleDto), eq(empno), eq(fromdate), eq(title)))
				.thenReturn(expectedResponse);

		mockMvc.perform(put("/titles/empno/{empno}/fromdate/{fromdate}/title/{title}", empno, fromdate, title)
				.contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"Updated Title\"}"))
				.andExpect(status().isOk());

		verify(titlesService, times(1)).updateTitlesByEmpNoFromDateTitle(eq(updatedTitleDto), eq(empno), eq(fromdate),
				eq(title));
	}

	@Test
	public void testUpdateTitlesByEmpNoFromDateTitle_TitleNotFound() throws Exception {
		// Set up the test data
		Integer empno = 1001;
		LocalDate fromdate = LocalDate.of(2022, 1, 1);
		String title = "Manager";
		TitlesDto dto = new TitlesDto();
		dto.setTitle("New Title");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		// Mock the titlesService.updateTitlesByEmpNoFromDateTitle() method to return a
		// null response
		when(titlesService.updateTitlesByEmpNoFromDateTitle(dto, empno, fromdate, title))
				.thenThrow(new UniversalExceptionHandler("InvalidInput"));

		// Perform the PUT request to update the titles
		mockMvc.perform(MockMvcRequestBuilders
				.put("/titles/empno/{empno}/fromdate/{fromdate}/title/{title}", empno, fromdate, title)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto)))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the titlesService.updateTitlesByEmpNoFromDateTitle() method was
		// called with the correct arguments
		verify(titlesService, times(1)).updateTitlesByEmpNoFromDateTitle(dto, empno, fromdate, title);
	}

	@Test
	public void testAddTitle_InvalidInput() throws Exception {
		// Create an invalid TitlesDto object for the request body
		TitlesDto dto = new TitlesDto(); // Missing required fields
		dto.setFromDate(LocalDate.of(2022, 1, 1));
		dto.setTitle("Manager");
		dto.setEmpNo(1001);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		when(titlesService.addTitle(dto)).thenThrow(new UniversalExceptionHandler("InvalidInput"));

		// Perform the POST request to add a title with the invalid input
		mockMvc.perform(MockMvcRequestBuilders.post("/titles").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto))).andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the titlesService.addTitle() method was not called
		// Verify that the titlesService.postAddTitle() method was called exactly once
		verify(titlesService, times(1)).addTitle(any(TitlesDto.class));
	}

	@Test
	public void testGetAllTitles_InvalidInput() throws Exception {
	    // Mock the titlesService.getAllTitles() method to throw an exception
	    when(titlesService.getAllTitles())
	    .thenThrow(new UniversalExceptionHandler("InvalidInput"));

	    // Perform the GET request to retrieve all titles
	    mockMvc.perform(MockMvcRequestBuilders.get("/titles"))
	            .andExpect(MockMvcResultMatchers.status().isNotFound());

	    // Verify that the titlesService.getAllTitles() method was called exactly once
	    verify(titlesService, times(1)).getAllTitles();
	}

	@Test
	public void testGetTitlesByEmpNoAndFromDateAndTitle_TitleNotFound() throws Exception {
		// Set up the test data
		Integer empno = 1001;
		LocalDate fromdate = LocalDate.of(2022, 1, 1);
		String title = "Manager";

		// Mock the titlesService.getTitlesByEmpNoAndFromDateAndTitle() method to return
		// null
		when(titlesService.getTitlesByEmpNoAndFromDateAndTitle(empno, fromdate, title))
				.thenThrow(new UniversalExceptionHandler("InvalidInput"));
		// Perform the GET request to retrieve titles
		mockMvc.perform(MockMvcRequestBuilders.get("/titles/empno/{empno}/fromdate/{fromdate}/title/{title}", empno,
				fromdate, title)).andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the titlesService.getTitlesByEmpNoAndFromDateAndTitle() method
		// was called with the correct arguments
		verify(titlesService, times(1)).getTitlesByEmpNoAndFromDateAndTitle(empno, fromdate, title);
	}

	@Test
	public void testGetTitlesByTitles_NoTitlesFound() throws Exception {
		// Set up the test data
		String title = "Manager";

		// Mock the titlesService.getTitlesByTitles() method to return an empty list
		when(titlesService.getTitlesByTitles(title)).thenThrow(new UniversalExceptionHandler("InvalidInput"));

		// Perform the GET request to retrieve titles
		mockMvc.perform(MockMvcRequestBuilders.get("/titles/title/{title}", title))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the titlesService.getTitlesByTitles() method was called with the
		// correct argument
		verify(titlesService, times(1)).getTitlesByTitles(title);
	}

	@Test
	public void testGetTitlesByFromDate_NoTitlesFound() throws Exception {
		// Set up the test data
		LocalDate fromDate = LocalDate.of(2022, 1, 1);

		// Mock the titlesService.getTitlesByFromDate() method to return an empty list
		when(titlesService.getTitlesByFromDate(fromDate)).thenThrow(new UniversalExceptionHandler("InvalidInput"));

		// Perform the GET request to retrieve titles
		mockMvc.perform(MockMvcRequestBuilders.get("/titles/fromdate/{fromdate}", fromDate))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

		// Verify that the titlesService.getTitlesByFromDate() method was called with
		// the correct argument
		verify(titlesService, times(1)).getTitlesByFromDate(fromDate);
	}

}
