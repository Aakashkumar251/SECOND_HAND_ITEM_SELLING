import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ProductService } from '../../services/product';

@Component({
  selector: 'app-my-listings',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './my-listings.html',
  styleUrl: './my-listings.css'
})
export class MyListingsComponent implements OnInit {

  listings: any[] = [];
  message = '';

  constructor(private productService: ProductService) {}

  ngOnInit() {
    this.loadListings();
  }

  loadListings() {
    this.productService.getMyListings().subscribe({
      next: (data : any[]) => this.listings = data,
      error: () => this.message = 'Please login to see your listings.'
    });
  }

  markSold(id: number) {
    this.productService.markAsSold(id).subscribe({
      next: () => this.loadListings(), // refresh list
      error: () => this.message = 'Failed to update.'
    });
  }

  deleteItem(id: number) {
    if (confirm('Are you sure you want to delete this listing?')) {
      this.productService.deleteProduct(id).subscribe({
        next: () => this.loadListings(), // refresh list
        error: () => this.message = 'Delete failed.'
      });
    }
  }
}