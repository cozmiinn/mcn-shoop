import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BaseProducts} from "./baseProducts-data";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BaseProductsService {
  private baseUrl = "http://localhost:8000";

  constructor(private http: HttpClient) {
  }

  getBaseProducts(): Observable<BaseProducts[]> {
    return this.http.get<BaseProducts[]>(`${this.baseUrl}/api/products`);
  }
}
