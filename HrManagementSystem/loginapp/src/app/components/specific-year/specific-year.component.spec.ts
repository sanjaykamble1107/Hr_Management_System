import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpecificYearComponent } from './specific-year.component';

describe('SpecificYearComponent', () => {
  let component: SpecificYearComponent;
  let fixture: ComponentFixture<SpecificYearComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SpecificYearComponent]
    });
    fixture = TestBed.createComponent(SpecificYearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
