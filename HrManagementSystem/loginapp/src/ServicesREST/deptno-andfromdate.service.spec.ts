import { TestBed } from '@angular/core/testing';

import { DeptnoAndfromdateService } from './deptno-andfromdate.service';

describe('DeptnoAndfromdateService', () => {
  let service: DeptnoAndfromdateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeptnoAndfromdateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
