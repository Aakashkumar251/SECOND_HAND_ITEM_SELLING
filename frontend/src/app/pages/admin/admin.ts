import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin.html'
})
export class AdminComponent implements OnInit {

  stats: any = {};
  users: any[] = [];
  products: any[] = [];
  activeTab = 'stats';
  message = '';

  private apiUrl = 'http://localhost:8088/api/admin';

  constructor(private http: HttpClient) {}

  private getHeaders() {
    const token = localStorage.getItem('token');
    return new HttpHeaders({ 'Authorization': `Bearer ${token}` });
  }

  ngOnInit() {
    this.loadStats();
  }

  loadStats() {
    this.http.get<any>(`${this.apiUrl}/stats`, {
      headers: this.getHeaders()
    }).subscribe({ next: (data) => this.stats = data });
  }

  loadUsers() {
    this.http.get<any[]>(`${this.apiUrl}/users`, {
      headers: this.getHeaders()
    }).subscribe({ next: (data) => this.users = data });
  }

  loadProducts() {
    this.http.get<any[]>(`${this.apiUrl}/products`, {
      headers: this.getHeaders()
    }).subscribe({ next: (data) => this.products = data });
  }

  switchTab(tab: string) {
    this.activeTab = tab;
    if (tab === 'users') this.loadUsers();
    if (tab === 'products') this.loadProducts();
  }

  deleteUser(id: number) {
    if (confirm('Delete this user?')) {
      this.http.delete(`${this.apiUrl}/users/${id}`, {
        headers: this.getHeaders()
      }).subscribe({ next: () => this.loadUsers() });
    }
  }

  deleteProduct(id: number) {
    if (confirm('Delete this product?')) {
      this.http.delete(`${this.apiUrl}/products/${id}`, {
        headers: this.getHeaders()
      }).subscribe({ next: () => this.loadProducts() });
    }
  }
}