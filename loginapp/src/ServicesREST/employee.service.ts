import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';
@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private apiUrl = "http://localhost:9091/api/v1";
  // private assignapiUrl = 'http://localhost:8080/assigndept';
  constructor(private http: HttpClient) { }

  // getAllEmployees(): Observable<Employee[]> {
  //   return this.http.get<Employee[]>(`${this.apiUrl}/employees`);
  // }

  getToken = () => {
    const token = localStorage.getItem("token");
    return new HttpHeaders().set('Authorization', `Bearer ${token}`)
  }
  getEmployeesJoinedLastYears(years: number): Observable<Employee[]> {
    const headers = this.getToken();
    return this.http.get<Employee[]>(`${this.apiUrl}/adminhrmsconsumer/employees/${years}`, { headers });
  }
  getCountEmployeesJoinedLast10Years(): Observable<number> {
    const headers = this.getToken();
    return this.http.get<number>(`${this.apiUrl}/adminhrmsconsumer/employees/count/10`, { headers });
  }
  getEmployeesJoinedAfter2005(): Observable<Employee[]> {
    const headers = this.getToken();
    return this.http.get<Employee[]>(`${this.apiUrl}/adminhrmsconsumer/employees/year/2005`,{ headers });
  }

  getCountEmployeesJoinedAfter2005(): Observable<number> {
    const headers = this.getToken();
    return this.http.get<number>(`${this.apiUrl}/adminhrmsconsumer/employees/count/year/2005`,{ headers });
  }
  getCountWomenEmployees(): Observable<number> {
    const headers = this.getToken();
    return this.http.get<number>(`${this.apiUrl}/adminhrmsconsumer/employee/gender/F/count`,{ headers });
  }

  // getWomenEmployees(): Observable<Employee[]> {
  //   return this.http.get<Employee[]>(`${this.apiUrl}/women-employees`);
  // }

  getWomenEmployees(pageWomen: number, sizeWomen: number): Observable<any> {
    const headers = this.getToken();
    let params = new HttpParams()
      .set('page', String(pageWomen))
      .set('size', String(sizeWomen));
    return this.http.get<Employee[]>(`${this.apiUrl}/adminhrmsconsumer/employee/gender/F`, { params,headers});
  }


  createEmployee(employee: Employee): Observable<Object> {
    const headers = this.getToken();
    return this.http.post(`${this.apiUrl}/adminhrmsconsumer/employee`, employee,{ headers });
  }


  //update rating Employees
  updateRatingEmployee(employeeId: number, rating: number, promoted: boolean) {
    const headers = this.getToken();
    const url = (`${this.apiUrl}/adminhrmsconsumer/salaries/${employeeId}`);
    const payload = {
      rating: rating,
      promoted: promoted
    };
    return this.http.put(url, payload,{ headers });
  }


  getallemployees(page: number, size: number): Observable<any> {
    const headers = this.getToken();
    let params = new HttpParams()
      .set('page', String(page))
      .set('size', String(size));
    return this.http.get<Employee[]>(`${this.apiUrl}/employees/getallemployees`, { params, headers });
  }

  saveDeptEmp(dto: any): Observable<string> {
    const headers = this.getToken();
    return this.http.post<string>("http://localhost:9091/api/v1/adminhrmsconsumer/assigndept", dto,{ headers });
  }
}

