import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DeptnoAndfromdateService {

  private apiUrl = 'http://localhost:9091/api/v1/deptemp/deptno';

  constructor(private http: HttpClient) {}

  getEmployeesByDepartmentAndFromDate(deptNo: string, fromDate: string): Observable<any[]> {
    const url = `${this.apiUrl}/${deptNo}/fromdate/${fromDate}`;
    return this.http.get<any[]>(url);
  }
}

