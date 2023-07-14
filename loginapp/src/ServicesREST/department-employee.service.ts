import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DepartmentEmployeeService {
  getToken = () => {
    const token = localStorage.getItem("token");
    return new HttpHeaders().set('Authorization', `Bearer ${token}`)
  }
  private apiUrl = 'http://localhost:9091/api/v1/adminhrmsconsumer/assigndept'; // Replace with your backend API URL

  constructor(private http: HttpClient) {}

  // Method to save department-employee assignment
  saveDeptEmp(dto: any): Observable<string> {
    const headers = this.getToken();
    return this.http.post<string>(this.apiUrl, dto,{headers});
  }

  // Method to assign a title to an employee
  assignTitle(title: string, fromDate: Date, toDate: Date, empNo: number): Observable<string> {
    const dto = {
      title: title,
      fromDate: fromDate.toISOString().substring(0, 10),
      toDate: toDate.toISOString().substring(0, 10),
      empNo: empNo
    };
    const headers = this.getToken();


    return this.http.post<string>('http://localhost:9091/api/v1/adminhrmsconsumer/assigntitle', dto,{headers});
  }
}
