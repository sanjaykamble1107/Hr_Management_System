import { TestBed } from '@angular/core/testing';

import { DeptWiseSalaryService } from './dept-wise-salary.service';

describe('DeptWiseSalaryService', () => {
  let service: DeptWiseSalaryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeptWiseSalaryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
