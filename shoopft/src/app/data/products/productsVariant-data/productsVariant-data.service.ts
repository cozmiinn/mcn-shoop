import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProductsVariant} from "./productsVariant-data";

@Injectable({
  providedIn: 'root'
})
export class ProductsVariantService {
  constructor(private http: HttpClient) {
  }
}
