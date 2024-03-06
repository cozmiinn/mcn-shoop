import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from "rxjs";
import {User} from "./user-data";
import {Router} from "@angular/router";


@Injectable({
  providedIn: 'root'
})

export class UserService {
  private baseUrl = "http://localhost:8000";
  isSellerLoggedIn = new BehaviorSubject<boolean>(false);
  isLoginError = new EventEmitter<boolean>(false);

  constructor(private http: HttpClient, private router: Router) {
  }


  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/api/users`);
  }


  userSignUp(data: User) {
    return this.http.post(`${this.baseUrl}/api/users/add`,
      data,
      {observe: 'response'}).subscribe((result) => {
      console.warn(result);
      if (result) {
        localStorage.setItem('customer', JSON.stringify(result));
        this.router.navigate(['customers']);
      }
    });
  }

  reloadSeller() {
    if (localStorage.getItem('customer')) {
      this.isSellerLoggedIn.next(true);
      this.router.navigate(['customers']);

    }
  }

  // userLogIn(data: User) {
  //   this.http.get(`http://localhost:8000/api/users`,
  //     {observe: 'response'}).subscribe((result: any) => {
  //     console.warn(result);
  //     if (result && result.body) {
  //       this.isLoginError.emit(false);
  //       localStorage.setItem('customer', JSON.stringify(result));
  //       this.router.navigate(['customers']);
  //     } else {
  //       this.isLoginError.emit(true);
  //     }
  //   });
  // }
}
