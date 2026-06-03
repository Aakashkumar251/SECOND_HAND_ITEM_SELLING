import { Injectable } from '@angular/core'; // This line imports the Injectable decorator from the Angular core library.
//  The Injectable decorator is used to mark a class as available to be provided and injected as a dependency
//  in other parts of the application.
import {HttpClient} from "@angular/common/http"; // This line imports the HttpClient service from the Angular common HTTP library.
// HttpClient is used to make HTTP requests to the backend API. It provides methods for making GET, POST, PUT, DELETE requests and more.

// is ka matlab hai ke hum sirf ek object banyenge aur isko app ke andar kahi bhi inject kar sakte hain. ya use kar sakte hain.
@Injectable({
  providedIn: 'root', // This means that the AuthService will be provided at the root level of the application,
  //  making it a singleton service that can be injected anywhere in the app.
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
