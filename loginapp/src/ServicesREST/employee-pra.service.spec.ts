import { TestBed } from '@angular/core/testing';

import { EmployeePRAService } from './employee-pra.service';

describe('EmployeePRAService', () => {
  let service: EmployeePRAService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmployeePRAService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
