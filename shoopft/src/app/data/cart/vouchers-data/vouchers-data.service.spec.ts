import { TestBed } from '@angular/core/testing';

import { VouchersDataService } from './vouchers-data.service';

describe('VouchersDataService', () => {
  let service: VouchersDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VouchersDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
