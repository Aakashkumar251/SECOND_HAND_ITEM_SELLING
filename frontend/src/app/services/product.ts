import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl = 'http://localhost:8088/api/products';

  constructor(private http: HttpClient) {}            // is used to send HTTP requests.

  // private getHeaders() {
  //   const token = localStorage.getItem('token');                                                                                                     
  //   return new HttpHeaders({                                                  
  //     'Authorization': `Bearer ${token}`                                                  
  //   });                                                  
  // }          
                                    // so bar bar ye  na likhna paray ke har request me token bhejna hai, h 
                                    // um ek method bana lete hain getHeaders, jo ke token ko localStorage se uthata 
                                    // hai aur usko HTTP headers me set karta hai.
  
  // createProduct(data: any) {
//   const token = localStorage.getItem('token');

//   return this.http.post(`${this.apiUrl}/create`, data, {
//     headers: new HttpHeaders({
//       'Authorization': `Bearer ${token}`
//     })
//   });
// }




// 
  // Add JWT token to every request                                                




                                        //Observable = Promise that data may arrive later
  getAllProducts() {
    return this.http.get<any[]>(`${this.apiUrl}/all`);   //asking for products

  }

  getProductById(id: number) {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  searchProducts(keyword: string) {
    return this.http.get<any[]>(`${this.apiUrl}/search?keyword=${keyword}`);
  }

  createProduct(data: any) {       //The method returns an Observable.
    return this.http.post<any>(`${this.apiUrl}/create`, data  //The request is sent when you subscribe: it will send by the post-item componenet
      );
  }

  getMyListings() {
    return this.http.get<any[]>(`${this.apiUrl}/my-listings`);
  }

  markAsSold(id: number) {
    return this.http.put<any>(`${this.apiUrl}/${id}/sold`, {});
  }

  deleteProduct(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
  filterProducts(category: string, minPrice: number,
               maxPrice: number, location: string) {
  return this.http.get<any[]>(
    `${this.apiUrl}/filter?category=${category}&minPrice=${minPrice}&maxPrice=${maxPrice}&location=${location}`
  );
}
}