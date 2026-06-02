import { Component } from '@angular/core';
import { AuthService } from '../../services/auth';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-register',
  imports: [FormsModule,RouterLink],
  templateUrl: './register.html',  //Connects HTML file with this component.
  styleUrl: './register.css',
})
export class RegisterComponent {
  user={
    name:"",
    email:"",
    password:"",
    phone:""
  };

  msg="";

  constructor(private authService:AuthService,private router:Router){}
                     
               onRegister(){
                this.authService.register(this.user).subscribe( { // subscribe is used to handle the response from the register method of authService. It is an asynchronous operation, so we need to subscribe to it to get the response when it is available.
                                      //   Angular sends the request.
                                      //   Backend processes it.
                                      //   Backend returns data.
                                      //   next() is called automatically with that data.
                                       next:(respose :any)=>{
                                        this.authService.saveToken(respose.token); // save the token in local storage.
                                        this.msg="Registration successful!";
                                        this.router.navigate(['/']);
                                       },
                                       error: (err) => {
                                      this.msg = 'Registration failed. Try again.';
      }
                } );
               }
}
