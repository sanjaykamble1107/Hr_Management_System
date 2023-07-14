import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  username!: string;
  password!: string;
  error!: string;

  constructor(private authService: AuthService, private router: Router) {}

  // Method to register a new user
  registerUser() {
    if (this.username == "" || this.password == "") {
      alert("Input the values");
    }

    // Call the authentication service's registerUser method with username and password
    let rsp = this.authService.registerUser(this.username, this.password);

    rsp.subscribe(
      (data) => {
        console.log(data);
        alert("Registered successfully. Please login.");
        this.router.navigate(['/dashboard']); // Navigate to the dashboard
      },
      (error) => {
        alert("User already exists. Please Login"); // Handle the specific error scenario
      }
    );
  }
}
