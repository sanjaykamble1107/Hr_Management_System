import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { DesgnServiceService } from 'src/ServicesREST/desgn-service.service';

@Component({
  selector: 'app-designation-wise-salary',
  templateUrl: './designation-wise-salary.component.html',
  styleUrls: ['./designation-wise-salary.component.css']
})
export class DesignationWiseSalaryComponent {

  designationList: any[] = [];
  isDataLoaded: boolean = false;

  constructor(private designationService: DesgnServiceService) {}

  // Method to fetch designation details
  fetchData() {
    this.designationService.getDesignationDetails()
      .subscribe(
        (data: any[]) => {
          this.designationList = data;
          console.log(data);
          this.isDataLoaded = true;
        },
        (error: HttpErrorResponse) => {
          const errorDetails = error.error.errorDetails;
          alert(errorDetails);
          console.log(errorDetails);
        }
      );
  }
}
