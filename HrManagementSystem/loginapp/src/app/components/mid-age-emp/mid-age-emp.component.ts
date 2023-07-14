import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-mid-age-emp',
  templateUrl: './mid-age-emp.component.html',
  styleUrls: ['./mid-age-emp.component.css']
})
export class MidAgeEmpComponent {

  employees!: any[];
  currentPage = 0;
  pageSize = 100;

  constructor(private http: HttpClient) {}

  // Method to get the authorization token from local storage
  getToken = () => {
    const token = localStorage.getItem("token");
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }

  // Method to get employees in the middle age range
  getMidAgeEmployees() {
    const headers = this.getToken();

    // Set the query parameters for pagination
    const params = new HttpParams()
      .set('page', this.currentPage.toString())
      .set('size', this.pageSize.toString());

    // Send a GET request to the API endpoint to fetch the mid-age employees
    this.http.get<any[]>('http://localhost:9091/api/v1/employeehrmsconsumer/midageemp', { params, headers }).subscribe(
      employees => {
        this.employees = employees;
      },
      (error: HttpErrorResponse) => {
        const errorDetails = error.error.errorDetails;
        alert(errorDetails);
        console.log(errorDetails);
      }
    );
  }

  // Method to navigate to the next page of employees
  nextPage() {
    this.currentPage++;
    this.getMidAgeEmployees();
  }

  // Method to navigate to the previous page of employees
  previousPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.getMidAgeEmployees();
    }
  }
}
