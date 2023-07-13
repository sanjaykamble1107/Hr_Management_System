package com.hrmanagementsystem.service;

import java.time.LocalDate;
import java.util.List;

import com.hrmanagementsystem.dto.TitlesDto;

public interface TitlesService {



	TitlesDto getTitlesByEmpNoAndFromDateAndTitle(Integer empno, LocalDate fromdate, String title);

	List<TitlesDto> getTitlesByFromDate(LocalDate fromdate);

	List<TitlesDto> getTitlesByTitles(String title);

	List<TitlesDto> getTitlesByTitlesAndFromDate(String title, LocalDate fromdate);

	List<TitlesDto> getTitlesByEmpNoAndFromDate(Integer empno, LocalDate fromdate);

	List<TitlesDto> getTitlesByEmpNoAndTitle(Integer empno, String title);

	List<TitlesDto> updateTitleByFromDate(TitlesDto dto, LocalDate fromdate);

	String addTitle(TitlesDto dto);

	List<TitlesDto> getAllTitles();

	List<TitlesDto> updateTitlesByTitle(TitlesDto dto, String title);

	List<TitlesDto> updateTitlesByEmpNo(TitlesDto dto, Integer empno);

	String updateTitlesByEmpNoFromDateTitle(TitlesDto dto, Integer empno, LocalDate fromdate, String title);

}
