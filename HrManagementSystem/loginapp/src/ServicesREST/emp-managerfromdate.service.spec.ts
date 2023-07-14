import { TestBed } from '@angular/core/testing';

import { EmpManagerfromdateService } from './emp-managerfromdate.service';

describe('EmpManagerfromdateService', () => {
  let service: EmpManagerfromdateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmpManagerfromdateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
