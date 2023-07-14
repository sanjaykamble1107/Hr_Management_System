import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerAfterSpecificDateComponent } from './manager-after-specific-date.component';

describe('ManagerAfterSpecificDateComponent', () => {
  let component: ManagerAfterSpecificDateComponent;
  let fixture: ComponentFixture<ManagerAfterSpecificDateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ManagerAfterSpecificDateComponent]
    });
    fixture = TestBed.createComponent(ManagerAfterSpecificDateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
