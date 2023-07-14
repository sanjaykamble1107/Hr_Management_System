import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { EmployeePraService } from 'src/ServicesREST/employee-pra.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {
  employeeId!: number;
  newLastName!: string;
  employees: any[] = [];

  constructor(private employeeService: EmployeePraService) {}

  ngOnInit() {
    this.fetchEmployees();
  }

  // Method to fetch employee details
  fetchEmployees() {
    this.employeeService.getEmployeeDetails().subscribe(
      employees => {
        this.employees = employees;
        console.log(employees);
      }
    );
  }

  // Method to update the last name of an employee
  updateLastName() {
    this.employeeService.updateEmployeeLastName(this.employeeId, this.newLastName).subscribe(
      () => {
        console.log('Last name updated successfully.');
        this.fetchEmployees(); // Fetch the updated employee details
        // this.showSuccessMessage('Update successful');
        alert("last name updated successfully")
      },
      (error: HttpErrorResponse) => {
        const errorDetails = error.error.errorDetails;
        alert(errorDetails);
        console.log(errorDetails);
      }
    );
  }
}
