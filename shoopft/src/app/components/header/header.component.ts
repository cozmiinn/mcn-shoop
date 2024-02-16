import {Component} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {DataBaseProducts} from "../../data/products_data/baseProduct_data/data_baseProducts";
import {DataBaseProductsService} from "../../data/products_data/baseProduct_data/data_baseProducts.service";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatToolbarModule, MatIconModule, NgOptimizedImage, RouterLink],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  products: DataBaseProducts[] = [];
  isLoggedIn: boolean = false;

  constructor(private productService: DataBaseProductsService) {
  }

  ngOnInit(): void {
    this.productService.getBaseProducts().subscribe((data: DataBaseProducts[]) => {
      this.products = data;
    });

  }
}
