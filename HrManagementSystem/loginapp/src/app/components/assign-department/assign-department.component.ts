import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { DepartmentEmployeeService } from 'src/ServicesREST/department-employee.service';

@Component({
  selector: 'app-assign-department',
  templateUrl: './assign-department.component.html',
  styleUrls: ['./assign-department.component.css']
})
export class AssignDepartmentComponent {
  employeeName!: string;
  department!: string;
  fromDate!: string;
  toDate!: string;
  responseMessage!: string;
  empNo!: number;

  constructor(private deptEmpService: DepartmentEmployeeService) { }

  // Method to save the department-employee assignment
  saveDeptEmp() {
    // Create a DTO (Data Transfer Object) with the necessary data
    const dto = {
      empNo: this.empNo,
      deptNo: this.department,
      fromDate: this.fromDate,
      toDate: this.toDate
    };
    alert("department assigned")

    // Call the service to save the department-employee assignment
    this.deptEmpService.saveDeptEmp(dto)
      .subscribe(
        (response: string) => {
          this.responseMessage = response;
        },
        (error: HttpErrorResponse) => {
          const errorDetails = error.error.errorDetails;
          alert(errorDetails);
          console.log(errorDetails);
        }
      );
  }
}
