import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { EmployeeHrms } from 'src/ServicesREST/EmployeeHrms';

@Component({
  selector: 'app-m-emp-details',
  templateUrl: './m-emp-details.component.html',
  styleUrls: ['./m-emp-details.component.css']
})
export class MEmpDetailsComponent implements OnInit {

  employeeHrms: EmployeeHrms[] = [];
  pagedEmployeeHrms: EmployeeHrms[] = [];
  showEmployeesTable: boolean = false;
  pageSize: number = 5; // Number of items to display per page
  pageSizeOptions: number[] = [5]; // Options for number of items per page
  totalEmployees: number = 0; // Total number of employees

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.getManagerEmployees();
  }

  // Method to get the authorization token from local storage
  getToken = () => {
    const token = localStorage.getItem("token");
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }

  // Method to get the employees managed by the manager
  getManagerEmployees() {
    const headers = this.getToken();

    // Send a GET request to the API endpoint to fetch the manager's employees
    this.http.get<any[]>('http://localhost:9091/api/v1/employeehrmsconsumer/manager', { headers }).subscribe(
      (response: any) => {
        this.employeeHrms = response;
        this.totalEmployees = this.employeeHrms.length;
        this.updatePagedEmployees(0); // Initial page, starting from index 0
        console.log(response);
        console.log(this.employeeHrms);
      },
      (error: HttpErrorResponse) => {
        const errorDetails = error.error.errorDetails;
        alert(errorDetails);
        console.log(errorDetails);
      }
    );
  }

  // Method to handle page change event
  onPageChange(event: any) {
    const pageIndex = event.pageIndex;
    this.updatePagedEmployees(pageIndex);
  }

  // Method to update the paged employees based on the current page index
  updatePagedEmployees(pageIndex: number) {
    const startIndex = pageIndex * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.pagedEmployeeHrms = this.employeeHrms.slice(startIndex, endIndex);
  }
}
