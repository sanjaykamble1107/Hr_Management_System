package com.hrmanagementsystem.dto;

public class DepartmentReportDtoImpl implements DepartmentReportDto {
	 
	        private String deptNo;
	        private Double avgSalary;
	        private Integer maxSalary;
	        private Integer minSalary;

	        public DepartmentReportDtoImpl(String deptNo, Double avgSalary, Integer maxSalary, Integer minSalary) {
	            this.deptNo = deptNo;
	            this.avgSalary = avgSalary;
	            this.maxSalary = maxSalary;
	            this.minSalary = minSalary;
	        }

	        public String getDeptNo() {
	            return deptNo;
	        }

	        public Double getAvgSalary() {
	            return avgSalary;
	        }

	        public Integer getMaxSalary() {
	            return maxSalary;
	        }

	        public Integer getMinSalary() {
	            return minSalary;
	        }
	    }
	
