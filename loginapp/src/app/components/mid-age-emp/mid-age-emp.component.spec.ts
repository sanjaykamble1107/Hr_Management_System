import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MidAgeEmpComponent } from './mid-age-emp.component';

describe('MidAgeEmpComponent', () => {
  let component: MidAgeEmpComponent;
  let fixture: ComponentFixture<MidAgeEmpComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MidAgeEmpComponent]
    });
    fixture = TestBed.createComponent(MidAgeEmpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
