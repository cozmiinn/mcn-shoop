import {TestBed} from '@angular/core/testing';

import {AttributeDataService} from './attribute-data.service';

describe('AttributeDataService', () => {
  let service: AttributeDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AttributeDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
