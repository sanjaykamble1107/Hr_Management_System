import { TestBed } from '@angular/core/testing';

import { AssignTitleService } from './assign-title.service';

describe('AssignTitleService', () => {
  let service: AssignTitleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AssignTitleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
