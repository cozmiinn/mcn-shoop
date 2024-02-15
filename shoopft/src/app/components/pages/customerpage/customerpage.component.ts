import {Component} from '@angular/core';
import {HeaderComponent} from "../../header/header.component";
import {UserComponent} from "../../user/user.component";

@Component({
  selector: 'app-customerpage',
  standalone: true,
  imports: [
    HeaderComponent,
    UserComponent
  ],
  templateUrl: './customerpage.component.html',
  styleUrl: './customerpage.component.css'
})
export class CustomerPageComponent {

}
