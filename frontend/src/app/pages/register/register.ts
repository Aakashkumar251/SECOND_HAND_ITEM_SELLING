import { Component } from '@angular/core';
import { AuthService } from '../../services/auth';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-register',
  imports: [FormsModule,RouterLink], // This line imports the FormsModule and RouterLink directives from the Angular forms and router libraries, respectively.
  // FormsModule is used to work with forms in Angular, allowing us to use features like ngModel for two-way data binding. 
  // RouterLink is used to create links that navigate to different routes in the application.
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
    // Router vs RouterLink: Router is a service that allows you to navigate programmatically in your component's 
    // TypeScript code, while RouterLink is a directive that you use in your HTML templates to create links
    //  that navigate to different routes when clicked. In this case, we are using Router in the TypeScript 
    // code to navigate to the home page after successful registration, and we would use RouterLink in the HTML
    //  template to create a link for users who already have an account to navigate to the login page.
  constructor(private authService:AuthService,private router:Router){} // The constructor is used to inject the AuthService 
  // and Router services into the component. Basically making the object of Authsevice as Router same as Scanner sc=new Scanner();
                     
               onRegister(){
                // this.authservice means Get the authService that belongs to THIS component.
                this.authService.register(this.user).subscribe( { // subscribe basically send the authservice me jo fn hai register wala
                                          // subscribe means go!!
                                        // subscribe is used to handle the response from the register method of authService. 
                                       // It is an asynchronous operation, so we need to subscribe to it to get the response when it is available.
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
