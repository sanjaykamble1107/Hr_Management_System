import { TestBed } from '@angular/core/testing';

import { ManagerAssignmentService } from './manager-assignment.service';

describe('ManagerAssignmentService', () => {
  let service: ManagerAssignmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ManagerAssignmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
