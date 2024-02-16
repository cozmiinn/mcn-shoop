import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProductsVariant} from "./productsVariant-data";

@Injectable({
  providedIn: 'root'
})
export class ProductsVariantService {
  private baseUrl = "http://localhost:8000";

  constructor(private http: HttpClient) {
  }


  getVariantProducts(): Observable<ProductsVariant[]> {
    return this.http.get<ProductsVariant[]>(`${this.baseUrl}/api/products/variant`);
  }
}
