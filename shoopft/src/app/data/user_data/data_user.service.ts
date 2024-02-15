import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from "rxjs";
import {Data_user} from "./data_user";
import {Router} from "@angular/router";


@Injectable({
  providedIn: 'root'
})

export class Data_userService {
  private baseUrl = "http://localhost:8000";
  isSellerLoggedIn = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient, private router: Router) {
  }


  getUsers(): Observable<Data_user[]> {
    return this.http.get<Data_user[]>(`${this.baseUrl}/api/users`);
  }

  userSignIn(data: any) {
    return this.http.get(`${this.baseUrl}/api/users`, data);
  }

  userSignUp(data: Data_user) {
    return this.http.post(`${this.baseUrl}/api/users/add`,
      data,
      {observe: 'response'}).subscribe((result) => {
      console.warn(result);
      if (result) {
        localStorage.setItem('customer', JSON.stringify(result));
        this.router.navigate(['customer-page']);
      }
    });
  }

  reloadSeller() {
    if (localStorage.getItem('customer')) {
      this.isSellerLoggedIn.next(true);
      this.router.navigate(['customer-page']);

    }
  }
}
