import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = () => { // This function is a route guard that checks if the user is authenticated before allowing 
// access to certain routes in the Angular application.
  const router = inject(Router); // The Router service is injected to enable navigation to the login page if the user is not authenticated.
  const token = localStorage.getItem('token');  // The function checks for the presence of a token in the browser's local storage.

  if (token) {
    return true; // allow
  }

  router.navigate(['/login']); // redirect to login
  return false;
};