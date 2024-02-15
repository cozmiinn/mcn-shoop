import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DataProductsVariant} from "./data-productsVariant";

@Injectable({
  providedIn: 'root'
})
export class DataProductsVariantService {
  private baseUrl = "http://localhost:8000";

  constructor(private http: HttpClient) {
  }


  getVariantProducts(): Observable<DataProductsVariant[]> {
    return this.http.get<DataProductsVariant[]>(`${this.baseUrl}/api/products/variant`);
  }
}
