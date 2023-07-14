import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerAssignmentComponent } from './manager-assignment.component';

describe('ManagerAssignmentComponent', () => {
  let component: ManagerAssignmentComponent;
  let fixture: ComponentFixture<ManagerAssignmentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ManagerAssignmentComponent]
    });
    fixture = TestBed.createComponent(ManagerAssignmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
