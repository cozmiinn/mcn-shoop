import {Component} from '@angular/core';
import {HeaderComponent} from "../../header/header.component";
import {FormsModule} from "@angular/forms";
import {Router} from "@angular/router";
import {Data_userService} from "../../../data/user_data/data_user.service";

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

  constructor(private router: Router, private costumer: Data_userService) {
  }

  handleSubmit(data: object): void {
    console.warn(data);
    this.costumer.userSignIn(data).subscribe((result) => {
      console.log(result);
    })
  }

  handleRegisterClick(): void {
    this.router.navigate(['/register']).then(r => console.log(r));
  }

}
