import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { EmpbytitleService } from 'src/ServicesREST/empbytitle.service';

@Component({
  selector: 'app-emps-specific-title',
  templateUrl: './emps-specific-title.component.html',
  styleUrls: ['./emps-specific-title.component.css']
})
export class EmpsSpecificTitleComponent {
  title!: string;
  employeeList: any[] = [];
  pagedEmployeeList: any[] = [];
  pageSize: number = 5; // Number of items to display per page
  pageSizeOptions: number[] = [5]; // Options for number of items per page
  totalEmployees: number = 0; // Total number of employees

  constructor(private employeeService: EmpbytitleService) {}

  searchEmployeesByTitle() {
    console.log("In the title");
    console.log(this.title);

    this.employeeService.searchEmployeesByTitle(this.title)
      .subscribe((employees: any[]) => {
        this.employeeList = employees;
        this.totalEmployees = this.employeeList.length;
        this.updatePagedEmployees(0); // Initial page, starting from index 0
        console.log([...employees]);
      });

    console.log("Last the title");
  }

  onPageChange(event: any) {
    const pageIndex = event.pageIndex;
    this.updatePagedEmployees(pageIndex);
  }

  updatePagedEmployees(pageIndex: number) {
    const startIndex = pageIndex * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.pagedEmployeeList = this.employeeList.slice(startIndex, endIndex);
  }
}