import {Component} from '@angular/core';
import {AppModule} from "./app.module";
import {RouterOutlet} from "@angular/router";
import {HomepageComponent} from "./components/pages/homepage/homepage.component";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone: true,
  imports: [
    AppModule,
    RouterOutlet,
    HomepageComponent
  ]
})
export class AppComponent {
  title: string;

  constructor() {
    this.title = 'SpeedyShop';
  }
}
