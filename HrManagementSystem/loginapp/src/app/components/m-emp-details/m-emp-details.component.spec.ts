import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MEmpDetailsComponent } from './m-emp-details.component';

describe('MEmpDetailsComponent', () => {
  let component: MEmpDetailsComponent;
  let fixture: ComponentFixture<MEmpDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MEmpDetailsComponent]
    });
    fixture = TestBed.createComponent(MEmpDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
