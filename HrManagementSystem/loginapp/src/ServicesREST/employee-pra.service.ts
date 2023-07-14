import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeePraService {
  getToken = () => {
    const token = localStorage.getItem("token");
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }  
  private apiUrl = 'http://localhost:9091/api/v1/employees';

  constructor(private http: HttpClient) {}

  getEmployeeDetails(): Observable<any[]> {
    const headers = this.getToken();
    return this.http.get<any[]>(`${this.apiUrl}`,{headers});
  }

  updateEmployeeLastName(empno: number, lastName: string): Observable<any> {
    const headers = this.getToken();
    return this.http.put<any>(`${this.apiUrl}/lastname/${empno}`, { lastName }, { headers });
  }
  
}

