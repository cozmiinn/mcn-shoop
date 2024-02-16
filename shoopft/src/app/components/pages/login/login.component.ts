import {Component, OnInit} from '@angular/core';
import {HeaderComponent} from "../../header/header.component";
import {FormBuilder, FormGroup, FormsModule, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {User_dataService} from "../../../data/user_data/user_data.service";
import {User_data} from "../../../data/user_data/user_data";
import {NgIf} from "@angular/common";
import {BehaviorSubject} from "rxjs";

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

  constructor(private fb: FormBuilder, private router: Router, private customer: User_dataService) {
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

  signUp(data: User_data): void {
    console.warn(data);
    this.customer.userSignUp(data);
  };

  logIn(data: User_data): void {
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
