import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DesignationWiseSalaryComponent } from './designation-wise-salary.component';

describe('DesignationWiseSalaryComponent', () => {
  let component: DesignationWiseSalaryComponent;
  let fixture: ComponentFixture<DesignationWiseSalaryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DesignationWiseSalaryComponent]
    });
    fixture = TestBed.createComponent(DesignationWiseSalaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
