import {Component} from '@angular/core';
import {MatIconModule} from "@angular/material/icon";
import {MatChipsModule} from "@angular/material/chips";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {HeaderComponent} from "../../header/header.component";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    MatIconModule,
    MatChipsModule,
    FormsModule,
    ReactiveFormsModule,
    HeaderComponent
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registrationForm: FormGroup;
  skills = ['React', 'Node', '.NET'];

  constructor(private fb: FormBuilder, private router: Router) {
    this.registrationForm = this.fb.group({
      name: ['', Validators.required],
      lastName: ['', [Validators.required, Validators.email]],
      middleName: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      streetLine: ['', Validators.required],
      postalCode: ['', Validators.required],
      city: ['', Validators.required],
      county: ['', Validators.required],
      country: ['', Validators.required]
    });
  }

  handleLoginClick(): void {
    this.router.navigate(['/login']);
  }

  handleSubmit(): void {
    if (this.registrationForm.valid) {
      console.log('Înregistrare reușită', this.registrationForm.value);
      // logica de înregistrare
    }
  }

}
