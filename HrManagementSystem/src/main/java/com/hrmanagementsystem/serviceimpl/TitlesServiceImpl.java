package com.hrmanagementsystem.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hrmanagementsystem.dto.TitlesDto;
import com.hrmanagementsystem.entity.Employees;
import com.hrmanagementsystem.entity.Titles;
import com.hrmanagementsystem.exception.UniversalExceptionHandler;
import com.hrmanagementsystem.repository.TitlesRepository;
import com.hrmanagementsystem.service.TitlesService;

@Service
public class TitlesServiceImpl implements TitlesService {

	@Autowired
	private TitlesRepository titlesRepository;

	// Method to retrieve all titles
	@Override
	public List<TitlesDto> getAllTitles() {
		List<Titles> titleslist = titlesRepository.findAll();
		List<TitlesDto> titlesdtolist = new ArrayList<>();
		for (Titles title : titleslist) {
			TitlesDto dto = new TitlesDto();
			BeanUtils.copyProperties(title, dto);
			dto.setEmpNo(title.getEmployees().getEmpNo());
			titlesdtolist.add(dto);

		}
		return titlesdtolist;
	}

	// Method to retrieve all titles by empNo and fromDate and title
	@Override
	public TitlesDto getTitlesByEmpNoAndFromDateAndTitle(Integer empno, LocalDate fromdate, String title) {
		Titles titles = titlesRepository.findByEmpNoAndFromDateAndTitle(empno, fromdate, title);
		TitlesDto titlesdto = new TitlesDto();
		BeanUtils.copyProperties(titles, titlesdto);
		titlesdto.setEmpNo(titles.getEmployees().getEmpNo());
		return titlesdto;
	}

	// Method to retrieve all titles by fromDate
	@Override
	public List<TitlesDto> getTitlesByFromDate(LocalDate fromdate) {
		List<Titles> titleslist = titlesRepository.findByFromDate(fromdate);
		List<TitlesDto> titlesdtolist = new ArrayList<>();
		for (Titles title : titleslist) {
			TitlesDto dto = new TitlesDto();
			BeanUtils.copyProperties(title, dto);
			dto.setEmpNo(title.getEmployees().getEmpNo());
			titlesdtolist.add(dto);

		}
		return titlesdtolist;
	}

	// Method to retrieve all titles by titles
	@Override
	public List<TitlesDto> getTitlesByTitles(String titles) {
		Pageable pageable = PageRequest.of(0, 20);
		Page<Titles> titleslist = titlesRepository.findByTitle(titles, pageable);
		List<TitlesDto> titlesdtolist = new ArrayList<>();
		for (Titles title : titleslist) {
			TitlesDto dto = new TitlesDto();
			BeanUtils.copyProperties(title, dto);
			dto.setEmpNo(title.getEmployees().getEmpNo());
			titlesdtolist.add(dto);

		}
		return titlesdtolist;
	}

	// Method to retrieve all titles by titles and fromDate
	@Override
	public List<TitlesDto> getTitlesByTitlesAndFromDate(String titles, LocalDate fromdate) {
		List<Titles> titleslist = titlesRepository.findByTitleAndFromDate(titles, fromdate);
		List<TitlesDto> titlesdtolist = new ArrayList<>();
		for (Titles title : titleslist) {
			TitlesDto dto = new TitlesDto();
			BeanUtils.copyProperties(title, dto);
			dto.setEmpNo(title.getEmployees().getEmpNo());
			titlesdtolist.add(dto);

		}
		return titlesdtolist;
	}

	// Method to retrieve all titles by empNo and fromDate
	@Override
	public List<TitlesDto> getTitlesByEmpNoAndFromDate(Integer empno, LocalDate fromdate) {
		List<Titles> titleslist = titlesRepository.findByEmpNoAndFromDate(empno, fromdate);
		List<TitlesDto> titlesdtolist = new ArrayList<>();
		for (Titles title : titleslist) {
			TitlesDto dto = new TitlesDto();
			BeanUtils.copyProperties(title, dto);
			dto.setEmpNo(title.getEmployees().getEmpNo());
			titlesdtolist.add(dto);

		}
		return titlesdtolist;
	}

	// Method to retrieve all titles by empNo and titles
	@Override
	public List<TitlesDto> getTitlesByEmpNoAndTitle(Integer empno, String titles) {
		List<Titles> titleslist = titlesRepository.findByEmpNoAndTitle(empno, titles);
		List<TitlesDto> titlesdtolist = new ArrayList<>();
		for (Titles title : titleslist) {
			TitlesDto dto = new TitlesDto();
			BeanUtils.copyProperties(title, dto);
			dto.setEmpNo(title.getEmployees().getEmpNo());
			titlesdtolist.add(dto);

		}
		return titlesdtolist;
	}

	// Method to update title by fromDate
	@Override
	public List<TitlesDto> updateTitleByFromDate(TitlesDto titledto, LocalDate fromdate) {
		List<Titles> titleslist = titlesRepository.findByFromDate(fromdate);
		if (!titleslist.isEmpty()) {
			List<TitlesDto> titlesdtolist = new ArrayList<>();
			for (Titles title : titleslist) {
				title.setToDate(titledto.getToDate());
				titlesRepository.save(title);
				titledto.setTitle(title.getTitle());
				titledto.setFromDate(title.getFromDate());
				titledto.setEmpNo(title.getEmployees().getEmpNo());
				titlesdtolist.add(titledto);

			}
			return titlesdtolist;
		}
		throw new UniversalExceptionHandler("Validation failed");
	}

	// Method to add title
	@Override
	public String addTitle(TitlesDto dto) {
		Titles title = new Titles();
		Employees e = new Employees(dto.getEmpNo());
		BeanUtils.copyProperties(dto, title);
		title.setEmployees(e);
		try {
			titlesRepository.save(title);

			return "New title added Successfully";
		} catch (Exception ex) {
			throw new UniversalExceptionHandler("Validation failed");
		}
	}

	// Method to update title by title
	@Override
	public List<TitlesDto> updateTitlesByTitle(TitlesDto titledto, String title) {
		Pageable pageable = PageRequest.of(0, 20);
		Page<Titles> titleslist = titlesRepository.findByTitle(title, pageable);
		if (!titleslist.isEmpty()) {
			List<TitlesDto> titlesdtolist = new ArrayList<>();
			for (Titles titles : titleslist) {
				titles.setToDate(titledto.getToDate());
				titlesRepository.save(titles);
				titledto.setTitle(titles.getTitle());
				titledto.setFromDate(titles.getFromDate());
				titledto.setEmpNo(titles.getEmployees().getEmpNo());
				titlesdtolist.add(titledto);

			}
			return titlesdtolist;
		}
		throw new UniversalExceptionHandler("Validation failed");
	}

	// Method to update title by empNo
	@Override
	public List<TitlesDto> updateTitlesByEmpNo(TitlesDto titledto, Integer empno) {
		List<Titles> titleslist = titlesRepository.findByEmpNo(empno);
		if (!titleslist.isEmpty()) {
			List<TitlesDto> titlesdtolist = new ArrayList<>();
			for (Titles titles : titleslist) {
				titles.setToDate(titledto.getToDate());
				titlesRepository.save(titles);
				titledto.setTitle(titles.getTitle());
				titledto.setFromDate(titles.getFromDate());
				titledto.setEmpNo(titles.getEmployees().getEmpNo());
				titlesdtolist.add(titledto);

			}
			return titlesdtolist;
		}
		throw new UniversalExceptionHandler("Validation failed");

	}

	// Method to update title by empNo and fromDate and title
	@Override
	public String updateTitlesByEmpNoFromDateTitle(TitlesDto titledto, Integer empno, LocalDate fromdate,
			String title) {
		Titles titleobj = titlesRepository.findByEmpNoAndFromDateAndTitle(empno, fromdate, title);
		titleobj.setToDate(titledto.getToDate());
		titlesRepository.save(titleobj);
		titledto.setTitle(titleobj.getTitle());
		titledto.setFromDate(titleobj.getFromDate());
		titledto.setEmpNo(titleobj.getEmployees().getEmpNo());
		return "title updated Successfully";

	}

}
