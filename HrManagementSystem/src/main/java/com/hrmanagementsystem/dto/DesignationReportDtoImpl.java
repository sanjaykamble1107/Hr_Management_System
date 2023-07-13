package com.hrmanagementsystem.dto;

public class DesignationReportDtoImpl implements DesignationReportDto{
	
        private String designation;
        private Double averageSalary;
        private Integer maxSalary;
        private Integer minSalary;

        public DesignationReportDtoImpl(String designation, Double averageSalary, Integer maxSalary, Integer minSalary) {
            this.designation = designation;
            this.averageSalary = averageSalary;
            this.maxSalary = maxSalary;
            this.minSalary = minSalary;
        }

        public String getDesignation() {
            return designation;
        }

        public Double getAverageSalary() {
            return averageSalary;
        }

        public Integer getMaxSalary() {
            return maxSalary;
        }

        public Integer getMinSalary() {
            return minSalary;
        }
    }

