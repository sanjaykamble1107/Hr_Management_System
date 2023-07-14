import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { EmployeeService } from 'src/ServicesREST/employee.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent {
  employeeId!: number;
  rating!: number;
  promoted: boolean = false;

  constructor(private employeeService: EmployeeService) { }

  // Method to update the rating and promotion status of an employee
  updateRatingEmployee() {
    this.employeeService.updateRatingEmployee(this.employeeId, this.rating, this.promoted).subscribe(
      () => {
        console.log(this.promoted);
        alert('Employee rating and salary updated successfully');
        // You can perform additional actions here, such as refreshing the employee list
      },
      (error: HttpErrorResponse) => {
        const errorDetails = error.error.errorDetails;
        alert(errorDetails);
        console.log(errorDetails);
      }
    );
  }
}
