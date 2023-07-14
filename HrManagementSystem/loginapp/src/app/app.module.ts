import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import { HomeComponent } from './components/home/home.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import { AddEmployeeComponent } from './components/add-employee/add-employee.component';
import { UpdateEmployeeComponent } from './components/update-employee/update-employee.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { HttpClientModule } from '@angular/common/http';
import {MatPaginatorModule} from '@angular/material/paginator';
import { FindemployeesComponent } from './components/findemployees/findemployees.component';
import { DepartmentComponent } from './components/department/department.component';
import {NgxPaginationModule} from 'ngx-pagination';
import { RegisterComponent } from './components/register/register.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { hi_IN } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import hi from '@angular/common/locales/hi';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';
import { AssignDepartmentComponent } from './components/assign-department/assign-department.component';
import { AssignTitleComponent } from './components/assign-title/assign-title.component';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { DepartmentWiseSalaryComponent } from './components/department-wise-salary/department-wise-salary.component';
import { DesignationWiseSalaryComponent } from './components/designation-wise-salary/designation-wise-salary.component';
import { EmpDashboardComponent } from './components/emp-dashboard/emp-dashboard.component';
import { MatCardModule } from '@angular/material/card';
import { MEmpDetailsComponent } from './components/m-emp-details/m-emp-details.component';
import { ManagerAfterSpecificDateComponent } from './components/manager-after-specific-date/manager-after-specific-date.component';
import { UpdateComponent } from './components/update/update.component';
import { MidAgeEmpComponent } from './components/mid-age-emp/mid-age-emp.component';
import { EmpsSpecificTitleComponent } from './components/emps-specific-title/emps-specific-title.component';
import { DesignationComponent } from './components/designation/designation.component';
import { SpecificYearComponent } from './components/specific-year/specific-year.component';
import { ManagerAssignmentComponent } from './components/manager-assignment/manager-assignment.component';
import { MatMenuModule } from '@angular/material/menu'
registerLocaleData(hi);

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavBarComponent,
    LoginComponent,
    DashboardComponent,
    AddEmployeeComponent,
    UpdateEmployeeComponent,
    FindemployeesComponent,
    DepartmentComponent,
    RegisterComponent,
    AssignDepartmentComponent,
    AssignTitleComponent,
    DepartmentWiseSalaryComponent,
    DesignationWiseSalaryComponent,
    EmpDashboardComponent,
    MEmpDetailsComponent,
    ManagerAfterSpecificDateComponent,
    UpdateComponent,
    MidAgeEmpComponent,
    EmpsSpecificTitleComponent,
    DesignationComponent,
    SpecificYearComponent,
    ManagerAssignmentComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatDatepickerModule,
    HttpClientModule,
    MatPaginatorModule,
    NgxPaginationModule,
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    CommonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatSelectModule,
    MatCardModule,
    MatMenuModule
    
  ],
  providers: [
    { provide: NZ_I18N, useValue: hi_IN }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
