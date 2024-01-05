import {TestBed} from '@angular/core/testing';

import {Data_userService} from './data_user.service';

describe('UserService', () => {
  let service: Data_userService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Data_userService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
