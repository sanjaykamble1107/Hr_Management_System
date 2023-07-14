import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmpManagerfromdateService {
  getToken = () => {
    const token = localStorage.getItem("token");
    return new HttpHeaders().set('Authorization', `Bearer ${token}`)
  }
  private apiUrl = 'http://localhost:9091/api/v1/employeehrmsconsumer';

  constructor(private http: HttpClient) {}

  getManagersFromDate(fromDate: string): Observable<any[]> {
    const headers = this.getToken();
    return this.http.get<any[]>(`${this.apiUrl}/manager/${fromDate}`,{headers});
  }
}

