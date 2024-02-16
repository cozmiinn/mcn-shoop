import {TestBed} from '@angular/core/testing';

import {User_dataService} from './user_data.service';

describe('UserService', () => {
  let service: User_dataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(User_dataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
