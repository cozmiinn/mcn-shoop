import {Component} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {BaseProducts} from "../../data/products/baseProduct-data/baseProducts-data";
import {BaseProductsService} from "../../data/products/baseProduct-data/baseProducts-data.service";
import {RouterLink, RouterLinkActive} from "@angular/router";

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatToolbarModule, MatIconModule, NgOptimizedImage, RouterLink, RouterLinkActive],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  products: BaseProducts[] = [];
  isLoggedIn: boolean = false;

  constructor(private productService: BaseProductsService) {
  }

  ngOnInit(): void {
    this.productService.getBaseProducts().subscribe((data: BaseProducts[]) => {
      this.products = data;
    });

  }
}
