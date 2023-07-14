import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ManagerAssignmentService {
  private baseUrl = 'http://localhost:9091/api/v1/adminhrmsconsumer';
  
  constructor(private http: HttpClient) {}

  getToken(): HttpHeaders {
    const token = localStorage.getItem("token");
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }

  assignManager(requestData: any): Observable<any> {
    const headers = this.getToken();
    const url = `${this.baseUrl}/assignmgr`;
    return this.http.post(url, requestData, { headers: headers });
  }
}
