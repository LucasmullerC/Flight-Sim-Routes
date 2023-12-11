import { TestBed } from '@angular/core/testing';

import { UnixTimeService } from './unix-time.service';

describe('UnixTimeService', () => {
  let service: UnixTimeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UnixTimeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
