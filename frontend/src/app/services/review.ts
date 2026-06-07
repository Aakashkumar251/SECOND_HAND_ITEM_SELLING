import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class ReviewService {

  private apiUrl = 'http://localhost:8088/api/reviews';

  constructor(private http: HttpClient) {}

  // private getHeaders() {
  //   const token = localStorage.getItem('token');
  //   return new HttpHeaders({ 'Authorization': `Bearer ${token}` });
  // }

  addReview(data: any) {
    return this.http.post<any>(this.apiUrl, data);
  }

  getSellerReviews(sellerId: number) {
    return this.http.get<any[]>(`${this.apiUrl}/seller/${sellerId}`);
  }

  getAverageRating(sellerId: number) {
    return this.http.get<number>(`${this.apiUrl}/seller/${sellerId}/rating`);
  }
}