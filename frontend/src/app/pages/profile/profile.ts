import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user';
import { ImageService } from '../../services/image';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './profile.html',
  styleUrl: './profile.css'
})
export class ProfileComponent implements OnInit {

  profile: any = null;
  editMode = false;  // toggle edit form
  message = '';

  updatedData = {
    name: '',
    phone: '',
    profileImage: ''
  };

  constructor(
    private userService: UserService,
    private imageService: ImageService
  ) {}

  ngOnInit() {
    this.loadProfile();
  }

  loadProfile() {
    this.userService.getProfile().subscribe({
      next: (data) => {
        this.profile = data;
        // pre-fill edit form
        this.updatedData.name = data.name;
        this.updatedData.phone = data.phone;
        this.updatedData.profileImage = data.profileImage;
      },
      error: () => this.message = 'Please login to view profile.'
    });
  }

  // Upload new profile picture
  onProfileImageSelected(event: any) {
    const file = event.target.files[0];
    this.imageService.uploadImage(file).subscribe({
      next: (res) => {
        this.updatedData.profileImage = res.url;
        this.message = 'Profile picture uploaded ✅';
      },
      error: () => this.message = 'Image upload failed.'
    });
  }

  saveProfile() {
    this.userService.updateProfile(this.updatedData).subscribe({
      next: (data) => {
        this.profile = data;
        this.editMode = false;
        this.message = 'Profile updated successfully!';
      },
      error: () => this.message = 'Update failed.'
    });
  }
}