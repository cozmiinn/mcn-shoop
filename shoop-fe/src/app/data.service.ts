import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class DataU {

  private baseUrl = "http://localhost:8000/api/users";

  constructor(private http: HttpClient) {
  }

  getUsers(): Observable<DataU[]> {
    return this.http.get<DataU[]>(`${this.baseUrl}`);
  }
}
