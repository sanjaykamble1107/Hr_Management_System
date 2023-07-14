import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpsSpecificTitleComponent } from './emps-specific-title.component';

describe('EmpsSpecificTitleComponent', () => {
  let component: EmpsSpecificTitleComponent;
  let fixture: ComponentFixture<EmpsSpecificTitleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EmpsSpecificTitleComponent]
    });
    fixture = TestBed.createComponent(EmpsSpecificTitleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
