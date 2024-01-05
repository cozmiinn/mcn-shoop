import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Data_products} from "./data_products";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class Data_productsService {
  private baseUrl = "http://localhost:8000/api/products";

  constructor(private http: HttpClient) {
  }


  getProducts(): Observable<Data_products[]> {
    return this.http.get<Data_products[]>(`${this.baseUrl}`);
  }
}
