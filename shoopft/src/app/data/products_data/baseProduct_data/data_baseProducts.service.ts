import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DataBaseProducts} from "./data_baseProducts";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DataBaseProductsService {
  private baseUrl = "http://localhost:8000";

  constructor(private http: HttpClient) {
  }

  getBaseProducts(): Observable<DataBaseProducts[]> {
    return this.http.get<DataBaseProducts[]>(`${this.baseUrl}/api/products`);
  }
}
