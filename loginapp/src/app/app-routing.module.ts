import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddEmployeeComponent } from './components/add-employee/add-employee.component';
import { AssignDepartmentComponent } from './components/assign-department/assign-department.component';
import { AssignTitleComponent } from './components/assign-title/assign-title.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DepartmentWiseSalaryComponent } from './components/department-wise-salary/department-wise-salary.component';
import { DepartmentComponent } from './components/department/department.component';
import { DesignationWiseSalaryComponent } from './components/designation-wise-salary/designation-wise-salary.component';
import { DesignationComponent } from './components/designation/designation.component';
import { EmpDashboardComponent } from './components/emp-dashboard/emp-dashboard.component';
import { EmpsSpecificTitleComponent } from './components/emps-specific-title/emps-specific-title.component';
import { FindemployeesComponent } from './components/findemployees/findemployees.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { MEmpDetailsComponent } from './components/m-emp-details/m-emp-details.component';
import { ManagerAfterSpecificDateComponent } from './components/manager-after-specific-date/manager-after-specific-date.component';
import { MidAgeEmpComponent } from './components/mid-age-emp/mid-age-emp.component';
import { RegisterComponent } from './components/register/register.component';
import { SpecificYearComponent } from './components/specific-year/specific-year.component';
import { UpdateEmployeeComponent } from './components/update-employee/update-employee.component';
import { UpdateComponent } from './components/update/update.component';
import { UserGuard} from './AuthGuard/user.guard'
import { ManagerAssignmentComponent } from './components/manager-assignment/manager-assignment.component';
const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path: "login",
    component: LoginComponent,
  },
  {
    path: "dashboard",
    component: DashboardComponent,
    canActivate:[UserGuard]
  },
  {
    path: "add-employee",
    component: AddEmployeeComponent,canActivate:[UserGuard]
  },
  {
    path: "update-employee",
    component: UpdateEmployeeComponent,canActivate:[UserGuard]
  },
  {
    path: "findemployees",
    component: FindemployeesComponent,canActivate:[UserGuard]
  },
  {
    path: "department", component: DepartmentComponent,canActivate:[UserGuard]

  },
  {
    path: "register", component: RegisterComponent
  },
  {
    path:"assign-department", component:AssignDepartmentComponent,canActivate:[UserGuard]

  },
  {
    path:"assign-title", component:AssignTitleComponent,canActivate:[UserGuard]

  },
  {
    path:"emp-dashboard", component:EmpDashboardComponent,canActivate:[UserGuard]

  },
  {
    path:"update", component:UpdateComponent,canActivate:[UserGuard]
  },
  {
    path:"MidAgeEmployeeDetails", component:MidAgeEmpComponent,canActivate:[UserGuard]
  },
  {
    path:"ManagersList", component:MEmpDetailsComponent,canActivate:[UserGuard]
  },
  {
    path:"DepartmentSalary", component:DepartmentWiseSalaryComponent,canActivate:[UserGuard]
  },
  {
    path:"DesignationSalary", component:DesignationWiseSalaryComponent,canActivate:[UserGuard]
  },
  {
    path:"TitleWise", component:EmpsSpecificTitleComponent,canActivate:[UserGuard]
  },
  {
    path:"CheckDesignations", component:DesignationComponent,canActivate:[UserGuard]
  },
  {
    path:"BecomeManager", component:ManagerAfterSpecificDateComponent,canActivate:[UserGuard]
  },
  {
    path:"SearchByDeptNo", component:SpecificYearComponent,canActivate:[UserGuard]
  },
  {
    path:"manager-assignment",component:ManagerAssignmentComponent,canActivate:[UserGuard]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
