import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ProductService } from '../../services/product';
import { ImageService } from '../../services/image';

@Component({
  selector: 'app-post-item',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './post-item.html',
  styleUrl: './post-item.css'
})
export class PostItemComponent {

  product = {
    title: '',
    description: '',
    price: null,
    location: '',
    category: '',
     imageUrls: [] as string[]
  };

  categories = ['ELECTRONICS', 'FURNITURE', 'CLOTHING', 'VEHICLES', 'BOOKS', 'SPORTS', 'OTHER'];
  message = '';
   uploading = false; // shows loading while uploading


  constructor(private productService: ProductService,
       private imageService: ImageService,
     private router: Router) {}

  addImageField() {   // basically this method is used to add more image input fields in the form. When the user clicks on "Add Another Image", this method will be called and it will add an empty string to the imageUrls array, which will create a new input field for the user to enter another image URL.
    this.product.imageUrls.push(''); // add another image input so that user can add more images. This will add an empty string to the imageUrls array, which will create a new input field in the form for the user to enter another image URL.
  }

  // onSubmit() {
  //   // Remove empty image urls
  //   this.product.imageUrls = this.product.imageUrls.filter(url => url.trim() !== '');  //remove empty image URLs before submitting. This will filter out any empty strings from the imageUrls array, so that only valid URLs are sent to the backend.

  //   this.productService.createProduct(this.product).subscribe({
  //     next: () => {
  //       this.message = 'Product posted successfully!';
  //       setTimeout(() => this.router.navigate(['/']), 1500);// Redirect to home after a short delay to show the success message.
  //     },
  //     error: () => {
  //       this.message = 'Failed to post. Please login first.';
  //     }
  //   });
  // }




   // When user picks a file
  onFileSelected(event: any) { // event means the file input change event, which contains information about the selected files. 
  // When the user selects one or more files using the file input, this method will be called with the event object 
  // that contains details about the selected files.
    const files: FileList = event.target.files; // This line retrieves the list of selected files from the event object.
    //  event.target refers to the file input element, and .files is a property that contains a FileList object 
    // representing the selected files.
                       event
  //  └── target
        // └── files
              // ├── phone.jpg
              // ├── laptop.jpg
              // └── watch.jpg
// 
    this.uploading = true;
    this.message = 'Uploading images...';  // disable the submit button

    let uploadCount = 0;

    // Upload each selected file
    Array.from(files).forEach(file => {
      this.imageService.uploadImage(file).subscribe({ // This line calls the uploadImage method of the ImageService for each selected file.
      //  The uploadImage method is responsible for uploading the file to the backend and returning an observable 
      // that we can subscribe to in order to get the response from the server.
        next: (res) => {
          this.product.imageUrls.push(res.url); // save cloudinary url
          uploadCount++;
          if (uploadCount === files.length) {
            this.uploading = false;
            this.message = `${files.length} image(s) uploaded ✅`;
          }
        },
        error: () => {
          this.uploading = false;
          this.message = 'Image upload failed.';
        }
      });
    });
  }



  onSubmit() {
    if (this.product.imageUrls.length === 0) {
      this.message = 'Please upload at least one image.';
      return;
    }

    this.productService.createProduct(this.product).subscribe({
      next: () => {
        this.message = 'Product posted successfully!';
        setTimeout(() => this.router.navigate(['/']), 1500);
      },
      error: () => {
        this.message = 'Failed to post. Please login first.';
      }
    });
  }
}
