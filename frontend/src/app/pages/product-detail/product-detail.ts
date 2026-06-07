import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../../services/product';
import { WishlistService } from '../../services/wishlist';
import { ChatService } from '../../services/chat';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-product-detail',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './product-detail.html',
  styleUrl: './product-detail.css'
})
export class ProductDetailComponent implements OnInit {

  product: any = null;
  message = '';
  wishlistMessage = '';
   chatMessage = '';
  showChat = false;

  constructor(
    private productService: ProductService,
     private wishlistService: WishlistService,
         private chatService: ChatService,
    private route: ActivatedRoute  // reads the id from URL
  ) {}

//   ngOnInit() {    // This method is called when the component is initialized. It is a good place to load data from the backend,
//   //  in this case, we want to load the details of a specific product when the page opens.
//   //  We read the product ID from the URL using ActivatedRoute and then call the ProductService to get the 
//   // details of that product from the backend.
//     const id = this.route.snapshot.paramMap.get('id');// Read the product ID from the URL
//     if (id) {  // If there is an ID in the URL, we call the ProductService to get the details of that product from the backend.
//       this.productService.getProductById(Number(id)).subscribe({
//         next: (data) => this.product = data,
//         error: () => this.message = 'Product not found.'
//       });
//     }
//   }
// }


ngOnInit() {
  this.route.paramMap.subscribe(params => {  // This line subscribes to changes in the route parameters. It allows the component to
  //  react to changes in the URL, such as when a user navigates to a different product detail page without reloading the entire component.
    const id = params.get('id');
    console.log('Product ID:', id);

    if (id) {  // If there is an ID in the URL, we call the ProductService to get the details of that product from the backend.
      this.product = null; // old data clear
      this.productService.getProductById(Number(id)).subscribe({
        next: (data) => {
          console.log('Product data:', data);
          this.product = data;
        },
        error: () => {
          this.message = 'Product not found.';
        }
      });
    } else {  // If there is no ID in the URL, we set a message indicating that no product ID was found. 
    // This can happen if the user navigates to the product detail page without specifying an ID in the URL.
      this.message = 'No product ID found in URL.';
    }
  });
}


toggleWishlist() {
    this.wishlistService.toggleWishlist(this.product.id).subscribe({
      next: (msg: any) => this.wishlistMessage = msg,
      error: () => this.wishlistMessage = 'Please login first.'
    });
  }

  sendMessage() {
    this.chatService.sendMessage({
      receiverId: this.product.sellerId,
      productId: this.product.id,
      content: this.chatMessage
    }).subscribe({
      next: () => {
        this.chatMessage = '';
        this.message = 'Message sent to seller ✅';
        this.showChat = false;
      },
      error: () => this.message = 'Please login to contact seller.'
    });
  }


}