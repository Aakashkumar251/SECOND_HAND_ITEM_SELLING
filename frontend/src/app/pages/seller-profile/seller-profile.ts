import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ReviewService } from '../../services/review';
import { ProductService } from '../../services/product';

@Component({
  selector: 'app-seller-profile',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './seller-profile.html'
})
export class SellerProfileComponent implements OnInit {

  sellerId: number = 0;
  reviews: any[] = [];
  avgRating: number = 0;
  message = '';

  newReview = { sellerId: 0, rating: 5, comment: '' };

  constructor(
    private reviewService: ReviewService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.sellerId = Number(id);
      this.newReview.sellerId = this.sellerId;
      this.loadReviews();
    }
  }

  loadReviews() {
    this.reviewService.getSellerReviews(this.sellerId).subscribe({
      next: (data) => this.reviews = data
    });
    this.reviewService.getAverageRating(this.sellerId).subscribe({
      next: (data) => this.avgRating = data
    });
  }

  submitReview() {
    this.reviewService.addReview(this.newReview).subscribe({
      next: () => {
        this.message = 'Review submitted!';
        this.newReview.comment = '';
        this.loadReviews();
      },
      error: () => this.message = 'Please login to review.'
    });
  }

  stars(n: number): string {
    return '⭐'.repeat(n);
  }
}