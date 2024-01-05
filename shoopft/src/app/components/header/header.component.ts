import {Component} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {BrowserModule} from "@angular/platform-browser";
import {Data_products} from "../../data/products_data/baseProduct_data/data_products";
import {Data_productsService} from "../../data/products_data/baseProduct_data/data_products.service";

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatToolbarModule, MatIconModule, NgOptimizedImage],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  products: Data_products[] = [];

  constructor(private productService: Data_productsService) {
  }

  ngOnInit(): void {
    this.productService.getProducts().subscribe((data: Data_products[]) => {
        console.log(data);
        this.products = data;
      }
    )
  }
}
