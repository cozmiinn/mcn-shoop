import {ComponentFixture, TestBed} from '@angular/core/testing';

import {Products_searchComponent} from './products_search.component';

describe('ProductsComponent', () => {
  let component: Products_searchComponent;
  let fixture: ComponentFixture<Products_searchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Products_searchComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(Products_searchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
