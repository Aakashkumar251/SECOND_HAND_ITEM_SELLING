import { Component } from '@angular/core';
import { AuthService } from '../../services/auth';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormsModule,RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class LoginComponent {
  credentials={
    email:"",
    password:""
  };

  msg="";

    constructor(private authService: AuthService, private router: Router) {}

   onlogin(){
    this.authService.login(this.credentials).subscribe({
      next:(respose:any)=>{
        this.authService.saveToken(respose.token);
        this.msg="Login successful!";
        this.router.navigate(['/']);
      },
      error: (err) => {
        this.msg = 'Login failed. Try again.';
      }
    });
   }
}
