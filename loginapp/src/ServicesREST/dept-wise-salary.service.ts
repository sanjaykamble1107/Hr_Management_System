import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DeptWiseSalaryService {

  // Function to get token from local storage and set it in the headers
  getToken = () => {
    const token = localStorage.getItem("token");
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }

  private apiUrl = 'http://localhost:9091/api/v1/employeehrmsconsumer/department';

  constructor(private http: HttpClient) {}

  // Method to get department details
  getDepartmentDetails(): Observable<any[]> {
    const headers = this.getToken();
    return this.http.get<any[]>(this.apiUrl, { headers });
  }
}
