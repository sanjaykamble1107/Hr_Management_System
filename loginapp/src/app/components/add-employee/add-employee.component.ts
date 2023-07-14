import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from 'src/ServicesREST/employee';
import { EmployeeService } from 'src/ServicesREST/employee.service';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  employee: Employee = new Employee();

  constructor(private employeeService: EmployeeService, private router: Router) { }

  ngOnInit(): void { }

  // Method to save an employee
  saveEmployee(employee: Employee): void {
    this.employeeService.createEmployee(employee).subscribe(
      data => {
        console.log(data);
        this.goToEmployeeList();
      },
      (error: HttpErrorResponse) => {
        const errorDetails = error.error.errorDetails;
        alert(errorDetails);
        console.log(errorDetails);
      }
    );
  }

  // Method to navigate to the employee list page
  goToEmployeeList(): void {
    this.router.navigate(['/dashboard']);
  }

  // Method called when the form is submitted
  onSubmit(userForm: any): void {
    // Assign form values to the employee object
    this.employee.employeeNumber = userForm.value.empNo;
    this.employee.birthDate = userForm.value.birthDate;
    this.employee.firstName = userForm.value.firstName;
    this.employee.lastName = userForm.value.lastName;
    this.employee.gender = userForm.value.gender;
    this.employee.hireDate = userForm.value.hireDate;

    // Save the employee
    this.saveEmployee(this.employee);

    // Reset the form
    userForm.reset();

    // Display a success message
    alert("Employee Added");

    console.log(this.employee);
  }
}
