import {Component, Input} from '@angular/core';
import {NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {HeaderComponent} from "../header/header.component";


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
}
