import {TestBed} from '@angular/core/testing';

import {Data_productsService} from './data_products.service';

describe('DataProductsService', () => {
  let service: Data_productsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Data_productsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
