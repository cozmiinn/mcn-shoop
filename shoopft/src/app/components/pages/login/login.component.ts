import {Component, OnInit} from '@angular/core';
import {HeaderComponent} from "../../header/header.component";
import {FormsModule} from "@angular/forms";
import {Router} from "@angular/router";
import {UserService} from "../../../data/user/users-data/user.service";
import {User} from "../../../data/user/users-data/user-data";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    HeaderComponent,
    FormsModule,
    NgIf
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';
  authError: string = '';
  role: User[] = [];

  constructor(private router: Router, private customer: UserService) {
  };

  ngOnInit(): void {
    // this.customer.reloadSeller();
  }

  logIn(data: User): void {
    // this.customer.userLogIn(data);
    this.customer.isLoginError.subscribe((isError) => {
      if (isError) {
        this.authError = 'Invalid email or password';
      }
    });
  }

  handleRegisterClick(): void {
    this.router.navigate(['/register']).then(r => console.log(r));
  }


}
