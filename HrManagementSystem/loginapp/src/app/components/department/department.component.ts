import { HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DepartmentService } from 'src/ServicesREST/department.service';
import { Departments } from 'src/ServicesREST/departments';

@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.css']
})
export class DepartmentComponent implements OnInit {
  deptNo: string = '';
  deptName: string = '';

  department: Departments = new Departments();

  constructor(private departmentService: DepartmentService, private router: Router) { }

  

  ngOnInit(): void {
    // Initialization logic goes here
  }

  // Method to save a department
  savingDepartment(usrObj: any): void {

    this.departmentService.createDepartment(usrObj.value).subscribe(
      (data) => {
        this.goToDepartmentList();
      },
      (error: HttpErrorResponse) => {
        const errorDetails = error.error.errorDetails;
        alert(errorDetails);
        console.log(errorDetails);
      }
    );
  }

  // Method to navigate to the department list page
  goToDepartmentList(): void {
    this.router.navigate(['/employees']);
  }

  // Method called when the form is submitted
  onSubmit(usrObj: any): void {
    console.log(usrObj.value);
    this.savingDepartment(usrObj);
    usrObj.reset();
  }
}
