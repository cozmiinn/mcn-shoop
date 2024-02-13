import {Component, OnInit} from '@angular/core';
import {HeaderComponent} from "../../header/header.component";
import {NgForOf} from "@angular/common";
import {DataProductsVariant} from "../../../data/products_data/productsVariant_data/data-productsVariant";
import {DataBaseProducts} from "../../../data/products_data/baseProduct_data/data_baseProducts";
import {DataBaseProductsService} from "../../../data/products_data/baseProduct_data/data_baseProducts.service";
import {ProductComponent} from "../../product/product.component";

@Component({
  selector: 'app-products-search',
  standalone: true,
  imports: [
    HeaderComponent,
    NgForOf,
    ProductComponent
  ],
  templateUrl: './products_search.component.html',
  styleUrl: './products_search.component.css'
})
export class Products_searchComponent implements OnInit {
  name: DataProductsVariant[] = [];
  productVariant: DataBaseProducts[] = [];
  type: DataBaseProducts[] = [];
  selectedCategory: string = '';
  filtered: any[] = [];
  searchWord: string = '';
  categories: Set<string> = new Set();

  constructor(private variantService: DataBaseProductsService) {
  }

  ngOnInit() {
    this.variantService.getBaseProducts().subscribe((data: DataBaseProducts[]) => {
      this.productVariant = data;
      this.categories = new Set(data.map((product: any) => product.type));
    });
  }

  handleCategorySelect(category: string) {
    this.selectedCategory = category;
    this.filterProducts();
  }

  handleSearch(event: Event) {
    const target = event.target as HTMLInputElement;
    this.searchWord = target.value;
    this.filterProducts();
  }

  filterProducts() {
    let filteredByCategory = this.filterByCategory();

    this.filtered = filteredByCategory
      .map(product => ({
        ...product,
        productVariants: this.filterVariantsBySearchWord(product.productVariants)
      }))
      .filter(product => product.productVariants.length > 0);
  }

  filterByCategory() {
    return this.selectedCategory
      ? this.productVariant.filter(product => product.type === this.selectedCategory)
      : [...this.productVariant];
  }

  filterVariantsBySearchWord(variants: DataProductsVariant[]) {
    return this.searchWord
      ? variants.filter(variant =>
        variant.name.toLowerCase().includes(this.searchWord.toLowerCase()))
      : variants;
  }
}

//
// import {Component, OnInit} from '@angular/core';
// import {HeaderComponent} from "../../header/header.component";
// import {NgForOf} from "@angular/common";
// import {DataProductsVariant} from "../../../data/products_data/productsVariant_data/data-productsVariant";
// import {DataBaseProducts} from "../../../data/products_data/baseProduct_data/data_baseProducts";
// import {DataBaseProductsService} from "../../../data/products_data/baseProduct_data/data_baseProducts.service";
// import {ProductComponent} from "../../product/product.component";
//
// @Component({
//   selector: 'app-products-search',
//   standalone: true,
//   imports: [
//     HeaderComponent,
//     NgForOf,
//     ProductComponent
//   ],
//   templateUrl: './products_search.component.html',
//   styleUrl: './products_search.component.css'
// })
// export class Products_searchComponent implements OnInit {
//   name: DataProductsVariant[] = [];
//   productVariant: DataBaseProducts[] = [];
//   type: DataBaseProducts[] = [];
//   selectedCategory: string = '';
//   filtered: any[] = [];
//   searchWord: string = '';
//   categories: Set<string> = new Set();
//
//   constructor(private variantService: DataBaseProductsService) {
//   }
//
//   ngOnInit() {
//     this.variantService.getBaseProducts().subscribe((data: DataBaseProducts[]) => {
//       this.productVariant = data;
//       this.categories = new Set(data.map((product: any) => product.type));
//     });
//   }
//
//   handleCategorySelect(category: string) {
//     this.selectedCategory = category;
//     this.filterProducts();
//   }
//
//   handleSearch(event: Event) {
//     const target = event.target as HTMLInputElement;
//     this.searchWord = target.value;
//     this.filterProducts();
//   }
//
//   filterProducts() {
//     let filteredByCategory = this.filterByCategory();
//
//     this.filtered = filteredByCategory
//       .map(product => ({
//         ...product,
//         productVariants: this.filterVariantsBySearchWord(product.productVariants)
//       }))
//       .filter(product => product.productVariants.length > 0);
//   }
//
//   filterByCategory() {
//     return this.selectedCategory
//       ? this.productVariant.filter(product => product.type === this.selectedCategory)
//       : [...this.productVariant];
//   }
//
//   filterVariantsBySearchWord(variants: DataProductsVariant[]) {
//     return this.searchWord
//       ? variants.filter(variant =>
//         variant.name.toLowerCase().includes(this.searchWord.toLowerCase()) ||
//         variant.description.toLowerCase().includes(this.searchWord.toLowerCase()))
//       : variants;
//   }
//
//
// }
