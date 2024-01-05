import {Component, OnInit} from '@angular/core';
import {Data_user} from "../../data/user_data/data_user";
import {Data_userService} from "../../data/user_data/data_user.service";
import {CommonModule, NgForOf} from "@angular/common";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrl: './user.component.css',
  imports: [
    CommonModule
  ],
  standalone: true
})
export class UserComponent implements OnInit {
  users: Data_user[] = [];

  constructor(private userService: Data_userService) {
  }

  ngOnInit(): void {
    this.userService.getUsers().subscribe((data: Data_user[]) => {
        console.log(data);
        this.users = data;
      }
    )
  }
}
