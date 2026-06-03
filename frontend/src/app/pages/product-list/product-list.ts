import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ProductService } from '../../services/product';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './product-list.html'
})
export class ProductListComponent implements OnInit {

  products: any[] = [];
  keyword = ''; // This property is used to bind the search input field in the template.
  //  When the user types a keyword in the search box, it will be stored in this property, 
  // and when the user clicks the search button, this keyword will be sent to the backend to search for products that match the keyword.
  message = '';// This property is used to display messages to the user, 
  // such as error messages when loading products fails or when a search fails.

  constructor(private productService: ProductService) {}

  ngOnInit() {      // This method is called when the component is initialized. It is a good place to load data from the backend, in this case, we want to load the list of products when the page opens.
    this.loadProducts(); // This will call the loadProducts method, which will send a request to the backend to get all products and display them on the page.
  }

                                                       // Page Open
                                                       //    ↓
                                                       // ngOnInit()
                                                       //    ↓
                                                       // loadProducts()
                                                       //    ↓
                                                       // Backend API Call
                                                       //    ↓
                                                       // Products Display
  
  
  



  loadProducts() {       // This method is responsible for loading the list of products from the backend. 
                        // It uses the ProductService to send a request to the backend and get the data. 
                        // The response is handled using subscribe, where we set the products property with the data received from the backend,
                        //  or set an error message if the request fails.
    this.productService.getAllProducts().subscribe({
      next: (data) => this.products = data,// When the request is successful, the next function is called with the data received from 
      // the backend. We set the products property with this data, which will be used in the template to display the list of products.   
      error: () => this.message = 'Failed to load products.'
    });
  }

  onSearch() {
    if (this.keyword.trim() === '') { // If the search keyword is empty, reload all products
      this.loadProducts();
      return;
    }
    this.productService.searchProducts(this.keyword).subscribe({ // This method is called when the user clicks the search button.
    //  It sends a request to the backend to search for products based on the keyword entered by the user. 
    // The response is handled using subscribe, where we set the products property with the data received from the backend, 
    // or set an error message if the request fails.
      next: (data) => this.products = data,    
      error: () => this.message = 'Search failed.'
    });
  }
}