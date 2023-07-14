import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignDepartmentComponent } from './assign-department.component';

describe('AssignDepartmentComponent', () => {
  let component: AssignDepartmentComponent;
  let fixture: ComponentFixture<AssignDepartmentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AssignDepartmentComponent]
    });
    fixture = TestBed.createComponent(AssignDepartmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
