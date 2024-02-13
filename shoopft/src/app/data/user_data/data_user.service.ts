import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {Data_user} from "./data_user";


@Injectable({
  providedIn: 'root'
})
export class Data_userService {

  private baseUrl = "http://localhost:8000";

  constructor(private http: HttpClient) {
  }

  getUsers(): Observable<Data_user[]> {
    return this.http.get<Data_user[]>(`${this.baseUrl}/api/users`);
  }

  userSignIn(data: any) {
    return this.http.get(`${this.baseUrl}/api/users`, data);
  }

  userSignUp(data: any) {
    return this.http.post(`${this.baseUrl}/api/users/add`, data);
  }
}
