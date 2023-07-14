import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/ServicesREST/employee';
import { EmployeeService } from 'src/ServicesREST/employee.service';

@Component({
  selector: 'app-findemployees',
  templateUrl: './findemployees.component.html',
  styleUrls: ['./findemployees.component.css']
})
export class FindemployeesComponent implements OnInit {

  employees: Employee[] = [];
  count!: number;
  employeesJoinedAfter2005: Employee[] = [];
  countEmployeesJoinedAfter2005!: number;
  countWomenEmployees!: number;
  pagination: number = 1;
  page!: number;
  size!: number;
  pageWomen!: number;
  sizeWomen!: number;
  isLastPage!: boolean;
  womenemp: Employee[] = [];

  constructor(private employeeService: EmployeeService) {
    this.page = 0;
    this.size = 30;
    this.pageWomen = 0;
    this.sizeWomen = 20;
  }

  ngOnInit(): void {
    this.getallemployees();
    this.getWomenEmployees();
  }

  // Method to get all employees
  getallemployees() {
    this.employeeService.getallemployees(this.page, this.size).subscribe((data) => {
      this.employees = data.content;
      this.isLastPage = data.last;
      console.log(data);
    });
  }

  // Method to navigate to the next page of employees
  nextPage() {
    this.page++;
    this.getallemployees();
  }

  // Method to navigate to the previous page of employees
  previousPage() {
    this.page--;
    this.getallemployees();
  }

  // Method to get women employees
  getWomenEmployees() {
    this.employeeService.getWomenEmployees(this.pageWomen, this.sizeWomen).subscribe((data: any) => {
      this.womenemp = [...data];
      this.isLastPage = data.last;
      console.log(this.womenemp);
    });
  }

  // Method to navigate to the next page of women employees
  nextWomwenPage() {
    this.pageWomen++;
    this.getWomenEmployees();
  }

  // Method to navigate to the previous page of women employees
  previousWomenPage() {
    this.pageWomen--;
    this.getWomenEmployees();
  }

  // Method to get employees who joined in the last specified number of years
  getEmployeesJoinedLastYears(years: number): void {
    this.employeeService.getEmployeesJoinedLastYears(years)
      .subscribe(
        (employees: Employee[]) => {
          this.employees = employees;
          if (this.employees.length === 0) {
            alert('No employees found.');
            console.log('Not able to retrieve data');
          }
        },
        (error: HttpErrorResponse) => {
          const errorDetails = error.error.errorDetails;
          alert(errorDetails);
          console.log(errorDetails);
        }
      );
  }

  // Method to get the count of employees who joined in the last 10 years
  getCount() {
    this.employeeService.getCountEmployeesJoinedLast10Years()
      .subscribe(
        (count: number) => {
          this.count = count;
        },
        (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
          console.log(errorMessage);
        }
      );
  }

  // Method to get employees who joined after 2005
  getEmployeesJoinedAfter2005() {
    this.employeeService.getEmployeesJoinedAfter2005()
      .subscribe(
        (employees: Employee[]) => {
          this.employeesJoinedAfter2005 = employees;
        },
        (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
          console.log(errorMessage);
        }
      );
  }

  // Method to get the count of employees who joined after 2005
  getCountEmployeesJoinedAfter2005() {
    this.employeeService.getCountEmployeesJoinedAfter2005()
      .subscribe(
        (count: number) => {
          this.countEmployeesJoinedAfter2005 = count;
        },
        (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
          console.log(errorMessage);
        }
      );
  }

  // Method to get the count of women employees
  getCountWomenEmployees() {
    this.employeeService.getCountWomenEmployees()
      .subscribe(
        (count: number) => {
          this.countWomenEmployees = count;
        },
        (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
          console.log(errorMessage);
        }
      );
  }
}
