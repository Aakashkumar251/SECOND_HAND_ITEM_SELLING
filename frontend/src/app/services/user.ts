import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8088/api/user';

  constructor(private http: HttpClient) {}

  // private getHeaders() {
  //   const token = localStorage.getItem('token');
  //   return new HttpHeaders({ 'Authorization': `Bearer ${token}` });
  // }

  getProfile() {
    return this.http.get<any>(`${this.apiUrl}/profile`);
  }

  updateProfile(data: any) {
    return this.http.put<any>(`${this.apiUrl}/profile`, data);
  }
}