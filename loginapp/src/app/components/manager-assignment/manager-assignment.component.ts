import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { ManagerAssignmentService } from 'src/ServicesREST/manager-assignment.service';

@Component({
  selector: 'app-manager-assignment',
  templateUrl: './manager-assignment.component.html',
  styleUrls: ['./manager-assignment.component.css']
})
export class ManagerAssignmentComponent {
  empNo!: number;
  deptNo!: string;
  fromDate!: string;
  toDate!: string;

  constructor(private managerAssignmentService: ManagerAssignmentService) { }

  // Method to assign a manager to a department
  assignManager() {
    // Prepare the request data
    const requestData = {
      empNo: this.empNo,
      deptNo: this.deptNo,
      fromDate: this.fromDate,
      toDate: this.toDate
    };

    // Call the manager assignment service to assign the manager
    this.managerAssignmentService.assignManager(requestData).subscribe(
      response => {
        console.log('Assignment successful:', response);
        alert('Assignment successful:');
      },
      (error: HttpErrorResponse) => {
        const errorDetails = error.error.errorDetails;
        alert(errorDetails);
        console.log(errorDetails);
      }
    );
  }
}
