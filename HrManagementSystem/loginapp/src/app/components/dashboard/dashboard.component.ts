import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  constructor(private authService: AuthService, private router: Router) { }

  // Method to log out the user
  logout(): void {
    // Call the logout method from the AuthService
    this.authService.logout();

    // Navigate to the login page
    this.router.navigate(['/login']);
  }
}
