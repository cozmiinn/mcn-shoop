import {Component, OnInit} from '@angular/core';
import {HeaderComponent} from "../../header/header.component";
import {FormBuilder, FormGroup, FormsModule, Validators} from "@angular/forms";
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
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  firstName: string = '';
  lastName: string = '';
  middleName: string = '';
  email: string = '';
  password: string = '';
  streetLine: string = '';
  postalCode: string = '';
  city: string = '';
  county: string = '';
  country: string = '';

  showLogin: boolean = false;
  registrationForm: FormGroup;
  authError: String = '';

  constructor(private fb: FormBuilder, private router: Router, private customer: UserService) {
    this.registrationForm = this.fb.group({
      fistName: ['', Validators.required],
      lastName: ['', [Validators.required, Validators.email]],
      middleName: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      // streetLine: ['', Validators.required],
      // postalCode: ['', Validators.required],
      // city: ['', Validators.required],
      // county: ['', Validators.required],
      // country: ['', Validators.required]
    });
  };

  ngOnInit(): void {
    this.customer.reloadSeller();
  }

  signUp(data: User): void {
    console.warn(data);
    this.customer.userSignUp(data);
  };

  logIn(data: User): void {
    this.customer.userLogIn(data);
    this.customer.isLoginError.subscribe((isError) => {
      if (isError) {
        this.authError = 'Invalid email or password';
      }
    });
  }

  openLogin() {
    this.showLogin = true;
  }

  openRegister() {
    this.showLogin = false;
  }

}
