import {DataBaseProducts} from './baseProducts-data';

describe('Products', () => {
  it('should create an instance', () => {
    expect(new DataBaseProducts()).toBeTruthy();
  });
});
