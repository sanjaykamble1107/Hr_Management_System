import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { EmpManagerfromdateService } from 'src/ServicesREST/emp-managerfromdate.service';
import { empManagerAftSpfDate } from 'src/ServicesREST/empManagerAftSpfDate';

@Component({
  selector: 'app-manager-after-specific-date',
  templateUrl: './manager-after-specific-date.component.html',
  styleUrls: ['./manager-after-specific-date.component.css']
})
export class ManagerAfterSpecificDateComponent {
  fromDate!: string;
  managers: empManagerAftSpfDate[] = [];
  pagedManagers: empManagerAftSpfDate[] = [];
  pageSize: number = 5; // Number of items to display per page
  pageSizeOptions: number[] = [5]; // Options for number of items per page
  totalManagers: number = 0; // Total number of managers

  constructor(private employeeService: EmpManagerfromdateService) {}

  getManagers() {
    this.employeeService.getManagersFromDate(this.fromDate)
      .subscribe(
        managers => {
          this.managers = managers;
          this.totalManagers = this.managers.length;
          this.updatePagedManagers(0); // Initial page, starting from index 0
          console.log(this.managers);
        },
        (error: HttpErrorResponse) => {
          const errorDetails = error.error.errorDetails;
          alert(errorDetails);
          console.log(errorDetails);
        }
      );
  }

  onPageChange(event: any) {
    const pageIndex = event.pageIndex;
    this.updatePagedManagers(pageIndex);
  }

  updatePagedManagers(pageIndex: number) {
    const startIndex = pageIndex * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.pagedManagers = this.managers.slice(startIndex, endIndex);
  }
}
