import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { WishlistService } from '../../services/wishlist';

@Component({
  selector: 'app-wishlist',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './wishlist.html',
  styleUrl: './wishlist.css'
})
export class WishlistComponent implements OnInit {

  items: any[] = [];
  message = '';

  constructor(private wishlistService: WishlistService) {}

  ngOnInit() {
    this.wishlistService.getWishlist().subscribe({
      next: (data) => this.items = data,
      error: () => this.message = 'Please login to view wishlist.'
    });
  }

  remove(productId: number) {
    this.wishlistService.toggleWishlist(productId).subscribe({
      next: () => {
        this.items = this.items.filter(i => i.id !== productId);
      }
    });
  }
}