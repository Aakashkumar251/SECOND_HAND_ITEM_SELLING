import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class ChatService {

  private apiUrl = 'http://localhost:8088/api/messages';

  constructor(private http: HttpClient) {}

  private getHeaders() {
    const token = localStorage.getItem('token');
    return new HttpHeaders({ 'Authorization': `Bearer ${token}` });
  }

  sendMessage(data: any) {
    return this.http.post<any>(`${this.apiUrl}/send`, data, {
      headers: this.getHeaders()
    });
  }

  getConversation(productId: number, otherUserId: number) {
    return this.http.get<any[]>(
      `${this.apiUrl}/conversation?productId=${productId}&otherUserId=${otherUserId}`,
      { headers: this.getHeaders() }
    );
  }

  getInbox() {
    return this.http.get<any[]>(`${this.apiUrl}/inbox`, {
      headers: this.getHeaders()
    });
  }
}