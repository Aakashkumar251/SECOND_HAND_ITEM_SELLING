import { Component, signal } from '@angular/core'; // This line imports the Component decorator and the signal function
//  from the Angular core library. 
// The Component decorator is used to define an Angular component, while the signal function is used to create reactive 
// signals that can be used for state management within the component.
import { RouterOutlet } from '@angular/router'; // This line imports the RouterOutlet directive from the Angular router library.
// RouterOutlet is a directive that acts as a placeholder in the template where the routed components will be displayed based on the current route.
import { NavbarComponent } from './components/navbar/navbar';
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent],// This line imports the RouterOutlet directive, which is used to display the components based on the current route. 
  // It acts as a placeholder in the template where the routed components will be displayed.
  template: `
    <app-navbar></app-navbar>
    <router-outlet></router-outlet>
  `
  // templateUrl: './app.html',
  // styleUrl: './app.css'
})
export class App { // This is the main component of the Angular application. It serves as the root component that bootstraps the
//  application and contains the router outlet where other components will be displayed based on the routing configuration.
  protected readonly title = signal('frontend');
}
