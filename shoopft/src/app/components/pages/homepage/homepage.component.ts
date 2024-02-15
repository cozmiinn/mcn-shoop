import {Component} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {HeaderComponent} from "../../header/header.component";
import {UserComponent} from "../../user/user.component";

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [CommonModule, HeaderComponent, UserComponent, NgOptimizedImage],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent {

}
