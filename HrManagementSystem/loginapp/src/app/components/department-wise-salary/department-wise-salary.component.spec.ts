import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmentWiseSalaryComponent } from './department-wise-salary.component';

describe('DepartmentWiseSalaryComponent', () => {
  let component: DepartmentWiseSalaryComponent;
  let fixture: ComponentFixture<DepartmentWiseSalaryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DepartmentWiseSalaryComponent]
    });
    fixture = TestBed.createComponent(DepartmentWiseSalaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
