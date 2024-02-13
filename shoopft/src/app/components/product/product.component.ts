import {Component, Input, OnInit} from '@angular/core';
import {NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {DataProductsVariant} from "../../data/products_data/productsVariant_data/data-productsVariant";
import {DataProductsVariantService} from "../../data/products_data/productsVariant_data/data-productsVariant.service";
import {HeaderComponent} from "../header/header.component";
import {Products_searchComponent} from "../pages/products-search/products_search.component";
import {DataBaseProductsService} from "../../data/products_data/baseProduct_data/data_baseProducts.service";
import {DataBaseProducts} from "../../data/products_data/baseProduct_data/data_baseProducts";
import {filter} from "rxjs";

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [
    NgOptimizedImage,
    HeaderComponent,
    NgForOf,
    NgIf
  ],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {
  @Input() product: any;


  // handleAddToWish(variant: any): void {
  // }
  protected readonly filter = filter;
}
