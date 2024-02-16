import {TestBed} from '@angular/core/testing';

import {CartEntriesDataService} from './cartEntries-data.service';

describe('CartEntriesDataService', () => {
  let service: CartEntriesDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CartEntriesDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
