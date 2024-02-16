// import {Component, OnInit} from '@angular/core';
// import {MatIconModule} from "@angular/material/icon";
// import {MatChipsModule} from "@angular/material/chips";
// import {FormBuilder, FormGroup, FormsModule, Validators} from "@angular/forms";
// import {Router} from "@angular/router";
// import {HeaderComponent} from "../../header/header.component";
// import {User_dataService} from "../../../data/user_data/data_user.service";
// import {User_data} from "../../../data/user_data/data_user";
//
// @Component({
//   selector: 'app-register',
//   standalone: true,
//   imports: [
//     MatIconModule,
//     MatChipsModule,
//     FormsModule,
//     HeaderComponent
//   ],
//   templateUrl: './register.component.html',
//   styleUrl: './register.component.css'
// })
// export class RegisterComponent implements OnInit {
//   registrationForm: FormGroup;
//   firstName: string = '';
//   lastName: string = '';
//   middleName: string = '';
//   email: string = '';
//   password: string = '';
//   streetLine: string = '';
//   postalCode: string = '';
//   city: string = '';
//   county: string = '';
//   country: string = '';
//
//   constructor(private fb: FormBuilder, private router: Router, private customer: User_dataService) {
//     this.registrationForm = this.fb.group({
//       fistName: ['', Validators.required],
//       lastName: ['', [Validators.required, Validators.email]],
//       middleName: ['', Validators.required],
//       email: ['', Validators.required],
//       password: ['', Validators.required],
//       // streetLine: ['', Validators.required],
//       // postalCode: ['', Validators.required],
//       // city: ['', Validators.required],
//       // county: ['', Validators.required],
//       // country: ['', Validators.required]
//     });
//   };
//
//   ngOnInit(): void {
//     this.customer.reloadSeller();
//   }
//
//   signUp(data: User_data): void {
//     console.warn(data);
//     this.customer.userSignUp(data);
//   };
//
//   handleLoginClick(): void {
//     this.router.navigate(['/login']).then(r => console.log(r));
//   }
// }
