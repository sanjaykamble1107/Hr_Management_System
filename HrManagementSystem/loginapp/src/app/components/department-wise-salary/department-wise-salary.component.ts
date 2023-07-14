import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { DeptWiseSalaryService } from 'src/ServicesREST/dept-wise-salary.service';

@Component({
  selector: 'app-department-wise-salary',
  templateUrl: './department-wise-salary.component.html',
  styleUrls: ['./department-wise-salary.component.css']
})
export class DepartmentWiseSalaryComponent {
  departmentList: any[] = [];

  constructor(private departmentService: DeptWiseSalaryService) {}

  // Method to fetch department details
  fetchDepartmentDetails() {
    this.departmentService.getDepartmentDetails()
      .subscribe(
        (data: any[]) => {
          this.departmentList = data;
        },
        (error: HttpErrorResponse) => {
          const errorDetails = error.error.errorDetails;
          alert(errorDetails);
          console.log(errorDetails);
        }
      );
  }
}
