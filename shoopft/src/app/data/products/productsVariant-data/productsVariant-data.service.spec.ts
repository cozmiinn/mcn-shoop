import {TestBed} from '@angular/core/testing';

import {DataPVariantService} from './productsVariant-data.service';

describe('DataPVariantService', () => {
  let service: DataPVariantService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataPVariantService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
