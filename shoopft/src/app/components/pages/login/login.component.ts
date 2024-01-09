import {Component} from '@angular/core';
import {HeaderComponent} from "../../header/header.component";
import {FormsModule} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    HeaderComponent,
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private router: Router) {
  }

  handleSubmit(): void {
    //some logic for login
  }

  handleRegisterClick(): void {
    this.router.navigate(['/register']).then(r => console.log(r));
  }

}
