import { Routes } from '@angular/router';
import { authGuard } from './guards/auth-guard';
import { ProductListComponent } from './pages/product-list/product-list';
import { LoginComponent } from './pages/login/login';
import { RegisterComponent } from './pages/register/register';
import { PostItemComponent } from './pages/post-item/post-item';
import { ProductDetailComponent } from './pages/product-detail/product-detail';
import { ProfileComponent } from './pages/profile/profile';
import { MyListingsComponent } from './pages/my-listings/my-listings';
import { WishlistComponent } from './pages/wishlist/wishlist';
import { InboxComponent } from './pages/inbox/inbox';
import { SellerProfileComponent } from './pages/seller-profile/seller-profile';
import { AdminComponent } from './pages/admin/admin';
import { HomeComponent } from './pages/home/home';
export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'products', component: ProductListComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'product/:id', component: ProductDetailComponent },
  { path: 'seller/:id', component: SellerProfileComponent },

  // Protected routes — need login
  { path: 'post-item', component: PostItemComponent, canActivate: [authGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [authGuard] },
  { path: 'my-listings', component: MyListingsComponent, canActivate: [authGuard] },
  { path: 'wishlist', component: WishlistComponent, canActivate: [authGuard] },
  { path: 'inbox', component: InboxComponent, canActivate: [authGuard] },
  { path: 'admin', component: AdminComponent, canActivate: [authGuard] },

  { path: '**', redirectTo: '' } // unknown routes go to home
];