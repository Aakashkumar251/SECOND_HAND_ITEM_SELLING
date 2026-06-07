import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class WishlistService {

  private apiUrl = 'http://localhost:8088/api/wishlist';

  constructor(private http: HttpClient) {}

  // private getHeaders() {
  //   const token = localStorage.getItem('token');
  //   return new HttpHeaders({ 'Authorization': `Bearer ${token}` });
  // }

  toggleWishlist(productId: number) {
    return this.http.post<string>(
      `${this.apiUrl}/${productId}`, {},
      
    );
  }

  getWishlist() {
    return this.http.get<any[]>(this.apiUrl);
  }
}