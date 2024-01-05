import { Component } from '@angular/core';
import {CommonModule, NgForOf} from "@angular/common";
import {UserComponent} from "../user/user.component";

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [
    NgForOf, CommonModule, UserComponent
  ],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent {

}
