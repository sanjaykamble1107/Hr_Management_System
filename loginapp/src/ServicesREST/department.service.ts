import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Departments } from './departments';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {
  private apiUrl = 'http://localhost:9091/api/v1';

  constructor(private http: HttpClient) { }
  getToken = () => {
    const token = localStorage.getItem("token");
    return new HttpHeaders().set('Authorization', `Bearer ${token}`)
  }

  // Method to get all departments
  getAllDepartment(): Observable<Departments[]> {
    const headers = this.getToken();

    return this.http.get<Departments[]>(this.apiUrl,{headers});
  }

  // Method to create a new department
  createDepartment(department: Departments): Observable<Object> {
    const headers = this.getToken();
    return this.http.post("http://localhost:9091/api/v1/adminhrmsconsumer/department", department, { headers });
  }
  
}
