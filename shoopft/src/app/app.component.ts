import {Component} from '@angular/core';
import {AppModule} from "./app.module";
import {RouterOutlet} from "@angular/router";
import {HomepageComponent} from "./components/pages/homepage/homepage.component";
import {HeaderComponent} from "./components/header/header.component";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone: true,
  imports: [
    AppModule,
    RouterOutlet,
    HomepageComponent,
    HeaderComponent
  ]
})
export class AppComponent {
}
