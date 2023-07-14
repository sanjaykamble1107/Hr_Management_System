import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { designationName } from 'src/ServicesREST/designationName';

@Component({
  selector: 'app-designation',
  templateUrl: './designation.component.html',
  styleUrls: ['./designation.component.css']
})
export class DesignationComponent {

  designations: designationName[] = [];

  constructor(private http: HttpClient) {}

  // Method to get the authorization token from local storage
  getToken = () => {
    const token = localStorage.getItem("token");
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }

  // Method to get the employee designations
  getEmployeeDesignations() {
    const headers = this.getToken();

    // Send a GET request to the API endpoint to fetch the employee designations
    this.http.get<any[]>('http://localhost:9091/api/v1/employeehrmsconsumer/designations', { headers }).subscribe(
      (response: any) => {
        this.designations = response;
        console.log(response);
        console.log(this.designations);
      },
      (error: HttpErrorResponse) => {
        const errorDetails = error.error.errorDetails;
        alert(errorDetails);
        console.log(errorDetails);
      }
    );
  }
}
