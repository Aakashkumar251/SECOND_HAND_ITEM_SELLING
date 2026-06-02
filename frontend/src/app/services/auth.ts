import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

// is ka matlab hai ke hum sirf ek object banyenge aur isko app ke andar kahi bhi inject kar sakte hain. ya use kar sakte hain.
@Injectable({
  providedIn: 'root',
})
export class AuthService {
   private apiUrl = 'http://localhost:8088/api/auth';
   constructor(private http: HttpClient) {
    // HttpClient ko inject karna zaroori hai taake hum HTTP requests bhej saken.
   // Angular automatically gives you an HttpClient object.
   }

   register(data : any){
    return this.http.post(`${this.apiUrl}/register`,data);
   }

   login(data : any){
    return this.http.post(`${this.apiUrl}/login`,data);
   }

   saveToken(token:string){
    localStorage.setItem('token',token);
   }

   isLoggedIn() : boolean {
    return !!localStorage.getItem('token');
   }

    logout() {
    localStorage.removeItem('token');
  }
}
