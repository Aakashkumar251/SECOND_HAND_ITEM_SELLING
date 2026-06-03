import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private apiUrl = 'http://localhost:8088/api/images';

  constructor(private http: HttpClient) {}

  uploadImage(file: File) { // Create FormData and append the file
    const formData = new FormData(); // Create FormData object
    formData.append('file', file); // key must match backend @RequestParam

    return this.http.post<any>(`${this.apiUrl}/upload`, formData);
  }
}