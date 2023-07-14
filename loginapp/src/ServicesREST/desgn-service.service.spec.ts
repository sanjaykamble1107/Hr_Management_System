import { TestBed } from '@angular/core/testing';

import { DesgnServiceService } from './desgn-service.service';

describe('DesgnServiceService', () => {
  let service: DesgnServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DesgnServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
