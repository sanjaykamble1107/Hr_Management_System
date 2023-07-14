import { TestBed } from '@angular/core/testing';

import { EmpbytitleService } from './empbytitle.service';

describe('EmpbytitleService', () => {
  let service: EmpbytitleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmpbytitleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
