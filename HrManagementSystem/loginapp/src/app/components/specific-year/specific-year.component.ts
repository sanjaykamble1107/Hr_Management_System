import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-specific-year',
  templateUrl: './specific-year.component.html',
  styleUrls: ['./specific-year.component.css']
})
export class SpecificYearComponent {
  deptNo: string = '';
  fromYear!: number;
  employees: any[] = [];
  pagedEmployees: any[] = [];
  pageSize: number = 5; // Number of items to display per page
  pageSizeOptions: number[] = [5]; // Options for number of items per page
  totalEmployees: number = 0; // Total number of employees

  constructor(private http: HttpClient) {}

  // Method to get the authorization token from local storage
  getToken = () => {
    const token = localStorage.getItem("token");
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }

  fetchEmployees() {
    if (!this.validateInputs()) {
      // Inputs are not valid, handle the error
      return alert("Id and year can't be blank");
    }

    const url = `http://localhost:9091/api/v1/employeehrmsconsumer/employees/department/${this.deptNo}/year/${this.fromYear}`;
    const headers = this.getToken();

    this.http.get<any[]>(url, { headers }).subscribe(
      (data) => {
        this.employees = data;
        this.totalEmployees = this.employees.length;
        this.updatePagedEmployees(0); // Initial page, starting from index 0
        console.log(this.employees);
      },
      (error: HttpErrorResponse) => {
        const errorDetails = error.error.errorDetails;
        alert(errorDetails);
        console.log(errorDetails);
      }
    );
  }

  validateInputs(): boolean {
    if (!this.deptNo || !this.fromYear) {
      // Department number or year is blank
      return false;
    }

    if (typeof this.fromYear !== 'number') {
      // Year is not a valid number
      return false;
    }

    // Additional validation logic if needed

    return true;
  }

  onPageChange(event: any) {
    const pageIndex = event.pageIndex;
    this.updatePagedEmployees(pageIndex);
  }

  updatePagedEmployees(pageIndex: number) {
    const startIndex = pageIndex * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.pagedEmployees = this.employees.slice(startIndex, endIndex);
  }
}
