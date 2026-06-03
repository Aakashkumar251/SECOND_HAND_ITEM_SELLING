import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login';
import { RegisterComponent } from './pages/register/register';
import { HomeComponent } from './pages/home/home';
import { PostItemComponent } from './pages/post-item/post-item';
import { ProductListComponent } from './pages/product-list/product-list';
import { ProductDetailComponent } from './pages/product-detail/product-detail';
// import { ProductDetailComponent } from './pages/product-detail/product-detail.component';
import { ProfileComponent } from './pages/profile/profile';
import { MyListingsComponent } from './pages/my-listings/my-listings';

export const routes: Routes = [ // This array defines the routing configuration for the Angular application. 
// Each object in the array represents a route, which consists of a path and a component.
   { path: '', component: ProductListComponent }, // Set ProductListComponent as the default route
   { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent }, // When the user navigates to '/register', the RegisterComponent will be displayed.
  { path: 'home', component: HomeComponent }, // When the user navigates to '/home', the HomeComponent will be displayed.
    { path: 'post-item', component: PostItemComponent }, // When the user navigates to '/post-item', the PostItemComponent will be displayed.
  { path: 'product/:id', component: ProductDetailComponent }, // When the user navigates to '/product/:id', where :id is a placeholder for the product ID, the ProductDetailComponent will be displayed.
    { path: 'profile', component: ProfileComponent },
  { path: 'my-listings', component: MyListingsComponent }



];