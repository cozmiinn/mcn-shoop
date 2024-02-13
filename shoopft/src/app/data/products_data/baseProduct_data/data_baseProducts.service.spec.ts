import {TestBed} from '@angular/core/testing';

import {DataBaseProductsService} from './data_baseProducts.service';

describe('DataProductsService', () => {
  let service: DataBaseProductsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataBaseProductsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
